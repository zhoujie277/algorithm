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
            fixAfterInsertion(root);
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
            fixAfterInsertion(eNode);
        }
        size++;
        return null;
    }

    @Override
    public E remove(E e) {
        Node<E> p = node(e);
        if (p == null) return null;
        E value = p.value;
        if (p.left != null && p.right != null) {
            // 找到后继结点,将要删除的结点的值，由后继结点覆盖
            Node<E> s = successor(p);
            p.value = s.value;
            p = s;
        }
        //删除后继结点，后继结点一定是度为0或者为1的结点
        Node<E> replacement = (p.left != null ? p.left : p.right);

        if (replacement != null) {
            // 删除的结点是度为1的结点
            replacement.parent = p.parent;
            if (p.parent == null) {
                root = replacement;
            } else if (p.parent.left == p) {
                p.parent.left = replacement;
            } else {
                p.parent.right = replacement;
            }
            fixAfterDeletion(p);
            p.left = p.right = p.parent = null;
        } else if (p.parent == null) {
            root = null;
        } else {
            // 删除结点是度为0的结点
            fixAfterDeletion(p);
            if (p.parent.left == p) {
                p.parent.left = null;
            } else {
                p.parent.right = null;
            }
            p.parent = null;
        }
        size--;
        return value;
    }

    protected void fixAfterInsertion(Node<E> node) {

    }

    protected void fixAfterDeletion(Node<E> node) {

    }

    /**
     * 中序遍历的后继结点
     */
    public Node<E> successor(Node<E> node) {
        if (node == null)
            return null;
        else if (node.right != null) {
            Node<E> successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        } else {
            Node<E> p = node.parent;
            Node<E> cur = node;
            while (p != null && p.right == cur) {
                // 说明父节点比我小,而我要找比我大的
                cur = p;
                p = p.parent;
            }
            return p;
        }
    }

    /**
     * 中序遍历的前驱结点
     */
    public Node<E> predecessor(Node<E> node) {
        if (node == null) return null;
        else if (node.left != null) {
            Node<E> predecessor = node.left;
            while (predecessor.right != null) {
                predecessor = predecessor.right;
            }
            return predecessor;
        } else {
            Node<E> p = node.parent;
            Node<E> cur = node;
            // 说明父节点比我大，而我要找比我小的
            while (p != null && p.left == cur) {
                cur = p;
                p = p.parent;
            }
            return p;
        }
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
