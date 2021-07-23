package com.future.utils.drawtree;

import com.future.utils.PrintUtils;

class DrawNode {
    double x;
    double y;
    String value;
    boolean red;

    DrawNode left;
    DrawNode right;
    DrawNode parent;
    int depth;
    int column;

    int consoleColumn;
    int leftPos;
    int rightPos;
    int valuePos = 0;
    int valueRightPos = 0;
    int leftLinePos = 0;
    int rightLinePos = 0;

    public DrawNode(String value) {
        this.value = value;
    }

    public int printLine(int offset) {
        int leftPosition = leftPos;
        int rightPosition = rightPos;
        if (leftPosition < valuePos) {
            for (int i = offset; i < leftPosition; i++) {
                PrintUtils.print(" ");
            }
            PrintUtils.print("|");
            offset = leftPosition + 1;
        }
        if (rightPosition > valueRightPos) {
            for (int i = offset; i < rightPosition; i++) {
                PrintUtils.print(" ");
            }
            PrintUtils.print("|");
            offset = rightPosition + 1;
        }
        return offset;
    }

    public int print(int offset) {
        int factor = 4;

        if (parent == null) {
            valuePos = factor * consoleColumn;
        } else {
            if (parent.left == this) {
                valuePos = parent.leftPos;
            } else {
                valuePos = parent.rightPos;
            }
        }
        valueRightPos = valuePos + value.length();
        if (left != null) {
            // 由于输出了值可能产生了偏移，所以算父节点的相对位置会比较靠谱
            leftPos = valuePos - factor * (consoleColumn - left.consoleColumn);
//            leftPos = factor * left.consoleColumn;
            leftLinePos = (leftPos + valuePos) >>> 1;
        } else {
            leftPos = valuePos;
        }
        if (right != null) {
            rightPos = valueRightPos + factor * (right.consoleColumn - consoleColumn);
//            rightPos = factor * right.consoleColumn ;
            rightLinePos = (rightPos + valueRightPos) >>> 1;
        } else {
            rightPos = valueRightPos;
        }
        int valueOffsetPos = valuePos - offset;
        int valueRightOffsetPos = valueRightPos - offset;
        int offsetLeftPos = leftPos - offset;
        int offsetRightPos = rightPos - offset;
        for (int i = 0; i < offsetLeftPos; i++) {
            PrintUtils.print(" ");
        }
        for (int i = offsetLeftPos; i < valueOffsetPos; i++) {
            PrintUtils.print("-");
        }
        if (!red) {
            PrintUtils.print(value);
        } else {
            PrintUtils.red(value);
        }
        for (int i = valueRightOffsetPos; i < offsetRightPos; i++) {
            PrintUtils.print("-");
        }
        return rightPos;
    }

    @Override
    public String toString() {
        return value;
    }
}
