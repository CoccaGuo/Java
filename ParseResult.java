package ticket.util;

import java.util.ArrayList;

public class ParseResult {

    public static String[][] parseResult(ArrayList<String> result){
        String[] temp_list[];
        try {
             temp_list = new String[result.size()][];
        }catch (Exception e){
            System.out.println("err in parsing json");
            temp_list =null;
        }
    	System.out.println("车次\t出发\t到达\t历时\t一等\t二等\t软卧\t硬卧\t硬座\t无座\t高级软卧");
    	System.out.println("--------------------------------------------------------------------------------------");
        for (int i = 0; i <result.size() ; i++){
            temp_list[i] = result.get(i).split("\\|");
            System.out.print(temp_list[i][3]+"\t");// 车次 出发时间 到达时间 历时 一等 二等 软卧 硬卧 硬座 无座 高级软卧 tmp_list[3],tmp_list[8],tmp_list[9],tmp_list[10],tmp_list[31],tmp_list[30],tmp_list[23],tmp_list[28],tmp_list[29],tmp_list[26],tmp_list[21] 
            System.out.print(temp_list[i][8]+"\t");
            System.out.print(temp_list[i][9]+"\t");
            System.out.print(temp_list[i][10]+"\t");
            System.out.print(temp_list[i][31]+"\t");
            System.out.print(temp_list[i][30]+"\t");
            System.out.print(temp_list[i][23]+"\t");
            System.out.print(temp_list[i][28]+"\t");
            System.out.print(temp_list[i][29]+"\t");
            System.out.print(temp_list[i][26]+"\t");
            System.out.print(temp_list[i][21]+"\t");
            System.out.println();
            
        }
        return temp_list;
    }
}
