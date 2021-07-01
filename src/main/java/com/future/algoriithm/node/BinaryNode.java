package com.future.algoriithm.node;

/**
 * 二叉树节点
 */
public class BinaryNode<T> extends Node<T> {
    public BinaryNode<T> left;
    public BinaryNode<T> right;

    public BinaryNode(T value, BinaryNode<T> left) {
        this(value, left, null);
    }

    public BinaryNode(T value, BinaryNode<T> left, BinaryNode<T> right) {
        super(value, null);
        this.left = left;
        this.right = right;
    }

    public BinaryNode(T value) {
        this(value, null, null);
    }

    public BinaryNode() {
        this(null, null, null);
    }
}
