/**
 * Created by junijun on 4/15/14.
 */

import java.util.*;
import java.lang.ref.WeakReference;
import java.lang.ref.SoftReference;
import java.lang.ref.PhantomReference;
import java.math.BigInteger;

/*      Collection
        ├List
        │├LinkedList
        │├ArrayList
        │└Vector
        │　└Stack
        └Set
        Map
        ├Hashtable
        ├HashMap
        └WeakHashMap
*/

public class dataType {
    public static void main(String []  args){
        //8 basic types
        int a;short b;char c;boolean d;long e;double f;byte g;float h;
        //rich wrapper
        Integer i; String j; Boolean k; Double l;Long m;Character n; Short o;Float p;Byte q;

        //big interger
        BigInteger bi = new BigInteger("0",10);
        bi.add(new BigInteger("1",10));

        //WeakReference SoftReference.
        //strong reference object never freed from memory even out of memory
        //soft reference object can be freed when no enough memory
        //weak reference object can be freed when gc triggered.
        //PhantomReference no any effect on object 's free, just hold a value.
        j = new String();
        WeakReference<String> s =  new WeakReference<String>(j);
        SoftReference<String> sr = new SoftReference<String>(j);
        //PhantomReference<String> sph = new PhantomReference<String>();

        //vector
        Byte [] byteVector = new Byte[100];

        // Container array implemented in deque and list
        ArrayList<String> as1 = new ArrayList<String>();
        as1.add(0,"hello");

        Arrays.binarySearch(as1.toArray(),0,1, "hello");

        //Set
        SortedSet<String> st1 = new TreeSet<String>();
        HashSet<String> st2 = new HashSet<String>();

        //Map
        Map mp1 = new HashMap();
        Map mp2 = new TreeMap();
        mp1.put(1,1);
        Map mp3 = new WeakHashMap(); //the key is weak reference.

        //vector is thread safe container
        Vector<Integer> vi1 = new Vector<Integer>();
        vi1.add(new Integer(1));

        //List , item could be any type.
        List ll1 = new LinkedList();
        ll1.add(1);ll1.add("");
        //List<Int> lint = new LinkedList<Int>();

        Enumeration<String> em = Collections.enumeration(st1);
        for(;em.hasMoreElements();){System.out.println(em.nextElement());}

        String str2 = new String();
        String str3 = new String();
        if (str2.equals(str3)){}
        if (str2 == str3) {}

    }
}
