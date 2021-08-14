package com.future.algoriithm.dynamic.linear;

/**
 * 最长公共子串
 * 子串是连续的子序列
 * 求两个字符串的最长公共子串长度
 *
 * @author jayzhou
 */
public class LongestCommonSubstring {

    /**
     * 时间复杂度：O(MN)
     * 空间复杂度：O(n)
     */
    public static int beta(String text1, String text2) {
        if (text1 == null || text2 == null || text2.length() == 0 || text1.length() == 0) return 0;
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int m = chars1.length;
        int n = chars2.length;
        int max = 0;
        int[] dp = new int[n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = n; j >= 0; j--) {
                if (i == 0 || j == 0) {
                    dp[j] = 0;
                } else {
                    if (chars1[i - 1] == chars2[j - 1]) {
                        dp[j] = dp[j - 1] + 1;
                        max = Math.max(max, dp[j]);
                    } else {
                        // 需要清空，重用一维数组值会被下面的行用到
                        dp[j] = 0;
                    }
                }
            }
        }
        return max;
    }

    /**
     * 设f[i][j]表示以i为结尾，以j为结尾的两个公共序列的最大子串长度。
     * 时间复杂度：O(MN)
     * 空间复杂度：O(MN)
     */
    public static int alpha(String text1, String text2) {
        if (text1 == null || text2 == null || text2.length() == 0 || text1.length() == 0) return 0;
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int m = chars1.length;
        int n = chars2.length;
        int max = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (chars1[i - 1] == chars2[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
        }
        return max;
    }

    /**
     * 朴素法
     * 时间复杂度：O(M*N)
     * 空间复杂度：O(1)
     */
    public static int dev(String text1, String text2) {
        if (text1 == null || text2 == null || text2.length() == 0 || text1.length() == 0) return 0;
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int m = chars1.length;
        int n = chars2.length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                int k = i;
                while (j < n && k < m && chars1[k] == chars2[j]) {
                    count++;
                    j++;
                    k++;
                }
                max = Math.max(count, max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String text1 = "abccccccccccde";
        String text2 = "dbccccccabccde";
//        String text1 = "ABCBA";
//        String text2 = "BABCA";
        System.out.println("dev=" + dev(text1, text2));
        System.out.println("alpha=" + alpha(text1, text2));
        System.out.println("beta=" + beta(text1, text2));
    }
}
