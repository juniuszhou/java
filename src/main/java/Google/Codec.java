package Google;

public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String left = rootLeftRight(root);
        String right = leftRootRight(root);
        return left + "," + right;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] numbers = data.split(",");
        int len = numbers.length;
        int start = 0;
        int mid = numbers.length / 2;
        if (len == 2) {
            if (numbers[0].isEmpty())
                return null;
            else return new TreeNode(Integer.valueOf(numbers[0]));
        } else {
            return de(numbers, mid, mid, numbers.length);
        }
    }

    private TreeNode de(String[] data, int len, int left, int right) {
        if (right == left) return null;
        if (right - left == 1) return new TreeNode(Integer.valueOf(data[left]));
        else {
            int position = 0;
            int i = 0;
                while( i < len) {
                    for (int j = left; j < right; j++) {
                        if (data[i].equals(data[j])) {
                            position = j;
                            i = len;
                            break;
                        }
                    }
                    i += 1;
                }

            TreeNode root = new TreeNode(Integer.valueOf(data[position]));
            if (position > left)
            root.left = de(data, len, left, position);
            if (position < right)
            root.right = de(data, len, position, right);
            return root;
        }
    }

    private String rootLeftRight(TreeNode root) {
        if (root == null) return "";
        StringBuffer sb = new StringBuffer();
        sb.append(root.val);
        String left = rootLeftRight(root.left);
        if (!left.isEmpty()) sb.append("," + left);
        String right = rootLeftRight(root.right);
        if (!right.isEmpty()) sb.append("," + right);
        return sb.toString();
    }

    private String leftRootRight(TreeNode root) {
        if (root == null) return "";
        StringBuffer sb = new StringBuffer();
        String left = rootLeftRight(root.left);
        if (!left.isEmpty()) sb.append(left + "," + root.val);
        else sb.append(root.val);
        String right = rootLeftRight(root.right);
        if (!right.isEmpty()) sb.append("," + right);
        return sb.toString();

    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        System.out.println(codec.serialize(new TreeNode(1)));
        System.out.println(codec.deserialize("1,2,2,1"));

    }
}
