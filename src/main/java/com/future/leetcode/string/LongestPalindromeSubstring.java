package com.future.leetcode.string;

/**
 * 最长回文子串
 *
 * @author jayzhou
 */
public class LongestPalindromeSubstring {

    /**
     * 以相邻相同元素为中心向两边延伸
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        if (chars.length == 2 && chars[0] == chars[1]) return s;
        int max = 1, begin = 0;
        for (int i = 0; i < chars.length - 1;) {
            int j = i + 1;
            while (j < chars.length && chars[i] == chars[j]) j++;
            int l = i, r = j - 1;
            while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
                l--;
                r++;
            }
            int palindromeLen = r - l - 1;
            if (palindromeLen > max) {
                max = palindromeLen;
                begin = l + 1;
            }
            i = j;
        }
        return new String(chars, begin, max);
    }

    public static void main(String[] args) {
        String s = "abbc";
        LongestPalindromeSubstring palindrome = new LongestPalindromeSubstring();
        System.out.println(palindrome.longestPalindrome(s));
    }
}
