package com.future.algoriithm.problem.expression;

import sun.util.resources.CalendarData;

class MaskCode {
    public static final int DIGIT = 0;
    public static final int LOW = 1;
    public static final int MIDDLE = 2;
    public static final int HIGH = 4;
}

public class Code implements Comparable<Code> {
    private int mask;
    private int value;

    public Code(int value) {
        this(value, true);
    }

    public Code(int value, boolean digit) {
        this.value = value;
        initMask(digit);
    }

    private void initMask(boolean digit) {
        if (digit) {
            this.mask = MaskCode.DIGIT;
        } else {
            if (value == '+' || value == '-') {
                this.mask = MaskCode.LOW;
            } else if (value == '*' || value == '/') {
                this.mask = MaskCode.MIDDLE;
            } else if (value == '(' || value == ')') {
                this.mask = MaskCode.HIGH;
            } else {
                //TODO: 待完善
                this.mask = MaskCode.HIGH;
            }
        }
    }

    public boolean isDigit() {
        return mask == MaskCode.DIGIT;
    }

    public boolean isOperator() {
        return mask > MaskCode.DIGIT;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Code o) {
        return mask - o.mask;
    }

    @Override
    public String toString() {
        if (isOperator()) {
            return (char) value + " ";
        } else if(isDigit()){
            return value + " ";
        }
        return "Code{" +
                "mask=" + mask +
                ", value=" + value +
                '}';
    }
}
