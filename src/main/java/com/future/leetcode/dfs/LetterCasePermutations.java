package com.future.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 字母大小写全排列
 * <p>
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * <p>
 * 示例：
 * 输入：S = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * 输入：S = "3z4"
 * 输出：["3z4", "3Z4"]
 * 输入：S = "12345"
 * 输出：["12345"]
 * <p>
 * 提示：
 * S的长度不超过12。
 * S仅由数字和字母组成。
 * <p>
 * 链接：https://leetcode-cn.com/problems/letter-case-permutation
 */
@SuppressWarnings("all")
public class LetterCasePermutations {

    private void dfs(char[] chars, int depth, List<String> result) {
        while (depth < chars.length && !Character.isLetter(chars[depth])) depth++;
        if (depth == chars.length) {
            result.add(new String(chars));
            return;
        }
        chars[depth] = Character.toLowerCase(chars[depth]);
        dfs(chars, depth + 1, result);
        chars[depth] = Character.toUpperCase(chars[depth]);
        dfs(chars, depth + 1, result);
    }

    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) return result;
        char[] chars = s.toCharArray();
        dfs(chars, 0, result);
        return result;
    }

    /**
     * 错误示例：
     * 此处只是单纯的将大小写转换，但是并没有进行排列组合。所以结果中必然会有重复以及遗漏。
     * 输入：a1b2
     * 返回结果：[A1b2, a1b2, a1B2, a1b2]
     */
    private List<String> letterCasePermutationError(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) return result;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])) {
                char old = chars[i];
                chars[i] = Character.toUpperCase(chars[i]);
                result.add(new String(chars));
                chars[i] = Character.toLowerCase(chars[i]);
                result.add(new String(chars));
                chars[i] = old;
            }
        }
        if (result.isEmpty()) result.add(s);
        return result;
    }

    public static void main(String[] args) {
        LetterCasePermutations permutations = new LetterCasePermutations();
        System.out.println(permutations.letterCasePermutation("a1b2"));
        System.out.println(permutations.letterCasePermutation("12345"));
        System.out.println(permutations.letterCasePermutation("a12345"));
    }
}
