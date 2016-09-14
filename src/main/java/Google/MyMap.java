package Google;

import java.util.*;


public class MyMap {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "1"); // put or update
        map.get("1");
        map.containsKey("1"); // if in

        TreeMap<String, String> map2 = new TreeMap<String, String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        map2.ceilingKey("a");
        map2.ceilingEntry("a");


    }
}
