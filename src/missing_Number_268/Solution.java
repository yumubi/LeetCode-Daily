package src.missing_Number_268;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int missingNumber(int[] nums){

        Arrays.sort(nums);
        //if(nums[0] != 0) return 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i) return i;
        }
        return nums.length;
    }

    /**
     * 排序
     * @param nums
     * @return
     */
    public int missingNumber2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            if(nums[i] != i) return i;
        }
        return n;
    }


    public int missingNumber3(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int n = nums.length;
        for(int i = 0; i < n; i++) set.add(nums[i]);
        int missing = -1;
        for(int i = 0; i <= n; i++) {
            if(!set.contains(i)) {
                missing = i;
                break;
            }
        }
        return missing;
    }

}
