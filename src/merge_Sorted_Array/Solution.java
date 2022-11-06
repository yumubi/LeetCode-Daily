package src.merge_Sorted_Array;

import org.junit.Test;

import java.util.Arrays;

public class Solution {
    /**
     * 我是菜逼
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0) return;
        for(int j = m, i = 0; j < nums1.length; j++, i++)
            nums1[j] = nums2[i];
        Arrays.sort(nums1);
        return;
    }

    /**
     * 菜逼错了好几次才过
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        if(nums2.length == 0) return;
        int[] arr =  Arrays.copyOf(nums1, nums1.length);
        int idx1 = 0;
        int idx2 = 0;
        for(int i = 0; i < nums1.length; i++) {
            if(idx2 < n && arr[idx1] > nums2[idx2] || idx1 >= m) {
                nums1[i] = nums2[idx2];
                idx2++;
            }
            else {
                nums1[i] = arr[idx1];
                idx1++;
            }
        }
        return;

    }

    @Test
    public void test() {
        int[] nums1 = new int[] {2,0};
        int[] nums2 = new int[] {1};
        int m = 1, n = 1;
        merge2(nums1, m, nums2, n);


    }


}
