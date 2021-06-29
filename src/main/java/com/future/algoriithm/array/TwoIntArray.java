package com.future.algoriithm.array;

import com.future.algoriithm.utils.PrintUtils;
import com.future.algoriithm.utils.Printable;

@SuppressWarnings("unused")
public class TwoIntArray implements Printable {

    private final int[][] array;
    private final int row;
    private final int column;

    public TwoIntArray(int row, int column) {
        this.row = row;
        this.column = column;
        array = new int[row][column];
    }

    public void set(int x, int y, int value) {
        if (!inRange(x, y)) return;
        array[x][y] = value;
    }

    public int get(int x, int y) {
        return array[x][y];
    }

    public int row() {
        return this.row;
    }

    public int column() {
        return this.column;
    }

    public int column(int x) {
        return this.array[x].length;
    }

    public boolean inRange(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < column;
    }

    public void xor(int x, int y, int value) {
        if (!inRange(x, y)) return;
        array[x][y] ^= value;
    }

    public void not(int x, int y) {
        if (!inRange(x, y)) return;
        array[x][y] = ~array[x][y];
    }

    public void andNot(int x, int y, int code) {
        if (!inRange(x, y)) return;
        array[x][y] &= (~code);
    }

    public void or(int x, int y, int code) {
        if (!inRange(x, y)) return;
        array[x][y] ^= code;
    }

    public void and(int x, int y, int code) {
        if (!inRange(x, y)) return;
        array[x][y] &= code;
    }

    @Override
    public void println() {
        for (int[] ints : array) {
            for (int j = 0; j < ints.length; j++) {
                PrintUtils.print(ints[j] + "\t");
            }
            PrintUtils.println();
        }
    }

    public void initValue(int value) {
        for (int[] ints : array) {
            for (int j = 0; j < ints.length; j++) {
                ints[j] = value;
            }
        }
    }
}
