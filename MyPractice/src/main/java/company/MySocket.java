package company;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by junius on 14-9-19.
 */
public class MySocket {
    public static void main(String[] args) {
        Socket st = new Socket();
        SocketAddress sa = new InetSocketAddress(80);
        try {
            st.bind(sa);

            ServerSocket ss = new ServerSocket(8080);
            ss.accept();

            //ss.
        }
        catch (Exception e){

        }



    }
}
