package com.future.utils.drawtree;

public interface IDrawableTree<E> {
    E root();

    E left(E node);

    E right(E node);

    default boolean isRed(E node) {
        return false;
    }

    default String string(E node) {
        return node.toString();
    }
}
