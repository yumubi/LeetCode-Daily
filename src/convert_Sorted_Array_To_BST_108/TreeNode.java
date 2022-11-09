package src.convert_Sorted_Array_To_BST_108;

import com.sun.source.tree.Tree;

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


       class Solution{
           /**
            * 试图用快排解决，错误
            * @param nums
            * @return
            */
           public TreeNode sortedArrayToBST(int[] nums) {
                    int n = nums.length;
                   return partition(nums, 0, n - 1);
           }

           public TreeNode partition(int[] nums, int lo, int hi) {
                    if(lo >= hi) return new TreeNode(nums[lo]);
                    int partition  = lo + (hi - lo) / 2;
                    TreeNode root = new TreeNode(nums[partition]);
                    root.left = partition(nums, lo, partition - 1);
                    root.right = partition(nums, partition + 1, hi);
                    return root;
           }


           /**
            * 中序遍历，总是选择左边中间位置左边的数字作为根节点
            //时间复杂度：O(n)，其中n是数组的长度。每个数字只访问一次。
            //空间复杂度：O(logn)，其中n是数组的长度。空间复杂度不考虑返回值，
            因此空间复杂度主要取决于递归栈的深度，递归栈的深度是O(logn)。

            * @param nums
            * @return
            */
           public TreeNode sortedArrayToBST2(int[] nums) {
               return helper(nums, 0, nums.length -1);
           }

           public TreeNode helper(int[] nums, int left, int right) {
               if(left > right) {
                   return null;
               }

               //选择中间位置左边的数字作为根节点
               int mid = (left + right) / 2;



               // 总是选择中间位置右边的数字作为根节点
               //int mid = (left + right + 1) / 2;




               // 选择任意一个中间位置数字作为根节点
               //int mid = (left + right + rand.nextInt(2)) / 2;



               TreeNode root = new TreeNode(nums[mid]);
               root.left = helper(nums, left, mid - 1);
               root.right = helper(nums, mid + 1, right);
               return root;
           }


           // TODO: 2022/11/9
           //迭代实现
           public TreeNode sortedArrayToBST02(int[] nums) {
               int len = nums.length;
               if(len == 0) return null;

               //0 as a placeholder
               TreeNode head = new TreeNode(0);

               Deque<TreeNode> nodeStack = new LinkedList<TreeNode>(){{ push(head); }};
               Deque<Integer> leftIndexStack = new LinkedList<Integer>(){{ push(0); }};
               Deque<Integer> rightIndexStack = new LinkedList<Integer>(){{ push(len - 1); }};

               while( !nodeStack.isEmpty()) {
                   TreeNode currNode = nodeStack.pop();
                   int left = leftIndexStack.pop();
                   int right = rightIndexStack.pop();
                   int mid = left + (right - left) / 2;

                   currNode.val = nums[mid];

                   while(left <= mid - 1 ) {
                       currNode.left = new TreeNode(0);
                       nodeStack.push(currNode.left);
                       leftIndexStack.push(left);
                       rightIndexStack.push(mid - 1);
                   }

                   if(mid + 1 <= right) {
                       currNode.right = new TreeNode(0);
                       nodeStack.push(currNode);
                       leftIndexStack.push(mid + 1);
                       rightIndexStack.push(right);
                   }
               }

               return head;



















           }



       }






}
