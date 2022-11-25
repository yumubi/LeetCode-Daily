package src.power_OF_Four_342;

public class Solution {
    public boolean isPowerOfFour(int n) {
        if(n == 4 || n == 1) return true;
        if(n < 4) return false;
        else {
            if(n % 4 == 0) return isPowerOfFour(n / 4);
            else return false;
        }
    }


    /**
     * 如果 n是 4 的幂，那么 n一定也是 2的幂。因此我们可以首先判断 n是否是 2 的幂，在此基础上再判断 n否是 4 的幂。
     *
     *
     * 二进制表示中1的位置
     * 如果 n 是 4 的幂，那么 n的二进制表示中有且仅有一个 1，并且这个 1 出现在从低位开始的第偶数个二进制位上（这是因为这个 1 后面必须有偶数个 0）。
     * 这里我们规定最低位为第 0 位，例如 n=16时，n的二进制表示为  (10000)
     * 唯一的 11 出现在第 44 个二进制位上，因此 nn 是 44 的幂。
     *
     * 由于题目保证了 n 是一个32位的有符号整数，因此我们可以构造一个整数 mask，它的所有偶数二进制位都是0，所有奇数二进制位都是1。这样一来，
     * 我们将 n和mask 进行按位与运算，如果结果为 0，说明 n二进制表示中的 1 出现在偶数的位置，否则说明其出现在奇数的位置。
     * mask=(10101010101010101010101010101010) = (AAAAAAAA)16

     * @param n
     * @return
     */
    public boolean isPowerOfFour2(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;

//        if (num < 0 || num & (num-1)){//check(is or not) a power of 2.
//            return false;
//        }
//        return num & 0x55555555;//check 1 on odd bits
    }


    /**
     * 取模性质
     * 如果 n 是 4的幂，那么它可以表示成 4^x形式，我们可以发现它除以 3 的余数一定为 1即：
     * 4^x≡(3+1)^x≡1^x≡1(mod3)
     *如果 n 是 2的幂却不是 4 的幂，那么它可以表示成 4^x * 2的形式，此时它除以 3的余数一定为2。
     * 因此我们可以通过 n除以 3的余数是否为 1 来判断 n是否是 4的幂。
     * @param n
     * @return
     */
    public boolean isPowerOfFour3(int n) {
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
    }


    /**
     * sqrt + lowbit
     * 我们可以先对 nn执行 sqrt 函数，然后应用 lowbit 函数快速判断 sqrt(n)是否为 2 的幂。
     * @param n
     * @return
     */
    public boolean isPowerOfFour4(int n) {
        if(n <= 0) return false;
        int x = (int) Math.sqrt(n);
        //
        return x * x == n && (x & -x) == x;
    }

    // TODO: 24/11/2022 待研究
    int getVal(int n) {
        long l = 0, r = n;
        while(l < r) {
            long mid = l + r >> 1;
            if(mid * mid >= n) r = mid;
            else l = mid + 1;
        }
        return (int)r;
    }
}
