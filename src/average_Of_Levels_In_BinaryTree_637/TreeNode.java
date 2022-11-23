package src.average_Of_Levels_In_BinaryTree_637;


import java.util.*;

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
         * 我感觉自己思路没错，但不行
         *
         * @param root
         * @return
         */
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> list = new ArrayList<>();
            Queue<TreeNode> rootQueue = new ArrayDeque<>();
            Queue<TreeNode> subQueue = new ArrayDeque<>();
            rootQueue.offer(root);

            while (!(rootQueue.isEmpty() && subQueue.isEmpty())) {
                double sum = 0;
                int num = rootQueue.size();
                while (!rootQueue.isEmpty()) {
                    TreeNode node = rootQueue.poll();
                    sum += node.val;
                    if (node.left != null) subQueue.offer(root.left);
                    if (node.right != null) subQueue.offer(root.right);
                }
                double average = sum / num;
                list.add(average);
                Queue<TreeNode> tmp = new ArrayDeque<>();
                tmp = rootQueue;
                rootQueue = subQueue;
                subQueue = tmp;
            }
            return list;
        }

        /**
         * 广度优先搜索
         * 也可以使用广度优先搜索计算二叉树的层平均值。从根节点开始搜索，每一轮遍历同一层的全部节点，计算该层的节点数以及该层的节点值之和，然后计算该层的平均值。
         * 如何确保每一轮遍历的是同一层的全部节点呢？我们可以借鉴层次遍历的做法，
         * 广度优先搜索使用队列存储待访问节点，只要确保在每一轮遍历时，队列中的节点是同一层的全部节点即可。具体做法如下：
         * 初始时，将根节点加入队列；
         * 每一轮遍历时，将队列中的节点全部取出，计算这些节点的数量以及它们的节点值之和，并计算这些节点的平均值，
         * 然后将这些节点的全部非空子节点加入队列，重复上述操作直到队列为空，遍历结束。
         * 由于初始时队列中只有根节点，满足队列中的节点是同一层的全部节点，
         * 每一轮遍历时都会将队列中的当前层节点全部取出，并将下一层的全部节点加入队列，
         * 因此可以确保每一轮遍历的是同一层的全部节点。
         * 具体实现方面，可以在每一轮遍历之前获得队列中的节点数量size，遍历时只遍历 size 个节点，即可满足每一轮遍历的是同一层的全部节点。
         * @param root
         * @return
         */
        public List<Double> averageOfLevels2(TreeNode root) {
            List<Double> averages = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                double sum = 0;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    sum += node.val;
                    TreeNode left = node.left, right = node.right;
                    if (left != null) queue.offer(left);
                    if (right != null) queue.offer(right);
                }
                averages.add(sum / size);
            }
            return averages;
        }

        /**
         * dfs
         * 使用深度优先搜索计算二叉树的层平均值，需要维护两个数组，counts 用于存储二叉树的每一层的节点数，sums 用于存储二叉树的每一层的节点值之和。
         * 搜索过程中需要记录当前节点所在层，如果访问到的节点在第 i 层，则将 counts[i] 的值加 1，并将该节点的值加到 sums[i]。
         * 遍历结束之后，第 i层的平均值即为 sums[i]/counts[i]。
         * @param root
         * @return
         */
        public List<Double> averageOfLevels3(TreeNode root) {
            List<Integer> counts = new ArrayList<>();
            List<Double> sums = new ArrayList<>();
            dfs(root, 0, counts, sums);
            List<Double> averages = new ArrayList<>();
            int size = sums.size();
            for (int i = 0; i < size; i++) {
                averages.add(sums.get(i) / counts.get(i));
            }
            return averages;
        }

        public void dfs(TreeNode root, int level, List<Integer> counts, List<Double> sums) {
            if(root == null) return;
            if(level < sums.size()) {
                sums.set(level, sums.get(level) + root.val);
                counts.set(level, counts.get(level) + 1);
            } else {
                sums.add(1.0 * root.val);
                counts.add(1);
            }
            dfs(root.left, level + 1, counts, sums);
            dfs(root.right, level + 1, counts, sums);
        }



        // .二叉树的层序遍历  dfs写法
        public List<List<Integer>> resList = new ArrayList<List<Integer>>();

        public List<List<Integer>> levelOrder(TreeNode root) {
            checkFun(root, 0);

            return resList;
        }
        public void checkFun(TreeNode node, Integer deep) {
            if(node == null) return;
            deep++;

            if(resList.size() < deep) {
                //当层级增加时，list的item也增加，利用list的索引值进行层级界定
                List<Integer> item = new ArrayList<>();
                resList.add(item);
            }
            resList.get(deep - 1).add(node.val);
            checkFun(node.left, deep);
            checkFun(node.right, deep);
        }




        public List<Double> averageOfLevels01(TreeNode root) {
            List<Double> ans = new ArrayList<>();
            List<Integer> levelNum = new ArrayList<>();
            List<Double> levelSum = new ArrayList<>();

            rescurse(root, 0, levelNum, levelSum);

            for (int i = 0; i < levelNum.size(); i++) {
                ans.add(levelSum.get(i) / levelNum.get(i));
            }

            return ans;
        }
        private void rescurse(TreeNode node, int level, List<Integer> levelNum, List<Double> levelSum) {
            if (node == null) {
                return;
            }

            if (levelNum.size() <= level) {
                levelNum.add(1);
                levelSum.add((double) node.val);
            } else {
                levelNum.set(level, levelNum.get(level) + 1);
                levelSum.set(level, levelSum.get(level) + node.val);
            }

            rescurse(node.left, level + 1, levelNum, levelSum);
            rescurse(node.right, level + 1, levelNum, levelSum);
        }
    }






}
