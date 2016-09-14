package Google;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

class skipTable {
    // http://www.acmerblog.com/skip-list-impl-java-5773.html

    // why just concurrent skip list.
    // because of its internal structure, if you modify a node, you just need lock it prev node
    // and next node. so a thread safe implementation is meaningful.
    public static void main(String[] args) {
        // both map and set are kept order in skip table since it is ordered via key.
        // only difference is map contains two elements.
        ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<String, String>(
                new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        ConcurrentSkipListSet<String> set = new ConcurrentSkipListSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        map.ceilingEntry("a");map.put("a", "a");map.remove("a", "a");
        set.ceiling("a");


    }
}