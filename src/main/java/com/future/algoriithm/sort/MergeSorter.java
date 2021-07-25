package com.future.algoriithm.sort;

import com.future.utils.PrintUtils;

/**
 * 归并排序
 * 源于分治思想
 * 是一种稳定的排序算法，在对象排序算法里通常作为首选算法
 * 平均时间复杂度nlogn。
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class MergeSorter<E extends Comparable<E>> extends Sorter<E> {

    public static final int BOTTOM_UP = VERSION_DEFAULT << 1;

    private E[] mergeArray = null;

    public MergeSorter() {
    }

    public MergeSorter(int version) {
        super(version);
    }

    @Override
    protected void sort() {
        mergeArray = (E[]) new Comparable[elements.length];
        if (version != BOTTOM_UP) {
            divide(0, elements.length - 1);
        } else {
            bottomUpSort();
        }
    }

    // 自顶向下的分治归并排序
    private void divide(int begin, int end) {
        if (begin >= end) return;
        int mid = (begin + end) >>> 1;
        divide(begin, mid);
        divide(mid + 1, end);
        merge(begin, mid, end);
    }

    private void merge(int left, int mid, int right) {
        int l = left, r = mid + 1;
        int m = left;
        while (l <= mid && r <= right) {
            mergeArray[m++] = compare(l, r) <= 0 ? elements[l++] : elements[r++];
        }
        while (l <= mid) {
            mergeArray[m++] = elements[l++];
        }
        while (r <= right) {
            mergeArray[m++] = elements[r++];
        }

        for (int i = left; i <= right; i++) {
            elements[i] = mergeArray[i];
        }
    }

    // 非递归，自底向上的归并排序
    private void bottomUpSort() {
        for (int i = 1; i < elements.length; i <<= 1) {
            for (int left = 0; left < elements.length - i; left += (i << 1)) {
                int mid = left + i - 1;
                int right = Math.min(left + (i << 1) - 1, elements.length - 1);
                merge(left, mid, right);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] ints = new Integer[]{
                56, 55, 58, 57, 0, 36
        };
        new MergeSorter<Integer>(BOTTOM_UP).sortOrigin(ints);
        PrintUtils.println(ints);
    }
}
