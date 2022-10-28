package src;

import java.util.Arrays;

public class Two_Sum_01 {
    public static void main(String[] args) {

    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] aux = Arrays.copyOf(nums,nums.length);
        Arrays.sort(nums);
        int lo = 0;
        int hi = nums.length - 1;
        while(lo < hi) {
            if(nums[lo] + nums[hi] > target) hi--;
            else if(nums[lo] + nums[hi] < target) lo++;
            else return new int[]{Arrays.binarySearch(aux,nums[lo]),Arrays.binarySearch(aux, nums[hi])};
        }
        return new int[]{0,1};
    }
}