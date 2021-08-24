package com.future.leetcode.search;

/**
 * 寻找峰值
 * <p>
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给你一个输入数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * <p>
 * 你可以假设nums[-1] = nums[n] = -∞ 。
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * 示例2：
 * <p>
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 * <p>
 * 提示：
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * <p>
 * 进阶：你可以实现时间复杂度为 O(logN) 的解决方案吗？
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xem7js/
 *
 * @author jayzhou
 */
public class FindPeakValue {

    /**
     * 上坡必有顶，如果数列本身是递增序列，则峰值就是最后一个元素。
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 0;
        int left = 0, right = nums.length - 2;
        do {
            int mid = (left + right) >>> 1;
            if (nums[mid] > nums[mid + 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } while (left <= right);
        return left;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1, 3, 5, 6, 4};
//        int[] nums = new int[]{1, 2};
        FindPeakValue findPeakValue = new FindPeakValue();
        System.out.println(findPeakValue.findPeakElement(nums));
    }
}
