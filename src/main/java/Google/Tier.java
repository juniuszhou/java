package Google;
class Tnode {
    Tnode[] tree = new Tnode[256];
    char value;
    boolean isLeaf;
}

public class Tier {
    Tnode[] tree = new Tnode[256];

    public static void main(String[] args) {
        Tier t = new Tier();
        if (t.tree[0] == null) System.out.println("is null");
    }
}
