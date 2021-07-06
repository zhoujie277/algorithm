package com.future.datastruct.list.define;

import java.util.Iterator;

public interface IList<E> {

    int size();

    boolean isEmpty();

    boolean contains(E o);

    Iterator<E> iterator();

    boolean add(E e);

    boolean remove(E o);

    void clear();

    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(E o);
}
