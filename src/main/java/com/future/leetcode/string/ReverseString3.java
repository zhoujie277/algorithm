package com.future.leetcode.string;

/**
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 示例：
 * <p>
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 * <p>
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 *
 * @author jayzhou
 */
@SuppressWarnings("SpellCheckingInspection")
public class ReverseString3 {

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        char[] chars = s.toCharArray();
        for (int slow = 0, fast = 0; fast < chars.length; fast++) {
            if (chars[fast] == ' ' || fast == chars.length - 1) {
                int left = slow, right = fast == chars.length - 1 ? fast : fast - 1;
                while (left < right) {
                    char tmp = chars[left];
                    chars[left++] = chars[right];
                    chars[right--] = tmp;
                }
                slow = fast + 1;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        ReverseString3 reverseString3 = new ReverseString3();
        System.out.println(reverseString3.reverseWords(s));
    }
}
