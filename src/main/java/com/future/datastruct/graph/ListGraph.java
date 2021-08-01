package com.future.datastruct.graph;

import com.future.datastruct.heap.BinaryHeap;
import com.future.datastruct.heap.BinaryIndexHeap;
import com.future.datastruct.list.DynamicArray;
import com.future.datastruct.list.LinkedQueue;
import com.future.datastruct.list.LinkedStack;
import com.future.datastruct.set.RBTreeSet;
import com.future.datastruct.union.TreeUnionFind;

import java.util.Iterator;
import java.util.Objects;

/**
 * 邻接表实现的图
 *
 * @author jayzhou
 */
public class ListGraph<V, E extends IWeightedEdge<E>> implements IWeightGraph<V, E> {

    private final TreeUnionFind<Integer> unionFind = new TreeUnionFind<>();
    private final boolean directed;
    protected Vertex<V, E>[] vertices;
    protected int numOfVertex = 0;
    protected int numOfEdge = 0;

    @SuppressWarnings("unchecked")
    public ListGraph(int cap, boolean directed) {
        this.directed = directed;
        vertices = new Vertex[cap];
    }

    @SuppressWarnings("unchecked")
    public ListGraph(boolean directed, V... args) {
        this(args.length, directed);
        addVertices(args);
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
        return indexOfVertex(vertex) >= 0;
    }

    @Override
    public boolean addVertex(V vertex) {
        if (indexOfVertex(vertex) >= 0) return false;
        unionFind.makeSet(numOfVertex);
        vertices[numOfVertex++] = newVertex(vertex);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addVertices(V... vertices) {
        for (V vertex : vertices) {
            addVertex(vertex);
        }
    }

    @Override
    public boolean addEdge(V from, V to) {
        return addEdge(from, to, null);
    }

    @Override
    public boolean addEdge(V from, V to, E edge) {
        int fromIndex = indexOfVertex(from);
        if (fromIndex < 0) return false;
        int toIndex = indexOfVertex(to);
        if (toIndex < 0) return false;
        addEdge(fromIndex, toIndex, edge);
        if (!directed) {
            addEdge(toIndex, fromIndex, edge);
        }
        numOfEdge++;
        return false;
    }

    protected void addEdge(int fromIndex, int toIndex, E weight) {
        unionFind.union(fromIndex, toIndex);
        Edge<E> edge = newEdge(toIndex, weight);
        edge.fromVex = fromIndex;
        Edge<E> p = vertices[fromIndex].firstEdge;
        if (p == null) {
            vertices[fromIndex].firstEdge = edge;
        } else {
            while (p.next != null) {
                p = p.next;
            }
            p.next = edge;
        }
    }

    @Override
    public boolean removeEdge(V from, V to) {
        int fromIndex = indexOfVertex(from);
        if (fromIndex < 0) return false;
        int toIndex = indexOfVertex(to);
        if (toIndex < 0) return false;
        removeEdge(fromIndex, toIndex);
        if (!directed) {
            removeEdge(toIndex, fromIndex);
        }
        numOfEdge--;
        return false;
    }

    private void removeEdge(int fromIndex, int toIndex) {
        Edge<E> p = vertices[fromIndex].firstEdge;
        if (p == null) return;
        if (p.adjVex == toIndex) {
            vertices[fromIndex].firstEdge = p.next;
            p.next = null;
            return;
        }
        Edge<E> c;
        while ((c = p.next) != null) {
            if (c.adjVex == toIndex) {
                break;
            }
            p = c;
        }
        if (c == null) return;
        p.next = c.next;
        c.next = null;
    }

    @Override
    public Iterator<V> breadthIterator(V from) {
        int index = indexOfVertex(from);
        return new BreadthIterator(index);
    }

    @Override
    public Iterator<V> deepIterator(V from) {
        int index = indexOfVertex(from);
        return new DeepIterator(index);
    }

    @Override
    public boolean hasCyclic() {

        return false;
    }

    @Override
    public boolean hasCyclic(V v) {
        int cyclicIndex = indexOfVertex(v);
        if (cyclicIndex == -1) return false;
        // 深度遍历
        Vertex<V, E> vertex = vertices[cyclicIndex];
        Edge<E> e = vertex.firstEdge;
        LinkedStack<Integer> stack = new LinkedStack<>();
        while (e != null) {
            stack.push(e.adjVex);
            do {
                int toIndex = stack.pop();
                if (toIndex == cyclicIndex) {
                    return true;
                }
                Edge<E> to = vertices[toIndex].firstEdge;
                while (to != null) {
                    stack.push(to.adjVex);
                    to = to.next;
                }
            } while (!stack.isEmpty());
            e = e.next;
        }
        return false;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public boolean isConnected() {
        for (int i = 1; i < vertices.length; i++) {
            if (!unionFind.isConnected(i, 0)) {
                return false;
            }
        }
        return true;
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
    public PathInfo<V, E>[] shortestPath(V from) {
        return dijkstra(from, null);
    }

    @SuppressWarnings("unchecked")
    private PathInfo<V, E>[] dijkstra(V from, V to) {
        int firstPickupVex = indexOfVertex(from);
        if (firstPickupVex == -1) return null;
        BinaryIndexHeap<Path<E>> heap = new BinaryIndexHeap<>();
        Path<E>[] paths = new Path[numOfVertex];
        slack(firstPickupVex, firstPickupVex, paths, heap);

        int length = 0;
        for (int i = 0; i < numOfVertex - 1; i++) {
            Path<E> pickupPath = heap.remove();
            if (pickupPath == null) break;
//            PrintTreeUtil.printIndexHeap(heap);
//            PrintUtils.debug("remove----" + pickupPath);
            length++;
            slack(pickupPath.adjVex, firstPickupVex, paths, heap);
        }

        PathInfo<V, E>[] pathInfos = new PathInfo[length];
        for (int i = 0, j = 0; i < paths.length; i++) {
            if (paths[i] != null) {
                V toVertex = vertices[i].element;
                E weight = paths[i].weight;
                DynamicArray<Integer> integers = paths[i].vertices;
                V[] vexes = (V[]) new Object[integers.size()];
                for (int k = 0; k < vexes.length; k++) {
                    vexes[k] = vertices[integers.get(k)].element;
                }
                pathInfos[j++] = new PathInfo<>(toVertex, weight, vexes);
            }
        }
        return pathInfos;
    }

    /**
     * 松弛操作
     */
    private void slack(int pickupVex, int firstVex, Path<E>[] paths, BinaryIndexHeap<Path<E>> heap) {
        Vertex<V, E> vertex = vertices[pickupVex];
        Edge<E> p = vertex.firstEdge;
        Path<E> pickupPath = paths[pickupVex];
        while (p != null) {
            if (p.adjVex == firstVex) {
                p = p.next;
                continue;
            }
            Path<E> path = paths[p.adjVex];
            if (path == null) {
                path = paths[p.adjVex] = new Path<>(p);
                if (pickupPath != null) {
                    path.weight = pickupPath.weight.add(path.weight);
                    path.addVertices(pickupPath.vertices);
                } else {
                    path.addVertex(pickupVex);
                }
                path.addVertex(p.adjVex);
                path.queueIndex = heap.insert(path);
//                PrintTreeUtil.printIndexHeap(heap);
//                PrintUtils.debug("insert----" + path);
            } else {
                E newWeight = p.weight.add(pickupPath.weight);
                if (path.weight.compareTo(newWeight) > 0) {
                    path.weight = newWeight;
                    path.vertices.clear();
                    path.addVertices(pickupPath.vertices);
                    path.addVertex(p.adjVex);
                    heap.update(path.queueIndex, path);
//                    PrintTreeUtil.printIndexHeap(heap);
//                    PrintUtils.debug("update----" + path);
                }
            }
            p = p.next;
        }
    }

    @Override
    public PathInfo<V, E>[] shortestPath() {
        return new PathInfo[0];
    }

    @SuppressWarnings("unchecked")
    private EdgeInfo<V, E>[] prim() {
        RBTreeSet<Integer> treeSet = new RBTreeSet<>();
        BinaryHeap<Edge<E>> heap = new BinaryHeap<>();
        EdgeInfo<V, E>[] result = new EdgeInfo[numOfVertex - 1];
        int resultIndex = 0;
        int vexIndex = 0;
        treeSet.add(0);
        do {
            Vertex<V, E> vertex = vertices[vexIndex];
            Edge<E> e = vertex.firstEdge;
            while (e != null) {
                heap.add(e);
                e = e.next;
            }
            Edge<E> edge;
            do {
                edge = heap.remove();
                if (treeSet.add(edge.adjVex)) {
                    break;
                }
            } while (!heap.isEmpty());
            result[resultIndex++] = replaceEdge(edge);
            vexIndex = edge.adjVex;
        } while (resultIndex < result.length);
        return result;
    }

    @SuppressWarnings("unchecked")
    private EdgeInfo<V, E>[] kruskal() {
        int edgeNum = directed ? numOfEdge : (numOfEdge << 1);
        Edge<E>[] edges = new Edge[edgeNum];
        for (int vexIndex = 0, edgeIndex = 0; vexIndex < numOfVertex; vexIndex++) {
            Vertex<V, E> vertex = vertices[vexIndex];
            Edge<E> e = vertex.firstEdge;
            while (e != null) {
                edges[edgeIndex++] = e;
                e = e.next;
            }
        }
        BinaryHeap<Edge<E>> heap = new BinaryHeap<>(edges);
        TreeUnionFind<Integer> unionFind = new TreeUnionFind<>();
        EdgeInfo<V, E>[] result = new EdgeInfo[numOfVertex - 1];
        int resultIndex = 0;
        do {
            Edge<E> edge = heap.remove();
            if (unionFind.isConnected(edge.fromVex, edge.adjVex)) continue;
            unionFind.union(edge.fromVex, edge.adjVex);
            result[resultIndex++] = replaceEdge(edge);
        } while (resultIndex < result.length);
        return result;
    }

    private EdgeInfo<V, E> replaceEdge(Edge<E> edge) {
        V toVex = vertices[edge.adjVex].element;
        V fromVex = vertices[edge.fromVex].element;
        return new EdgeInfo<>(fromVex, toVex, edge.weight);
    }

    private int indexOfVertex(V data) {
        for (int i = 0; i < numOfVertex; i++) {
            if (Objects.equals(vertices[i].element, data)) {
                return i;
            }
        }
        return -1;
    }

    protected Vertex<V, E> newVertex(V data) {
        Vertex<V, E> vertex = new Vertex<>();
        vertex.element = data;
        return vertex;
    }

    protected Edge<E> newEdge(int toVertex, E weight) {
        return new Edge<>(toVertex, weight);
    }

    private class BreadthIterator implements Iterator<V> {
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        BreadthIterator(int vexIndex) {
            queue.push(vexIndex);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public V next() {
            int vexIndex = queue.poll();
            Vertex<V, E> vertex = vertices[vexIndex];
            Edge<E> e = vertex.firstEdge;
            while (e != null) {
                queue.push(e.adjVex);
                e = e.next;
            }
            return vertex.element;
        }
    }

    private class DeepIterator implements Iterator<V> {
        LinkedStack<Integer> stack = new LinkedStack<>();

        DeepIterator(int vexIndex) {
            stack.push(vexIndex);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public V next() {
            int vexIndex = stack.pop();
            Vertex<V, E> vertex = vertices[vexIndex];
            Edge<E> e = vertex.firstEdge;
            while (e != null) {
                stack.push(e.adjVex);
                e = e.next;
            }
            return vertex.element;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Vertex<V, E> vertex : vertices) {
            builder.append(vertex.element);
            builder.append(",");
            Edge<E> p = vertex.firstEdge;
            while (p != null) {
                builder.append(vertices[p.adjVex].element);
                builder.append("(");
                builder.append(p.weight);
                builder.append(")");
                builder.append(",");
                p = p.next;
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    protected static class Vertex<V, E extends IWeightedEdge<E>> {
        V element;
        Edge<E> firstEdge;

        @Override
        public String toString() {
            return "Vertex{" + element + '}';
        }
    }

    protected static class Edge<E extends IWeightedEdge<E>> implements Comparable<Edge<E>> {
        E weight;
        Edge<E> next;
        // 邻接顶点
        int adjVex;

        // 入度的顶点索引
        int fromVex;

        public Edge(int adjVex, E w) {
            this.adjVex = adjVex;
            weight = w;
        }

        @Override
        public int compareTo(Edge<E> o) {
            return weight.compareTo(o.weight);
        }
    }

    private static class Path<E extends IWeightedEdge<E>> implements Comparable<Path<E>> {
        int adjVex;
        E weight;
        BinaryIndexHeap.ElementIndex queueIndex;
        DynamicArray<Integer> vertices = new DynamicArray<>(3);

        public Path(int adjVex, E weight) {
            this.adjVex = adjVex;
            this.weight = weight;
            this.queueIndex = null;
        }

        public Path(Edge<E> edge) {
            this(edge.adjVex, edge.weight);
            this.queueIndex = null;
        }

        @Override
        public int compareTo(Path<E> o) {
            return weight.compareTo(o.weight);
        }

        public void addVertex(int vertexIndex) {
            vertices.add(vertexIndex);
        }

        public void addVertices(DynamicArray<Integer> vertices) {
            this.vertices.addAll(vertices);
        }

        @Override
        public String toString() {
            return "{" + adjVex +
                    "," + weight +
                    '}';
        }
    }
}
