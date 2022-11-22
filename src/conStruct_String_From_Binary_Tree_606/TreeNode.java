package src.conStruct_String_From_Binary_Tree_606;

import com.sun.source.tree.Tree;

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


      class Solution{
          public String tree2str1(TreeNode root) {
              StringBuilder sb =new StringBuilder();
              preOrder(root, sb);
              return sb.toString().replace("()", "");
          }


          public void preOrder(TreeNode root, StringBuilder sb) {
              if(root == null) {
                  sb.append(")()(");
                  return;
              };
              sb.append(root.val + "(");
              preOrder(root.left, sb);
              preOrder(root.right, sb);
          }


          /**
           *
           * @param root
           * @return
           */
          public String tree2str(TreeNode root) {
              if(root == null) return "";
              if(root.left == null && root.right == null) return Integer.toString(root.val);
              if(root.right == null) return new StringBuffer().append(root.val).append("(")
                      .append(tree2str(root.left)).append(")").toString();
              return new StringBuffer().append(root.val).append("(")
                      .append(tree2str(root.left)).append(")(").append(tree2str(root.right))
                      .append(")").toString();
          }


          // TODO: 21/11/2022 还是不会写
          /**
           * 迭代
           * @param root
           * @return
           */
          public String tree2str2(TreeNode root) {
              StringBuffer ans = new StringBuffer();
              Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
              stack.push(root);
              Set<TreeNode> visited = new HashSet<TreeNode>();
              while(!stack.isEmpty()) {
                  TreeNode node = stack.peek();
                  if(!visited.add(node)){
                      if(node != root) ans.append(")");
                      stack.pop();
                  }
                  else {
                      if(node != root) ans.append("(");
                      ans.append(node.val);
                      if(node.left == null && node.right != null) ans.append("()");
                      if(node.right != null) stack.push(node.right);
                      if(node.left != null) stack.push(node.left);
                  }
              }
              return ans.toString();
          }


          /**
           * 递归
           * 还得是三叶大佬
           */
          //生成字符串的规则其实就是在「前序遍历」输出节点值的同时，在每颗子树的左右添加一对 ()（根节点除外），同时需要忽略掉一些不必要的 () 。
          //所谓的不必要就是指当以某个节点 xx 为根时，其只「有左子树」而「没有右子树」时，
          // 右子树的 () 可被忽略，或者「左右子树都没有」时，两者的 () 可被忽略。
          //或者反过来说，如果对于每个非空节点才添加 () 的话，那么当「有右子树」同时「没有左子树」时，左子树的 () 不能被忽略，
          // 需要额外添加，从而确保生成出来的字符串能够与「有左子树」同时「没有右子树」的情况区分开来，而不会产生二义性。

            StringBuilder sb = new StringBuilder();
            public String tree2str01(TreeNode root) {
                dfs01(root);
                return sb.substring(1, sb.length() - 1);
            }
            void dfs01(TreeNode root) {
                sb.append("(");
                sb.append(root.val);
                if(root.left != null) dfs01(root.left);
                else if(root.right != null) sb.append("()");
                if(root.right != null) dfs01(root.right);
                sb.append(")");
            }


          /**
           * 迭代
           * 由于当以某个节点 xx 为根节点时，其需要在 开始 前序遍历当前子树时添加 (，
           * 在 结束 前序遍历时添加 )，
           * 因此某个节点需要出入队两次。
           * 同时区分是首次出队（开始前序遍历）还是二次出队（结束前序遍历），
           * 这需要使用一个 set 来做记录，其余逻辑与「递归」做法类似。
           * @param root
           * @return
           */
            public String tree2str02(TreeNode root) {

                StringBuilder sb = new StringBuilder();
                Set<TreeNode> vis = new HashSet<>();
                Deque<TreeNode> d = new ArrayDeque<>();
                d.addLast(root);
                while(!d.isEmpty()) {
                    TreeNode t = d.pollLast();
                    if(vis.contains(t)) sb.append(")");
                    else {
                        d.addLast(t);
                        sb.append("(");
                        sb.append(t.val);
                        if(t.right != null) d.addLast(t.right);
                        if(t.left != null) d.addLast(t.left);
                        else if(t.right != null) sb.append("()");
                        vis.add(t);
                    }
                }
                return sb.substring(1, sb.length() - 1);
            }





















      }
}
