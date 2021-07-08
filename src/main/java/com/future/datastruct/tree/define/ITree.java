package com.future.datastruct.tree.define;

/**
 * 一棵树可以没有任何结点，称为空树
 * 结点的度：子树的个数
 * 叶子结点：度为0的结点
 * 结点的深度：从根结点到当前结点的唯一路径上的结点个数
 * 结点的高度：从当前结点到最远叶子结点的路径上的结点个数
 * 树的深度：所有结点深度的最大值
 * 树的高度：所有结点高度的最大值
 * 有序树：树中任意结点的子结点之间有顺序关系
 * 无序树：树中任意结点的子结点之间没有顺序关系
 * 森林：由m颗互不相交的树组成的集合。
 */
public interface ITree<E> {
    /**
     * 返回树的结点个数
     * @return 个数
     */
    int size();

    /**
     * 该树中是否存在某个元素
     * @return true存在
     */
    boolean contains(E element);

    /**
     * 这棵树是不是空树
     * @return true是 或 false不是
     */
    boolean isEmpty();

    /**
     * 清空该树
     */
    void clear();

    /**
     * 返回该树的高度
     * @return 树的高度
     */
    int height();

    /**
     * 返回该树的深度
     * @return 树的深度
     */
    int depth();

    /**
     * 向树中添加元素
     * @return true添加成功，false添加失败
     */
    E add(E element);

    /**
     * 移除树中元素
     * @return null删除失败，否则返回原来的值
     */
    E remove(E element);

}
