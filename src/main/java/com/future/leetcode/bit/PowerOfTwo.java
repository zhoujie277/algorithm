package com.future.leetcode.bit;

/**
 * 2 的 幂
 * <p>
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true；否则，返回 false 。
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 * <p>
 * 示例 1：
 * 输入：n = 1
 * 输出：true
 * 解释：20 = 1
 * 示例 2：
 * 输入：n = 16
 * 输出：true
 * 解释：24 = 16
 * 示例 3：
 * 输入：n = 3
 * 输出：false
 * 示例 4：
 * 输入：n = 4
 * 输出：true
 * 示例 5：
 * 输入：n = 5
 * 输出：false
 * 提示：
 * -2^31 <= n <= 2^31 - 1
 * 进阶：你能够不使用循环/递归解决此问题吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/power-of-two
 *
 * @author jayzhou
 */
@SuppressWarnings("unused")
public class PowerOfTwo {

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & -n) == n;
    }

    public boolean isPowerOfTwo2(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

    static final int BIG = 1 << 30;
    public boolean isPowerOfTwo3(int n) {
        return n > 0 && BIG % n == 0;
    }

    public static void main(String[] args) {
        PowerOfTwo powerOfTwo = new PowerOfTwo();
        System.out.println(powerOfTwo.isPowerOfTwo(0));
        System.out.println(powerOfTwo.isPowerOfTwo(1));
        System.out.println(powerOfTwo.isPowerOfTwo(5));
        System.out.println(powerOfTwo.isPowerOfTwo(8));
    }
}
