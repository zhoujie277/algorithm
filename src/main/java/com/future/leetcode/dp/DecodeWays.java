package com.future.leetcode.dp;

/**
 * 解码方法
 * <p>
 * 一条包含字母A-Z 的消息通过以下映射进行了 编码 ：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 * 示例 1：
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2：
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 示例 3：
 * 输入：s = "0"
 * 输出：0
 * 解释：没有字符映射到以 0 开头的数字。
 * 含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
 * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 * 示例 4：
 * 输入：s = "06"
 * 输出：0
 * 解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
 * <p>
 * 提示：
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 * <p>
 * 链接：https://leetcode-cn.com/problems/decode-ways
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class DecodeWays {

    /**
     * 动态规划解法：
     * 设 f[i] 表示前 i 个数字字符的解码方式。
     * 决策：当 chars[i] ≠ 0时，至少有 f[i] = f[i - 1]，
     * *    当 chars[i - 1] 和 chars[i] ≤ 26 时，至少有 f[i] += f[i - 2]
     */
    public int numDecodings(String s) {
        char[] chars = s.toCharArray();
        if (chars[0] == '0') return 0;
        int[] f = new int[chars.length + 1];
        f[0] = 1;
        for (int i = 1; i < f.length; i++) {
            if (chars[i - 1] != '0') {
                f[i] = f[i - 1];
            }
            if (i > 1 && chars[i - 2] != '0' && (chars[i - 2] - '0') * 10 + chars[i - 1] - '0' <= 26) {
                f[i] += f[i - 2];
            }
        }
        return f[f.length - 1];
    }

    public int numDecodings2(String s) {
        char[] chars = s.toCharArray();
        if (chars[0] == '0') return 0;
        int far = 1;
        int prev = 1;
        for (int i = 1; i <= chars.length; i++) {
            int now = 0;
            if (chars[i - 1] != '0') {
                now = prev;
            }
            if (i > 1 && chars[i - 2] != '0' && (chars[i - 2] - '0') * 10 + chars[i - 1] - '0' <= 26) {
                now += far;
            }
            far = prev;
            prev = now;
        }
        return prev;
    }

    public int numDecodings3(String s) {
        char[] chars = s.toCharArray();
        if (chars[0] == '0') return 0;
        int far = 1;
        int prev = 1;
        for (int i = 1; i < chars.length; i++) {
            int now = 0;
            if (chars[i] != '0') {
                now = prev;
            }
            if (chars[i - 1] != '0' && (chars[i - 1] - '0') * 10 + chars[i] - '0' <= 26) {
                now += far;
            }
            far = prev;
            prev = now;
        }
        return prev;
    }

    public static void main(String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        System.out.println(decodeWays.numDecodings("11106"));
//        System.out.println(decodeWays.numDecodings("226"));
//        System.out.println(decodeWays.numDecodings("06"));
//        System.out.println(decodeWays.numDecodings("006"));
//        System.out.println(decodeWays.numDecodings("2101"));
//        System.out.println(decodeWays.numDecodings2("2101"));
//        System.out.println(decodeWays.numDecodings2("226"));
        System.out.println(decodeWays.numDecodings2("11106"));
    }
}
