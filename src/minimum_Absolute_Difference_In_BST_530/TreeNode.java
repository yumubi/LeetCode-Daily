package src.minimum_Absolute_Difference_In_BST_530;


import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

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


          /**
           * 中序遍历
           * 考虑对升序数组 aa 求任意两个元素之差的绝对值的最小值，答案一定为相邻两个元素之差的最小值
           * 朴素的方法是经过一次中序遍历将值保存在一个数组中再进行遍历求解，
           * 我们也可以在中序遍历的过程中用 pre 变量保存前驱节点的值，
           * 这样即能边遍历边更新答案，不再需要显式创建数组来保存，
           * 需要注意的是 pre 的初始值需要设置成任意负数标记开头，下文代码中设置为 -1。
           *
           * 时间复杂度O(n)
           * 空间复杂度O(n)
           */
          int pre;
          int ans;
          public int getMinimumDifference2(TreeNode root) {
                ans = Integer.MAX_VALUE;
                pre = -1;
                dfs(root);
                return ans;
          }
          public void dfs(TreeNode root) {
              if(root == null) return;
              dfs(root.left);
              if(pre == -1) pre = root.val;
              else {
                  ans = Math.min(ans, root.val - pre);
                  pre = root.val;
              }
              dfs(root.right);
          }


        public int getMinimumDifference4(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            int minRes = Integer.MAX_VALUE;
            TreeNode pre = null;
            while(stack.size() > 0 || root != null) {
                if(root != null) {
                    stack.add(root);
                    root = root.left;
                }
                else {
                    TreeNode cur = stack.pop();
                    if(pre != null) minRes = Math.min(cur.val - pre.val, minRes);
                    pre = cur;
                    root = cur.right;
                }
            }
            return minRes;
        }


          /**
           * 非递归解法
           * @param root
           * @return
           */
          public int getMinimumDifference04(TreeNode root) {
              int min = Integer.MAX_VALUE;
              Stack<TreeNode> stack = new Stack<>();
              TreeNode cur = root, prev = null;
              while (cur != null || !stack.empty()) {
                  if (cur != null) {
                      stack.push(cur);
                      cur = cur.left;
                  } else {
                      cur = stack.pop();

                      //记录差值的最小值
                      if (prev != null)
                          min = Math.min(min, cur.val - prev.val);
                      prev = cur;

                      cur = cur.right;
                  }
              }
              return min;
          }








          // TODO: 2022/11/16 Morris中序遍历



//          public List<Integer> inorderTraversal(TreeNode root) {
//              List<Integer> res = new ArrayList<>();
//              //首先把根节点赋值给cur
//              TreeNode cur = root;
//              //如果cur不为空就继续遍历
//              while (cur != null) {
//                  if (cur.left == null) {
//                      //如果当前节点cur的左子节点为空，就访问当前节点cur，
//                      //接着让当前节点cur指向他的右子节点
//                      res.add(cur.val);
//                      cur = cur.right;
//                  } else {
//                      TreeNode pre = cur.left;
//                      //查找pre节点，注意这里有个判断就是pre的右子节点不能等于cur
//                      while (pre.right != null && pre.right != cur)
//                          pre = pre.right;
//                      //如果pre节点的右指针指向空，我们就让他指向当前节点cur，
//                      //然后当前节点cur指向他的左子节点
//                      if (pre.right == null) {
//                          pre.right = cur;
//                          cur = cur.left;
//                      } else {
//                          //如果pre节点的右指针不为空，那么他肯定是指向cur的,
//                          //表示cur的左子节点都遍历完了，我们需要让pre的右
//                          //指针指向null，目的是把树给还原，然后再访问当前节点
//                          //cur，最后再让当前节点cur指向他的右子节点。
//                          pre.right = null;
//                          res.add(cur.val);
//                          cur = cur.right;
//                      }
//                  }
//              }
//              return res;
//          }
//
//          作者：sdwwld
//          链接：https://leetcode.cn/problems/minimum-absolute-difference-in-bst/solution/di-gui-he-fei-di-gui-liang-chong-jie-fa-zui-hao-de/
//          来源：力扣（LeetCode）
//          著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。








          public int getMinimumDifference5(TreeNode root) {
              int min = Integer.MAX_VALUE;
              TreeNode preNode = null;
              //首先把根节点赋值给cur
              TreeNode cur = root;
              //如果cur不为空就继续遍历
              while (cur != null) {
                  if (cur.left == null) {


                      //1，这个地方需要修改，记录差值的最小值
                      if (preNode != null)
                          min = Math.min(min, cur.val - preNode.val);
                      preNode = cur;


                      cur = cur.right;
                  } else {
                      TreeNode pre = cur.left;
                      //查找pre节点，注意这里有个判断就是pre的右子节点不能等于cur
                      while (pre.right != null && pre.right != cur)
                          pre = pre.right;
                      //如果pre节点的右指针指向空，我们就让他指向当前节点cur，
                      //然后当前节点cur指向他的左子节点
                      if (pre.right == null) {
                          pre.right = cur;
                          cur = cur.left;
                      } else {
                          pre.right = null;


                          //2，这个地方也需要修改，记录差值的最小值
                          if (preNode != null)
                              min = Math.min(min, cur.val - preNode.val);
                          preNode = cur;


                          cur = cur.right;
                      }
                  }
              }
              return min;
          }



      }


}
