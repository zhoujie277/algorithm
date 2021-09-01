package com.future.leetcode.dfs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 排序序列
 * <p>
 * 给出集合[1,2,3,...,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 示例 1：
 * 输入：n = 3, k = 3
 * 输出："213"
 * 示例 2：
 * 输入：n = 4, k = 9
 * 输出："2314"
 * 示例 3：
 * 输入：n = 3, k = 1
 * 输出："123"
 * <p>
 * 提示：
 * 1 <= n <= 9
 * 1 <= k <= n!
 * <p>
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 */
public class PermutationSequence {

    int count = 0;

    /**
     * 深度其实就是一个排列中的第几个位置
     */
    private boolean dfs(int depth, int n, int k, boolean[] seen, Deque<Integer> path) {
        if (depth == n + 1) {
            count++;
            if (count == k) {
                return true;
            }
            return false;
        }
        for (int i = 1; i <= n; i++) {
            if (seen[i]) continue;
            seen[i] = true;
            path.addLast(i);
            if (dfs(depth + 1, n, k, seen, path)) return true;
            path.removeLast();
            seen[i] = false;
        }
        return false;
    }

    public String getPermutation(int n, int k) {
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] seen = new boolean[n + 1];
        dfs(1, n, k, seen, path);
        StringBuilder builder = new StringBuilder();
        for (Integer integer : path) {
            builder.append(integer);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        PermutationSequence sequence = new PermutationSequence();
        System.out.println(sequence.getPermutation(3, 3));
    }
}
