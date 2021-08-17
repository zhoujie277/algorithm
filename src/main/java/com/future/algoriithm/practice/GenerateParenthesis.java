package com.future.algoriithm.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成括号
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
 * 有效括号组合需满足：左括号必须以正确的顺序闭合。
 *
 * @author jayzhou
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        if (n <= 0) return null;
        char[] chars = new char[n << 1];
        List<String> result = new ArrayList<>();
        dfs(chars, 0, n, n, result);
        return result;
    }

    private void dfs(char[] chars, int idx, int leftCount, int rightCount, List<String> result) {
        if (idx == chars.length) {
            result.add(new String(chars));
            return;
        }

        if (leftCount > 0) {
            chars[idx] = '(';
            dfs(chars, idx + 1, leftCount - 1, rightCount, result);
        }
        // 当且仅当可用数量大于0并且，小于左括号数量时才可放，否则非法
        if (rightCount > 0 && leftCount < rightCount) {
            chars[idx] = ')';
            dfs(chars, idx + 1, leftCount, rightCount - 1, result);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        List<String> strings = new GenerateParenthesis().generateParenthesis(n);
        System.out.println(strings);
    }
}
