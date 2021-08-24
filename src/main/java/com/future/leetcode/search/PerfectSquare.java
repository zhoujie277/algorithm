package com.future.leetcode.search;

/**
 * 有效的完全平方数
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xel3tc/
 *
 * @author jayzhou
 */
class PerfectSquare {

    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        int left = 0, right = num >> 1;
        long square;
        do {
            int mid = (left + right) >>> 1;
            if ((square = (long) mid * mid) == num) return true;
            if (square > num) right = mid - 1;
            else left = mid + 1;
        } while (left <= right);
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new PerfectSquare().isPerfectSquare(808201));
    }
}
