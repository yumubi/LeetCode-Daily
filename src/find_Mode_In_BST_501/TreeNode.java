package src.find_Mode_In_BST_501;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
         * 为了找到一个众数数组，也太麻烦了，这代码太丑了
         * @param root
         * @return
         */
          public int[] findMode(TreeNode root) {
                List<Integer> res = new ArrayList<>();
                preorder(root, res);
              HashMap<Integer, Integer> map = new HashMap<>();
              for (Integer num: res) {
                 map.put(num, map.getOrDefault(num, 0)+1);
              }
              int max = 0;
              for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                  if(entry.getValue() > max) max = entry.getValue();
              }
              ArrayList<Integer> result = new ArrayList<>();
              for( Map.Entry<Integer, Integer> entry : map.entrySet()) {
                  if(entry.getValue() == max)  result.add(entry.getKey());
              }
                int[] a = result.stream().mapToInt(Integer :: intValue).toArray();
              return a;

          }

          public void preorder(TreeNode root, List<Integer> res) {
              if(root == null) return;
              res.add(root.val);
              preorder(root.left, res);
              preorder(root.right, res);
          }






    }

    @Test
    public void test() {

    }
}
