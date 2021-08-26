package com.future.leetcode.tree;

import java.util.HashMap;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * *    3
 * *   / \
 * *  9  20
 * *    /  \
 * *   15   7
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xo98qt/
 *
 * @author jayzhou
 */
public class GenerateBinaryTree {

    private final HashMap<Integer, Integer> hashMap = new HashMap<>();
    int postIndex;

    private TreeNode generate(int start, int end, int[] postorder) {
        if (start > end) return null;
        int rootVal = postorder[--postIndex];
        int rootIdx = hashMap.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        root.right = generate(rootIdx + 1, end, postorder);
        root.left = generate(start, rootIdx - 1, postorder);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length;
        for (int i = 0; i < inorder.length; i++) {
            hashMap.put(inorder[i], i);
        }
        return generate(0, inorder.length - 1, postorder);
    }

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 3, 15, 20, 7};
        GenerateBinaryTree binaryTree = new GenerateBinaryTree();
        binaryTree.buildTree(inorder, postorder);
    }
}
