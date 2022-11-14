package src.reverse_Bits_190;

import org.junit.Test;

public class Solution {
    public int reverseBits(int n) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(n));
        System.out.println(sb);
        if(n >= 0)
           return Integer.valueOf(sb.reverse().toString(), 2);
        else {
           return 0;
        }
    }

    @Test
    public void test() {
        System.out.println(reverseBits(43261596));
    }

}
