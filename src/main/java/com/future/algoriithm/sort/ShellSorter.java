package com.future.algoriithm.sort;

import com.future.utils.NumberUtils;

/**
 * 希尔排序，即缩小增量排序
 * 本质上是一种分组插入排序
 * 插入排序的特点：
 * 插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率。
 * 但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位。
 * 另外，当最小元素在数组最末尾的时候，需要比较的次数很多
 * 针对这些特点，希尔排序做出了改进。
 * 希尔排序的时间性能优于直接插入排序的原因：
 * ①当文件初态基本有序时直接插入排序所需的比较和移动次数均较少。
 * ②当n值较小时，n和  的差别也较小，即直接插入排序的最好时间复杂度O(n)和最坏时间复杂度0(n2)差别不大。
 * ③在希尔排序开始时增量较大，分组较多，每组的记录数目少，故各组内直接插入较快，后来增量di逐渐缩小，分组数逐渐减少，而各组的记录数目逐渐增多，但由于已经按di-1作为距离排过序，使文件较接近于有序状态，所以新的一趟排序过程也较快。
 * 这样做的结果就是：将本来靠后的小的数字通过提前分组插入排序挪到了比较前的位置，所以最后直接插入排序的时候要移动的次数就减少了。
 * 间隔大的时候，挪的次数比较少，当间隔比较小的时候，挪的距离又比较小
 * <p>
 * 总之：思想是尽量保证直接插入排序时尽可能保证容量小，当容量大时，其中元素尽量有序，这样就可以减少移动次数和比较次数
 *
 * @author jayzhou
 */
public class ShellSorter<E extends Comparable<E>> extends Sorter<E> {

    public static final int KNUTH_SEQUENCE = VERSION_DEFAULT << 1;
    public static final int BINARY_SEQUENCE = VERSION_DEFAULT << 2;
    public static final int BINARY_SEQUENCE_OPT = VERSION_DEFAULT << 4;

    public ShellSorter() {
    }

    public ShellSorter(int version) {
        super(version);
    }

    @Override
    protected void sort() {
        if (version == VERSION_DEFAULT) {
            shellSort();
        } else if (version == KNUTH_SEQUENCE) {
            knuthSort();
        } else if (version == BINARY_SEQUENCE) {
            binarySort();
        } else if (version == BINARY_SEQUENCE_OPT) {
            binarySortOpt();
        }
    }

    private void shellSort() {
        int half = elements.length >> 1;
        int step = (NumberUtils.findLastBinary(half) >> 1);
        for (int gap = step; gap > 0; gap >>>= 1) {
            for (int i = gap; i < elements.length; i++) {
                for (int j = i; j >= gap && compare(j, j - gap) < 0; j -= gap) {
                    swap(j, j - gap);
                }
            }
        }
    }

    private void knuthSort() {
        //knuth序列 3h + 1
        int h = 1;
        while (h <= elements.length / 3) {
            h = 3 * h + 1;
        }
        for (int gap = h; gap > 0; gap = (gap - 1) / 3) {
            for (int i = gap; i < elements.length; i++) {
                for (int j = i; j >= gap && compare(j, j - gap) < 0; j -= gap) {
                    swap(j, j - gap);
                }
            }
        }
    }

    public void binarySort() {
        for (int gap = elements.length >>> 1; gap > 0; gap >>>= 1) {
            for (int i = gap; i < elements.length; i++) {
                for (int j = i; j >= gap && compare(j, j - gap) < 0; j -= gap) {
                    swap(j, j - gap);
                }
            }
        }
    }

    public void binarySortOpt() {
        E temp;
        int j;
        for (int gap = elements.length >>> 1; gap > 0; gap >>>= 1) {
            for (int i = gap; i < elements.length; i++) {
                temp = elements[i];
                for (j = i; j >= gap && compare(temp, elements[j - gap]) < 0; j -= gap) {
                    move(j - gap, j);
                }
                if (temp != elements[i]) {
                    elements[j] = temp;
                }
            }
        }
    }

}
