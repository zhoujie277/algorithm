package com.future.leetcode.dfs;

import java.util.*;

/**
 * 组合总和
 * <p>
 * 给定一个无重复元素的正整数数组candidates和一个正整数target，找出candidates中所有可以使数字和为目标数target的唯一组合。
 * candidates中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
 * 对于给定的输入，保证和为target 的唯一组合数少于 150 个。
 * <p>
 * 示例1：
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[7],[2,2,3]]
 * 示例2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 * 示例 4：
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 * 示例 5：
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 * <p>
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 * <p>
 * 链接：https://leetcode-cn.com/problems/combination-sum
 */
public class CombinationSum {

    private void dfs(int[] candidates, int index, int prevSum, int target, Deque<Integer> path, List<List<Integer>> list) {
        if (prevSum == target) {
            list.add(new ArrayList<>(path));
            return;
        }
        int curSum;
        for (int i = index; i < candidates.length && (curSum = prevSum + candidates[i]) <= target; i++) {
            path.addLast(candidates[i]);
            // 从上次的索引开始dfs
            dfs(candidates, i, curSum, target, path, list);
            path.removeLast();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates);
        dfs(candidates, 0, 0, target, path, list);
        return list;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{2, 3, 6, 7};
        CombinationSum sum = new CombinationSum();
        System.out.println(sum.combinationSum(ints, 7));
    }
}
