package com.future.leetcode.search;

/**
 * 山脉数组的峰顶索引
 * <p>
 * 符合下列属性的数组 arr 称为 山脉数组 ：
 * arr.length >= 3
 * 存在 i（0 < i< arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给你由整数组成的山脉数组 arr ，返回任何满足
 * arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
 * <p>
 * 示例 1：
 * 输入：arr = [0,1,0]
 * 输出：1
 * 示例 2：
 * 输入：arr = [0,2,1,0]
 * 输出：1
 * 示例 3：
 * 输入：arr = [0,10,5,2]
 * 输出：1
 * 示例 4：
 * 输入：arr = [3,4,5,1]
 * 输出：2
 * 示例 5：
 * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
 * 输出：2
 *
 * @author jayzhou
 */
public class MountainPeak {

    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        do {
            int mid = (left + right) >>> 1;
            if (arr[mid + 1] > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        } while (left <= right);
        return left;
    }

    public static void main(String[] args) {
        int[] mid = new int[]{24, 69, 100, 99, 79, 78, 67, 36, 26, 19};
        int[] arr1 = new int[]{0, 2, 1, 0};
        MountainPeak mountainPeak = new MountainPeak();
        System.out.println(mountainPeak.peakIndexInMountainArray(mid));
        System.out.println(mountainPeak.peakIndexInMountainArray(arr1));
    }
}
