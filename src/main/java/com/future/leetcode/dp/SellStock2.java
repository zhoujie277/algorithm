package com.future.leetcode.dp;

import java.util.Arrays;

/**
 * 买卖股票的最佳时机2
 * <p>
 * 给定一个数组 prices ，其中prices[i] 是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * 输入: prices = [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * *    随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * 输入: prices = [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * *    注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例3:
 * <p>
 * 输入: prices = [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 提示：
 * 1 <= prices.length <= 3 * 10^4
 * 0 <= prices[i] <= 10^4
 * <p>
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 *
 * @author jayzhou
 */
@SuppressWarnings("unused")
class SellStock2 {

    /**
     * 动态规划分析过程：
     * 因为每天股票交易完后，手里的股票状态有两种：有股票和没有股票。(手里至多只能持有一张股票)
     * 故
     * 阶段：f[i]表示为第i天交易完之后。
     * 状态：0 表示手中没有股票，1 表示手中有股票。
     * 故阶段和状态的定义为：
     * *    f[i][0] 表示为第 i 天交易结束后，手中没有股票；
     * *    f[i][1] 表示为第 i 天交易结束后，手中有股票的状态；
     * *    f[i][j] 表示为第 i 天交易结束后，处于不同状态下，所能获取到的最大利润。(j = 0 || j = 1)
     * 决策：在第i天交易要不要购买股票，或者要不要卖出股票。分别对应如下几种情况：
     * *  1、前一天没有股票，今天也没有购买股票。此时状态为 没有股票
     * *  2、前一天持有的股票今天卖出，此时状态为 没有股票
     * *  3、前一天持有的股票今天继续持有，此时状态为 有股票。
     * *  4、前一天没有股票但今天买入购票，此时状态为 有股票。
     * *  5、今天持有的股票卖出，继续买入股票，此时状态为：有股票。
     * *  6、T + 0 操作没有利润，不考虑。
     * *  总结来说，就是有股票和没有股票的状态
     * *  第 i 天没有股票的状态，即 f[i][0] 所能获取的最大利润是：
     * *    f[i][0] = max {f[i - 1][0], f[i - 1][1] + prices[i]}
     * *  第 i 天持有股票的状态，即 f[i][1] 所能获取的最大利润是：
     * *    f[i][1] = max {f[i - 1][1], dp[i - 1][0] - prices[i]}
     * *
     * 策略及最优策略：以上便是状态转移过程及形成的策略；需从中挑选最优策略。
     * <p>
     * 初始状态：f[0][0] = 0; f[0][1] = -prices[0];
     * 返回答案：由于最后一天持有股票是没有收入的，所以答案为最后一天没有股票的利润，即： f[n-1][0]；
     * *
     */
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int[][] f = new int[length][2];
        f[0][0] = 0;
        f[0][1] = -prices[0];
        for (int i = 1; i < length; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
            f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - prices[i]);
        }
        System.out.println(Arrays.deepToString(f));
        return f[length - 1][0];
    }

    public int maxProfitOpt(int[] prices) {
        int length = prices.length;
        int selling = 0;
        int buying = -prices[0];
        for (int i = 0; i < length; i++) {
            int prevSelling = selling;
            int prevBuying = buying;
            selling = Math.max(prevSelling, prevBuying + prices[i]);
            buying = Math.max(prevBuying, prevSelling - prices[i]);
        }
        return selling;
    }

    /**
     * 贪心算法，每当这些图出现向上转折就买卖股票
     */
    public int greedy(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(0, prices[i] - prices[i - 1]);
        }
        return profit;
    }

    public static void main(String[] args) {
        SellStock2 stock = new SellStock2();
        int[] nums = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(stock.maxProfit(nums));
        System.out.println(stock.maxProfitOpt(nums));
    }
}
