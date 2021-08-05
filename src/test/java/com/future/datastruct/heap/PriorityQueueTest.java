package com.future.datastruct.heap;

import com.future.utils.ArrayUtils;
import com.future.utils.PrintUtils;
import com.future.utils.drawtree.PrintTreeUtil;
import org.junit.Assert;
import org.junit.Test;

public class PriorityQueueTest {

    @Test
    public void testPriorityQueue() {
//        int[] array = StdRandom.permutation(10);
        int[] array = new int[]{3, 8, 5, 1, 4, 9, 2, 0, 7, 6};
//        int[] array = new int[]{8, 5, 4, 9, 7, 6};
        PrintUtils.println(array);
        Integer[] wrap = ArrayUtils.wrap(array);
        PriorityHeap<Integer> queue = new PriorityHeap<>(wrap);
        PrintUtils.println(queue);
        Assert.assertEquals(array.length, queue.size());
        Assert.assertEquals(9, queue.getMax().intValue());
        Assert.assertEquals(0, queue.getMin().intValue());
        PrintTreeUtil.printPriority(queue, "minHeap");
        PrintTreeUtil.printPriority(queue, "maxHeap");

        for (int i = 0; i < 8; i++) {
            System.out.print(queue.delMax());
            System.out.print(" ");
//            PrintTreeUtil.printPriority(queue, "maxHeap");
//            PrintTreeUtil.printPriority(queue, "minHeap");
        }
        PrintUtils.println();
        while (!queue.isEmpty()) {
            System.out.print(queue.delMin());
            System.out.print(" ");
//            PrintTreeUtil.printPriority(queue, "maxHeap");
//            PrintTreeUtil.printPriority(queue, "minHeap");
        }
    }
}
