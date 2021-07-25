package com.future.algorithm.sort;

import com.future.algoriithm.sort.CountSorter;
import com.future.utils.DataUtils;
import com.future.utils.PrintUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CountSorterTest {

    @Test
    public void simpleNumberTest() {
        int[] array = {2, 8, 4, 2, 1, 3, 2, 0, 4, 7, 6, 4, 5, 1, 2, 9, 8, 6, 3};
        CountSorter.CountSorting1 sorter = new CountSorter.CountSorting1();
        sorter.sort(array);
    }

    @Test
    public void counterSorter() {
        int[] array = {2, 8, 4, 2, 1, 3, 2, 0, 4, 7, 6, 4, 5, 1, 2, 9, 8, 6, 3};
        Integer[] integers = Arrays.stream(array).boxed().toArray(Integer[]::new);
        CountSorter countSorter = new CountSorter();
        countSorter.sortOrigin(integers);
        PrintUtils.println(integers);
        Assert.assertEquals(array.length, integers.length);
        Assert.assertTrue(countSorter.ascSorted());
    }

    @Test
    public void stableCounterSorter() {
        int[] array = {2, 8, 4, 2, 1, 3, 2, 0, 4, 7, 6, 4, 5, 1, 2, 9, 8, 6, 3};
        CountSorter.CountSorting2 sorter2 = new CountSorter.CountSorting2();
        sorter2.sort(array);
    }

    @Test
    public void countSorterObject() {
        DataUtils.Student[] students = DataUtils.generateStudents(10);
        CountSorter.CountSorting3 sorter2 = new CountSorter.CountSorting3();
        sorter2.sort(students);
    }
}
