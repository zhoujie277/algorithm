package com.future.leetcode.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 数据流中的移动平均值
 * 给出一串整数流和窗口大小，计算滑动窗口中所有整数的平均值。
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1 // 返回 1.00000
 * m.next(10) = (1 + 10) / 2 // 返回 5.50000
 * m.next(3) = (1 + 10 + 3) / 3 // 返回 4.66667
 * m.next(5) = (10 + 3 + 5) / 3 // 返回 6.00000
 *
 * @author jayzhou
 */
public class MovingAverage {

    private final Deque<Integer> queue = new ArrayDeque<>();

    private final int size;
    private long sum;

    public MovingAverage(int size) {
        this.size = size;
        sum = 0;
    }

    /*
     * @param val: An integer
     * @return:
     */
    @SuppressWarnings("all")
    public double next(int val) {
        queue.addLast(val);
        sum += val;
        if (queue.size() > size) {
            int first = queue.pollFirst();
            sum -= first;
        }
        return sum * 1d / queue.size();
    }

    public static void main(String[] args) {
        MovingAverage m = new MovingAverage(3);
        System.out.println(m.next(1));
        System.out.println(m.next(10));
        System.out.println(m.next(3));
        System.out.println(m.next(5));
    }
}
