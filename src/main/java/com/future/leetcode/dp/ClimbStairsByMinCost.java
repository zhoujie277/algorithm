package com.future.leetcode.dp;

/**
 * 使用最小花费爬楼梯
 * <p>
 * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值cost[i]（下标从 0 开始）。
 * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
 * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 * <p>
 * 示例1：
 * 输入：cost = [10, 15, 20]
 * 输出：15
 * 解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
 * 示例 2：
 * 输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出：6
 * 解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
 *
 * @author jayzhou
 */
class ClimbStairsByMinCost {

    /**
     * cost[i]表示第i个阶梯的花费数值。同时cost数组表示 0 - (n - 1)个阶梯的花费。则到楼顶的最小花费是 f(n)。
     * f[i] 表示楼顶为 i 的最小花费体力值，则最近的阶梯为 cost[i - 1],以及次级阶梯 cost[i - 2]
     * 状态定义：爬到楼顶为i的最小花费体力值为 f[i] = min { f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2]};
     * 决策：从最近的阶梯和次级阶梯中选择一个最小的花费。
     * 状态转移方程： f[i] = min { f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2]};
     * 初始状态： f[0] = f[1] = 0; // 第0个台阶，无意义，仅仅初始化数据。
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] f = new int[cost.length + 1];
        f[1] = 0;
        for (int i = 2; i < f.length; i++) {
            f[i] = Math.min(f[i - 1] + cost[i - 1], f[i - 2] + cost[i - 2]);
        }
        return f[cost.length];
    }

    public int minCostClimbingStairsOpt(int[] cost) {
        int secondary = 0;
        int last = 0;
        for (int i = 2; i <= cost.length; i++) {
            int now = Math.min(last + cost[i - 1], secondary + cost[i - 2]);
            secondary = last;
            last = now;
        }
        return last;
    }

    public static void main(String[] args) {
        int[] costs1 = new int[]{10, 15};
        int[] costs = new int[]{10, 15, 20};
        ClimbStairsByMinCost min = new ClimbStairsByMinCost();
        System.out.println(min.minCostClimbingStairs(costs1));
        System.out.println(min.minCostClimbingStairs(costs));
        System.out.println(min.minCostClimbingStairsOpt(costs));
    }
}




















