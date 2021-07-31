package com.future.datastruct.heap;

import com.future.utils.ArrayUtils;
import com.future.utils.PrintUtils;
import com.future.utils.drawtree.PrintTreeUtil;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

public class BinaryHeapTest {

    @Test
    public void testHeap() {
        int[] permutation = StdRandom.permutation(100, 10);
        PrintUtils.println(permutation);
        Integer[] wrap = ArrayUtils.wrap(permutation);
        BinaryHeap<Integer> heap = new BinaryHeap<>(wrap);
        PrintTreeUtil.printHeap(heap);
    }
}
