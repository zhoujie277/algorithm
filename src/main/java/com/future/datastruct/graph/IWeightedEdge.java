package com.future.datastruct.graph;

public interface IWeightedEdge<E> extends Comparable<E> {
    @Override
    int compareTo(E o);

    E add(E e);
}
