package com.future.datastruct.heap;

import java.util.Comparator;
import java.util.Iterator;

/**
 * 索引小顶堆
 *
 * @author jayzhou
 */
@SuppressWarnings("unchecked")
public class BinaryIndexHeap<E> implements IHeap<E> {

    private static final int DEFAULT_CAPACITY = 8;

    private Object[] elements;
    private int[] indexes;
    private int[] reverses;
    private int size;
    private Comparator<? super E> comparator = null;

    public BinaryIndexHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryIndexHeap(Comparator<? super E> comparator) {
        this(DEFAULT_CAPACITY);
        this.comparator = comparator;
    }

    public BinaryIndexHeap(int cap) {
        indexes = new int[cap];
        reverses = new int[cap];
        elements = new Object[cap];
        size = cap;
    }

    public BinaryIndexHeap(E[] data) {
        this(data.length);
        for (int i = 0; i < size; i++) {
            elements[i] = data[i];
            indexes[i] = i;
        }
        heapify();
    }

    @SuppressWarnings("all")
    public void heapify() {
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i, i, (E) elements[i]);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            indexes[i] = 0;
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        ensureCapacity();
        int index = size++;
        elements[index] = element;
        updateIndex(index, index);
        siftUp(index, index, element);
    }

    @Override
    public E get() {
        int index = getIndex();
        return (E) elements[index];
    }

    public int getIndex() {
        return indexes[0];
    }

    public E get(int index) {
        return (E) elements[index];
    }

    @Override
    public E remove() {
        int elementIndex = removeIndex();
        return (E) elements[elementIndex];
    }

    public int removeIndex() {
        size--;
        int elementIndex = indexes[0];
        int lastElementIndex = indexes[size];
        updateIndex(size, 0);
        siftDown(0, lastElementIndex, (E) elements[lastElementIndex]);
        return elementIndex;
    }

    @Override
    public E replace(E element) {
        int elementIndex = indexes[0];
        E oldVal = (E) elements[elementIndex];
        elements[elementIndex] = element;
        siftDown(0, elementIndex, element);
        return oldVal;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    public E update(int elementIndex, E value) {
        E oldValue = get(elementIndex);
        elements[elementIndex] = value;
        if (comparator == null) {
            Comparable<? super E> newValue = (Comparable<? super E>) value;
            if (newValue.compareTo(oldValue) > 0) {
                siftDownByComparable(reverses[elementIndex], elementIndex, value);
            } else if (newValue.compareTo(oldValue) < 0) {
                siftUpByComparable(reverses[elementIndex], elementIndex, value);
            }
        } else {
            if (comparator.compare(value, oldValue) > 0) {
                siftDownByComparator(reverses[elementIndex], elementIndex, value);
            } else if (comparator.compare(value, oldValue) < 0) {
                siftUpByComparator(reverses[elementIndex], elementIndex, value);
            }
        }
        return oldValue;
    }

    private void updateIndex(int i, int elementIndex) {
        indexes[i] = elementIndex;
        reverses[elementIndex] = i;
    }

    private void siftUp(int index, int elementIndex, E element) {
        if (comparator == null) {
            siftUpByComparable(index, elementIndex, element);
        } else {
            siftUpByComparator(index, elementIndex, element);
        }
    }

    private void siftDown(int index, int elementIndex, E element) {
        if (comparator == null) {
            siftDownByComparable(index, elementIndex, element);
        } else {
            siftDownByComparator(index, elementIndex, element);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftUpByComparable(int index, int elementIndex, E element) {
        while (index > 0) {
            int parent = index >> 1;
            if (compare((E) elements[indexes[parent]], element) < 0) {
                break;
            }
            updateIndex(index, indexes[parent]);
            index = parent;
        }
        updateIndex(index, elementIndex);
    }

    @SuppressWarnings("unchecked")
    private void siftDownByComparable(int index, int elementIndex, E element) {
        int half = size >> 1, smaller, right;
        while (index < half) {
            smaller = (index << 1) + 1;
            right = smaller + 1;
            if (right < size && compare((E) elements[indexes[smaller]], (E) elements[indexes[right]]) > 0) {
                smaller = right;
            }
            if (compare((E) elements[indexes[smaller]], element) > 0) {
                break;
            }
            updateIndex(index, indexes[smaller]);
            index = smaller;
        }
        updateIndex(index, elementIndex);
    }

    private void siftUpByComparator(int index, int elementIndex, E element) {
        while (index > 0) {
            int parent = index >> 1;
            if (comparator.compare((E) elements[indexes[parent]], element) < 0) {
                break;
            }
            updateIndex(index, indexes[parent]);
            index = parent;
        }
        updateIndex(index, elementIndex);
    }

    private void siftDownByComparator(int index, int elementIndex, E element) {
        int half = size >> 1, smaller, right;
        while (index < half) {
            smaller = (index << 1) + 1;
            right = smaller + 1;
            if (right < size && comparator.compare((E) elements[indexes[smaller]], (E) elements[indexes[right]]) > 0) {
                smaller = right;
            }
            if (comparator.compare((E) elements[indexes[smaller]], element) > 0) {
                break;
            }
            updateIndex(index, indexes[smaller]);
            index = smaller;
        }
        updateIndex(index, elementIndex);
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            grow();
        }
    }

    private void grow() {
        int oldCap = size;
        int newCap;
        if (oldCap < DEFAULT_CAPACITY) {
            newCap = oldCap >> 1;
        } else {
            newCap = oldCap + (oldCap >> 1);
        }
        Object[] newElements = new Object[newCap];
        int[] newIndexes = new int[newCap];
        int[] newReverses = new int[newCap];
        for (int i = 0; i < oldCap; i++) {
            newElements[i] = elements[i];
            newIndexes[i] = indexes[i];
            newReverses[i] = reverses[i];
        }
        elements = newElements;
        indexes = newIndexes;
        reverses = newReverses;
    }

    private int compare(E e1, E e2) {
        Comparable<? super E> c1 = (Comparable<? super E>) e1;
        return c1.compareTo(e2);
    }

}
