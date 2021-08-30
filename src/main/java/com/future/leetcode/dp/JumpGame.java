package com.future.leetcode.dp;

/**
 * 跳跃游戏
 * <p>
 * 给定一个非负整数数组nums ，你最初位于数组的第一个下标。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 示例1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i] <= 10^5
 * <p>
 * 链接：https://leetcode-cn.com/problems/jump-game
 *
 * @author jayzhou
 */
class JumpGame {

    public boolean canJump(int[] nums) {
        int most = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > most) break;
            most = Math.max(most, i + nums[i]);
            if (most >= nums.length - 1) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int[] ints = new int[]{2, 3, 1, 1, 4};
        int[] nums = new int[]{3, 2, 1, 0, 4};
        int[] nums1 = new int[]{3, 0, 8, 2, 0, 0, 1};
        int[] nums2 = new int[]{0};
        System.out.println(jumpGame.canJump(ints));
        System.out.println(jumpGame.canJump(nums));
        System.out.println(jumpGame.canJump(nums1));
        System.out.println(jumpGame.canJump(nums2));
    }
}
