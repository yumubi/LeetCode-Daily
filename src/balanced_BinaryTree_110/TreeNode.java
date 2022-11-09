package src.balanced_BinaryTree_110;

import java.util.Deque;
import java.util.LinkedList;

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


             //my answer 测试203  [1,2,2,3,null,null,3,4,null,null,4]没过
             public boolean isBalanced(TreeNode root) {
                 if(root == null || (root.left == null && root.right == null)) return true;

                 Deque<TreeNode> queue = new LinkedList<TreeNode>();
                 queue.offer(root);
                 while(!queue.isEmpty()) {
                     int size = queue.size();
                     while(size > 0 ) {
                         TreeNode node = queue.poll();
                         if(Math.abs( depth(root.left) - depth(root.right)) > 1) return false;
                         if(node.left != null) queue.offer(node.left);
                         if(node.right != null) queue.offer(node.right);
                         size--;
                     }
                 }
                    return true;
             }
             public int depth(TreeNode root) {
                 if(root == null) return 0;
                 return Math.max( depth(root.left), depth(root.right)) + 1;
             }
         }
}
