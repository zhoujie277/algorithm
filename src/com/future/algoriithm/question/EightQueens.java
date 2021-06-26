package com.future.algoriithm.question;

import com.future.algoriithm.PrintUtils;
import com.future.algoriithm.Printable;
import com.future.algoriithm.array.TwoIntArray;

/**
 * 八皇后问题
 * 在8*8的棋盘上，摆放8个皇后，使其不能互相攻击
 * 即：任意两个皇后不能在同一行、同一列、同一斜线上，问有多少种摆法
 * 该解决方案性能上应该比第二种更优；
 * 采用了位运算，减少了冲突判断的逻辑
 * 采用比对前标记，回溯时清除标记的做法。
 */
public class EightQueens implements Printable {

    private final TwoIntArray chess;
    private int chessCount;
    private int putCount;
    private final int DEFAULT_CHESS_VALUE = 0;

    private int runMarkCount = 0;
    private int recursionCount = 0;

    public EightQueens(int rowCount) {
        this.chessCount = rowCount;
        chess = new TwoIntArray(rowCount, rowCount);
        chess.initValue(DEFAULT_CHESS_VALUE);
        this.putCount = 0;
    }

    /**
     * 每放上一个皇后，给不能再放皇后的位置用异或的方式打一个标记，等放完后，取消标记
     *
     * @param x 位置x
     * @param y 位置y
     * @param n 需要放皇后的个数
     */
    private void mark(int x, int y, int n) {
        if (!chess.inRange(x, y)) return;
        runMarkCount++;
        int code = 1 << n;
        for (int i = 0; i < chessCount; i++) {
            chess.or(x, i, code);
            chess.or(i, y, code);
            chess.or(i, y - x + i, code);
            chess.or(i, x + y - i, code);
        }
    }

    private void unmark(int x, int y, int n) {
        if (!chess.inRange(x, y)) return;
        int code = 1 << n;
        for (int i = 0; i < chessCount; i++) {
            chess.andNot(x, i, code);
            chess.andNot(i, y, code);
            chess.andNot(i, y - x + i, code);
            chess.andNot(i, x + y - i, code);
        }
    }

    private void putChess() {
        for (int i = 0; i < chessCount; i++) {
            putChess(0, i, chessCount);
        }
    }

    private boolean putChess(int x, int y, int n) {
        recursionCount++;
        int code = chess.get(x, y);
        if (code != DEFAULT_CHESS_VALUE) return false;
        if (code == DEFAULT_CHESS_VALUE && n == 1) {
            putCount++;
            return true;
        }
        mark(x, y, n);
        int i = x + 1;
        for (int j = 0; j < chessCount; j++) {
            putChess(i, j, n - 1);
        }
        unmark(x, y, n);
        return false;
    }

    @Override
    public void println() {
        PrintUtils.println("--------八皇后棋盘Solution1--------");
//        chess.println();
        PrintUtils.println("total put count is " + putCount);
        PrintUtils.println("runMarkCount is " + runMarkCount);
        PrintUtils.println("recursionCount is " + recursionCount);
        PrintUtils.println();
    }

    public static void testSolution1(int n) {
        EightQueens queens = new EightQueens(n);
        queens.println();
        queens.putChess();
        queens.println();
    }

    public static void testSolution2(int n) {
        QueensSolution2 queens = new QueensSolution2(n);
        queens.putChess();
        queens.println();
    }

    public static void main(String[] args) {
        int length = 8;
//        testSolution1(length);
        testSolution2(length);
    }
}

/**
 * 八皇后解法2
 * 该解法在回溯思想上是一致的
 * 区别在于数据结构的设计上
 * 该一维数组的设计巧妙
 * 例：arr[8] = {0,4,7,5,2,6,1,3}
 * arr[i] = val;
 * 下标表示第几个皇后放在第几行
 * val表示放在行索引为i的列索引
 */
class QueensSolution2 implements Printable {

    // 保存皇后放置位置的结果
    private int[] queens;
    private int length;
    private int runCanPutCount = 0;
    private int putCount = 0;

    public QueensSolution2(int length) {
        this.length = length;
        queens = new int[length];
    }

    // n是即将要放置皇后的索引
    public boolean canPut(int n) {
        runCanPutCount++;
        for (int i = 0; i < n; i++) {
            if (queens[i] == queens[n]) {
                return false;
            }
            // 不在同一斜线的判定条件,通过y = kx+b, 可知其中k=1（斜线）或者-1（反斜线）
            // 故y = x + b; 设x1,y1;x2,y2为其中连个点，则满足 Math.abs(y1-y2) = Math.abs(x1-x2);
            if (Math.abs(n - i) == Math.abs(queens[n] - queens[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 从第一个皇后放起
     *
     * @param n 放第几个皇后
     */
    public void put(int n) {
        if (n == length) {
            // n是放置皇后的索引，length是长度，当n==length时，说明，第8个也已经放成功
            putCount++;
            PrintUtils.print(queens);
            return;
        }
        for (int i = 0; i < length; i++) {
            queens[n] = i;
            if (canPut(n)) {
                put(n + 1);
            }
        }
    }

    public void putChess() {
        put(0);
    }

    @Override
    public void println() {
        PrintUtils.println("--------八皇后棋盘Solution2--------");
        PrintUtils.println("total put count is " + putCount);
        PrintUtils.println("runCanPutCount is " + runCanPutCount);
        PrintUtils.println();
    }
}
