package src.ugly_Number_263;

import org.junit.Test;

public class Solution {
    /**
     * 超时了
     * @param n
     * @return
     */
    public boolean isUgly(int n) {
        if(n <= 0) return false;
        int value = Math.abs(n);
        if(value == 1 || value == 2 || value == 3 || value == 5) return true;
        if(isPrime(value)) return false;
        else
            if(value % 2 == 0) return isUgly(n / 2);
            else if(value % 3 == 0) return isUgly(n / 3);
            else if(value % 5 ==0) return isUgly(n / 5);
            else return false;
    }

    public boolean isPrime(int a) {
        if (a < 2) return false;
        for (int i = 2; i * i <= a; ++i)
            if (a % i == 0) return false;
        return true;
    }


    /**
     * 根据丑数的定义，0和负整数一定不是丑数。
     * 当 n>0时，若 n是丑数，则 n可以写成 n = 2^a * 3^b * 5^c的形式，
     * 其中 a,b,c都是非负整数。特别地，当 a,b,c都是 0 时，n=1
     * 为判断 n是否满足上述形式，可以对 n 反复除以 2,3,5直到n不再包含质因数 2,3,5。
     * 若剩下的数等于 1，则说明 n 不包含其他质因数，是丑数；否则，说明 n 包含其他质因数，不是丑数。
     * @param n
     * @return
     */

    public boolean isUgly2(int n) {
        if(n <= 0) return false;
        int[] factors = {2, 3, 5};
        for(int factor : factors) {
            while(n % factor == 0) n /= factor;
        }
        return n == 1;
    }




    @Test
    public void test() {
        int n = 49;
        System.out.println(isUgly(n));
    }

}
