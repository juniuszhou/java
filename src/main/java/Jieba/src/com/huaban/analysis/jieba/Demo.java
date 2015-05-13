package com.huaban.analysis.jieba;


import java.util.List;

public class Demo {
    public static void main(String [] args){
        JiebaSegmenter segmenter = new JiebaSegmenter();
        String sentence =
                new String("这是一个伸手不见五指的黑夜。找小姐，学生妹");
        List<SegToken> res = segmenter.process(sentence, JiebaSegmenter.SegMode.SEARCH);
        for(int i = 0; i < res.size(); i++)
            System.out.println(res.get(i).word + " Finally");

        System.out.println("Over ");
    }
}
