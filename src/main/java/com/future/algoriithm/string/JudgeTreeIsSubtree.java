package com.future.algoriithm.string;

/**
 * 另一个树的子树
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 * <p>
 * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
 *
 * @author jayzhou
 */
public class JudgeTreeIsSubtree {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;
        return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        // 此处用isSubTree是利用了递归的函数栈。当执行&&之后，isSameTree出栈了，如果继续调用isSameTree又要重新入栈。
        // 而isSubTree还在栈中，并且刚好处于栈顶，故可以提高性能。
        return isSameTree(t1.left, t2.left) && isSubtree(t1.right, t2.right);
    }

    public boolean isSubtree2(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) return false;
        StringBuilder rootBuilder = new StringBuilder();
        StringBuilder subBuilder = new StringBuilder();
        postSerializable(root, rootBuilder);
        postSerializable(subRoot, subBuilder);
        return rootBuilder.toString().contains(subBuilder);
    }

    public void postSerializable(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("#!");
            return;
        }
        postSerializable(node.left, sb);
        postSerializable(node.right, sb);
        sb.append(node.val).append("!");
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;
        boolean subtree = new JudgeTreeIsSubtree().isSubtree(n1, n2);
        System.out.println(subtree);
    }
}
