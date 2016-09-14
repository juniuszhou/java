package Google;

class TrieTreeNode {
    public TrieTreeNode[] nodes = new TrieTreeNode[26];
    public boolean isLeaf = false;
}

public class TrieTree {
    public TrieTreeNode root = null;
    public void init() {
        root = new TrieTreeNode();
        root.isLeaf = false;
    }

    public void add(String s) {
        TrieTreeNode tmp = root;
        for(int i=0;i<s.length();i++) {
            int index = s.charAt(i) - 'a';
            if (tmp.nodes[index] == null)
                tmp.nodes[index] = new TrieTreeNode();
            tmp = tmp.nodes[index];
        }
        tmp.isLeaf = true;
    }

    public boolean ifExist(String s) {
        TrieTreeNode tmp = root;
        for(int i=0;i<s.length();i++) {
            int index = s.charAt(i) - 'a';
            if (tmp.nodes[index] == null) return false;
            tmp = tmp.nodes[index];
        }
        return tmp.isLeaf;
    }

    public static void main(String[] args) {
        TrieTreeNode node = new TrieTreeNode();
        node.isLeaf = true;
        System.out.println("Hello");
    }
}
