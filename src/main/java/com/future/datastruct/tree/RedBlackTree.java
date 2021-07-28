package com.future.datastruct.tree;

import com.future.utils.drawtree.IDrawableTree;


/**
 * 先抛开红黑树的定义、性质、实现平衡的操作方法不谈，先谈下红黑树的设计目标。
 * 红黑树的设计目标：
 * 所有的红黑树上的操作只是为了实现一个特点：黑平衡。即黑色结点的完美平衡。不管是什么类型的红黑树，它的目标有且只有这么一个。
 * 值得一提的是，红黑树的黑平衡是依托于B树来实现的，B树是一棵完全平衡的树，从B树上同一高度的任意结点到叶子结点的所有路径都相等。
 * 而经典红黑树中定义的黑色结点和4阶B树中的结点是一一对应的，且对应B树的黑结点一定是二叉子树中的根结点。
 * 所以，所有对于黑色结点的添加、删除操作都对应于B树中的对结点的添加和删除操作。
 * 只要保证每个对应B树中的结点有且只有一个黑结点，那就可以从二叉树的形式上，保证黑平衡。
 * 具体一一对应的关系表现在：
 * 如果任一黑色结点没有红色子结点，则一个黑色结点对应B树中一个结点；
 * 如果黑色结点有红色子结点，那么就将黑色结点和红色子结点一起对应B树中的一个结点，由于二叉树最多有2个子结点，所以对应的一棵4阶B树。
 * 由这个目标可推出：红黑树中所有的红色结点都是因为妨碍了黑平衡，才变色处理以便可以实现黑平衡。另外，删除黑色结点的时候，也可以用红色结点来变色处理实现黑平衡。
 * 故红结点在红黑树中始终作为一种类似"备胎"的角色出现。当要删除的时候，有红色子结点，则自己内部先修复；如果没有，说明已达到B树结点下限，产生B树中的下溢。
 * 总的来说：红黑树就是在用二叉树的结构来实现4阶B树。
 * <p>
 * 红黑树的性质：
 * 1、结点是 RED 或者 BLACK
 * 2、根结点必须是 BLACK
 * 3、叶子结点（假想结点、空结点）都是 BLACK，（由于有这条性质，红黑树其实是一颗真二叉树）
 * 4、RED 结点的子结点必然是 BLACK。
 * -   可推导出 RED 的parent必然是 BLACK
 * -   从根结点到叶子结点的所有路径上不能有连续的两个 RED 结点
 * 5、从任一结点到叶子结点的所有路径都包含相同数目的 BLACK 结点数量
 * <p>
 * 红黑树与4阶B树的等价变换
 * 1、红黑树和4阶B树具有等价性
 * 2、BLACK结点与它的RED子结点融合在一起，形成一个B树结点
 * 3、红黑树的BLACK结点个数与4阶B树的结点总个数相等
 * <p>
 * parent: 父结点
 * sibling: 兄弟结点
 * uncle：叔父结点（parent的兄弟结点）
 * grand：祖父结点（parent的父结点）
 * <p>
 * 红黑树平衡标准比较宽松：没有一条路径会大于其它路径的2倍
 * 最大高度是 2 * log(n+1) (100w个结点，红黑树最大树高40）
 * 搜索添加删除都是O(logN)复杂度，其中添加、删除都仅需O(1)次旋转调整
 * <p>
 * 相对于AVL树，红黑树牺牲了部分平衡性以换取插入/删除操作时少量的旋转操作，整体来说性能优于AVL树。
 * 红黑树的平均统计性能优于AVL树，实际应用更多选择红黑树。
 */
public class RedBlackTree<K extends Comparable<K>> extends BinaryBalancedTree<K> {

    private static final byte RED = 1;
    private static final byte BLACK = 0;

    private RBNode<K> parentOf(Node<K> node) {
        return node == null ? null : (RBNode<K>) node.parent;
    }

    private byte colorOf(Node<K> node) {
        return node == null ? BLACK : ((RBNode<K>) node).color;
    }

    private RBNode<K> leftOf(Node<K> node) {
        return node == null ? null : (RBNode<K>) node.left;
    }

    private RBNode<K> rightOf(Node<K> node) {
        return node == null ? null : (RBNode<K>) node.right;
    }

    private void setColor(Node<K> node, byte color) {
        ((RBNode<K>) node).color = color;
    }

    @Override
    protected void fixAfterInsertion(Node<K> node) {
        while (node != null && node != root && colorOf(parentOf(node)) == RED) {
            if (leftOf(parentOf(parentOf(node))) == parentOf(node)) {
                RBNode<K> uncle = rightOf(parentOf(parentOf(node)));
                if (colorOf(uncle) == RED) {
                    setColor(parentOf(node), BLACK);
                    setColor(uncle, BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    node = parentOf(parentOf(node));
                } else {
                    if (rightOf(parentOf(node)) == node) {
                        node = parentOf(node);
                        leftRotate(node);
                    }
                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    rightRotate(parentOf(parentOf(node)));
                }
            } else {
                RBNode<K> uncle = leftOf(parentOf(parentOf(node)));
                if (colorOf(uncle) == RED) {
                    setColor(parentOf(node), BLACK);
                    setColor(uncle, BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    node = parentOf(parentOf(node));
                } else {
                    if (leftOf(parentOf(node)) == node) {
                        node = parentOf(node);
                        rightRotate(node);
                    }
                    setColor(parentOf(node), BLACK);
                    setColor(parentOf(parentOf(node)), RED);
                    leftRotate(parentOf(parentOf(node)));
                }
            }
        }
        setColor(root, BLACK);
    }

    @Override
    protected void fixAfterDeletion(Node<K> node) {
        if (colorOf(node) == RED) {
            setColor(node, BLACK);
            return;
        }
        //delete black leaf node
        while (node != root && colorOf(node) == BLACK) {
            // 被删除之前处于黑平衡状态，所以其父结点必定有两个子结点
            boolean leftChild = (node.parent.left == null) || leftOf(parentOf(node)) == node;
            if (leftChild) {
                Node<K> sib = rightOf(parentOf(node));
                if (colorOf(sib) == RED) {
                    // 二叉结构虽然旋转，但是B树结构始终不变，仅仅是颜色交换
                    setColor(sib, BLACK);
                    setColor(parentOf(node), RED);
                    leftRotate(parentOf(node));
                    sib = rightOf(parentOf(node));
                }
                if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    node = parentOf(node);
                } else {
                    if (colorOf(rightOf(sib)) == BLACK) {
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, RED);
                        rightRotate(sib);
                        sib = rightOf(parentOf(node));
                    }
                    setColor(sib, colorOf(parentOf(node)));
                    setColor(parentOf(node), BLACK);
                    setColor(rightOf(sib), BLACK);
                    leftRotate(parentOf(node));
                    break;
                }
            } else {
                // right node
                Node<K> sib = leftOf(parentOf(node));
                if (colorOf(sib) == RED) {
                    // 二叉结构虽然旋转，但是B树结构始终不变，仅仅是颜色交换
                    setColor(sib, BLACK);
                    setColor(parentOf(node), RED);
                    rightRotate(parentOf(node));
                    sib = leftOf(parentOf(node));
                }
                if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    node = parentOf(node);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        leftRotate(sib);
                        sib = leftOf(parentOf(node));
                    }
                    setColor(sib, colorOf(parentOf(node)));
                    setColor(parentOf(node), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rightRotate(parentOf(node));
                    break;
                }
            }
        }
        setColor(node, BLACK);
    }

    @Override
    protected Node<K> newNode(K e) {
        return new RBNode<>(e);
    }

    private static class RBNode<K> extends Node<K> {
        byte color;
//        V value;

        public RBNode(K key) {
            super(key);
            this.color = RED;
//            this.value = value;
        }
    }

    @SuppressWarnings("all")
    @Override
    public IDrawableTree getDrawableTree(boolean copy) {
        return new RBDrawableTree(copy);
    }

    private class RBDrawableTree extends DrawableTree {

        public RBDrawableTree(boolean copy) {
            super(copy);
        }

        @Override
        public boolean isRed(Node<K> node) {
            if (node instanceof RBNode) {
                return ((RBNode<K>) node).color == RED;
            }
            return false;
        }
    }

}
