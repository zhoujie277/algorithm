package com.future.utils;

/**
 * 数字工具类
 *
 * @author jayzhou
 */
public class NumberUtils {
    /**
     * 找出距离给定值最近的2的幂
     */
    public static int findLastBinary(int n) {
        n = n - 1;
        n |= (n >>> 1);
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    public static void main(String[] args) {
        int lastBinary = findLastBinary(7);
        System.out.println(lastBinary);
    }
}
