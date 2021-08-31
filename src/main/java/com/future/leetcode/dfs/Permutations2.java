package com.future.leetcode.dfs;

import java.util.*;

/**
 * 全排列2
 * <p>
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class Permutations2 {

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    private void dfs(int[] nums, int depth, List<List<Integer>> result) {
        if (depth == nums.length) {
            List<Integer> list = new ArrayList<>(nums.length);
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }
        outer:
        for (int i = depth; i < nums.length; i++) {
            for (int j = depth; j < i; j++) {
                if (nums[i] == nums[j]) continue outer;
            }
            swap(nums, depth, i);
            dfs(nums, depth + 1, result);
            swap(nums, depth, i);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        dfs(nums, 0, result);
        return result;
    }

    public static void main(String[] args) {
        //输入：nums = [1,1,2]
        //输出：[[1,1,2],[1,2,1],[2,1,1]]
        int[] nums = new int[]{1, 2, 2};
        Permutations2 permutations2 = new Permutations2();
        System.out.println(permutations2.permuteUnique(nums));
    }
}
