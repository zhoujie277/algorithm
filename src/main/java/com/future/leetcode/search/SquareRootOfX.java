package com.future.leetcode.search;

/**
 * x 的平方根
 * 实现int sqrt(int x)函数。
 * <p>
 * 计算并返回x的平方根，其中x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xe9cog/
 *
 * @author jayzhou
 */
class SquareRootOfX {

    // 返回整数部分
    public int mySqrt(int x) {
        if (x <= 1) return x;
        int left = 0, right = x >> 1;
        do {
            int mid = (left + right) >> 1;
            long square = (long) mid * mid;
            if (square == x) return mid;
            if (square > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } while (left <= right);
        return right;
    }

    public static void main(String[] args) {
        int x = 2147395599;
        System.out.println(new SquareRootOfX().mySqrt(x));
    }
}
