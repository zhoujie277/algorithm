package com.future.leetcode.hash;

import java.util.HashMap;

/**
 * 第一个不重复的字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class FirstUnRepeatChar {

    public int firstUniqChar(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            Integer integer = hashMap.get(c);
            if (integer != null) {
                hashMap.put(c, integer + 1);
            } else {
                hashMap.put(c, 1);
            }
        }

        for (int i = 0; i < chars.length; i++) {
            if (hashMap.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FirstUnRepeatChar unRepeatChar = new FirstUnRepeatChar();
        System.out.println(unRepeatChar.firstUniqChar("bbaa"));
    }
}
