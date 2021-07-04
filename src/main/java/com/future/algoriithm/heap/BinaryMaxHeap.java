package com.future.algoriithm.heap;

import com.future.algoriithm.utils.ArrayUtils;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 二叉大顶堆
 */
@SuppressWarnings("unused")
public class BinaryMaxHeap<T extends Comparable<T>> implements Iterable<T> {

    private static final int DEFAULT_INIT_CAPACITY = 8;
    private static final int DEFAULT_MIN_CAPACITY = 4;

    private Object[] elements;
    private int size;

    public BinaryMaxHeap() {
        this(DEFAULT_INIT_CAPACITY);
    }

    public BinaryMaxHeap(int capacity) {
        if (capacity < DEFAULT_MIN_CAPACITY) capacity = DEFAULT_MIN_CAPACITY;
        elements = new Object[capacity];
        size = 0;
    }

    public BinaryMaxHeap(T[] array) {
        size = array.length;
        elements = new Object[size];
        ArrayUtils.copy(array, elements);
        heapify();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(T t) {
        ensureCapacity();
        siftUp(size, t);
        size++;
    }

    public int size() {
        return size;
    }

    public T findLargest() {
        return valueAt(0);
    }

    public T delMax() {
        if (size == 0) return null;
        T oldVal = valueAt(0);
        T v = valueAt(size - 1);
        elements[size - 1] = null;
        size--;
        siftDown(0, v);
        return oldVal;
    }

    private void siftUp(int k, T t) {
        int parent;
        while (k > 0) {
            parent = (k - 1) >>> 1;
            T parentEle = valueAt(parent);
            if (parentEle != null && t.compareTo(parentEle) > 0) {
                elements[k] = parentEle;
                k = parent;
            } else {
                break;
            }
        }
        elements[k] = t;
    }

    private void siftDown(int k, T t) {
        int half = size >>> 1, left, right, largest;
        while (k < half) {
            left = (k << 1) + 1;
            right = left + 1;
            largest = left;
            if (right < size && valueAt(left).compareTo(valueAt(right)) < 0) {
                largest = right;
            }
            if (valueAt(largest).compareTo(t) < 0) {
                break;
            } else {
                elements[k] = valueAt(largest);
                k = largest;
            }
        }
        elements[k] = t;
    }

    private void heapify() {
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i, (T) elements[i]);
        }
    }

    private T valueAt(int index) {
        if (index >= size) return null;
        return (T) elements[index];
    }

    private void ensureCapacity() {
        if (size + 1 < elements.length - 1) return;
        grow();
    }

    private void grow() {
        int oldCap = elements.length;
        int newCap = oldCap + (oldCap >> 1);
        Object[] newArr = new Object[newCap];
        ArrayUtils.copy(elements, newArr);
        elements = newArr;
    }

    @Override
    public Iterator<T> iterator() {
        return new HeapItr();
    }

    public class HeapItr implements Iterator<T> {
        private BinaryMaxHeap<T> copy;

        public HeapItr() {
            copy = new BinaryMaxHeap(size);
            for (int i = 0; i < size; i++) {
                copy.insert((T) elements[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public T next() {
            if (copy.isEmpty()) throw new NoSuchElementException();
            T val = (T) copy.delMax();
            return val;
        }
    }
}
