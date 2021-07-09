package com.future.utils;

import com.future.datastruct.list.HashTab;

import java.util.Arrays;
import java.util.Iterator;

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

    public static void error(String s) {
        System.err.println(s);
    }

    public static <T> void println(T[] t) {
        System.out.println();
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i].toString() + " ");
        }
        System.out.println();
    }
    public static <T> void print(int[] t) {
        System.out.println(Arrays.toString(t));
    }
    public static <T> void println(int[] t) {
        System.out.println(Arrays.toString(t));
    }

    public static <K, V> void println(HashTab<K, V> table) {
        Iterator<K> iterator = table.iterator();
        while (iterator.hasNext()) {
            K next = iterator.next();
            println("key=" + next + ", value=" + table.get(next) + "\t");
        }
    }

}
