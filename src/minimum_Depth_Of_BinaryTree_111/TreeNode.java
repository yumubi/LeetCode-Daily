package src.minimum_Depth_Of_BinaryTree_111;

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
            * 不行，不会写
            * @param root
            * @return
            */
           public int minDepth(TreeNode root) {
                return minDepth(root,1);
           }

           public int minDepth(TreeNode root, int level) {
               if(root == null) return --level;
               if(root.left == null && root.right == null )  return level;
               else if(root.left == null) return minDepth(root.right, ++level);
               else if(root.right == null) return minDepth(root.left, ++level);
               else
                   return Math.min(minDepth(root.left,++level), minDepth(root.right, ++level));
           }

       }
}
