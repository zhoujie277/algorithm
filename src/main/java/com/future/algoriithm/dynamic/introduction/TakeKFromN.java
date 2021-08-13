package com.future.algoriithm.dynamic.introduction;

/**
 * 示例；
 * 从N个数中取K个数，使得它们的和最大
 * 该问题正常高效算法思想应采用贪心策略，具体数据结构采用最小堆，取topK个数求和。
 * 此处用动态规划演示
 * 划分阶段：从前1个数中取，前2个数中取，前3个数中取，前i个数中取...
 * 状态：取出1个数，取出2个数，...取出j个数
 * 决策：第j个数，取还是不取;
 * 状态转移方程：dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-1]+array[i-1]));
 * 方程说明：dp[i][j]：前i个数中取出j个数;到第j个数时，有取和不取两种策略。
 * 如果不取，则从前i-1个数中取j个数，如果取了，那就从前i-1个数中取j-1个数，比较取和不取的策略，哪个最优，则采用哪个决策。
 * <p>
 * （该问题其实是背包系列问题的雏形）
 *
 * @author jayzhou
 */
public class TakeKFromN {
    /**
     * alpha版本
     * 空间复杂度 O(NK), 时间复杂度O(NK)
     */
    public static int alpha(int[] array, int k) {
        int n = array.length;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + array[i - 1]);
                }
            }
        }
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        return dp[n][k];
    }

    /**
     * beta版本
     * 滚动数组的空间优化
     * 空间复杂度O(K), 时间复杂度:O(NK)
     */
    public static int beta(int[] array, int k) {
        int n = array.length;
        int[] dp = new int[k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = k; j >= 1; j--) {
                dp[j] = Math.max(dp[j], dp[j - 1] + array[i - 1]);
            }
            for (int i1 : dp) {
                System.out.print(i1 + " ");
            }
            System.out.println();
        }
        return dp[k];
    }

    public static void main(String[] args) {
        int[] array = new int[]{9, 1, 6, 12, 3};
        int value = alpha(array, 4);
        System.out.println(value);

        value = beta(array, 4);
        System.out.println(value);
    }
}
