package com.future.algoriithm.question.expression;

import com.future.utils.PrintUtils;
import com.future.utils.Printable;
import com.future.datastruct.list.DualLinkedList;
import com.future.datastruct.list.LinkedStack;

import java.util.Iterator;

/**
 * 队列+栈实现的表达式的解析、存储、运算
 * 前缀表达式/中缀表达式/后缀表达式
 *
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
        LinkedStack<Character> digitCache = new LinkedStack<>();
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
        Iterator<Code> iterator = infixExpr.iterator();
        forEachInfixExpression(iterator, ')', '(', suffixExpr, true);
    }

    /**
     * 转前缀表达式，
     * 表达式从右到左入栈，则栈顶元素（后入栈）在表达式右边，栈底元素（先入栈）在表达式左边
     * 这一点和后缀表达式不同
     */
    public void infixToPrefixExpression() {
        Iterator<Code> iterator = infixExpr.reverseIterator();
        forEachInfixExpression(iterator, '(', ')', prefixExpr, false);
    }

    private void forEachInfixExpression(Iterator<Code> iterator, char popStack, char pushStack, DualLinkedList<Code> expr, boolean leftToRight) {
        LinkedStack<Code> codeStack = new LinkedStack<>();
        Code code;
        while (iterator.hasNext()) {
            code = iterator.next();
            if (code.isDigit()) {
                addToExpression(expr, leftToRight, code);
            } else if (code.isOperator()) {
                if (code.getValue() == popStack) {
                    Code pop;
                    while ((pop = codeStack.pop()) != null && pop.getValue() != pushStack) {
                        addToExpression(expr, leftToRight, pop);
                    }
                    continue;
                }
                Code peekCode;
                while ((peekCode = codeStack.peek()) != null && peekCode.getValue() != pushStack && code.compareTo(peekCode) < 0) {
                    addToExpression(expr, leftToRight, codeStack.pop());
                }
                codeStack.push(code);
            }
        }
        while ((code = codeStack.pop()) != null) {
            addToExpression(expr, leftToRight, code);
        }
    }

    private void addToExpression(DualLinkedList<Code> expr, boolean leftToRight, Code code) {
        if (leftToRight)
            expr.push(code);
        else
            expr.unshift(code);
    }

    /**
     * 从左到右的表达式，先出栈的在表达式右边，后出栈的在表达式左边
     * 从右到左的表达式，先出栈的在表达式左边，后出栈的在表达式右边
     * 数字的出栈运算并重新入栈
     */
    private void digitOperation(LinkedStack<Code> digitStack, Code code, boolean leftToRight) {
        Code upCode = digitStack.pop();
        Code downCode = digitStack.pop();
        Code result;
        if (leftToRight) {
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
        LinkedStack<Code> digitStack = new LinkedStack<>();
        LinkedStack<Code> operatorStack = new LinkedStack<>();
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
     * 后缀表达式执行运算
     * 5 + (3 * 4) - 2
     * 5 3 4 * + 2 -
     */
    public float suffixOperation() {
        return internalOperation(suffixExpr, true);
    }

    /**
     * 前缀表达式执行运算
     * 5 + (3 * 4) - 2
     * - + 5 * 3 4 2
     */
    public float prefixOperation() {
        return internalOperation(prefixExpr, false);
    }

    private float internalOperation(DualLinkedList<Code> expr, boolean leftToRight) {
        LinkedStack<Code> operationStack = new LinkedStack<>();
        Code code;
        while ((code = (leftToRight ? expr.poll() : expr.pop())) != null) {
            if (code.isDigit()) {
                operationStack.push(code);
            } else {
                digitOperation(operationStack, code, leftToRight);
            }
        }
        return operationStack.pop().getDecimal();
    }

    private void pushDigit(LinkedStack<Character> digitCache) {
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
        PrintUtils.println(infixExpr);
        PrintUtils.println("\n----------------PostExpression---------------");
        PrintUtils.println(suffixExpr);
        PrintUtils.println("\n----------------PrefixExpression---------------");
        PrintUtils.println(prefixExpr);
        PrintUtils.println();
        PrintUtils.println();
    }
}
