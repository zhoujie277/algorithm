package com.future.algoriithm.sort;

import com.future.utils.ArrayUtils;
import com.future.utils.Sortable;

/**
 * 基数排序
 * 桶思想的一种排序方法
 * 是一种非比较、稳定的排序方法
 * 平均时间复杂度为 n * k
 * 平均空间复杂度为 n + k
 * 本质上是多关键字排序
 * 可实现类似字符串排序、
 */
public class RadixSorter {

    public static class RadixNumberSorter implements Sorter {

        private int findBit(int[] array) {
            int max = ArrayUtils.max(array);
            int bit = 1;
            while (max / bit > 0) {
                bit *= 10;
            }
            return bit;
        }

        @Override
        public void sort(int[] array) {
            int maxBit = findBit(array);
            int bit = 1;
            int bucketNo;
            int[] result = new int[array.length];
            int[] buckets = new int[10];
            while (bit < maxBit) {
                for (int i = 0; i < array.length; i++) {
                    bucketNo = array[i] / bit % 10;
                    buckets[bucketNo]++;
                }

                for (int i = 1; i < buckets.length; i++) {
                    buckets[i] = buckets[i] + buckets[i - 1];
                }

                for (int i = array.length - 1; i >= 0; i--) {
                    bucketNo = array[i] / bit % 10;
                    result[--buckets[bucketNo]] = array[i];
                }
                ArrayUtils.copy(result, array);
                ArrayUtils.fill(buckets, 0);
                bit *= 10;
            }
        }
    }

    public static class StringSorter {

        public void sort(String[] array) {
            int index = ArrayUtils.findMaxLength(array);
            char firstLetter = 'a';
            int bucketLen = 'z' - firstLetter + 1;
            int bucketIndex;
            String[] result = new String[array.length];
            int[] buckets = new int[bucketLen];
            while (index >= 0) {
                char c;
                for (int i = 0; i < array.length; i++) {
                    if (index >= array[i].length()) {
                        c = firstLetter;
                    } else {
                        c = array[i].charAt(index);
                    }
                    bucketIndex = c - firstLetter;
                    buckets[bucketIndex]++;
                }

                for (int i = 1; i < buckets.length; i++) {
                    buckets[i] = buckets[i] + buckets[i - 1];
                }

                for (int i = array.length - 1; i >= 0; i--) {
                    if (index >= array[i].length()) {
                        c = firstLetter;
                    } else {
                        c = array[i].charAt(index);
                    }
                    bucketIndex = c - firstLetter;
                    result[--buckets[bucketIndex]] = array[i];
                }
                ArrayUtils.copy(result, array);
                ArrayUtils.fill(buckets, 0);
                index--;
            }
        }
    }

    public static class MultiKeySorter<T extends Sortable> {

        public void sort(T[] array) {
            int keysLength = array[0].keys().length;
            int keyIndex = 0;
            int bucketIndex;
            T[] result = array.clone();
            while (keyIndex < keysLength) {
                int max = ArrayUtils.minOrMax(array, keyIndex, true);
                int min = ArrayUtils.minOrMax(array, keyIndex, false);
                int[] buckets = new int[max - min + 1];
                for (int i = 0; i < array.length; i++) {
                    bucketIndex = array[i].keyAt(keyIndex) - min;
                    buckets[bucketIndex]++;
                }

                for (int i = 1; i < buckets.length; i++) {
                    buckets[i] = buckets[i] + buckets[i - 1];
                }

                for (int i = array.length - 1; i >= 0; i--) {
                    bucketIndex = array[i].keyAt(keyIndex) - min;
                    result[--buckets[bucketIndex]] = array[i];
                }

                ArrayUtils.copy(result, array);
                ArrayUtils.fill(buckets, 0);
                keyIndex++;
            }
        }
    }
}
