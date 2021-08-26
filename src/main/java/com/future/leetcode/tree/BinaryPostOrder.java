package com.future.leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("all")
public class BinaryPostOrder {

    public List<Integer> morris(TreeNode root) {
        return null;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList();
        TreeNode currentNode = root;
        TreeNode lastVisited = null;
        while (currentNode != null || !deque.isEmpty()) {
            while (currentNode != null) {
                deque.addLast(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = deque.peekLast();
            if (currentNode.right == null || currentNode.right == lastVisited) {
                deque.pollLast();
                result.add(currentNode.val);
                lastVisited = currentNode;
                currentNode = null;
            } else {
                currentNode = currentNode.right;
            }
        }
        return result;
    }

    public List<Integer> postorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    public void postorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(1);
        root.left = left;
        root.right = right;
        BinaryPostOrder postOrder = new BinaryPostOrder();
        postOrder.postorderTraversal(root);
    }
}
