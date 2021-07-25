package com.future.algoriithm.sort;

import java.util.Arrays;

public class JavaSorter<E extends Comparable<E>> extends Sorter<E> {
    @Override
    protected void sort() {
        Arrays.sort(elements);
    }
}
