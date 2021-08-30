package com.future.leetcode.dp;

/**
 * 爬楼梯
 * <p>
 * 假设你正在爬楼梯。需要 n阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 */
public class ClimbStairs {

    /**
     * 设 f[i] 表示爬到第i个台阶的所拥有的不同方法数量。
     * 阶段：第i个台阶
     * 状态：爬到第i个台阶的方法数量。
     * 决策：当到达第 i - 2 个台阶时，可以选择一步到位，也可以选择先到 i - 1个台阶，再一步到位。
     * 策略：也就是到达第i个台阶时，等于到达第 i - 1 个台阶的方法数量与到达第 i - 2个台阶的数量之和。
     * 初始状态：f(1) = 1；f(2) = 2;
     * 状态转移方程：f[i] = f[i - 1] + f[i - 2];
     */
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int[] f = new int[n + 1];
        f[1] = 1;
        f[2] = 2;
        for (int i = 3; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    public int climbStairsDp(int n) {
        if (n <= 2) return n;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int x = second + first;
            first = second;
            second = x;
        }
        return second;
    }

    public static void main(String[] args) {
        int n = 1;
        ClimbStairs stairs = new ClimbStairs();
        System.out.println(stairs.climbStairs(n));
        System.out.println(stairs.climbStairsDp(n));
    }


}
