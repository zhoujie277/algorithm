package com.future.leetcode.dp;

/**
 * 给定一个整数数组，其中第i个元素代表了第i天的股票价格。
 * <p>
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * <p>
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 */
@SuppressWarnings("unused")
public class SellStockWithCoolDown {

    /**
     * 相比stock2多了一个冷冻期状态。
     * 没有股票状态： j = 0；
     * 持有股票状态：j = 1；
     * 冷冻期状态： j = 2；
     * f[i][j] 表示在第 i 天交易结束后，处于 j 状态下的最大收益。
     * f[i][0] 表示第 i 天处于没有股票，但不是冷冻期的状态
     * f[i][1] 表示第 i 天处于有股票的状态
     * f[i][2] 表示第 i 天处于冷冻期的状态。
     * <p>
     * 状态转移过程如下：
     * 前一天持有股票，今天卖出。此时状态为：没有股票
     * 前一天没有股票，今天也没买入，此时状态为：没有股票
     * 前一天处于冷冻期，今天也没有买入，此时状态为：没有股票。这种状态跟上一种其实是一种。
     * 前一天卖出股票，今天处于冷冻期，不能买入股票，此时状态为：冷冻期
     * 前一天持有股票，今天继续持有股票，此时状态为：持有股票
     * 前一天没有股票(今天一定具备购买资格)，今天购买股票，此时状态为：持有股票
     * <p>
     * 最后一天持有股票是没有收益的，所以最大收益是f[n-1][0]
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] f = new int[len][3];
        f[0][0] = f[0][2] = 0;
        f[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
            f[i][1] = Math.max(f[i - 1][1], f[i - 1][2] - prices[i]);
            f[i][2] = f[i - 1][0];
        }
        return f[len - 1][0];
    }

    public int maxProfitOpt(int[] prices) {
        int len = prices.length;
        int selling = 0, buying = 0, cooling = 0;
        buying = -prices[0];
        for (int i = 1; i < len; i++) {
            int prevBuying = buying;
            int prevSelling = selling;
            int prevCooling = cooling;
            selling = Math.max(prevSelling, prevBuying + prices[i]);
            buying = Math.max(prevBuying, cooling - prices[i]);
            cooling = prevSelling;
        }
        return selling;
    }

    /**
     * 另一种对冷冻期的状态转移理解：
     * 这里的"处于冷冻期"指的是第 i 天结束之后的状态，也就是说，在第 i 天结束之后处于冷冻期，那么第 i + 1 天无法买入股票。
     * 此时返回值会有变化，要么是处于冷冻期的，要么是没有股票的
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        int[][] f = new int[len][3];
        f[0][0] = f[0][2] = 0;
        f[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            // 交易结束后处于冷冻期，也就是今天有股票卖出的收益
            f[i][2] = f[i - 1][1] + prices[i];
            // 交易结束后，没有股票状态，且不是冷冻期，则要么前一天没有股票，要么前一天处于冷冻期。
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2]);
            // 交易结束后，持有股票，可能是昨天持有股票，或者昨天处于没有股票的状态，今天买入。
            f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - prices[i]);
        }
        return Math.max(f[len - 1][0], f[len - 1][2]);
    }

    public static void main(String[] args) {
        int[] profits = new int[]{1, 2, 3, 0, 2};
        int[] nums = new int[]{7, 1, 5, 3, 6, 4};
        SellStockWithCoolDown stock = new SellStockWithCoolDown();
        System.out.println(stock.maxProfit(profits));
        System.out.println(stock.maxProfit(nums));
        System.out.println(stock.maxProfit2(profits));
        System.out.println(stock.maxProfit2(nums));
        System.out.println(stock.maxProfitOpt(profits));
        System.out.println(stock.maxProfitOpt(nums));
    }
}
