package Chinese;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyUtilily {
    public static Map<Long, String> ReadToMap(String path)throws Exception{
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

    public static void WriteMapToFile(Map<Long, String> m , String path)throws Exception{

        FileWriter fw = new FileWriter(path);

        Iterator it = m.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();

            fw.write(pairs.getKey().toString() + "\t" + pairs.getValue() + "\r\n");
        }
        fw.close();
    }
}
