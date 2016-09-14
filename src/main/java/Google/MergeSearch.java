package Google;

/**
 * Created by junius on 16-9-6.
 */
class MsNode {
    int parent;
    int value;
    int rank;
}

public class MergeSearch {
    MsNode[] a = new MsNode[100];

    public MergeSearch() {
        for(int i=0;i<100;i++) {
            MsNode node = new MsNode();
            node.value = i;
            node.parent = i;
            node.rank = 0; // depth of tree.
        }
    }

    public int getParent(int i) {
        while(a[i].parent != i) i = a[i].parent;
        return i;
    }

    public void merge(int i, int j) {
        int pi = getParent(i);
        int pj = getParent(j);
        if (pi == pj) return;

        if (a[pi].rank == a[pj].rank) {
            a[pi].parent = pj;
            a[pj].rank ++;
        } else if (a[pi].rank > a[pj].rank) {
            a[pj].parent = pi;
        } a[pi].parent = pj;
    }

    public static void main(String[] args) {

    }
}
