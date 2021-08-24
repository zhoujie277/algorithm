package com.future.leetcode.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 查找 K 个最接近的元素
 * 给定一个排序好的数组arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。
 * 返回的结果必须要是按升序排好的。
 * <p>
 * 整数 a 比整数 b 更接近 x 需要满足：
 * <p>
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 * 示例 2：
 * <p>
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 10^4
 * 数组里的每个元素与x 的绝对值不超过 10^4
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xeve4m/
 *
 * @author jayzhou
 */
class FindClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) return null;
        List<Integer> list = new ArrayList<>(k);
        if (arr.length < k) return list;
        if (arr.length == 1) {
            list.add(arr[0]);
            return list;
        }
        int left = 0, right = arr.length - k;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // left是接近于x的k个数中的最远的一个数
        for (int i = left; i < left + k; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) return null;
        List<Integer> list = new ArrayList<>(k);
        if (arr.length < k) return list;
        if (arr.length == 1) {
            list.add(arr[0]);
            return list;
        }
        int left = 0, right = arr.length - 1;
        do {
            int mid = (left + right) >>> 1;
            if (x == arr[mid]) {
                left = mid;
                break;
            }
            if (x > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } while (left <= right);

        int l = Math.max(0, left - k), r = Math.min(left + k, arr.length - 1);
        while (r - l + 1 > k) {
            if (x - arr[l] <= arr[r] - x) {
                r--;
            } else {
                l++;
            }
        }
        for (int j = l; j <= r; j++) {
            list.add(arr[j]);
        }
        return list;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 2, 3, 4, 5};
//        int[] nums = new int[]{1};
//        int[] nums = new int[]{0, 1, 1, 1, 2, 3, 6, 7, 8, 9};
        int[] nums = new int[]{0, 0, 1, 2, 3, 3, 3, 3, 4, 7, 7, 8};
        FindClosestElements closestElements = new FindClosestElements();
        System.out.println(closestElements.findClosestElements(nums, 5, 5));
        System.out.println(closestElements.findClosestElements2(nums, 5, 5));
    }
}
