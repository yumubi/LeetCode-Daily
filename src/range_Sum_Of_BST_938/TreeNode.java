package src.range_Sum_Of_BST_938;

import java.util.ArrayDeque;
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

       class Solution{
           int sum;
           public int rangeSumBST(TreeNode root, int low, int high) {
                dfs(root, low, high);
                return sum;
           }


           public void dfs(TreeNode root, int low, int high) {
               if(root == null) return;
               dfs(root.left, low, high);
               if(root.val >= low && root.val < high) sum += root.val;
               if(root.val == high) {
                   sum += root.val;
                   return;
               }
               dfs(root.right, low, high);
           }


           /**
            * 按深度优先搜索的顺序计算范围和。记当前子树根节点为 root，分以下四种情况讨论：
            * root 节点为空返回 0。
            * root 节点的值大于 high
            * 由于二叉搜索树右子树上所有节点的值均大于根节点的值，即均大于 high，故无需考虑右子树，返回左子树的范围和。
            * root 节点的值小于 low
            * 由于二叉搜索树左子树上所有节点的值均小于根节点的值，即均小于 low，故无需考虑左子树，返回右子树的范围和。
            *root 节点的值在 [low,high] 范围内
            * 此时应返回 root 节点的值、左子树的范围和、右子树的范围和这三者之和。

            * @param root
            * @param low
            * @param high
            * @return
            */
           public int rangeSumBST1(TreeNode root, int low, int high) {
               if(root == null) return 0;
               if(root.val > high) return rangeSumBST1(root.left, low, high);
               if(root.val < low) return rangeSumBST1(root.right, low, high);
               return root.val + rangeSumBST1(root.left, low, high) + rangeSumBST1(root.right, low, high);
           }


           /**
            * 广度优先搜索
            * 用一个队列 q存储需要计算的节点。每次取出队首节点时，若节点为空则跳过该节点，否则按方法一中给出的大小关系来决定加入队列的子节点
            * @param root
            * @param low
            * @param high
            * @return
            */
           public int rangeSumBST2(TreeNode root, int low, int high) {
               int sum = 0;
               Queue<TreeNode> q = new LinkedList<TreeNode>();
               q.offer(root);
               while(!q.isEmpty()) {
                   TreeNode node = q.poll();
                   if(node == null) continue;
                   if(node.val > high) q.offer(node.left);
                   else if(node.val < low) q.offer(node.right);
                   else {
                       sum += node.val;
                       q.offer(node.left);
                       q.offer(node.right);
                   }
               }
               return sum;
           }


           int low, high;
           int ans;
           public int rangeSumBST3(TreeNode root, int _low, int _high) {
               low = _low;
               high = _high;
               dfs1(root);
               return ans;
           }

           void dfs1(TreeNode root) {
               if(root == null) return;
               dfs1(root.left);
               if(low <= root.val && root.val <= high) ans += root.val;
               dfs1(root.right);
           }

           public int rangeSumBST4(TreeNode root, int low, int high) {
               int ans = 0;
               Deque<TreeNode> d = new ArrayDeque<>();
               while(root != null || !d.isEmpty()) {
                   while(root != null) d.addLast(root);
                   root = root.left;
               }
               root = d.pollLast();
               if(low <= root.val && root.val <= high) ans += root.val;
               root = root.right;
               return ans;
           }

           public  int rangeSumBST5(TreeNode root, int low, int high) {
               TreeNode cur = root;
               int sum = 0;
               Deque<TreeNode> stack = new LinkedList<>();
               // 二叉树的中序遍历（迭代）：
               while (cur != null || !stack.isEmpty()) {
                   while (cur != null) {
                       stack.push(cur);
                       cur = cur.left;
                   }
                   cur = stack.pop();

                   if (cur.val > high) break; // BST中序递增，可提前退出
                   if (cur.val >= low && cur.val <= high) sum += cur.val;

                   cur = cur.right;
               }

               return sum;
           }


       }
}
