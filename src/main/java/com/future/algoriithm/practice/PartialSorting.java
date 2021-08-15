package com.future.algoriithm.practice;

import com.future.utils.PrintUtils;

/**
 * 部分排序
 * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小。
 * 也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
 * <p>
 * 示例：
 * 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
 * 输出： [3,9]
 * 提示：
 * 0 <= len(array) <= 1000000
 * 链接：https://leetcode-cn.com/problems/sub-sort-lcci
 *
 * @author jayzhou
 */
public class PartialSorting {
    /**
     * 要求时间复杂度：O(n)
     */
    public int[] subSort(int[] array) {
        if (array == null || array.length <= 1) return new int[]{-1, -1};
        int len = array.length;
        int max = array[0], min = array[len - 1];
        int farMaxInversePair = -1, farMinInversePair = -1;
        for (int i = 0; i < len; i++) {
            int j = len - i - 1;
            if (array[i] >= max) {
                max = array[i];
            } else {
                farMaxInversePair = i;
            }
            if (array[j] <= min) {
                min = array[j];
            } else {
                farMinInversePair = j;
            }
        }
        return new int[]{farMinInversePair, farMaxInversePair};
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        int[] ints = new PartialSorting().subSort(array);
        PrintUtils.println(ints);
    }
}
