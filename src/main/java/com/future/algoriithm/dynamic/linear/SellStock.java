package com.future.algoriithm.dynamic.linear;

import com.future.utils.PrintUtils;

/**
 * 买卖股票的问题
 * 假设某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少。
 *
 * @author jayzhou
 */
public class SellStock {

    /**
     * 最终版本
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int product(int[] stocks) {
        int len = stocks.length;
        int maxProfit = 0;
        int min = 0x3f3f3f;
        for (int i = 1; i < len; i++) {
            min = Math.min(min, stocks[i - 1]);
            maxProfit = Math.max(stocks[i] - min, maxProfit);
        }
        return maxProfit;
    }

    /**
     * 求区间最小值的优化
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int beta(int[] stocks) {
        int len = stocks.length;
        int[] dp = new int[len];
        int maxProfit = 0;
        int min = 0x3f3f3f;
        for (int i = 1; i < len; i++) {
            min = Math.min(min, stocks[i - 1]);
            dp[i] = Math.max(0, stocks[i] - min);
            maxProfit = Math.max(dp[i], maxProfit);
        }
        PrintUtils.println(dp);
        return maxProfit;
    }

    /**
     * 阶段: 第i天卖出股票
     * 状态：第i天卖出股票的价值
     * 决策：在第i天卖出股票能获取的最大利益
     * 时间复杂度: O(n2)
     * 空间复杂度: O(n)
     */
    private static int alpha(int[] stocks) {
        int len = stocks.length;
        int[] dp = new int[len];
        int maxProfit = 0;
        int min = 0x3f3f3f;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                min = Math.min(min, stocks[j]);
            }
            dp[i] = Math.max(0, stocks[i] - min);
            maxProfit = Math.max(dp[i], maxProfit);
        }
        PrintUtils.println(dp);
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] data = new int[]{7, 1, 5, 3, 6, 4};
        int alpha = alpha(data);
        System.out.println("alpha=" + alpha);
        int beta = beta(data);
        System.out.println("beta=" + beta);
        int product = product(data);
        System.out.println("product=" + product);
    }
}
