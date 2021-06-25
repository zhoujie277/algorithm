package com.future.algoriithm.problem.expression;

import com.future.algoriithm.PrintUtils;
import com.future.algoriithm.Printable;
import com.future.algoriithm.queue.Queue;
import com.future.algoriithm.stack.Stack;
import edu.princeton.cs.algs4.In;

import java.util.Iterator;

/**
 * 队列+栈实现的表达式的解析、存储、运算
 *
 * @author zhoujie
 */
public class Expression implements Printable {
    private String originExpr;

    private Queue<Code> infixExpr = new Queue<>();
    private Queue<Code> prefixExpr = new Queue<>();
    private Queue<Code> suffixExpr = new Queue<>();

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
        infixToPostExpression();
    }

    /**
     * 转后序表达式，方便运算
     */
    public void infixToPostExpression() {
        Stack<Code> codeStack = new Stack<>();
        Iterator<Code> iterator = infixExpr.iterator();
        Code code;
        while (iterator.hasNext()) {
            code = iterator.next();
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
                if (!codeStack.isEmpty()) {
                    Code peekCode = codeStack.peek();
                    if (peekCode.getValue() != '(' && code.compareTo(peekCode) <= 0) {
                        suffixExpr.push(codeStack.pop());
                    }
                }
                codeStack.push(code);
            }
        }
        while ((code = codeStack.pop()) != null) {
            suffixExpr.push(code);
        }
    }

    /**
     * 数字的出栈运算并重新入栈
     */
    private void digitOperation(Stack<Code> digitStack, Code code) {
        Code right = digitStack.pop();
        Code left = digitStack.pop();
        Code result = Operator.operation(left, right, code);
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
        while ((code = infixExpr.unshift()) != null) {
            if (code.isDigit()) {
                digitStack.push(code);
            } else {
                if (code.getValue() == ')') {
                    Code pop;
                    while ((pop = operatorStack.pop()) != null && pop.getValue() != '(') {
                        digitOperation(digitStack, pop);
                    }
                    continue;
                }
                if (!operatorStack.isEmpty()) {
                    Code peekCode = operatorStack.peek();
                    if (peekCode.getValue() != '(' && code.compareTo(peekCode) <= 0) {
                        Code peek = operatorStack.pop();
                        digitOperation(digitStack, peek);
                    }
                }
                operatorStack.push(code);
            }
        }
        while ((code = operatorStack.pop()) != null) {
            digitOperation(digitStack, code);
        }
        return digitStack.pop().getDecimal();
    }

    /**
     * 5 + (3 * 4) - 2
     * 5 3 4 * + 2 -
     */
    public int suffixOperation() {
        Stack<Code> operationStack = new Stack<>();
        Code code;
        while ((code = suffixExpr.unshift()) != null) {
            if (code.isDigit()) {
                operationStack.push(code);
            } else {
                digitOperation(operationStack, code);
            }
        }
        return operationStack.pop().getValue();
    }

    /**
     * 5 + (3 * 4) - 2
     * - + 5 * 3 4 2
     */
    public int prefixOperation() {
        return 0;
    }

    private int pushDigit(Stack<Character> digitCache) {
        if (digitCache.isEmpty()) return 0;
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
        return lastDigit;
    }

    @Override
    public void println() {
        PrintUtils.println("\n----------------OriginExpression---------------");
        PrintUtils.println(originExpr);
        PrintUtils.println("\n----------------InfixExpression---------------");
        this.infixExpr.println();
        PrintUtils.println("\n----------------PostExpression---------------");
        this.suffixExpr.println();
    }
}
