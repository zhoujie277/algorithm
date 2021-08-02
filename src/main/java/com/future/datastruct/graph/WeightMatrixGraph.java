package com.future.datastruct.graph;

import com.future.datastruct.heap.BinaryHeap;
import com.future.datastruct.set.RBTreeSet;
import com.future.datastruct.union.TreeUnionFind;

import java.util.Objects;


/**
 * 带权邻接矩阵图
 *
 * @author jayzhou
 */
@SuppressWarnings("unused")
public class WeightMatrixGraph<V, E extends IWeightedEdge<E>> extends MatrixGraph<V> implements IWeightGraph<V, E> {

    public WeightMatrixGraph(int cap) {
        super(cap);
    }

    public WeightMatrixGraph(int cap, boolean directed) {
        super(cap, directed);
    }

    @SuppressWarnings("unchecked")
    public WeightMatrixGraph(V... vertices) {
        super(vertices);
    }

    @SuppressWarnings("unchecked")
    public WeightMatrixGraph(boolean directed, V... vertices) {
        super(directed, vertices);
    }

    @Override
    public boolean addEdge(V from, V to, E edge) {
        int fromVertexIndex = getVertexIndex(from);
        int toVertexIndex = getVertexIndex(to);
        WeightedEdge weightedEdge = newWeightedEdge(fromVertexIndex, toVertexIndex, edge);
        return addEdgeWeight(fromVertexIndex, toVertexIndex, weightedEdge);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addReverseEdge(int fromIndex, int toIndex, Object edge) {
        E element = ((WeightedEdge) edge).weight;
        WeightedEdge weightedEdge = newWeightedEdge(toIndex, fromIndex, element);
        super.addReverseEdge(fromIndex, toIndex, weightedEdge);
    }

    @Override
    public EdgeInfo<V, E>[] minimalSpanningTree() {
        return minimalSpanningTree(MINIMAL_SPANNING_TREE_PRIM);
    }

    @Override
    public EdgeInfo<V, E>[] minimalSpanningTree(byte strategy) {
        if (strategy == MINIMAL_SPANNING_TREE_PRIM) {
            return prim();
        } else if (strategy == MINIMAL_SPANNING_TREE_KRUSKAL) {
            return kruskal();
        }
        return null;
    }

    @Override
    public PathInfo<V, E> shortestPath(V from, V to) {
        return null;
    }

    @Override
    public PathInfo<V, E>[] shortestPath(V from, int type) {
        return new PathInfo[0];
    }

    @Override
    public PathInfo<V, E>[][] shortestPath() {
        return new PathInfo[0][0];
    }

    @SuppressWarnings("unchecked")
    private EdgeInfo<V, E>[] prim() {
        if (vertices.length < 2) return null;
        EdgeInfo<V, E>[] result = new EdgeInfo[numOfVertex - 1];
        RBTreeSet<Integer> simpleSet = new RBTreeSet<>();
        BinaryHeap<WeightedEdge> heap = new BinaryHeap<>(numOfEdge);
        int resultIndex = 0;
        int vertexIndex = 0;
        simpleSet.add(vertexIndex);
        do {
            Vertex<V> vertex = vertices[vertexIndex];
            for (int i = 0; i < vertex.toVertices.size(); i++) {
                int toIndex = vertex.toVertices.get(i);
                WeightedEdge edge = (WeightedEdge) edges[vertexIndex][toIndex];
                heap.add(edge);
            }
            WeightedEdge remove;
            do {
                remove = heap.remove();
                vertexIndex = remove.toIndex;
            } while (simpleSet.contains(vertexIndex) && !heap.isEmpty());
            simpleSet.add(vertexIndex);
            result[resultIndex++] = replaceEdge(remove);
        } while (resultIndex < result.length);
        return result;
    }

    @SuppressWarnings("unchecked")
    private EdgeInfo<V, E>[] kruskal() {
        BinaryHeap<WeightedEdge> heap = new BinaryHeap<>(numOfEdge);
        for (Object[] edge : edges) {
            for (Object value : edge) {
                if (value != null) {
                    WeightedEdge o = (WeightedEdge) value;
                    heap.add(o);
                }
            }
        }
        EdgeInfo<V, E>[] result = new EdgeInfo[numOfVertex - 1];
        int index = 0;
        TreeUnionFind<Integer> unionFind = new TreeUnionFind<>();
        do {
            WeightedEdge remove = heap.remove();
            if (!unionFind.isConnected(remove.fromIndex, remove.toIndex)) {
                unionFind.union(remove.fromIndex, remove.toIndex);
                result[index++] = replaceEdge(remove);
            }
        } while (!heap.isEmpty() && index < result.length);
        return result;
    }

    private EdgeInfo<V, E> replaceEdge(WeightedEdge innerEdge) {
        return new EdgeInfo<>(vertices[innerEdge.fromIndex].element, vertices[innerEdge.toIndex].element, innerEdge.weight);
    }

    private WeightedEdge newWeightedEdge(int from, int to, E weight) {
        return new WeightedEdge(from, to, weight);
    }

    private class WeightedEdge implements IWeightedEdge<WeightedEdge>, Cloneable {
        int fromIndex;
        int toIndex;
        E weight;

        public WeightedEdge(int fromIndex, int toIndex, E weight) {
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
            this.weight = weight;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WeightedEdge that = (WeightedEdge) o;
            if (!Objects.equals(weight, that.weight)) return false;
            if (!directed && fromIndex == that.toIndex && toIndex == that.fromIndex) return true;
            return fromIndex == that.fromIndex && toIndex == that.toIndex;
        }

        @Override
        public int hashCode() {
            return 31 + weight.hashCode();
        }

        @Override
        public int compareTo(WeightedEdge o) {
            return weight.compareTo(o.weight);
        }

        @Override
        public WeightedEdge add(WeightedEdge edge) {
            return null;
        }

        @Override
        public String toString() {
            return "WeightedEdge{" +
                    "fromIndex=" + fromIndex +
                    ", toIndex=" + toIndex +
                    ", weight=" + weight +
                    '}';
        }
    }
}
