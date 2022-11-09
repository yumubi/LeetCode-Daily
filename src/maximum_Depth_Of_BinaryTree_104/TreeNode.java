package src.maximum_Depth_Of_BinaryTree_104;

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
            * my answer，看了labuladong
            */
           int depth = 0;
           int maxdepth = 0;
           public int maxDepth(TreeNode root) {
               return tranvserse(root);
           }

           public int tranvserse(TreeNode root) {
               if(root == null) return 0;
               depth++;
               int leftDepth = tranvserse(root.left);
               int rightDepth = tranvserse(root.right);
               maxdepth = Math.max(leftDepth, rightDepth) + 1;
                depth--;
                return maxdepth;
           }








           /**
            * 深度优先搜索
            *  如果我们知道了左子树和右子树的最大深度 l 和 r，那么该二叉树的最大深度即为max(l,r)+1
            * 而左子树和右子树的最大深度又可以以同样的方式进行计算
            * @param root
            * @return
            */
           //时间复杂度：O(n)，其中 n 为二叉树节点的个数。每个节点在递归中只被遍历一次。
           //空间复杂度：O(height)，其中height 表示二叉树的高度。递归函数需要栈空间，
           // 而栈空间取决于递归的深度，因此空间复杂度等价于二叉树的高度。
           public int maxDepth2(TreeNode root) {
               if(root == null) return 0;
               else {
                   int leftHeight = maxDepth2(root.left);
                   int rightHeight = maxDepth2(root.right);
                   return Math.max(leftHeight, rightHeight) + 1;
               }
           }

           //dfs版本2
           int maxLevel = 0;
           public int maxDepth02(TreeNode root) {
               if (root == null) {
                   return 0;
               }
               dfs(root, 1);
               return maxLevel;
           }

           public void dfs(TreeNode root, int level) {
               if (root == null)
                   return;
               if (level > maxLevel) maxLevel = level;
               dfs(root.left, level + 1);
               dfs(root.right, level + 1);
           }






           /**
            * 广度优先搜索
            * @param root
            * @return
            */
           //此时我们广度优先搜索的队列里存放的是「当前层的所有节点」。
           // 每次拓展下一层的时候，不同于广度优先搜索的每次只从队列里拿出一个节点，我们需要将队列里的所有节点都拿出来进行拓展，
           // 这样能保证每次拓展完的时候队列里存放的是当前层的所有节点，
           // 即我们是一层一层地进行拓展，最后我们用一个变量ans 来维护拓展的次数，
           // 该二叉树的最大深度即为 ans。
           //时间复杂度：O(n)，其中 n 为二叉树的节点个数。与方法一同样的分析，每个节点只会被访问一次。
           //空间复杂度：此方法空间的消耗取决于队列存储的元素数量，其在最坏情况下会达到 O(n)

           public int maxDepth3(TreeNode root) {
               if(root == null) return 0;

               Queue<TreeNode> queue = new LinkedList<TreeNode>();
               queue.offer(root);
               int ans = 0;
               while(!queue.isEmpty()) {
                   int size = queue.size();
                   while(size > 0) {
                       TreeNode node = queue.poll();
                       if(node.left != null) queue.offer(node.left);
                       if(node.right != null) queue.offer(node.right);
                       size--;
                   }
                   ans++;
               }
               return ans;
           }









       }
}






