package Chinese;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * Created by juzhou on 12/12/2014.
 */
public class Filter {
    public static void main(String[] args) {
        try {
            String path = "D:\\dev\\work\\nlq.txt";
            String path2 = "D:\\dev\\work\\nlq_unique.txt";
            File f = new File(path);
            File f2 = new File(path2);

            BufferedReader in = new BufferedReader(new FileReader(f));
            Vector<String> result = new Vector<String>();
            Pattern p = Pattern.compile(
                    ".*(如家|速八|7天|公寓|宾馆|旅社|旅馆|锦江|招待所|客栈|旅舍|饭店).*");
            while (in.ready()) {
                String s = in.readLine();

                //System.out.println(s);
                if (p.matcher(s).matches())
                    System.out.println(s);
                else if (s.length() > 1) {
                    result.add(s);
                }
            }


            BufferedWriter bw = new BufferedWriter(new FileWriter(f2));
            //bw.write();
            int i = 0;
            while (i < result.size()){
                bw.write(result.get(i) + "\r\n");
                i++;
            }


            in.close();
            bw.close();
        }
        catch (Exception e){

        }


    }
}
