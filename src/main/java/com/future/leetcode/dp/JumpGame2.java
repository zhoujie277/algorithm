package com.future.leetcode.dp;

/**
 * 跳跃游戏2
 * <p>
 * 给你一个非负整数数组nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * *    从下标为 0 跳到下标为 1 的位置，跳1步，然后跳3步到达数组的最后一个位置。
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * <p>
 * 提示:
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 1000
 * <p>
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 *
 * @author jayzhou
 */
class JumpGame2 {

    /**
     * 动态规划：反向查找
     * 先确定最接近终点的位置的最少跳跃步数，再确定前面的最少跳跃步数。
     * 设 f[i] 为表示跳跃到 终点的最少跳跃步数。
     * 决策：首先看当前位置能不能一步到位，如果不能，则从当前位置选择能跳跃的距离内，选择跳跃到终点步数最少的格子。
     */
    public int jump(int[] nums) {
        int[] f = new int[nums.length];
        int end = nums.length - 1;
        f[end] = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            int k = nums[i];
            if (i + k >= end) {
                f[i] = 1;
            } else {
                int min = Integer.MAX_VALUE - 1;
                for (int jump = i + 1; jump <= i + k; jump++) {
                    min = Math.min(min, f[jump]);
                }
                f[i] = min + 1;
            }
        }
        return f[0];
    }

    /**
     * 贪心：正向查找
     * 寻找当前能跳到的格子里，能够跳的最远的地方。
     * i == end 表示从某一个格子开始能跳到的最远的地方。在这之中寻找一个最大值。更新下一次end。
     */
    public int jump2(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        JumpGame2 jumpGame2 = new JumpGame2();
        int[] nums = new int[]{2, 3, 0, 1, 4};
//        System.out.println(jumpGame2.jump(nums));
        int[] nums2 = new int[]{2, 3, 1, 1, 4};
//        System.out.println(jumpGame2.jump(nums2));
        System.out.println(jumpGame2.jump2(nums2));
    }
}
