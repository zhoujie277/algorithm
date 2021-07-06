package com.future.algorithm.sort;

import com.future.algoriithm.sort.CountSorter;
import com.future.algoriithm.sort.Sorter;
import com.future.utils.DataUtils;
import org.junit.Test;

public class CountSorterTest {

    @Test
    public void simpleNumberTest() {
        int[] array = {2, 8, 4, 2, 1, 3, 2, 0, 4, 7, 6, 4, 5, 1, 2, 9, 8, 6, 3};
        Sorter sorter = new CountSorter.CountSorting1();
        sorter.sort(array);
    }

    @Test
    public void stableCounterSorter() {
        int[] array = {2, 8, 4, 2, 1, 3, 2, 0, 4, 7, 6, 4, 5, 1, 2, 9, 8, 6, 3};
        Sorter sorter2 = new CountSorter.CountSorting2();
        sorter2.sort(array);
    }

    @Test
    public void countSorterObject() {
        DataUtils.Student[] students = DataUtils.generateStudents(10);
        CountSorter.CountSorting3 sorter2 = new CountSorter.CountSorting3();
        sorter2.sort(students);
    }
}
