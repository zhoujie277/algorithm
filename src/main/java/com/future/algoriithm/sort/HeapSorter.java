package com.future.algoriithm.sort;

import com.future.utils.ArrayUtils;

/**
 * 堆排序
 * 把数组视为一个完全二叉树
 * 设索引为k，根节点root，左节点left，右节点right。则满足下列式子
 * 若 root = k;
 * root.left = 2 * k + 1;
 * root.right = 2 * k + 2;
 * 且 若子节点索引为m，则根节点索引为
 * root = (m - 1) / 2;
 * <p>
 * 堆排序其实是一种选择排序，只是它不是线性遍历的选择，而是以二叉树的遍历选择。
 * <p>
 * 算法步骤：
 * 1、先从非叶子节点开始反向遍历，构造一个大顶堆。每次比较完当前单位堆的大小后，需要往后面的堆继续调整堆。
 * 2、构造大顶堆之后，第一个元素会是最大值，此时，将第一个值与数组末尾值相交换，长度减一，继续调整堆。
 * 3、所谓调整堆就是指：原本是大顶堆结构的二叉树，突然间某个节点被替换，此时需要和左右节点比较交换。交换完之后，继续会交换后的节点所在的堆进行调整。
 * 调整堆的好处是，假设k为调整堆的首个索引，那么影响到的只会是2k+1的这种节点，大大的减小了重新比较和交换的重排序次数。
 * <p>
 * 所以，该算法最后的时间复杂度，设数组长度为n，则会进行1次建堆过程，n-1次遍历调整堆过程。
 * 每次调整堆的次数都为 log2n。所以时间复杂度为 nlog2n
 */
public class HeapSorter<E extends Comparable<E>> extends Sorter<E> {

    @Override
    protected void sort() {
        int len = elements.length;
//        heapify
        for (int i = (len >> 1) - 1; i >= 0; i--) {
            siftDown(i, len);
        }
        while (len > 1) {
            swap(--len, 0);
            siftDown(0, len);
        }
    }

    private void siftDown(int index, int len) {
        E element = elements[index];
        int half = (len >> 1);
        while (index < half) {
            int largest = (index << 1) + 1;
            int right = largest + 1;
            if (right < len && compare(elements[largest], elements[right]) < 0) {
                largest = right;
            }
            if (compare(elements[largest], element) < 0) {
                break;
            }
            elements[index] = elements[largest];
            index = largest;
        }
        elements[index] = element;
    }

    /**
     * 这个方法仅仅是用堆思想优化过后的选择排序
     * 本质上这种算法还是选择排序，并且交换次数远超普通选择排序
     * 时间频度为 n2/4 - (3/4)n,时间复杂度还是 O(n2)
     * 第一次选最大值，遍历了 n /2 -1次
     * 第二次选最大值，遍历了 (n-1) /2 -1次
     * ...
     */
    @SuppressWarnings("unused")
    public static void slowSort(int[] array) {
        int len = array.length;
        // 从子树开始遍历
        for (int k = len; k > 0; k--) {
            for (int i = k / 2 - 1; i >= 0; i--) {
                int leftIndex = 2 * i + 1;
                int right = 0;
                if (leftIndex + 1 < k) {
                    right = array[leftIndex + 1];
                }
                int maxIndex = array[leftIndex] >= right ? leftIndex : leftIndex + 1;
                if (array[maxIndex] > array[i]) {
                    ArrayUtils.swap(array, i, maxIndex);
                }
            }
            ArrayUtils.swap(array, 0, k - 1);
        }
    }
}
