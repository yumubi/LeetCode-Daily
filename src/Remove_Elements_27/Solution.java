package src.Remove_Elements_27;

import org.junit.Test;

import java.util.Arrays;

public class Solution {

    /**
     * 我的解法,不知道为什么过不i了
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0) return 0;
            Arrays.sort(nums);
            int trail = nums.length-1;
            int idx = -1;
            for(int i = 0; i< nums.length; i++) {
                if(nums[i] == val) {
                    idx = i;
                    break;
                }
            }
            if(idx == -1) return nums.length-1;

            while(idx <= trail) {
                if(nums[idx] == nums[trail]) return idx;
                swap(nums, idx++, trail--);
            }
            return trail;
    }

    public void swap(int[] arr,int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void Wrong_swap(int i, int j) {
        int tmp = i;
        i = j;
        j = tmp;
    }

    @Test
    public void test() {
        int[] nums= {4,5};
        int val = 4;
        System.out.println(removeElement(nums, val));
        System.out.println(Arrays.toString(nums));
    }
}
