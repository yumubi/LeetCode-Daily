package src.intersection_Of_Two_ArraysPlus_350;

import org.junit.Test;

import java.util.*;

public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> res = new ArrayList<>();
        int ptr1 = 0, ptr2 = 0;

        while(ptr1 < nums1.length && ptr2 < nums2.length) {
            int num1 = nums1[ptr1], num2 = nums2[ptr2];
            if(num1 == num2) {
                res.add(num1);
                ptr1++;
                ptr2++;
            }
            else if(num1 < num2) ptr1++;
            else ptr2++;
        }
        return res.stream().mapToInt(i -> i).toArray();
    }


    /**
     * 时间复杂度：O(mlogm+nlogn)，其中 m和 n分别是两个数组的长度。
     * 对两个数组进行排序的时间复杂度是 O(mlogm+nlogn)，遍历两个数组的时间复杂度是 O(m+n)，因此总时间复杂度是 O(mlogm+nlogn)。

     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length;
        int length2 = nums2.length;
        int[] intersection = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while(index1 < length1 && index2 < length2) {
            if(nums1[index1] < nums2[index2]) {
                index1++;
            } else if(nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }


    /**
     * 由于同一个数字在两个数组中都可能出现多次，因此需要用哈希表存储每个数字出现的次数。对于一个数字，其在交集中出现的次数等于该数字在两个数组中出现次数的最小值。
     * 首先遍历第一个数组，并在哈希表中记录第一个数组中的每个数字以及对应出现的次数，
     * 然后遍历第二个数组，对于第二个数组中的每个数字，如果在哈希表中存在这个数字，则将该数字添加到答案，并减少哈希表中该数字出现的次数。
     * 为了降低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数，然后遍历较长的数组得到交集
     *
     * 时间复杂度：O(m+n)，其中 m 和 n 分别是两个数组的长度。需要遍历两个数组并对哈希表进行操作，
     * 哈希表操作的时间复杂度是 O(1)，因此总时间复杂度与两个数组的长度和呈线性关系。
     * 空间复杂度：O(min(m,n))，其中 m 和 n 分别是两个数组的长度。对较短的数组进行哈希表的操作，
     * 哈希表的大小不会超过较短的数组的长度。为返回值创建一个数组 intersection，其长度为较短的数组的长度。
     *

     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect3(int[] nums1, int[] nums2) {
            if(nums1.length > nums2.length) {
                return intersect3(nums2,nums1);
            }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for(int num : nums1) {
                int count = map.getOrDefault(num, 0) + 1;
                map.put(num, count);
            }

            int[] intersection= new int[nums1.length];
            int index = 0;
            for(int num : nums2) {
                int count = map.getOrDefault(num, 0);
                if(count > 0) {
                    intersection[index++] = num;
                    count--;
                    if(count > 0) map.put(num, count);
                    else map.remove(num);
                }
            }

            return Arrays.copyOfRange(intersection, 0, index);

    }

}
