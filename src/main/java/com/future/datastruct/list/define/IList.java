package com.future.datastruct.list.define;

public interface IList<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    boolean contains(E o);

    void clear();

    boolean add(E e);

    boolean remove(E o);
}
