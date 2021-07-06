package com.future.algoriithm.sort;

import com.future.utils.ArrayUtils;

/**
 * 直接插入排序
 * 假设前面部分数组已经有序，后面遍历的数字，进行挨个比较并插入。
 * 最开始假设第一个元素已经有序，从第二个元素遍历(索引为1)，然后每比较插入后，成为了一个新的有序序列。
 * 该算法性能较高，特别适用于部分已经有序的数组。
 * 为了避免减少递归调用的损耗，在规模较小的时候适合采用插入排序
 */
public class InsertSorter {

    /**
     * 直接插入
     */
    public static void directInsert(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
                ArrayUtils.swap(array, j, j - 1);
            }
        }
    }

    /**
     * 直接插入的优化版本
     */
    public static void directInsertOpt(int[] array) {
        int temp, j;
        for (int i = 1; i < array.length; i++) {
            temp = array[i];
            for (j = i; j > 0 && temp < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            if (temp != array[i]) {
                array[j] = temp;
            }
        }
    }

    /**
     * 折半插入法
     */
    public static void binaryInsert(int[] array) {
        int temp, j;
        int low = 0, high = 0, mid = 0;
        for (int i = 1; i < array.length; i++) {
            temp = array[i];
            low = 0;
            high = i - 1;
            while (low <= high) {
                mid = (low + high) >>> 1;
                //保证稳定性，插入的值在尾端
                if (temp >= array[mid]) {
                    low = mid + 1;
                } else if (temp < array[mid]) {
                    high = mid - 1;
                }
            }
            for (j = i; j > low; j--) {
                array[j] = array[j - 1];
            }
            array[low] = temp;
        }
    }

    /**
     * 折半双插入法
     */
    public static void binaryInsertOpt(int[] array) {
        //TODO:
    }
}
