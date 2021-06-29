package com.future.algoriithm.sort;

/**
 * 归并排序
 * 源于分治思想
 * 是一种稳定的排序算法，在对象排序算法里通常作为首选算法
 * 平均时间复杂度nlogn。
 */
public class MergeSorter {

    public static void topDownSort(int[] array) {
        recursive(array, 0, array.length - 1);
    }

    private static void recursive(int[] array, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) >>> 1;
        recursive(array, left, mid);
        recursive(array, mid + 1, right);
        int[] result = new int[array.length];
        merge(array, left, mid, right, result);
    }

    public static void bottomUpSort(int[] array) {
        int[] result = new int[array.length];
        for (int i = 1; i < array.length; i <<= 1) {
            for (int j = 0; j < array.length - i; j += (i << 1)) {
                int mid = j + i - 1;
                int right = Math.min(j + (i << 1) - 1, array.length - 1);
                merge(array, j, mid, right, result);
            }
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int m = left, l = left, r = mid + 1;
        while (l <= mid && r <= right) {
            temp[m++] = arr[l] <= arr[r] ? arr[l++] : arr[r++];
        }
        while (l <= mid) {
            temp[m++] = arr[l++];
        }
        while (r <= right) {
            temp[m++] = arr[r++];
        }
        int k = left;
        for (int i = left; i < m; i++) {
            arr[k++] = temp[i];
        }
    }
}
