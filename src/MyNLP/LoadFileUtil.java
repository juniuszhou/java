package MyNLP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class LoadFileUtil {
    public static Map<String, Double> LoadChineseDict() throws Exception{
        Map<String, Double> m = new HashMap<>();
        String path = "D:\\dev\\my_git\\java\\src\\MyNLP\\dict.txt";

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        //System.out.println("path");
        String s = in.readLine();
        double total = 0.0;
        while (s != null) {
            String[] idAndName = s.split(" ");
            total += Double.parseDouble(idAndName[1]);
            // System.out.println(idAndName[0] + idAndName[1]);
            m.put(idAndName[0],Double.parseDouble(idAndName[1]));
            // System.out.println(Long.parseLong(idAndName[0]) + " " + idAndName[1]);
            s = in.readLine();
        }

        Iterator i = m.keySet().iterator();
        while (i.hasNext()) {
            String k = (String) i.next();
            double j = m.get(k);
            j = Math.log(j / total);
            m.replace(k, j);
            // System.out.println(k + " " + j);
        }

        return m;
    }

    public static Vector<HashMap<Character, Double>> LoadTagTable() throws Exception{
        String path = "D:\\dev\\my_git\\java\\src\\MyNLP\\prob.txt";

        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

        String s = in.readLine();
        String curTag = "";
        Vector<HashMap<Character, Double> > v = new Vector<>();
        HashMap<Character, Double> mb = new HashMap<>();
        HashMap<Character, Double> mm = new HashMap<>();
        HashMap<Character, Double> me = new HashMap<>();
        HashMap<Character, Double> ms = new HashMap<>();

        while (s != null) {
            // 'B', 'M', 'E', 'S'
            if (s.equals("B") || s.equals("M") || s.equals("E") || s.equals("S")){
                curTag = s;
                s = in.readLine();
                continue;
            }

            String[] idAndName = s.split("\t");

            // System.out.println(idAndName[0] + idAndName[1]);
            Character c1 = idAndName[0].charAt(0);
            Double d1 = Double.parseDouble(idAndName[1]);

            switch (curTag){
                case "B":
                    mb.put(c1,d1);
                    break;
                case "M":
                    mm.put(c1,d1);
                    break;
                case "E":
                    me.put(c1,d1);
                    break;
                case "S":
                    ms.put(c1,d1);
                    break;

            }

            s = in.readLine();
        }

        v.add(mb);v.add(mm);v.add(me);v.add(ms);
        return v;
    }

    public static  void main(String[] args){
        try {
            LoadTagTable();
        }
        catch (Exception e)
        {

        }
    }
}
