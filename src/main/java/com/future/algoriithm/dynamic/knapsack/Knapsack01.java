package com.future.algoriithm.dynamic.knapsack;

import static com.future.algoriithm.dynamic.DynamicProgramming.printDp;

/**
 * 运用动态规划的解决方案
 * 0-1背包
 * <p>
 * 有n件物品和一个最大承重为W的背包，每件物品的重量是Wi，价值是Vi；
 * 在保证总重量不超过W的前提下，将哪几件物品装入背包，可使得背包总价值最大。
 * 注意：每个物品只有1件，也就是每个物品只能选择0件或1件，因此称为0-1背包问题。
 *
 * @author jayzhou
 */
public class Knapsack01 {

    int[] weights = new int[]{2, 3, 4, 7};
    int[] values = new int[]{1, 3, 5, 9};

    /**
     * dp[i][j] 表示在最大承重为j，且可选物品为前i件的情况下，能装进背包的最大价值
     * 假设从第一件物品开始选，选到第i件时，最大承重为j，则有如下两种情况：
     * 1、第i件物品能装下，但是能选择装和不装进背包
     * a、选择装进背包
     * 则有 dp[i][j] = dp[i-1][j-weights[i-1]] + v[i-1]
     * b、选择不装进背包,
     * 则有 dp[i][j] = dp[i-1][j];
     * 如上，则背包能达到的最大价值为a、b的两种情况的最大值。
     * 2、如果物品装不下背包，则背包的最大价值为（前i-1件物品、承重为j的最大值）
     * 则有 dp[i][j] = dp[i-1][j];
     */
    public int alpha(int cap) {
        int n = weights.length;
        int[][] dp = new int[n + 1][cap + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= cap; j++) {
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }

        printDp(dp);
        return dp[n][cap];
    }

    /**
     * 从后往前递推数据
     * beta版的基础
     */
    public int alpha2(int cap) {
        int n = weights.length;
        int[][] dp = new int[n + 1][cap + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = cap; j > 0; j--) {
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        printDp(dp);
        return dp[n][cap];
    }

    /**
     * beta版是上面的优化版本，
     * 主要是优化空间复杂度，因为计算dp[i][j]只依赖了前一行，并且是容量小于等于j的数据
     * 故可以采用滚动的一维数组，以及从后往前运算覆盖一维数组就可以。
     * 当 j < weights[i-1]时，则dp[j]为上一次的dp[j]
     * 当j >= weights[i-1]时，则dp[j] = dp[j-weights[i-1]+values[i-1]
     * 此处weights[i-1]采用i-1是因为weights数组也就是物品数组的索引是从0开始。
     * 而dp数组的索引0位置都初始化为了0。表示选取第0件物品，最大承重为0
     */
    private int beta(int cap) {
        int n = weights.length;
        int[] dp = new int[cap + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = cap; j >= weights[i - 1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
            }
        }
        return dp[cap];
    }

    public static void main(String[] args) {
        int n = 10;
        Knapsack01 sack = new Knapsack01();
        int solve = sack.alpha(n);
        System.out.println("alpha maxValue=" + solve);

        System.out.println("-----------------------------");
        int alpha2 = sack.alpha2(n);
        System.out.println("alpha2 maxValue=" + alpha2);

        System.out.println("-----------------------------");
        int beta = sack.beta(n);
        System.out.println("beta maxValue=" + beta);
    }
}
