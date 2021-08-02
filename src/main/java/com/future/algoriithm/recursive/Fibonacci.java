package com.future.algoriithm.recursive;

public class Fibonacci {

    public static int recursion(int n) {
        if (n == 1) return 0;
        if (n == 2 || n == 3) return 1;
        return recursion(n - 1) + recursion(n - 2);
    }

    public static int recursionOptimize(int n) {
        if (n == 1) return 0;
        if (n == 2 || n == 3) return 1;
        int[] array = new int[n];
        array[1] = array[2] = 1;
        // 前n项，索引为n-1
        return recursion(array.length - 1, array);
    }

    private static int recursion(int i, int[] array) {
        if (array[i] == 0) {
            return recursion(i - 1, array) + recursion(i - 2, array);
        }
        return array[i];
    }

    private static int array(int n) {
        if (n == 1) return 0;
        if (n == 2 || n == 3) return 1;
        int[] array = new int[n];
        array[1] = 1;
        for (int i = 2; i < array.length; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n - 1];
    }

    private static int rollArray(int n) {
        if (n == 1) return 0;
        if (n == 2 || n == 3) return 1;
        int[] array = new int[2];
        array[1] = 1;
        for (int i = 2; i < n; i++) {
            array[i & 1] = array[(i - 1) & 1] + array[(i - 2) & 1];
        }
        return array[(n - 1) & 1];
    }

    public static int get(int n) {
        if (n == 1) return 0;
        int prev = 0;
        int cur = 1;
        int temp;
        for (int i = 2; i < n; i++) {
            temp = prev + cur;
            prev = cur;
            cur = temp;
        }
        return cur;
    }

    public static void main(String[] args) {
        int n = 7;
        int i = Fibonacci.get(n);
        System.out.println(i);

        int j = Fibonacci.recursion(n);
        System.out.println(j);

        int k = Fibonacci.recursionOptimize(n);
        System.out.println(k);

        int l = Fibonacci.array(n);
        System.out.println(l);

        int m = Fibonacci.rollArray(n);
        System.out.println(m);
    }
}
