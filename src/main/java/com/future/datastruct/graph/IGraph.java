package com.future.datastruct.graph;

import java.util.Iterator;

/**
 * 图
 * 邻接矩阵（Adjacency Matrix）
 * 邻接表（Adjacency List）
 * <p>
 * 邻接矩阵的存储方式
 * 一维数组存放顶点信息
 * 二维数组存放边信息
 * 邻接矩阵比较适合稠密图（不然会比较浪费内存）
 *
 * @author jayzhou
 */
public interface IGraph<V> {

    /**
     * 顶点数量
     */
    int numOfVertices();

    /**
     * 边的数量
     */
    int numOfEdges();

    /**
     * 包含顶点
     */
    boolean containsVertex(V vertex);

    /**
     * 添加顶点
     */
    boolean addVertex(V vertex);

    /**
     * 添加顶点
     */
    void addVertices(V... vertices);

    /**
     * 删除顶点
     */
    boolean removeVertex(V vertex);

    /**
     * 添加边
     */
    boolean addEdge(V from, V to);

    /**
     * 删除边
     */
    boolean removeEdge(V from, V to);

    /**
     * 广度遍历迭代器
     */
    Iterator<V> breadthIterator(V from);

    /**
     * 深度遍历迭代器
     */
    Iterator<V> deepIterator(V from);

    /**
     * 图中是不是有环
     */
    boolean hasCyclic();

    /**
     * 判断某个顶点是不是有环
     */
    boolean hasCyclic(V v);

    /**
     * 是不是有向图
     */
    boolean isDirected();

    /**
     * 是不是连通图
     */
    boolean isConnected();
}
