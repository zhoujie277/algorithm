package com.future.leetcode.dp;

/**
 * 等差数列的划分
 * <p>
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * 子数组 是数组中的一个连续序列。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 * 示例 2：
 * 输入：nums = [1]
 * 输出：0
 * 提示：
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 * <p>
 * 链接：https://leetcode-cn.com/problems/arithmetic-slices
 *
 * @author jayzhou
 */
public class ArithmeticSlices {

    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3) return 0;
        int count = 0;
        int prevCount = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                prevCount = prevCount + 1;
                count += prevCount;
            } else {
                prevCount = 0;
            }
        }
        return count;
    }

    /**
     * 第一种动态规划
     * 设 f[i] 是以 nums[i] 为结尾的等差数列的个数。
     */
    public int dynamicOne(int[] nums) {
        if (nums.length < 3) return 0;
        int count = 0;
        int[] f = new int[nums.length];
        f[0] = f[1] = 0;
        for (int i = 2; i < f.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                f[i] = f[i - 1] + 1;
                count += f[i];
            } else {
                f[i] = 0;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 3, 5, 7, 9};
        int[] nums = new int[]{1, 2, 3, 4};
        ArithmeticSlices arithmeticSlices = new ArithmeticSlices();
        System.out.println(arithmeticSlices.numberOfArithmeticSlices(ints));
        System.out.println(arithmeticSlices.numberOfArithmeticSlices(nums));
        System.out.println("---------");
        System.out.println(arithmeticSlices.dynamicOne(ints));
        System.out.println(arithmeticSlices.dynamicOne(nums));
    }
}
