package src.containsDuplicatesPlus_219;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    /**
     * 我太垃圾了
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicates(int[] nums, int k) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if( map.containsKey(nums[i]) && (i - map.get(nums[i]))<=k ) return true;
                //if( (!map.containsKey(nums[i])) || (i - map.get(nums[i]) > k))
                else  map.put(nums[i], i);
            }
            return false;
    }


    /**
     * 哈希表法
     * 时间复杂度：O(n)其中 n是数组nums的长度。需要遍历数组一次，对于每个元素，哈希表的操作时间都是 O(1)
     * 空间复杂度：O(n)
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (map.containsKey(num) && i - map.get(num) <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
    }


    /**
     * 考虑数组 nums 中的每个长度不超过k+1 的滑动窗口，同一个滑动窗口中的任意两个下标差的绝对值不超过 k。
     * 如果存在一个滑动窗口，其中有重复元素，则存在两个不同的下标 i 和 j 满足 nums[i]=nums[j] 且 ∣i−j∣≤k。
     * 如果所有滑动窗口中都没有重复元素，则不存在符合要求的下标。因此，只要遍历每个滑动窗口，判断滑动窗口中是否有重复元素即可。
     * 如果一个滑动窗口的结束下标是 i，则该滑动窗口的开始下标是 max(0,i−k)。
     * 可以使用哈希集合存储滑动窗口中的元素。从左到右遍历数组 nums，当遍历到下标 i 时，具体操作如下：
     * 1. 如果 i > k，则下标 i−k−1 处的元素被移出滑动窗口，因此将 nums[i−k−1] 从哈希集合中删除；
     * 2. 判断 nums[i] 是否在哈希集合中，如果在哈希集合中则在同一个滑动窗口中有重复元素，返回 true，
     * 如果不在哈希集合中则将其加入哈希集合。
     * 当遍历结束时，如果所有滑动窗口中都没有重复元素，返回 false。
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if(i > k) set.remove(nums[i - k - 1]);
            if(!set.add(nums[i])) return true;
        }
        return false;
    }

}
