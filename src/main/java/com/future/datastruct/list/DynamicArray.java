package com.future.datastruct.list;

import com.future.datastruct.list.define.IList;
import com.future.utils.ArrayUtils;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 动态数组
 * @param <E>
 */
public class DynamicArray<E> implements Iterable<E>, IList<E> {
    protected static final int ELEMENT_NOT_FOUND = -1;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(int capacity) {
        this.elements = new Object[capacity];
        this.size = 0;
    }

    @Override
    public E get(int index) {
        return (E) elements[index];
    }

    @Override
    public E set(int index, Object element) {
        rangeCheck(index);
        E value = (E) elements[index];
        elements[index] = element;
        return value;
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public void add(int index, E element) {
        rangeCheck(index);
        ensureCapacity(size + 1);
        if (size - index >= 0) System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Object element = elements[index];
        if (size - index >= 0) System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return (E) element;
    }

    @Override
    public int indexOf(E o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return ELEMENT_NOT_FOUND;
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
    public boolean contains(E o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public boolean add(E o) {
        ensureCapacity(size + 1);
        elements[size++] = o;
        return true;
    }

    @Override
    public boolean remove(E e) {
        int index = indexOf(e);
        if (index < 0 || index >= size) return false;
        return remove(index) != null;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, null);
    }

    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) grow();
    }

    private void grow() {
        int len = elements.length + (elements.length >> 1);
        Object[] newArr = new Object[len];
        ArrayUtils.copy(elements, newArr);
        elements = newArr;
    }

    @SuppressWarnings("unused")
    public Iterator<E> reverseIterator() {
        return new ReverseItr(size);
    }

    @Override
    public String toString() {
        return "DynamicArray{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                '}';
    }

    private class Itr implements Iterator<E> {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            E val = (E) elements[currentIndex];
            currentIndex++;
            return val;
        }
    }

    private class ReverseItr implements Iterator<E> {

        int currentIndex;

        ReverseItr(int count) {
            this.currentIndex = count - 1;
        }

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public E next() {
            E val = (E) elements[currentIndex];
            currentIndex--;
            return val;
        }
    }


}
