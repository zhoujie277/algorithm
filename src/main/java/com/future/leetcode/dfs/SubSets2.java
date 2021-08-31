package com.future.leetcode.dfs;

import java.util.*;

/**
 * 子集2
 * <p>
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * <p>
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 *
 * @author jayzhou
 */
public class SubSets2 {

    private void dfs(int[] nums, int index, Deque<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        if (index == nums.length) return;
        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i - 1]) continue;
            path.addLast(nums[i]);
            dfs(nums, i + 1, path, result);
            path.removeLast();
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, 0, path, result);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2};
        SubSets2 sets2 = new SubSets2();
        System.out.println(sets2.subsetsWithDup(nums));
    }
}
