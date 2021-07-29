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
public class WeightMatrixGraph<V, E extends IWeightGraph.IWeightedEdge<E>> extends MatrixGraph<V> implements IWeightGraph<V, E> {

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
    public Edge<V, E>[] minimalSpanningTree() {
        return minimalSpanningTree(MINIMAL_SPANNING_TREE_PRIM);
    }

    @Override
    public Edge<V, E>[] minimalSpanningTree(byte strategy) {
        if (strategy == MINIMAL_SPANNING_TREE_PRIM) {
            return prim();
        } else if (strategy == MINIMAL_SPANNING_TREE_KRUSKAL) {
            return kruskal();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private Edge<V, E>[] prim() {
        if (vertices.length < 2) return null;
        Edge<V, E>[] result = new Edge[numOfVertex - 1];
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
    private Edge<V, E>[] kruskal() {
        BinaryHeap<WeightedEdge> heap = new BinaryHeap<>(numOfEdge);
        for (Object[] edge : edges) {
            for (Object value : edge) {
                if (value != null) {
                    WeightedEdge o = (WeightedEdge) value;
                    heap.add(o);
                }
            }
        }
        Edge<V, E>[] result = new Edge[numOfVertex - 1];
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

    private Edge<V, E> replaceEdge(WeightedEdge innerEdge) {
        return new Edge<>(vertices[innerEdge.fromIndex].element, vertices[innerEdge.toIndex].element, innerEdge.weight);
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
        public String toString() {
            return "WeightedEdge{" +
                    "fromIndex=" + fromIndex +
                    ", toIndex=" + toIndex +
                    ", weight=" + weight +
                    '}';
        }
    }
}
