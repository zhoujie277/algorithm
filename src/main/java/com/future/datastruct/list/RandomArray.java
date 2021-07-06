package com.future.datastruct.list;

import edu.princeton.cs.algs4.StdRandom;

public class RandomArray {
    private int[] array;

    public RandomArray(int len) {
        array = new int[len];
        generate();
    }

    public void generate() {
        for (int i = 0; i < array.length; i++) {
            array[i] = StdRandom.uniform(array.length);
        }
    }

    public int[] getArray() {
        return array;
    }
}
