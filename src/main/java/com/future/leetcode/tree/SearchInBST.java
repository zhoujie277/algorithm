package com.future.leetcode.tree;

/**
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。
 * 如果节点不存在，则返回 NULL。
 *
 * @author jayzhou
 */
public class SearchInBST {

    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode current = root;
        while (current != null) {
            if (val == current.val) {
                return current;
            }
            if (val < current.val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    /**
     * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。
     * 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode parent = null;
        TreeNode current = root;
        while (current != null) {
            parent = current;
            if (val < current.val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        TreeNode n = new TreeNode(val);
        if (val > parent.val) {
            parent.right = n;
        } else {
            parent.left = n;
        }
        return root;
    }

    /**
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的key对应的节点，并保证二叉搜索树的性质不变。
     * 返回二叉搜索树（有可能被更新）的根节点的引用。
     * 一般来说，删除节点可分为两个步骤：
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     * 说明： 要求算法时间复杂度为O(h)，h 为树的高度。
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode current = root;
        TreeNode parent = null;
        while (current != null) {
            if (key == current.val) {
                break;
            }
            parent = current;
            if (key < current.val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (current == null) return root;
        if (current.left != null && current.right != null) {
            parent = current;
            TreeNode s = current.right;
            while (s.left != null) {
                parent = s;
                s = s.left;
            }
            current.val = s.val;
            current = s;
        }
        TreeNode replacement = current.left == null ? current.right : current.left;
        if (replacement != null) {
            if (parent == null) {
                root = replacement;
            } else if (parent.left == current) {
                parent.left = replacement;
            } else {
                parent.right = replacement;
            }
        } else {
            if (parent == null) {
                root = null;
            } else if (parent.left == current) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        return root;
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        while (cur != null) {
            if (p.val > cur.val && q.val > cur.val) {
                cur = cur.right;
            } else if (p.val < cur.val && q.val < cur.val) {
                cur = cur.left;
            } else {
                break;
            }
        }
        return cur;
    }
}















