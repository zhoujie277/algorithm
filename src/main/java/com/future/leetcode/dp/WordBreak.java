package com.future.leetcode.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 单词拆分
 * <p>
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 * *    注意你可以重复使用字典中的单词。
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * <p>
 * 链接：https://leetcode-cn.com/problems/word-break
 */
@SuppressWarnings("all")
public class WordBreak {


    /**
     * 如果用朴素法，时间复杂度将会是：O(n^n)。太高了。
     * 动态规划方法解析：
     * 阶段及状态定义：设 f[i] 是以 char[0] 为开始，char[i - 1] 为结尾，的字符是不是可分割的字符串.
     * 状态转移过程：枚举 0 - j， j ≤ i; 将字符串分割成 [0, j) 和 [j, i）两部分。
     * [0, j) 即：f[j]，是由前面推导过的状态而可以获取到。
     * [j, i) 需要去字典表里查找是否存在。
     * 初始状态：
     * 为了方便计算，设 f[0] = true; f.length = s.length + 1;
     * *  即： f[1] 表示 [0, 1)； f[2] 表示 [0, 2)； f[3] 表示 [0, 3);
     * * 每次枚举的顺序是：
     * *  [0, i) -> [0, 1)和[1, i) -> [0, 2)和[2, i) -> [0, 3)和[3, i) -> [0, 4)和[4, i)
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> hashSet = new HashSet<>(wordDict);
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;
        for (int i = 1; i < f.length; i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && hashSet.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[f.length - 1];
    }

    public static void main(String[] args) {
        String s = "catsandog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("and");
        wordDict.add("cat");
        WordBreak wordBreak = new WordBreak();
//        System.out.println(wordBreak.wordBreak(s, wordDict));

        System.out.println("-----");
        String s1 = "aaaaaaa";
        List<String> wordDict1 = new ArrayList<>();
        wordDict1.add("aaaa");
        wordDict1.add("aaa");
        System.out.println(wordBreak.wordBreak(s1, wordDict1));
    }
}
