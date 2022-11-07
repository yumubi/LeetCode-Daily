package src.binary_Tree_Inorder_Traversal;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    class Solution{

        /**
         *
         *
         * @param root
         * @return
         */
        public List<Integer> inorderTraversal(TreeNode root) {
            if(root == null) return null;
            List<Integer> list = new ArrayList<>();
            return inorderTraversal(root, list);
        }

        private List<Integer> inorderTraversal(TreeNode root, List<Integer> list) {
            if(root == null) return list;
            if( root != null && root.left != null) {
                inorderTraversal(root.left, list).add(root.left.val);
            }
           inorderTraversal(root.left, list).add(root.val);

            if(root != null && root.right != null) {
                inorderTraversal(root.right, list).add(root.right.val);
            }
            return list;
        }

    }



}
