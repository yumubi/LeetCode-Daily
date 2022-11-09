package src.convert_Sorted_Array_To_BST_108;

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
       }






}
