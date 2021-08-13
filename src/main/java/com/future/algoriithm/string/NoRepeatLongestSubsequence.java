package com.future.algoriithm.string;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 无重复的最长子串
 *
 * @author jayzhou
 */
public class NoRepeatLongestSubsequence {

    /**
     * 限定字符都是ASCII码内的单个字符
     * 时间复杂度：O(n)
     * 空间复杂度:O(1)/O(128)
     */
    private static int beta(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int[] ascii = new int[128];
        Arrays.fill(ascii, -1);
        ascii[chars[0]] = 0;
        int li = 0;
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            int pi = ascii[chars[i]];
            if (li <= pi) {
                li = pi + 1;
            }
            ascii[chars[i]] = i;
            max = Math.max(max, i - li + 1);
        }
        return max;
    }

    /**
     * 以a[i]为结尾的最大不重复子序列
     * 时间复杂度:O(n)
     * 空间复杂度:0(n)
     */
    private static int alpha(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        // 字符上一次出现的位置
        HashMap<Character, Integer> map = new HashMap<>();
        map.put(chars[0], 0);
        int li = 0;
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            Integer pi = map.get(chars[i]);
            if (pi != null && li <= pi) {
                li = pi + 1;
            }
            max = Math.max(max, i - li + 1);
            map.put(chars[i], i);
        }
        return max;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public static void main(String[] args) {
        String s = "abcdab";
        int alpha = alpha(s);
        System.out.println("alpha=" + alpha);
        int beta = beta(s);
        System.out.println("beta=" + beta);
    }
}
