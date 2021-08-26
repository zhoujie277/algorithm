package com.future.leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的中序遍历
 * <p>
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class BinaryInOrder {

    public List<Integer> morris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
//TODO:
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode currentNode = root;
        while (currentNode != null || !deque.isEmpty()) {
            while (currentNode != null) {
                deque.addLast(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = deque.pollLast();
            result.add(currentNode.val);
            currentNode = currentNode.right;
        }
        return result;
    }

    private List<Integer> inorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode nl = new TreeNode(2);
        TreeNode nr = new TreeNode(3);
        n1.left = nl;
        n1.right = nr;
        BinaryInOrder binaryInOrder = new BinaryInOrder();
        System.out.println(binaryInOrder.inorderTraversal(n1));
    }
}
