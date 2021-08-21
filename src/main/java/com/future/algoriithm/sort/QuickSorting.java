package com.future.algoriithm.sort;


import com.future.utils.ArrayUtils;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;


/**
 * 快速排序
 * 找到一个基准值，将小于该基准值的放左边，大于该基准值的放右边
 * 再从两边依次类推，分而治之。
 *
 * @author jayzhou
 */
public class QuickSorting<E extends Comparable<E>> extends Sorter<E> {

    public static final int SWAP_FIND = VERSION_DEFAULT << 1;

    public QuickSorting() {
    }

    public QuickSorting(int version) {
        super(version);
    }

    @Override
    protected void sort() {
        if (version == VERSION_DEFAULT) {
            quicksort(0, elements.length - 1);
        } else if (version == SWAP_FIND) {
            swapFind(0, elements.length - 1);
        }
    }

    private void quicksort2(int[] nums, int l, int r) {
        if (l >= r) return;
        int slow = l, fast = l;
        for (; fast < r; fast++) {
            if (nums[fast] > nums[r]) continue;
            swap(slow++, fast);
        }
        swap(slow, r);
        quicksort2(nums, l, slow - 1);
        quicksort2(nums, slow + 1, r);
    }

    private void quicksort(int left, int right) {
        if (left >= right) return;
        int start = left++;
        int end = right;
        while (left <= right) {
            while (left <= right && compare(right, start) >= 0) {
                right--;
            }
            while (left <= right && compare(left, start) <= 0) {
                left++;
            }
            if (left > right) break;
            swap(left, right);
        }
        if (right > start)
            swap(start, right);
        quicksort(start, right - 1);
        quicksort(right + 1, end);
    }

    public static void main(String[] args) {
        int[] permutation = StdRandom.permutation(5);
//        int[] permutation = new int[]{1, 3, 4, 2, 0};
        System.out.println(Arrays.toString(permutation));
        Integer[] integers = Arrays.stream(permutation).boxed().toArray(Integer[]::new);
        new QuickSorting<Integer>(SWAP_FIND).sortOrigin(integers);
        System.out.println(Arrays.toString(integers));
    }

    /**
     * 左右交换查找顺序的快排,
     * 一端开，一端闭，所以要用<，不能用≤
     * 本质上和第一个版本没有区别
     */
    private void swapFind(int left, int right) {
        if (left >= right) return;
        int start = left, end = right;
        E pivot = elements[left];
        while (left < right) {
            while (left < right) {
                if (compare(elements[right], pivot) > 0) {
                    right--;
                } else {
                    elements[left++] = elements[right];
                    break;
                }
            }
            while (left < right) {
                if (compare(elements[left], pivot) < 0) {
                    left++;
                } else {
                    elements[right--] = elements[left];
                    break;
                }
            }
        }
        elements[right] = pivot;
        swapFind(start, right - 1);
        swapFind(right + 1, end);
    }


    static class QuickSorting2 {
        public void quickSort(int[] arr, int start, int end) {
            if (start >= end) return;
            int partitionIndex = partition(arr, start, end);
            quickSort(arr, start, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }

        private int partition(int[] arr, int start, int end) {
            int pivot = start;
            int left = pivot + 1;
            for (int right = left; right <= end; right++) {
                if (arr[right] < arr[pivot]) {
                    ArrayUtils.swap(arr, right, left);
                    left++;
                }
            }
            ArrayUtils.swap(arr, pivot, left - 1);
            return left - 1;
        }

    }
}
