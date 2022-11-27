package src.sum_Of_Root_To_Leaf_Binary_Tree_1022;
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
             int sum = 0;
             public int sumRootToLeaf(TreeNode root) {
                 if(root.left != null && root.right != null) sum += root.val;

             }






         }
}
