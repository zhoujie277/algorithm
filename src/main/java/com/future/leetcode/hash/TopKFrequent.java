package com.future.leetcode.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * 前k个高频元素
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * <p>
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * <p>
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n是数组大小。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/hash-table/xxwb2v/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class TopKFrequent {

    private static class Pair {
        int value;
        int frequent;

        public Pair(int value, int frequent) {
            this.value = value;
            this.frequent = frequent;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "value=" + value +
                    ", frequent=" + frequent +
                    '}';
        }
    }

    /**
     * 时间复杂度：O(nlogk)
     * 空间复杂度：O(n)
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }
        Pair[] array = new Pair[k];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Pair(0, 0);
        }
        for (Integer key : hashMap.keySet()) {
            Pair pair = new Pair(key, hashMap.get(key));
            if (pair.frequent > array[0].frequent) {
                array[0] = pair;
                siftDown(array, 0);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < array.length; i++) {
            res[i] = array[i].value;
        }
        return res;
    }

    private void siftDown(Pair[] array, int index) {
        Pair element = array[index];
        int half = array.length >> 1;
        while (index < half) {
            int smaller = (index << 1) + 1;
            int right = smaller + 1;
            if (right < array.length && array[right].frequent < array[smaller].frequent) {
                smaller = right;
            }
            if (array[smaller].frequent > element.frequent) {
                break;
            }
            array[index] = array[smaller];
            index = smaller;
        }
        array[index] = element;
    }

    Random random = new Random();

    private void swap(Pair[] pairs, int i, int j) {
        if (i == j) return;
        Pair tmp = pairs[i];
        pairs[i] = pairs[j];
        pairs[j] = tmp;
    }

    private void qsort(Pair[] pairs, int left, int right, int k) {
        if (left >= right) return;
        int rand = random.nextInt(right - left + 1) + left;
        swap(pairs, rand, right);
        int l = left, r = right - 1;
        while (true) {
            while (l <= r && pairs[l].frequent >= pairs[right].frequent) {
                l++;
            }
            while (l <= r && pairs[r].frequent < pairs[right].frequent) {
                r--;
            }
            if (l > r) break;
            swap(pairs, l, r--);
        }
        swap(pairs, l, right);
        if (k < l) {
            qsort(pairs, left, l - 1, k);
        } else if (k > l) {
            qsort(pairs, l + 1, right, k);
        }
    }

    /**
     * 最好时间复杂度：O(n)
     * 平均情况下：O(n)
     * 最坏时间复杂度：O(n^2) 每次取的中枢数组的元素都位于数组的两端，时间复杂度退化为 O(n^2)
     * *             但由于我们在每次递归的开始会先随机选取中枢元素，故出现最坏情况的概率很低
     * 空间复杂度：O(n)
     */
    public int[] topKFrequentQsort(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }
        Pair[] array = new Pair[hashMap.size()];
        int index = 0;
        for (Integer key : hashMap.keySet()) {
            array[index++] = new Pair(key, hashMap.get(key));
        }
        System.out.println(Arrays.toString(array));
        qsort(array, 0, array.length - 1, k);
        System.out.println(Arrays.toString(array));
        int[] res = new int[k];
        for (int i = 0; i < res.length; i++) {
            res[i] = array[i].value;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int[] nums2 = new int[]{4, 1, -1, 2, -1, 2, 3};
        int[] nums3 = new int[]{6, 0, 1, 4, 9, 7, -3, 1, -4, -8, 4, -7, -3, 3, 2, -3, 9, 5, -4, 0};
        TopKFrequent topKFrequent = new TopKFrequent();
//        System.out.println(Arrays.toString(topKFrequent.topKFrequent(nums, 2)));
//        System.out.println(Arrays.toString(topKFrequent.topKFrequent(nums2, 2)));
//        System.out.println(Arrays.toString(topKFrequent.topKFrequent(nums3, 6)));
//        System.out.println(Arrays.toString(topKFrequent.topKFrequentQsort(nums, 2)));
//        System.out.println(Arrays.toString(topKFrequent.topKFrequentQsort(nums2, 2)));
//        System.out.println(Arrays.toString(topKFrequent.topKFrequentQsort(nums3, 6)));
//        int[] nums4 = new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3};
//        System.out.println(Arrays.toString(topKFrequent.topKFrequentQsort(nums4, 3)));
        int[] nums5 = new int[]{5, -3, 9, 1, 7, 7, 9, 10, 2, 2, 10, 10, 3, -1, 3, 7, -9, -1, 3, 3};
        int[] nums6 = new int[]{-1, 1, 4, -4, 3, 5, 4, -2, 3, -1};
        System.out.println(Arrays.toString(topKFrequent.topKFrequentQsort(nums6, 3)));
    }
}
