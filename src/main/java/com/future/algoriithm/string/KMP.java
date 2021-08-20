package com.future.algoriithm.string;

import java.util.Arrays;

/**
 * KMP字符串匹配算法
 *
 * @author jayzhou
 */
public class KMP {

    public int indexOf2(String text, String pattern) {
        if (text == null || pattern == null || text.length() < pattern.length()) return -1;
        int[] nextTable = getOriginNextTable(pattern);
        int i = 0, j = 0;
        while (j < pattern.length() && i - j < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (j > 0)
                    j = nextTable[j - 1];
                else i++;
            }
        }
        if (j == pattern.length()) return i - j;
        return -1;
    }

    public int indexOf(String text, String pattern) {
        if (text == null || pattern == null || text.length() < pattern.length()) return -1;
        int[] nextTable = getNextTable(pattern);
        int i = 0, j = 0;
        while (j < nextTable.length && i - j < text.length()) {
            if (j < 0 || text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = nextTable[j];
            }
        }
        if (j == pattern.length()) return i - j;
        return -1;
    }

    /**
     * 构建右移之后的next表
     * 原始next表的最后一个字符的next值，在实际使用中，没有意义。
     * j 表示以 j 为结尾的最大真前缀，i表示以i为结尾的真后缀子串。
     * next[i]表示的是以 pattern.charAt(i-1)字符为结尾的真后缀子串相等的真前缀的最大长度。
     * 由于真前缀其实索引为0，故真前缀长度减1，就是真前缀的结尾索引。
     */
    private int[] getNextTable(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = -1;
        int iMax = next.length - 1;
        int i = 1, j = 0;
        while (i < iMax) {
            if (j < 0 || pattern.charAt(i) == pattern.charAt(j)) {
                // 右移：填充下一个i的值
                next[++i] = ++j;
            } else {
                // 当pattern[i] != pattern[j]时, 已知 pattern[i-1] = pattern[j-1]。
                // 拿出以 pattern[j - 1] 字符为结尾的子串的最大真前缀长度。赋给j。然后与pattern[i]字符再次进行比较。
                j = next[j];
            }
        }
        return next;
    }

    /**
     * 构建原始的next表
     * j 表示以 j 为结尾的最大真前缀，i表示以i为结尾的真后缀子串。
     * next[i]表示以 pattern.charAt(i) 字符为结尾的真后缀子串相等的真前缀的最大长度。
     * 由于真前缀起始索引为0，所以真前缀的长度减 1,也就是真前缀的结尾索引。
     */
    private int[] getOriginNextTable(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < next.length; ) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                next[i++] = ++j;
            } else {
                // 最后一个字符不想等，则取最大相等真前缀子串的前一个真前缀的结尾字符，然后再进行匹配
                // 而 pattern[j - 1]和 pattern[i - 1]是匹配的，而next[j-1]表示以j-1为结尾的子串的最大真前缀子串长度
                // 所以查找 next[j - 1]的值，将它赋给j, 继续匹配。
                if (j > 0)
                    j = next[j - 1];
                else i++;
            }
        }
        return next;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public static void main(String[] args) {
        String pattern = "ABCDEFG";
        String pattern1 = "AAAAAAAB";
        String pattern2 = "AABCAAABC";
        String pattern3 = "ABCABCAAAA";
        KMP kmp = new KMP();
        System.out.println("------origin next table------");
        System.out.println("pattern=" + Arrays.toString(kmp.getOriginNextTable(pattern)));
        System.out.println("AAAAAAAB=" + Arrays.toString(kmp.getOriginNextTable(pattern1)));
        System.out.println("AABCAAABC=" + Arrays.toString(kmp.getOriginNextTable(pattern2)));
        System.out.println("AABCAAABC=" + Arrays.toString(kmp.getOriginNextTable(pattern3)));
        System.out.println("------next table------");
        System.out.println("pattern=" + Arrays.toString(kmp.getNextTable(pattern)));
        System.out.println("AAAAAAAB=" + Arrays.toString(kmp.getNextTable(pattern1)));
        System.out.println("AABCAAABC=" + Arrays.toString(kmp.getNextTable(pattern2)));
        System.out.println("AABCAAABC=" + Arrays.toString(kmp.getNextTable(pattern3)));

        System.out.println("-------indexOf-------");
        System.out.println(kmp.indexOf("abcfabczhoujie", "zhou"));
    }
}
