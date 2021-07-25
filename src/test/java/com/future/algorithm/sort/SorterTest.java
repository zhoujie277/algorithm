package com.future.algorithm.sort;

import com.future.algoriithm.sort.*;
import com.future.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 排序单元测试
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class SorterTest {

    private final int largeLength = 2000000;
    private final int testLength = 30;
    private final int minLength = 10000;
    private final int dataLength = 80000;

    public static <E extends Comparable<E>> Sorter[] sort(E[] elements, Sorter<E>... sorters) {
        for (int i = 0; i < sorters.length; i++) {
            sorters[i].sort(elements);
        }
        new BubbleSorter<Sorter<E>>().sortOrigin(sorters);

        for (int i = 0; i < sorters.length; i++) {
            PrintUtils.println(sorters[i]);
        }
        return sorters;
    }

    @Test
    public void testFunction() {
        int[] intArray = StdRandom.permutation(testLength << 1, testLength);
        Integer[] array = Arrays.stream(intArray).boxed().toArray(Integer[]::new);
        Sorter[] sort = sort(array, new SelectionSorter<>(), new BubbleSorter<>(), new InsertSorter<>(),
                new InsertSorter<>(InsertSorter.DIRECT_INSERT), new ShellSorter<>(ShellSorter.KNUTH_SEQUENCE),
                new HeapSorter<>(), new MergeSorter<>(),  new ShellSorter<>(),
                new QuickSorting<>(), new ShellSorter<>(ShellSorter.BINARY_SEQUENCE),
                new ShellSorter<>(ShellSorter.BINARY_SEQUENCE_OPT),
                new QuickSorting<>(QuickSorting.SWAP_FIND),
                new MergeSorter<>(MergeSorter.BOTTOM_UP)
        );
        Assert.assertNotNull(sort);
        for (int i = 0; i < sort.length; i++) {
            PrintUtils.print(sort[i].getClass().getSimpleName() + ", v" + sort[i].getVersion() + ":");
            if (!sort[i].ascSorted()) {
                PrintUtils.println(intArray);
                PrintUtils.println(sort[i].getElements());
            }
            Assert.assertTrue(sort[i].ascSorted());
        }
    }

    @Test
    public void testMinData() {
        int[] intArray = StdRandom.permutation(minLength << 1, minLength);
        Integer[] array = Arrays.stream(intArray).boxed().toArray(Integer[]::new);
        sort(array, new SelectionSorter<>(), new BubbleSorter<>(), new HeapSorter<>(),
                new QuickSorting<>(), new MergeSorter<>(), new InsertSorter<>(),
                new InsertSorter<>(InsertSorter.DIRECT_INSERT), new ShellSorter<>(),
                new ShellSorter<>(ShellSorter.KNUTH_SEQUENCE));
    }

    @Test
    public void testLargeData() {
        //new ShellSorter<>(),
        int[] intArray = StdRandom.permutation(largeLength << 1, largeLength);
        Integer[] array = Arrays.stream(intArray).boxed().toArray(Integer[]::new);
        sort(array, new HeapSorter<>(),
                new QuickSorting<>(), new MergeSorter<>(), new MergeSorter<>(MergeSorter.BOTTOM_UP),
                new ShellSorter<>(ShellSorter.KNUTH_SEQUENCE),
                new ShellSorter<>(ShellSorter.BINARY_SEQUENCE),
                new ShellSorter<>(ShellSorter.BINARY_SEQUENCE_OPT),
                new JavaSorter<>()
        );
    }

    @Test
    public void testEData() {
//        int[] intArray = StdRandom.permutation(100, dataLength);
//        Integer[] array = Arrays.stream(intArray).boxed().toArray(Integer[]::new);
//        sort(array, new SelectionSorter<>(), new BubbleSorter<>(), new HeapSorter<>());
    }

    /**
     * 排序算法稳定性测试
     */
    @Test
    public void testStable() {
        int stableLen = 40;
        StableClass[] classes = new StableClass[stableLen];
        for (int i = 0; i < stableLen; i++) {
            classes[i] = new StableClass(i % 7, i + 1);
        }
        testStable(classes, new SelectionSorter<>(), new HeapSorter<>(), new BubbleSorter<>(),
                new InsertSorter<>(),
                new MergeSorter<>(), new QuickSorting<>(), new ShellSorter<>());
    }

    private void testStable(StableClass[] classes, Sorter<StableClass>... sorters) {
        for (Sorter<StableClass> sorter : sorters) {
            sorter.sort(classes);
            StableClass[] newClasses = new StableClass[classes.length];
            sorter.getElements(newClasses);
            boolean isStable = true;
            for (int i = 1; i < newClasses.length; i++) {
                StableClass after = newClasses[i];
                StableClass before = newClasses[i - 1];
                if (after.age == before.age) {
                    if (after.score - before.score != 7) {
                        isStable = false;
                        break;
                    }
                }
            }
            String str = sorter.getClass().getSimpleName() + "'s stable is:" + isStable;
            if (isStable) {
                PrintUtils.info(str);
            } else {
                PrintUtils.println(str);
            }
        }
    }

    static class StableClass implements Comparable<StableClass> {
        public int age;
        public int score;

        public StableClass(int age, int score) {
            this.age = age;
            this.score = score;
        }

        @Override
        public int compareTo(StableClass o) {
            return age - o.age;
        }

        @Override
        public String toString() {
            return "{" +
                    "age=" + age +
                    ", score=" + score +
                    '}';
        }
    }
}
