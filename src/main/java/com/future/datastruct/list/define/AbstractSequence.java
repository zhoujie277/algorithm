package com.future.datastruct.list.define;

import com.future.utils.ArrayUtils;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 抽象的顺序存储结构
 */
public abstract class AbstractSequence<E> extends AbstractList<E> implements ISequence<E> {

    private static final int DEFAULT_CAPACITY = 8;
    protected Object[] elements;

    public AbstractSequence() {
        this(DEFAULT_CAPACITY);
    }

    public AbstractSequence(int capacity) {
        this.elements = new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) elements[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E value = (E) elements[index];
        elements[index] = element;
        return value;
    }

    @Override
    public int indexOf(E o) {
        return innerIndexOf(o);
    }

    @Override
    public int innerIndexOf(E o) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    protected void ensureCapacity(int capacity) {
        if (capacity > elements.length) grow();
    }

    protected void grow() {
        int len;
        if (elements.length < DEFAULT_CAPACITY) {
            len = (elements.length << 1);
        } else {
            len = elements.length + (elements.length >> 1);
        }
        Object[] newArr = new Object[len];
        ArrayUtils.copy(elements, newArr);
        elements = newArr;
    }

    @Override
    public boolean add(E o) {
        ensureCapacity(size + 1);
        elements[size++] = o;
        return true;
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
    public boolean remove(E e) {
        int index = innerIndexOf(e);
        if (index < 0 || index >= size) return false;
        return remove(index) != null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        rangeCheck(index);
        Object element = elements[index];
        if (size - index >= 0) System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return (E) element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public String toString() {
        return "Sequence Array{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                '}';
    }

    public Iterator<E> reverseIterator() {
        return new ReverseItr(size);
    }

    private class Itr implements Iterator<E> {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @SuppressWarnings("unchecked")
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

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            E val = (E) elements[currentIndex];
            currentIndex--;
            return val;
        }
    }
}
