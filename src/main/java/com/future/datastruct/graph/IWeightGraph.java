package com.future.datastruct.graph;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 带权图
 */
public interface IWeightGraph<V, E> extends IGraph<V> {
    byte MINIMAL_SPANNING_TREE_PRIM = 1;
    byte MINIMAL_SPANNING_TREE_KRUSKAL = 2;

    boolean addEdge(V from, V to, E edge);

    EdgeInfo<V, E>[] minimalSpanningTree();

    EdgeInfo<V, E>[] minimalSpanningTree(byte strategy);

    /**
     * 获取两点之间的最短路径
     */
    PathInfo<V, E> shortestPath(V from, V to);

    /**
     * 获取某点到其它所有点的最短路径
     */
    PathInfo<V, E>[] shortestPath(V from);

    /**
     * 获取任意两顶点之间的最短路径
     *
     * @return
     */
    PathInfo<V, E>[] shortestPath();

    int numOfVertices();

    int numOfEdges();

    boolean containsVertex(V vertex);

    boolean addVertex(V vertex);

    void addVertices(V... vertices);

    boolean addEdge(V from, V to);

    boolean removeEdge(V from, V to);

    Iterator<V> breadthIterator(V from);

    Iterator<V> deepIterator(V from);

    boolean hasCyclic();

    boolean hasCyclic(V v);

    /**
     * 是不是有向图
     */
    boolean isDirected();

    /**
     * 是不是连通图
     */
    boolean isConnected();

    class PathInfo<V, E>  {
        public final V adjVex;
        public final E weight;
        public final V[] vertices;

        public PathInfo(V to, E weight, V[] vertices) {
            this.adjVex = to;
            this.weight = weight;
            this.vertices = vertices;
        }

        @Override
        public String toString() {
            return "PathInfo{" +
                    "adjVex=" + adjVex +
                    ", weight=" + weight +
                    ", vertices=" + Arrays.toString(vertices) +
                    '}';
        }
    }

    class EdgeInfo<V, E> {
        public final V from;
        public final V to;
        public final E weight;

        public EdgeInfo(V from, V to, E weight) {
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
