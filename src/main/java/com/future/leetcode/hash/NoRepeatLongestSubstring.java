package com.future.leetcode.hash;

import java.util.HashMap;

/**
 * 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
class NoRepeatLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        // 存储重复字符上一次出现的位置。
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int len = 1;
        // 表示以i为结尾的不重复子串的开始位置
        int li = 0;
        hashMap.put(chars[0], 0);
        for (int i = 1; i < chars.length; i++) {
            // pi是上一次出现的位置
            Integer pi = hashMap.get(chars[i]);
            if (pi != null && li <= pi) {
                // pi出现的位置如果大于等于li，就更新li的位置。否则不用管。
                li = pi + 1;
            }
            hashMap.put(chars[i], i);
            int l = i - li + 1;
            if (l > len) {
                len = l;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        //输入: s = "abcabcbb"
        //输出: 3
        //解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
        NoRepeatLongestSubstring instance = new NoRepeatLongestSubstring();
        System.out.println(instance.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(instance.lengthOfLongestSubstring("pwwkew"));
        System.out.println(instance.lengthOfLongestSubstring("bbbbb"));
        System.out.println(instance.lengthOfLongestSubstring("tmmzuxt"));
    }
}
