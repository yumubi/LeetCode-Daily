package src.Contains_Duplicate_217;

import java.util.Arrays;
import java.util.HashSet;

public class Solution {
    /**
     * laji
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums[i+1]) return true;
        }
        return false;
    }

    /**
     * 官方
     * 时间复杂度：O(NlogN)，其中 N为数组的长度。需要对数组进行排序。
     * 空间复杂度：O(logN)，
     * @param nums
     * @return
     */
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
        // return Arrays.stream(nums).distinct().count() < nums.length;
    }


    /**
     * 哈希表
     * 时间复杂度：O(N)，其中 N为数组的长度。
     *
     * 空间复杂度：O(N)，其中 N为数组的长度
     * @param nums
     * @return
     */
    public boolean containDuplicate3(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) {
            if (!set.add(x)) {
                return true;
            }
        }
        return false;
    }


}
