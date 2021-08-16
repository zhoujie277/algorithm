package com.future.algoriithm.string;

import java.util.Arrays;

/**
 * 翻转字符串
 * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
 * 说明：
 * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
 * 翻转后单词间应当仅用一个空格分隔。
 * 翻转后的字符串中不应包含额外的空格。
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 *
 * @author jayzhou
 */
public class ReverseWords {

    /**
     * 寻常做法
     */
    private static String normal(String str) {
        String[] s = str.trim().split("\\s+");
        int len = s.length;
        int mid = len >> 1;
        for (int i = 0; i < mid; i++) {
            String tmp = s[len - i - 1];
            s[len - i - 1] = s[i];
            s[i] = tmp;
        }
        StringBuilder builder = new StringBuilder();
        for (String s1 : s) {
            builder.append(s1);
            builder.append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    /**
     * alpha版本
     * 第一步，遍历字符数组，去除多余空格。
     * 第二步，inverse整个字符串
     * 第三步，以空格为单位，识别单词，依次inverse每个单词。
     * 这个效率比normal版本要高。
     */
    private static String alpha(String str) {
        char[] chars = str.toCharArray();
        int len = 0;
        char lastChar = ' ';
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c != ' ' || lastChar != ' ') {
                chars[len++] = c;
                lastChar = c;
            }
        }
        if (len == 0) return "";
        if (lastChar == ' ') len -= 1;
        reverse(chars, 0, len);
        int lastSpacePos = -1;
        for (int i = 0; i < len; i++) {
            if (chars[i] == ' ') {
                reverse(chars, lastSpacePos + 1, i);
                lastSpacePos = i;
            } else if (i == len - 1) {
                reverse(chars, lastSpacePos + 1, len);
            }
        }
        return new String(chars, 0, len);
    }

    public static void reverse(char[] chars, int l, int r) {
        int len = l + r;
        int mid = len >> 1;
        for (int i = l; i < mid; i++) {
            char tmp = chars[len - i - 1];
            chars[len - i - 1] = chars[i];
            chars[i] = tmp;
        }
    }

    public static void main(String[] args) {
        String allSpace = "      ";
//        String str = "  are  you  ok  ";
        String str = "  hello world  ";
        String normal = normal(str);
        System.out.println("normal=" + normal);

        String alpha = alpha(str);
        System.out.println("alpha=" + alpha);

        char[] chars = "world olleh".toCharArray();
        reverse(chars, 6, chars.length);
        System.out.println(Arrays.toString(chars));
    }
}
