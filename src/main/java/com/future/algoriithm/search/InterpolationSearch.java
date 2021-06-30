package com.future.algoriithm.search;

/**
 * 插值查找
 * 适用于分布均匀的数组列表，在分布不均匀不一定比二分快
 */
public class InterpolationSearch {

    public static int search(int[] array, int target) {
        int left = 0, right = array.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) * (target - array[left]) / array[right] - array[left];
            if (mid < 0 || mid >= array.length) {
                mid = - 1;
                break;
            }
            if (target == array[mid]) break;
            if (target > array[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ~mid;
    }
}
