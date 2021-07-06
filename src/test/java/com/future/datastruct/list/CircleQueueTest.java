package com.future.datastruct.list;

import com.future.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class CircleQueueTest {

    private CircleQueue<Integer> circleQueue;

    @Before
    public void setup() {
        circleQueue = new CircleQueue<>();
    }

    @Test
    public void testAdd() {
        int[] arr = StdRandom.permutation(10);
        PrintUtils.println(arr);
        for (int i = 0; i < arr.length; i++) {
            circleQueue.add(arr[i]);
        }

        Assert.assertEquals(arr[arr.length - 1], (int) circleQueue.remove());
        Assert.assertEquals(arr[0], (int) circleQueue.peek());
        Assert.assertEquals(arr[0], (int) circleQueue.poll());

        for (int i = 1; i <= 2; i++) {
            circleQueue.add(i * 10);
        }
        for (int i = 0; i < 8; i++) {
            circleQueue.poll();
        }

        for (int i = 0; i < 3; i++) {
            circleQueue.offer(i);
        }
        for (int i = 0; i < 2; i++) {
            circleQueue.add(i + 11);
        }
        PrintUtils.println("before remove");
        PrintUtils.println(circleQueue);
        circleQueue.remove(10);

        PrintUtils.println("------after remove-----");
        PrintUtils.println(circleQueue);
        for (int i = 1; i < 12; i++) {
            circleQueue.add(i * 7);
        }
    }

    @After
    public void println() {
        PrintUtils.println(circleQueue);
        Iterator<Integer> iterator = circleQueue.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            PrintUtils.print(next + " ");
        }
    }

}
