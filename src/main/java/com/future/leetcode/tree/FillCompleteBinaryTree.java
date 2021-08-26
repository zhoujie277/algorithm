package com.future.leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 填充每个节点的下一个右侧节点指针
 * <p>
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * * struct Node {
 * *   int val;
 * *   Node *left;
 * *   Node *right;
 * *   Node *next;
 * * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有next 指针都被设置为 NULL。
 * <p>
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xoo0ts/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
class FillCompleteBinaryTree {
    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public void dfs(Node left, Node right) {
        if (left == null || left.next == right) return;
        left.next = right;
        dfs(left.left, left.right);
        dfs(left.right, right.left);
        dfs(right.left, right.right);
    }

    public Node connect2(Node root) {
        if (root == null) return null;
        dfs(root.left, root.right);
        return root;
    }

    public Node connect(Node root) {
        if (root == null) return null;
        Deque<Node> deque = new LinkedList<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            Node prev = null;
            for (int i = 0; i < size; i++) {
                Node node = deque.pollFirst();
                if (prev != null) {
                    prev.next = node;
                }
                if (node.left != null) {
                    deque.addLast(node.left);
                }
                if (node.right != null) {
                    deque.addLast(node.right);
                }
                prev = node;
            }
        }
        return root;
    }
}
