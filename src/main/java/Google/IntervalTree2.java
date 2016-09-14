package Google;

class IntervalTreeNode {
    int low;
    int high;
    int max;
    IntervalTreeNode left;
    IntervalTreeNode right;
    public void IntervalTreeNode(int i, int j) {
        low = i;
        high = j;
        max = j;
    }
}

public class IntervalTree {
    public IntervalTreeNode root = null;
    public boolean ifOverLap(IntervalTreeNode node1, IntervalTreeNode node2) {
        return node1.low <= node2.high && node2.low <= node1.high;
    }

    public void insert(IntervalTreeNode node) {
        if (root == null) { root = node; return;}
        IntervalTreeNode parent = null;
        IntervalTreeNode tmp = root;
        while (tmp != null) {
            parent = tmp;
            parent.max = Math.max(parent.max, node.high);
            if (tmp.low <= node.low) tmp = tmp.right;
            else  tmp = tmp.left;
        }

        if (parent.low > node.low) parent.left = node;
        else parent.right = node;
    }

    private IntervalTreeNode findOverLap(IntervalTreeNode node, IntervalTreeNode tmp) {
        if (tmp == null) return null;
        if (ifOverLap(node, tmp)) return tmp;
        if (tmp.left != null && tmp.left.max > node.low)
            return findOverLap(node, tmp.left);
        else return findOverLap(node, tmp.right);
    }

    public IntervalTreeNode findOverLap(IntervalTreeNode node) {
        return findOverLap(node, root);
    }





}
