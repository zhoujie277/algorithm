package com.future.datastruct.list.define;

/**
 * 队列
 */
public interface IQueue<E> extends IList<E> {
    boolean add(E e);

    boolean offer(E e);

    E remove();

    E poll();

    E peek();
}
