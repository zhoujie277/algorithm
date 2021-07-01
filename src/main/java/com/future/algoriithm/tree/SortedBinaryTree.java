package com.future.algoriithm.tree;

import com.future.algoriithm.node.BinaryNode;

/**
 * 可排序的二叉树
 * 根据元素的大小进行排序插入
 *
 */
@SuppressWarnings("unused")
public class SortedBinaryTree<E extends Comparable> extends AbstractBinaryTree<E> {

    public SortedBinaryTree() {

    }

    public E add(E e) {
        if (root == null) {
            root = newNode(e);
        } else {
            BinaryNode<E> current = root;
            BinaryNode<E> parent = current;
            while (current != null) {
                int cmp = e.compareTo(current.value);
                if (cmp == 0) {
                    E oldVal = current.value;
                    current.value = e;
                    return oldVal;
                }
                parent = current;
                if (cmp > 0) {
                    current = current.right;
                } else {
                    current = current.left;
                }
            }
            if (e.compareTo(parent.value) > 0) {
                parent.right = newNode(e);
            } else {
                parent.left = newNode(e);
            }
        }
        size++;
        return null;
    }

    public boolean remove(E e) {
        BinaryNode<E> parent = null;
        BinaryNode<E> current = root;
        while (current != null) {
            int cmp = e.compareTo(current.value);
            if (cmp == 0) break;
            parent = current;
            if (cmp > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        if (current == null) return false;
        if (parent == null) {
            root = null;
        } else {
            // 需要选择右子树最小值或者是左子树的最大值接替current的位置，
            BinaryNode<E> successor;
            int cmp = current.value.compareTo(parent.value);
            if (current.right != null) {
                // 需要选择右子树最小值接替current的位置，
                successor = adjustFarLeftNode(current.right);
            } else if (current.left != null) {
                //需要选择左子树的最大值接替current的位置，
                successor = adjustFarRightNode(current.left);
            } else {
                successor = null;
            }
            if (successor != null) {
                successor.left = current.left;
                successor.right = current.right;
            }
            if (cmp > 0) {
                // 删除的是右边
                parent.right = successor;
            } else {
                // 删除的是左边
                parent.left = successor;
            }
        }
        size--;
        return true;
    }

    /**
     * 查找并调整子树的最小值
     */
    private BinaryNode<E> adjustFarLeftNode(BinaryNode<E> node) {
        if (node.left == null) return node;
        BinaryNode<E> parent = node;
        BinaryNode<E> minNode = node.left;
        while (minNode.left != null) {
            parent = minNode;
            minNode = minNode.left;
        }
        parent.left = minNode.right;
        return minNode;
    }

    /**
     * 查找子树的最大值
     */
    private BinaryNode<E> adjustFarRightNode(BinaryNode<E> node) {
        if (node.right == null) return node;
        BinaryNode<E> parent = node;
        BinaryNode<E> maxNode = node.right;
        while (maxNode.right != null) {
            parent = maxNode;
            maxNode = maxNode.right;
        }
        parent.right = maxNode.left;
        return maxNode;
    }

    private BinaryNode<E> node(E e) {
        BinaryNode<E> current = root;
        while (current != null) {
            int cmp = e.compareTo(current.value);
            if (cmp == 0) break;
            if (cmp > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return current;
    }
}
