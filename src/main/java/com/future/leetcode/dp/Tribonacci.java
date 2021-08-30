package com.future.leetcode.dp;

/**
 * 泰波那契序列Tn定义如下：
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数n，请返回第 n 个泰波那契数Tn 的值。
 * <p>
 * 链接：https://leetcode-cn.com/problems/n-th-tribonacci-number
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class Tribonacci {
    public int tribonacci(int n) {
        if (n < 2) return n;
        int t0 = 0;
        int t1 = 1;
        int t2 = 1;
        for (int i = 3; i <= n; i++) {
            int t3 = t0 + t1 + t2;
            t0 = t1;
            t1 = t2;
            t2 = t3;
        }
        return t2;
    }

    public static void main(String[] args) {
        //输入：n = 4
        //输出：4
        //输入：n = 25
        //输出：1389537
        Tribonacci tribonacci = new Tribonacci();
        System.out.println(tribonacci.tribonacci(4));
        System.out.println(tribonacci.tribonacci(25));
    }
}
