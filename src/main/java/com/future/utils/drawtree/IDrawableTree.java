package com.future.utils.drawtree;

public interface IDrawableTree<E> {
    E root();

    E left(E node);

    E right(E node);
}