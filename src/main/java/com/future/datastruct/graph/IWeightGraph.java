package com.future.datastruct.graph;

import java.util.Iterator;

/**
 * 带权图
 */
public interface IWeightGraph<V, E> extends IGraph<V> {
    byte MINIMAL_SPANNING_TREE_PRIM = 1;
    byte MINIMAL_SPANNING_TREE_KRUSKAL = 2;

    boolean addEdge(V from, V to, E edge);

    Edge<V, E>[] minimalSpanningTree();

    Edge<V, E>[] minimalSpanningTree(byte strategy);

    int numOfVertices();

    int numOfEdges();

    boolean containsVertex(V vertex);

    boolean addVertex(V vertex);

    void addVertices(V... vertices);

    boolean removeVertex(V vertex);

    boolean addEdge(V from, V to);

    boolean removeEdge(V from, V to);

    Iterator<V> breadthIterator(V from);

    Iterator<V> deepIterator(V from);

    boolean hasCyclic();

    boolean hasCyclic(V v);

    boolean isDirected();

    boolean isConnected();

    interface IWeightedEdge<E> extends Comparable<E> {
        @Override
        int compareTo(E o);

    }

    class Edge<V, E> {
        public final V from;
        public final V to;
        public final E weight;

        public Edge(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

}
