package com.future.leetcode.search;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 两个数组的交集2
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 * <p>
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * <p>
 * 进阶：
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果nums1的大小比nums2小很多，哪种方法更优？
 * 如果nums2的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xe3pwj/
 *
 * @author jayzhou
 */
public class ArrayIntersection2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>(nums1.length);
        for (int i = 0; i < nums1.length; i++) {
            Integer count = map.getOrDefault(nums1[i], 0);
            map.put(nums1[i], ++count);
        }
        int[] answer = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        for (int i = 0; i < nums2.length; i++) {
            Integer integer = map.getOrDefault(nums2[i], 0);
            if (integer > 0) {
                answer[index++] = nums2[i];
                map.put(nums2[i], --integer);
            }
        }
        return Arrays.copyOfRange(answer, 0, index);
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] answer = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                answer[index++] = nums1[i];
                i++;
                j++;
            }
        }
        return Arrays.copyOfRange(answer, 0, index);
    }

    /**
     * 时间复杂度：O(MLogM + NLogN).其中M和N分别数组的长度
     */
    public int[] intersect1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return null;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] answer = new int[Math.min(nums1.length, nums2.length)];
        int index = 0;
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            while (i < nums1.length && nums1[i] < nums2[j]) {
                i++;
            }
            if (i >= nums1.length) break;
            while (j < nums2.length && nums2[j] < nums1[i]) {
                j++;
            }
            if (j >= nums2.length) break;
            if (nums1[i] == nums2[j]) {
                answer[index++] = nums1[i];
                i++;
                j++;
            }
        }
        return Arrays.copyOfRange(answer, 0, index);
    }

    public static void main(String[] args) {
        int[] num1 = new int[]{4, 9, 5};
        int[] num2 = new int[]{9, 4, 9, 8, 4};
        ArrayIntersection2 intersection = new ArrayIntersection2();
        System.out.println(Arrays.toString(intersection.intersect(num1, num2)));
    }
}
