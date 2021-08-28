package com.future.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 寻找重复的子树
 * <p>
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 * 示例 1：
 * *         1
 * *        / \
 * *       2   3
 * *      /   / \
 * *    4   2   4
 * *       /
 * *      4
 * *  下面是两个重复的子树：
 * *        2
 * *       /
 * *      4
 * *  和
 * *      4
 * 因此，你需要以列表的形式返回上述重复子树的根结点。
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class FindRepeatSubTree {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        serialize(root, new HashMap<>(), list);
        return list;
    }

    private String serialize(TreeNode node, HashMap<String, Integer> hashMap, List<TreeNode> list) {
        if (node == null) {
            return "#";
        }
        String key = node.val + "," + serialize(node.left, hashMap, list) + "," + serialize(node.right, hashMap, list);
        int count = hashMap.getOrDefault(key, 0) + 1;
        hashMap.put(key, count);
        if (count == 2) {
            list.add(node);
        }
        return key;
    }

    public static void main(String[] args) {
        TreeNode four3 = new TreeNode(4);
        TreeNode four2 = new TreeNode(4);
        TreeNode two2 = new TreeNode(2, four3, null);
        TreeNode four = new TreeNode(4);
        TreeNode three = new TreeNode(3, two2, four2);
        TreeNode two = new TreeNode(2, four, null);
        TreeNode node = new TreeNode(1, two, three);
        FindRepeatSubTree findRepeatSubTree = new FindRepeatSubTree();
        System.out.println(findRepeatSubTree.findDuplicateSubtrees(node));
    }
}
