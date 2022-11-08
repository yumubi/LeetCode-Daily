package src.maximum_Depth_Of_BinaryTree_104;

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

           /**
            * my answer，看来labuladong
            */
           int depth = 0;
           int maxdepth = 0;
           public int maxDepth(TreeNode root) {
               return tranvserse(root);
           }

           public int tranvserse(TreeNode root) {
               if(root == null) return 0;
               depth++;
               int leftDepth = tranvserse(root.left);
               int rightDepth = tranvserse(root.right);
               maxdepth = Math.max(leftDepth, rightDepth) + 1;
                depth--;
                return maxdepth;
           }





       }
}
