package com.future.datastruct.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * 索引小顶堆
 * 索引堆在实际使用过程中最麻烦的是，删除时候的逻辑复杂度相比普通二叉堆增加了，
 * 外面使用该索引堆的时候，需要知道索引，而删除的时候，为了不浪费空间，元素需要移动，则元素索引则会改变。
 * 此时，有四种做法。
 * 一：继承约定的IndexHeapElement；此做法管理了index的维护和更新，使用者得到简便，不用关心索引。负面影响就是必须得继承，在使用上会受限；比如普通的Integer就存不了
 * 二：在remove的时候采用事件通知；通知让外面管理了index的对象需要更新索引，此做法暴露细节太多，外面使用不便
 * 三：使用IndexHeapElement包装实际对象E；该做法好处在于使用方便，如果使用者关心索引，则获取IndexHeapElement对象，不关心，则如同使用普通堆一样。
 * 四：采用ElementIndex对象包装index，这样就使得传出去的index是个对象，内部的index会被维护；这里采用的这个做法。
 *
 * @author jayzhou
 */
//@SuppressWarnings("unchecked")
public class BinaryIndexHeap<E> implements IHeap<E> {

    private static final int DEFAULT_CAPACITY = 8;

    private Object[] elements;
    private ElementIndex[] elementIndexes;
    private int[] reverses;
    private int size = 0;
    private Comparator<? super E> comparator = null;

    public BinaryIndexHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryIndexHeap(Comparator<? super E> comparator) {
        this(DEFAULT_CAPACITY);
        this.comparator = comparator;
    }

    public BinaryIndexHeap(int cap) {
        elements = new Object[cap];
        elementIndexes = new ElementIndex[cap];
        reverses = new int[cap];
    }

    public BinaryIndexHeap(E[] data) {
        this(data.length);
        size = data.length;
        for (int i = 0; i < size; i++) {
            elements[i] = data[i];
            elementIndexes[i] = newElementIndex(i);
            reverses[i] = i;
        }
        heapify();
    }

    @SuppressWarnings({"SpellCheckingInspection", "unchecked"})
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
            elementIndexes[i] = null;
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        insert(element);
    }

    public ElementIndex insert(E element) {
        if (element == null) throw new NullPointerException();
        ensureCapacity();
        int subscript = size++;
        elements[subscript] = element;
        ElementIndex e = newElementIndex(subscript);
        elementIndexes[subscript] = e;
        reverses[subscript] = subscript;
        siftUp(subscript, subscript, element);
        return e;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get() {
        ElementIndex e = getElementIndex();
        return (E) elements[e.index];
    }

    public ElementIndex getElementIndex() {
        return elementIndexes[0];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove() {
        if (size == 0) return null;
        size--;
        ElementIndex e = elementIndexes[0];
        Object oldValue = elements[e.index];
        // 移动元素并更新原来的索引数值
        elements[e.index] = elements[size];
        setIndex(reverses[size], e.index);
        // 将堆底元素移到堆顶进行下滤
        ElementIndex bottom = elementIndexes[size];
        siftDown(0, bottom.index, (E) elements[bottom.index]);
        return (E) oldValue;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E replace(E element) {
        ElementIndex e = elementIndexes[0];
        E oldVal = (E) elements[e.index];
        elements[e.index] = element;
        siftDown(0, e.index, element);
        return oldVal;
    }

    @SuppressWarnings("all")
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public void update(ElementIndex e) {
        int elementIndex = e.index;
        E value = (E) elements[elementIndex];
        if (comparator == null) {
            siftDownByComparable(reverses[elementIndex], elementIndex, value);
            siftUpByComparable(reverses[elementIndex], elementIndex, value);
        } else {
            siftDownByComparator(reverses[elementIndex], elementIndex, value);
            siftUpByComparator(reverses[elementIndex], elementIndex, value);
        }
    }

    public E update(ElementIndex e, E value) {
        return update(e.index, value);
    }

    @SuppressWarnings("unchecked")
    public E update(int elementIndex, E value) {
        E oldValue = (E) elements[elementIndex];
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

    private void setIndex(int i, int elementIndex) {
        elementIndexes[i].index = elementIndex;
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
            int parent = (index - 1) >> 1;
            if (compare((E) elements[elementIndexes[parent].index], element) < 0) {
                break;
            }
            setIndex(index, elementIndexes[parent].index);
            index = parent;
        }
        setIndex(index, elementIndex);
    }

    @SuppressWarnings("unchecked")
    private void siftDownByComparable(int index, int elementIndex, E element) {
        int half = size >> 1, smaller, right;
        while (index < half) {
            smaller = (index << 1) + 1;
            right = smaller + 1;
            if (right < size && compare((E) elements[elementIndexes[smaller].index],
                    (E) elements[elementIndexes[right].index]) > 0) {
                smaller = right;
            }
            if (compare((E) elements[elementIndexes[smaller].index], element) > 0) {
                break;
            }
            setIndex(index, elementIndexes[smaller].index);
            index = smaller;
        }
        setIndex(index, elementIndex);
    }

    @SuppressWarnings("unchecked")
    private void siftUpByComparator(int index, int elementIndex, E element) {
        while (index > 0) {
            int parent = index >> 1;
            if (comparator((E) elements[elementIndexes[parent].index], element) < 0) {
                break;
            }
            setIndex(index, elementIndexes[parent].index);
            index = parent;
        }
        setIndex(index, elementIndex);
    }

    @SuppressWarnings("unchecked")
    private void siftDownByComparator(int index, int elementIndex, E element) {
        int half = size >> 1, smaller, right;
        while (index < half) {
            smaller = (index << 1) + 1;
            right = smaller + 1;
            if (right < size && comparator((E) elements[elementIndexes[smaller].index],
                    (E) elements[elementIndexes[right].index]) > 0) {
                smaller = right;
            }
            if (comparator((E) elements[elementIndexes[smaller].index], element) > 0) {
                break;
            }
            setIndex(index, elementIndexes[smaller].index);
            index = smaller;
        }
        setIndex(index, elementIndex);
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
        ElementIndex[] newIndexes = new ElementIndex[newCap];
        int[] newReverses = new int[newCap];
        for (int i = 0; i < oldCap; i++) {
            newElements[i] = elements[i];
            newIndexes[i] = elementIndexes[i];
            newReverses[i] = reverses[i];
        }
        elements = newElements;
        elementIndexes = newIndexes;
        reverses = newReverses;
    }

    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2) {
        Comparable<? super E> c1 = (Comparable<? super E>) e1;
        return c1.compareTo(e2);
    }

    private int comparator(E e1, E e2) {
        return comparator.compare(e1, e2);
    }

    @Override
    public String toString() {
        return "BinaryIndexHeap{" +
                "elements=" + Arrays.toString(elements) +
                ", elementIndexes=" + Arrays.toString(elementIndexes) +
                ", reverses=" + Arrays.toString(reverses) +
                ", size=" + size +
                '}';
    }

    private ElementIndex newElementIndex(int index) {
        return new ElementIndex(index);
    }

    /**
     * 元素的索引对象
     *
     * @author jayzhou
     */
    public final static class ElementIndex {
        private int index;

        private ElementIndex(int index) {
            this.index = index;
        }

        public int get() {
            return index;
        }

        @Override
        public String toString() {
            return index + "";
        }
    }
}
