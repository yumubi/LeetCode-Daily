package src.power_Of_Two_231;

import org.junit.Test;

public class Solution {

    /**
     * 我的递归
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
      if( n == 1) return true;
      if(n <= 0 || n % 2 == 1) return false;
      else if(n == 3) return false;
      else
          return n % 2 == 0 && isPowerOfTwo(n / 2);
    }


    /**
     * 位运算
     * @param n
     * @return
     */
    public boolean isPowerOfTwo2(int n) {
        return n > 0 && ( n & (n - 1)) == 0;
    }

    public boolean isPowerOfTwo3(int n) {
        return n > 0 && (n & -n) == 0;
    }

    static final int BIG = 1 << 30;


    /**
     * 在题目给定的 32 位有符号整数的范围内，最大的 2的幂为 2^30 = 10737418242
     * 我们只需要判断n是否是 2^30的约数即可。
     * @param n
     * @return
     */
    public boolean isPowerOfTwo4(int n) {
        return n > 0 && BIG % n == 0;
    }


    /**
     * 自底向上递归
     * @param n
     * @return
     */
    public boolean isPowerOfTwo5(int n) {
        return doPowerOfTwo(0, n);
    }

    public boolean doPowerOfTwo(int x, int n) {
        if(Math.pow(2, x) > n) return false;
        if(Math.pow(2, x) == n) return true;
        boolean res = doPowerOfTwo(++x, n);
        return res;
    }

    /**
     * 出32个bit位里面有几个1
     * @param n
     * @return
     */
    public boolean isPowerOfTwo6(int n) {
        return Integer.bitCount(n) == 1;
    }













    @Test
    public void test() {
        System.out.println(isPowerOfTwo(-16));
    }












}
