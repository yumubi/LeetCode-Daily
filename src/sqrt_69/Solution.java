package src.sqrt_69;

import org.junit.Test;

public class Solution {

    /**
     *刚开始i用int,到2147395600没过，改成long就过了
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if(x == 0) return 0;
        if(x == 1 || x == 2 || x == 3) return 1;
        if(x == 4 || x == 5 || x == 6 || x == 7 || x ==8) return 2;
        for(long i = 3; i <= x / 2; i++)
        {
            if(i * i > x)
                return (int)(i - 1);
        }
        return 0;
    }

    /**
     * 袖珍计算器
     * 用指数函数和对数函数换底
     * 时间复杂度：O(1)，由于内置的 exp 函数与 log 函数一般都很快，我们在这里将其复杂度视为 O(1)
     * 空间复杂度：O(1)
     * @param x
     * @return
     */
    public int mySqrt2(int x) {
        if(x == 0) return 0;
        int ans = (int)Math.exp(0.5 * Math.log(x));
        return (long) (ans + 1) * (ans + 1) <= x ? ans+1 : ans;
    }

    /**
     * todo
     * 待研究
     * 二分法
     * 由于 x 平方根的整数部分 ans 是满足 k^2 ≤x 的最大 k 值，因此我们可以对 kk 进行二分查找，
     * 时间复杂度log(n)
     * 空间复杂度O(1)
     * @param x
     * @return
     */
    public int mySqrt3(int x) {
        int l = 0, r = x, ans = -1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else
                r = mid - 1;
        }
        return ans;
    }


    /**
     * 牛顿迭代法
     * C 表示待求出平方根的那个整数，C 的平方根就是函数y = f(x) = x^2 - C的零点
     * @param x
     * @return
     */
    public int mySqrt4(int x) {
       if(x == 0) return 0;

       double C = x, x0 = x;
       while(true) {
           double xi = 0.5 * (x0 + C / x0);
           if(Math.abs(x0 - xi) < 1e-7) break;
           x0 = xi;
       }
       return (int)x0;
    }




    @Test
    public void test() {
        System.out.println(mySqrt(2147395600));
    }
}
