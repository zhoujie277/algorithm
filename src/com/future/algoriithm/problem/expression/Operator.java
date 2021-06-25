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
        float result = 0f;
        int operatorValue = operator.getValue();
        float leftValue = left.getDecimal();
        float rightValue = right.getDecimal();
        switch (operatorValue) {
            case '+':
                result = leftValue + rightValue;
                break;
            case '-':
                result = leftValue - rightValue;
                break;
            case '*':
                result = leftValue * rightValue;
                break;
            case '/':
                result = leftValue / rightValue;
                break;
        }
        Code resCode = new Code((int)result, true);
        resCode.setDecimal(result);
        return resCode;
    }
}
