package src.search_In_BST_700;

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
           TreeNode target;
           public TreeNode searchBST(TreeNode root, int val) {
                dfs(root, val);
                return target;
           }
           public void dfs(TreeNode root,int val) {
               if(root == null) return;
               if(root.val == val){
                   target = root;
                   return;
               }
               dfs(root.left, val);
               dfs(root.right, val);
           }
       }
}
