package com.future.leetcode.queue;

/**
 * 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 * <p>
 * 向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 示例 2：
 * 输入：nums = [1], target = 1
 * 输出：1
 * 提示：
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/ga4o2/
 */
public class TargetSum {
    int way = 0;

    public int findTargetSumWaysDFS(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        dfs(0, target, 0, nums);
        return way;
    }

    private void dfs(int prev, int target, int index, int[] ints) {
        if (index == ints.length) {
            if (prev == target)
                way++;
            return;
        }
        dfs(prev + ints[index], target, index + 1, ints);
        dfs(prev - ints[index], target, index + 1, ints);
    }


    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        return way;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 1, 1, 1, 1};
        TargetSum targetSum = new TargetSum();
        System.out.println(targetSum.findTargetSumWays(ints, 3));
    }
}
