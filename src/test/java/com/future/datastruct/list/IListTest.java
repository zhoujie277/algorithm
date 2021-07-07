package com.future.datastruct.list;

import com.future.datastruct.list.define.AbstractSequence;
import com.future.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class IListTest {

    private AbstractSequence<Integer> list = new DynamicArray<>();

    @Before
    public void setup() {
        int[] array = StdRandom.permutation(10);
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
    }

    @Test
    public void testFunction() {
        PrintUtils.println("before remove");
        PrintUtils.println(list);
        int index = list.indexOf(8);
        PrintUtils.println("The obj will remove at indexOf is " + index);

        list.remove(new Integer(8));

        PrintUtils.println("------after remove-----");
        PrintUtils.println(list);

        PrintUtils.println("------add(int index)-----");
        list.add(0, 23);
        PrintUtils.println(list);
        list.add( 2, 27);
        PrintUtils.println(list);
    }

    @After
    public void println() {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            PrintUtils.print(next + " ");
        }
    }
}
