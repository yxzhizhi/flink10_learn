package com.yyb.flink10.util1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;

/**
 * @Author yyb
 * @Description
 * @Date Create in 2020-09-14
 * @Time 10:48
 */
public class SavebytesFile {
    public static void main(String[] args) throws Exception{
        File file = new File("./LS7H4880DJ16B230.dat");

        if(file.exists()){
            file.delete();
        }

//        String a = "10000000000000000010111110101111011001111100101010000000000000010000000000000000000000000111011111011111110111101000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000101000000000000000000000000000000000000000001100000000000000000011000000000000000000000000000000000000000000000000000000000000001110000000001110100111011111011111110111101001101000001000001011100000000000000100000000001111011111011111110111101000000000000000000000000000000000000000000001001000110001110111110111111101111010000000111101111101111111011110111101111101111111011110100010010000000000000101011101111101111111011110111101111101111111011110100000000000000000000000000000000000000000000101100000000000000000000000000000000000000000000000000000000000000010000000000000000010111110101111011001111100101100000000000000010000000000000000000000000111011111011111110111101000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000101000000000000000000000000000000000000000001100000000000000000011000000000000000000000000000000000000000000000000000000000000001110000000001100000111011111011111110111101001100000001000001111000000000000000100000000001111011111011111110111101000000000000000000000000000000000000000000001001000110001110111110111111101111010000000111101111101111111011110111101111101111111011110100010010000000000000101011101111101111111011110111101111101111111011110100000000000000000000000000000000000000000000101100000000000000000000000000000000000000000000000000000000000000010000000000000000010111110101111011001111100101110000000000000010000000000000000000000000111011111011111110111101000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000101000000000000000000000000000000000000000001100000000000000000011000000000000000000000000000000000000000000000000000000000000001110000000001101100111011111011111110111101001000000001000001100000000000000000100000000001111011111011111110111101000000000000000000000000000000000000000000001001000110001110111110111111101111010000000111101111101111111011110111101111101111111011110100010010000000000000101011101111101111111011110111101111101111111011110100000000000000000000000000000000000000000000101100000000000000000000000000000000000000000000000000000000000000010000000000000000010111110101111011001111100110000000000000000010000000000000000000000000111011111011111110111101000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000101000000000000000000000000000000000000000001100000000000000000011000000000000000000000000000000000000000000000000000000000000001110000000001110100111011111011111110111101001100000001000001111000000000000000100000000001111011111011111110111101000000000000000000000000000000000000000000001001000110001110111110111111101111010000000111101111101111111011110111101111101111111011110100010010000000000000101011101111101111111011110111101111101111111011110100000000000000000000000000000000000000000000101100000000000000000000000000000000000000000000000000000000000000010000000000000000010111110101111011001111100110010000000000000010000000000000000000000000111011111011111110111101000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000101000000000000000000000000000000000000000001100000000000000000011000000000000000000000000000000000000000000000000000000000000001110000000001100000111011111011111110111101001011000001000001111000000000000000100000000001111011111011111110111101000000000000000000000000000000000000000000001001000110001110111110111111101111010000000111101111101111111011110111101111101111111011110100010010000000000000101011101111101111111011110111101111101111111011110100000000000000000000000000000000000000000000101100000000000000000000000000000000000000000000000000000000000000010000000000000000010111110101111011001111100110100000000000000010000000000000000000000000111011111011111110111101000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000101000000000000000000000000000000000000000001100000000000000000011000000000000000000000000000000000000000000000000000000000000001110000000001110000111011111011111110111101001010000001000001110000000000000000100000000001111011111011111110111101000000000000000000000000000000000000000000001001000110001110111110111111101111010000000111101111101111111011110111101111101111111011110100010010000000000000101011101111101111111011110111101111101111111011110100000000000000000000000000000000000000000000101100000000000000000000000000000000000000000000000000000000000000010000000000000000010111110101111011001111100110110000000000000010000000000000000000000000111011111011111110111101000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000101000000000000000000000000000000000000000001100000000000000000011000000000000000000000000000000000000000000000000000000000000001110000000001111100111011111011111110111101001010000001000001011100000000000000100000000001111011111011111110111101000000000000000000000000000000000000000000001001000110001110111110111111101111010000000111101111101111111011110111101111101111111011110100010010000000000000101011101111101111111011110111101111101111111011110100000000000000000000000000000000000000000000101100000000000000000000000000000000000000000000000000000000000000010000000000000000010111110101111011001111100111000000000000000010000000000000000000000000111011111011111110111101000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000101000000000000000000000000000000000000000001100000000000000000011000000000000000000000000000000000000000000000000000000000000001110000000001011000111011111011111110111101001011000001000001101000000000000000100000000001111011111011111110111101000000000000000000000000000000000000000000001001000110001110111110111111101111010000000111101111101111111011110111101111101111111011110100010010000000000000101011101111101111111011110111101111101111111011110100000000000000000000000000000000000000000000101100000000000000000000000000000000000000000000000000000000000000010000000000000000010111110101111011001111100111010000000000000010000000000000000000000000111011111011111110111101000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000101000000000000000000000000000000000000000001100000000000000000011000000000000000000000000000000000000000000000000000000000000001110000000001101000111011111011111110111101001101000001000001100000000000000000100000000001111011111011111110111101000000000000000000000000000000000000000000001001000110001110111110111111101111010000000111101111101111111011110111101111101111111011110100010010000000000000101011101111101111111011110111101111101111111011110100000000000000000000000000000000000000000000101100000000000000000000000000000000000000000000000000000000000000010000000000000000010111110101111011001111100111100000000000000010000000000000000000000000111011111011111110111101000000000000000000000000000001000000000000000000000000000000000000000000000000000000000000000101000000000000000000000000000000000000000001100000000000000000011000000000000000000000000000000000000000000000000000000000000001110000000001110000111011111011111110111101001001000001000001011000000000000000100000000001111011111011111110111101000000000000000000000000000000000000000000001001000110001110111110111111101111010000000111101111101111111011110111101111101111111011110100010010000000000000101011101111101111111011110111101111101111111011110100000000000000000000000000000000000000000000101100000000000000000000000000000000000000000000000011101111101111111011110111101111101111111011110100000000000000000101101111101111101111111011110101101011011100110100110001010011001101110100100000110100001110000011100000110000010001000100101001000011001100010011011001000010001100100011001100110000";
        String a = "561472076049511828474499430334617914930558806611417586330177824866140685733768140883297852642871555001249251652908271340793063595008891515638935399980658756580825508195465991593295965894253428894413607211450675691682863888333763380890033677550116632378511083759450829755958174189425259641140318813448818450388952653065965033542180219857065787634243336452916436235367128489603638594089493375219134976125191857851049904042296888161542343976391173468024539103926510834226203848231197936428183096667021532723003406138399547132769832546017701928479629736542087998343840554722272622747727552895821275871833155180025576223953986574516996098834468250313574960980500397537866454232894418429867211366762627193690751582490927773578372815315412463948374676365581602853305226697696473352515227451960691597039999599495576790758126430821378303291731010383267237767840694590532049024201764646430112500434407081464375607129163208598712329272863167407563676367083971019294778472203768075960924795608252329850866882804514190880400218500069427328936754721049608433819667242729286685033115435074089714985901708664684778449347031167206992424424158024880522500424813159052910725276224735705291857311920698259883859769850538220181089478688679200919857013005544519981851098479020134869820010040321660000341969238303443135115028528964088435569176639798357530690480600426861010329318334508939738767395953905103573291649459811377941627118896111104686656643380467746189052443307572644983116203055376334367092329146980154604201932007211665645411740744519953655034141852053629612604388707432691986364841229323697236318337855107204539640283485808805569791825500978663579459802806665475396507501622469601573866529026578790302988463372590053728571104543674232918480568689309547575756216639341550037303414613208475251898319792601527723181145516074913738946570291211288776955377404440612511384998191415404393224084055389187099859476100482341303657317180995838588908372918782342279149542361710737475644077422327251024148020291203015417053098184651804107184922434223528564674855493633219822790999904206464639692263028218336703090386291832588151780582649242086214753083407669549164665842313127059941513975923049705803350895772921866218492480905148673072087082448112991064362376802932157068682004735451462162014195511936694821015136330528196692251220291789356151164373783098868907013478146231848762718862973780054011687728";
//        OutputStream os = new FileOutputStream(file);
//        os.write(a.getBytes());
//        os.close();

        int[] aaa = new int[]{0, 1, 0, 0, 95, 94, -49, -107, 0, 2, 0, 0, 0, -17, -65, -67, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 96, 0, 6, 0, 0, 0, 0, 0, 0, 0, 7, 0, 116, -17, -65, -67, 52, 16, 92, 0, 8, 1, -17, -65, -67, 0, 0, 0, 0, 0, 9, 24, -17, -65, -67, 1, -17, -65, -67, -17, -65, -67, 18, 0, 10, -17, -65, -67, -17, -65, -67, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 95, 94, -49, -106, 0, 2, 0, 0, 0, -17, -65, -67, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 96, 0, 6, 0, 0, 0, 0, 0, 0, 0, 7, 0, 96, -17, -65, -67, 48, 16, 120, 0, 8, 1, -17, -65, -67, 0, 0, 0, 0, 0, 9, 24, -17, -65, -67, 1, -17, -65, -67, -17, -65, -67, 18, 0, 10, -17, -65, -67, -17, -65, -67, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 95, 94, -49, -105, 0, 2, 0, 0, 0, -17, -65, -67, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 96, 0, 6, 0, 0, 0, 0, 0, 0, 0, 7, 0, 108, -17, -65, -67, 32, 16, 96, 0, 8, 1, -17, -65, -67, 0, 0, 0, 0, 0, 9, 24, -17, -65, -67, 1, -17, -65, -67, -17, -65, -67, 18, 0, 10, -17, -65, -67, -17, -65, -67, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 95, 94, -49, -104, 0, 2, 0, 0, 0, -17, -65, -67, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 96, 0, 6, 0, 0, 0, 0, 0, 0, 0, 7, 0, 116, -17, -65, -67, 48, 16, 120, 0, 8, 1, -17, -65, -67, 0, 0, 0, 0, 0, 9, 24, -17, -65, -67, 1, -17, -65, -67, -17, -65, -67, 18, 0, 10, -17, -65, -67, -17, -65, -67, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 95, 94, -49, -103, 0, 2, 0, 0, 0, -17, -65, -67, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 96, 0, 6, 0, 0, 0, 0, 0, 0, 0, 7, 0, 96, -17, -65, -67, 44, 16, 120, 0, 8, 1, -17, -65, -67, 0, 0, 0, 0, 0, 9, 24, -17, -65, -67, 1, -17, -65, -67, -17, -65, -67, 18, 0, 10, -17, -65, -67, -17, -65, -67, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 95, 94, -49, -102, 0, 2, 0, 0, 0, -17, -65, -67, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 96, 0, 6, 0, 0, 0, 0, 0, 0, 0, 7, 0, 112, -17, -65, -67, 40, 16, 112, 0, 8, 1, -17, -65, -67, 0, 0, 0, 0, 0, 9, 24, -17, -65, -67, 1, -17, -65, -67, -17, -65, -67, 18, 0, 10, -17, -65, -67, -17, -65, -67, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 95, 94, -49, -101, 0, 2, 0, 0, 0, -17, -65, -67, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 96, 0, 6, 0, 0, 0, 0, 0, 0, 0, 7, 0, 124, -17, -65, -67, 40, 16, 92, 0, 8, 1, -17, -65, -67, 0, 0, 0, 0, 0, 9, 24, -17, -65, -67, 1, -17, -65, -67, -17, -65, -67, 18, 0, 10, -17, -65, -67, -17, -65, -67, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 95, 94, -49, -100, 0, 2, 0, 0, 0, -17, -65, -67, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 96, 0, 6, 0, 0, 0, 0, 0, 0, 0, 7, 0, 88, -17, -65, -67, 44, 16, 104, 0, 8, 1, -17, -65, -67, 0, 0, 0, 0, 0, 9, 24, -17, -65, -67, 1, -17, -65, -67, -17, -65, -67, 18, 0, 10, -17, -65, -67, -17, -65, -67, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 95, 94, -49, -99, 0, 2, 0, 0, 0, -17, -65, -67, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 96, 0, 6, 0, 0, 0, 0, 0, 0, 0, 7, 0, 104, -17, -65, -67, 52, 16, 96, 0, 8, 1, -17, -65, -67, 0, 0, 0, 0, 0, 9, 24, -17, -65, -67, 1, -17, -65, -67, -17, -65, -67, 18, 0, 10, -17, -65, -67, -17, -65, -67, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 95, 94, -49, -98, 0, 2, 0, 0, 0, -17, -65, -67, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 96, 0, 6, 0, 0, 0, 0, 0, 0, 0, 7, 0, 112, -17, -65, -67, 36, 16, 88, 0, 8, 1, -17, -65, -67, 0, 0, 0, 0, 0, 9, 24, -17, -65, -67, 1, -17, -65, -67, -17, -65, -67, 18, 0, 10, -17, -65, -67, -17, -65, -67, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, -17, -65, -67, -17, -65, -67, 0, 0, 91, -17, -65, -67, 107, 115, 76, 83, 55, 72, 52, 56, 56, 48, 68, 74, 67, 49, 54, 66, 50, 51, 48};

        FileWriter fileWriter = new FileWriter(file, true);
        for(int aaaa_i : aaa){
            //写对应的 ascii 码值 ，即打开文件 是乱码的
            fileWriter.write(aaaa_i);
        }
        fileWriter.flush();
        fileWriter.close();
    }
}
