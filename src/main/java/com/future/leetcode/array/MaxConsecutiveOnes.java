package com.future.leetcode.array;

/**
 * 最大连续1的个数
 * <p>
 * 给定一个二进制数组， 计算其中最大连续 1 的个数。
 * <p>
 * 示例：
 * <p>
 * 输入：[1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 * <p>
 * 提示：
 * <p>
 * 输入的数组只包含0 和 1 。
 * 输入数组的长度是正整数，且不超过 10,000。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/cd71t/
 *
 * @author jayzhou
 */
public class MaxConsecutiveOnes {

    /**
     * 动态规划优化空间复杂度
     */
    public int findMaxConsecutiveOnesDp2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int prev = 0;
        int max = prev;
        for (int num : nums) {
            prev = num == 1 ? prev + 1 : 0;
            max = Math.max(max, prev);
        }
        return max;
    }

    /**
     * 动态规划解法。
     * 以i为结尾的最大连续为1的数量，从中找到最大值。
     */
    public int findMaxConsecutiveOnesDp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 0;
        int[] dp = new int[nums.length + 1];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = nums[i - 1] == 1 ? dp[i - 1] + 1 : 0;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 0;
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] == 1) {
                slow++;
            } else {
                max = Math.max(slow, max);
                slow = 0;
            }
        }
        return Math.max(slow, max);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 0, 1, 1, 1};
        MaxConsecutiveOnes ones = new MaxConsecutiveOnes();
        System.out.println(ones.findMaxConsecutiveOnes(nums));
        System.out.println(ones.findMaxConsecutiveOnesDp(nums));
        System.out.println(ones.findMaxConsecutiveOnesDp2(nums));
    }
}
