package com.future.algoriithm.sort;

import com.future.algoriithm.utils.PrintUtils;
import com.future.algoriithm.utils.ArrayUtils;
import com.future.algoriithm.utils.Sortable;

/**
 * 计数排序
 * 桶思想的一种排序方法
 * 非比较排序，也可实现为稳定排序
 * 平均时间复杂度为 n + k
 * 平均空间复杂度为 n + k
 * 特别适合于量大但是取值范围有限的排序
 * 比如高考分数排序，考生人数很多，但分值都在0-750以内，这样桶的个数可以控制
 * 本质上是将要排序的关键字作为桶的key，也就是数组的下标，而桶数组对应的值是该关键字的重复次数，也就是计数，故称为计数排序
 * 为非比较排序
 */
public class CountSorter {

//    public static <T> void sort(T[] array,int max,int min) {
//        T[] result = array.clone();
//        int bucketIndex;
//        int[] buckets = new int[max - min + 1];
//        for (int i = 0; i < array.length; i++) {
//            bucketIndex = array[i].key() - min;
//            buckets[bucketIndex]++;
//        }
//        // 累加后，桶数组的数值减1就是排序后的数组出现的最后的索引
//        for (int i = 1; i < buckets.length; i++) {
//            buckets[i] = buckets[i] + buckets[i - 1];
//        }
//
//        // 因为要输出原数组，需要原数组的值，所以需要一个额外的空间来保存输出
//        // 此处需要倒序插入值，为了保证稳定排序
//
//        for (int i = array.length - 1; i >= 0; i--) {
//            bucketIndex = array[i].key() - min;
//            result[--buckets[bucketIndex]] = array[i];
//        }
//        ArrayUtils.copy(result, array);
//    }

    /**
     * 极简计数排序
     */
    public static final class CountSorting1 implements Sorter {

        /**
         * 新建一个数组作为桶
         * 将int数组的值作为桶的键存储
         * 这种实现为非稳定排序，也无法实现对象数组的排序，但是空间复杂度为O(1)
         *
         * @param array
         */
        @Override
        public void sort(int[] array) {
            int[] buckets = new int[10];
            for (int i = 0; i < array.length; i++) {
                buckets[array[i]]++;
            }
            PrintUtils.print(array);
            PrintUtils.print(buckets);
            int i = 0;
            for (int i1 = 0; i1 < buckets.length; i1++) {
                while (buckets[i1]-- > 0)
                    array[i++] = i1;
            }
            PrintUtils.print(array);
        }
    }

    public static final class CountSorting2 implements Sorter {

        @Override
        public void sort(int[] array) {
            // find range
            int max = ArrayUtils.max(array);
            int min = ArrayUtils.min(array);
            int bucketIndex;
            int[] buckets = new int[max - min + 1];
            for (int i = 0; i < array.length; i++) {
                bucketIndex = array[i] - min;
                buckets[bucketIndex]++;
            }
            PrintUtils.print(array);
            PrintUtils.print(buckets);

            // 累加后，桶数组的数值减1就是排序后的数组出现的最后的索引
            for (int i = 1; i < buckets.length; i++) {
                buckets[i] = buckets[i] + buckets[i - 1];
            }
            PrintUtils.print(buckets);

            // 因为要输出原数组，需要原数组的值，所以需要一个额外的空间来保存输出
            // 此处需要倒序插入值，为了保证稳定排序
            int[] result = new int[array.length];
            for (int i = array.length - 1; i >= 0; i--) {
                bucketIndex = array[i] - min;
                result[--buckets[bucketIndex]] = array[i];
            }
            ArrayUtils.copy(result, array);
            PrintUtils.print(array);
        }
    }

    public static final class CountSorting3<T extends Sortable> {

        public void sort(T[] array) {
            int max = ArrayUtils.max(array);
            int min = ArrayUtils.min(array);
            Sortable[] result = new Sortable[array.length];
            int bucketIndex;
            int[] buckets = new int[max - min + 1];
            for (int i = 0; i < array.length; i++) {
                bucketIndex = array[i].key() - min;
                buckets[bucketIndex]++;
            }
            // 累加后，桶数组的数值减1就是排序后的数组出现的最后的索引
            for (int i = 1; i < buckets.length; i++) {
                buckets[i] = buckets[i] + buckets[i - 1];
            }

            // 因为要输出原数组，需要原数组的值，所以需要一个额外的空间来保存输出
            // 此处需要倒序插入值，为了保证稳定排序

            for (int i = array.length - 1; i >= 0; i--) {
                bucketIndex = array[i].key() - min;
                result[--buckets[bucketIndex]] = array[i];
            }
            ArrayUtils.copy(result, array);
        }
    }
}
