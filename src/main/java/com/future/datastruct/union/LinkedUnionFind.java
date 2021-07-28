package com.future.datastruct.union;

import java.util.Objects;

/**
 * 泛型并查集
 * 基于QuickUnion + size + pathHalving 方案实现的并查集
 * 采用路径压缩/分裂/减半等方案如果采用rank的方案，需要维护rank的更新，带来一定复杂度，
 * 而且随着find次数越多，待合并的两个集合高度几乎不会相差太多，甚至大概率会相等，不如采用size的优化更合适
 * <p>
 * 并查集不处理去重
 *
 * @author jayzhou
 */
public class LinkedUnionFind<E> implements IUnionFind<E> {

    private final UnionFindCore<Node> unionFindCore = new UnionFindCore<>();

    protected Node root = null;

    @Override
    public void makeSet(E e) {
        Node node = node(e);
        if (node != null) return;
        root = newNode(e, root);
        unionFindCore.makeSet(root);
    }

    @Override
    public E find(E e) {
        Node p = node(e);
        if (p == null) return null;
        return unionFindCore.find(p).element;
    }

    @Override
    public void union(E e1, E e2) {
        Node node1 = node(e1);
        if (node1 == null) return;
        Node node2 = node(e2);
        if (node2 == null || node1 == node2) return;
        unionFindCore.union(node1, node2);
    }

    @Override
    public boolean isConnected(E e1, E e2) {
        return Objects.equals(find(e1), find(e2));
    }

    private Node newNode(E element, Node next) {
        return new Node(element, next);
    }

    /**
     * 将并查集每个元素集合重置
     * 因分离并查集不太好分，暂时采用reset方案。时间复杂度为O(n)
     */
    @Override
    public void reset() {
        Node p = root;
        while (p != null) {
            unionFindCore.makeSet(p);
            p = p.next;
        }
    }

    @Override
    public void clear() {
        reset();
        root = null;
    }

    @Override
    public boolean remove(E element) {
        if (root == null) return false;
        Node p = root;
        if (Objects.equals(p.element, element)) {
            root = p.next;
            p.next = null;
        } else {
            Node c;
            while ((c = p.next) != null) {
                if (Objects.equals(c.element, element)) {
                    break;
                }
                p = c;
            }
            if (c == null) return false;
            p.next = c.next;
            c.next = null;
        }
        reset();
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("--LinkedUnionFind ");
        Node p = root;
        while (p != null) {
            builder.append(p);
            builder.append('\n');
            p = p.next;
        }
        return builder.toString();
    }

    protected Node node(E e) {
        Node p = root;
        while (p != null) {
            if (Objects.equals(p.element, e)) break;
            p = p.next;
        }
        return p;
    }

    private class Node extends UnionFindCore.Element {
        E element;
        Node next;

        public Node(E element, Node next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }

}
