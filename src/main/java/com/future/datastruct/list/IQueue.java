package com.future.datastruct.list;

/**
 * 队列
 */
public interface IQueue<E> extends ILinear<E> {
    boolean add(E e);

    boolean offer(E e);

    E remove();

    E poll();

    E peek();
}
