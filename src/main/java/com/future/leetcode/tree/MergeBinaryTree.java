package com.future.leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 合并二叉树
 * <p>
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，
 * 否则不为NULL 的节点将直接作为新二叉树的节点。
 * 输入:
 * * 	Tree 1                     Tree 2
 * *           1                         2
 * *          / \                       / \
 * *         3   2                     1   3
 * *        /                           \   \
 * *       5                             4   7
 * 输出:
 * 合并后的树:
 * * 	     3
 * * 	    / \
 * * 	   4   5
 * * 	  / \   \
 * * 	 5   4   7
 * *
 * <p>
 * 合并必须从两个树的根节点开始
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class MergeBinaryTree {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        TreeNode node = new TreeNode(root1.val + root2.val);
        node.left = mergeTrees(root1.left, root2.left);
        node.right = mergeTrees(root1.right, root2.right);
        return node;
    }

    public TreeNode mergeTreesBFS(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        Deque<TreeNode> deque1 = new LinkedList<>();
        Deque<TreeNode> deque2 = new LinkedList<>();
        deque1.addLast(root1);
        deque2.addLast(root2);
        root1.val += root2.val;
        while (!deque1.isEmpty()) {
            TreeNode node1 = deque1.pollFirst();
            TreeNode node2 = deque2.pollFirst();
            if (node1.left != null && node2.left != null) {
                deque1.addLast(node1.left);
                deque2.addLast(node2.left);
                node1.left.val += node2.left.val;
            } else {
                TreeNode node = node1.left == null ? node2.left : node1.left;
                node1.left = node;
            }
            if (node1.right != null && node2.right != null) {
                deque1.addLast(node1.right);
                deque2.addLast(node2.right);
                node1.right.val += node2.right.val;
            } else {
                TreeNode node = node1.right == null ? node2.right : node1.right;
                node1.right = node;
            }
        }
        return root1;
    }

    public static void main(String[] args) {
        TreeNode five = new TreeNode(5);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3, five, null);
        TreeNode root1 = new TreeNode(1, three, two);

        TreeNode seven2 = new TreeNode(7);
        TreeNode four2 = new TreeNode(4);
        TreeNode three2 = new TreeNode(3, null, seven2);
        TreeNode one2 = new TreeNode(1, null, four2);
        TreeNode root2 = new TreeNode(2, one2, three2);

        MergeBinaryTree mergeBinaryTree = new MergeBinaryTree();
        System.out.println(mergeBinaryTree.mergeTrees(root1, root2).val);
        DrawTreeNode.drawTree(root1);
    }
}
