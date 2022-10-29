package src.Two_Sum_01;


import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

public class Main {
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

/**
 * 暴力枚举
 */
class answer1 {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) return new int[]{i, j};
            }
        }
        return new int[]{0,1};
    }
}


/**
 * hash表
 */

/*
 两个元素x，y必然是一前一后出现的，如果存在符合条件的解
 在遍历到x时，哈希表里没有符合的y，此时把x加入到了哈希表里
 当遍历到y时，就可以在哈希表里找到对应的x了，所以只需要一次遍历
 */
class answer2 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i]))
                return new int[]{i, map.get(target - nums[i])};
            map.put(nums[i], i);
        }
        return null;
    }
}