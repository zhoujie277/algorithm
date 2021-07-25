package com.future.algoriithm.sort;

/**
 * 直接插入排序
 * 假设前面部分数组已经有序，后面遍历的数字，进行挨个比较并插入。
 * 最开始假设第一个元素已经有序，从第二个元素遍历(索引为1)，然后每比较插入后，成为了一个新的有序序列。
 * 该算法性能较高，特别适用于部分已经有序的数组。
 * 为了避免减少递归调用的损耗，在规模较小的时候适合采用插入排序
 * <p>
 * 逆序对(invertion)越多，插入排序性能越低
 *
 * @author jayzhou
 */
public class InsertSorter<E extends Comparable<E>> extends Sorter<E> {

    public static final int DIRECT_INSERT = VERSION_DEFAULT << 1;
    public static final int MOVE_INSERT = VERSION_DEFAULT << 2;

    public InsertSorter() {
    }

    public InsertSorter(int version) {
        super(version);
    }

    @Override
    protected void sort() {
        if (version == VERSION_DEFAULT) {
            binaryInsert();
        } else if (version == DIRECT_INSERT) {
            directInsert();
        } else if (version == MOVE_INSERT) {
            moveInsert();
        }
    }

    private void binaryInsert() {
        for (int i = 1; i < elements.length; i++) {
            E ele = elements[i];
            int dest = search(i);
            for (int j = i; j > dest; j--) {
                move(j - 1, j);
            }
            elements[dest] = ele;
        }
    }

    private int search(int index) {
        int begin = 0, end = index;
        while (begin < end) {
            int mid = (begin + end) >>> 1;
            if (compare(index, mid) < 0) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }


    /**
     * 直接插入
     */
    private void directInsert() {
        for (int i = 1; i < elements.length; i++) {
            for (int j = i; j > 0 && compare(j, j - 1) < 0; j--) {
                swap(j, j - 1);
            }
        }
    }

    /**
     * 直接插入的优化版本
     */
    private void moveInsert() {
        E temp;
        int j;
        for (int i = 1; i < elements.length; i++) {
            temp = elements[i];
            for (j = i; j > 0 && compare(temp, elements[j - 1]) < 0; j--) {
                move(j - 1, j);
            }
            if (temp != elements[i]) {
                elements[j] = temp;
            }
        }
    }

//    public static void binaryInsert(int[] array) {
//        int temp, j;
//        int low, high, mid;
//        for (int i = 1; i < array.length; i++) {
//            temp = array[i];
//            low = 0;
//            high = i - 1;
//            while (low <= high) {
//                mid = (low + high) >>> 1;
//                //保证稳定性，插入的值在尾端
//                if (temp >= array[mid]) {
//                    low = mid + 1;
//                } else {
//                    high = mid - 1;
//                }
//            }
//            for (j = i; j > low; j--) {
//                array[j] = array[j - 1];
//            }
//            array[low] = temp;
//        }
//    }

//    /**
//     * 折半双插入法
//     */
//    private void binaryInsertOpt(int[] array) {
//
//    }

}
