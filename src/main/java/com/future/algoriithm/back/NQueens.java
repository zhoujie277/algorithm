package com.future.algoriithm.back;

import java.util.Arrays;

/**
 * N 皇后问题
 * <p>
 * 要求获取共有多少种摆法，并且每种摆法的具体位置。
 *
 * @author jayzhou
 */
public class NQueens {

    public static class PutWay {
        final int[] way;
        PutWay next;

        public PutWay(int n, int[] arr) {
            this.way = new int[n];
            System.arraycopy(arr, 0, way, 0, arr.length);
        }

        @Override
        public String toString() {
            return "PutWay{" +
                    "way=" + Arrays.toString(way) +
                    '}';
        }
    }

    public static final byte RECURSIVE = 1;
    public static final byte ITERABLE = RECURSIVE << 1;

    final int N;
    PutWay putWays;
    byte strategyType;

    public NQueens(int n, byte strategy) {
        this.N = n;
        this.strategyType = strategy;
    }

    public NQueens(int n) {
        this(n, RECURSIVE);
    }

    public void place() {
        NQueenStrategy strategy;
        if (strategyType == RECURSIVE) {
            strategy = new NQueenRecursive(this);
        } else if (strategyType == ITERABLE) {
            strategy = new NQueensIterable(this);
        } else {
            throw new UnsupportedOperationException();
        }
        strategy.place();
    }

    void addPutCount(PutWay way) {
        if (putWays == null) {
            putWays = way;
            return;
        }
        PutWay p = putWays;
        while (p.next != null) {
            p = p.next;
        }
        p.next = way;
    }

    public static void main(String[] args) {
        NQueens queens = new NQueens(8);
        queens.place();
        PutWay way = queens.putWays;
        int wayCount = 0;
        while (way != null) {
            wayCount++;
            System.out.println(way);
            way = way.next;
        }
        System.out.println("wayCount=" + wayCount);
    }

}
