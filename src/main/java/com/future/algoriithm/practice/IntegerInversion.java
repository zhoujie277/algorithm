package com.future.algoriithm.practice;

/**
 * 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31− 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 *
 * @author jayzhou
 */
public class IntegerInversion {

    public int reverse(int x) {
        int res = 0;
        int min = Integer.MIN_VALUE / 10;
        int max = Integer.MAX_VALUE / 10;
        while (x != 0) {
            if (res < min || res > max) return 0;
            res = res * 10 + x % 10;
            x = x / 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(-123 % 10);
        System.out.println(-123 / 10);
        int n = -2147483412;
        int reverse = new IntegerInversion().reverse(n);
        System.out.println(reverse);
    }

}
