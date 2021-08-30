package com.future.leetcode.dp;

/**
 * 最大乘积子数组
 * <p>
 * 给你一个整数数组 nums，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释:子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释:结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 *
 * @author jayzhou
 */
public class MaximumProductSubarray {

    /**
     * 试看数组：{-2, 3, -4}
     * 最大值，应该为 24。
     * 以最大连续子数组和为基础试着分析：
     * 以 -2 为结尾的最大乘积为-2。最小乘积为 -2。
     * 以 3 为结尾的最大乘积为 3，最小乘积为 -6。
     * 以 -4 为结尾的最大乘积为：-4 * -6 = 24；
     * 可以发现。当求乘积结构时，仅仅用 f[i] = Max(f[i - 1] + nums[i], nums[i])，已不能满足最优子结构。
     * 即：无法从上一个最优解转换为当前最优解。
     * 另外，当求最大乘积时，摸索规律可以发现，可以分两种情况考虑：
     * 1、当nums[i]为负数时，那如果能求得 fMin[i - 1] 表示的最小连续子数组的乘积。即可求得以nums[i]为结尾的最大乘积。
     * 2、当nums[i]为正数时，如果能求得 fMax[i - 1] 表示的最大连续子数组的乘积，即可求得以nums[i]为结尾的最大乘积。
     * 当然，还要考虑 0 和 f[i - 1]的正负情况，这个就和连续子数组的解法一样了。
     * 所以这题的关键是要求两个，一个以 i 为结尾的连续子数组最大乘积、一个以 i 为结尾的连续子数组的最小乘积。
     */
    public int maxProduct(int[] nums) {
        int[] fMax = new int[nums.length];
        int[] fMin = new int[nums.length];
        fMax[0] = nums[0];
        fMin[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                fMax[i] = fMax[i - 1] > 0 ? fMax[i - 1] * nums[i] : nums[i];
                fMin[i] = fMin[i - 1] > 0 ? nums[i] : fMin[i - 1] * nums[i];
            } else if (nums[i] == 0) {
                fMin[i] = fMax[i] = 0;
            } else {
                fMax[i] = fMin[i - 1] > 0 ? nums[i] : fMin[i - 1] * nums[i];
                fMin[i] = fMax[i - 1] > 0 ? nums[i] * fMax[i - 1] : nums[i];
            }
            max = Math.max(fMax[i], max);
        }
        return max;
    }

    /**
     * 上面的代码简化版
     * 空间复杂度：O(1)
     */
    public int maxProductSimple(int[] nums) {
        int prevMax = nums[0];
        int prevMin = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                prevMax = Math.max(prevMax * nums[i], nums[i]);
                prevMin = Math.min(nums[i], prevMin * nums[i]);
            } else if (nums[i] == 0) {
                prevMin = prevMax = 0;
            } else {
                int nowMax = Math.max(nums[i], prevMin * nums[i]);
                prevMin = Math.min(nums[i] * prevMax, nums[i]);
                prevMax = nowMax;
            }
            max = Math.max(prevMax, max);
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumProductSubarray subarray = new MaximumProductSubarray();
        int[] nums = new int[]{2, 3, -2, 4};
        int[] nums1 = new int[]{0, 2};
        int[] nums2 = new int[]{-2, 3, -4};
        int[] nums3 = new int[]{-4, -3, -2};
        System.out.println(subarray.maxProduct(nums));
        System.out.println(subarray.maxProduct(nums1));
        System.out.println(subarray.maxProduct(nums2));
        System.out.println(subarray.maxProductSimple(nums3));
    }

}
