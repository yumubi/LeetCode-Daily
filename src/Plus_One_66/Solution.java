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

    /**
     * 找出最长的后缀9
     * 逆序遍历，找出第一个不为9的元素加一，并且将其后面的元素置为0
     * @param digits
     * @return
     */
    public int[] plusOne1(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if(digits[i] != 9) {
                ++digits[i];
                for(int j = i + 1; j < n; j++) digits[j] = 0;
                return digits;
            }
        }

        // TODO: 2022/11/5 不太清楚为什么前面不加判断直接return了,后面在new有什么用，不是直接返回了吗
            int[] ans = new int[n+1];
            ans[0] = 1;
            return ans;
    }


    @Test
    public void test() {


    }
}