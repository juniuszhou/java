package Google;


class USSNode {
    int parent;
    int rank;
}

public class UnionSearchSet {
    int length;
    USSNode[] a;
    void init(int len) {
        a = new USSNode[len];
        length = len;
        for(int i=0;i<len;i++) {
            USSNode node = new USSNode();
            node.parent = i;
            node.rank = 0;
            a[i] = node;
        }
    }

    int search(int i) {
        while (a[i].parent != i) {
            i = a[i].parent;
        }
        return i;
    }

    void union(int i, int j) {
        int pi = search(i);
        int pj = search(j);
        if (pi == pj) return;
        if (a[pi].rank > a[pj].rank) {
            a[pi].parent = pj;
        } else {
            a[pj].parent = pi;
        }
        if (a[pi].rank == a[pj].rank) a[pi].rank++;
    }

    void compress(int i) {
        int p = search(i);
        while (i != p) {
            int tmp = a[i].parent;
            a[i].parent = p;
            i = tmp;
        }
    }
}
