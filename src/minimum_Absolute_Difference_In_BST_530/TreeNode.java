package src.minimum_Absolute_Difference_In_BST_530;

import java.util.ArrayList;
import java.util.Iterator;
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
           * laji
           * @param root
           * @return
           */
          public int getMinimumDifference(TreeNode root) {
              ArrayList<Integer> res = new ArrayList<>();
              dfs(root, res);
              int miniDiff = Integer.MAX_VALUE;
              Iterator<Integer> iterator = res.iterator();
              int pre = iterator.next();
              while(iterator.hasNext()) {
                 int cur = iterator.next();
                 if(miniDiff > (cur - pre)) miniDiff = cur - pre;
                 pre = cur;
              }
              return miniDiff;

          }


          public void dfs(TreeNode root, List<Integer> res) {
              if(root == null) return;
              dfs(root.left, res);
              res.add(root.val);
              dfs(root.right, res);
          }



          int pre;
          int ans;
          public int getMinimumDifference2(TreeNode root) {

          }



      }


}
