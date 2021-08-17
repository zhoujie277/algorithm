package com.future.algoriithm.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * @author jayzhou
 */
public class Permute {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        dfs(nums, 0, result);
        return result;
    }

    private void dfs(int[] nums, int idx, List<List<Integer>> result) {
        if (idx == nums.length) {
            List<Integer> s = new ArrayList<>(nums.length);
            for (int num : nums) {
                s.add(num);
            }
            result.add(s);
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            swap(nums, idx, i);
            dfs(nums, idx + 1, result);
            swap(nums, idx, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        List<Integer> process = Arrays.asList(new Integer[nums.length]);
        boolean[] duplicate = new boolean[nums.length];
        dfs1(nums, 0, process, duplicate, result);
        return result;
    }

    private void dfs1(int[] nums, int idx, List<Integer> process, boolean[] exists, List<List<Integer>> result) {
        if (idx == nums.length) {
            result.add(new ArrayList<>(process));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (exists[i]) continue;
            process.set(idx, num);
            exists[i] = true;
            dfs1(nums, idx + 1, process, exists, result);
            exists[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        Permute permute = new Permute();
        List<List<Integer>> p = permute.permute(nums);
        System.out.println(p);
        List<List<Integer>> permute1 = permute.permute1(nums);
        System.out.println(permute1);
    }
}
