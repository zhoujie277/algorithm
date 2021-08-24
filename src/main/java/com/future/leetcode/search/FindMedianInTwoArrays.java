package com.future.leetcode.search;

/**
 * 寻找两个正序数组的中位数
 * <p>
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 * <p>
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xe6jas/
 *
 * @author jayzhou
 */
public class FindMedianInTwoArrays {

    /**
     * 二分法的典型应用。
     * 两个有序数组，看成是一个有序数组，
     * 取有序数组中的最小值（首位元素比较可得到）和最大值。
     * 然后取得平均值。
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return 0;

        return 0;
    }

    /**
     * 空间复杂度：O(1)
     * 时间复杂度：O(m+n)
     */
    public double merge2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return 0;
        int l = 0, r = 0;
        int endIndex = (nums1.length + nums2.length) >>> 1;
        int preValue;
        int nowValue = 0;
        int index = 0;
        do {
            preValue = nowValue;
            if (l < nums1.length && r < nums2.length) {
                if (nums1[l] < nums2[r]) {
                    nowValue = nums1[l];
                    l++;
                } else {
                    nowValue = nums2[r];
                    r++;
                }
            } else if (l < nums1.length) {
                nowValue = nums1[l];
                l++;
            } else if (r < nums2.length) {
                nowValue = nums2[r];
                r++;
            }
            index++;
        } while (index <= endIndex);
        boolean odd = ((nums1.length + nums2.length) & 1) == 1;
        return odd ? nowValue : (preValue + nowValue) / 2d;
    }

    /**
     * 时间复杂度：O(m+n)
     * 空间复杂度:O(m+n)
     */
    public double merge(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return 0;
        int l = 0, r = 0;
        int[] tmp = new int[nums1.length + nums2.length];
        int endIndex = tmp.length >> 1;
        int index = 0;
        do {
            if (l < nums1.length && r < nums2.length) {
                tmp[index++] = (nums1[l] < nums2[r]) ? nums1[l++] : nums2[r++];
            } else if (l < nums1.length) {
                tmp[index++] = nums1[l++];
            } else if (r < nums2.length) {
                tmp[index++] = nums2[r++];
            }
        } while (index <= endIndex);
        boolean odd = (tmp.length & 1) == 1;
        return odd ? tmp[endIndex] : (tmp[endIndex] + tmp[endIndex - 1]) / 2d;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        FindMedianInTwoArrays arrays = new FindMedianInTwoArrays();
        System.out.println(arrays.findMedianSortedArrays(nums1, nums2));
        System.out.println(arrays.merge(nums1, nums2));
        System.out.println(arrays.merge2(nums1, nums2));

    }
}
