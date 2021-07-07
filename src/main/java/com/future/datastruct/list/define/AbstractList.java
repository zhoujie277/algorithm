package com.future.datastruct.list.define;

import java.util.Iterator;

/**
 * 线性表结构的抽象类
 * @param <E>
 */
public abstract class AbstractList<E> implements IDataObject<E> {

    protected static final int ELEMENT_NOT_FOUND = -1;

    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    protected abstract int innerIndexOf(E e);

    public abstract Iterator<E> reverseIterator();

    @Override
    public boolean contains(E o) {
        return innerIndexOf(o) >= 0;
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    protected void nullCheck(E e) {
        if (e == null)
            throw new NullPointerException("不允许传进来的值为null");
    }

    protected String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

}
