package com.future.algoriithm;

import java.util.Arrays;

public class PrintUtils {
    public static void print(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void println() {
        System.out.println();
    }

    public static void println(Object s) {
        System.out.println(s);
    }

    public static void print(Object s) {
        System.out.print(s);
    }

    public static void printf(String s, Object... args) {
        System.out.printf(s, args);
    }

    public static <T> void print(T[] t) {
        System.out.println(Arrays.toString(t));
    }
}
