package Chinese;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class CombineLocation {

    public static Map<Long, Vector<String>> getAllLocation(Map<Long, Vector<String> > loc) {
        Map<Long, Vector<String>> m = new HashMap<>();
        String path = "D:\\RegionInfo.txt";
        String oPath = "D:\\training.txt";


        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(oPath));
            // file format is GaiaID, RegionID, RegionName, RegionTypeID, landmark lat, landmark long.
            File f = new File(path);

            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String s = in.readLine();
            System.out.println(s);
            int i = 1;
            while (s != null) {
                StringBuilder result = new StringBuilder();
                String[] as = s.split("\t");
                Vector<String> vs = new Vector<>();
                Long gaia = Long.parseLong(as[0]);
                if (!loc.containsKey(gaia)) {
                    s = in.readLine();
                    continue;
                }
                Vector<String> locs = loc.get(gaia);

                result.append(as[1]); // region ID
                result.append("\t");

                result.append(as[2]); // region name
                result.append("\t");

                result.append("City"); // hard code.
                result.append("\t");

                result.append(as[3]); // region type id
                result.append("\t");

                result.append(locs.size()); // region name
                result.append("\t");

                for(String ss: locs){
                    result.append(ss); // all location.
                    result.append("\t");
                }

                result.append("NULL"); // just add don't know why.
                result.append("\t");

                result.append(as[4]); // Landmark
                result.append("\t");

                result.append(as[5]); // Landmark.
                result.append("\t");

                result.append(i); // Landmark.
                i++;

                String restStr = result.toString();
                bw.write(restStr);
                bw.newLine();
                System.out.println(restStr);

                //output to file.

                s = in.readLine();

            }

        } catch (Exception e) {
            System.out.println("Exception god");
        }
        return m;
    }


    public static Map<Long, Vector<String> > getAllPosition(){

        Map<Long, Vector<String>> m = new HashMap<>();
        String path = "D:\\RegionTop.txt";
        try {
            File f = new File(path);

            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String s = in.readLine();
            Long cur = 0L;
            Vector<String> vs = new Vector<>();

            while (s != null) {
                String[] as = s.split("\t");
                Long tmp = Long.parseLong(as[0]);
                if (!cur.equals(tmp)){
                    cur = tmp;
                    m.put(cur, vs);
                    vs = new Vector<>();
                }
                vs.add(as[1]);
                vs.add(as[2]);
                s = in.readLine();
            }
            m.put(cur, vs);
            /*
            Iterator it = m.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                System.out.print(pairs.getKey());
                Vector<String> value = (Vector<String>) pairs.getValue();
                for(String ssv : value){
                    System.out.print(" " + ssv);

                }
                System.out.println();
            }*/

        } catch (Exception e) {
            System.out.println("Exception god1");
        }


        return m;
    }

    public static void main(String[] args) {
        //getAllPosition();
        getAllLocation(getAllPosition());
    }
}
