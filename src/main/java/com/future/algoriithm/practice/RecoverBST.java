package com.future.algoriithm.practice;

/**
 * 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 *
 * @author jayzhou
 */
public class RecoverBST {

    private TreeNode prev;
    private TreeNode first;
    private TreeNode second;

    public void recoverTree(TreeNode root) {
        morris(root);
        if (first == null || second == null) return;
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    public void morris(TreeNode root) {
        if (root == null) return;
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                access(node);
                node = node.right;
            } else {
                TreeNode pred = node.left;
                while (pred.right != null && pred.right != node) {
                    pred = pred.right;
                }
                if (pred.right == null) {
                    pred.right = node;
                    node = node.left;
                } else {
                    pred.right = null;
                    access(node);
                    node = node.right;
                }
            }
        }
    }

    public void access(TreeNode node) {
        if (prev != null && node.val < prev.val) {
            second = node;
            if (first != null) return;
            first = prev;
        }
        prev = node;
    }

    public void alpha(TreeNode root) {
        inorder(root);
        if (first == null || second == null) return;
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        access(root);
        inorder(root.right);
    }

    public void print(TreeNode node) {
        if (node == null) return;
        print(node.left);
        System.out.print(node.val + " ");
        print(node.right);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n3;
        n3.right = n2;
        RecoverBST recoverBST = new RecoverBST();
        recoverBST.print(n1);
        System.out.println();
        recoverBST.recoverTree(n1);
        recoverBST.print(n1);
    }
}
