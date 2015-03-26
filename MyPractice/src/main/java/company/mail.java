package company;

import java.io.*;


public class mail{
    public static void exps()throws IOException{

    }
    public static void main(String [] args){
        try{
        exps();
        }
        catch (IOException e){
        }


        try{
            String str = new String("heojjqew joij qweih ojhqiew ");
            StringReader sr = new StringReader(str);
            char [] buf2 = new char[3];
            while (sr.read(buf2) > 0)
                System.out.println(buf2);
        }

        catch (Exception e){
            System.out.println("exception");
        }
        System.out.println("hello");
    }
}

