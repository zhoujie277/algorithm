package com.future.algoriithm.search;

import com.future.utils.ArrayUtils;

public class FibonacciSearch {

    public static int fib(int k) {
        if (k <= 2) return 1;
        int first = 1, second = 1;
        int m = 3;
        int next = 0;
        while (m <= k) {
            next = first + second;
            first = second;
            second = next;
            m++;
        }
        return next;
    }

    public static int search(int[] array, int target) {
        int k = 1;
        int left = 0, mid;
        int right = array.length - 1;
        int fibLength;
        while (right > (fibLength = fib(k) - 1)) {
            k++;
        }
        // 扩充数组
        int[] temp = new int[fibLength];
        ArrayUtils.fill(array, array[right]);
        ArrayUtils.copy(array, temp);

        while (left <= right) {
            mid = left + fib(k - 1) - 1;
            if (array[mid] == target) {
                return mid < right ? mid : right;
            }
            if (target < array[mid]) {
                right = mid - 1;
                k--;
            } else {
                left = mid + 1;
                k -= 2;
            }
        }
        return left;
    }
}
