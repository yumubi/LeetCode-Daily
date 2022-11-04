package src.Search_Insert_Position_35;

import org.junit.Test;


public class Solution {
    /**
     *二分法ac
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0 || target < nums[0]) return 0;
        int lo = 0;
        int hi = nums.length - 1;
        if(target > nums[hi]) return nums.length;
        int mid;
        while(lo <= hi) {
            mid = lo + (hi - lo)/2;
           if(nums[mid] == target) return mid;
           else if(nums[mid] < target) lo = mid + 1;
           else hi = mid - 1;
        }
        return lo;
    }

    @Test
    public void test(){
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 2;
        System.out.println(searchInsert(nums,target));
    }
}
