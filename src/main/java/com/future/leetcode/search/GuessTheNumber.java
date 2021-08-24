package com.future.leetcode.search;

/**
 * 猜数字游戏的规则如下：
 * <p>
 * 每轮游戏，我都会从1到n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1或 0）：
 * <p>
 * -1：我选出的数字比你猜的数字小 pick < num
 * 1：我选出的数字比你猜的数字大 pick > num
 * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
 * 返回我选出的数字。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10, pick = 6
 * 输出：6
 * 示例 2：
 * <p>
 * 输入：n = 1, pick = 1
 * 输出：1
 * <p>
 * 1 <= n <= 2^31 - 1
 * 1 <= pick <= n
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xee4ev/
 *
 * @author jayzhou
 */
class GuessTheNumber {
    private int guess(int num) {
        return Integer.compare(5, num);
    }

    public int guessNumber(int n) {
        if (n <= 1) return n;
        int left = 0, right = n;
        do {
            int mid = (left + right) >>> 1;
            int res = guess(mid);
            if (res == 0) return mid;
            if (res > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } while (left <= right);
        return -1;
    }

    public static void main(String[] args) {
        GuessTheNumber guessTheNumber = new GuessTheNumber();
        System.out.println(guessTheNumber.guessNumber(10));
    }
}
