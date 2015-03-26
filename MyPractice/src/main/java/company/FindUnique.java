package company;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by juzhou on 11/26/2014.
 */
public class FindUnique {
    public static void main(String[] args) {
        try {
            String path = "D:\\dev\\work\\query\\user_query_for_test.txt";
            String path2 = "D:\\dev\\work\\query\\user_query_for_test_unique.txt";
            File f = new File(path);
            File f2 = new File(path2);

            BufferedReader in = new BufferedReader(new FileReader(f));
            HashMap<String, String> hm = new HashMap<String, String>();

            while (in.ready()) {
                String s = in.readLine();
                //System.out.println(s);
                String[] arr = s.split("\\s+");
                String mapIndex = new String("");
                for (int i = 0; i < arr.length; ++i){
                    //System.out.println("Item " + arr.length + arr[i]);
                    mapIndex += arr[i];
                }
                hm.put(mapIndex, mapIndex);
            }

            Iterator<String> i =  hm.keySet().iterator();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f2));
            //bw.write();

            while (i.hasNext()){
                bw.write(i.next() + "\r\n");
                //System.out.println(i.next());
            }
            in.close();
            bw.close();
        }
        catch (Exception e){

        }


    }

}
