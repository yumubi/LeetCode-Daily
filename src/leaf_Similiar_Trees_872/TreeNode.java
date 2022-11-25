package src.leaf_Similiar_Trees_872;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
          public boolean leafSimilar(TreeNode root1, TreeNode root2) {

                List<Integer> res1 = new ArrayList<>();
                List<Integer> res2 = new ArrayList<>();
                dfs(root1, res1);
                dfs(root2, res2);
                if(res1.equals(res2)) return true;
                else return false;

          }

          public void dfs(TreeNode root, List<Integer> res) {
              if(root == null) return;
              if(root.left == null && root.right == null) res.add(root.val);
              dfs(root.left, res);
              dfs(root.right, res);

          }


          /**
           * 深度优先搜索的过程中，我们总是先搜索当前节点的左子节点，再搜索当前节点的右子节点。如果我们搜索到一个叶节点，就将它的值放入序列中。
           * 在得到了两棵树分别的「叶值序列」后，我们比较它们是否相等即可。
           * @param root1
           * @param root2
           * @return
           */
          public boolean leafSimilar2(TreeNode root1, TreeNode root2) {
              List<Integer> seql = new ArrayList<Integer>();
              if(root1 != null) dfs1(root1, seql);
              List<Integer> seql2 = new ArrayList<Integer>();
              if(root2 != null) dfs(root2, seql2);
              return seql2.equals(seql2);
          }

          public void dfs1(TreeNode node, List<Integer> seq) {
              if(node.left == null && node.right == null) seq.add(node.val);
              else {
                  if(node.left != null) dfs1(node.left, seq);
                  if(node.right != null) dfs1(node.right, seq);
              }
          }


          public boolean leafSimilar3(TreeNode t1, TreeNode t2) {
              List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
              process(t1, l1);
              process(t2, l2);
              if(l1.size() == l2.size()) {
                  for(int i = 0; i < l1.size(); i++) {
                      if (!l1.get(i).equals(l2.get(i)))
                          return false;
                  }
                  return true;
              }
             return false;
          }
          void process(TreeNode root, List<Integer> list) {
              Deque<TreeNode> d = new ArrayDeque<>();
              while(root != null || !d.isEmpty()) {
                  while(root != null) {
                      d.addLast(root);
                      root = root.left;
                  }
                  root = d.pollLast();
                  if(root.left == null && root.right == null) list.add(root.val);
                  root = root.right;
              }
          }

      }

}
