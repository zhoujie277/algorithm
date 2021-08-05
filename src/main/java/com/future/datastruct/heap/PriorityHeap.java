package com.future.datastruct.heap;


import java.util.Arrays;
import java.util.Comparator;

/**
 * 优先队列
 * 二叉堆实现
 *
 * @author jayzhou
 */
public class PriorityHeap<Key> {

    private static final int DEFAULT_CAPACITY = 8;

    private Key[] elements;
    private int[] minHeap;
    private int[] maxHeap;
    private int[] maxInverses;
    private int[] minInverses;
    private int size;
    private final Comparator<Key> comparator;

    @SuppressWarnings("unused")
    public PriorityHeap() {
        this(DEFAULT_CAPACITY);
    }

    public PriorityHeap(int capacity) {
        this(capacity, null);
    }

    @SuppressWarnings("unchecked")
    public PriorityHeap(int capacity, Comparator<Key> comparator) {
        this.elements = (Key[]) new Object[capacity];
        this.minHeap = new int[capacity];
        this.maxHeap = new int[capacity];
        this.maxInverses = new int[capacity];
        this.minInverses = new int[capacity];
        Comparator<Key> defaultComparator = (o1, o2) -> ((Comparable<Key>) o1).compareTo(o2);
        this.comparator = (comparator == null) ? defaultComparator : comparator;
        size = 0;
    }

    public PriorityHeap(Key[] keys, Comparator<Key> comparator) {
        this(keys.length, comparator);
        this.size = keys.length;
        for (int i = 0; i < size; i++) {
            this.elements[i] = keys[i];
            setMaxHeapIndex(i, i);
            setMinHeapIndex(i, i);
        }
        heapify();
    }

    public PriorityHeap(Key[] keys) {
        this(keys, null);
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void heapify() {
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDownMaxHeap(i);
            siftDownMinHeap(i);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
            minHeap[i] = 0;
            maxHeap[i] = 0;
            maxInverses[i] = 0;
            minInverses[i] = 0;
        }
        size = 0;
    }

    @SuppressWarnings("unused")
    public void insert(Key element) {
        ensureCapacity();
        elements[size++] = element;
        setMaxHeapIndex(size, size);
        setMinHeapIndex(size, size);
        siftUpMaxHeap(size);
        siftUpMinHeap(size);
    }

    public Key getMin() {
        return elements[minHeap[0]];
    }

    public Key getMax() {
        return elements[maxHeap[0]];
    }

    public Key delMax() {
        if (size == 0) return null;
        int eleIndex = maxHeap[0];
        Key oldVal = elements[eleIndex];
        // move last index to top
        setMaxHeapIndex(0, maxHeap[--size]);
        // 需要从最小堆中删除该索引。即从最小堆执行删除中间元素操作，将末尾索引值拿上来替换并需要进行下滤。
        int minArrayIndex = minInverses[eleIndex];
        if (minArrayIndex != size) {
            // size 减少了，需要将最后一个元素挪动
            setMinHeapIndex(minArrayIndex, minHeap[size]);
        }

        if (eleIndex != size) {
            // move element, need to update five array.
            elements[eleIndex] = elements[size];
            setMinHeapIndex(minInverses[size], eleIndex);
            setMaxHeapIndex(maxInverses[size], eleIndex);
            elements[size] = null; // help GC
        }
        // 清空
        resetLastHeapIndex();

        //进行最大堆的下滤
        siftDownMaxHeap(0);
        //最大堆的上滤
        if (minArrayIndex < size)
            siftUpMinHeap(minArrayIndex);
        return oldVal;
    }

    public Key delMin() {
        if (size == 0) return null;
        int eleIndex = minHeap[0];
        Key oldVal = elements[eleIndex];
        // move last index to top
        setMinHeapIndex(0, minHeap[--size]);

        // 需要从最大堆中删除该索引。即从最大堆执行删除中间元素操作，将末尾索引值拿上来替换并需要进行上滤。
        int maxArrayIndex = maxInverses[eleIndex];
        if (maxArrayIndex != size) {
            // size 减少了，需要将最后一个元素挪动
            setMaxHeapIndex(maxArrayIndex, maxHeap[size]);
        }

        if (eleIndex != size) {
            // move element, need to update five array.
            elements[eleIndex] = elements[size];
            setMinHeapIndex(minInverses[size], eleIndex);
            setMaxHeapIndex(maxInverses[size], eleIndex);
            elements[size] = null; // help GC
        }
        // 清空
        resetLastHeapIndex();

        // 最小堆的下滤
        siftDownMinHeap(0);
//        //最大堆的上滤
        if (maxArrayIndex < size)
            siftUpMaxHeap(maxArrayIndex);
        return oldVal;
    }

    @SuppressWarnings("unused")
    public Key replaceMax(Key element) {
        int maxIndex = maxHeap[0];
        Key oldVal = elements[maxIndex];
        elements[maxIndex] = element;
        siftDownMaxHeap(0);
        siftUpMinHeap(minInverses[maxIndex]);
        return oldVal;
    }

    @SuppressWarnings("unused")
    public Key replaceMin(Key element) {
        int minIndex = minHeap[0];
        Key oldVal = elements[minIndex];
        elements[minIndex] = element;
        siftDownMinHeap(0);
        siftUpMaxHeap(maxInverses[minIndex]);
        return oldVal;
    }

    private void siftUpMinHeap(int subscript) {
        int elementIndex = minHeap[subscript];
        while (subscript > 0) {
            int parent = (subscript - 1) >> 1;
            if (compare(minHeap[parent], minHeap[subscript]) < 0) {
                break;
            }
            setMinHeapIndex(subscript, minHeap[parent]);
            subscript = parent;
        }
        setMinHeapIndex(subscript, elementIndex);
    }

    private void siftUpMaxHeap(int subscript) {
        int elementIndex = maxHeap[subscript];
        while (subscript > 0) {
            int parent = (subscript - 1) >> 1;
            if (compare(maxHeap[parent], maxHeap[subscript]) > 0) {
                break;
            }
            setMaxHeapIndex(subscript, maxHeap[parent]);
            subscript = parent;
        }
        setMaxHeapIndex(subscript, elementIndex);
    }

    private void siftDownMinHeap(int subscript) {
        int elementIndex = minHeap[subscript];
        int half = size >> 1;
        while (subscript < half) {
            int smaller = (subscript << 1) + 1;
            int right = smaller + 1;
            if (right < size && compare(maxHeap[smaller], maxHeap[right]) > 0) {
                smaller = right;
            }
            if (compare(elementIndex, minHeap[smaller]) < 0) {
                break;
            }
            setMinHeapIndex(subscript, minHeap[smaller]);
            subscript = smaller;
        }
        setMinHeapIndex(subscript, elementIndex);
    }

    private void siftDownMaxHeap(int subscript) {
        int elementIndex = maxHeap[subscript];
        int half = size >> 1;
        while (subscript < half) {
            int smaller = (subscript << 1) + 1;
            int right = smaller + 1;
            if (right < size && compare(maxHeap[smaller], maxHeap[right]) < 0) {
                smaller = right;
            }
            if (compare(elementIndex, maxHeap[smaller]) > 0) {
                break;
            }
            setMaxHeapIndex(subscript, maxHeap[smaller]);
            subscript = smaller;
        }
        setMaxHeapIndex(subscript, elementIndex);
    }

    private void setMaxHeapIndex(int subscript, int elementIndex) {
        maxHeap[subscript] = elementIndex;
        maxInverses[elementIndex] = subscript;
    }

    private void setMinHeapIndex(int subscript, int elementIndex) {
        minHeap[subscript] = elementIndex;
        minInverses[elementIndex] = subscript;
    }

    public void resetLastHeapIndex() {
        minHeap[size] = 0;
        minInverses[size] = 0;
        maxHeap[size] = 0;
        maxInverses[size] = 0;
    }

    private int compare(int left, int right) {
        return comparator.compare(elements[left], elements[right]);
    }

    private void ensureCapacity() {
        if (size < elements.length) return;
        grow();
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int oldCap = size;
        int newCap;
        if (oldCap < DEFAULT_CAPACITY) {
            newCap = oldCap << 1;
        } else {
            newCap = oldCap + (oldCap >> 1);
        }

        int[] newMaxHeap = new int[newCap];
        int[] newMinHeap = new int[newCap];
        int[] newMinInverse = new int[newCap];
        int[] newMaxInverse = new int[newCap];
        Key[] newElements = (Key[]) new Comparable[newCap];
        for (int i = 0; i < oldCap; i++) {
            newElements[i] = elements[i];
            newMinHeap[i] = minHeap[i];
            newMaxHeap[i] = maxHeap[i];
            newMaxInverse[i] = maxInverses[i];
            newMinInverse[i] = minInverses[i];
        }
        maxHeap = newMaxHeap;
        minHeap = newMinHeap;
        elements = newElements;
        maxInverses = newMaxInverse;
        minInverses = newMinInverse;
    }

    @Override
    public String toString() {
        return "PriorityHeap\n" + "elements=" + Arrays.toString(elements) + "\n" +
                "minHeap=" + Arrays.toString(minHeap) + "\n" +
                "maxHeap=" + Arrays.toString(maxHeap) + "\n" +
                "maxInverses=" + Arrays.toString(maxInverses) + "\n" +
                "minInverses=" + Arrays.toString(minInverses) + "\n" +
                "size=" + size;
    }

}
