package com.future.datastruct.tree;

/**
 * 平衡二叉树，又称平衡二叉搜索树
 * 因为二叉排序树存在有可能变成单链表的情况，或者类似单链表。如果根节点的值很小，那么后面的值就会形成一个类似单链表的树，其查询效率会很低
 * 因此，出现了平衡二叉树，常见的平衡二叉树实现方法有：AVL、红黑树、替罪羊树、Treap树、伸展树。
 * <p>
 * AVL树平衡标准比较严格：每个左右子树的高度差不超过1
 * 最大高度是1.44 * log(n+2) -1.328（100w个结点，AVL最大树高28）
 * 搜索、添加、删除都是O(logN)复杂度，其中添加仅需O(1)次旋转调整，删除最多需要O(logN)次旋转调整
 * <p>
 * 以下是AVL树的实现方式，它具有以下特点：
 * 它是一颗空树或者它的左右两颗子树的高度差的绝对值不超过1，并且它的左右子树也都是平衡二叉树
 * <p>
 * 自平衡的处理方法和逻辑如下：
 * 前提条件：某颗树失衡，一定是其高度发生了变化。反之，如果某颗子树高度没发生变化，那么其祖父结点都不会失衡。
 * 所以修复失衡保证两点即可：
 * 1、整棵树的左右子树的高度差小于等于1，即修复了平衡。即自身保持了平衡。
 * 2、修复平衡之后整棵树的高度没发生变化。则不会影响到祖父结点的平衡。
 * <p>
 * 一、添加元素导致的失衡处理
 * 1、如果添加元素导致子树G失衡，那么失衡前它的左子树L和其右子树R高度差一定等于1，且添加的元素一定是在高树中添加。
 * 原因是：当添加元素导致的某颗子树G失衡，一定是因为G树原来的左右子树的高度差等于1，因为以前是一颗平衡树，所以高度差<=1;
 * 而又因为添加一个元素后，高度差马上大于一，所以可证明失衡前的高度差等于1。
 * 2、左子树L、右子树R的分别左右子树的高度差一定等于0，且 H(高树)-H(矮树) = 1 因为在失衡之前G树是平衡的，又因为L、R两棵树已经有高度差了.
 * 另外，如果高树中已经有高度差了，且添加元素之后导致了失衡，那么最先发现失衡的就不应该是G树，而是高树中的根结点，也就是G的子节点。
 * 所以可证明，G树的比较高的那颗子树，其子树的高度一定相等。即L子树的左右子树高度相等。
 * 3、假设：子树L、右树R、L的左子树A，L的右子树B, H(R) = h; 由上述第2点可知：H(A) = H(B),
 * 又假设左子树L为比较高的子树，则有H(L) - H(R)) = 1; 且 H(A) = H(B) = H(R) = h;
 * 现在，为了方便，将比较高的左子树记作P，添加元素导致G点失衡，首先确定是在P中添加元素，那么就有如下两种情况：
 * <p>
 * a）往P的左子树中添加元素，即在A树中添加，G树失衡，则只需将G结点向右移动即可(右旋)。也就是G点的left指向B树，P点的right指向G点。则可完成平衡。平衡原因如下：
 * i）A子树添加元素前，H(A) = H(B) = H(R) = h; H(P) = h + 1; H(G) = h + 2;
 * ii）A子树添加元素，则有 H(A) = h + 1; H(P) = h + 2; G点由于旋转后不是P结点的父结点，所以 H(G) = Math.max(H(B),H(R)) + 1, 即H(G) = h + 1;
 * iii）可以看到完成旋转操作后，G的平衡因子 BF(G) = abs(H(B) - H(R)) = 0，且 BF(P) = abs(H(A) - H(G)) = 0; P的新高度和G点以前的高度都是h + 2；
 * 所以可证明，在这种情况下，处理了G点的失衡，既保证了原来的G树(后来的P树）平衡，又不会影响祖父结点的高度，所以可以确定修复平衡之后整棵树都是平衡的。
 * <p>
 * b）往P的右子树中添加元素，即在B树中添加，G树失衡，则先需要将P点往左移动(左旋)，再将G点往右边移动(右旋)，即可完成平衡。具体步骤即平衡原因如下：
 * i) B子树添加元素前，跟前面一样：H(A) = H(B) = H(R) = h; H(P) = h + 1; H(G) = h + 2;
 * ii）B子树添加元素后，旋转操作前： H(B) = h + 1; H(P) = h + 2; H(G)检测到不平衡，不更新高度。
 * iii）设B的左子树e、右子树f。则 H(e) = H(f) = h - 1; 则H(A) = H(e) + 1; P点左旋即：P.left = e; B.right = P; H(B) = H(P) + 1;
 * Wi) 如果是往e子树中加元素，则 H(P) = max(H(A), H(e) + 1)) + 1 = max(H(A)) = h + 1; 则 H(B) = h + 2;即取代了的P点的B树高度在这轮旋转之后还是和P点的高度一样。
 * Wii) 如果是往f树中添加元素, 则 H(f) = h; 此时不会影响 H(P)，也不会影响 H(B)。
 * Wii）无论是wi和wii哪种情况，此时取代了P点的B树就是在a情况中的P点。往左旋即可修复平衡。
 * <p>
 * 综上所述，是假定P点为左树比较高的情况，其实右树比较高的添加元素情况，也和这个是一样的，可以看出是树的翻转罢了，只有具体的旋转操作不一样，其实修复原理是一样的。
 * <p>
 * <p>
 * 二、删除元素导致失衡处理
 * 由于删除，父结点的平衡因子BF可能会发生变化，所以需要检测是否平衡。
 * 如果不平衡，调整之后的树的高度比原来的树小，所以，需要继续往祖父结点寻找是否平衡。
 * 只是在具体自身平衡修复方法上其实和添加元素同样的处理方法。
 * 以下举例：原树的高度为3，删除右边叶子结点后，打乱了平衡，修复平衡后如右图，树的高度变为了2，因此，需要继续寻找父结点是否平衡。
 * -   ⭕️            ⭕️
 * - ⭕  ⭕  -->  ️️⭕   ⭕
 * ⭕  ️
 *
 * @author jayzhou
 */
public class AVLBinaryTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    /**
     * 修复删除之后导致的失衡
     * 注意：删除结点之后，最多只会导致最近的一个祖结点失衡，
     * 但是祖结点恢复平衡之后，所在树的高度可能减小，所以需要继续检查祖父结点，直到根结点.
     * 因此删除一个元素，最多可能会倒是 O(logN) 次的调整
     */
    @Override
    protected void fixAfterDeletion(Node<E> node) {
        AVLNode<E> parent = (AVLNode<E>) node.parent;
        //祖父结点有可能失衡
        while (parent != null) {
            updateNodeHeight(parent);
            if (notBalanced(parent)) {
                rebalanced(parent);
            }
            parent = (AVLNode<E>) parent.parent;
        }
    }

    /**
     * 自平衡
     */
    private void rebalanced(AVLNode<E> g) {
        AVLNode<E> p = tallerSubTree(g);
        AVLNode<E> c = tallerSubTree(p);
        if (g.left == p) {
            if (p.right == c) {
                // LR
                leftRotate(p);
            }
            //LL
            rightRotate(g);
        } else {
            // RL
            if (p.left == c) {
                // RL
                rightRotate(p);
            }
            //RR
            leftRotate(g);
        }
    }

    /**
     * 修复插入导致的失衡
     * 本身是一颗平衡二叉树，添加之后，可能会影响到父节点或者其祖结点的失衡
     * 所以需要检查该树所在的父节点及其祖结点是不是平衡，找到一个失衡的结点即可返回
     * 修复最近的祖结点的失衡，即可修复由于该结点插入导致的失衡。
     * 只需要调整一次，所以添加元素给二叉搜索树增加了仅 O(1) 的调整
     */
    @Override
    protected void fixAfterInsertion(Node<E> node) {
        AVLNode<E> c = (AVLNode<E>) node;
        while (c != null) {
            updateNodeHeight(c);
            if (notBalanced(c)) {
                rebalanced(c);
                break;
            }
            c = (AVLNode<E>) c.parent;
        }
    }

    private void leftRotate(AVLNode<E> g) {
        AVLNode<E> p = (AVLNode<E>) g.right;
        g.right = p.left;
        if (p.left != null) {
            p.left.parent = g;
        }
        p.left = g;
        updateRotate(g, p);
    }

    private void rightRotate(AVLNode<E> g) {
        AVLNode<E> p = (AVLNode<E>) g.left;
        g.left = p.right;
        if (p.right != null) {
            p.right.parent = g;
        }
        p.right = g;
        updateRotate(g, p);
    }

    private void updateRotate(AVLNode<E> g, AVLNode<E> p) {
        if (g.parent == null) {
            root = p;
            p.parent = null;
        } else if (g.parent.left == g) {
            g.parent.left = p;
        } else if (g.parent.right == g) {
            g.parent.right = p;
        }
        p.parent = g.parent;
        g.parent = p;
        updateNodeHeight(g);
        updateNodeHeight(p);
    }

    protected AVLNode<E> tallerSubTree(AVLNode<E> node) {
        AVLNode<E> left = (AVLNode<E>) node.left;
        AVLNode<E> right = (AVLNode<E>) node.right;
        return getHeight(left) > getHeight(right) ? left : right;
    }

    protected boolean notBalanced(AVLNode<E> node) {
        return Math.abs(getHeight(node.left) - getHeight(node.right)) > 1;
    }

    private void updateNodeHeight(AVLNode<E> node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public int getHeight(Node<E> node) {
        if (node == null) return 0;
        return ((AVLNode<E>) node).height;
    }

    @Override
    protected AVLNode<E> newNode(E e) {
        return new AVLNode<>(e);
    }

    protected static class AVLNode<E> extends Node<E> {
        public int height;

        public AVLNode(E value) {
            super(value);
        }

        protected Node<E> instance(E value) {
            AVLNode<E> avlNode = new AVLNode<>(value);
            avlNode.height = height;
            return avlNode;
        }

        @Override
        public String toString() {
            return value + "(" + height + ")";
        }
    }
}
