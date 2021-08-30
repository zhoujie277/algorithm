package com.future.leetcode.dp;

/**
 * 买卖股票的最佳时机含有手续费
 * <p>
 * 给定一个整数数组prices，其中第i个元素代表了第i天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * 示例 1：
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润:((8 - 1) - 2) + ((9 - 4) - 2) = 8
 * 示例 2：
 * 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 * <p>
 * 提示：
 * 1 <= prices.length <= 5 * 10^4
 * 1 <= prices[i] < 5 * 10^4
 * 0 <= fee < 5 * 10^4
 * <p>
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 *
 * @author jayzhou
 */
public class SellStockWithFee {

    /**
     * 状态定义：
     * f[i[0]： 没有股票状态；
     * f[i][1]： 持有股票状态；
     * 此处假设：交易费用产生在买入股票的时候。
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int[][] f = new int[len][2];
        f[0][1] = -prices[0] - fee;
        for (int i = 1; i < len; i++) {
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
            f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - prices[i] - fee);
        }
        return f[len - 1][0];
    }

    public int maxProfitOpt(int[] prices, int fee) {
        int len = prices.length;
        int selling = 0, buying = -prices[0] - fee;
        for (int i = 1; i < len; i++) {
            int prevSelling = selling, prevBuying = buying;
            selling = Math.max(prevSelling, prevBuying + prices[i]);
            buying = Math.max(prevBuying, prevSelling - prices[i] - fee);
        }
        return selling;
    }

    /**
     * 贪心策略：
     *
     */
    public int greedy(int[] prices, int fee) {
        int len = prices.length;
        int profit = 0;
        int buy = prices[0] + fee;
        for (int i = 1; i < len; i++) {
            if (prices[i] + fee < buy) {
                // 当出现更低成本的股票价格时，以更低的价格重新买入股票。也有可能因此产生了交易次数。
                buy = prices[i] + fee;
            } else if (prices[i] > buy){
                profit += prices[i] - buy;
                // 当价格连续升高的时候，相当于0费用重新买入了一只更高价格的股票，即累计收益
                buy = prices[i];
            }
            // 其它情况，就是收益低于买入的价格，不做处理。
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 2, 8, 4, 9};
        int[] nums2 = new int[]{1, 3, 7, 5, 10, 3};
        SellStockWithFee withFee = new SellStockWithFee();
        System.out.println(withFee.maxProfit(nums, 2));
        System.out.println(withFee.maxProfit(nums2, 3));
        System.out.println(withFee.maxProfitOpt(nums, 2));
        System.out.println(withFee.maxProfitOpt(nums2, 3));
        System.out.println(withFee.greedy(nums, 2));
        System.out.println(withFee.greedy(nums2, 3));
    }
}
