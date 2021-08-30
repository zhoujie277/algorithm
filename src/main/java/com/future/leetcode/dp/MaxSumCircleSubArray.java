package com.future.leetcode.dp;

/**
 * 环形子数组的最大和
 * <p>
 * 给定一个由整数数组 A表示的环形数组 C，求 C的非空子数组的最大可能和。
 * <p>
 * 在此处，环形数组意味着数组的末端将会与开头相连呈环状。
 * （形式上，当0 <= i < A.length 时 C[i] = A[i]，且当i >= 0时C[i+A.length] = C[i]）
 * 此外，子数组最多只能包含固定缓冲区 A中的每个元素一次。
 * （形式上，对于子数组C[i], C[i+1], ..., C[j]，不存在i <= k1, k2 <= j其中k1 % A.length= k2 % A.length）
 * <p>
 * 示例 1：
 * 输入：[1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * 示例 2：
 * 输入：[5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * 示例 3：
 * 输入：[3,-1,2,-1]
 * 输出：4
 * 解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4
 * 示例 4：
 * 输入：[3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * 示例 5：
 * 输入：[-2,-3,-1]
 * 输出：-1
 * 解释：从子数组 [-1] 得到最大和 -1
 * 提示：
 * -30000 <= A[i] <= 30000
 * 1 <= A.length <= 30000
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-sum-circular-subarray
 */
public class MaxSumCircleSubArray {

    /**
     * 在环形数组中，若求得一部分是最大连续子数组和，则另外一部分便是最小连续子数组和。
     * 分别求以 i 为结尾的最大子数组和和最小数组和。
     * 然后用总和减去最小数组和与最大子数组和比较。
     * 如果最大子数组和在数组中间，则是max。
     * 如果最小子数组和在中间，说明两端是最大连续子数组和。
     * 特殊情况：全部是负数。此时数组和最小连续子数组和相等。两数相减==0，需要特殊处理。
     */
    public int maxSubarraySumCircular(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int prev = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prev = prev > 0 ? prev + nums[i] : nums[i];
            max = Math.max(prev, max);
        }
        int min = 0;
        int sum = nums[0];
        int pred = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            pred = pred < 0 ? pred + nums[i] : nums[i];
            min = Math.min(min, pred);
        }
        if (sum == min) return max;
        return Math.max(max, sum - min);
    }

    public static void main(String[] args) {
        MaxSumCircleSubArray subArray = new MaxSumCircleSubArray();
        int[] nums = new int[]{1, -2, 3, -2};
        int[] nums1 = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = new int[]{-2,-3,-1};
        System.out.println(subArray.maxSubarraySumCircular(nums));
        System.out.println(subArray.maxSubarraySumCircular(nums1));
        System.out.println(subArray.maxSubarraySumCircular(nums2));
    }
}
