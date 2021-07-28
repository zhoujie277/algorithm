package com.future.datastruct.union;

import com.future.datastruct.map.IMap;
import com.future.datastruct.map.RBTreeMap;

/**
 * 用红黑树管理V的并查集
 *
 * @author jayzhou
 */
public class TreeUnionFind<V extends Comparable<V>> implements IUnionFind<V> {
    private final RBTreeMap<V, Node<V>> treeMap = new RBTreeMap<>();
    private final UnionFindCore<Node<V>> unionFindSet = new UnionFindCore<>();

    @Override
    public void makeSet(V v) {
        if (treeMap.containsKey(v)) return;
        putVal(v);
    }

    private Node<V> putVal(V v) {
        Node<V> vNode = newNode(v);
        treeMap.put(v, vNode);
        unionFindSet.makeSet(vNode);
        return vNode;
    }

    @Override
    public V find(V v) {
        Node<V> vNode = treeMap.get(v);
        if (vNode == null) return null;
        return unionFindSet.find(vNode).element;
    }

    @Override
    public void union(V e1, V e2) {
        Node<V> vNode1 = treeMap.get(e1);
        if (vNode1 == null) {
            vNode1 = putVal(e1);
        }
        Node<V> vNode2 = treeMap.get(e2);
        if (vNode2 == null) {
            vNode2 = putVal(e2);
        }
        if (vNode1 == vNode2) return;
        unionFindSet.union(vNode1, vNode2);
    }

    @Override
    public boolean isConnected(V e1, V e2) {
        Node<V> vNode1 = treeMap.get(e1);
        if (vNode1 == null) return false;
        Node<V> vNode2 = treeMap.get(e2);
        if (vNode2 == null) return false;
        return unionFindSet.isConnected(vNode1, vNode2);
    }

    @Override
    public void reset() {
        IMap.IMapIterator<V, Node<V>> iterator = treeMap.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            Node<V> value = iterator.value();
            unionFindSet.makeSet(value);
        }
    }

    @Override
    public void clear() {
        treeMap.clear();
    }

    @Override
    public boolean remove(V element) {
        Node<V> remove = treeMap.remove(element);
        if (remove != null) {
            UnionFindCore.Element p = remove.parent;
            IMap.IMapIterator<V, Node<V>> iterator = treeMap.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                Node<V> value = iterator.value();
                if (value.parent == remove) {
                    value.parent = p;
                }
            }
            return true;
        }
        return false;
    }

    private Node<V> newNode(V element) {
        return new Node<>(element);
    }

    private static class Node<V> extends UnionFindCore.Element {
        V element;

        public Node(V v) {
            this.element = v;
        }
    }

}
