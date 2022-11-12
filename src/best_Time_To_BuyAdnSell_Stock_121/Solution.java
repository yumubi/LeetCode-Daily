package src.best_Time_To_BuyAdnSell_Stock_121;

import org.junit.Test;

public class Solution {

    /**
     * 完全没思路
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int enter = prices[0];
        int exit = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if ((prices[i] - enter) > (exit - enter)) {
                exit = prices[i];
                for (int j = i; j < prices.length; j++) {
                    if (prices[j] > prices[i]) exit = prices[j];
                }
                for (int k = i; k >= 0; k--) {
                    if (prices[k] < enter) enter = prices[k];
                }
            }
        }
        return exit - enter;
    }


    /**
     * 暴力解法
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int maxprofit = 0;
        for(int i = 0; i < prices.length - 1; i++) {
            for(int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if(profit > maxprofit) maxprofit = profit;
            }
        }
        return maxprofit;
    }


    /**
     * 我们只要用一个变量记录一个历史最低价格 minprice，
     * 我们就可以假设自己的股票是在那天买的。那么我们在第 i 天卖出股票能得到的利润就是 prices[i] - minprice。
     * 因此，我们只需要遍历价格数组一遍，记录历史最低点，然后在每一天考虑这么一个问题：如果我是在历史最低点买进的，
     * 那么我今天卖出能赚多少钱？当考虑完所有天数之时，我们就得到了最好的答案。
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if(prices[i] < minprice) {
                minprice = prices[i];
            } else if(prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }


    public int maxProfit4(int[] prices) {
        int length = prices.length;
        if(length == 0) return 0;
        int have = -prices[0];//此时持有的股票就一定是买入股票了
        int no = 0;//不持有股票现金就是0
        for (int i = 1; i < length; i++) {
            have = Math.max(have, -prices[i]);
            no = Math.max(no, prices[i] + have);
        }
        return no;//不持有股票状态所得的金钱一定比持有股票得到的多
    }


    // TODO: 2022/11/12 自右往左动态规划
    public int maxProfit5(int[] prices) {
        int len = prices.length;
        int[] dp = new int[len];//每天买入股票对应能获取的最大利润
        int max = 0;//最大利润
        dp[len-1] = 0;//最后一天买入的话利润为0
        for (int i = len - 2; i >= 0; i--) {
            //因为利润最大的话，当然是选择价格最高的时候卖出
            //所以dp[i+1]=（第i+1天之后最高价格）- prices[i+1];
            //如果dp[i+1]=0,那么第i天之后最高价格只可能是prices[i+1]
            //如果dp[i+1]>0就是dp[i+1]+prices[i+1];
            //所以第i天买入能获得的最大利润就是profit
            //如果第i天价格比之后都高的话，那么profit<0
            int profit = dp[i+1] + prices[i+1] - prices[i];
            dp[i] = profit > 0 ? profit : 0;
            max = profit > max ? profit : max;
        }
        return max;
    }




    @Test
    public void test() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }
}