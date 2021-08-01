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
        PrintUtils.println(heap);

        int size = heap.size();
        Assert.assertEquals(permutation.length, size);

        int element = heap.remove();
        Assert.assertEquals(1, element);
        PrintTreeUtil.printIndexHeap(heap);
        PrintUtils.println(heap);

        BinaryIndexHeap.ElementIndex maxIndex = heap.getElementIndex();
        PrintUtils.println("\nmaxIndex=" + maxIndex);
        int update = heap.update(maxIndex, 54);
        Assert.assertEquals(12, update);
        PrintTreeUtil.printIndexHeap(heap);
        PrintUtils.println(heap);

        heap.add(26);
        PrintTreeUtil.printIndexHeap(heap);
        PrintUtils.println(heap);
    }

    @Test
    public void testSame() {
        int[] permutation = new int[]{4, 8, 8, 8, 88, 8, 3, 8, 2, 8, 8, 1, 8};
        Integer[] convert = ArrayUtils.wrap(permutation);
        PrintUtils.println(convert);
        BinaryIndexHeap<Integer> heap = new BinaryIndexHeap<>(convert);
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

    @Test
    public void testEmpty() {
        BinaryIndexHeap<Integer> heap = new BinaryIndexHeap<>();
        int[] array = new int[]{23, 33, 45, 63, 12, 20, 41};
        for (int i : array) {
            heap.add(i);
        }

        PrintTreeUtil.printIndexHeap(heap);
    }

    private class Person {
        String name;
    }

    private class Student extends Person {
        int age;
    }

    private class Exam extends Student {
        int score;
    }
}
