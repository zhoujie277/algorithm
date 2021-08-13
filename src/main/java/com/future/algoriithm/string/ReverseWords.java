package com.future.algoriithm.string;

/**
 * 翻转字符串
 */
public class ReverseWords {

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
        int mid = len >> 1;
        for (int i = 0; i < mid; i++) {
            char tmp = chars[len - i - 1];
            chars[len - i - 1] = chars[i];
            chars[i] = tmp;
        }
        int lastSpacePos = -1;
        for (int i = 0; i < len; i++) {
            if (chars[i] == ' ') {
                char tmp = chars[i - 1];
                chars[i - 1] = chars[lastSpacePos + 1];
                chars[1 + lastSpacePos] = tmp;
                lastSpacePos = i;
            } else if (i == len - 1) {
                char tmp = chars[i];
                chars[i] = chars[lastSpacePos + 1];
                chars[1 + lastSpacePos] = tmp;
            }
        }
        return new String(chars, 0, len);
    }

    public static void main(String[] args) {
        String allSpace = "      ";
        String str = "  are  you  ok  ";
        String normal = normal(allSpace);
        System.out.println("normal=" + normal);

        String alpha = alpha(allSpace);
        System.out.println("alpha=" + alpha);
    }
}
