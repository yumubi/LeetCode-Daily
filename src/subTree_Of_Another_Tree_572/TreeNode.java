package src.subTree_Of_Another_Tree_572;

import java.util.ArrayList;
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
          /**
           * 过了150,还差点意思
           * @param root
           * @param subRoot
           * @return
           */
          public boolean isSubtree(TreeNode root, TreeNode subRoot) {
              List<Integer> resRoot = new ArrayList<Integer>();
              List<Integer> resSub = new ArrayList<Integer>();
              dfs01(root, resRoot);
              dfs01(subRoot, resSub);
              return resRoot.containsAll(resSub);
          }

          public void dfs01(TreeNode root, List<Integer> res) {
              if(root == null) return;
              dfs01(root.left, res);
              res.add(root.val);
              dfs01(root.right, res);
          }

          public boolean isSubList(List<Integer> rootList, List<Integer> subList) {
              if(rootList == null || subList == null) return false;
              return subList.stream().allMatch(n -> rootList.contains(n));
          }




          public boolean isSubTree(TreeNode s, TreeNode t) {
              return dfs(s, t);
          }

          public boolean dfs(TreeNode s, TreeNode t) {
              if(s == null) return false;
              return check(s, t ) || dfs(s.left, t) || dfs(s.right, t);
          }

          public boolean check(TreeNode s, TreeNode t) {
              if(s == null && t == null) return true;
              if(s == null || t == null || s.val != t.val) return false;
              return check(s.left, t.left) && check(s.right, t.right);
          }



      }

}
