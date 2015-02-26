package Chinese;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * Created by juzhou on 2/12/2015.
 */
public class DealPOI {
    public static Map<Long, String> replaceLongName(){
        String path = "D:\\TrainingData\\POI\\poiLongname.txt";
        Map<Long, String> mm = new HashMap<>();
        try {
            File f = new File(path);

            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String s = in.readLine();

            while (s != null) {
                String[] as = s.split("\t");
                Long tmp = Long.parseLong(as[0]);
                mm.put(tmp, as[1]);
                // System.out.println(" " + tmp + " as " + as[1]);
                s = in.readLine();
            }
        }catch(Exception e){
            System.out.println("Exception god1");
        }
        return mm;
    }

    public static Map<Long, Vector<String> > getAllPosition(Map<Long, String> mm){

        Map<Long, Vector<String>> m = new HashMap<>();
        String path = "D:\\TrainingData\\POI\\poiInfo.txt";
        try {
            File f = new File(path);

            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String s = in.readLine();
            int ii = 0;
            while (s != null) {
                Vector<String> vs = new Vector<>();
                String[] as = s.split("\t");
                Long tmp = 0L;
                try {
                    tmp = Long.parseLong(as[6]);
                }
                catch (NumberFormatException e){
                    ii++;
                    s = in.readLine();
                    continue;
                }
                System.out.println(" " + tmp + " " + mm.get(tmp));

                // vs.add(as[0]);
                if (mm.get(tmp) == null){
                    ii++;
                    s = in.readLine();
                    continue;
                }
                vs.add(mm.get(tmp));
                for(int i = 2; i < as.length; i++){
                    vs.add(as[i]);
                }
                m.put(Long.parseLong(as[0]), vs);
                s = in.readLine();
            }

            System.out.println("not found is " + ii);
        } catch (Exception e) {
            System.out.println("Exception god");
        }
        return m;
    }

    public static void main(String[] args)throws Exception{
        Map<Long, Vector<String>> m = getAllPosition(replaceLongName());
        // replaceLongName();
        String path = "D:\\TrainingData\\POI\\newPoiInfo.txt";
        FileWriter fw = new FileWriter(path);

        for(Map.Entry<Long, Vector<String>> e: m.entrySet()){
            //System.out.print(e.getKey());
            fw.write("" + e.getKey());
            for(String s: e.getValue()) {
                //System.out.print("\t" + s);
                fw.write("\t" + s);
            }
            fw.write("\n");
        }
        fw.close();
    }
}
