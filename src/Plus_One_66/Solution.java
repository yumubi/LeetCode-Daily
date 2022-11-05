package src.Plus_One_66;

import org.junit.Test;

import java.util.Arrays;


public class Solution {

    /**
     * ac了
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        if(digits == null || digits.length == 0) return new int[]{0};
        int endPtr = digits.length - 1;
        while(endPtr >= 0 && ++digits[endPtr]  == 10) {
            digits[endPtr] = 0;
            endPtr--;
        }
        if(endPtr == -1) {
            int[] arr = new int[digits.length+1];
            Arrays.fill(arr, 1, arr.length, 0);
            arr[0] = 1;
            return arr;
        }
        return digits;
    }

    @Test
    public void test() {


    }
}