package com.future.algoriithm.dynamic.section;

import static com.future.algoriithm.dynamic.DynamicProgramming.printDp;

/**
 * 最长回文子串
 * 给定一个字符串s，找到s中最大的回文子串，假设s的最大长度为1000。
 * <p>
 * 输入："babad"
 * 输出："bab"
 * 备注："aba"也是一个有效答案
 * <p>
 * 输入："cbbd"
 * 输出："bb"
 *
 * @author jayzhou
 */
public class LongestPalindromeSubstring {

    private static String product(String s) {
        return "";
    }

    /**
     * 题意要求返回string，alpha版本要返回string比较难。
     * 换一种思路，依然是动态规划，
     * 现假设 f[i][j] 表示区间(i,j)是不是回文子串
     * 阶段：区间(i,j)
     * 状态：f[i][j] = true 是回文子串
     * 决策：s[i] == s[j] ，则 f[i][j] = f[i+1][j-1] 否则：f[i][j] = false;
     * 策略：从是回文子串中寻找长度最大的。
     * 状态转移方程：
     * 计算顺序，依然从左至右，从下至上搜索。
     */
    private static String beta(String s) {
        if (s == null || s.length() == 0) return "";
        char[] chars = s.toCharArray();
        int n = chars.length;
        int max = 0;
        int beginIndex = 0;
        boolean[][] f = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                int len = j - i + 1;
                if (i == j) {
                    f[i][j] = true;
                } else if (chars[i] == chars[j]) {
                    f[i][j] = len <= 2 || f[i + 1][j - 1];
                }
                if (f[i][j] && len > max) {
                    max = len;
                    beginIndex = i;
                }
            }
        }
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                System.out.print(f[i][j] + " ");
            }
            System.out.println();
        }
        return s.substring(beginIndex, beginIndex + max);
    }

    /**
     * 典型区间型dp问题
     * 思路：根据题意，要求解最大回文子串，设f[i][j]表示以第i个字符与第j个字符之间的最大回文子串。
     * 阶段：区间(i,j)
     * 状态：f[i][j] = true, 表示区间(i,j)是回文子串
     * 决策：如果 s[i] == s[j], 则 f[i][j] = f[i+1][j-1] + 1。否则 f[i][j] = max{f[i+1][j], f[i][j-1]}
     * 最优策略：f[i][j]就是区间(i,j)最大的
     * 状态转移方程：f[i][j] = s[i]==s[j]? f[i+1]+[j-1] + 1 : max {f[i][j-1], f[i+1][j]};
     * 边界情况：由于是区间(i,j)，所以j ≥ i时，计算才有意义。i==j ，最大回文子串为 1;
     * 计算顺序：由于状态转移方程需要求 i+1 和 j-1，用dp二维数组的视角来看，就是从最下面和左边开始计算舒徐。
     * 所以需要从字符串最右边算起。
     */
    public static int alpha(String s) {
        //TODO 有bug 待查
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    f[i][j] = 1;
                } else {
                    if (chars[i] == chars[j]) {
                        int len = j - i + 1;
                        if (len == 2) {
                            f[i][j] = 2;
                        } else {
                            f[i][j] = f[i + 1][j - 1] + 1;
                        }
                    } else {
                        f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                    }
                }
            }
        }

        printDp(f);
        return f[0][n - 1];
    }

    /**
     * 朴素法
     * 时间复杂度：O(N3)
     * 空间复杂度: O(1)
     */
    public static String dev(String s) {
        if (s == null || s.length() == 0) return "";
        char[] chars = s.toCharArray();
        int max = 0;
        int beginIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                if (isPalindrome(chars, i, j)) {
                    int len = j - i + 1;
                    if (len > max) {
                        max = len;
                        beginIndex = i;
                    }
                }
            }
        }
        return s.substring(beginIndex, beginIndex + max);
    }

    private static boolean isPalindrome(char[] chars, int i, int j) {
        while (i < j && chars[i] == chars[j]) {
            i++;
            j--;
        }
        return i >= j;
    }

    public static void main(String[] args) {
//        String text1 = "babad";
        String text1 = "cbbd";
        System.out.println("dev=" + dev(text1));
        System.out.println("alpha=" + alpha(text1));
        System.out.println("beta=" + beta(text1));
    }
}
