package com.future.algoriithm.dynamic.linear;

/**
 * 在一个m*n的棋盘中每一格都放有一个礼物。每个礼物都有一定的价值（价值大于0）。
 * 你可以从棋盘上的左上角开始拿格子的礼物。
 * 规则是：每次只能向右或者向下移动一格。
 * 问：怎样走法，到达棋盘的右下角时，获取的礼物价值最大？
 *
 * @author jayzhou
 */
public class FindGift {

    /**
     * 空间压缩
     */
    public static int beta(int[][] chess) {
        int m = chess.length;
        int n = chess[0].length;
        int[] dp = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[j] = chess[i][j];
                } else if (i == 0) {
                    dp[j] = dp[j - 1] + chess[i][j];
                } else if (j == 0) {
                    dp[j] = dp[j] + chess[i][j];
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]) + chess[i][j];
                }
            }
        }
        return dp[m - 1];
    }

    /**
     * 阶段：走到第i行第j列个格子
     * 状态：拥有的礼物价值。
     * 决策：往左走还是往右走,(需要考虑边界情况，可能只有一种选择)
     * 状态转移方程：dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
     */
    public static int alpha(int[][] chess) {
        int m = chess.length;
        int n = chess[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[i].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = chess[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + chess[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + chess[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + chess[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] chess = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int alpha = alpha(chess);
        System.out.println("alpha=" + alpha);
        int beta = beta(chess);
        System.out.println("beta=" + beta);
    }
}
