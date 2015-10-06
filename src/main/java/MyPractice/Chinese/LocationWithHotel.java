package MyPractice.Chinese;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class LocationWithHotel {
    public static String getChineseNation(String str){
        if (str.equals("CHN")) return "中国";
        else if (str.equals("TWN")) return "台湾";
        else if (str.equals("MAC")) return "澳门";
        else if (str.equals("HKG")) return "香港";

        else  return "NULL";
    }

    public static String setAsNull(String str){
        if (str.trim().length() == 0){
            return "NULL";
        }
        return str;
    }

    public static Map<Long, Vector<String>> ReadLanMyMap(String path,
                      Map<Long, Vector<String>> mm)throws Exception{
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


            Vector<String> values = mm.get(Long.parseLong(idAndName[1]));
            if (values != null){
                v.add("Hotel");
                v.add(values.get(0)); //city
                v.add(values.get(1)); //province
                v.add(values.get(2)); // state
                v.add(values.get(3)); // state english
                v.add(values.get(4)); // pek
            }else{
                v.add("Hotel");
                v.add("NULL");v.add("NULL");v.add("NULL");v.add("NULL");v.add("NULL");

            }
            v.add(lan);
            v.add(lon);v.add("NULL");v.add("NULL");


            m.put(Long.parseLong(idAndName[1]), v);
            s = in.readLine();
        }
        return m;
    }

    public static Vector<Vector<String> > ReadToMyMap(String path)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String s = in.readLine();
        Vector<Vector<String> > vv = new Vector<>();

        while (s != null) {
            String[] idAndName = s.split("\t");
            Vector<String> v = new Vector<>();
            String name = idAndName[0];
            String id = idAndName[1];
            String city = setAsNull(idAndName[2]);
            String province = setAsNull(idAndName[3]);
            String state = setAsNull(idAndName[4]);
            String chineseState = getChineseNation(state);

            String airport = idAndName[5];
            String lan = idAndName[6];
            String lon = idAndName[7];

            v.add(id);v.add(name);v.add("Hotel");
            v.add(city);v.add(province);
            v.add(chineseState);
            v.add(state);
            v.add(airport);v.add(lan);v.add(lon);
            v.add("NULL");v.add("NULL");

            vv.add(v);
            System.out.println(idAndName[0]);
            s = in.readLine();
        }
        return vv;
    }
    public static void main(String[] args) throws Exception{
        String path = "D:\\TrainingData\\hotels\\HotelCityProvinceNationLangLong.txt";
        // String path2 = "D:\\TrainingData\\hotels\\HotelLatitudeLongitude.txt";
        Vector<Vector<String> > vv = ReadToMyMap(path);

        FileWriter fw = new FileWriter("D:\\TrainingData\\hotels\\a.txt");

        Iterator it = vv.iterator();
        while (it.hasNext()) {
            StringBuilder sb = new StringBuilder();
            Vector<String> v = (Vector<String>) it.next();
            boolean isFirst = true;
            for(String s : v){
                if(isFirst){
                    isFirst = false;
                }
                else {
                    sb.append("\t");
                }
                sb.append(s);
            }
            System.out.println(sb.toString());
            fw.write(sb.toString() + "\r\n");
        }
        fw.close();
    }
}

