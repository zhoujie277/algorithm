package com.future.datastruct.tree;

import com.future.datastruct.node.BinaryNode;

import java.util.function.Consumer;

/**
 * 线索二叉树
 */
public class ThreadedBinaryTree<E> extends CompleteBinaryTree<E> {

    private BinaryNode<E> orderPrev = null;

    @Override
    protected void onPreOrderTraversal(BinaryNode<E> node) {
        threading(node);
    }

    @Override
    protected void onInOrderTraversal(BinaryNode<E> node) {
        threading(node);
    }

    @Override
    protected void onPostOrderTraversal(BinaryNode<E> node) {
        threading(node);
    }

    private void threading(BinaryNode<E> node) {
        if (node.left == null) {
            node.lChildFlag = BinaryNode.FLAG_THREADED;
            node.left = orderPrev;
        }
        if (orderPrev != null && orderPrev.right == null) {
            orderPrev.rChildFlag = BinaryNode.FLAG_THREADED;
            orderPrev.right = node;
        }
        orderPrev = node;
    }

    public void threadedPreOrder(Consumer<E> consumer) {
        BinaryNode<E> current = root;
        while (current != null) {
            while (!current.lChildThreaded()) {
                consumer.accept(current.value);
                current = current.left;
            }
            consumer.accept(current.value);
            while (current.rChildThreaded()) {
                current = current.right;
                consumer.accept(current.value);
            }
            if (current.right == null) break;
            current = current.left;
        }
    }

    public void threadedInOrder(Consumer<E> consumer) {
        BinaryNode<E> current = root;
        while (current != null) {
            while (!current.lChildThreaded()) {
                current = current.left;
            }
            consumer.accept(current.value);

            while (current.rChildThreaded()) {
                current = current.right;
                consumer.accept(current.value);
            }
            current = current.right;
        }
    }
}
