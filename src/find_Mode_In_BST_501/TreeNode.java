package src.find_Mode_In_BST_501;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
          public int[] findMode(TreeNode root) {

                List<Integer> res = new ArrayList<>();
                preorder(root, res);
                res.sort((o1, o2) -> o1 - o2);


                return new int[]{32};

          }

          public void preorder(TreeNode root, List<Integer> res) {
              if(root == null) return;
              res.add(root.val);
              preorder(root.left, res);
              preorder(root.right, res);
          }
    }
}
