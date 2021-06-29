package com.future.algoriithm.sort;

import com.future.algoriithm.utils.PrintUtils;
import com.future.algoriithm.array.RandomArray;
import com.future.algoriithm.utils.ArrayUtils;

import java.util.function.Consumer;

/**
 * 简单排序
 * 包含选择排序、冒泡排序、插入排序
 * 本类算法均为升序排序规则
 */
public class SimpleSorting {

    /**
     * 需要一个临时空间，逐个遍历，选出最小值或者最大值
     * 再跟第一个元素进行替换
     * 该算法移动元素的次数最低，循环次数则一直是n(n+1)/2，时间复杂度为0(n2）
     *
     * @param array
     */
    public static void selectSort(int[] array) {
        long loopCount = 0, moveCount = 0;
        int length = array.length;
        int minIndex;
        for (int i = 0; i < length - 1; i++) {
            minIndex = i;
            for (int j = i; j < length; j++) {
                loopCount++;
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            // 如果同一个位置，就不需要交换，这个判断必须得加，如若不然，两个索引指向同一个位置，导致无论通过哪个索引变量修改值，另一个索引访问到的也是修改后的值
            if (minIndex != i) {
                moveCount++;
                ArrayUtils.swap(array, i, minIndex);
            }
        }
        PrintUtils.println("chooseSort loopCount=" + loopCount + ", moveCount=" + moveCount);
    }

    /**
     * 冒泡排序：从前面索引开始遍历，让元素往相反方向冒泡
     * 冒泡排序的特点：
     * 1、一共进行n-1躺排序过程
     * 2、每次冒泡就确定一个尾数是排好序了的，也就是说，N次冒泡，那么数组尾部Length-N～Length的部分已然是有序的
     * 每冒一次泡，就确定一个数的顺序，故称为冒泡排序.
     * 时间频度相对于选择排序少一个N。其loopCount=(n-1)n/2。时间复杂度为0(n2)
     *
     * @param array
     */
    public static void bubbleSort(int[] array) {
        long loopCount = 0, moveCount = 0;
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                loopCount++;
                if (array[j] > array[j + 1]) {
                    moveCount++;
                    ArrayUtils.swap(array, j, j + 1);
                }
            }
        }
        PrintUtils.println("bubbleSort loopCount=" + loopCount + ", moveCount=" + moveCount);
    }

    /**
     * 冒泡排序的优化版本
     * 当进行每相邻元素两两比较的过程中，如果没有可交换的元素，则说明数组已然有序，可以提前结束整个排序过程
     *
     * @param array
     */
    public static void bubbleSortOptimization(int[] array) {
        long loopCount = 0, moveCount = 0;
        int length = array.length;
        boolean swapFlag;
        for (int i = 0; i < length - 1; i++) {
            swapFlag = false;
            for (int j = 0; j < length - i - 1; j++) {
                loopCount++;
                if (array[j] > array[j + 1]) {
                    swapFlag = true;
                    moveCount++;
                    array[j] = array[j] ^ array[j + 1];
                    array[j + 1] = array[j] ^ array[j + 1];
                    array[j] = array[j] ^ array[j + 1];
                }
            }
            if (!swapFlag) {
                break;
            }
        }
        PrintUtils.println("bubbleSortOptimization loopCount=" + loopCount + ", moveCount=" + moveCount);
    }

    public static void qsort(int[] array) {
        PrintUtils.println("quicksort");
        QuickSorting sorting = new QuickSorting();
        sorting.quickSort(array, 0, array.length - 1);
        sorting.println();
    }

    private static void complexTest() {
        int originArray[] = {9, 1, 4, 6, 3, 5, 7, 2, 8};
//        int originArray[] = {9, 1, 4, 6, 3};
        PrintUtils.print(originArray);

        int[] array = originArray.clone();
        selectSort(array);
        PrintUtils.print(array);

        array = originArray.clone();
        bubbleSort(array);
        PrintUtils.print(array);

        array = originArray.clone();
        bubbleSortOptimization(array);
        PrintUtils.print(array);

        array = originArray.clone();
        qsort(array);
        PrintUtils.print(array);
    }

    private static void performTest(Consumer<int[]> function) {
        RandomArray randomArray = new RandomArray(800000);
        int array[] = randomArray.getArray();
        PrintUtils.println("The array is generated, the length is " + array.length);
        long start = System.currentTimeMillis();
        function.accept(array);
//        PrintUtils.print(array);
        long end = System.currentTimeMillis();
        System.out.println("The array are sorted cost time " + (end - start) + " ms");
    }

    private static void sortInvoke() {
//        performTest(SimpleSorting::selectSort);
//        performTest(SimpleSorting::bubbleSort);
//        performTest(SimpleSorting::bubbleSortOptimization);
        performTest(SimpleSorting::qsort);
    }

    public static void main(String[] args) {
//        complexTest();
        sortInvoke();
    }
}
