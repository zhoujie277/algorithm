package com.future.leetcode.tree;

/**
 * 验证BST树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/introduction-to-data-structure-binary-search-tree/xpkc6i/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class ValidBST {

    private Integer lastVal = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (lastVal != null && lastVal >= root.val) return false;
        lastVal = root.val;
        return isValidBST(root.right);
    }
}
