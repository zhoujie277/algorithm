package com.future.leetcode.tree;

import java.util.HashMap;

/**
 * 从前序与中序遍历序列构造二叉树
 * <p>
 * 给定一棵树的前序遍历preorder 与中序遍历 inorder。请构造二叉树并返回其根节点。
 * <p>
 * 示例 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 示例 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xoei3r/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
class GenerateBinaryTree2 {
    HashMap<Integer, Integer> hashMap = new HashMap<>();
    int preIndex;

    public TreeNode generate(int start, int end, int[] preorder) {
        if (start > end) return null;
        int rootVal = preorder[preIndex++];
        Integer inorderIdx = hashMap.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        root.left = generate(start, inorderIdx - 1, preorder);
        root.right = generate(inorderIdx + 1, end, preorder);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            hashMap.put(inorder[i], i);
        }
        return generate(0, inorder.length - 1, preorder);
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        GenerateBinaryTree2 tree = new GenerateBinaryTree2();
        System.out.println(tree.buildTree(preorder, inorder));
    }
}
