import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

class IntPair{
    private int mFirst;
    private int mSecond;
    public IntPair(int first, int second){
        mFirst = first;
        mSecond = second;
    }
    public int getFirst() {return mFirst;}
    public int getSecond() {return mSecond;}
}

class RangeTree{
    //in case of range across several existing ranges.
    private void RemoveBetween(int low, int high){
        System.out.println("remove " + low + " " + high);
        int i = low;
        while (low < high){
            Map.Entry<Integer,Integer> lowEntry = mTreeMap.ceilingEntry(low);
            if (lowEntry == null || high <= lowEntry.getValue())
                return;
            mTreeMap.remove(lowEntry.getKey());
            low = lowEntry.getValue() + 1;
        }
    }

    public RangeTree(int lowBoundary, int highBoundary){
        mLowBoundary = lowBoundary;
        mHighBoundary = highBoundary;
        mTreeMap = new TreeMap<Integer, Integer>();
    }
    public void insertNode(int lowBoundary, int highBoundary){
        if (highBoundary < lowBoundary) return;
        if (lowBoundary > mHighBoundary) return;
        if (highBoundary < mLowBoundary) return;

        if (lowBoundary < mLowBoundary) lowBoundary = mLowBoundary;
        if (highBoundary > mHighBoundary) highBoundary = mHighBoundary;

        int removeLow;
        int removeHigh;

        //deal low boundary, which range I fall in
        Map.Entry<Integer,Integer> lowEntry = mTreeMap.floorEntry(lowBoundary);
        //null means I am new low, bigger than high boundary means I am in black hole.
        if (lowEntry == null || lowBoundary > (lowEntry.getValue()+1))
            removeLow = lowBoundary;
        else
            removeLow = lowEntry.getKey();

        //deal high boundary, which range I fall in
        Map.Entry<Integer,Integer> highEntry = mTreeMap.floorEntry(highBoundary+1);
        if (highEntry == null || highBoundary > highEntry.getValue()+1)
            removeHigh = highBoundary;
        else
            removeHigh = highEntry.getValue();

        //if not in the same range, delete across range and create new one.
        if (lowEntry != highEntry){
            RemoveBetween(removeLow, removeHigh);
        }
        mTreeMap.put(removeLow,removeHigh);
    }
    public void printTree(){
        Iterator it = mTreeMap.values().iterator();
        Map.Entry<Integer,Integer> first = mTreeMap.firstEntry();
        if (first == null){
            System.out.println("empty");
            return;
        }

        while (first != null){
            System.out.print("[" + first.getKey().toString() + "," + first.getValue() + "] ");
            first = mTreeMap.ceilingEntry(first.getValue() + 1);
        }
        System.out.println();
    }

    private int mLowBoundary;
    private int mHighBoundary;
    private TreeMap<Integer,Integer> mTreeMap;
}

public class Question2 {
    private static IntPair parseItem(String range){
        String tmp = range.trim();
        int i = tmp.indexOf('[');
        if (i == -1) return null;

        int j = tmp.lastIndexOf(']');
        if (j == -1 || i > j) return null;

        int k = tmp.indexOf(',');
        if (k == -1 || k > j || k < i) return null;

        String lowStr = tmp.substring(i+1,k);
        String highStr = tmp.substring(k+1,j);

        int low = Integer.parseInt(lowStr);
        int high = Integer.parseInt(highStr);
        if (low > high) return null;

        return new IntPair(low, high);
    }

    public static void main(String[] args) {
        if (args.length < 1){
            System.out.println("invalid");
            return;
        }

        IntPair pair = parseItem(args[0]);
        if (pair == null){
            System.out.println("invalid");
            return;
        }

        RangeTree tree = new RangeTree(pair.getFirst(), pair.getSecond());
        for(int i = 1; i < args.length; ++i){
            System.out.println(args[i]);
            pair = parseItem(args[i]);
            if (pair != null){
                tree.insertNode(pair.getFirst(), pair.getSecond());
            }
        }
        tree.printTree();
    }
}
