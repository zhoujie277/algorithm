package com.future.algoriithm.string;

/**
 * 有效的字母异味词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 注意：若s和t中每个字符出现的次数都相同，则称s 和 t互为字母异位词。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 */
public class Anagram {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(k) k为字符集大小。此处k = 26;
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        int[] counts = new int[26];
        for (char c : cs) {
            counts[c - 'a']++;
        }
        for (char c : ct) {
            if (--counts[c - 'a'] < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean anagram = new Anagram().isAnagram("anagram", "nagaram");
        System.out.println(anagram);
    }
}
