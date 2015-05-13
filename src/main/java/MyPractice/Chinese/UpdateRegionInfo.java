package Chinese;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by juzhou on 1/22/2015.
 */
public class UpdateRegionInfo {
    public static void main(String[] args) throws Exception{
        String path = "D:\\TrainingData\\Region\\training.txt";
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String s = in.readLine();
        ArrayList<String> als = new ArrayList<String>();

        //Map<Long, String> m = MyUtilily.ReadToMap("D:\\TrainingData\\Region\\RegionTypeToName.txt");

        while (s != null) {
            String[] idAndName = s.split("\t");
            Integer num = Integer.parseInt(idAndName[4]) * 2;
            StringBuilder sb = new StringBuilder();
            sb.append(idAndName[0]);
            sb.append("\t");
            sb.append(idAndName[1]);
            sb.append("\t");
            sb.append(idAndName[2]);
            sb.append("\t");
            sb.append(idAndName[3]);
            sb.append("\t");
            sb.append(idAndName[4]);
            sb.append("\t");

            for(int i = 0; i < num-1; i++){
                sb.append(idAndName[5+i]);
                sb.append(", ");
            }
            System.out.println("-----------------");
            sb.append(idAndName[4+num]);
            sb.append("\t");

            sb.append(idAndName[5+num]); //NULL
            sb.append("\t");

            sb.append(idAndName[6+num]);
            sb.append(", ");

            sb.append(idAndName[7+num]);
            sb.append("\t");
            sb.append(idAndName[8+num]);

            System.out.println(sb.toString());
            als.add(sb.toString());
            s = in.readLine();
        }


        FileWriter fw = new FileWriter("D:\\TrainingData\\Region\\newTraining.txt");

        for(String ssss : als){

            fw.write(ssss + "\r\n");
        }
        fw.close();
    }
}
