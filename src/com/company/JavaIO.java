/**
 * Created by junijun on 4/8/14.
 */
import java.io.*;
import java.nio.*;
import java.net.*;
import java.lang.Thread;

class Client extends Thread{
    public Socket sc;
    public void run(){
        try{
            sc = new Socket();
            InetSocketAddress isa = new InetSocketAddress("localhost",9999);
            sc.connect(isa);
            OutputStream ost = sc.getOutputStream();

            for(int i=0;i<10;++i){
                String str = "client interation " + (new Integer(i)).toString();
                ost.write(str.getBytes());
                ost.flush();
                System.out.println("Client at " + (new Integer(i)).toString());
                sleep(1000);
            }
        }
        catch (Exception e){
        }
    }
}

class Server extends Thread{
    public ServerSocket ss;
    public void run(){
        try{
            ss = new ServerSocket();
            InetSocketAddress isa = new InetSocketAddress("localhost",9999);
            ss.bind(isa);
            Socket sc = ss.accept();
            InputStream ist = sc.getInputStream();
            InputStreamReader isr = new InputStreamReader(ist);

            byte [] buf = new byte[4096];
            while(ist.read(buf) > 0){
                String str = new String(buf);

                System.out.println("Server got " + str);
                if (str.contains("9")){
                    System.out.println("Server got last message ");
                    return;
                }
            }
        }
        catch (Exception e){

        }

    }

}
public class JavaIO {
    public static void main(String args[]) {
        try {
             Client cli = new Client();
             cli.start();

             Server ser = new Server();
             ser.start();
        }
        catch (Throwable e) {
            System.err.println(e);
        }

        System.out.println("hello ");
    }
}
