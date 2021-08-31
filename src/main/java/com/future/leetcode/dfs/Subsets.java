package com.future.leetcode.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 子集
 * <p>
 * 给你一个整数数组nums ，数组中的元素互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集不能包含重复的子集。你可以按任意顺序返回解集。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 * <p>
 * 链接：https://leetcode-cn.com/problems/subsets
 *
 * @author jayzhou
 */
public class Subsets {

    private void dfs(int[] nums, int index, Deque<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        if (index == nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            path.addLast(nums[i]);
            dfs(nums, i + 1, path, result);
            path.removeLast();
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, 0, path, list);
        return list;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int[] nums1 = new int[]{0};
        Subsets subsets = new Subsets();
        System.out.println(subsets.subsets(nums));
        System.out.println(subsets.subsets(nums1));
    }
}
