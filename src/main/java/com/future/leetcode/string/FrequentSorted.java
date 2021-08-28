package com.future.leetcode.string;

import java.util.HashMap;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * <p>
 * 输入:
 * "tree"
 * 输出:
 * "eert"
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * <p>
 * 输入:
 * "cccaaa"
 * 输出:
 * "cccaaa"
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * <p>
 * 输入:
 * "Aabb"
 * 输出:
 * "bbAa"
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/all-about-lockup-table/fc4ic/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class FrequentSorted {

    private static class Pair {
        char c;
        int freq;

        public Pair(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }

    private void swap(Pair[] pairs, int i, int j) {
        if (i == j) return;
        Pair tmp = pairs[i];
        pairs[i] = pairs[j];
        pairs[j] = tmp;
    }

    private void qsort(Pair[] pairs, int left, int right) {
        if (left >= right) return;
        int l = left, r = right - 1;
        while (true) {
            while (l <= r && pairs[l].freq <= pairs[right].freq) {
                l++;
            }
            while (l <= r && pairs[r].freq > pairs[right].freq) {
                r--;
            }
            if (l > r) break;
            swap(pairs, l, r);
        }
        swap(pairs, l, right);
        qsort(pairs, left, l - 1);
        qsort(pairs, l + 1, right);
    }

    /**
     * hashmap 实现桶计数，然后按计数排序
     * 时间复杂度：O(n + klogk) 。其中k是不重复字符的个数。
     * 空间复杂度：O(n + k)
     */
    public String frequencySort(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (char aChar : chars) {
            hashMap.put(aChar, hashMap.getOrDefault(aChar, 0) + 1);
        }
        Pair[] pairs = new Pair[hashMap.size()];
        int index = 0;
        for (Character character : hashMap.keySet()) {
            pairs[index++] = new Pair(character, hashMap.get(character));
        }
        qsort(pairs, 0, pairs.length - 1);
        index = chars.length - 1;
        for (int i = 0; i < pairs.length; i++) {
            Integer integer = hashMap.get(pairs[i].c);
            while (integer > 0) {
                chars[index--] = pairs[i].c;
                integer--;
            }
        }
        return new String(chars);
    }

    /**
     * 桶排序
     * 每个字符出现的频率是有个上限的，用字符上限作为桶的索引，即可实现桶排序。频率不可能大于n
     * 需要考虑的是，有字符会出现相同的频率，此时用stringbuilder拼接。
     * 时间复杂度：O(n + k)
     * 空间复杂度：O(n + k)
     */
    public String frequencySortBucket(String s) {
        char[] chars = s.toCharArray();
        int max = 0;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (char aChar : chars) {
            int freq = hashMap.getOrDefault(aChar, 0) + 1;
            hashMap.put(aChar, freq);
            max = Math.max(freq, max);
        }
        StringBuilder[] buckets = new StringBuilder[max + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new StringBuilder();
        }
        for (Character character : hashMap.keySet()) {
            int freq = hashMap.get(character);
            int count = freq;
            while (count > 0) {
                buckets[freq].append(character);
                count--;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = buckets.length - 1; i > 0; i--) {
            result.append(buckets[i]);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        FrequentSorted frequentSorted = new FrequentSorted();
        String s = "tree";
        System.out.println(frequentSorted.frequencySort(s));
        System.out.println(frequentSorted.frequencySort("cccaaa"));
        System.out.println(frequentSorted.frequencySort("raaeaedere"));
        System.out.println("----------");
        System.out.println(frequentSorted.frequencySortBucket(s));
        System.out.println(frequentSorted.frequencySortBucket("cccaaa"));
        System.out.println(frequentSorted.frequencySortBucket("raaeaedere"));
    }
}
