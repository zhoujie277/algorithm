package com.future.algoriithm.problem.expression;

/**
 * @author zhoujie
 */
@SuppressWarnings("unused")
public class Operator {
    public static boolean isBinaryOperator(char code) {
        return code == '+' || code == '-' || code == '*' || code == '/';
    }

    public static boolean isDot(char code) {
        return code == '.';
    }

    public static Code operation(Code left, Code right, Code operator) {
        int result = 0;
        int operatorValue = operator.getValue();
       switch (operatorValue) {
           case '+':
               result = left.getValue() + right.getValue();
               break;
           case '-':
               result = left.getValue() - right.getValue();
               break;
           case '*':
               result = left.getValue() * right.getValue();
               break;
           case '/':
               result = left.getValue() / right.getValue();
               break;
       }
        return new Code(result, true);
    }
}
