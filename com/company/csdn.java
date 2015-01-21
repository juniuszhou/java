/**
 * Created by junijun on 5/2/14.
 */
import java.io.*;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Random;

class MyServer extends Thread{
    public void run(){
        try {
            ServerSocket sst = new ServerSocket();
            InetSocketAddress isa = new InetSocketAddress("localhost",8899);
            sst.bind(isa);
            OutputStream os;
            Socket sck;
            sck = sst.accept();

            System.out.println("client coming");
            os = sck.getOutputStream();

            System.out.println("client coming");
            while (true){
            os.write("12345678".getBytes());
            os.flush();
            }
        }
        catch (Exception e){
            System.out.println("server exception");
        }
    }
}

class MyClient extends Thread{
    public void run(){
        try {
            Socket sc = new Socket();

            InetSocketAddress isa = new InetSocketAddress("localhost",8899);
            sc.connect(isa);
            InputStream is = sc.getInputStream();

            char [] buffer = new char[200];
            InputStreamReader isr = new InputStreamReader(is);
            //isr.read(buffer);

            while (isr.read(buffer) > 0)
            {
                String str = new String(buffer);
                System.out.println("client got " + str);
            }
            System.out.println("client exit");
        }
        catch (Exception e){
            System.out.println("client exception");
        }
    }
}

public class csdn {
    public static void main(String args[]) throws Exception{
        MyServer server = new MyServer();
        server.start();
        MyClient client = new MyClient();
        client.start();
        System.out.println("start");

        Thread.sleep(1000);

        System.out.println("over");
    }
}
