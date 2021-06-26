package com.future.algoriithm.question;

/**
 * 约瑟夫问题
 * //TODO： 回头再研究
 */
public class JosephusProblem {

    public static void test1(int capacity, int k) {
        int count = capacity;
        while (count > 1) {
            count = count - count % k;
        }
    }

    public static void main(String[] args) {
        int capacity = 20;
        int k = 3;
        int[] a = new int[capacity];
        for (int i = k - 1; i < a.length; i = i + k) {
            a[i] = 1;
        }

        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                sum++;
            }
            System.out.print(a[i] + "\t");
        }
        System.out.printf("\n capacity=%d, k=%d, sum=%d, count=%d \n", capacity, k, sum, sum);
    }
}
