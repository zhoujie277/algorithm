package com.future.datastruct.set;

import java.util.Iterator;

/**
 * 集合接口
 *
 * @author jayzhou
 */
public interface ISet<E> extends Iterable<E> {

    boolean contains(E e);

    boolean add(E e);

    int size();

    boolean isEmpty();

    Iterator<E> iterator();

    void clear();

}
