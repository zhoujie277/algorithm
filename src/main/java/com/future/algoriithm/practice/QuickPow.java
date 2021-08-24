package com.future.algoriithm.practice;

/**
 * 快速幂
 * 实现pow(x, n)，即计算 x 的 n 次幂函数（即，xn）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 * <p>
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 * <p>
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * 提示：
 * <p>
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 * <p>
 * 链接：https://leetcode-cn.com/problems/powx-n
 *
 * @author jayzhou
 */
public class QuickPow {

    /**
     * 公式：(a * b) % c = (a % c) * (b % c)
     */
    public static int powMod1(int x, int y, int z) {
        if (y < 0 || z == 0) return 0;
        int res = 1 % z;
        x %= z;
        while (y > 0) {
            if ((y & 1) == 1) {
                // 如果最后一个二进制位是1，就累乘上x
                res = (res * x) % z;
            }
            x = (x * x) % z;
            // 舍弃掉最后一个二进制位
            y >>= 1;
        }
        return res;
    }

    /**
     * 时间复杂度：O(logN)
     * 空间复杂度：O(1)
     * 3的二进制表示 = (101)
     * x ^ 3 = (x^2) * (x^1) = (x^(1 * (2^1))) * (x^(0 * (2^0))) * (x^(1 * (2^0)))
     */
    public double myPow(double x, int n) {
        long y = (n < 0) ? -((long) n) : n;
        double res = 1.0;
        while (y > 0) {
            if ((y & 1) == 1) {
                // 如果最后一个二进制位是1，就累乘上x
                res *= x;
            }
            x *= x;
            // 舍弃掉最后一个二进制位
            y >>= 1;
        }
        return (n < 0) ? (1 / res) : res;
    }

    /**
     * 自顶向下分治递归
     * 时间复杂度：O(logN)
     * 空间复杂度：O(logN)
     */
    public double myPow2(double x, int n) {
        if (n == 0) return 1;
        return myPowR(x, n);
    }

    public double myPowR(double x, int n) {
        if (n == -1) return 1 / x;
        if (n == 1) return x;
        boolean odd = (n & 1) == 1;
        double res = 1;
        if (odd) res = x;
        double half = myPowR(x, n >> 1);
        // 当n<0且为奇数时，向下取整为： 比如 -3 >> 1 = -2。
        // 当n>0且为奇数时，向下取整：比如 3 >> 1 = 1;
        // 所以最后需要用 res * half * half ( -2 + -2 + 1) = -3 或者 正数 (1 + 1 + 1) = 3
        return res * half * half;
    }

    /**
     * 时间复杂度：O(n)
     */
    private double nPow(double x, int n) {
        double res = 1;
        int c = n >= 0 ? n : -n;
        while (c-- > 0) {
            res *= x;
        }
        return n >= 0 ? res : 1 / res;
    }

    public static void main(String[] args) {
        QuickPow p = new QuickPow();
        System.out.println(-3 >> 1);
        int min = Integer.MIN_VALUE;
        System.out.println("min=" + min);
        System.out.println(Integer.toBinaryString(min));
        int n = -1 * (1 << 31);
        String s = Integer.toBinaryString(n);
        System.out.println(n);
        System.out.println(s);
        System.out.println("10000000000000000000000000000000".length());
        System.out.println(p.nPow(2, 20));
        System.out.println(p.myPow(2, 20));
        System.out.println(p.myPow2(2, n));
    }

}
