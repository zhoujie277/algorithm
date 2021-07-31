package com.future.utils;

import java.util.Arrays;

@SuppressWarnings("all")
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
            result = max ? Math.max(result, key) : Math.min(result, key);
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

    public static void fill(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            array[i] = value;
        }
    }

    public static Integer[] wrap(int[] array) {
        return Arrays.stream(array).boxed().toArray(Integer[]::new);
    }

    /**
     * 二分查找
     * [low, high] 左闭右闭的数据区间，当 low > high即结束
     * 这种二分查找方式比[left,right)左闭右开的方式的好处在于
     * 返回值可以返回目标元素应该插入的位置
     */
    public static <E extends Comparable<E>> int binarySearch(E[] elements, E value) {
        int low = 0, high = elements.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (elements[mid] == value) return mid;
            if (value.compareTo(elements[mid]) < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ~low;
    }
}
