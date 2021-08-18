package com.future.algoriithm.practice;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两数之和
 * <p>
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target的那两个整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/two-sum
 *
 * @author jayzhou
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int x = target - num;
            Integer idx = map.get(x);
            if (idx != null) {
                return new int[]{idx, i};
            }
            map.put(num, i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int[] ints = new TwoSum().twoSum(nums, 9);
        System.out.println(Arrays.toString(ints));
    }
}
