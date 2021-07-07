package com.future.datastruct.list;

import com.future.datastruct.list.define.AbstractSequence;
import com.future.datastruct.list.define.ILinked;

/**
 * 数组队列
 *
 * @author jayzhou
 */
public class ArrayQueue<E> extends AbstractSequence<E> implements ILinked<E> {
    public ArrayQueue() {
        super();
    }

    public ArrayQueue(int capacity) {
        super(capacity);
    }

    @Override
    public void push(E e) {
        add(e);
    }

    @Override
    public void offer(E e) {
        add(e);
    }

    @Override
    public E peek() {
        return (E) elements[0];
    }

    @Override
    public E pop() {
        return poll();
    }

    @Override
    public E poll() {
        return remove(0);
    }

    @Override
    public E last() {
        return peek();
    }
}
