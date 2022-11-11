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




    @Test
    public void test() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }
}