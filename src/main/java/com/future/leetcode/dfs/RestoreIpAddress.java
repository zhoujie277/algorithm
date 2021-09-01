package com.future.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原IP地址。
 * <p>
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、
 * "192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * <p>
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * <p>
 * 提示：
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 * <p>
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 */
public class RestoreIpAddress {

    private static final int SEG_COUNT = 4;
    private final int[] segments = new int[SEG_COUNT];

    private void dfs(String s, int segId, int segStart, List<String> result) {
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < SEG_COUNT; i++) {
                    builder.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        builder.append(".");
                    }
                }
                result.add(builder.toString());
            }
            return;
        }
        if (segStart == s.length()) return;
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1, result);
        }
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1, result);
            } else {
                break;
            }
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> strings = new ArrayList<>();
        dfs(s, 0, 0, strings);
        return strings;
    }

    public static void main(String[] args) {

    }
}
