package com.future.algoriithm.dynamic.linear;

/**
 * 求一个序列，n个数的最大连续子序列和
 * <p>
 * 阶段：f[i]表示以a[i]为结尾的连续子序列和。前i个数表示第i个阶段
 * 状态：子序列最后一个数a[i]作为连续子序列结尾的和的正负
 * 决策：取或者不取最后一个数作为以i为结尾的连续子序列的和。
 * 最优策略：前面的序列之和如果为负数，则不取最后一个数作为连续子序列，并且单独称为一个连续子序列的开始。
 * 如果为正数，则以i为结尾的数列最大和为dp[i-1]加上array[i]
 * 状态转移方程：dp[i] = dp[i-1] > 0 ? dp[i-1] + array[j]: array[j]
 *
 * @author jayzhou
 */
public class MaximalContinuousSubsequence {

    /**
     * alpha版本
     * 时间复杂度:O(n)
     * 空间复杂度：O(n)
     */
    public static int alpha(int[] array) {
        int n = array.length;
        int[] dp = new int[n];
        int max = dp[0] = array[0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + array[i] : array[i];
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * beta版本：优化空间复杂度
     * 空间复杂度：O(1)
     * 时间复杂度：O(n)
     */
    public static int beta(int[] array) {
        int dp, max;
        max = dp = array[0];
        for (int j : array) {
            dp = dp > 0 ? dp + j : j;
            max = Math.max(max, dp);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int alpha = alpha(array);
        System.out.println("alpha=" + alpha);

        int beta = beta(array);
        System.out.println("beta=" + beta);
    }
}
