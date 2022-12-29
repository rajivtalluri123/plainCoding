package tree;

import javax.swing.tree.TreeNode;
import java.util.*;

public class TreeLeet {
    class Pair {
        TreeNode key;
        Integer val;
        public Pair(TreeNode key, Integer val) {
            this.key = key;
            this.val = val;
        }
    }

    int count; //for pb- 437
    Stack<TreeNode> stack = new Stack<TreeNode>(); // for 173 - binary tree iterator
    public class TreeNode {
      int val;
      boolean isBalanced; //for pb 110

      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
        TreeNode(int x, boolean isBalanced) {
          val = x;
          this.isBalanced = isBalanced;
        }
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

    // 337. House Robber III
    // The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
    //Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree.
    // It will automatically contact the police if two directly-linked houses were broken into on the same night.
    //Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
    // Input: root = [3,2,3,null,3,null,1]   Output: 7 (3 + 3 + 1 = 7)
    // alg -- use knap sack -- I am little drunk
    public int houseRobberTree(TreeNode root) {
        Map<TreeNode, Integer> robMap = new HashMap<>();
        Map<TreeNode, Integer> notRobMap = new HashMap<>();
        return Math.max(rob(root, true, robMap, notRobMap), rob(root, false, robMap, notRobMap));
    }
    private int rob(TreeNode node, boolean rob, Map<TreeNode, Integer> robMap, Map<TreeNode, Integer>  notRobMap) {
        if(node == null)
            return 0;
        int currAmount =0;
        if(rob) {
            //knap sack
            if(robMap.containsKey(node))
                return robMap.get(node);
            currAmount = Math.max(node.val + rob(node.left, false, robMap, notRobMap) + rob(node.right, false, robMap, notRobMap), rob(node.left, true, robMap, notRobMap) + rob(node.right, true, robMap, notRobMap));
            robMap.put(node, currAmount);
        } else {
            currAmount = rob(node.left, true, robMap, notRobMap) + rob(node.right, true, robMap, notRobMap);
            notRobMap.put(node, currAmount);
        }
        return currAmount;
    }

    //110. Balanced Binary Tree
    // Given a binary tree, determine if it is height-balanced
    //alg - dfs
    public boolean isBalanced(TreeNode root) {
        return isBalancedUtility(root).isBalanced;
    }
    private TreeNode isBalancedUtility(TreeNode node) {
        if(node == null)
            return new TreeNode(-1, true);
        TreeNode left = isBalancedUtility(node.left);
        if(!left.isBalanced)
            return new TreeNode(-1, false);
        TreeNode right =  isBalancedUtility(node.right);
        if(!right.isBalanced)
            return new TreeNode(-1, false);
        if(Math.abs(left.val - right.val) < 2) {
            //isbalanced
            return new TreeNode(Math.max(left.val, right.val) +1, true);
        }
        return new TreeNode(-1, false);
    }

    // 437. Path Sum III
    // Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
    //The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
    //alg -- use map for prefisSum and their counts and do dfs
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> sumCount = new HashMap<>();
        sumCount.put(0,1); //need this to get path from root
        dfs(root, sumCount, 0, sum);
        return count;
    }
    private void dfs(TreeNode node, Map<Integer, Integer> sumCount, int currSum, int sum) {
        if(node == null)
            return;
        currSum += node.val;
        if(sumCount.containsKey(currSum - sum)) {
            count += sumCount.get(currSum - sum);
        }
        sumCount.put(currSum, sumCount.getOrDefault(currSum, 0) +1);
        dfs(node.left, sumCount, currSum, sum);
        dfs(node.right, sumCount, currSum, sum);
        sumCount.put(currSum, sumCount.get(currSum) -1);

    }

    // 173. Binary Search Tree Iterator
    //done using custom stack
    public void pushLeftNodes(TreeNode root) {
        //save left part
        while(root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    public int next() {
        //pushLeft should be called once before this method..top has left most node
        //top of stack
        TreeNode currNode = stack.pop();
        pushLeftNodes(currNode.right);
        return currNode.val;
    }
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    // 310. Minimum Height Trees
    // A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
    //Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
    //Return a list of all MHTs' root labels. You can return the answer in any order.
    //The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
    // i/p - n = 4, edges = [[1,0],[1,2],[1,3]]  o/p - [1]
    // i/p - n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]  o/p - [3,4]
    //alg- this problem can be converted to finding centroids (at max 2). remove leaf nodes layer by layer untill u get centroids which is similarc to topological sorting
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List<Integer> centroids = new ArrayList<>();
        //edge case
        if(n < 2) {
            for(int i =0; i <= n; i++)
                centroids.add(i);
            return centroids;
        }

        //step1 - form a graph / tree here
        Map<Integer, Set<Integer>> graph = new HashMap<>(); // removing is fast using set
        for(int i =0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        List<Integer> leaves = new ArrayList<>();
        for(Integer key : graph.keySet()) {
            if(graph.get(key).size() == 1) {
                //is a leaf
                leaves.add(key);
            }
        }
        int remaining = leaves.size();
        while(remaining > 2) {
            remaining -= leaves.size();
            //take off curr leaves
            List<Integer> nextLeaves = new ArrayList<>();
            for(int leaf : leaves) {
                int possibleNextLeaf = graph.get(leaf).iterator().next();  //leaf has only one
                graph.get(possibleNextLeaf).remove(leaf);
                if(graph.get(possibleNextLeaf).size() == 1) {
                    //it is my next leaf as it has only 1 and that woule be parent
                    nextLeaves.add(possibleNextLeaf);
                }
                graph.remove(leaf);

            }
            leaves = nextLeaves;
        }
        return  leaves;

    }

    // 662. Maximum Width of Binary Tree
    // Given the root of a binary tree, return the maximum width of the given tree.
    //The maximum width of a tree is the maximum width among all levels.
    //The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes), where the null nodes between the end-nodes that would be present in a complete binary tree extending down to that level are also counted into the length calculation.
    // checkout example -- including null are little tricky to understans
    //Alg - use bfs and index staring from 0 on very level of tree [left child - 2*n, right node - 2*n +1],  width of every level is indexOfLast - indexOfFirst + 1
    public int widthOfBinaryTree(TreeNode root) {
        //use index of the nodes to cal the width
        //start next level from 2n, 2n + 1, ...
        // width is indexOfLast - indexOfFirst + 1

        Integer max = Integer.MIN_VALUE;

        //traverse through levels using bfs
        LinkedList<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        while(!queue.isEmpty()) {
            Pair head = queue.getFirst();
            int size = queue.size();
            Pair curr = null;
            for(int i =0; i < size; i++) {
                curr =  queue.removeFirst();
                TreeNode currNode = curr.key;
                if(currNode.left != null) {
                    queue.add(new Pair(currNode.left, 2*curr.val));
                }
                if(currNode.right != null) {
                    queue.add(new Pair(currNode.right, 2*curr.val +1));
                }
            }
            //head  is first node in the level and curr after for loop is last node
            max = Math.max(max, curr.val - head.val +1);
        }

        return max;

    }




 }
