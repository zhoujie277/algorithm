package com.future.datastruct.graph;

import java.util.Iterator;

/**
 * 邻接表实现的图
 *
 * @author jayzhou
 */
public class ListGraph<V> implements IGraph<V> {

    @Override
    public int numOfVertices() {
        return 0;
    }

    @Override
    public int numOfEdges() {
        return 0;
    }

    @Override
    public boolean containsVertex(V vertex) {
        return false;
    }

    @Override
    public boolean addVertex(V vertex) {
        return false;
    }

    @Override
    public void addVertices(V... vertices) {

    }

    @Override
    public boolean removeVertex(V vertex) {
        return false;
    }

    @Override
    public boolean addEdge(V from, V to) {
        return false;
    }

    @Override
    public boolean removeEdge(V from, V to) {
        return false;
    }

    @Override
    public Iterator<V> breadthIterator(V from) {
        return null;
    }

    @Override
    public Iterator<V> deepIterator(V from) {
        return null;
    }

    @Override
    public boolean hasCyclic() {
        return false;
    }

    @Override
    public boolean hasCyclic(V v) {
        return false;
    }

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public boolean isConnected() {
        return false;
    }
}
