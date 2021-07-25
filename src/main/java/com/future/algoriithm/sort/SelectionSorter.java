package com.future.algoriithm.sort;

/**
 * 选择排序
 * 逐个遍历，选出最小值或者最大值
 * 跟第一个元素进行替换
 * 该算法交换元素的次数较低，循环次数则一直是n(n+1)/2，时间复杂度为0(n2）
 * @author jayzhou
 */
public class SelectionSorter<E extends Comparable<E>> extends Sorter<E> {

    @Override
    protected void sort() {
        int minIndex;
        for (int i = 0; i < elements.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < elements.length; j++) {
                if (compare(minIndex, j) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(i, minIndex);
            }
        }
    }
}
