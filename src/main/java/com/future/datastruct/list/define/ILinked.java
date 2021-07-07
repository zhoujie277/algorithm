package com.future.datastruct.list.define;

public interface ILinked<E> {
    void push(E e);

    void offer(E e);

    E peek();

    E pop();

    E poll();

    E last();
}
