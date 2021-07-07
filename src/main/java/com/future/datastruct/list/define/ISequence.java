package com.future.datastruct.list.define;

/**
 * 顺序存储结构要实现的接口
 */
public interface ISequence<E>{

    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(E o);

}
