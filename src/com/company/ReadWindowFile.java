package com.company;

import jdk.jfr.events.FileReadEvent;
import sun.reflect.annotation.ExceptionProxy;

import java.io.*;

/**
 * Created by juzhou on 11/11/2014.
 */
public class ReadWindowFile {
    public static void main(String[] args) {
        String winPath = "D:\\\\dev\\\\github\\\\CoreNLP\\\\scripts\\\\pos-tagger\\\\wsj-0-18-bidirectional-distsim.tagger.props";
        File f = new File(winPath);

        System.out.println(f.getPath());
        try {
            System.out.println(winPath);
            FileReader fr = new FileReader(f);
            char [] c = new char[1000];
            fr.read(c);
            String str = new String(c);
            System.out.println(str);

        }
        catch (Exception e) {
            System.out.println(" exception ");
        }
        if (f.isFile())
            System.out.println("is file");
        else
            System.out.println("not file");
    }
}
