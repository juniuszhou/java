import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

class IntervalTreeNode {
    public int left;
    public int right;
}

public class IntervalTree {

    public TreeSet<IntervalTreeNode> tree = new TreeSet<IntervalTreeNode>();
    public TreeMap<IntervalTreeNode, Integer> map = new TreeMap<IntervalTreeNode, Integer>();


    public PriorityQueue<IntervalTreeNode> pq = new PriorityQueue<IntervalTreeNode>(new Comparator<IntervalTreeNode>() {
        public int compare(IntervalTreeNode o1, IntervalTreeNode o2) {
            return o1.left - o2.left;
        }
    });

    public void insert(IntervalTreeNode node) {

        tree.floor(node);
        tree.ceiling(node);
        tree.first();
        tree.last();

        pq.add(node);
    }


}
