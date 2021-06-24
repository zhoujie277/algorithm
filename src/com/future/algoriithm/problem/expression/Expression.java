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
    private Queue<Code> postExpr = new Queue<>();

    public Expression(String originExpr) {
        this.originExpr = originExpr;
    }

    public void parse() {
        Stack<Integer> digitCache = new Stack<>();
        StringBuilder digitBuilderr = new StringBuilder();
        for (int i = 0; i < originExpr.length(); i++) {
            char c = originExpr.charAt(i);
            if (Character.isDigit(c)) {
//                digitBuilder.append(c);
                int num = c - '0';
                digitCache.push(num);
                if (i == originExpr.length() - 1) {
                    pushDigit(digitCache);
//                    pushDigit(digitBuilder);
                }
            } else {
                pushDigit(digitCache);
//                pushDigit(digitBuilder);
                if (!Character.isWhitespace(c)) {
                    infixExpr.push(new Code(c, false));
                }
            }
        }
        toPostExpression();
    }

    /**
     * 转后序表达式，方便运算
     *
     * @return
     */
    public void toPostExpression() {
        Stack<Code> codeStack = new Stack<>();
        Iterator<Code> iterator = infixExpr.iterator();
        Code code;
        while (iterator.hasNext()) {
            code = iterator.next();
            if (code.isDigit()) {
                postExpr.push(code);
            } else if (code.isOperator()) {
                if (code.getValue() ==')') {
                    Code pop;
                    while ((pop = codeStack.pop()) != null && pop.getValue() != '(') {
                        postExpr.push(pop);
                    }
                    continue;
                }
                if (!codeStack.isEmpty()) {
                    Code peekCode = codeStack.peek();
                    if (peekCode.getValue() != '(' && code.compareTo(peekCode) <= 0) {
                        postExpr.push(codeStack.pop());
                    }
                }
                codeStack.push(code);
            }
        }
        while ((code = codeStack.pop()) != null) {
            postExpr.push(code);
        }
    }

    public int infixOperation() {
        Stack<Code> digitStack = new Stack<>();
        Stack<Code> operatorStack = new Stack<>();
        Code code;
        while ((code = infixExpr.unshift()) != null) {
            if (code.isDigit()) {
                digitStack.push(code);
            } else {
                if (code.getValue() ==')') {
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
        return digitStack.pop().getValue();
    }

    /**
     * 数字的出栈运算并重新入栈
     * @param digitStack
     * @param code
     */
    private void digitOperation(Stack<Code> digitStack, Code code) {
        Code right = digitStack.pop();
        Code left = digitStack.pop();
        Code result = Operator.operation(left, right, code);
        digitStack.push(result);
    }

    public int postOperation() {
        Stack<Code> operationStack = new Stack<>();
        Code code;
        while ((code = postExpr.unshift()) != null) {
            if (code.isDigit()) {
                operationStack.push(code);
            } else {
                digitOperation(operationStack, code);
            }
        }
        return operationStack.pop().getValue();
    }

    private int pushDigit(Stack<Integer> digitCache) {
        if (digitCache.isEmpty()) return 0;
        Integer ch;
        int bit = 1;
        int lastDigit = 0;
        while ((ch = digitCache.pop()) != null) {
            lastDigit = lastDigit + ch * bit;
            bit *= 10;
        }
        if (lastDigit != 0) {
            infixExpr.push(new Code(lastDigit, true));
        }
        return lastDigit;
    }

    private int pushDigit(StringBuilder digitBuilder) {
        if (digitBuilder.length() == 0) return 0;
        int lastDigit = Integer.parseInt(digitBuilder.toString());
        infixExpr.push(new Code(lastDigit, true));
        digitBuilder.delete(0, digitBuilder.length());
        return lastDigit;
    }

    @Override
    public void println() {
        PrintUtils.println("\n----------------InfixExpression---------------");
        this.infixExpr.println();
        PrintUtils.println("\n----------------PostExpression---------------");
        this.postExpr.println();
    }
}
