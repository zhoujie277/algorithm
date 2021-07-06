package com.future.datastruct.list;

import com.future.datastruct.list.define.IQueue;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 环形队列
 */
public class CircleQueue<E> implements IQueue<E> {

    protected static final int ELEMENT_NOT_FOUND = -1;
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;
    private int front;

    public CircleQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CircleQueue(int capacity) {
        elements = new Object[capacity];
        size = 0;
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
    public boolean remove(E o) {
        int index = indexOf(o);
        return remove(index) != null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
    }

    @Override
    public E get(int index) {
        return (E) elements[mapIndex(index)];
    }

    @Override
    public boolean add(E e) {
        ensureCapacity(size + 1);
        elements[mapIndex(front + size)] = e;
        size++;
        return false;
    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size) return null;
        int mapIndex = mapIndex(front + index);
        Object element = elements[mapIndex];
        elements[mapIndex] = e;
        return (E) element;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) return;
        ensureCapacity(size + 1);
        boolean moveBack = index > (size >> 1);
        if (moveBack) {
            for (int i = size; i > index; i--) {
                int curIndex = mapIndex(i + front);
                int prevIndex = mapIndex(i - 1 + front);
                elements[curIndex] = elements[prevIndex];
            }
        } else {
            // 往前移
            for (int i = 0; i < index; i++) {
                int curIndex = mapIndex(i + front);
                int prevIndex = mapIndex(i - 1 + front);
                elements[prevIndex] = elements[curIndex];
            }
            front = mapIndex(front - 1);
        }
        elements[mapIndex(index + front)] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) return null;
        int mapIndex = mapIndex(index);
        E val = (E) elements[mapIndex];
        for (int i = index + 1; i < index + size; i++) {
            int dstIndex = mapIndex(i - 1);
            int srcIndex = mapIndex(i);
            elements[dstIndex] = elements[srcIndex];
        }
        size--;
        return val;
    }

    @Override
    public boolean offer(E e) {
        front = mapIndex(front - 1);
        elements[front] = e;
        size++;
        return false;
    }

    @Override
    public E remove() {
        if (size == 0) return null;
        int index = mapIndex(front + size - 1);
        Object element = elements[index];
        elements[index] = null;
        size--;
        return (E) element;
    }

    @Override
    public E poll() {
        if (size == 0) return null;
        Object element = elements[front];
        elements[front] = null;
        front = mapIndex(front + 1);
        size--;
        return (E) element;
    }

    @Override
    public E peek() {
        return (E) elements[front];
    }

    @Override
    public int indexOf(E e) {
        for (int i = front; i < front + size; i++) {
            int index = mapIndex(i);
            if (elements[index].equals(e)) {
                return (index + elements.length - front) % elements.length;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) grow();
    }

    private void grow() {
        int len = elements.length + (elements.length >> 1);
        Object[] newArr = new Object[len];
        for (int i = front; i < front + size; i++) {
            int srcIndex = mapIndex(i);
            int dstIndex = i % len;
            newArr[dstIndex] = elements[srcIndex];
        }
        elements = newArr;
    }

    private int mapIndex(int index) {
//        return index % elements.length;
        return index - (index >= elements.length ? elements.length : 0);
    }

    @Override
    public String toString() {
        return "CircleQueue{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                ", front=" + front +
                '}';
    }

    private class Itr implements Iterator<E> {
        private int index = front;

        @Override
        public boolean hasNext() {
            return index < front + size;
        }

        @Override
        public E next() {
            Object element = elements[mapIndex(index)];
            index++;
            return (E) element;
        }
    }
}
