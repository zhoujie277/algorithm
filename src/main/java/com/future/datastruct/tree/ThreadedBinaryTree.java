package com.future.datastruct.tree;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 线索二叉树
 */
public class ThreadedBinaryTree<E> extends CompleteBinaryTree<E> {

    private Node<E> orderPrev = null;

    public void preOrderThread() {
        iteratorThreading(preOrderNodeIterator());
    }

    public void inOrderThread() {
        iteratorThreading(inOrderNodeIterator());
    }

    public void postOrderThread() {
        iteratorThreading(postOrderNodeIterator());
    }

    private void iteratorThreading(Iterator<Node<E>> nodeIterator) {
        orderPrev = null;
        while (nodeIterator.hasNext()) {
            Node<E> node = nodeIterator.next();
            threading(node);
        }
    }

    private void threading(Node<E> node) {
        if (node.left == null) {
            node.lChildFlag = BinaryTree.Node.FLAG_THREADED;
            node.left = orderPrev;
        }
        if (orderPrev != null && orderPrev.right == null) {
            orderPrev.rChildFlag = BinaryTree.Node.FLAG_THREADED;
            orderPrev.right = node;
        }
        orderPrev = node;
    }

    public void threadedPreOrder(Consumer<E> consumer) {
        Node<E> current = root;
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
        Node<E> current = root;
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
