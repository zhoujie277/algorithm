package com.future.datastruct.graph;

import com.future.utils.PrintUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Objects;

public class GraphTest {

    @Test
    public void testMatrixGraph() {
        IGraph<String> graph = new MatrixGraph<>(GraphData.VERTICES_AI);
        addEdge(graph, GraphData.BFS_AI);
        PrintUtils.printKey(graph, "true");

        Iterator<String> b = graph.breadthIterator("A");
        while (b.hasNext()) {
            PrintUtils.print(b.next() + " ");
        }
        PrintUtils.println();
        Iterator<String> a = graph.deepIterator("A");
        while (a.hasNext()) {
            PrintUtils.print(a.next() + " ");
        }

        Assert.assertEquals(graph.numOfVertices(), GraphData.VERTICES_AI.length);
    }

    private static <V> IGraph<V> addEdge(IGraph<V> graph, Object[][] data) {
        for (Object[] edge : data) {
            graph.addEdge((V) edge[0], (V) edge[1]);
        }
        return graph;
    }

    private static <V> IWeightGraph<V, Edge> addWeightedEdge(IWeightGraph<V, Edge> graph, Object[][] data) {
        for (Object[] edge : data) {
            int e = (int) edge[2];
            graph.addEdge((V) edge[0], (V) edge[1], new Edge(e));
        }
        return graph;
    }

    static class Edge implements IWeightGraph.IWeightedEdge<Edge> {
        int distance;

        Edge(int weight) {
            this.distance = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return distance - o.distance;
        }

        @Override
        public String toString() {
            return distance + "";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return distance == edge.distance;
        }

        @Override
        public int hashCode() {
            return Objects.hash(distance);
        }
    }

    @Test
    public void testMinimalSpanningTree() {
        IWeightGraph<Integer, Edge> graph = new WeightMatrixGraph<>(GraphData.VERTICES_09);
        addWeightedEdge(graph, GraphData.MST_01);
        Assert.assertTrue(graph.isConnected());
        System.out.println(graph);
        IWeightGraph.Edge<Integer, Edge>[] primEdges = graph.minimalSpanningTree(IWeightGraph.MINIMAL_SPANNING_TREE_PRIM);
        for (IWeightGraph.Edge<Integer, Edge> primEdge : primEdges) {
            System.out.println(primEdge);
        }
        System.out.println("---------------------");
        IWeightGraph.Edge<Integer, Edge>[] kruskal = graph.minimalSpanningTree(IWeightGraph.MINIMAL_SPANNING_TREE_KRUSKAL);
        for (IWeightGraph.Edge<Integer, Edge> edge : kruskal) {
            System.out.println(edge);
        }
    }

    @Test
    public void testListGraph() {
        IWeightGraph<Integer, Edge> graph = new ListGraph<>(false, GraphData.VERTICES_09);
        addWeightedEdge(graph, GraphData.MST_01);
        Assert.assertTrue(graph.isConnected());
        System.out.println(graph);
        IWeightGraph.Edge<Integer, Edge>[] primEdges = graph.minimalSpanningTree(IWeightGraph.MINIMAL_SPANNING_TREE_PRIM);
        for (IWeightGraph.Edge<Integer, Edge> primEdge : primEdges) {
            System.out.println(primEdge);
        }
        System.out.println("---------------------");
        IWeightGraph.Edge<Integer, Edge>[] kruskal = graph.minimalSpanningTree(IWeightGraph.MINIMAL_SPANNING_TREE_KRUSKAL);
        for (IWeightGraph.Edge<Integer, Edge> edge : kruskal) {
            System.out.println(edge);
        }
    }

    @Test
    public void testCyclic() {
        IWeightGraph<Integer, Edge> graph = new WeightMatrixGraph<>(GraphData.VERTICES_09);
        addWeightedEdge(graph, GraphData.MST_01);
        Assert.assertTrue(graph.hasCyclic());

        MatrixGraph<Integer> acyclic = new MatrixGraph<>(GraphData.VERTICES_09);
        addEdge(acyclic, GraphData.ACyclic);
        Assert.assertFalse(acyclic.hasCyclic());
        Assert.assertTrue(acyclic.isConnected());
    }
}
