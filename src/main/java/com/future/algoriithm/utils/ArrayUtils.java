package com.future.algoriithm.utils;

public class ArrayUtils {
    public static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void copy(int[] src, int[] dst) {
        for (int i = 0; i < src.length; i++) {
            dst[i] = src[i];
        }
    }

    public static <T> void copy(T[] src, T[] dst) {
        for (int i = 0; i < src.length; i++) {
            dst[i] = src[i];
        }
    }

    public static int max(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }

    public static int min(int[] array) {
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
        }
        return min;
    }

    public static int max(Sortable[] array) {
        int max = array[0].key();
        for (int i = 1; i < array.length; i++) {
            if (max < array[i].key()) {
                max = array[i].key();
            }
        }
        return max;
    }

    public static int min(Sortable[] array) {
        int min = array[0].key();
        for (int i = 0; i < array.length; i++) {
            if (min > array[i].key()) {
                min = array[i].key();
            }
        }
        return min;
    }

    public static int minOrMax(Sortable[] array, int keyIndex, boolean max) {
        int result = array[0].keyAt(keyIndex);
        for (int i = 0; i < array.length; i++) {
            int key = array[i].keyAt(keyIndex);
            result = max ? Math.max(result, key): Math.min(result, key);
        }
        return result;
    }

    public static int findMaxLength(String[] array) {
        int max = array[0].length();
        for (int i = 1; i < array.length; i++) {
            if (max < array[i].length()) {
                max = array[i].length();
            }
        }
        return max;
    }

    public static void fill(int[] array ,int value) {
        for (int i = 0; i < array.length; i++) {
            array[i] = value;
        }
    }
}
