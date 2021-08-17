package com.future.algoriithm.dynamic.section;

import java.util.Arrays;

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

    private static char[] preprocess(char[] chars) {
        char[] newChars = new char[(chars.length << 1) + 3];
        newChars[0] = '^';
        newChars[1] = '#';
        newChars[newChars.length - 1] = '$';
        for (int i = 0; i < chars.length; i++) {
            int idx = (i + 1) << 1;
            newChars[idx] = chars[i];
            newChars[idx + 1] = '#';
        }
        System.out.println(Arrays.toString(newChars));
        return newChars;
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static String manacherOpt(String s) {
        if (s == null || s.length() <= 1) return s;
        int begin = 2;
        int max = 1;
        char[] chars = preprocess(s.toCharArray());
        int[] manacher = new int[chars.length];
        int c = 0, r = 0;
        for (int i = 2; i < chars.length - 2; i++) {
            if (i < r) {
                int li = (c << 1) - i;
                int ri = i + manacher[li];
                if (ri < r) {
                    manacher[i] = manacher[li];
                    continue;
                }
                manacher[i] = ri == r ? manacher[li] : (r - i);
            }

            while (chars[i + manacher[i] + 1] == chars[i - manacher[i] - 1]) {
                manacher[i]++;
            }

            if (i + manacher[i] > r) {
                r = i + manacher[i];
                c = i;
            }

            if (manacher[i] > max) {
                max = manacher[i];
                begin = i;
            }
        }
        // max代表manacher数组以begin为中心点向一方扩展的元素个数。 begin - max 是开始的索引
        begin = (begin - max) >> 1;
        return s.substring(begin, begin + max);
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static String manacher(String s) {
        if (s == null || s.length() <= 1) return s;
        int begin = 2;
        int max = 1;
        char[] chars = preprocess(s.toCharArray());
        int[] manacher = new int[chars.length];
        int c = 0, r = 0;
        for (int i = 2; i < chars.length - 2; i++) {
            if (i < r) {
                int li = (c << 1) - i;
                manacher[i] = (i + manacher[li] <= r) ? manacher[li] : (r - i);
            }

            while (chars[i + manacher[i] + 1] == chars[i - manacher[i] - 1]) {
                manacher[i]++;
            }

            if (i + manacher[i] > r) {
                r = i + manacher[i];
                c = i;
            }

            if (manacher[i] > max) {
                max = manacher[i];
                begin = i;
            }
        }
        // max代表manacher数组以begin为中心点向一方扩展的元素个数。 begin - max 是开始的索引
        begin = (begin - max) >> 1;
        return s.substring(begin, begin + max);
    }

    /**
     * 扩展中心法的优化
     * 以相同元素为基准，向旁边扩散。该算法包含了以中心线为基准的扩散，故相比普通的中心扩散法优化至少一半。
     * 时间复杂度：最坏情况，"abababababababa"这种串。每次都需要扩散遍历。此时复杂度为：O(n^2)
     * 空间复杂度: O(1)
     */
    private static String extendsCenterOpt(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        int max = 1;
        int begin = 0;
        int l, r;
        for (int i = 1, li = 0; i < chars.length; i++) {
            while (i < chars.length && chars[li] == chars[i]) {
                i++;
            }
            l = li;
            r = i - 1;
            while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
                l--;
                r++;
            }
            int len = r - l - 1;
            if (len > max) {
                max = len;
                begin = l + 1;
            }
            li = i;
        }
        return new String(chars, begin, max);
    }

    /**
     * 扩展中心法
     * 逐个遍历数组中的每个元素，分别计算以该元素为中心扩撒的回文串长度，如果该长度比之前的大，则覆盖。
     * 注意：由于有奇偶型存在，所以还需要增加该元素和该元素右边的中心线为基准的扩散。
     * 另外：数组首尾两端的元素因为以其为中心最大的回文子串便是它自身，故不需要遍历。
     * *    又因为，去掉首尾两数之后，便会有一端的中心线遍历不到，所以在最后需要补上判断。
     * *    并且，为了方便计算，所以从数组后面元素开始遍历，这样最后补上chars[0] ==chars[1]的判断即可。
     * <p>
     * 时间复杂度：最坏时间复杂度，整个字符都是回文串，此时，时间复杂度O(n^2)。可优化
     * 空间复杂度：O(1)
     */
    private static String extendsCenter(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        int max = 1;
        int begin = 0;
        for (int i = chars.length - 2; i >= 1; i--) {
            // 基于字符i为中心的扩散
            int len1 = palindromeLen(chars, i - 1, i + 1);
            // 基于i和i+1之间的竖线扩散
            int len2 = palindromeLen(chars, i, i + 1);
            len1 = Math.max(len1, len2);
            if (len1 > max) {
                max = len1;
                begin = i - ((max - 1) >> 1);
            }
        }
        if (chars[0] == chars[1] && max < 2) {
            max = 2;
            begin = 0;
        }
        return new String(chars, begin, max);
    }

    private static int palindromeLen(char[] chars, int l, int r) {
        while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
            l--;
            r++;
        } // len = (r - 1) - (l + 1) + 1 = r - l - 1;
        return r - l - 1;
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
        if (s == null || s.length() == 0) return s;
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
        String text1 = "abb";
        System.out.println("dev=" + dev(text1));
        System.out.println("alpha=" + alpha(text1));
        System.out.println("beta=" + beta(text1));
        System.out.println("extendsCenter=" + extendsCenter(text1));
        System.out.println("extendsCenterOpt=" + extendsCenterOpt(text1));
        System.out.println("manacher=" + manacher(text1));
        System.out.println("product=" + product(text1));
    }
}
