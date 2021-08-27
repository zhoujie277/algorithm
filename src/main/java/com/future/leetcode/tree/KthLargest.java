package com.future.leetcode.tree;

import java.util.Arrays;

/**
 * 数据流中第 k 大元素的类
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * 请实现 KthLargest类：
 * <p>
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/introduction-to-data-structure-binary-search-tree/xpjovh/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
class KthLargest {

    private int[] array = null;

    public KthLargest(int k, int[] nums) {
        array = new int[k];
        int i = 0;
        Arrays.fill(array, Integer.MIN_VALUE);
        int len = Math.min(nums.length, array.length);
        for (; i < len; i++) {
            array[i] = nums[i];
        }
        heapify();
        for (int j = i; j < nums.length; j++) {
            if (nums[j] > array[0]) {
                array[0] = nums[j];
                siftDown(0);
            }
        }
    }

    private void heapify() {
        int len = array.length >> 1;
        for (int i = len - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftUp(int index) {
        int element = array[index];
        while (index > 0) {
            int parent = index >> 1;
            if (array[parent] < element) {
                break;
            }
            array[index] = array[parent];
            index = parent;
        }
    }

    private void siftDown(int index) {
        int element = array[index];
        int half = array.length >> 1;
        while (index < half) {
            int smaller = (index << 1) + 1;
            int right = smaller + 1;
            if (right < array.length && array[smaller] > array[right]) {
                smaller = right;
            }
            if (array[smaller] >= element) {
                break;
            }
            array[index] = array[smaller];
            index = smaller;
        }
        array[index] = element;
    }

    public int add(int val) {
        if (val > array[0]) {
            array[0] = val;
            siftDown(0);
        }
        return array[0];
    }

    public static void main(String[] args) {
        //输入：
        //["KthLargest", "add", "add", "add", "add", "add"]  [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
        //输出：[null, 4, 5, 5, 8, 8]
        //解释：
        //["KthLargest","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add","add"]
        //[[7,[]],[3],[2],[3],[1],[2],[4],[5],[5],[6],[7],[7],[8],[2],[3],[1],[1],[1],[10],[11],[5],[6],[2],[4],[7],[8],[5],[6]]
        int[] ints = new int[]{-10, 1, 3, 1, 4, 10, 3, 9, 4, 5, 1};
        KthLargest kthLargest = new KthLargest(7, ints);
        System.out.println(Arrays.toString(kthLargest.array));
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(2));
        System.out.println(kthLargest.add(3));
//        System.out.println(kthLargest.add(5));
//        System.out.println(kthLargest.add(10));
//        System.out.println(kthLargest.add(9));
//        System.out.println(kthLargest.add(4));

//        KthLargest kthLargest1 = new KthLargest(2, new int[]{0});
//        System.out.println(kthLargest1.add(-1));
//        System.out.println(kthLargest1.add(1));
//        System.out.println(kthLargest1.add(-2));
//        System.out.println(kthLargest1.add(-4));
//        System.out.println(kthLargest1.add(3));
    }
}