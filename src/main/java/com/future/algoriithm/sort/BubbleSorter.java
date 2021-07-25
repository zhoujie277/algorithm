package com.future.algoriithm.sort;

/**
 * 冒泡排序
 * 冒泡排序：从前面索引开始遍历，让元素往相反方向冒泡
 * 冒泡排序的特点：
 * 1、一共进行n-1躺排序过程
 * 2、每次冒泡就确定一个尾数是排好序了的，也就是说，N次冒泡，那么数组尾部Length-N～Length的部分已然是有序的
 * 每冒一次泡，就确定一个数的位置，故称为冒泡排序.
 * 时间频度相对于选择排序少一个N。其loopCount=(n-1)n/2。时间复杂度为0(n2)
 *
 * @author jayzhou
 */
public class BubbleSorter<E extends Comparable<E>> extends Sorter<E> {

    public static final int VERSION_ORIGIN = VERSION_DEFAULT << 1;

    public BubbleSorter() {
    }

    public BubbleSorter(int version) {
        super(version);
    }

    @Override
    protected void sort() {
        if (version == VERSION_DEFAULT) {
            sortOpt();
        } else if (version == VERSION_ORIGIN) {
            originSort();
        }
    }

    private void sortOpt() {
        // 优化版的冒泡排序：记录最后一次交换的索引。
        // 由于冒泡是前后比较，所以可利用没交换就表示原数组已经是有序数组的这一特性来减少比较次数
        int lastSwapIndex;
        for (int i = elements.length - 1; i > 0; ) {
            lastSwapIndex = -1;
            for (int j = 1; j <= i; j++) {
                if (compare(j - 1, j) > 0) {
                    swap(j - 1, j);
                    lastSwapIndex = j;
                }
            }
            if (lastSwapIndex == -1) {
                break;
            }
            i = lastSwapIndex;
        }
    }

    private void originSort() {
        for (int i = elements.length - 1; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                if (compare(j - 1, j) > 0) {
                    swap(j - 1, j);
                }
            }
        }
    }
}
