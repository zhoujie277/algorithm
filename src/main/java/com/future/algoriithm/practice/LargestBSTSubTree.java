package com.future.algoriithm.practice;

import com.future.utils.drawtree.IDrawableTree;
import com.future.utils.drawtree.PrintTreeUtil;

/**
 * 最大的BST子树
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST),
 * where largest means subtree with largest number of nodes in it.
 *
 * @author jayzhou
 */
public class LargestBSTSubTree {

    public int largestBSTSubtree(TreeNode root) {
        TreeInfo info = getInfo(root);
        return info == null ? 0 : info.size;
    }

    private TreeInfo getInfo(TreeNode node) {
        if (node == null) return null;

        TreeInfo leftInfo = getInfo(node.left);
        TreeInfo rightInfo = getInfo(node.right);

        if (leftInfo == null && rightInfo == null) {
            return new TreeInfo(node, node.val, node.val, 1);
        }
        if (leftInfo != null && rightInfo != null) {
            if (node.left == leftInfo.node && node.val > leftInfo.max
                    && node.right == rightInfo.node && node.val < rightInfo.min) {
                return new TreeInfo(node, rightInfo.max, leftInfo.min, leftInfo.size + rightInfo.size + 1);
            }
            return leftInfo.size > rightInfo.size ? leftInfo : rightInfo;
        }
        if (leftInfo == null) {
            if (node.right == rightInfo.node && node.val < rightInfo.min) {
                return new TreeInfo(node, rightInfo.max, node.val, rightInfo.size + 1);
            }
            return rightInfo;
        }
        if (node.left == leftInfo.node && node.val > leftInfo.max) {
            return new TreeInfo(node, node.val, leftInfo.min, leftInfo.size + 1);
        }
        return leftInfo;
    }

    private static class TreeInfo {
        TreeNode node;
        int max;
        int min;
        int size;

        public TreeInfo(TreeNode node, int max, int min, int size) {
            this.node = node;
            this.max = max;
            this.min = min;
            this.size = size;
        }
    }

    public static void main(String[] args) {
        TreeNode n30 = new TreeNode(30);
        TreeNode n8 = new TreeNode(8);
        TreeNode n13 = new TreeNode(13);
        TreeNode n22 = new TreeNode(22);
        TreeNode n29 = new TreeNode(29);
        TreeNode n11 = new TreeNode(11, n8, n13);
        TreeNode n25 = new TreeNode(25, n22, n29);
        TreeNode n16 = new TreeNode(16, n11, n25);
        TreeNode n18 = new TreeNode(18);
        TreeNode n15 = new TreeNode(15, null, n18);
        TreeNode n14 = new TreeNode(14, n30, n16);
        TreeNode n10 = new TreeNode(10, n14, n15);

        int i = new LargestBSTSubTree().largestBSTSubtree(n10);
        System.out.println("largestBST=" + i);

        PrintTreeUtil.printTree(new IDrawableTree<TreeNode>() {
            @Override
            public TreeNode root() {
                return n10;
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
        });

    }

}
