package src.binaryTree_Postorder_Traversal_145;


import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int val;
   TreeNode left;
   TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val,TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    class Solution {

        /**
         * ac了
         * @param root
         * @return
         */
        public List<Integer> postorderTravsersal(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            helper(root, res);
            return res;
        }

        public void helper(TreeNode root, List<Integer> res) {
            if(root == null) return;
            helper(root.left, res);
            helper(root.right, res);
            res.add(root.val);
        }
    }



}
