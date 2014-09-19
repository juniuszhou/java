import java.io.*;
import java.net.*;
import java.lang.Thread;
import java.nio.*;
import java.nio.channels.spi.SelectorProvider;
import java.nio.channels.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;
import java.nio.file.*;
import java.nio.channels.*;

class aClient extends Thread{
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

class aServer extends Thread{
    public ServerSocket ss;
    public void run(){
        try{
            SocketChannel sc = SocketChannel.open();
            InetSocketAddress sinaAddr = new InetSocketAddress("sina.com",80);
            sc.socket().connect(sinaAddr);

            //init a selector via helper class selector provider
            Selector aSel = SelectorProvider.provider().openSelector();

            Socket soc = new Socket("host",80);
            soc.getChannel().register(aSel,SelectionKey.OP_ACCEPT);


            //init a channel for server socket. method 1
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(true);

            //method 2, get server socket first, then init its channel
            ServerSocket ss = new ServerSocket();
            ServerSocketChannel ssc2 = ss.getChannel();

            //set socket channel as non blocking.
            ssc.configureBlocking(false);

            InetSocketAddress isa = new InetSocketAddress("localhost",9999);
            ssc.socket().bind(isa);

            SelectionKey acceptKey = ssc.register(aSel,SelectionKey.OP_ACCEPT);
            int keysAdded = 0;
            while ((keysAdded = aSel.select()) > 0){
                Set readyKeys = aSel.selectedKeys();
                Iterator i = readyKeys.iterator();
                while (i.hasNext()){
                    SelectionKey sk = (SelectionKey) i.next();
                    i.remove();
                    ServerSocketChannel n = (ServerSocketChannel) sk.channel();
                    //now we got a new socket and its channel after accept in server
                    Socket s = n.accept().socket();
                    SocketChannel socketChannel = s.getChannel();
                    socketChannel.register(aSel,SelectionKey.OP_READ);

                }
            }
        }
        catch (Exception e){

        }
    }
}
public class JavaNewIO {
    public static void main(String args[]) {
        try {
            aServer asr = new aServer();

            //file channel.
            FileInputStream is = new FileInputStream("");
            is.read();
            FileChannel cha = is.getChannel();
            ByteBuffer bf = ByteBuffer.allocate(1024);
            bf.flip();

            cha.read(bf);

            //Path Paths
            Path pth = Paths.get("","");

            //Files some static operation.
            Files.newByteChannel(pth);
            Files.copy(pth,pth);
            //file attribute, other different class for dos and posix system.
            BasicFileAttributes bas = Files.readAttributes(pth,BasicFileAttributes.class);
            bas.size();

        }
        catch (Exception e) {
            System.err.println(e);
        }

        System.out.println("hello ");
    }
}
