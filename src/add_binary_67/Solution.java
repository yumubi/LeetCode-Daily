package src.add_binary_67;

import org.junit.Test;

import java.util.Arrays;

public class Solution {
    /**
     * ac了，有够烂的
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        char[] L = a.length() > b.length() ? L = a.toCharArray() : b.toCharArray();
        char[] S = a.length() > b.length() ? S = b.toCharArray() : a.toCharArray();
        int len = L.length;
        int[] arr = new int[len+1];

        int i = L.length - 1;
        int j = S.length - 1;
        for(int op = 0; op < L.length; op++) {
            if(j < 0) arr[len--] = L[i--] - '0';
            else arr[len--] = (L[i--] - '0') + (S[j--] - '0');
        }
        for(int k = L.length; k >= 1; k--) {
            if(arr[k] > 1) {
                arr[k] %= 2;
                arr[k-1] += 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        if(arr[0] != 0) sb.append(arr[0]);
        for(int s = 1; s < arr.length; s++) {
            sb.append(arr[s]);
        }
        return sb.toString();
}


    @Test
    public void test() {
        String s1 = "1111";
        String s2 = "1111";
        System.out.println(addBinary(s1, s2));
    }
}



