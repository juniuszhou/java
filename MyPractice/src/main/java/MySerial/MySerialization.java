package MySerial;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

import java.io.*;

/**
 * Created by juzhou on 1/21/2015.
 */
class Foo implements java.io.Serializable{
    public Foo(){
        id = "0";
        name = "junius";
    }
    private String id;
    private String name;
    private void ReadObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException{
        id = (String) in.readObject();
        name = (String) in.readObject();
    }
    private void WriteObject(ObjectOutputStream out)
            throws IOException{
        out.writeObject(id);
        out.writeObject(name);
    }
    public String toString(){
        return id + " Foo " + name;
    }
}
public class MySerialization {
    public static void OutFoo()throws Exception{
        Foo f = new Foo();
        FileOutputStream fos = new FileOutputStream("D:\\obj.txt");
        ObjectOutputStream ows = new ObjectOutputStream(fos);
        ows.writeObject(f);
        ows.close();
    }

    public static void InFoo()throws Exception{

        FileInputStream fis = new FileInputStream("D:\\obj.txt");
        ObjectInputStream ows = new ObjectInputStream(fis);
        Foo f = (Foo) ows.readObject();
        ows.close();
        System.out.println(f.toString());
    }

    public static void main(String[] args) throws Exception{
        //OutFoo();
        InFoo();
    }
}
