package com.future.algoriithm.back;

/**
 * N 皇后问题
 * <p>
 * 要求获取共有多少种摆法，并且每种摆法的具体位置。
 * 回溯 + 递归
 *
 * @author jayzhou
 */
public class NQueenRecursive extends NQueenStrategy {

    public NQueenRecursive(NQueens context) {
        super(context);
    }

    @Override
    public void place() {
        place(0);
    }

    /**
     * 放第几个皇后
     * 递归 + 回溯
     */
    private void place(int row) {
        if (row == N) {
            context.addPutCount(new NQueens.PutWay(N, queens));
            return;
        }
        for (int j = 0; j < N; j++) {
            queens[row] = j;
            if (canPut(row, j)) {
                place(row + 1);
            }
        }
    }

    private boolean canPut(int row, int column) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == column || Math.abs(row - i) == Math.abs(column - queens[i])) {
                return false;
            }
        }
        return true;
    }
}
