package src.minimum_Depth_Of_BinaryTree_111;

import java.util.Deque;
import java.util.LinkedList;
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
            * 不行，不会写
            * @param root
            * @return
            */
           public int minDepth(TreeNode root) {
                return minDepth(root,1);
           }

           public int minDepth(TreeNode root, int level) {
               if(root == null) return --level;
               if(root.left == null && root.right == null )  return level;
               else if(root.left == null) return minDepth(root.right, ++level);
               else if(root.right == null) return minDepth(root.left, ++level);
               else
                   return Math.min(minDepth(root.left,++level), minDepth(root.right, ++level));
           }


           /**
            * 深度优先搜索
            * 遍历整棵树，记录最小深度。
            * 对于每一个非叶子节点，我们只需要分别计算其左右子树的最小叶子节点深度。
            * `这样就将一个大问题转化为了小问题，可以递归地解决该问题。
            * 时间复杂度：O(N)，其中 N 是树的节点数。对每个节点访问一次。
            * @param root
            * @return
            */
           public int minDepth2(TreeNode root) {
               if(root == null) return 0;
               if(root.left == null && root.right == null) return 1;
               int min_depth = Integer.MAX_VALUE;
               if(root.left != null) min_depth = Math.min(minDepth2(root.left), min_depth);
               if(root.right != null) min_depth = Math.min(minDepth2(root.right), min_depth);
               return min_depth + 1;
           }


           /**
            * 广度优先搜索
            * 当我们找到一个叶子节点时，直接返回这个叶子节点的深度。
            * 广度优先搜索的性质保证了最先搜索到的叶子节点的深度一定最小
            */
           class QueueNode {
               TreeNode node;
               int depth;

               public QueueNode(TreeNode node, int depth) {
                   this.node = node;
                   this.depth = depth;
               }
           }


           public int minDepth3(TreeNode root) {
               if(root == null) return 0;

               Queue<QueueNode> queue = new LinkedList<QueueNode>();
               queue.offer(new QueueNode(root, 1));
               while(!queue.isEmpty()) {
                   QueueNode nodeDepth = queue.poll();
                   TreeNode node = nodeDepth.node;
                   int depth = nodeDepth.depth;
                   if(node.left == null && node.right == null) return depth;
                   if(node.left != null) queue.offer(new QueueNode(node.left, depth + 1));
                   if(node.right != null) queue.offer(new QueueNode(node.right, depth + 1));
               }
               return 0;
           }


           /**
            * 不使用类
            * @param root
            * @return
            */
           public static int minDepth4(TreeNode root) {
               if (root == null) {
                   return 0;
               }
               Deque<TreeNode> deque = new LinkedList<>();
               deque.offer(root);
               int level = 1;
               while (!deque.isEmpty()) {
                   int size = deque.size();
                   for (int i = 0; i < size; i++) {
                       TreeNode cur = deque.poll();
                       if (cur.right == null && cur.left == null){
                           return level;
                       }
                       if (cur.left!=null){
                           deque.offer(cur.left);
                       }
                       if (cur.right!=null){
                           deque.offer(cur.right);
                       }
                   }
                   level++;
               }
               return level;
           }


           /**
            * 正确的level
            * @param root
            * @return
            */
           public int minDepth5(TreeNode root) {
               if (root == null) return 0;
               helper(root, 1);
               return min;
           }
           int min = Integer.MAX_VALUE;
           public void helper(TreeNode root, int deep) {
               if (root == null) return;
               if (deep >= min) return;
               if (root.left == null && root.right == null) min = Math.min(min, deep);
               helper(root.left, deep + 1);
               helper(root.right, deep + 1);
           }


           /**
            * 分治法+递归
            * 分治应该是本题的标准解法，定义 minDepth(root) 是为以 root 为根的二叉树的最小深度。
            * 既然是二叉树，我们很容易就会想到 minDepth(root) 可以分成两个子问题：
            * 左子树的最小深度 ll - minDepth(root.left)
            * 右子树的最小深度 rr - minDepth(root.right)
            * 左右子树的最小深度递归调用 minDepth() 即可，下面我们来看一下递归出口：
            * 当 root 指向空时返回0。
            * 如果左子树为空，则返回右子树的 最小深度 + 1。
            * 如果右子树为空，则返回左子树的 最小深度 + 1。
            * 如果左右子树都不为空，则取两个子树深度的最小值再加一。
            * @param root
            * @return
            */

           public int minDepth6(TreeNode root) {
               if(root == null) return 0;
               int leftChild = minDepth(root.left);
               int rightChild = minDepth(root.right);
               if (leftChild == 0)
                   return rightChild + 1;
               if (rightChild == 0)
                   return leftChild + 1;
               return Math.min(leftChild, rightChild) + 1;
           }


       }




}
