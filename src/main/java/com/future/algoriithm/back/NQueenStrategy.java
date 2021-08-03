package com.future.algoriithm.back;

public abstract class NQueenStrategy {
    protected final NQueens context;
    protected final int[] queens;
    protected final int N;

    public NQueenStrategy(NQueens context) {
        this.context = context;
        N = context.N;
        queens = new int[context.N];
    }

    public abstract void place();
}
