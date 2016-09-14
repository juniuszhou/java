package Google;

import java.util.Stack;

/**
 * Created by junius on 16-7-27.
 */
public class TreeOps {
    double a  = 1e-9; // 0.000000001
    public void preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
    }

    public static void main(String[] args) {
        String[][] a = {{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
        int[] j = {1, 2, 3};
        int[] i = {1,2};
        System.out.println();
    }
}
