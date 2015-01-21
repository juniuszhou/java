import java.io.*;
import java.util.ArrayList;

/**
 * Created by junijun on 4/16/14.
 */
import java.util.ArrayList;
import java.util.List;

class temp1<T>{
    void printObj(){}
}

//partial polynomial not supported
//class temp1<object>{}

public class templateClass {
    public static void main(String [] args)throws Exception {
        temp1<Integer> tii = new temp1<Integer>();
        File fl = new File("my.txt");

        FileOutputStream osw = new FileOutputStream(fl);
        FileWriter fwr = new FileWriter(fl);

        String str = "Hello world!";
        osw.write(str.getBytes());
        osw.flush();
        osw.close();

        InputStreamReader isr = new FileReader(fl);
        char [] buf = new char [1024];
        isr.read(buf);
        String str2 = new String(buf);
        // you can do this conversion since java doesn't support variance
        // List <Object> lob = new ArrayList<String>();
       // lob.add(new String("injnasdf"));


        System.out.println(str2);
    }
}
