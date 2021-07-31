package com.future.datastruct.heap;

import com.future.utils.ArrayUtils;
import com.future.utils.PrintUtils;
import com.future.utils.drawtree.PrintTreeUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

public class BinaryIndexHeapTest {

    @Test
    public void testIndexHeap() {
//        int[] permutation = StdRandom.permutation(100, 10);
        int[] permutation = new int[]{84, 43, 60, 50, 12, 48, 30, 44, 65, 1};
        Integer[] convert = ArrayUtils.wrap(permutation);
        PrintUtils.println(convert);
        BinaryIndexHeap<Integer> heap = new BinaryIndexHeap<>(convert);
        PrintTreeUtil.printIndexHeap(heap);

        int size = heap.size();
        Assert.assertEquals(size, permutation.length);

        int index = heap.removeIndex();
        Assert.assertEquals(index, 9);
        PrintTreeUtil.printIndexHeap(heap);

        index = heap.getIndex();
        PrintUtils.println("\nindex=" + index);
        Assert.assertEquals(heap.get(index), heap.get());

        int update = heap.update(index, 54);
        Assert.assertEquals(12, update);
        PrintTreeUtil.printIndexHeap(heap);

    }

    @Test
    public void testComparator() {
        Comparator<Person> comparator = (Person s1, Person s2) -> {
            return s1.name.compareTo(s2.name);
        };
        BinaryIndexHeap<Student> heap = new BinaryIndexHeap<>(comparator);
        heap.clear();
    }

    private class Person {
        String name;
    }

    private class Student extends Person{
        int age;
    }

    private class Exam extends Student {
        int score;
    }
}
