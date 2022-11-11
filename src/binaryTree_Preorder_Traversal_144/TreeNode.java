package src.binaryTree_Preorder_Traversal_144;

import java.util.*;

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
            * 我的前序遍历
            * @param root
            * @return
            */
           public List<Integer> preorderTravsersal(TreeNode root) {
               List<Integer> res = new ArrayList<>();
               helper(root, res);
               return res;
           }

           public void helper(TreeNode root, List<Integer> res) {
               if(root == null) return;
               res.add(root.val);
               helper(root.left, res);
               helper(root.right, res);
           }


           /**
            * 迭代
            * @param root
            * @return
            */
           public List<Integer> preorderTraversal2(TreeNode root) {
                List<Integer> res = new ArrayList<>();
                if(root == null) return res;
                Deque<TreeNode> stack = new LinkedList<TreeNode>();
                TreeNode node = root;
                while(!stack.isEmpty() || node != null) {
                    while(node != null) {
                        res.add(node.val);
                        stack.push(node);
                        node = node.left;
                    }
                    node = stack.pop();
                    node = node.right;
                }
                return res;
           }
       }

    // TODO: 2022/11/11 莫里斯遍历


    /**
     * 记录一个先push(root)的
     * @param root
     * @return
     */
       public List<Integer> preorderTravsersal3(TreeNode root) {

            List<Integer> res = new ArrayList<>();
            if(root == null) return res;

            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            while(!stack.isEmpty()) {
                TreeNode node = stack.pop();
                res.add(node.val);

                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
            return res;
       }





}
