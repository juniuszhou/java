package Chinese;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * Created by juzhou on 1/22/2015.
 */
public class EnglishRegion {
    public static Map<Long, String> Read(String path)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String s = in.readLine();
        Map<Long, String> m = new HashMap<>();

        while (s != null) {
            String[] idAndName = s.split("\t");
            m.put(Long.parseLong(idAndName[0]), idAndName[1]);
            System.out.println(Long.parseLong(idAndName[0]) + " " + idAndName[1]);
            s = in.readLine();
        }
        return m;
    }

    public static Map<Long, String> ReadRegionType()throws Exception{
        String path = "D:\\TrainingData\\Region\\RegionTypeToName.txt";
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String s = in.readLine();
        Map<Long, String> m = new HashMap<>();

        while (s != null) {
            String[] idAndName = s.split("\t");
            m.put(Long.parseLong(idAndName[0]), idAndName[1]);
            //System.out.println(Long.parseLong(idAndName[0]) + " " + idAndName[1]);
            s = in.readLine();
        }
        return m;
    }

    public static void main(String[] args) throws Exception{
        String engPath = "D:\\TrainingData\\Region\\RegionEnglishName.txt";
        String chnPath = "D:\\TrainingData\\Region\\RegionChineseName.txt";
        Map<Long, String> m1 = Read(engPath);
        Map<Long, String> m2 = Read(chnPath);

        String outPath = "D:\\RegionData";
        //FileOutputStream fos = new FileOutputStream(outPath);
        FileWriter fw = new FileWriter(outPath);

        Iterator it = m2.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            //System.out.print(pairs.getKey());
            String str2 = m1.get(pairs.getKey());
            String str1 = (String) pairs.getValue();
            System.out.println(str1 + "\t" + str2);
            fw.write(str1 + "\t" + str2 + "\r\n");
        }
        fw.close();

    }
}
