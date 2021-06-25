package com.future.algoriithm.problem.expression;

import com.future.algoriithm.PrintUtils;
import com.future.algoriithm.Printable;
import com.future.algoriithm.linked.DualLinkedList;
import com.future.algoriithm.stack.Stack;

import java.util.Iterator;

/**
 * 队列+栈实现的表达式的解析、存储、运算
 * 前缀表达式/中缀表达式/后缀表达式
 * @author zhoujie
 */
public class Expression implements Printable {
    private final String originExpr;

    // 前缀表达式，运算顺序从右至左，用栈存储更合适
    private final DualLinkedList<Code> prefixExpr = new DualLinkedList<>();
    // 中缀和后缀表达式，运算顺序从左至右，用队列存储更合适
    private final DualLinkedList<Code> infixExpr = new DualLinkedList<>();
    private final DualLinkedList<Code> suffixExpr = new DualLinkedList<>();

    public Expression(String originExpr) {
        this.originExpr = originExpr;
    }

    public void parse() {
        Stack<Character> digitCache = new Stack<>();
        for (int i = 0; i < originExpr.length(); ) {
            char c = originExpr.charAt(i);
            if (!Character.isDigit(c) && c != '.') {
                if (!Character.isWhitespace(c)) {
                    infixExpr.push(new Code(c, false));
                }
                i++;
            } else {
                do {
                    digitCache.push(c);
                    i++;
                } while (i < originExpr.length() && Character.isDigit(c = originExpr.charAt(i)) || c == '.');
                pushDigit(digitCache);
            }
        }
    }

    /**
     * 转后序表达式，方便运算
     * 表达式从左到右入栈
     * 则栈底元素（先入栈）在表达式的左边，栈顶元素（后入栈）在表达式左边
     */
    public void infixToPostExpression() {
        Stack<Code> codeStack = new Stack<>();
        Code code;
        for (Code value : infixExpr) {
            code = value;
            if (code.isDigit()) {
                suffixExpr.push(code);
            } else if (code.isOperator()) {
                if (code.getValue() == ')') {
                    Code pop;
                    while ((pop = codeStack.pop()) != null && pop.getValue() != '(') {
                        suffixExpr.push(pop);
                    }
                    continue;
                }
                Code peekCode;
                while ((peekCode = codeStack.peek()) != null && peekCode.getValue() != '(' && code.compareTo(peekCode) <= 0) {
                    suffixExpr.push(codeStack.pop());
                }
                codeStack.push(code);
            }
        }
        while ((code = codeStack.pop()) != null) {
            suffixExpr.push(code);
        }
    }

    /**
     * 转前缀表达式，
     * 表达式从右到左入栈，则栈顶元素（后入栈）在表达式右边，栈底元素（先入栈）在表达式左边
     * 这一点和后缀表达式不同
     */
    public void infixToPrefixExpression() {
        Stack<Code> codeStack = new Stack<>();
        Code code;
        Iterator<Code> iterator = infixExpr.reverseIterator();
        while (iterator.hasNext()) {
            code = iterator.next();
            if (code.isDigit()) {
                prefixExpr.unshift(code);
            } else if (code.isOperator()) {
                if (code.getValue() == '(') {
                    Code pop;
                    while ((pop = codeStack.pop()) != null && pop.getValue() != ')') {
                        prefixExpr.unshift(pop);
                    }
                    continue;
                }
                Code peekCode;
                while ((peekCode = codeStack.peek()) != null && peekCode.getValue() != ')' && code.compareTo(peekCode) < 0) {
                    prefixExpr.unshift(codeStack.pop());
                }
                codeStack.push(code);
            }
        }
        while ((code = codeStack.pop()) != null) {
            prefixExpr.unshift(code);
        }
    }

    /**
     * 从左到右的表达式，先出栈的在表达式右边，后出栈的在表达式左边
     * 从右到左的表达式，先出栈的在表达式左边，后出栈的在表达式右边
     * 数字的出栈运算并重新入栈
     */
    private void digitOperation(Stack<Code> digitStack, Code code, boolean leftToRight) {
        Code upCode = digitStack.pop();
        Code downCode = digitStack.pop();
        Code result;
        if(leftToRight) {
            result = Operator.operation(downCode, upCode, code);
        } else {
            result = Operator.operation(upCode, downCode, code);
        }
        digitStack.push(result);
    }

    /**
     * 5 + (3 * 4) - 2
     * 5 + (3 * 4) - 2
     */
    public float infixOperation() {
        Stack<Code> digitStack = new Stack<>();
        Stack<Code> operatorStack = new Stack<>();
        Code code;
        while ((code = infixExpr.poll()) != null) {
            if (code.isDigit()) {
                digitStack.push(code);
            } else {
                if (code.getValue() == ')') {
                    Code pop;
                    while ((pop = operatorStack.pop()) != null && pop.getValue() != '(') {
                        digitOperation(digitStack, pop, true);
                    }
                    continue;
                }
                Code peekCode;
                while ((peekCode = operatorStack.peek()) != null && peekCode.getValue() != '(' && code.compareTo(peekCode) <= 0) {
                    Code pop = operatorStack.pop();
                    digitOperation(digitStack, pop, true);
                }
                operatorStack.push(code);
            }
        }
        while ((code = operatorStack.pop()) != null) {
            digitOperation(digitStack, code, true);
        }
        return digitStack.pop().getDecimal();
    }

    /**
     * 5 + (3 * 4) - 2
     * 5 3 4 * + 2 -
     */
    public float suffixOperation() {
        Stack<Code> operationStack = new Stack<>();
        Code code;
        while ((code = suffixExpr.poll()) != null) {
            if (code.isDigit()) {
                operationStack.push(code);
            } else {
                digitOperation(operationStack, code, true);
            }
        }
        return operationStack.pop().getDecimal();
    }

    /**
     * 5 + (3 * 4) - 2
     * - + 5 * 3 4 2
     */
    public float prefixOperation() {
        Stack<Code> operationStack = new Stack<>();
        Code code;
        while ((code = prefixExpr.pop()) != null) {
            if (code.isDigit()) {
                operationStack.push(code);
            } else {
                digitOperation(operationStack, code, false);
            }
        }
        return operationStack.pop().getDecimal();
    }

    private void pushDigit(Stack<Character> digitCache) {
        if (digitCache.isEmpty()) return;
        Character ch;
        int bit = 1;
        int lastDigit = 0;
        float dotDigit = 0f;
        int temp;
        while ((ch = digitCache.pop()) != null) {
            if (ch == '.') {
                dotDigit = lastDigit * 1f / bit;
                bit = 1;
                lastDigit = 0;
            } else {
                temp = ch - '0';
                lastDigit = lastDigit + temp * bit;
                bit *= 10;
            }
        }
        float finalDigit = lastDigit + dotDigit;
        Code digitCode = new Code(lastDigit, true);
        digitCode.setDecimal(finalDigit);
        infixExpr.push(digitCode);
    }

    @Override
    public void println() {
        PrintUtils.println("\n----------------OriginExpression---------------");
        PrintUtils.println(originExpr);
        PrintUtils.println("\n----------------InfixExpression---------------");
        this.infixExpr.println();
        PrintUtils.println("\n----------------PostExpression---------------");
        this.suffixExpr.println();
        PrintUtils.println("\n----------------PrefixExpression---------------");
        this.prefixExpr.println();
        PrintUtils.println();
        PrintUtils.println();
    }
}
