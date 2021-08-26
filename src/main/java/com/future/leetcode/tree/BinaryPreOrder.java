package com.future.leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的前序遍历
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class BinaryPreOrder {

    public List<Integer> morris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        return result;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        if (root != null)
            deque.addLast(root);
        while (!deque.isEmpty()) {
            TreeNode treeNode = deque.pollLast();
            result.add(treeNode.val);
            if (treeNode.right != null) {
                deque.addLast(treeNode.right);
            }
            if (treeNode.left != null) {
                deque.addLast(treeNode.left);
            }
        }
        return result;
    }


    public List<Integer> preorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    public void preorder(TreeNode root, List<Integer> result) {
        if (root == null) return;
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }

}
