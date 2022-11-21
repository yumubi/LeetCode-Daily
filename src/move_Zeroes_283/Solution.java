package src.move_Zeroes_283;

public class Solution {
    /**
     * 暴力解法
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int ZeroNums = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) ZeroNums++;
            else swap(nums, i,i - ZeroNums);
        }
    }
    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    /**
     * 双指针
     * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
     * 注意到以下性质：
     * 左指针左边均为非零数；
     * 右指针左边直到左指针处均为零。
     * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while(right < n) {
            if(nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }


    /**
     * 交换啥，直接覆盖
     * @param nums
     */
    public void moveZeroes3(int[] nums) {
        int len = nums.length;
        int slow = 0;
        for(int fast = 0; fast < len; fast++) {
            if(nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        for(int i = slow; i < len; i++) nums[i] = 0;
    }
}
