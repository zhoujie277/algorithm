package com.future.algoriithm.array;

import java.util.Iterator;

public class Vector <E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private Object[] elements;
    private int count;

    public Vector() {
        this(DEFAULT_CAPACITY);
    }

    public Vector(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[capacity];
        this.count = 0;
    }

    public void add(E e) {
        if (count >= capacity) return;
        elements[count] = e;
        count++;
    }

    public int size() {
        return count;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    public Iterator<E> reverseIterator() {
        return new ReverseItr(count);
    }

    private class Itr implements Iterator<E> {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < count;
        }

        @Override
        public E next() {
            E val = (E) elements[currentIndex];
            currentIndex++;
            return val;
        }
    }

    private class ReverseItr implements Iterator<E> {

        int currentIndex = - 1;

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
            return null;
        }
    }


}
