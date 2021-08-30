package com.future.leetcode.dp;

/**
 * 乘积为正数的最长子数组长度
 * <p>
 * 给你一个整数数组 nums，请你求出乘积为正数的最长子数组的长度。
 * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
 * 请你返回乘积为正数的最长子数组长度。
 * <p>
 * 示例 1：
 * 输入：nums = [1,-2,-3,4]
 * 输出：4
 * 解释：数组本身乘积就是正数，值为 24 。
 * 示例 2：
 * 输入：nums = [0,1,-2,-3,-4]
 * 输出：3
 * 解释：最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。
 * 注意，我们不能把 0 也包括到子数组中，因为这样乘积为 0 ，不是正数。
 * 示例 3：
 * 输入：nums = [-1,-2,-3,0,1]
 * 输出：2
 * 解释：乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。
 * 示例 4：
 * 输入：nums = [-1,2]
 * 输出：1
 * 示例 5：
 * 输入：nums = [1,2,3,5,-6,4,0,10]
 * 输出：4
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i]<= 10^9
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-subarray-with-positive-product
 *
 * @author jayzhou
 */
public class MaximumPositiveProductSubarray {

    /**
     * 阶段和状态定义：设 positive 表示以nums[i]为结尾的乘积为正数的最大长度
     * *            设 negative 表示以nums[i]为结尾的乘积为负数的最大长度。
     * 决策：当nums[i] = 0 时，positive[i] = negative[i] = 0;
     * *    当nums[i] > 0 时，positive[i] = positive[i - 1] + 1;
     * *                  negative[i] = negative[i - 1] > 0 ? negative[i - 1] + 1 : 0;
     * *    当nums[i] < 0 时，negative[i] = positive[i - 1] + 1;
     * *                 positive[i] = negative[i - 1] > 0 ? negative[i - 1] + 1 : 0;
     * * 此处说明一下：长度只能大于等于0。负数长度等于0的说明，前面的乘积是正数。正数长度等于0说明，前面的乘积是负数。
     * *
     */
    public int getMaxLen(int[] nums) {
        int[] positive = new int[nums.length];
        int[] negative = new int[nums.length];
        positive[0] = negative[0] = 0;
        if (nums[0] > 0) {
            positive[0] = 1;
        } else if (nums[0] < 0) {
            negative[0] = 1;
        }
        int maxLength = positive[0];
        for (int i = 1; i < positive.length; i++) {
            if (nums[i] > 0) {
                positive[i] = positive[i - 1] + 1;
                negative[i] = negative[i - 1] > 0 ? negative[i - 1] + 1 : 0;
            } else if (nums[i] < 0) {
                positive[i] = negative[i - 1] > 0 ? negative[i - 1] + 1 : 0;
                negative[i] = positive[i - 1] + 1;
            } else {
                positive[i] = negative[i] = 0;
            }
            maxLength = Math.max(maxLength, positive[i]);
        }
        return maxLength;
    }

    public int getMaxLenOpt(int[] nums) {
        int positiveLength = 0;
        int negativeLength = 0;
        if (nums[0] > 0) {
            positiveLength = 1;
        } else if (nums[0] < 0) {
            negativeLength = 1;
        }
        int maxLength = positiveLength;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                positiveLength = positiveLength + 1;
                negativeLength = negativeLength > 0 ? negativeLength + 1 : 0;
            } else if (nums[i] < 0) {
                int prevPositiveLength = positiveLength;
                positiveLength = negativeLength > 0 ? negativeLength + 1 : 0;
                negativeLength = prevPositiveLength + 1;
            } else {
                positiveLength = negativeLength = 0;
            }
            maxLength = Math.max(maxLength, positiveLength);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        MaximumPositiveProductSubarray subarray = new MaximumPositiveProductSubarray();
        int[] nums = new int[]{1, -2, -3, 4};
        int[] nums1 = new int[]{0, 1, -2, -3, -4};
        int[] nums2 = new int[]{0, 0, 0, 0, 0};
        System.out.println(subarray.getMaxLen(nums));
        System.out.println(subarray.getMaxLen(nums1));
        System.out.println(subarray.getMaxLen(nums2));
        System.out.println(subarray.getMaxLenOpt(nums));
        System.out.println(subarray.getMaxLenOpt(nums1));
        System.out.println(subarray.getMaxLenOpt(nums2));
    }
}
