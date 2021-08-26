package com.future.leetcode.tree;

/**
 * 填充每个结点的下一个右侧结点指针2
 * 给定一个二叉树
 * * struct Node {
 * *   int val;
 * *   Node *left;
 * *   Node *right;
 * *   Node *next;
 * * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xorvud/
 *
 * @author jayzhou
 */
public class FillCompleteBinaryTree2 {

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

    public Node connect(Node root) {
        if (root == null) return null;
        Node start = root;
        while (start != null) {
            Node nextStart = null;
            Node last = null;
            for (Node current = start; current != null; current = current.next) {
                if (current.left != null) {
                    if (last != null) {
                        last.next = current.left;
                    }
                    if (nextStart == null) {
                        nextStart = current.left;
                    }
                    last = current.left;
                }
                if (current.right != null) {
                    if (last != null) {
                        last.next = current.right;
                    }
                    if (nextStart == null) {
                        nextStart = current.right;
                    }
                    last = current.right;
                }
            }
            start = nextStart;
        }
        return root;
    }
}
