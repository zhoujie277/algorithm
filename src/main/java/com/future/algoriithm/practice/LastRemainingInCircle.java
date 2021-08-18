package com.future.algoriithm.practice;

/**
 * 圆圈中剩下的数字
 * <p>
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。
 * 求出这个圆圈里剩下的最后一个数字。
 * <p>
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，
 * 因此最后剩下的数字是3。
 * <p>
 * 示例 1：
 * 输入: n = 5, m = 3
 * 输出:3
 * 示例 2：
 * <p>
 * 输入: n = 10, m = 17
 * 输出:2
 * <p>
 * 限制：
 * 1 <= n<= 10^5
 * 1 <= m <= 10^6
 * <p>
 * 链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
 *
 * @author jayzhou
 */
public class LastRemainingInCircle {

    /**
     * 公式： f(n, m) = (f(n-1, m) + m) % n
     */
    public int lastRemaining(int n, int m) {
        if (n <= 1) return 0;
        int res = 0;
        // i是数据规模，代表有多少个数字
        for (int i = 2; i <= n; i++) {
            res = (res + m) % i;
        }
        return res;
    }

    public int lastRemainingR(int n, int m) {
        return f(n, m);
    }

    public int f(int n, int m) {
        if (n == 1) return 0;
        return (f(n - 1, m) + m) % n;
    }

    public static void main(String[] args) {
        System.out.println(new LastRemainingInCircle().lastRemainingR(5, 3));
        System.out.println(new LastRemainingInCircle().lastRemaining(5, 3));
    }
}
