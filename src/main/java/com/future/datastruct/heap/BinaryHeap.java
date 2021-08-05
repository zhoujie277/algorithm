package com.future.datastruct.heap;

import com.future.utils.ArrayUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 二叉堆
 *
 * 二叉堆的初始批量构建堆的方式有两种：
 * 1、自顶向下的上滤
 * 2、自底向上的下滤
 * 其中第1种时间复杂度为O(nlogN)，而第二种可接近于O(n)。
 * 因为其第一种在初始元素少的时候上滤的元素少，后面树高元素多的时候需要进行上滤的操作多；
 * 而第二种恰恰相反，在初始元素少的时候，需要进行下滤的元素多；而在需要下滤路径比较长的时候元素少；并且总共只需要一半的元素进行下滤
 * 故第2中批量建堆效率更优。
 *
 * @author jayzhou
 */
public class BinaryHeap<T extends Comparable<T>> implements IHeap<T> {

    private static final int DEFAULT_INIT_CAPACITY = 8;
    private static final int DEFAULT_MIN_CAPACITY = 4;

    private Object[] elements;
    private int size;

    public BinaryHeap() {
        this(DEFAULT_INIT_CAPACITY);
    }

    public BinaryHeap(int capacity) {
        if (capacity < DEFAULT_MIN_CAPACITY) capacity = DEFAULT_MIN_CAPACITY;
        elements = new Object[capacity];
        size = 0;
    }

    public BinaryHeap(T[] array) {
        size = array.length;
        elements = new Object[size];
        ArrayUtils.copy(array, elements);
        heapify();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        Arrays.fill(elements, null);
    }

    @Override
    public void add(T element) {
        ensureCapacity();
        siftUp(size, element);
        size++;
    }

    @Override
    public T get() {
        return findLargest();
    }

    @Override
    public T remove() {
        return delMax();
    }

    @Override
    public T replace(T element) {
        T oldVal = valueAt(0);
        siftDown(0, element);
        return oldVal;
    }

    @Override
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
            if (parentEle != null && t.compareTo(parentEle) < 0) {
                elements[k] = parentEle;
                k = parent;
            } else {
                break;
            }
        }
        elements[k] = t;
    }

    private void siftDown(int k, T t) {
        int half = size >>> 1, right, smaller;
        while (k < half) {
            smaller = (k << 1) + 1;
            right = smaller + 1;
            if (right < size && valueAt(smaller).compareTo(valueAt(right)) > 0) {
                smaller = right;
            }
            if (valueAt(smaller).compareTo(t) > 0) {
                break;
            } else {
                elements[k] = valueAt(smaller);
                k = smaller;
            }
        }
        elements[k] = t;
    }

    @SuppressWarnings("all")
    private void heapify() {
        // 自下而上的下滤
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i, (T) elements[i]);
        }
    }

    @SuppressWarnings("unchecked")
    private T valueAt(int index) {
        if (index >= size) throw new ArrayIndexOutOfBoundsException("indexOutOfBounds:" + index);
        return (T) elements[index];
    }

    private void ensureCapacity() {
        if (size + 1 < elements.length - 1) return;
        grow();
    }

    private void grow() {
        int oldCap = elements.length;
        int newCap;
        if (oldCap < DEFAULT_INIT_CAPACITY) {
            newCap = oldCap << 1;
        } else {
            newCap = oldCap + (oldCap >> 1);
        }
        Object[] newArr = new Object[newCap];
        ArrayUtils.copy(elements, newArr);
        elements = newArr;
    }

    @Override
    public Iterator<T> iterator() {
        return new HeapItr();
    }

    public class HeapItr implements Iterator<T> {
        private final BinaryHeap<T> copy;

        @SuppressWarnings("unchecked")
        public HeapItr() {
            copy = new BinaryHeap<>(size);
            for (int i = 0; i < size; i++) {
                copy.add((T) elements[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public T next() {
            if (copy.isEmpty()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }
}
