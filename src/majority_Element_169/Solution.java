package src.majority_Element_169;

import java.util.Arrays;

public class Solution {
    /**
     * ac
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int half = (nums.length-1)/2;
        if(nums[0] != nums[half]) return nums[half];
        return nums[0];
    }
}
