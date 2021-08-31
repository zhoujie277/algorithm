package com.future.leetcode.dfs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 有效的括号
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 * 示例2：
 * 输入：s = "()[]{}"
 * 输出：true
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/g9d0h/
 *
 * @author jayzhou
 */
public class ValidBrackets {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return false;
        char[] chars = s.toCharArray();
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{' || chars[i] == '[' || chars[i] == '(')
                deque.addLast(chars[i]);
            else {
                if (deque.isEmpty()) return false;
                Character c = deque.pollLast();
                if ((c == '(' && chars[i] == ')') || (c == '[' && chars[i] == ']') || (c == '{' && chars[i] == '}')) {
                    continue;
                }
                return false;
            }
        }
        return deque.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new ValidBrackets().isValid("()[]{}"));
        System.out.println(new ValidBrackets().isValid("()[]{"));
        System.out.println(new ValidBrackets().isValid("(]"));
    }
}
