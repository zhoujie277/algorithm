package com.future.algoriithm.problem.expression;

import com.future.algoriithm.PrintUtils;

public class Calculator {

    public static void main(String[] args) {
//        String expr = "721 * 22 * 3 - 4 - 42 + 6875";
//        String expr = "7*2*2-5+1-5+3-3";
        String expr = "5 * (6 + 3 * (5-2)) - 8.6 + 3.45";
//        String expr = "4 + 3 - 2";
        Expression expression = new Expression(expr);
        expression.parse();
        expression.println();
        float postResult = expression.suffixOperation();
        float infixResult = expression.infixOperation();
        PrintUtils.println("\nexpress operation postResult is " + postResult + ",infixResult=" + infixResult);
    }
}
