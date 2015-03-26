package company;

import java.util.HashMap;

/**
 * Created by junius on 14-8-24.
 */
public class StringOps {
    public static void main(String[] args) {
        HashMap<String, Integer> hsi = new HashMap<String, Integer>();
        hsi.put("ok", 12);
        System.out.println(hsi.size());
        hsi.put("ok", 34);

        String resultString = "adf <> -=-=--=-=";
        System.out.println(resultString.replaceAll("[^a-zA-Z0-9<>]", " "));


        System.out.println(hsi.size());

        String str = new String("hello world");
        str.charAt(0);

    }
}
