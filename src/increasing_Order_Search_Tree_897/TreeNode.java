package src.increasing_Order_Search_Tree_897;



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
          public TreeNode increasingBST(TreeNode root) {
                List<Integer> res = new ArrayList<>();
                dfs(root, res);
                TreeNode dummyRoot = new TreeNode();
                TreeNode idx = new TreeNode();
                idx = dummyRoot;
              for(Integer val : res) {
                    TreeNode node = new TreeNode(val);
                    idx.right = node;
                    idx = idx.right;
              }
              return dummyRoot.right;

          }

          public void dfs(TreeNode root, List<Integer> res) {
              if(root == null) return;
              dfs(root.left, res);
              res.add(root.val);
              dfs(root.right, res);
          }


          /**
           * 递归+集合
           *
           * @param root
           * @return
           */
          public TreeNode increasingBST2(TreeNode root) {
              List<Integer> res = new ArrayList<Integer>();
              inorder(root, res);

//              dfs(root);
//              TreeNode dummy = new TreeNode(-1);
//              TreeNode cur = dummy;
//              for (TreeNode node : list) {
//                  cur.right = node;
//                  node.left = null;
//                  cur = node;
//              }
//              return dummy.right;



              TreeNode dummyNode = new TreeNode(-1);
              TreeNode currNode = dummyNode;
              for(int value : res) {
                  currNode.right = new TreeNode(value);
                  currNode = currNode.right;
              }
              return dummyNode.right;
          }


          public void inorder(TreeNode node, List<Integer> res) {
              if(node == null) return;
              inorder(node.left, res);
              res.add(node.val);
              inorder(node.right, res);
          }


          /**
           * 我们遍历到一个节点时，把它的左孩子设为空，并将其本身作为上一个遍历到的节点的右孩子。
           * 这里需要有一些想象能力。递归遍历的过程中，由于递归函数的调用栈保存了节点的引用，因此上述操作可以实现。
           */
          private TreeNode resNode;

          public TreeNode increasingBST3(TreeNode root) {
              TreeNode dummyNode = new TreeNode(-1);
              resNode = dummyNode;
              inorder(root);
              return dummyNode.right;

          }

          public void inorder(TreeNode node) {
              if(node == null) return;
              inorder(node.left);
              //在中序遍历的过程中修改节点指向
              resNode.right = node;
              node.left = null;
              resNode = node;
              inorder(node.right);
          }

          // TODO: 24/11/2022
          TreeNode head;
          public TreeNode increasingBST03(TreeNode root) {
              if(root == null) return null;
              root.right = increasingBST03(root.right);
              if(root.left != null) {
                  TreeNode node = root.left;
                  root.left = null;
                  head = node;
                  while (node.right != null) node = node.right;
                  node.right = node;
                  return increasingBST03(head);
              }
              else return root;
          }


          // TODO: 24/11/2022  逆中序遍历
          //上一个节点
//          private TreeNode resNode;
//          public void reorder(TreeNode node) {
//              //逆中序遍历
//              //逆中序遍历最后输出的即为根节点
//              if (node == null) {
//                  return;
//              }
//              reorder(node.right);
//
//              //在中序遍历的过程中修改节点指向
//              node.right = resNode;
//              //使得该节点成为“上一个父节点”
//              resNode = node;
//
//              reorder(node.left);
//
//              node.left = null;
//          }


//          List<TreeNode> list = new ArrayList<TreeNode>();
//          public TreeNode increasingBST4(TreeNode root) {
//              Deque<TreeNode> d = new ArrayDeque<>();
//              while(root != null || !d.isEmpty()) {
//                  while(root != null) {
//                      d.add(root);
//                      root = root.left;
//                  }
//                  root = d.pollLast();
//                  list.add(root);
//                  root = root.right;
//              }
//              TreeNode dummy = new TreeNode(-1);
//              TreeNode cur = dummy;
//              for(TreeNode node : list) {
//                  cur.right = node;
//                  node.left = null;
//                  cur = node;
//              }
//              return dummy.right;
//          }


          // TODO: 24/11/2022 Morris遍历
          // Morris算法解，Morris中序遍历，时间：O(N)，额外空间：O(1)
          public static TreeNode increasingBST01(TreeNode root) {
              TreeNode cur = root, pre = null; // 中序遍历当前节点，中序遍历的前一个节点
              TreeNode newHead = null; // 处理后的新头节点
              // Morris算法中序遍历：
              while (cur != null) {
                  if (cur.left == null) { // 无左树，处理cur，向右走
                      if (newHead == null) newHead = cur; // 中序首节点
                      else pre.right = cur; // 串联
                      pre = cur;
                      cur = cur.right;
                  } else { // 有左树，需再看左树状态
                      TreeNode preNode = findTheMostRightInLeft(cur); // cur的前驱节点
                      if (preNode.right == null) { // 当前为首次到达cur，不处理，cur往左走
                          preNode.right = cur;
                          cur = cur.left;
                      } else { // 当前为二次到达cur，处理cur，向右走
                          pre.right = cur; // 串联
                          pre = cur;
                          cur.left = null; // 清空左指针
                          cur = cur.right;
                      }
                  }
              }
              return newHead;
          }

          // 找到cur节点左子树上的最右节点（前驱节点）返回
          private static TreeNode findTheMostRightInLeft(TreeNode cur) {
              TreeNode node = cur.left;
              while (node.right != null && node.right != cur) {
                  node = node.right;
              }
              return node;
          }


          // TODO: 24/11/2022 二叉树迭代版本

//          private TreeNode resNode;
//
//          public TreeNode increasingBST(TreeNode root) {
//              TreeNode dummyNode = new TreeNode(-1);
//              resNode = dummyNode;
//              inorder(root);
//              return dummyNode.right;
//          }
//
//          public void inorder(TreeNode root) {
//              Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
//              TreeNode node = root;
//              while (node != null || !treeNodeStack.isEmpty()) {
//                  while (node != null) {
//                      treeNodeStack.push(node);
//                      node = node.left;
//                  }
//                  if (!treeNodeStack.isEmpty()) {
//                      node = treeNodeStack.pop();
//                      // 在中序遍历的过程中修改节点指向
//                      resNode.right = node;
//                      node.left = null;
//                      resNode = node;
//                      node = node.right;
//                  }
//              }



//          public TreeNode increasingBST(TreeNode root) {
//              if(root == null) return root;
//              Deque<TreeNode> stk = new LinkedList<>();
//
//              TreeNode head = null;
//              TreeNode pre = null;
//              while(root != null || !stk.isEmpty() ) {
//                  while(root != null) {
//                      stk.push(root);
//                      root = root.left;
//                  }
//                  TreeNode node = stk.pop();
//                  if(head == null) {
//                      head = node;
//                  }
//                  node.left = null;
//                  if(pre != null) pre.right = node;
//                  pre = node;
//                  root = node.right;
//              }
//              return head;
//          }

//
      }
}
