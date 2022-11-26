package src.univalued_Binary_Tree_965;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    int val;
       TreeNode left;
       TreeNode right;
       TreeNode() {}
       TreeNode(int val) { this.val = val; }
       TreeNode(int val, TreeNode left, TreeNode right) {
           this.val = val;
           this.left = left;
           this.right = right;
       }


       class Solution {
           boolean flag = true;
           public boolean isUnivalTree(TreeNode root) {
               dfs(root);
               return flag;
           }
           public void dfs(TreeNode root) {
               if(root.left != null) {
                   if(root.left.val != root.val) {
                       flag = false;
                       return;
                   }
                   dfs(root.left);
               }
               if(root.right != null) {
                   if(root.right.val != root.val) {
                       flag = false;
                       return;
                   }
                   dfs(root.right);
               }
           }


           /**
            * dfs
            * 一棵树的所有节点都有相同的值，当且仅当对于树上的每一条边的两个端点，它们都有相同的值（这样根据传递性，所有节点都有相同的值）。
            * 因此，我们可以对树进行一次深度优先搜索。当搜索到节点 x 时，我们检查 x 与 x 的每一个子节点之间的边是否满足要求。
            * 例如对于左子节点而言，如果其存在并且值与 x 相同，那么我们继续向下搜索该左子节点；如果值与 x 不同，那么我们直接返回 False。

            * @param root
            * @return
            */
           public boolean isUnivalTree1(TreeNode root) {
               if(root == null) return true;
               if(root.left != null) {
                   if(root.val != root.left.val || !isUnivalTree(root.left)) {
                       return false;
                   }
               }
               if(root.right != null) {
                   if(root.val != root.right.val || !isUnivalTree(root.right)) {
                       return false;
                   }
               }
               return true;
           }

           /**
            * 模拟
            */
           int val = -1;
           public boolean isUnivalTree2(TreeNode root) {
               if(val == -1) val = root.val;
               if(root == null) return true;
               if(root.val != val) return false;
               return isUnivalTree2(root.left) && isUnivalTree2(root.right);
           }

           /**
            * 迭代
            * @param root
            * @return
            */
           public boolean isUnivalTree3(TreeNode root) {
               int val = root.val;
               Deque<TreeNode> d = new ArrayDeque<>();
               d.addLast(root);
               while(!d.isEmpty()) {
                   TreeNode poll = d.pollFirst();
                   if(poll.val != val) return false;
                   if(poll.left != null) d.addLast(poll.left);
                   if(poll.right != null) d.addLast(poll.right);
               }
               return true;
           }

           /**
            * bfs
            * @param root
            * @return
            */
           public boolean isUnivalTree4(TreeNode root) {
               int target = root.val;
               return bfs(root, target);
           }
           private boolean bfs(TreeNode root, int target) {
               Queue<TreeNode> queue = new LinkedList<>();
               queue.offer(root);
               while(!queue.isEmpty()) {
                   int curSize = queue.size();
                   for(int i = 0; i < curSize; i++) {
                       TreeNode curNode = queue.poll();
                       if(curNode.val != target) return false;
                       if(curNode.left != null) queue.offer(curNode.left);
                       if(curNode.right != null) queue.offer(curNode.right);
                   }
               }
               return true;
           }




       }
}
