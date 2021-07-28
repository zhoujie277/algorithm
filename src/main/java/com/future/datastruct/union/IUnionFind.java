package com.future.datastruct.union;

/**
 * 并查集相关接口
 *
 * @author jayzhou
 */
public interface IUnionFind<E> {

    /**
     * 使元素重新归属自己的集合
     */
    void makeSet(E e);

    /**
     * 查找元素所属的集合
     *
     * @param e 指定元素
     * @return 所属集合的代表元素
     */
    E find(E e);

    /**
     * 合并元素
     */
    void union(E e1, E e2);

    /**
     * 是否属于同一集合
     */
    boolean isConnected(E e1, E e2);

    /**
     * 将并查集每个元素集合重置
     */
    void reset();

    void clear();

    boolean remove(E element);
}
