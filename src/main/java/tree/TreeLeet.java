package tree;


import java.util.*;

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

    //1469. Find All The Lonely Nodes
    // In a binary tree, a lonely node is a node that is the only child of its parent node. The root of the tree is not lonely because it does not have a parent node.
    //
    //Given the root of a binary tree, return an array containing the values of all lonely nodes in the tree. Return the list in any order.
    // Input: root = [1,2,3,null,4]
    // o/p -- [4]
    // alg -- start the bfs process from the root and track all the single child nodes
    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if(curr.left == null || curr.right == null) {
                if(curr.left != null) {
                    res.add(curr.left.val);  //left is a lonely child
                    queue.add(curr.left);
                }
                if(curr.right != null) {
                    res.add(curr.right.val);  //right is a lonely child
                    queue.add(curr.right);
                }
            } else {
                queue.add(curr.left);
                queue.add(curr.right);
            }
        }
        return res;
    }
}
