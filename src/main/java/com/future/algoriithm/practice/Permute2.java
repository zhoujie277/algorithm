package com.future.algoriithm.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列2
 * <p>
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 *
 * @author jayzhou
 */
public class Permute2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
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
        outer:
        for (int i = idx; i < nums.length; i++) {
            // 去重，检查区间[idx,i)间是否已经有元素与idx交换过位置，如果有，则说明属于重复排列。
            for (int j = idx; j < i; j++) {
                if (nums[j] == nums[i]) continue outer;
            }
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

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 1, 1};
        List<List<Integer>> lists = new Permute2().permuteUnique(nums);
        System.out.println(lists);
    }
}
