package src.binary_Tree_Inorder_Traversal_94;

import java.util.*;

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
         *我的解法
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



        //定义 inorder(root) 表示当前遍历到 root 节点的答案，那么按照定义，我们只要递归调用 inorder(root.left)
        // 来遍历 root 节点的左子树，然后将 root 节点的值加入答案，再递归调用inorder(root.right) 来遍历 root 节点的右子树即可，
        // 递归终止的条件为碰到空节点

        //时间复杂度：O(n)，其中 nn 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
        //
        //空间复杂度：O(n)。空间复杂度取决于递归的栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 的级别。

        public List<Integer> inorderTraversal3(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            inorder(root, res);
            return res;
        }

        public void inorder(TreeNode root, List<Integer> res) {
            if (root == null) {
                return;
            }
            inorder(root.left, res);
            res.add(root.val);
            inorder(root.right, res);
        }




        //时间复杂度：O(n)，其中 n 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
        //
        //空间复杂度：O(n)。空间复杂度取决于栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 的级别。

        /**
         * 迭代法
         * @param root
         * @return
         */
        public List<Integer> inorderTraversal4(TreeNode root) {
//            List<Integer> res = new ArrayList<>();
//            Deque<TreeNode> stk = new LinkedList<>();
//            while( root != null || stk.isEmpty()) {
//                while(root != null) {
//                    stk.push(root);
//                    root = root.left;
//                }
//                root = stk.pop();
//                res.add(root.val);
//                root = root.right;
//            }
//            return res;


            // 栈 先进后出
            // 前序遍历，出栈顺序：根左右; 入栈顺序：右左根
            // 中序遍历，出栈顺序：左根右; 入栈顺序：右根左
            // 后序遍历，出栈顺序：左右根; 入栈顺序：根右左
            List<Integer> ans = new ArrayList<Integer>();
            Stack<TreeNode> stack = new Stack<>();
            // root为空且stack为空，遍历结束
            while (root != null || !stack.isEmpty()) {
                // 先根后左入栈
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                // 此时root==null，说明上一步的root没有左子树
                // 1. 执行左出栈。因为此时root==null，导致root.right一定为null
                // 2. 执行下一次外层while代码块，根出栈。此时root.right可能存在
                // 3a. 若root.right存在，右入栈，再出栈
                // 3b. 若root.right不存在，重复步骤2
                root = stack.pop();
                ans.add(root.val);
                root = root.right;
            }
            return ans;
        }


        // TODO: 2022/11/7 Morris 中序遍历

    }



}
