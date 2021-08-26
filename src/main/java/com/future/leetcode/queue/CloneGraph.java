package com.future.leetcode.queue;

import java.util.*;

/**
 * 克隆图
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * <p>
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * 输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
 * 输出：[[2,4],[1,3],[2,4],[1,3]]
 * 解释：
 * 图中有 4 个节点。
 * 节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
 * 节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
 * 节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
 * 节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
 * <p>
 * 提示：
 * 节点数不超过 100 。
 * 每个节点值Node.val 都是唯一的，1 <= Node.val <= 100。
 * 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
 * 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p的邻居。
 * 图是连通图，你可以从给定节点访问到所有节点。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/gmcr6/
 *
 * @author jayzhou
 */
@SuppressWarnings("unused")
public class CloneGraph {

    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraphBFS(Node node) {
        if (node == null) return null;
        HashMap<Node, Node> hashMap = new HashMap<>();
        Deque<Node> deque = new ArrayDeque<>();
        deque.addLast(node);
        Node root = new Node(node.val);
        hashMap.put(node, root);
        while (!deque.isEmpty()) {
            Node first = deque.pollFirst();
            Node clone = hashMap.get(first);
            for (int i = 0; i < first.neighbors.size(); i++) {
                Node c = first.neighbors.get(i);
                Node pp = hashMap.get(c);
                if (pp == null) {
                    pp = new Node(c.val);
                    hashMap.put(c, pp);
                    deque.addLast(c);
                }
                clone.neighbors.add(pp);
            }
        }
        return root;
    }

    public Node cloneGraphDFS(Node node) {
        if (node == null) return null;
        HashMap<Node, Node> hashSet = new HashMap<>();
        return cloneGraphSimple(node, hashSet);
    }

    public Node cloneGraphSimple(Node node, HashMap<Node, Node> hashMap) {
        if (hashMap.containsKey(node)) return hashMap.get(node);
        Node p = new Node(node.val);
        hashMap.put(node, p);
        for (int i = 0; i < node.neighbors.size(); i++) {
            p.neighbors.add(cloneGraphSimple(node.neighbors.get(i), hashMap));
        }
        return p;
    }

    public Node cloneGraph2(Node node) {
        if (node == null) return null;
        HashMap<Node, Node> hashSet = new HashMap<>();
        hashSet.put(node, null);
        return cloneGraph(node, hashSet);
    }

    public Node cloneGraph(Node node, HashMap<Node, Node> hashSet) {
        Node p = new Node(node.val);
        hashSet.put(node, p);
        for (int i = 0; i < node.neighbors.size(); i++) {
            Node to = node.neighbors.get(i);
            if (hashSet.containsKey(to)) {
                hashSet.put(to, null);
                Node c = cloneGraph(to, hashSet);
                p.neighbors.add(c);
            } else {
                p.neighbors.add(hashSet.get(to));
            }
        }
        return p;
    }

    public static void main(String[] args) {
        CloneGraph cloneGraph = new CloneGraph();
        Node root = new Node(1);
        Node tw = new Node(2);
        Node t = new Node(3);
        root.neighbors.add(tw);
        root.neighbors.add(t);
        tw.neighbors.add(root);
        tw.neighbors.add(t);
        t.neighbors.add(root);
        t.neighbors.add(tw);
        Node node = cloneGraph.cloneGraphBFS(root);
        System.out.println(node.val);
        for (int i = 0; i < node.neighbors.size(); i++) {
            System.out.print(node.neighbors.get(i).val + "\n");
        }
    }

}
