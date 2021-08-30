package com.future.leetcode.dp;

/**
 * 斐波那契数，通常用F(n) 表示，形成的序列称为 斐波那契数列 。该数列由0 和 1 开始，后面的每一项数字都是前面两项数字的和。
 * 也就是：
 * F(0) = 0，F(1)= 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n) 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/fibonacci-number
 *
 * @author jayzhou
 */
public class Fibonacci {

    public int fib(int n) {
        if (n < 2) return n;
        int prev = 1;
        int now = 1;
        for (int i = 2; i < n; i++) {
            int s = prev + now;
            prev = now;
            now = s;
        }
        return now;
    }

    public static void main(String[] args) {
        System.out.println(new Fibonacci().fib(4));
    }

}
