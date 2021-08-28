package com.future.leetcode.hash;

import java.util.HashSet;

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为 1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/hash-table/xh1k9i/
 *
 * @author jayzhou
 */
public class HappyNumber {

    private int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int m = n % 10;
            sum += m * m;
            n = n / 10;
        }
        return sum;
    }

    public boolean isHappy(int n) {
        HashSet<Integer> hashSet = new HashSet<>();
        while (n != 1 && !hashSet.contains(n)) {
            hashSet.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    public static void main(String[] args) {
        HappyNumber happyNumber = new HappyNumber();
//        System.out.println(happyNumber.isHappy(19));
//        System.out.println(happyNumber.isHappy(2));
        System.out.println(happyNumber.isHappy(12));
    }

}
