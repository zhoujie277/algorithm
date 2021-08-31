package com.future.leetcode.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 组合
 * <p>
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * * [
 * *   [2,4],
 * *   [3,4],
 * *   [2,3],
 * *   [1,2],
 * *   [1,3],
 * *   [1,4],
 * * ]
 * 示例 2：
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= n <= 20
 * 1 <= k <= n
 * <p>
 * 链接：https://leetcode-cn.com/problems/combinations
 *
 * @author jayzhou
 */
public class Combinations {

    private void dfs(int depth, int n, int k, Deque<Integer> path, List<List<Integer>> result) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        // n - (k - path.size()) + 1
        for (int i = depth; i <= n; i++) {
            path.addLast(i);
            dfs(i + 1, n, k, path, result);
            path.removeLast();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(1, n, k, path, lists);
        return lists;
    }

    public static void main(String[] args) {
        Combinations combinations = new Combinations();
        System.out.println(combinations.combine(4, 2));
    }
}
