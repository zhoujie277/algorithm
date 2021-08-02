package com.future.datastruct.graph;

import com.future.datastruct.heap.BinaryHeap;
import com.future.datastruct.heap.BinaryIndexHeap;
import com.future.datastruct.list.DynamicArray;
import com.future.datastruct.list.LinkedQueue;
import com.future.datastruct.list.LinkedStack;
import com.future.datastruct.set.RBTreeSet;
import com.future.datastruct.union.TreeUnionFind;
import com.future.utils.PrintUtils;

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
        int toVex = indexOfVertex(to);
        Path<E>[] dijkstra = dijkstra(from, toVex);
        if (dijkstra == null) return null;
        for (Path<E> path : dijkstra) {
            if (path != null && path.adjVex == toVex) {
                return pathToPathInfo(path);
            }
        }
        return null;
    }

    @Override
    public PathInfo<V, E>[] shortestPath(V from, int type) {
        Path<E>[] paths;
        if (type == SHORTEST_PATH_BELLMAN_FORD) {
            paths = bellmanFord(from);
        } else {
            paths = dijkstra(from, null);
        }
        if (paths == null) return null;
        return pathToPathInfo(paths, numOfVertex - 1);
    }

    @SuppressWarnings("unchecked")
    private Path<E>[] dijkstra(V from, Integer toVex) {
        int firstPickupVex = indexOfVertex(from);
        if (firstPickupVex == -1) return null;
        RBTreeSet<Integer> redundant = new RBTreeSet<>();
        BinaryIndexHeap<Path<E>> heap = new BinaryIndexHeap<>();
        Path<E>[] paths = new Path[numOfVertex];
        paths[firstPickupVex] = new Path<>(firstPickupVex);
        paths[firstPickupVex].addVertex(firstPickupVex);
        for (Path<E> path = paths[firstPickupVex]; path != null; ) {
            if (toVex != null && path.adjVex == toVex) break;
            redundant.add(path.adjVex);
            Vertex<V, E> vertex = vertices[path.adjVex];
            Edge<E> edge = vertex.firstEdge;
            while (edge != null) {
                Edge<E> e = edge;
                edge = edge.next;
                if (redundant.contains(e.adjVex)) continue;
                Path<E> p = slack(e, paths);
                if (p == null || p.weight == null) continue;
                if (p.queueIndex == null) {
                    p.queueIndex = heap.insert(p);
                } else {
                    heap.update(p.queueIndex);
                }
            }
            path = heap.remove();
        }
        paths[firstPickupVex] = null;
        return paths;
    }

    @SuppressWarnings("unchecked")
    private Path<E>[] bellmanFord(V from) {
        int firstVex = indexOfVertex(from);
        if (firstVex == -1) return null;
        Path<E>[] paths = new Path[numOfVertex];
        paths[firstVex] = new Path<>(firstVex, null);
        paths[firstVex].addVertex(firstVex);
        for (int j = 0; j < numOfVertex - 1; j++) {
            for (Vertex<V, E> vertex : vertices) {
                Edge<E> e = vertex.firstEdge;
                while (e != null) {
                    slack(e, paths);
                    e = e.next;
                }
            }
        }
        paths[firstVex] = null;
        return paths;
    }

    /**
     * 松弛操作
     * 对一条边进行松弛操作
     */
    private Path<E> slack(Edge<E> edge, Path<E>[] paths) {
        // path等于空为无穷大，weight等于空表示权为0
        Path<E> inDegreePath = paths[edge.fromVex];
        if (inDegreePath == null) return null;
        Path<E> path = paths[edge.adjVex];
        E newWeight = inDegreePath.weight != null ? inDegreePath.weight.add(edge.weight) : edge.weight;
        if (newWeight == null) throw new NullPointerException();
        if (path == null) {
            path = new Path<>(edge.adjVex, newWeight);
            path.addVertices(inDegreePath.vertices);
            path.addVertex(edge.adjVex);
            paths[edge.adjVex] = path;
        } else {
            if (path.weight != null && path.weight.compareTo(newWeight) > 0) {
                path.weight = newWeight;
                path.vertices.clear();
                path.addVertices(inDegreePath.vertices);
                path.addVertex(edge.adjVex);
            }
        }
        return path;
    }

    @SuppressWarnings("unchecked")
    private PathInfo<V, E>[] pathToPathInfo(Path<E>[] paths, int length) {
        PathInfo<V, E>[] pathInfos = new PathInfo[length];
        for (int vex = 0, j = 0; vex < paths.length; vex++) {
            if (paths[vex] != null) {
                pathInfos[j++] = pathToPathInfo(paths[vex]);
            }
        }
        return pathInfos;
    }

    @SuppressWarnings("unchecked")
    private PathInfo<V, E> pathToPathInfo(Path<E> path) {
        V toVertex = vertices[path.adjVex].element;
        DynamicArray<Integer> integers = path.vertices;
        V[] vexes = (V[]) new Object[integers.size()];
        for (int k = 0; k < vexes.length; k++) {
            vexes[k] = vertices[integers.get(k)].element;
        }
        return new PathInfo<>(toVertex, path.weight, vexes);
    }

    @Override
    public PathInfo<V, E>[][] shortestPath() {
        Path<E>[][] floyd = floyd();
        PathInfo<V, E>[][] vePathInfo = new PathInfo[numOfVertex][numOfVertex];
        for (int i = 0; i < floyd.length; i++) {
            for (int j = 0; j < floyd[i].length; j++) {
                if (floyd[i][j] != null) {
                    vePathInfo[i][j] = pathToPathInfo(floyd[i][j]);
                }
            }
        }
        return vePathInfo;
    }

    private Path<E>[][] floyd() {
        Path<E>[][] paths = new Path[numOfVertex][numOfVertex];
        for (int i = 0; i < vertices.length; i++) {
            Edge<E> e = vertices[i].firstEdge;
            while (e != null) {
                paths[i][e.adjVex] = new Path<>(e.adjVex, e.weight);
                paths[i][e.adjVex].addVertex(i);
                paths[i][e.adjVex].addVertex(e.adjVex);
                e = e.next;
            }
        }
        PrintUtils.println(paths);
        // path == null 表示路径无穷大
        // path != null && path.weight == null 表示权为0
        for (int k = 0; k < numOfVertex; k++) {
            for (int i = 0; i < numOfVertex; i++) {
                // optimize
                if (i == k || paths[i][k] == null) continue;
                for (int j = 0; j < numOfVertex; j++) {
//                    if (i == j || paths[i][k] == null || paths[k][j] == null) continue;
                    if (i == j || paths[k][j] == null) continue;
                    E newWeight = paths[i][k].weight.add(paths[k][j].weight);
                    if (paths[i][j] == null) {
                        paths[i][j] = new Path<>(j, newWeight);
                        paths[i][j].addVertex(i);
                        paths[i][j].addVertex(j);
                    } else if (paths[i][j].weight.compareTo(newWeight) > 0) {
                        paths[i][j].weight = newWeight;
                        paths[i][j].vertices.clear();
                        paths[i][j].addVertices(paths[i][k].vertices);
                        paths[i][j].addVertices(paths[k][j].vertices, 1);
                    }
                }
            }
        }
        PrintUtils.println("--------------------");
        PrintUtils.println(paths);
        return paths;
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
        // 这里只适用于无向图或者有向图的来回权重是一样的
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

        public Path(int adjVex) {
            this.adjVex = adjVex;
        }

        public Path(int adjVex, E weight) {
            this.adjVex = adjVex;
            this.weight = weight;
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

        public void addVertices(DynamicArray<Integer> vertices, int start) {
            for (int j = start; j < vertices.size(); j++) {
                this.vertices.add(vertices.get(j));
            }
        }

        @Override
        public String toString() {
            return "{" + adjVex +
                    "," + weight +
                    '}';
        }
    }
}
