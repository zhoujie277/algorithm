package com.future.datastruct.list;

import com.future.datastruct.list.define.AbstractSequence;
import com.future.datastruct.list.define.ILinked;

/**
 * 数组栈
 * @author jayzhou
 */
public class ArrayStack<E> extends AbstractSequence<E> implements ILinked<E> {

    public ArrayStack() {
        super();
    }

    public ArrayStack(int capacity) {
        super(capacity);
    }

    @Override
    public void push(E e) {
        add(e);
    }

    @Override
    public void offer(E e) {
        push(e);
    }

    @Override
    public E peek() {
        return (E) elements[size];
    }

    public E pop() {
        return remove(size - 1);
    }

    @Override
    public E poll() {
        return pop();
    }

    @Override
    public E last() {
        return peek();
    }

}
