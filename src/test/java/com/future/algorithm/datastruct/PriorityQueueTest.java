package com.future.algorithm.datastruct;

import com.future.algoriithm.queue.PriorityHeapQueue;
import com.future.algoriithm.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class PriorityQueueTest {

    private int[] array = null;
    private PriorityHeapQueue<Integer> queue = null;

    @Before
    public void setup() {
        array = StdRandom.permutation(10);
        queue = new PriorityHeapQueue(array);
    }

    @Test
    public void testInsert() {
        queue.push(5);
        queue.push(14);
    }

    @After
    public void println() {
        PrintUtils.println(array);
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            PrintUtils.print(next + "\t");
        }
        PrintUtils.println();
    }
}
