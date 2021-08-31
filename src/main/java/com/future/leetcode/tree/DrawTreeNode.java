package com.future.leetcode.tree;

import com.future.utils.drawtree.IDrawableTree;
import com.future.utils.drawtree.PrintTreeUtil;

public class DrawTreeNode implements IDrawableTree<TreeNode> {
    private TreeNode root;

    DrawTreeNode(TreeNode root) {
        this.root = root;
    }

    @Override
    public TreeNode root() {
        return root;
    }

    @Override
    public TreeNode left(TreeNode node) {
        return node.left;
    }

    @Override
    public TreeNode right(TreeNode node) {
        return node.right;
    }

    @Override
    public String string(TreeNode node) {
        return node.val + "";
    }

    public static void drawTree(TreeNode node) {
        PrintTreeUtil.printTree(new DrawTreeNode(node));
    }

}
