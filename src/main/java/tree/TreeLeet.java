package tree;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TreeLeet {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    /////////////Level 2 problems--------

   // 297. Serialize and Deserialize Binary Tree
   // Encodes a tree to a single string.
   public String serialize(TreeNode root) {
       //do pre order traversal and save it in array
       //also save in_oreder to deserialize
       StringBuilder sb = new StringBuilder("");
       String str =  preorderTraverse(root, sb).toString();
       return str;
   }
   private StringBuilder preorderTraverse(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append("null,");
            return sb;
        }
        sb.append(String.valueOf(root.val) + ",");
        preorderTraverse(root.left, sb);
        preorderTraverse(root.right,sb);
        return sb;
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strings = data.split(",");
        //ll is more useful for removes and adds
        List<String> ll = new LinkedList(Arrays.asList(strings));
        return rdeserialize(ll);
    }
    private TreeNode rdeserialize(List<String> ll) {
        //base case
        if(ll.get(0).equals("null")) {
            ll.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(ll.get(0)));
        ll.remove(0);
        root.left = rdeserialize(ll);
        root.right = rdeserialize(ll);
        return root;

    }


}
