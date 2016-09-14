
class Snode {
    int left;
    int right;
    int cover;
}

public class SegmentTree {
    public Snode[] tree = new Snode[100];
    public void buildTree(int i, int left, int right, int value) {
        tree[i] = new Snode();
        tree[i].left = left;
        tree[i].right = right;
        tree[i].cover = value;
        if (left == right) return;
        int mid = left + (right - left) / 2;
        buildTree(i*2, left, mid, 0);
        buildTree(i*2+1, mid+1, right, 0);
    }
    public void updateTree(int left, int right, int value, int i) {
        Snode s = tree[i];
        if (s.left == left && s.right == right) { s.cover += value; return ;}
        int mid = s.left + (s.right - s.left) / 2;
        if (left >= mid) updateTree(mid, right, value, i*2);
        else if (right < mid) updateTree(left, mid, value, i*2+1);
        else updateTree(mid+1, right, value, i*2);
    }

    // for overlap case will return false directly.

    public boolean ifAllCover(int i) {
        Snode root = tree[i];
        if (root.cover > 0) return true;
        else return (ifAllCover(i*2) && ifAllCover(i*2+1));
    }

    public boolean update(int i) {
        if (i == 1) return true;
        else {
            tree[i].cover = 10;
            return update(i / 2);
        }
    }

    public boolean insert(int i, int begin, int end) {
        Snode node = tree[i];
        if (end < node.left) return true;
        if (begin > node.right) return true;

        if (node.left == begin && node.right == end) {
            node.cover = 1;
            return true;
        } else {
            int mid = node.left + (node.right - node.left) / 2;
            insert(i*2, begin, mid);
            insert(i*2+1, mid+1, end);
            return true;
        }
    }
}
