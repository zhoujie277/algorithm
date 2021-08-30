package com.future.leetcode.dp;

/**
 * 打家劫舍
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋
 * 装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * *     偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * *    偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * <p>
 * 链接：https://leetcode-cn.com/problems/house-robber
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class HouseRobber {

    /**
     * 有i间房屋，设 f[i] 是能在到了第 i 间房屋内获取到的最大价值。
     * 阶段：准备获取第 i 间房屋的金额时。
     * 状态：获取了第 i 间房屋之后的金额，或者获取到了第 i - 1间房屋的金额。
     * 决策：是获取第 i - 1间房屋的金额，还是获取第 i 间房屋的金额？换句话说，就是选择要要i房间的金额还是不要i房间的金额
     **     如果不要第i间房屋的金额，那总金额就是前 i - 1房间金额的价值。如果拿，那就是 i - 2间房屋加上i间房屋的价值。
     * 最优策略：在两者做出选择之后，选择能获取金额最大的，
     * 状态转移方程：f[i] = max { f[i - 1], f[i - 2] + cost[i - 1] };
     */
    public int rob(int[] nums) {
        int[] f = new int[nums.length + 1];
        f[0] = 0;
        f[1] = nums[0];
        for (int i = 2; i < f.length; i++) {
            f[i] = Math.max(f[i - 1], f[i - 2] + nums[i - 1]);
        }
        return f[nums.length];
    }

    public int robOpt(int[] nums) {
        int secondary = 0;
        int last = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            int now = Math.max(last, secondary + nums[i - 1]);
            secondary = last;
            last = now;
        }
        return last;
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 7, 9, 3, 1};
        int[] array1 = new int[]{2, 7};
        HouseRobber houseRobber = new HouseRobber();
        System.out.println(houseRobber.rob(array));
        System.out.println(houseRobber.rob(array1));
    }
}
