package src.reverse_Bits_190;

import org.junit.Test;

public class Solution {
    /**
     * 我是sb
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        return Integer.reverse(n);
    }

    // TODO: 2022/11/15 看不懂，日后再欣赏 
    /**
     * 对称位构造
     * 对输入的n做诸位检查
     * 如果某一位是1，则将答案相应的对称位置修改为1
     * @param n
     * @return
     */
    public int reverseBits2(int n) {
//        int rev = 0;
//        for (int i = 0; i < 32 && n != 0; ++i) {
//            rev |= (n & 1) << (31 - i);
//            n >>>= 1;
//        }
//        return rev;


        int ans = 0;
        for(int i = 0; i < 32; i++) {
            int t = (n >> i) & i;
            if(t == 1) ans |= ( 1 << (31 - i));
        }
        return ans;
    }

    /**
     * 逐位分离构造
     * 每次都使用n的最低一位，使用n的最低一位去更新答案的最低一位，使用完将n进行右移一位，将答案左移一位
     * 相当于每次都用n的最低一位更新成ans的最低一位
     * @param n
     * @return
     */
    public int reverseBits3(int n) {
        int ans = 0;
        int cnt = 32;
        while(cnt-- > 0) {
            ans <<= 1;
            ans += (n & 1);
            n >>= 1;
        }
        return ans;
    }

    /**
     * 分组交换
     * 事实上，对于长度固定的int类型，我们可以使用分组构造的方式进行
     * 两位互换->四位互换->八位互换->十六位互换
     * @param n
     * @return
     */
    public int reverseBits4(int n) {
        n = (( n & 0xAAAAAAAA >>> 1) | (( n & 0x55555555) << 1));
        n = (( n & 0xCCCCCCCC >>> 2) | (( n & 0x33333333) << 2));
        n = (( n & 0xF0F0F0F0 >>> 4) | (( n & 0x0F0F0F0F) << 4));
        n = (( n & 0xFF00FF00 >>> 8) | (( n & 0x00FF00FF) << 8));
        n = (( n & 0xFFFF0000 >>> 16) | (( n & 0x0000FFFF) << 16));
        return n;
    }


    /**
     * 分治法
     * 若要翻转一个二进制串，可以将其均分成左右两部分，对每部分递归执行翻转操作，然后将左半部分拼在右半部分的后面，即完成了翻转。
     * 由于左右两部分的计算方式是相似的，利用位掩码和位移运算，我们可以自底向上地完成这一分治流程。
     *
     * 对于递归的最底层，我们需要交换所有奇偶位：
     * 取出所有奇数位和偶数位；
     * 将奇数位移到偶数位上，偶数位移到奇数位上。
     * 类似地，对于倒数第二层，每两位分一组，按组号取出所有奇数组和偶数组，然后将奇数组移到偶数组上，偶数组移到奇数组上。以此类推。
     * 需要注意的是，在某些语言（如 Java）中，没有无符号整数类型，因此对 nn 的右移操作应使用逻辑右移。


     */
    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111
    public int reverse5(int n) {
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }

    @Test
    public void test() {
        System.out.println(reverseBits(43261596));
    }

}
