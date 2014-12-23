package Chinese;

import java.io.*;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * Created by juzhou on 12/18/2014.
 */
public class JoinLocation {
    public static void main(String[] args) {
        try {
            int len = 0;
            String path = "D:\\atlas.region.txt";
            String path2 = "D:\\dev\\work\\nlq_unique.txt";
            File f = new File(path);
            File f2 = new File(path2);

            BufferedReader in = new BufferedReader(new FileReader(f));
            Vector<Vector<String>> result = new Vector<Vector<String>>();

            while (in.ready()) {
                String s = in.readLine();
                String [] as = s.split("\t");
                Vector<String> vs = new Vector<String>();
                for (String ss: as) {
                    System.out.print(ss + " jj ");
                    vs.add(ss);
                }
                result.add(vs);
                System.out.println( " " + as.length);
            }

            Vector<Vector<String>> result2 = new Vector<Vector<String>>();

            for(Vector<String> vvv : result){
                Vector<String> vs = new Vector<String>();
                vs = vvv;

            }


            in.close();
            //bw.close();
        }
        catch (Exception e){

        }


    }
}
