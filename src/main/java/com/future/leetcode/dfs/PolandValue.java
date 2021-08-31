package com.future.leetcode.dfs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 逆波兰表达式求值
 * <p>
 * 根据 逆波兰表示法，求表达式的值。
 * 有效的算符包括+、-、*、/。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * <p>
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * <p>
 * 示例1：
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * 示例2：
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * 示例3：
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：
 * 该算式转化为常见的中缀算术表达式为：
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * 提示：
 * <p>
 * 1 <= tokens.length <= 10^4
 * tokens[i] 要么是一个算符（"+"、"-"、"*" 或 "/"），要么是一个在范围 [-200, 200] 内的整数
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/gomvm/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class PolandValue {

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            String c = tokens[i];
            switch (c) {
                case "+": {
                    int v1 = stack.pollLast();
                    int v2 = stack.pollLast();
                    stack.addLast(v2 + v1);
                }
                break;
                case "-": {
                    int v1 = stack.pollLast();
                    int v2 = stack.pollLast();
                    stack.addLast(v2 - v1);
                }
                break;
                case "*": {
                    int v1 = stack.pollLast();
                    int v2 = stack.pollLast();
                    stack.addLast(v2 * v1);
                }
                break;
                case "/": {
                    int v1 = stack.pollLast();
                    int v2 = stack.pollLast();
                    stack.addLast(v2 / v1);
                }
                break;
                default:
                    stack.addLast(Integer.valueOf(c));
            }
        }
        if (stack.isEmpty()) return 0;
        return stack.pollLast();
    }

    public static void main(String[] args) {
        //输入：tokens = ["2","1","+","3","*"]
        //[]
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        PolandValue value = new PolandValue();
        System.out.println(value.evalRPN(tokens));
    }

}
