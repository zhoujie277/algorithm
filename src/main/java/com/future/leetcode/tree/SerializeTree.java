package com.future.leetcode.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的序列化和反序列化
 * <p>
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题
 * <p>
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xomr73/
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class SerializeTree {

    private String EMPTY = "#";
    private String SPLIT = ",";


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        serialize(root, builder);
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public void serialize(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append(EMPTY);
            builder.append(SPLIT);
            return;
        }
        builder.append(root.val);
        builder.append(SPLIT);
        serialize(root.left, builder);
        serialize(root.right, builder);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(SPLIT);
        List<String> list = new LinkedList<>(Arrays.asList(split));
        return deserialize(list);
    }

    private TreeNode deserialize(List<String> list) {
        if (EMPTY.equals(list.get(0))) {
            list.remove(0);
            return null;
        }
        String remove = list.remove(0);
        TreeNode root = new TreeNode(Integer.valueOf(remove));
        root.left = deserialize(list);
        root.right = deserialize(list);
        return root;
    }

    public static void main(String[] args) {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        one.left = two;
        one.right = three;
        SerializeTree tree = new SerializeTree();
        String serialize = tree.serialize(one);
        System.out.println("serialize=" + serialize);
        TreeNode node = tree.deserialize(serialize);
        System.out.println(node.val);
        System.out.println(node.left.val);
    }
}
