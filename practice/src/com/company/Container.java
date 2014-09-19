import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by junijun on 6/28/14.
 */
public class Container {
    public static void main(String[] args) {
        //Vector is a thread safe container
        Vector vt = new Vector();
        //Hash table is a thread safe container
        Hashtable ht = new Hashtable();

        //default init size is 16 and load factor is 0.75
        HashSet hs1 = new HashSet();
        HashSet hs2 = new HashSet(16);
        HashSet hs3 = new HashSet(16, 0.75f);

        //implemented in black red tree
        TreeSet ts = new TreeSet();

        //
        ArrayDeque aq = new ArrayDeque();
        ArrayList al = new ArrayList();

        //
        HashMap<String, String> hmss = new HashMap<String, String>();


        //ReentrantLock used for CopyOnWriteArrayList. if set/add/remove. get a
        //brand new array and copy old value and set new value. then use the new array
        //to replace old array. useful if most threads just read.
        CopyOnWriteArrayList cowal = new CopyOnWriteArrayList();
        CopyOnWriteArraySet cowars = new CopyOnWriteArraySet();



    }
}
