package src.same_Tree_100;


import org.w3c.dom.Node;
import src.remove_Duplicates_From_Sorted_List.ListNode;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    class Solution {


        /**
         * my answer
         * @param p
         * @param q
         * @return
         */
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == q) return true;
            List<Integer> list1 = inorderTraversal(p);
            List<Integer> list2 = inorderTraversal(q);
            return list1 == list2;
        }


        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res  = new ArrayList<>();
            inorder(root, res);
            return res;
        }

        public void inorder(TreeNode root, List<Integer> res) {
            if(root == null) return;
            inorder(root.left, res);
            res.add(root.val);
            inorder(root.right, res);
        }

    }
}
