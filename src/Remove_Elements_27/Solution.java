package src.Remove_Elements_27;

import org.junit.Test;

import java.util.Arrays;

public class Solution {

    /**
     * 我的解法,不知道为什么过不i了
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int trail = nums.length - 1;
        int idx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                idx = i;
                break;
            }
        }
        if (idx == -1) return nums.length - 1;

        while (idx <= trail) {
            if (nums[idx] == nums[trail]) return idx;
            swap(nums, idx++, trail--);
        }
        return trail;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void Wrong_swap(int i, int j) {
        int tmp = i;
        i = j;
        j = tmp;
    }

    /**
     * 双指针，i--就很妙
     * i--是先用i再减，就算是第一个，减完i为-1但是已经到下一层循环了，i+1之后i又变回0了
     * <p>
     * <p>
     * 我们可以将数组分成「前后」两段：
     * 前半段是有效部分，存储的是不等于 val 的元素。
     * 后半段是无效部分，存储的是等于 val 的元素。
     * 最终答案返回有效部分的结尾下标。
     * https://leetcode.cn/problems/remove-element/solution/shua-chuan-lc-shuang-bai-shuang-zhi-zhen-mzt8/
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement1(int[] nums, int val) {
        int j = nums.length - 1;
        for (int i = 0; i <= j; i++) {
            if (nums[i] == val) {
                Swap(nums, i--, j--);
            }
        }
        return j + 1;
    }

    public void Swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 解法1的人性化版本
     * @param nums
     * @param val
     * @return
     */
    public int removeElement2(int[] nums, int val) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }






    /**通用解法
     * @param nums
     * @param val
     * @return
     * //先设定变量 idx，指向待插入位置。idx 初始值为 0
     * //
     * //然后从题目的「要求/保留逻辑」出发，来决定当遍历到任意元素 x 时，应该做何种决策：
     * //
     * //如果当前元素 x 与移除元素 val 相同，那么跳过该元素。
     * //如果当前元素 x 与移除元素 val 不同，那么我们将其放到下标 idx 的位置，并让 idx 自增右移。
     * //最终得到的 idx 即是答案
     */
    public int removeElement3(int[] nums, int val) {
        int idx = 0;
        for (int x : nums) {
            if (x != val) nums[idx++] = x;
        }
        return idx;
    }





        @Test
        public void test () {
            int[] nums = {4, 5};
            int val = 4;
            System.out.println(removeElement(nums, val));
            System.out.println(Arrays.toString(nums));
        }
    }