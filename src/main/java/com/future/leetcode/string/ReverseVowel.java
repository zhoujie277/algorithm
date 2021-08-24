package com.future.leetcode.string;

/**
 * 反转元音字母
 *
 * @author jayzhou
 */
public class ReverseVowel {

    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; ) {
            while (i < j && !isVowel(chars[i])) {
                i++;
            }
            while (i < j && !isVowel(chars[j])) {
                j--;
            }
            if (i >= j) break;
            char t = chars[i];
            chars[i++] = chars[j];
            chars[j--] = t;
        }
        return new String(chars);
    }

    public boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    public static void main(String[] args) {
        String s = "hellO";
        System.out.println(new ReverseVowel().reverseVowels(s));
    }
}
