package com.future.datastruct.tree;


/**
 * 可排序的二叉树
 * 根据元素的大小进行排序插入
 */
@SuppressWarnings("unused")
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

    @Override
    public E add(E e) {
        if (root == null) {
            root = newNode(e);
        } else {
            Node<E> parent = null;
            Node<E> current = root;
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
            Node<E> eNode = newNode(e);
            if (e.compareTo(parent.value) > 0) {
                parent.right = eNode;
            } else {
                parent.left = eNode;
            }
            eNode.parent = parent;
        }
        size++;
        return null;
    }

    @Override
    public E remove(E e) {
//        Node<E> parent = null;
//        Node<E> current = root;
//        while (current != null) {
//            int cmp = e.compareTo(current.value);
//            if (cmp == 0) break;
//            parent = current;
//            if (cmp > 0) {
//                current = current.right;
//            } else {
//                current = current.left;
//            }
//        }
//        if (current == null) return false;
//        if (parent == null) {
//            root = null;
//            size = 0;
//            return true;
//        } else {
//            // 需要选择右子树最小值或者是左子树的最大值接替current的位置，
//            Node<E> successor;
//            int cmp = current.value.compareTo(parent.value);
//            if (current.right != null) {
//                // 需要选择右子树最小值接替current的位置，
//                successor = adjustFarLeftNode(current.right);
//            } else if (current.left != null) {
//                //需要选择左子树的最大值接替current的位置，
//                successor = adjustFarRightNode(current.left);
//            } else {
//                successor = null;
//            }
//            if (successor != null) {
//                // 如果接替者不是左节点
//                if (current.left != successor) {
//                    successor.left = current.left;
//                }
//                if (current.right != successor) {
//                    successor.right = current.right;
//                }
//            }
//            if (cmp > 0) {
//                // 删除的是右边
//                parent.right = successor;
//            } else {
//                // 删除的是左边
//                parent.left = successor;
//            }
//        }
//        size--;
//        return true;
        return null;
    }

    /**
     * 查找并调整子树的最小值
     */
    private Node<E> adjustFarLeftNode(Node<E> node) {
        if (node.left == null) return node;
        Node<E> parent = node;
        Node<E> minNode = node.left;
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
    private Node<E> adjustFarRightNode(Node<E> node) {
        if (node.right == null) return node;
        Node<E> parent = node;
        Node<E> maxNode = node.right;
        while (maxNode.right != null) {
            parent = maxNode;
            maxNode = maxNode.right;
        }
        parent.right = maxNode.left;
        return maxNode;
    }

    private Node<E> node(E e) {
        Node<E> current = root;
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
