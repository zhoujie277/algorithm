package com.future.leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 对称二叉树
 * <p>
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树[1,2,2,3,4,4,3] 是对称的。
 * *     1
 * *    / \
 * *   2   2
 * *  / \ / \
 * * 3  4 4  3
 * <p>
 * * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 * *
 * *     1
 * *    / \
 * *   2   2
 * *    \   \
 * *    3    3
 * 进阶：
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xoxzgv/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class BinarySymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return true;
        if (root.left == null || root.right == null) return false;
        Deque<TreeNode> leftDeque = new LinkedList<>();
        Deque<TreeNode> rightDeque = new LinkedList<>();
        leftDeque.addLast(root.left);
        rightDeque.addLast(root.right);
        while (!leftDeque.isEmpty()) {
            int size = leftDeque.size();
            for (int i = 0; i < size; i++) {
                TreeNode l = leftDeque.pollFirst();
                TreeNode r = rightDeque.pollFirst();
                if (l == null && r == null) continue;
                if (l == null || r == null) return false;
                if (l.val != r.val) return false;
                leftDeque.addLast(l.left);
                rightDeque.addLast(r.right);

                leftDeque.addLast(l.right);
                rightDeque.addLast(r.left);
            }
        }
        return true;
    }

    public boolean isSymmetricRec(TreeNode root) {
        return check(root.left, root.right);
    }

    public boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return check(left.left, right.right) && check(left.right, right.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode l1 = new TreeNode(2);
        TreeNode r1 = new TreeNode(2);
        TreeNode l2 = new TreeNode(3);
        TreeNode l3 = new TreeNode(4);
        TreeNode r2 = new TreeNode(4);
        TreeNode r3 = new TreeNode(3);

        root.left = l1;
        root.right = r1;
        l1.left = l2;
        l1.right = l3;
        r1.left = r2;
        r1.right = r3;

        BinarySymmetricTree symmetricTree = new BinarySymmetricTree();
        System.out.println(symmetricTree.isSymmetric(root));

    }
}
