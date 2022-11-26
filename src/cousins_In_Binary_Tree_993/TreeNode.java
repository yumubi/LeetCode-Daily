package src.cousins_In_Binary_Tree_993;

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

            public boolean isCousins(TreeNode root, int x, int y) {
                int depthX = -1;
                TreeNode parentX = new TreeNode(-1);
                TreeNode parentY = new TreeNode(-1);
                int depthY = -1;
                    dfs(root, 0, x, depthX, parentX);
                    dfs(root, 0, x, depthY, parentY);
                    if(depthX == depthY && parentX.val != parentY.val) return true;
                    return false;
            }

            public void dfs(TreeNode root, int depth, int val, int ans, TreeNode parent) {
                if(root == null) return;
                if(root.val == val) {
                    ans = depth;
                    return;
                }
                parent = root;
                dfs(root.left, depth + 1, val, ans, parent);
                dfs(root.right, depth + 1,val, ans, parent);
            }
        }
}
