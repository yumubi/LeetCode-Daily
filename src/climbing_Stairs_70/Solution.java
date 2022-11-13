package src.climbing_Stairs_70;

public class Solution {
    public int climbStairs(int n) {
        if(n == 1 || n == 0) return 1;
        int prev = 1;
        int prevTwo = 1;
        int cur = 2;
        for(int i = 3; i <= n; i++) {
            prevTwo = prev;
            prev = cur;
            cur = prev + prevTwo;
        }
        return cur;
    }


    // TODO: 2022/11/13 矩阵快速幂

    /**
     * dp
     * @param n
     * @return
     */
    public int climbStairs3(int n) {
        int[] dp = new int[n + 2]; //该数组每个元素的指针对应的是台阶数,元素的值存放的是台阶数对应的方法数
        //dp[0] = 0; //不管怎样,数组下标指针肯定是从0开始的,所以要考虑0.有0个台阶,不需要爬,所以没有方法数(但从斐波那契角度,dp[0]=1)
        dp[1] = 1; //1阶台阶,只有一种方式(1)

        //2阶台阶,有两种方式(1+1, 2), 因为题目设定n是正整数,所以n最小是1,此时如果定义dp的长度是int[n + 1],则length=2
        //而dp[2]实际对应的是第三个元素,超出length了,所以定义new int[n + 2]更合理
        dp[2] = 2;

        //从第三个台阶开始遍历,第三个台阶,是第二个台阶的方法和第一个台阶的方法之和
        //第四个台阶,是第三个台阶和第二个台阶方法之和,依此论推....
        for (int i = 3; i <= n; i++) { //要遍历到第n个台阶,所以指针其实是从0到n,所以dp数组数量比n多1
            dp[i] = dp[i - 1] + dp[i - 2]; //最后到第n个台阶,得到结果后正好遍历完
        }

        return dp[n];
    }
}
