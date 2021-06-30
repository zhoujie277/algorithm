package com.future.algoriithm.search;

import com.sun.xml.internal.org.jvnet.mimepull.CleanUpExecutorFactory;

/**
 * 二分查找
 * 二分查找可以准确的找到数组元素应该所在的位置，即便没有找到
 */
public class BinarySearch {

    public static int search(int[] array, int target) {
        int left = 0, right = array.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (target == array[mid]) return mid;
            if (target > array[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ~left;
    }

    public static int searchRecursive(int[] array, int target) {
        return recursive(array, 0, array.length - 1, target);
    }

    private static int recursive(int[] array, int left, int right, int target) {
        if (left > right) return ~left;
        int mid = (left + right) >>> 1;
        if (target == array[mid]) return mid;
        if (target > array[mid]) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
        return recursive(array, left, right, target);
    }
}
