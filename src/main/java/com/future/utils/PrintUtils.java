package com.future.utils;

import com.future.datastruct.map.HashTable;

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

    public static void red(Object s) {
        // ANSI 转义序列
        System.out.print("\u001b[91m");
        System.out.print(s);
        System.out.print("\u001b[0m");
    }

    public static void info(Object s) {
        System.out.print("\u001b[32m");
        System.out.println(s);
        System.out.print("\u001b[0m");
    }

    public static void debug(Object s) {
        System.out.print("\u001b[36m");
        System.out.println(s);
        System.out.print("\u001b[0m");
    }

    public static void warning(Object s) {
        System.out.print("\u001b[93m");
        System.out.println(s);
        System.out.print("\u001b[0m");
    }

    public static void error(Object s) {
        System.out.print("\u001b[91m");
        System.out.println(s);
        System.out.print("\u001b[0m");
    }

    public static void printKey(Object obj, String key) {
        String str = obj.toString();
        int cursor = 0;
        int index = 0;
        while (cursor < str.length() && (index = str.indexOf(key, cursor)) >= 0) {
            System.out.print(str.substring(cursor, index));
            System.out.print("\u001b[36m");
            System.out.print(key);
            System.out.print("\u001b[0m");
            cursor = index + key.length();
        }
        System.out.print(str.substring(cursor));
        System.out.println();
    }

    private static void str(String str, String subStr) {
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

    public static <K, V> void println(HashTable<K, V> table) {
        Iterator<K> iterator = table.iterator();
        while (iterator.hasNext()) {
            K next = iterator.next();
            println("key=" + next + ", value=" + table.get(next) + "\t");
        }
    }

}
