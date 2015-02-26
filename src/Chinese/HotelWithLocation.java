package Chinese;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * Created by juzhou on 1/26/2015.
 */
public class HotelWithLocation {
    public static String getChineseNation(String str){
        if (str.equals("CHN")) return "中国";
        else if (str.equals("TWN")) return "台湾";
        else if (str.equals("MAC")) return "澳门";
        else if (str.equals("HKG")) return "香港";

        else  return "11";
    }

    public static Map<Long, Vector<String>> ReadLanMyMap(String path)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String s = in.readLine();
        Map<Long, Vector<String>> m = new HashMap<>();

        while (s != null) {
            String[] idAndName = s.split("\t");
            Vector<String> v = new Vector<>();
            String name = idAndName[0];
            String lan = idAndName[2];
            String lon = idAndName[3];
            v.add(name);
            v.add(lan);
            v.add(lon);

            m.put(Long.parseLong(idAndName[1]), v);
            s = in.readLine();
        }
        return m;
    }

    public static Map<Long, Vector<String>> ReadToMyMap(
            String path, Map<Long, Vector<String> > mm)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String s = in.readLine();
        Map<Long, Vector<String>> m = new HashMap<>();

        while (s != null) {
            String[] idAndName = s.split("\t");
            Vector<String> v = new Vector<>();
            String city = idAndName[4];
            String province = idAndName[5];
            String state = idAndName[7];
            String chineseState = getChineseNation(state);
            if (chineseState.equals("11")) {
                s = in.readLine();
                continue;
            }

            String PEK = "PEK";
            Vector<String> lan = mm.get(Long.parseLong(idAndName[0]));
            if (lan == null){
                s = in.readLine();
                continue;
            }
            v.add(lan.get(0)); //name
            v.add("Hotel");
            v.add(city);
            v.add(province);
            v.add(chineseState);
            v.add(state);
            v.add(PEK);
            v.add(lan.get(1));
            v.add(lan.get(2));
            v.add("NULL");
            v.add("NULL");

            m.put(Long.parseLong(idAndName[0]), v);
            System.out.println(idAndName[0]);
            s = in.readLine();
        }
        return m;
    }
    public static void main(String[] args) throws Exception{
        String path = "D:\\TrainingData\\hotels\\HotelCityProvinceNation.txt";
        String path2 = "D:\\TrainingData\\hotels\\HotelLatitudeLongitude.txt";
        Map<Long, Vector<String>> m = ReadToMyMap(path, ReadLanMyMap(path2));

        FileWriter fw = new FileWriter("D:\\TrainingData\\hotels\\a.txt");

        Iterator it = m.entrySet().iterator();
        while (it.hasNext()) {
            StringBuilder sb = new StringBuilder();
            Map.Entry pairs = (Map.Entry) it.next();
            sb.append(pairs.getKey().toString());
            Vector<String> v = (Vector<String>) pairs.getValue();
            for(String s : v){
                sb.append("\t");
                sb.append(s);
            }
            System.out.println(pairs.getKey().toString() + "\t" + sb.toString());
            //fw.write(pairs.getKey().toString() + "\t" + sb.toString() + "\r\n");
        }
        fw.close();
    }
}
