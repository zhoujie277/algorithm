package com.future.leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。
 * 指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 * <p>
 * 你可以假设next()调用总是有效的，也就是说，当调用 next()时，BST 的中序遍历中至少存在一个下一个数字。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/introduction-to-data-structure-binary-search-tree/xpg4qe/
 *
 * @author jayzhou
 */
public class BSTIterator {

    Deque<TreeNode> deque = new LinkedList<>();

    public BSTIterator(TreeNode root) {
        inorder(root);
    }

    private void inorder(TreeNode node) {
        while (node != null) {
            deque.addLast(node);
            node = node.left;
        }
    }

    public int next() {
        TreeNode node = deque.pollLast();
        inorder(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !deque.isEmpty();
    }
}
