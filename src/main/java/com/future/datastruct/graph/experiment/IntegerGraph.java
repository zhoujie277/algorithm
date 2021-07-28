package com.future.datastruct.graph.experiment;

import com.future.datastruct.list.DynamicArray;
import com.future.datastruct.list.LinkedQueue;
import com.future.utils.PrintUtils;


/**

 * 该类是一个邻接矩阵实现的无向图
 */
public class IntegerGraph<T> {
    private DynamicArray<T> vertexList;
    private int[][] edges;
    private int numOfEdges;
    // 该顶点是否被访问过
    private boolean[] isVisited;

    public IntegerGraph(int n) {
        edges = new int[n][n];
        vertexList = new DynamicArray<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public void insert(T val) {
        vertexList.add(val);
    }

    public void insertEdge(int x, int y, int weight) {
        edges[x][y] = weight;
        edges[y][x] = weight;
        numOfEdges++;
    }

    public T getValueByIndex(int index) {
        return vertexList.get(index);
    }

    public void breadthFirstSearch() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (isVisited[i]) {
                breadthFirstSearch(isVisited, i);
            }
        }
    }

    public void breadthFirstSearch(boolean[] isVisited, int i) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.push(i);
        Integer temp;
        int w;
        while ((temp = queue.poll()) != null) {
            if (isVisited[i]) {
                PrintUtils.println(getValueByIndex(temp) + "->");
                isVisited[i] = true;
            }
            w = getFirstNeighbor(temp);
            while (w != -1) {
                queue.push(w);
                w = getNextNeighbor(temp, w);
            }
        }
    }

    public void deepFirstSearch(boolean[] isVisited, int i) {
        PrintUtils.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        int neighbor = getFirstNeighbor(i);
        while (neighbor != -1) {
            if (!isVisited[neighbor]) {
                deepFirstSearch(isVisited, neighbor);
            }
            neighbor = getNextNeighbor(i + 1, i);
        }
    }

    public void deepFirstSearch() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                deepFirstSearch(isVisited, i);
            }
        }
    }

    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    public void println() {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                PrintUtils.print(edges[i][j] + " ");
            }
            PrintUtils.println();
        }
    }

    public static void main(String[] args) {
        int n = 5;
        String[] vertex = {"A", "B", "C", "D", "E"};
        IntegerGraph<String> graph = new IntegerGraph<>(n);
        for (String v : vertex) {
            graph.insert(v);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.println();
    }
}
