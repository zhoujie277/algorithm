package com.future.leetcode.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 全排列
 * <p>
 * 给定一个不含重复数字的数组 nums ，返回其所有可能的全排列。你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 * <p>
 * 链接：https://leetcode-cn.com/problems/permutations
 *
 * @author jayzhou
 */
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        dfs(nums, 0, result);
        return result;
    }

    private void dfs(int[] nums, int depth, List<List<Integer>> result) {
        if (depth == nums.length) {
            List<Integer> list = new ArrayList<>(depth);
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }

        for (int i = depth; i < nums.length; i++) {
            swap(nums, depth, i);
            dfs(nums, depth + 1, result);
            swap(nums, depth, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        Deque<Integer> path = new ArrayDeque<>(nums.length);
        boolean[] used = new boolean[nums.length];
        dfs2(0, nums, used, path, result);
        return result;
    }

    private void dfs2(int depth, int[] nums, boolean[] used, Deque<Integer> path, List<List<Integer>> result) {
        if (depth == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            path.addLast(nums[i]);
            used[i] = true;
            dfs2(depth + 1, nums, used, path, result);
            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        Permutations permutations = new Permutations();
        System.out.println(permutations.permute(nums));
        System.out.println(permutations.permute2(nums));
    }
}
