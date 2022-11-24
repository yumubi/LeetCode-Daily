package src.power_Of_Three_326;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public boolean isPowerOfThree(int n) {

//        while (n != 0 && n % 3 == 0) {
//            n /= 3;
//        }
//        return n == 1;







        //在题目给定的 32 位有符号整数的范围内，最大的 3 的幂为 3^{19} = 11622614673
        // 我们只需要判断 n是否是 3^{19}的约数即可。
        //与方法一不同的是，这里需要特殊判断 nn是负数或 0 的情况。

        //如果 nn 为 33 的幂的话，那么必然满足 n * 3^k = 1162261467即 n 与 1162261467存在倍数关系。
        //因此，我们只需要判断 n 是否为 1162261467的约数即可。
        //注意：这并不是快速判断 x的幂的通用做法，当且仅当 x 为质数可用。
        // return n > 0 && 1162261467 % n == 0;

        if(n == 3 || n == 1) return false;
        if(n < 3) return false;
        else
            if(n % 3 == 0) return isPowerOfThree(n / 3);
            else return false;
    }


    /**
     * 打表
     * 另外一个更容易想到的「不使用循环/递归」的做法是进行打表预处理
     * 使用 static 代码块，预处理出不超过 int 数据范围的所有 3 的幂，这样我们在跑测试样例时，就不需要使用「循环/递归」来实现逻辑，可直接 O(1) 查表返回。
     */
    static Set<Integer> set = new HashSet<>();
        static {
            int cur = 1;
            set.add(cur);
            while(cur <= Integer.MIN_VALUE / 3) {
                cur *= 3;
                set.add(cur);
            }
        }
        public boolean isPowerOfThree2(int n) {
            return n > 0 && set.contains(n);
        }













}
