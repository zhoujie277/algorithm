package com.future.algoriithm.sort;


import com.future.utils.PrintUtils;
import com.future.utils.Printable;
import com.future.utils.ArrayUtils;


/**
 * 快速排序
 * 找到一个基准值，将小于该基准值的放左边，大于该基准值的放右边
 * 再从两边依次类推，分而治之。
 */
public class QuickSorting implements Printable {

    private interface QuickSortStrategy extends Printable {
        void quickSort(int[] array, int start, int end);
    }

    private QuickSortStrategy strategy = new QuickSorting1();
//    private QuickSortStrategy strategy = new QuickSorting2();

    public void quickSort(int[] array, int start, int end) {
        strategy.quickSort(array, start, end);
    }

    static class QuickSorting1 implements QuickSortStrategy {

        private int recursiveCount;
        private int moveCount;

        public void quickSort(int[] array, int start, int end) {
            if (start >= end) return;
            recursiveCount++;
            int pivot = array[end];
            int i = start;
            int j = end - 1;
            while (true) {
                while (i < end && array[i] <= pivot) {
                    i++;
                }
                while (j >= start && array[j] > pivot) {
                    j--;
                }
                if (i >= j) break;
                moveCount++;
                ArrayUtils.swap(array, i, j);
            }
            if (i != end) {
                array[end] = array[i];
                array[i] = pivot;
                moveCount++;
            }
            quickSort(array, start, i - 1);
            quickSort(array, i + 1, end);
        }

        @Override
        public void println() {
            PrintUtils.println("QuickSorting1 recursiveCount=" + recursiveCount + ", moveCount=" + moveCount);
        }
    }


    @Override
    public void println() {
        strategy.println();
    }

    static class QuickSorting2 implements QuickSortStrategy{
        private int recursiveCount;
        private int moveCount;

        public void quickSort(int[] arr, int start, int end) {
            if (start >= end) return;
            recursiveCount++;
            int partitionIndex = partition(arr, start, end);
            quickSort(arr, start, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }

        private int partition(int[] arr, int start, int end) {
            int pivot = start;
            int index = pivot + 1;
            for (int i = index; i <= end; i++) {
                if (arr[i] < arr[pivot]) {
                    moveCount++;
                    ArrayUtils.swap(arr, i, index);
                    index++;
                }
            }
            moveCount++;
            ArrayUtils.swap(arr, pivot, index - 1);
            return index - 1;
        }

        @Override
        public void println() {
            PrintUtils.println("QuickSorting2 recursiveCount=" + recursiveCount + ", moveCount=" + moveCount);
        }
    }
}
