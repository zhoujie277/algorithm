package com.future.java;

import org.junit.Test;

public class RecursiveTest {

    private int recursiveCount = 0;

    private int fib(int n) {
        if (n <= 1) return 1;
        recursiveCount++;
        int r = fib(n - 1) + fib(n - 2);
        recursiveCount--;
        return r;
    }

    @Test
    public void testRecursiveDepth() {
        int fib = fib(6);
        System.out.println("fib=" + fib);
        System.out.println(recursiveCount);
    }
}
