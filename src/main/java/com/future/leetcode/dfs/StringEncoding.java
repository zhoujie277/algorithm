package com.future.leetcode.dfs;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 字符串解码
 * <p>
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入。
 * <p>
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/gdwjv/
 *
 * @author jayzhou
 */
public class StringEncoding {

    public String decodeString(String s) {
        if (s == null || s.length() == 0) return s;
        Deque<String> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isDigit(c)) {
                StringBuilder ch = new StringBuilder();
                do {
                    ch.append(c);
                    c = chars[++i];
                } while (Character.isDigit(c));
                stack.addLast(ch.toString());
            } else {
                if (c == ']') {
                    StringBuilder builder = new StringBuilder();
                    while (!"[".equals(stack.peekLast())) {
                        builder.insert(0, stack.pollLast());
                    }
                    stack.pollLast();
                    StringBuilder result = new StringBuilder();
                    int prevNumber = Integer.parseInt(stack.pollLast());
                    for (int k = 0; k < prevNumber; k++) {
                        result.append(builder);
                    }
                    stack.addLast(result.toString());
                    continue;
                }
            }
            stack.addLast(c + "");
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pollLast());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "2[abc]3[cd]ef";
        String s1 = "3[abc]3[cd]";
        String s2 = "3[a]";
        String s3 = "3[a2[c]]";
        String s4 = "100[leetcode]";
        StringEncoding encoding = new StringEncoding();
        System.out.println(encoding.decodeString(s));
        System.out.println(encoding.decodeString(s1));
        System.out.println(encoding.decodeString(s2));
        System.out.println(encoding.decodeString(s3));
        System.out.println(encoding.decodeString(s4));
    }
}
