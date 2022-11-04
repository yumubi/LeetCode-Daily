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


    // TODO: 2022/11/4 第一种二分写法
    //在左闭右闭的区间里，也就是[lo, hi]
    public int searchInsert2(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while(lo <= hi) { //当lo==hi时，区间[lo,hi]依然有效
            //分析：假如最后迭代了[lo,mid,hi]，
            //不妨设要查找lo,则会进入下一轮循环  变成[lo,hi]                       不妨设要查找hi,则会进入下一轮循环  变成[lo,hi]                    不妨设要查找lo<target<hi,则会进入下一轮循环  变成[lo,hi]
            //此时，lo=mid=hi-1,直接命中mid(=lo),while循环会在lo=hi-1时结束       此时，lo=mid=hi-1,未命中，在右区间，迭代为lo=mid+1               此时，mid=lo<target,lo=mid+1,即target<lo=mid=hi
            //                                                              迭代后，进入while进行判断，此时lo=mid=hi，查找命中则返回mid.       迭代后，进入while进行判断，此时mid=lo=hi>target,hi=lo-1,退出while循环
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] > target) hi = mid - 1;
            else if(nums[mid] < target) lo = mid + 1;
            else return mid;
        }
        // 分别处理如下四种情况
        // 目标值在数组所有元素之前  [0, -1]
        // 目标值等于数组中某一个元素  return mid;
        // 目标值插入数组中的位置 [lo,hi]，return  hi + 1
        // 目标值在数组所有元素之后的情况 [lo,hi]，这是右闭区间，所以  return hi + 1
        return hi + 1;
    }

    // TODO: 2022/11/4
    //如果说定义 target 是在一个在左闭右开的区间里，也就是[left, right) 。
    //
    //那么二分法的边界处理方式则截然不同。
    //
    //不变量是[left, right)的区间，如下代码可以看出是如何在循环中坚持不变量的。
    //
    //大家要仔细看注释，思考为什么要写while (left < right)， 为什么要写right = middle。
    public int searchInsert3(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n; // 定义target在左闭右开的区间里，[left, right)  target
        while (left < right) { // 因为left == right的时候，在[left, right)是无效的空间
            int middle = left + ((right - left) >> 1);
            if (nums[middle] > target) {
                right = middle; // target 在左区间，在[left, middle)中
            } else if (nums[middle] < target) {
                left = middle + 1; // target 在右区间，在 [middle+1, right)中
            } else { // nums[middle] == target
                return middle; // 数组中找到目标值的情况，直接返回下标
            }
        }
        // 分别处理如下四种情况
        // 目标值在数组所有元素之前 [0,0)
        // 目标值等于数组中某一个元素 return middle
        // 目标值插入数组中的位置 [left, right) ，return right 即可
        // 目标值在数组所有元素之后的情况 [left, right)，这是右开区间，return right 即可
        return right;
    }




    @Test
    public void test(){
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 2;
        System.out.println(searchInsert(nums,target));
    }
}
