package com.future.leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个 N 叉树，返回其节点值的 前序遍历 。
 * <p>
 * N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 */

@SuppressWarnings("all")
public class NTree {

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * 给定一个 N 叉树，找到其最大深度。
     * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔
     */
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int max = 0;
        for (Node child : root.children) {
            max = Math.max(max, maxDepth(child));
        }
        return max + 1;
    }

    /**
     * 给定一个 N 叉树，返回其节点值的层序遍历。
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Deque<Node> deque = new LinkedList<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Node node = deque.pollFirst();
                list.add(node.val);
                for (Node child : node.children) {
                    deque.addLast(child);
                }
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 给定一个 N 叉树，返回其节点值的 后序遍历 。
     */
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }
    public void postorder(Node root, List<Integer> list) {
        if (root == null) return;
        for (Node child : root.children) {
            postorder(child, list);
        }
        list.add(root.val);
    }

    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    public void preorder(Node root, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        for (Node child : root.children) {
            preorder(child, list);
        }
    }
}
