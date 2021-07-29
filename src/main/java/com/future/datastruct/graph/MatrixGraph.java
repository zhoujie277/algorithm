package com.future.datastruct.graph;

import com.future.datastruct.list.DynamicArray;
import com.future.datastruct.list.LinkedQueue;
import com.future.datastruct.list.LinkedStack;
import com.future.datastruct.union.TreeUnionFind;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 邻接矩阵实现的图
 * <p>
 * 邻接矩阵采用数组结构，
 * 一维数组存储顶点，二维数组存储边；
 * 无论是空间复杂度和时间复杂度都较高。
 * 不适用于存储大量顶点。
 *
 * @author jayzhou
 */
public class MatrixGraph<V> implements IGraph<V> {
    private static final Integer DEFAULT_EDGE = 1;

    // 顶点数组
    protected final Vertex<V>[] vertices;
    // 边数组。Object[x][y] = 1， x表示fromVertex，y表示toVertex，1说明可达路径
    protected final Object[][] edges;
    protected int numOfEdge = 0;
    protected int numOfVertex = 0;
    protected final boolean directed;

    private final TreeUnionFind<Integer> unionFindSet = new TreeUnionFind<>();

    public MatrixGraph(int cap) {
        this(cap, false);
    }

    @SuppressWarnings("unchecked")
    public MatrixGraph(int cap, boolean directed) {
        this.vertices = new Vertex[cap];
        this.edges = new Object[cap][cap];
        this.directed = directed;
    }

    @SuppressWarnings("unchecked")
    public MatrixGraph(V... vertices) {
        this(vertices.length, false);
        addVertices(vertices);
    }

    @SuppressWarnings("unchecked")
    public MatrixGraph(boolean directed, V... vertices) {
        this(vertices.length, directed);
        addVertices(vertices);
    }

    @Override
    public int numOfVertices() {
        return numOfVertex;
    }

    @Override
    public int numOfEdges() {
        return numOfEdge;
    }

    @Override
    public boolean containsVertex(V vertex) {
        return getVertexIndex(vertex) != -1;
    }

    @Override
    public boolean addVertex(V vertex) {
        if (vertex == null) throw new NullPointerException();
        rangeCheck();
        if (containsVertex(vertex)) return false;
        Vertex<V> vVertex = newVertex(vertex, numOfVertex);
        unionFindSet.makeSet(numOfVertex);
        vertices[numOfVertex++] = vVertex;
        return true;
    }

    @SafeVarargs
    @Override
    public final void addVertices(V... vertices) {
        for (V vertex : vertices) {
            addVertex(vertex);
        }
    }

    private void resetUnionFindSet() {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j] != null) {
                    unionFindSet.union(i, j);
                }
            }
        }
    }

    @Override
    public boolean addEdge(V from, V to) {
        int fromVertexIndex = getVertexIndex(from);
        int toVertexIndex = getVertexIndex(to);
        return addEdgeWeight(fromVertexIndex, toVertexIndex, DEFAULT_EDGE);
    }

    protected boolean addEdgeWeight(int fromIndex, int toIndex, Object weight) {
        if (fromIndex == -1 || toIndex == -1) return false;
        edges[fromIndex][toIndex] = weight;
        vertices[fromIndex].addOutDegree(toIndex);
        vertices[toIndex].addInDegree(fromIndex);
        addReverseEdge(fromIndex, toIndex, weight);
        unionFindSet.union(fromIndex, toIndex);
        numOfEdge++;
        return true;
    }

    protected void addReverseEdge(int fromIndex, int toIndex, Object weight) {
        if (!directed) {
            edges[toIndex][fromIndex] = weight;
            vertices[fromIndex].addInDegree(toIndex);
            vertices[toIndex].addOutDegree(fromIndex);
        }
    }

    @Override
    public boolean removeEdge(V from, V to) {
        int fromVertexIndex = getVertexIndex(from);
        int toVertexIndex = getVertexIndex(from);
        if (fromVertexIndex == -1 || toVertexIndex == -1) return false;
        edges[fromVertexIndex][toVertexIndex] = null;
        if (!directed) {
            edges[toVertexIndex][fromVertexIndex] = null;
        }
        vertices[fromVertexIndex].removeTo(toVertexIndex);
        vertices[toVertexIndex].removeFrom(fromVertexIndex);
        numOfEdge--;
        unionFindSet.reset();
        resetUnionFindSet();
        return true;
    }

    @Override
    public Iterator<V> breadthIterator(V from) {
        return new BreadthItr(from);
    }

    @Override
    public Iterator<V> deepIterator(V from) {
        return new DeepItr(from);
    }

    @Override
    public boolean hasCyclic() {
        int vertexIndex = 0;
        TreeUnionFind<Integer> treeUnionFind = new TreeUnionFind<>();
        treeUnionFind.makeSet(vertexIndex);
        do {
            DynamicArray<Integer> toVertices = vertices[vertexIndex].toVertices;
            for (int i = 0; i < toVertices.size(); i++) {
                int to = toVertices.get(i);
                if (to <= vertexIndex) continue;
                if (treeUnionFind.isConnected(vertexIndex, to)) {
                    return true;
                }
                treeUnionFind.union(vertexIndex, to);
            }
            vertexIndex++;
        } while (vertexIndex < vertices.length);
        return false;
    }

    @Override
    public boolean hasCyclic(V v) {

        return false;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public boolean isConnected() {
        for (int i = 1; i < vertices.length; i++) {
            if (!unionFindSet.isConnected(i, 0)) {
                return false;
            }
        }
        return true;
    }

    private void rangeCheck() {
        if (numOfVertex >= edges.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    protected int getVertexIndex(V v) {
        if (v == null) return -1;
        for (int i = 0; i < numOfVertex; i++) {
            if (v.equals(vertices[i].element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MatrixGraph ");
        builder.append("vertices=");
        builder.append(Arrays.toString(vertices));
        builder.append("\n");
        for (Object[] edge : edges) {
            builder.append(Arrays.toString(edge));
            builder.append("\n");
        }
        return builder.toString();
    }

    private class BreadthItr implements Iterator<V> {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        boolean[] visited = new boolean[numOfVertex];

        BreadthItr(V vertex) {
            int vertexIndex = getVertexIndex(vertex);
            visited[vertexIndex] = true;
            queue.push(vertexIndex);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @SuppressWarnings("unchecked")
        @Override
        public V next() {
            Integer index = queue.poll();
            for (int i = 0; i < edges.length; i++) {
                if (i != index && edges[index][i] != null && !visited[i]) {
                    queue.push(i);
                    visited[i] = true;
                }
            }
            return (V) vertices[index];
        }
    }

    private class DeepItr implements Iterator<V> {
        LinkedStack<Integer> stack = new LinkedStack<>();
        boolean[] visited = new boolean[numOfVertex];

        DeepItr(V vertex) {
            int vertexIndex = getVertexIndex(vertex);
            stack.push(vertexIndex);
            visited[vertexIndex] = true;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @SuppressWarnings("unchecked")
        @Override
        public V next() {
            int pop = stack.pop();
            for (int i = 0; i < edges.length; i++) {
                if (i != pop && edges[pop][i] != null && !visited[i]) {
                    visited[i] = true;
                    stack.push(i);
                }
            }
            return (V) vertices[pop];
        }
    }

    protected Vertex<V> newVertex(V element, int index) {
        return new Vertex<>(element, index);
    }

    protected static class Vertex<V> {
        DynamicArray<Integer> fromVertices = new DynamicArray<>(2);
        DynamicArray<Integer> toVertices = new DynamicArray<>(2);
        V element;
        int index;

        public Vertex(V v, int index) {
            this.element = v;
            this.index = index;
        }

        public void addInDegree(int fromIndex) {
            this.fromVertices.add(fromIndex);
        }

        public void addOutDegree(int toIndex) {
            this.toVertices.add(toIndex);
        }

        public void removeFrom(int fromIndex) {
            this.fromVertices.remove(fromIndex);
        }

        public void removeTo(int toIndex) {
            this.toVertices.remove(toIndex);
        }

        @Override
        public String toString() {
            return "Vertex{" + element +
                    '}';
        }
    }
}
