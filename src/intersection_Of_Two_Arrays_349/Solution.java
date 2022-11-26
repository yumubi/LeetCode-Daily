package src.intersection_Of_Two_Arrays_349;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> res1 = new ArrayList<>();
        List<Integer> res2 = new ArrayList<>();
        for(int num : nums1) res1.add(num);
        for(int num : nums2) res2.add(num);
        res1.retainAll(res2);
        int[] result = new int[res1.size()];
        for(int i = 0; i < res1.size(); i++) {
            result[i] = res1.get(i);
        }
        return result;
    }

    /**
     * 两个集合
     * 计算两个数组的交集，直观的方法是遍历数组 nums1，对于其中的每个元素，遍历数组 nums2 判断该元素是否在数组 nums2 中，
     * 如果存在，则将该元素添加到返回值。假设数组 nums1 和 nums2 的长度分别是 m 和 n，则遍历数组 nums1 需要 O(m) 的时间，
     * 判断 nums1 中的每个元素是否在数组 nums2 中需要 O(n) 的时间，因此总时间复杂度是 O(mn)。
     * 如果使用哈希集合存储元素，则可以在 O(1) 的时间内判断一个元素是否在集合中，从而降低时间复杂度。
     * 首先使用两个集合分别存储两个数组中的元素，然后遍历较小的集合，
     * 判断其中的每个元素是否在另一个集合中，如果元素也在另一个集合中，则将该元素添加到返回值。该方法的时间复杂度可以降低到 O(m+n)
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection1(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for(int num : nums1) set1.add(num);
        for(int num : nums2) set2.add(num);
        return getIntersection(set1, set2);
    }
    public int[] getIntersection(Set<Integer> set1, Set<Integer> set2) {
        if(set1.size() > set2.size()) {
            return getIntersection(set2, set1);
        }
        Set<Integer> intersectionSet = new HashSet<Integer>();
        for(int num : set1) {
            if(set2.contains(num)) intersectionSet.add(num);
        }
        int[] intersection = new int[intersectionSet.size()];
        int index = 0;
        for(int num : intersectionSet) intersection[index++] = num;
        return intersection;
    }


    /**
     * 排序+双指针
     * 首先对两个数组进行排序，然后使用两个指针遍历两个数组。可以预见的是加入答案的数组的元素一定是递增的
     * 为了保证加入元素的唯一性，我们需要额外记录变量 pre 表示上一次加入答案数组的元素。
     * 初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两个数组中的数字，如果两个数字不相等，
     * 则将指向较小数字的指针右移一位，如果两个数字相等，且该数字不等于 pre ，
     * 将该数字添加到答案并更新 pre变量，同时将两个指针都右移一位。当至少有一个指针超出数组范围时，遍历结束。
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while(index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if(num1 == num2) {
                // 保证加入元素的唯一性
                if(index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if(num1 < num2) index1++;
            else index2++;
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }


    /**
     * java8写法
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection3(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for(int i : nums1) list.add(i);
        for(int i : nums2) set2.add(i);
        list.retainAll(set2);
        set1.addAll(list);
        return set1.stream().mapToInt(i -> i).toArray();
    }
    public int[] intersection4(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums2).distinct().filter(set :: contains).toArray();
    }


    // TODO: 25/11/2022 bitmap写法
    public int[] intersection5(int[] nums1, int[] nums2){
        int[] bitmap1 = new int[1001 / 32 + 1];
        int[] bitmap2 = new int[1001 / 32 + 1];
        for(int num : nums1) {
            int temp = 1;
            for(int i = 0; i < num % 32; i++) temp <<= 1;
            bitmap1[num / 32] |= temp;
        }
        for (int num : nums2) {
            int temp = 1;
            for(int i = 0; i < num % 32; i++) {
                temp <<= 1;
            }
            bitmap2[num / 32] |= temp;
        }


        List<Integer> list = new ArrayList<>();
        int[] bitmapRes = new int[1001 / 32 + 1];
        for(int i = 0; i < 1001 / 32 + 1; i++){
            bitmapRes[i] = bitmap1[i] & bitmap2[i];
            int temp = 0;
            for(int j = 0; j < 32; j++) {
                temp = 1 << j;
                if( (temp & bitmapRes[i]) != 0) {
                    list.add(i * 32 + j);
                }
            }
        }
        return list.stream().mapToInt(Integer :: intValue).toArray();
    }




    public int[] intersection6(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Set<Integer> resSet = new HashSet<>();
        for(int i = 0; i < nums2.length; i++) {
            //通过二分，判断nums2[i]是否存在于num1中
            if(binarySearch(nums1, nums2[i])) resSet.add(nums2[i]);
        }

        int[] res = new int[resSet.size()];
        int k = 0;
        for(Integer num : resSet) {
            res[k++] = num;
        }
        return res;
    }
    private boolean binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = nums[mid];
            if(midValue == target) {
                return true;
            } else if(midValue > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }















    @Test
    public void test() {
        int[] num1 = new int[] {1, 2, 3, 4, 5, 6};
        int[] num2 = new int[] {4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(intersection(num1, num2)));
    }
}
