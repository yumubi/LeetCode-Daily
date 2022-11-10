package src.path_Sum_112;

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
             *观察要求我们完成的函数，
             * 我们可以归纳出它的功能：询问是否存在从当前节点 root 到叶子节点的路径，满足其路径和为 sum。
             * 假定从根节点到当前节点的值之和为 val，
             * 我们可以将这个大问题转化为一个小问题：是否存在从当前节点的子节点到叶子的路径，满足其路径和为 sum - val。
             * 不难发现这满足递归的性质，若当前节点就是叶子节点，
             * 那么我们直接判断 sum 是否等于 val 即可（因为路径和已经确定，就是当前节点的值，我们只需要判断该路径和是否满足条件）。
             * 若当前节点不是叶子节点，我们只需要递归地询问它的子节点是否能满足条件即可。
             * @param root
             * @param sum
             * @return
             */
            public boolean hasPathSum3(TreeNode root, int sum) {
                if (root == null) return false;
                if (root.left == null && root.right == null) return sum == root.val;
                return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
            }


            /**
             * bfs+单队列
             * 路径值覆盖节点值
             * @param root
             * @param sum
             * @return
             */
            public boolean hasPathSum4(TreeNode root, int sum) {
                    if(root == null) return false;
                    Queue<TreeNode> queue = new LinkedList<TreeNode>();
                    queue.offer(root);

                    while(!queue.isEmpty()) {
                        TreeNode poll = queue.poll();
                        if(poll.left == null && poll.right == null) {
                            if(poll.val == sum) return true;
                        } else {
                            if(poll.left != null) {
                                poll.left.val += poll.val;
                                queue.offer(poll.left);
                            }
                            if(poll.right != null) {
                                poll.right.val += poll.val;
                                queue.offer(poll.right);
                            }
                        }
                    }
                    return false;
                }


            /**
             * dfs+单栈
             * @param root
             * @param sum
             * @return
             */
                public boolean hasPathSum5(TreeNode root, int sum) {
                if(root == null) return false;
                Deque<TreeNode> stack = new LinkedList<>();
                stack.push(root);

                while(!stack.isEmpty()) {
                    TreeNode pop = stack.pop();
                    if(pop.left == null && pop.right == null) {
                        if(pop.val == sum) return true;
                    } else {
                        // TODO: 2022/11/10 这个顺序就很妙，是dfs的精髓
                        if(pop.right != null) {
                            pop.right.val += pop.val;
                            stack.push(pop.right);
                        }
                        if(pop.left != null) {
                            pop.left.val += pop.val;
                            stack.push(pop.left);
                        }
                    }
                }
                    return false;
}


            /**
             * dfs+剪枝
             */
        private boolean hasPathSum = false;
        public boolean hasPathSum6(TreeNode root, int targetSum) {
                if(root == null) return hasPathSum;
                fun(root, targetSum, 0);
                return hasPathSum;
        }
        public void fun(TreeNode root, int targetSum, int sum) {
            if(!hasPathSum) {//剪枝，一旦hasPathSum为true,结束
                sum += root.val;//每次将根节点的值加sum
                if(root.left == null && root.right == null && sum == targetSum) {//叶子节点时，判断路径节点和是否等于目标值
                    hasPathSum = true;
                    return;
                }
                if(root.left != null) fun(root.left, targetSum, sum);
                if(root.right != null) fun(root.right, targetSum, sum);
            }
        }
    }

}
