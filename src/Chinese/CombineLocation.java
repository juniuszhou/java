package Chinese;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class CombineLocation {
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

    public static Map<Long, Vector<String>> getAllLocation(Map<Long, Vector<String> > loc
    , Map<Long, String> tt) {
        Map<Long, Vector<String>> m = new HashMap<>();
        String path = "D:\\TrainingData\\Region\\RegionLandmark.txt";
        String oPath = "D:\\TrainingData\\Region\\regionInfo.txt";
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

                result.append(as[0]); // gaia ID
                result.append("\t");

                result.append(as[2]); // region name
                result.append("\t");

                result.append(tt.get(Long.parseLong(as[3]))); // hard code.
                result.append("\t");

                result.append(as[3]); // region type id
                result.append("\t");

                int nums = locs.size() / 2 - 1;
                result.append(nums); // region name
                result.append("\t");


                for(int j = 0; j < nums * 2; j ++){
                    result.append(locs.get(j)); // all location.
                    if (j < nums * 2 - 1)
                        result.append(", ");
                }
                result.append("\t");

                result.append("NULL"); // just add don't know why.
                result.append("\t");

                result.append(as[4]); // Landmark
                result.append(", ");

                result.append(as[5]); // Landmark.
                result.append("\t");

                result.append(as[1]); // region ID.

                String restStr = result.toString();
                // bw.write(restStr);
                // bw.newLine();
                System.out.println(restStr);

                s = in.readLine();

            }

        } catch (Exception e) {
            System.out.println("Exception god");
        }
        return m;
    }


    public static Map<Long, Vector<String> > getAllPosition(){

        Map<Long, Vector<String>> m = new HashMap<>();
        String path = "D:\\TrainingData\\Region\\RegionTop.txt";
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
                    if (!cur.equals(0L))
                        m.put(cur, vs);
                    cur = tmp;
                    vs = new Vector<>();
                }
                vs.add(as[1]);
                vs.add(as[2]);
                s = in.readLine();
            }
            m.put(cur, vs);

        } catch (Exception e) {
            System.out.println("Exception god1");
        }


        return m;
    }

    public static void main(String[] args)throws Exception{
        Map<Long, Vector<String>> m = getAllLocation(getAllPosition(), ReadRegionType());
    }
}
