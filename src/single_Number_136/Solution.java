package src.single_Number_136;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Solution {
    /**
     * 我的解法
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        HashMap<Integer,Boolean> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i])) map.put(nums[i],false);
            else map.put(nums[i], true);
        }

        int result = nums[0];
        for (Map.Entry<Integer,Boolean> entry :
             map.entrySet()) {
            if(entry.getValue() == false) result = entry.getKey();
        }
        return result;
    }

    /**
     * leetcode上过不了不知道为什么
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        Arrays.sort(nums);
        if(nums[0] != nums[1]) return nums[0];
        for(int i = 1; i < nums.length; i+=2)
            if(nums[i] != nums[i-1]) return nums[i];
        return nums[0];
    }

    //位运算
    public int singleNumber3(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    public int singleNumber4(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : nums) {
            Integer count = map.get(i);
            count = count == null ? 1 : ++count;
            map.put(i, count);
        }
        for (Integer i : map.keySet()) {
            Integer count = map.get(i);
            if (count == 1) {
                return i;
            }
        }
        return -1; // can't find it.
    }




    @Test
    public void test() {
        int[] nums = new int[]{4,1,2,1,2};
        System.out.println(singleNumber(nums));
    }

}
