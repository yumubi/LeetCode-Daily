package src.number_Of_1Bits_191;

public class Solution {
    /**
     * laji code
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        // return Integer.bitCount(n);

//        int sum = 0;
//        char[] chars = Integer.toBinaryString(n).toCharArray();
//        for(char ch : chars)
//            if(ch == '1') sum++;
//        return sum;


        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans += ((n >> i) & 1);
        }
        return ans;
    }

    /**
     * 当检查第 i 位时，我们可以让 n 与 2^i进行与运算，
     * 当且仅当 n 的第 i 位为 1 时，运算结果不为 0。
     * @param n
     * @return
     */
    public int hammingWeight2(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }

    /**
     *
     * @param n
     * @return
     */
    public int hammingWeight3(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }

    /**
     * 「右移统计」解法
     * 对于方法一，即使 n的高位均为是 0，我们也会对此进行循环检查。
     * 因此另外一个做法是：通过 n & 1 来统计当前 nn的最低位是否为 1，同时每次直接对 n 进行右移并高位补 0。
     * 当 n = 0 代表我们已经将所有的 1 统计完成。
     * 这样的做法，可以确保只会循环到最高位的 1
     * @param n
     * @return
     */
    public int hammingWeight4(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans += ((n >> i) & 1);
        }
        return ans;
    }

    /**
     * 「lowbit」解法
     * 对于方法二，如果最高位 1 和 最低位 1 之间全是 0，我们仍然会诸次右移，直到处理到最高位的 1 为止。
     * 那么是否有办法，只对位数为 1 的二进制位进行处理呢？
     * 使用 lowbit 即可做到，lowbit 会在O(1) 复杂度内返回二进制表示中最低位 11所表示的数值。
     * 例如 (0000...111100)传入 lowbit 返回 (0000...000100)
     *  (0000...00011) 传入 lowbit 返回 (0000...00001)
     * @param n
     * @return
     */
    public int hammingWeight5(int n) {
        int ans = 0;
        for (int i = n; i != 0; i -= lowbit(i)) ans++;
        return ans;
    }
    int lowbit(int x) {
        return x & -x;
    }


    /**
     * 分组统计
     * @param n
     * @return
     */
    public int hammingWeight6(int n) {
        n = (n & 0x55555555) + ((n >>> 1)  & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2)  & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >>> 4)  & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >>> 8)  & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
        return n;
    }

}
