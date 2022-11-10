package src.path_Sum_112;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
             * 我的解法，[5,4,8,11,null,13,4,7,2,null,null,null,1] 22 过不了
             * @param root
             * @param targetSum
             * @return
             */
            public boolean hasPathSum(TreeNode root, int targetSum) {
                if(root == null) return false;
                List<Integer> list = new ArrayList<>();
                traverse(root, list);
                for(Integer value : list) {
                    if(value == targetSum) return true;
                }
                return false;
            }

            //?我在干什么？
            public void traverse(TreeNode root, List res) {
                if(root == null) return;
                traverse(root.left, res);
                res.add(root.val);
                traverse(root.right, res);
            }


            /**
             * 使用广度优先搜索的方式，记录从根节点到当前节点的路径和，以防止重复计算。
             * 这样我们使用两个队列，分别存储将要遍历的节点，以及根节点到这些节点的路径和即可。
             * 时间复杂度：O(N)，其中 NN 是树的节点数。对每个节点访问一次。
             * 空间复杂度：O(N)，其中 NN 是树的节点数。空间复杂度主要取决于队列的开销，队列中的元素个数不会超过树的节点数。
             * @param root
             * @param sum
             * @return
             */
            public boolean hasPathSum2(TreeNode root, int sum) {
                if(root == null) return false;
                Queue<TreeNode> queNode = new LinkedList<TreeNode>();
                Queue<Integer> queVal = new LinkedList<Integer>();
                queNode.offer(root);
                queVal.offer(root.val);
                while(!queNode.isEmpty()) {
                    TreeNode now = queNode.poll();
                    int temp = queVal.poll();
                    if(now.left == null && now.right == null) {
                        if(temp == sum) return true;
                        continue;
                    }
                    if(now.left != null) {
                        queNode.offer(now.left);
                        queVal.offer(now.left.val + temp);
                    }
                    if(now.right != null) {
                        queNode.offer(now.right);
                        queVal.offer(now.right.val + temp);
                    }
                }
                return false;
            }


            /**
             * 递归
             *
             * @param root
             * @param sum
             * @return
             */
            public boolean hasPathSum3(TreeNode root, int sum) {
                if(root == null) return false;
                if(root.left == null && root.right == null) return sum == root.val;
                return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
            }

        }

}
