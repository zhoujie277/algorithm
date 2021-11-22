package com.future.leetcode.fifty;

/**
 * 反转一个只有3位数的整数。
 * 你可以假设输入一定是一个只有三位数的整数，这个整数大于等于100，小于1000。
 */
public class ReverseInteger {

    public int reverseInteger(int number) {
        // write your code here
        int x = 0;
        do {
            int mod = number % 10;
            x = x * 10 + mod;
            number = number / 10;
        } while (number > 0);
        return x;
    }

    public static void main(String[] args) {
        int number = 900;
        ReverseInteger reverseInteger = new ReverseInteger();
        System.out.println(reverseInteger.reverseInteger(number));
    }
}
