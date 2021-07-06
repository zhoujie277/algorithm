package com.future.datastruct.list;

import java.util.Iterator;

public interface ILinear<E> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

    boolean add(E e);

    boolean remove(Object o);

    void clear();
}
