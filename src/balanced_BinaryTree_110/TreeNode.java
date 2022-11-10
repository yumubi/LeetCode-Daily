package src.balanced_BinaryTree_110;

import java.util.Deque;
import java.util.LinkedList;

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


             //my answer 测试203  [1,2,2,3,null,null,3,4,null,null,4]没过
             public boolean isBalanced(TreeNode root) {
                 if(root == null || (root.left == null && root.right == null)) return true;

                 Deque<TreeNode> queue = new LinkedList<TreeNode>();
                 queue.offer(root);
                 while(!queue.isEmpty()) {
                     int size = queue.size();
                     while(size > 0 ) {
                         TreeNode node = queue.poll();
                         if(Math.abs( depth(root.left) - depth(root.right)) > 1) return false;
                         if(node.left != null) queue.offer(node.left);
                         if(node.right != null) queue.offer(node.right);
                         size--;
                     }
                 }
                    return true;
             }
             public int depth(TreeNode root) {
                 if(root == null) return 0;
                 return Math.max( depth(root.left), depth(root.right)) + 1;
             }


             /**
              * 自顶向下递归
              * 具体做法类似于二叉树的前序遍历，即对于当前遍历到的节点
              * 首先计算左右子树的高度，如果左右子树的高度差是否不超过 1
              * ，再分别递归地遍历左右子节点，并判断左子树和右子树是否平衡。
              * 这是一个自顶向下的递归的过程。
                //时间复杂度：O(n^2)
              // 其中 n 是二叉树中的节点个数。
              //最坏情况下，二叉树是满二叉树，需要遍历二叉树中的所有节点，时间复杂度是 O(n)。
              //对于节点 p，如果它的高度是 d，则 height(p) 最多会被调用 d 次（即遍历到它的每一个祖先节点时）。
              对于平均的情况，一棵树的高度 h 满足 O(h)=O(logn)，因为 d ≤h，所以总时间复杂度为O(nlogn)。
              对于最坏的情况，二叉树形成链式结构，高度为 O(n)，此时总时间复杂度为 O(n^2)
              //空间复杂度：O(n)，其中 n 是二叉树中的节点个数。空间复杂度主要取决于递归调用的层数，递归调用的层数不会超过 n。
              * @param root
              * @return
              */

             public boolean isBalanced2(TreeNode root) {
                 if(root == null) return true;
                 else
                     return Math.abs(height(root.left) - height(root.right)) <= 1
                         && isBalanced2(root.left)
                         && isBalanced2(root.right);
             }

             public int height(TreeNode root) {
                 if(root == null) return 0;
                 else return Math.max(height(root.left), height(root.right)) + 1;
             }


             /**
              *自底向上递归的做法类似于后序遍历，
              * 对于当前遍历到的节点，先递归地判断其左右子树是否平衡，再判断以当前节点为根的子树是否平衡。
              * 如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返回 -1。
              * 如果存在一棵子树不平衡，则整个二叉树一定不平衡
            //时间复杂度：O(n)，其中 n是二叉树中的节点个数。使用自底向上的递归，
              每个节点的计算高度和判断是否平衡都只需要处理一次，
              最坏情况下需要遍历二叉树中的所有节点，因此时间复杂度是 O(n)。

              * @param root
              * @return
              */
             public boolean isBalanced3(TreeNode root){
                return height2(root) >= 0;
             }

             public int height2(TreeNode root) {
                 if(root == null) return 0;
                 int leftHeight = height2(root.left);
                 int rightHeight = height2(root.right);
                 if(leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1)
                     return -1;
                 else  return Math.max(leftHeight, rightHeight) + 1;
             }


         }








}
