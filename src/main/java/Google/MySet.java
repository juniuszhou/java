package Google;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class MySet {
    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        set.add("1");
        set.remove("1");
        set.contains("1");


        TreeSet<String> set2 = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        set2.ceiling("a");
        set2.floor("a");
        set2.remove("a");
        set2.add("a");
    }
}
