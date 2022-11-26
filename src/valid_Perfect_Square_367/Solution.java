package src.valid_Perfect_Square_367;

public class Solution {
    /**
     * 直接超时了
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if(num == 1 || num == 4) return true;
        for(int i = 1; i < num / 2; i++) if(i * i == num) return true;
        return  false;
    }

    /**
     * 内置函数
     * @param num
     * @return
     */
    public boolean isPerfectSquare1(int num) {
        int x = (int) Math.sqrt(num);
        return x * x == num;
    }

    /**
     * 时间复杂度：O(sqrt{n})
     * 其中 n 为正整数 num 的最大值。我们最多需要遍历 sqrt{n} + 1个正整数
     * @param num
     * @return
     */
    public boolean isPerfectSquare2(int num) {
        long x = 1, square = 1;
        while(square <= num) {
            if(square == num) return true;
            ++x;
            square = x * x;
        }
        return false;
    }

    /**
     * 二分查找
     * 考虑使用二分查找来优化方法二中的搜索过程。因为 num 是正整数，所以若正整数 x 满足x×x=num，则 x 一定满足 1≤x≤num。
     * 于是我们可以将 1 和 num 作为二分查找搜索区间的初始边界。
     * 细节
     * 因为我们在移动左侧边界 left 和右侧边界 \right 时，新的搜索区间都不会包含被检查的下标 mid，所以搜索区间的边界始终是我们没有检查过的。
     * 因此，当left=right 时，我们仍需要检查 mid=(left+right)/2。
     * 时间复杂度：O(logn)，其中 n为正整数 num 的最大值。
     * 空间复杂度：O(1)
     * @param num
     * @return
     */
    public boolean isPerfectSquare3(int num) {
        int left = 0, right = num;
        while(left <= right) {
            int mid = (right - left) / 2 + left;
            long square = (long)mid * mid;
            if(square < num) left = mid + 1;
            else if(square > num) right = mid - 1;
            else return true;
        }
        return false;
    }

    public boolean isPerfectSquare03(int num) {
        long l = 0, r = num;
        while(l < r) {
            long mid = l + (r - l) >> 1;
            if(mid * mid <= num) l = mid;
            else r = mid - 1;
        }
        return r * r == num;
    }


    /**
     * 牛顿迭代法
     * @param num
     * @return
     */
    public boolean isPerfectSquare4(int num) {
        double x0 = num;
        while(true) {
            double x1 = (x0 + num / x0) / 2;
            if(x0 - x1 < 1e-6) break;
            x0 = x1;
        }
        int x = (int)x0;
        return x * x == num;
    }

    /**
     * 数学
     * num = n * 2 = 1+3+5+...+(2∗n−1)
     * 因此另外一种做法是对num 进行不断的奇数试减，如果最终能够减到 0，
     * 说明 num 可展开成如 1+3+5+...+(2*n-1)的形式，num 为完全平方数。
     * @param num
     * @return
     */
    public boolean isPerfectSquare5(int num) {
        int x = 1;
        while(num > 0) {
            num -= x;
            x += 2;
        }
        return num == 0;
    }

    public boolean isPerfectSquare6(int num) {
        int low = 1;
        int high = num;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            //int product = mid * mid;越界
            int t = num / mid;
            if(t == mid) {
                if(num % mid == 0) return true;
                low = mid + 1;
            } else if(t < mid) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }






















}
