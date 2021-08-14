package com.future.algoriithm.dynamic.linear;

/**
 * 最长公共子序列
 * 给定两个字符串text1和text2，返回这两个字符串的最长公共子序列的长度。如果不存在公共子序列，返回0。
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * <p>
 * 示例:
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是"ace" ，它的长度为 3 。
 * <p>
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 * <p>
 * 典型的坐标型动态规划题
 *
 * @author jayzhou
 */
public class LongestCommonSubsequence {

    /**
     * 空间复杂度：O(M)
     * 时间复杂度: O(MN)
     */
    public static int product(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int m = chars1.length;
        int n = chars2.length;
        int leftTop, lastFj = 0;
        int[] f = new int[n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                leftTop = lastFj;
                lastFj = f[j];
                if (i == 0 || j == 0) {
                    f[j] = 0;
                } else {
                    if (chars1[i - 1] == chars2[j - 1]) {
                        f[j] = leftTop + 1;
                    } else {
                        f[j] = Math.max(f[j], f[j - 1]);
                    }
                }
            }
        }
        return f[n];
    }

    public static int beta(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int m = chars1.length;
        int n = chars2.length;
        int[][] f = new int[2][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    f[i & 1][j] = 0;
                } else {
                    if (chars1[i - 1] == chars2[j - 1]) {
                        f[i & 1][j] = f[(i - 1) & 1][j - 1] + 1;
                    } else {
                        f[i & 1][j] = Math.max(f[(i - 1) & 1][j], f[i & 1][j - 1]);
                    }
                }
            }
        }
        return f[m & 1][n];
    }

    /**
     * 动态规划方法
     * 减小问题规模恰恰也是动态规划的主要思想。
     * 设 f[i][j] 表示 s1中前i个字符，s2中前j个字符的最小公共子串长度。
     * 阶段：f[i][j]: 前i个字符和前j个字符.
     * 状态：前i个字符和前j个字符的最长公共子序列的长度.
     * 初始阶段的状态：f[0][0] = f[i][0] = f[0][j] = 0;
     * 决策：如果 s1[i-1] == s2[j-1], 则 f[i][j] = f[i-1][j-1] + 1;
     * *    否则: f[i][j] = max{f[i-1][j], f[i][j-1]};
     * 此处，去最大值的集合没有包括f[i-1][j-1]是因为f[i][j-1]包括了这个情况。
     * 最优策略：从决策情况中，取得最大值，便是子问题的最优解，从而推导出愿问题的最优解。
     * 状态转移方程：f[i][j] = (s1[i-1]==s2[j-1])? f[i][j]=f[i-1][j-1]+1:max{f[i-1][j], f[i][j-1]};
     * <p>
     * 时间复杂度：O(MN)
     * 空间复杂度：O(MN)
     */
    public static int alpha(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        int m = chars1.length;
        int n = chars2.length;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    f[i][j] = 0;
                } else {
                    if (chars1[i - 1] == chars2[j - 1]) {
                        f[i][j] = f[i - 1][j - 1] + 1;
                    } else {
                        f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                    }
                }
            }
        }
        return f[m][n];
    }

    private static int dev(char[] s1, int i, char[] s2, int j) {
        if (i == 0 || j == 0) return 0;
        if (s1[i - 1] != s2[j - 1]) {
            return Math.max(dev(s1, i - 1, s2, j), dev(s1, i, s2, j - 1));
        }
        return dev(s1, i - 1, s2, j - 1) + 1;
    }

    /**
     * 递归法
     * 设text1长度为N，text2长度为M
     * 没有采用记忆话搜索的递归搜索，时间复杂度很高
     * 当n = m时，时间复杂度为 O(2^n)
     * 空间复杂度：O(k)，k = min{m, n};
     */
    public static int dev(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        return dev(chars1, chars1.length, chars2, chars2.length);
    }

    public static void main(String[] args) {
//        String text1 = "mhunuzqrkzsnidwbun";
//        String text2 = "szulspmhwpazoxijwbq";
        String text1 = "abcba";
        String text2 = "abcbcba";
//        String text1 = "sequence";
//        String text2 = "equals";
        int dev = dev(text1, text2);
        System.out.println("dev=" + dev);
        int alpha = alpha(text1, text2);
        System.out.println("alpha=" + alpha);
        System.out.println("beta=" + beta(text1, text2));
        System.out.println("product=" + product(text1, text2));
    }

}
