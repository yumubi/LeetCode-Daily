package src.invert_BinaryTree_226;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    class Solution {
        /**
         * 我是laji
         * @param root
         * @return
         */
        public TreeNode invertTree(TreeNode root) {
            if(root == null) return root;
            helper(root);
            return root;
        }
        public void helper(TreeNode root) {
            swap(root);
            if(root.left != null) swap(root.left);
            if(root.right != null) swap(root.right);
        }
        public void swap(TreeNode root) {
            if(root == null) return;
            while(!((root.left != null) && root.right != null)) {
                TreeNode tmp = root.left;
                root.left = root.right;
                root.right = tmp;
            }
        }

        /**
         * 从根节点开始，递归地对树进行遍历，并从叶子节点先开始翻转。
         * 如果当前遍历到的节点root 的左右两棵子树都已经翻转，
         * 那么我们只需要交换两棵子树的位置，即可完成以
         * root 为根节点的整棵子树的翻转
         * @param root
         * @return
         */
        public TreeNode invertTree2(TreeNode root) {
            if(root == null) return null;
            TreeNode left = invertTree(root.left);
            TreeNode right = invertTree(root.right);
            root.left = right;
            root.right = left;
            return root;
        }


        /**
         * 层序遍历反转
         * @param root
         * @return
         */
        public TreeNode invertTree3(TreeNode root) {
            if(root == null) return null;
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            while(!queue.isEmpty()) {
                TreeNode node = queue.poll();
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            return root;
        }

        /**
         * 深度优先遍历
         * @param root
         * @return
         */
        public TreeNode invertTree4(TreeNode root) {
            if (root == null)
                return root;
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.empty()) {
                TreeNode node = stack.pop();
                //先交换子节点
                TreeNode left = node.left;
                node.left = node.right;
                node.right = left;
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
            return root;
        }

        /**
         * 广度有限遍历
         * @param root
         * @return
         */
        public TreeNode invertTree5(TreeNode root) {
            if(root == null) return root;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()) {
                TreeNode node = queue.poll();
                TreeNode left = node.left;
                node.left = node.right;
                node.right = left;

                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            return root;
        }


        /**
         * 中序遍历
         * @param root
         * @return
         */
        public TreeNode invertTree6(TreeNode root) {
            if(root == null) return root;
            Stack<TreeNode> stack = new Stack<>();
            TreeNode node = root;
            while(node != null || stack.isEmpty()) {
                while(node != null) {
                    stack.push(node);
                    node = node.left;
                }
                if(!stack.isEmpty()) {
                    node = stack.pop();
                    TreeNode left = node.left;
                    node.left = node.right;
                    node.right = left;
                    node = node.left;
                }
            }
            return root;
        }
    }
}
