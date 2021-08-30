package com.future.leetcode.dp;

/**
 * 删除并获得点数
 * <p>
 * 给你一个整数数组nums，你可以对它进行一些操作。
 * 每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。之后，你必须删除所有等于nums[i] - 1
 * 和 nums[i] + 1的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * <p>
 * 示例 1：
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例2：
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 * <p>
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] <= 104
 * <p>
 * 链接：https://leetcode-cn.com/problems/delete-and-earn
 *
 * @author jayzhou
 */
public class DeleteAndEarn {

    /**
     * 计数排序思想
     * 将相同的值放同一个桶里，这样通过元素 x 当作下标，自然就能找到 x - 1, x + 1的值
     * 每个桶存放值的和。因为删除一个点数，会删除隔壁两个的点数，并不会删除自己的点数。
     * 其实换个角度想：就是选择一个点数，相邻的点数就不能选择。
     * 而最终是要获得在数组中能选择的最大点数。
     * 这时就变成了打家劫舍的问题。
     */
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        int[] sums = new int[max + 1];
        for (int val : nums) {
            sums[val] += val;
        }
        return robber(sums);
    }

    private int robber(int[] sums) {
        int secondary = 0;
        int last = sums[0];
        for (int i = 2; i <= sums.length; i++) {
            int now = Math.max(last, secondary + sums[i - 1]);
            secondary = last;
            last = now;
        }
        return last;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 3, 3, 3, 4};
        DeleteAndEarn earn = new DeleteAndEarn();
        System.out.println(earn.deleteAndEarn(nums));
    }
}
