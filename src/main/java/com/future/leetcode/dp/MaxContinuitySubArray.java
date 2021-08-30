package com.future.leetcode.dp;

/**
 * 最大子序和
 * <p>
 * 给定一个整数数组 nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组[4,-1,2,1] 的和最大，为6 。
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 * 输入：nums = [-100000]
 * 输出：-100000
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * -10^5 <= nums[i] <= 10^5
 * <p>
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
class MaxContinuitySubArray {

    private static class Status {
        int lSum; // 表示[l,r]区间内以 l 为左端点的最大子段和
        int rSum; // 表示[l,r]区间内以 r 为右端点的最大子段和
        int mSum; // 表示[l,r]区间内的最大子段和
        int iSum; // 表示[l,r]的区间和

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    private Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, l.rSum + r.iSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    /**
     * 时间复杂度：O(n)  =>  Tn = 2 * T(n/2) + O(1)
     * 空间复杂度： O(logn)
     */
    private Status divide(int[] nums, int left, int right) {
        if (left == right) {
            return new Status(nums[left], nums[left], nums[left], nums[left]);
        }
        int mid = (left + right) >> 1;
        Status l = divide(nums, left, mid);
        Status r = divide(nums, mid + 1, right);
        return pushUp(l, r);
    }

    /**
     * 分治法
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return divide(nums, 0, nums.length - 1).mSum;
    }

    /**
     * 设 f[i] 是以nums[i]为结尾的最大连续子数组的和。
     * 阶段：以nums[i]为结尾的子数组
     * 状态：以nums[i]为结尾的最大连续子数组的和。
     * 决策：到了nums[i]的时候，是否要将值加到f[i-1]以构成一个新的最大值。
     * 策略：f[i-1]+nums[i]构成一个新状态或者 nums[i]就作为f[i]的最大值的新状态。取决于f[i-1]是否大于0。
     * 状态转移方程：f[i] = f[i-1] > 0 ? f[i-1]+nums[i] : nums[i];
     * 初始化状态：f[0] = nums[0];
     */
    public int maxSubArrayDp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] f = new int[nums.length];
        f[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < f.length; i++) {
            f[i] = f[i - 1] > 0 ? f[i - 1] + nums[i] : nums[i];
            max = Math.max(f[i], max);
        }
        return max;
    }

    /**
     * 优化
     */
    public int maxSubArrayOpt(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int prev = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prev = prev > 0 ? prev + nums[i] : nums[i];
            max = Math.max(prev, max);
        }
        return max;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        MaxContinuitySubArray subArray = new MaxContinuitySubArray();
        System.out.println(subArray.maxSubArray(nums));
        System.out.println(subArray.maxSubArrayOpt(nums));
    }
}
