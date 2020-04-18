# Flinl10_learn
主要用于学习 flink 10 版本的目的

## 包目录介绍
batch存放 批代码的包

stream存放 流代码的包


## flink parquet
1. 增加 flink-parquet maven 依赖


## Checkpointing

## Sink
### StreamingFileSink format
StreamingFileSink 有2种 File Formats：
1. Row-encoded sink
2. Bulk-encoded sink
注意 在 使用 StreamingFileSink 的时候需要开启 Checkpointing。


#### Row-encoded sink 需要指定 写目录 和 Encoder：
这个 Encoder 只有一个 直接子类 SimpleStringEncoder 只是对数据做一个编码，如果要实现自己的 可以继承这个类
#### Bulk-encoded sink 需要也指定 写目录和 BulkWriter.Factory.
BulkWriter.Factory 有 3 个实现类 CompressWriterFactory, ParquetWriterFactory, SequenceFileWriterFactory
对应 三个 数据格式 (注意需要添加 maven flink依赖)：
1. ParquetWriterFactory
2. Hadoop SequenceFileWriterFactory
3. SequenceFileWriterFactory supports additional constructor parameters to specify compression settings

### StreamingFileSink RollingPolicy(滚动策略，文件轮替)
1. DefaultRollingPolicy
2. OnCheckpointRollingPolicy

DefaultRollingPolicy 可以设置三个 策略条件：
RolloverInterval 当前文件 早于 滚动间隔；
InactivityInterval 当前没有数据写到文件超过非活动的时间 默认 60S；
MaxPartSize 这个文件的大小，默认 128M； 

OnCheckpointRollingPolicy 的 滚动执行只会在 每一次 checkpoint 的时候。

注意这2个类都实现了 flink 的 RollingPolicy 接口，但是这个接口的实现有3个 DefaultRollingPolicy、OnCheckpointRollingPolicy、CheckpointRollingPolicy。
其中 CheckpointRollingPolicy 是抽象类，而 OnCheckpointRollingPolicy 又是 CheckpointRollingPolicy抽象类的实现。

因为 flink stream 不像spark stream 一样是 微批处理模式，不会产生 小文件，所以这里如果不指定 滚动策略，那么可能都在文件中追加内容。


### StreamingFileSink BucketAssigner(输出文件 名称 指定 匹配模式)
1. 默认的是 DateTimeBucketAssigner 这种方式，即以时间格式 yyyy-MM-dd--HH，可以自己修改 以时间格式 和 时区
2. BasePathBucketAssigner 这种方式就是 不会指定 子文件的命名方式。

这2个类都是非 final的，所以可以继承用来实现自己的BucketAssigner。  
通过 调用 .withBucketAssigner(assigner) on the format builders.

这一部分是配置 输出文件的 前后缀的：
可以是调用 .withOutputFileConfig(config) 和 OutputFileConfig 结合 配置 输出文件的 前后缀的。

## 问题
### 从 flink 官网使用 maven 初始化的项目 问题
1. idea 本地运行 提示 缺包问题。修改 pom文件 dependency 的 scope 范围，可以直接注释掉这个 选项
### batch 程序 有的 地方不执行的问题
1. batch 程序 有的地方没有执行，可能你的程序 最后没有调用 env.execution() 方法


## Flink10 BUG
### StreamingFileSink build bug
当使用 StreamingFileSink 的多个 with... 方法时，会提示
 
      Error:xxx value build is not a member of ?0
      possible cause: maybe a semicolon is missing before `value build'?
      build()
      
请注意，这是一个 flink StreamingFileSink scala 版本的是一个 bug，可以使用 java 版本编写，或者使用 更高的 flink 版本。

详情见：https://issues.apache.org/jira/browse/FLINK-16684