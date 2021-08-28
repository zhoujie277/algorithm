package com.future.leetcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> strings = hashMap.get(key);
            if (strings == null) {
                strings = new ArrayList<>();
            }
            strings.add(strs[i]);
            hashMap.put(key, strings);
        }

        List<List<String>> out = new ArrayList<>();
        for (String s : hashMap.keySet()) {
            out.add(hashMap.get(s));
        }
        return out;
    }

    public static void main(String[] args) {
        //输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
        //输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
        //
        String[] strings = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagrams anagrams = new GroupAnagrams();
        System.out.println(anagrams.groupAnagrams(strings));
    }
}
