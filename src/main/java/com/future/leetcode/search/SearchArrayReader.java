package com.future.leetcode.search;

/**
 * 探索长度未知的有序数组
 * <p>
 * 给定一个升序整数数组，写一个函数搜索 nums 中数字target。如果 target 存在，返回它的下标，否则返回 -1。
 * 注意，这个数组的大小是未知的。
 * 你只可以通过 ArrayReader 接口访问这个数组，ArrayReader.get(k) 返回数组中第 k 个元素（下标从 0 开始）。
 * 你可以认为数组中所有的整数都小于 10000。如果你访问数组越界，ArrayReader.get 会返回 2147483647。
 * <p>
 * 样例 1：
 * 输入: array = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 存在在 nums 中，下标为 4
 * 样例 2：
 * <p>
 * 输入: array = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不在数组中所以返回 -1
 * <p>
 * 注释 ：
 * 你可以认为数组中所有元素的值互不相同。
 * 数组元素的值域是[-9999, 9999]。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/binary-search/xe0sq6/
 *
 * @author jayzhou
 */
@SuppressWarnings("unused")
public class SearchArrayReader {
    interface ArrayReader {
        int get(int index);
    }

    public int search(ArrayReader reader, int target) {
        int left = 0, right = Integer.MAX_VALUE;
        do {
            int mid = (left + right) >>> 1;
            int value = reader.get(mid);
            if (value == target) return mid;
            if (value == Integer.MAX_VALUE || target < value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } while (left <= right);
        return -1;
    }
}
