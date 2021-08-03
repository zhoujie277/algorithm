package com.future.algoriithm.back;


/**
 * 迭代+回溯
 */
public class NQueensIterable extends NQueenStrategy {

    public NQueensIterable(NQueens context) {
        super(context);
    }

    @Override
    public void place() {
        put();
    }

    private boolean canPut(int row, int column) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == column || Math.abs(row - i) == Math.abs(column - queens[i])) {
                return false;
            }
        }
        return true;
    }

    private void put() {
        int row = 0;
        int col = 0;
        while (true) {
            int oldRow = row;
            for (int i = col; i < N; i++) {
                if (canPut(row, i)) {
                    // 末尾
                    if (row == N - 1) {
                        context.addPutCount(new NQueens.PutWay(N, queens));
                        col = queens[--row] + 1;
                        break;
                    }
                    queens[row++] = i;
                    col = 0;
                    break;
                }
            }
            if (oldRow == row) {
                if (row == 0 && queens[0] == N - 1) break;
                if (row > 0) row--;
                col = queens[row] + 1;
            }
        }
    }
}
