package com.future.algoriithm.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码的字母组合
 * <p>
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 提示：
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 * <p>
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 *
 * @author jayzhou
 */
public class LetterCombinations {

    private static final char[][] LETTERS = new char[][]{
            {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'},
            {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result;
        char[] chars = digits.toCharArray();
        char[] process = new char[chars.length];
        dfs(chars, 0, process, result);
        return result;
    }

    private void dfs(char[] chars, int idx, char[] process, List<String> result) {
        if (idx == chars.length) {
            result.add(new String(process));
            return;
        }
        char[] letters = LETTERS[chars[idx] - '2'];
        for (char letter : letters) {
            process[idx] = letter;
            dfs(chars, idx + 1, process, result);
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        LetterCombinations combinations = new LetterCombinations();
        List<String> strings = combinations.letterCombinations(digits);
        System.out.println(strings);
    }

}
