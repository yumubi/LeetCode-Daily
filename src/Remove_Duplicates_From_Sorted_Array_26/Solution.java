package src.Remove_Duplicates_From_Sorted_Array_26;

public class Solution {
    /**快慢指针
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int count = nums.length;
        if (count == 0) return 1;
        int slow = 0;
        int fast = 1;
        while (slow < fast && fast < nums.length) {
            if (nums[fast] == nums[slow]) {
                count--;
                fast++;
            }
            else {
                nums[++slow] = nums[fast++];
            }

        }
        return count;
    }
}
