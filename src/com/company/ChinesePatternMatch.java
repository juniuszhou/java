package com.company;

import java.util.regex.Pattern;

/**
 * Created by juzhou on 11/19/2014.
 */
public class ChinesePatternMatch {
    public static void main(String [] args){
        String str = "一二三四";
        System.out.println(str);

        boolean b = Pattern.matches("一.*四", str);
        if (b) System.out.println("matched.");

        String str1 = "abbbbba";
        b = Pattern.matches("ab*ba", str1);
        if (b) System.out.println("matched.");

    }
}
