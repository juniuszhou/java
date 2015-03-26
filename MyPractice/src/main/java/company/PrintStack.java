package company;
import java.io.*;

public class PrintStack{
    public static void main(String[] args) {

        File f = new File("/home/junius/txt");
        try {
            InputStream is = new FileInputStream("ok");
            is.read();
        }
        catch (Exception e){
            e.toString();
        }

        try {
            Reader rr = new FileReader("ok");
            rr.read();

        }
        catch (Exception e){
            e.toString();
        }

    }
}
