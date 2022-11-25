package src.minimum_Distance_Between_BST_Nodes_783;

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
           * 没过
           */
          int ans = Integer.MAX_VALUE;
          public int miniDiffInBST(TreeNode root) {
                inorder(root, root.val);
                return ans;
          }

          public void inorder(TreeNode root, int preVal) {
              if(root == null) return;
              inorder(root.left,root.val);
              int tmp = Math.abs(root.val - preVal);
              if(tmp != 0) ans = Math.min(ans, tmp);
              inorder(root.right, root.val);
          }


          /**
           * 中序遍历
           * 虑对升序数组 a 求任意两个元素之差的最小值，答案一定为相邻两个元素之差的最小值，
           * 二叉搜索树有个性质为二叉搜索树中序遍历得到的值序列是递增有序的，因此我们只要得到中序遍历后的值序列
           * 在中序遍历的过程中用 pre 变量保存前驱节点的值，这样即能边遍历边更新答案，不再需要显式创建数组来保存，
           * 需要注意的是 pre 的初始值需要设置成任意负数标记开头，下文代码中设置为 −1
           */
          int pre;
          int ans2;
          public int minDiffInBST2(TreeNode root) {
              ans2 = Integer.MAX_VALUE;
              pre = -1;
              dfs(root);
              return ans;
          }
              public void dfs(TreeNode root) {
                  if(root == null) return;
                  dfs(root.left);
                  if(pre == -1) pre = root.val;
                  else {
                      ans2 = Math.min(ans, root.val - pre);
                      pre = root.val;
                  }
                  dfs(root.right);
              }


          public int miniDiffInBST3(TreeNode root) {
              List<Integer> list = new ArrayList<>();
              //BFS
              Deque<TreeNode> d = new ArrayDeque<>();
              d.addLast(root);
              while(!d.isEmpty()) {
                  TreeNode poll = d.pollFirst();
                  list.add(poll.val);
                  if(poll.left != null) d.addLast(poll.left);
                  if(poll.right != null) d.addLast(poll.right);
              }

              //DFS
              //dfs2(root, list)

              Collections.sort(list);
              int n = list.size();
              int ans = Integer.MAX_VALUE;
              for(int i = 1; i < n; i++) {
                  int cur = Math.abs(list.get(i) - list.get(i - 1));
                  ans = Math.min(ans, cur);
              }
              return ans;
          }
          void dfs2(TreeNode root, List<Integer> list) {
              list.add(root.val);
              if(root.left != null) dfs2(root.left, list);
              if(root.right != null) dfs2(root.right, list);
          }


          int ans3 = Integer.MAX_VALUE;
          TreeNode prev = null;
          public int miniDiffInBST4(TreeNode root) {
                //栈模拟
              Deque<TreeNode> d = new ArrayDeque<>();
              while(root != null || !d.isEmpty()) {
                  while(root != null) {
                      d.addLast(root);
                      root = root.left;
                  }
                  root = d.pollLast();
                  if(prev != null) ans3 = Math.min(ans3, Math.abs(prev.val - root.val));
                  prev = root;
                  root = root.right;
              }
              //递归
              //dfs3(root)
              return ans;
          }

          void dfs3(TreeNode root){
              if(root == null) return;
              dfs3(root.left);
              if(prev != null) ans3 = Math.min(ans3, Math.abs(prev.val - root.val));
              prev = root;
              dfs(root.right);
          }







          //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          // TODO: 24/11/2022 Morris中序遍历
          // Morris算法中序遍历求解
          public int minDiffInBST(TreeNode root) {
              TreeNode cur = root;
              int lastNodeVal = -1;
              while (cur != null) {
                  if (cur.left == null) { // 无左树，结算，cur往右走
                      lastNodeVal = doCalculate(lastNodeVal, cur.val);
                      cur = cur.right;
                  } else { // 有左树，进一步判断左树最右节点的状态
                      TreeNode preNode = findTheMostRightNodeInLeft(cur); // 找到cur的前驱节点（左树最右节点）
                      if (preNode.right != null) { // 第二次到达cur节点，结算，恢复前驱节点right指针，cur往右走
                          lastNodeVal = doCalculate(lastNodeVal, cur.val);
                          preNode.right = null;
                          cur = cur.right;
                      } else { // 第一次到达cur节点，不结算，修改前驱节点右指针，cur往左走
                          preNode.right = cur;
                          cur = cur.left;
                      }
                  }
              }
              return minDiff;
          }

          // 找到cur的前驱节点（左树最右节点）
          private TreeNode findTheMostRightNodeInLeft(TreeNode cur) {
              TreeNode node = cur.left;
              while (node.right != null && node.right != cur) node = node.right;
              return node;
          }

          // 结算差值，更新全局答案
          private int doCalculate(int lastNodeVal, int curVal) {
              if (lastNodeVal >= 0) minDiff = Math.min(minDiff, curVal - lastNodeVal);
              return curVal;
          }

          private int minDiff = Integer.MAX_VALUE;







      }
}
