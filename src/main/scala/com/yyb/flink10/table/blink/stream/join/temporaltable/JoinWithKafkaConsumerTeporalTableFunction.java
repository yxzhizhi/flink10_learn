package com.yyb.flink10.table.blink.stream.join.temporaltable;

import com.alibaba.fastjson.JSON;
import com.yyb.flink10.commonEntity.Current1;
import com.yyb.flink10.commonEntity.Rate;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.table.api.*;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.ConnectTableDescriptor;
import org.apache.flink.table.descriptors.Json;
import org.apache.flink.table.descriptors.Kafka;
import org.apache.flink.table.descriptors.Schema;
import org.apache.flink.table.functions.TemporalTableFunction;
import org.apache.flink.types.Row;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Author yyb
 * @Description kafka 作为 流式维度表
 * @Date Create in 2020-07-27
 * @Time 16:59
 */
public class JoinWithKafkaConsumerTeporalTableFunction {
    public static void main(String[] args) throws Exception {
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        StreamTableEnvironment blinkTableEnv = StreamTableEnvironment.create(env, settings);

        //注意配置这里，可能导致 其他的问题
//        TableConfig tConfig = blinkTableEnv.getConfig();
//        tConfig.setIdleStateRetentionTime(org.apache.flink.api.common.time.Time.minutes(1), org.apache.flink.api.common.time.Time.minutes(6));

        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.enableCheckpointing(3000);
        env.getCheckpointConfig().setTolerableCheckpointFailureNumber(3);
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);

        env.getConfig().setAutoWatermarkInterval(1000);

        InputStream in_env  = ClassLoader.getSystemResourceAsStream("env.properties");
        Properties prop = new Properties();
        prop.load(in_env);


        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", prop.getProperty("bootstrap.servers"));
        properties.setProperty("zookeeper.connect", prop.getProperty("zookeeper.connect"));
        properties.setProperty("group.id", "test");

        /**
         *  Orders kafka
         */
        FlinkKafkaConsumer011<String> kafkaSourceOrder = new FlinkKafkaConsumer011<String>("eventsource_yyb_order", new SimpleStringSchema(), properties);
        DataStream<String> streamOrderStr = env.addSource(kafkaSourceOrder);
        DataStream<Current1> streamOrder = streamOrderStr.map(new MapFunction<String, Current1>() {
            @Override
            public Current1 map(String value) throws Exception {
                Current1 current1 = JSON.parseObject(value, Current1.class);
                return current1;
            }
        });
        streamOrder.assignTimestampsAndWatermarks(new TimestampExtractorOrder(Time.seconds(0)));
//        oederDS.print().setParallelism(1);
        Table orders = blinkTableEnv.fromDataStream(streamOrder, "rowtime,amount,currency,user_action_time.rowtime");
        blinkTableEnv.registerTable("Orders", orders);
//        DataStream<Row> orderPC = blinkTableEnv.toAppendStream(blinkTableEnv.sqlQuery("select *, 'Orders' from Orders"), Row.class);
//        orderPC.print().setParallelism(1);

        /**
         * Rates kafka
         */

        FlinkKafkaConsumer011<String> kafkaSource = new FlinkKafkaConsumer011<String>("eventsource_yyb_rate", new SimpleStringSchema(), properties);
        DataStream<String> stream = env.addSource(kafkaSource);
        DataStream<Rate> rate = stream.map(new MapFunction<String, Rate>() {
            @Override
            public Rate map(String value) throws Exception {
                Rate rate = JSON.parseObject(value, Rate.class);
                return rate;
            }
        });
        rate.assignTimestampsAndWatermarks(new TimestampExtractorRate(Time.seconds(0)));


        Table rates = blinkTableEnv.fromDataStream(rate, "rowtime,currency,rate,user_action_time.rowtime");
        blinkTableEnv.registerTable("Rates", rates);
//        DataStream<Row> ratePC = blinkTableEnv.toAppendStream(blinkTableEnv.sqlQuery("select *, 'Rates' from Rates"), Row.class);
//        ratePC.print().setParallelism(1);

        TemporalTableFunction ratesF = rates.createTemporalTableFunction("user_action_time", "currency");
        blinkTableEnv.registerFunction("RatesF", ratesF);

        // CASET , TO_TIMESTAMP

        String sql = "select o.currency,o.amount,r.rate,o.amount * r.rate AS amount, o.rowtime, r.rowtime, 'inner' from Orders as o inner join Rates as r on o.currency = r.currency";
        blinkTableEnv.toAppendStream(blinkTableEnv.sqlQuery(sql), Row.class).print();

        String sql1 = "select o.currency,o.amount,r.rate,o.amount * r.rate AS amount, 'hahahah' " +
                "from Orders as o , " +
                "LATERAL TABLE (RatesF(o.user_action_time)) as r " +
                "WHERE o.currency = r.currency";
        //等值 join 的时候，是会出现结果的，设置 TableConfig 放置 内存占用过大
//        String sql1 = "select o.currency,o.amount,r.rate,o.amount * r.rate AS amount, o.rowtime, r.rowtime, 'hahaha' from Orders as o inner join Rates r on o.currency = r.currency";
        Table rs1 = blinkTableEnv.sqlQuery(sql1);
        DataStream<Row> rs1DS = blinkTableEnv.toAppendStream(rs1, Row.class);
        rs1DS.print().setParallelism(1);

        env.execute("JoinWithTeporalTableFunction");



    }


    static class TimestampExtractorOrder extends BoundedOutOfOrdernessTimestampExtractor<Current1> {

        public TimestampExtractorOrder(Time maxOutOfOrderness){
            super(maxOutOfOrderness);
        }
        @Override
        public long extractTimestamp(Current1 element) {
            return Long.parseLong(element.getRowtime());
        }
    }

    static class TimestampExtractorRate extends BoundedOutOfOrdernessTimestampExtractor<Rate> {

        public TimestampExtractorRate(Time maxOutOfOrderness){
            super(maxOutOfOrderness);
        }
        @Override
        public long extractTimestamp(Rate element) {
//            System.out.println("here:" + element);
            return Long.parseLong(element.getRowtime());
        }
    }
}