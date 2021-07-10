package com.future.datastruct.tree;

import com.future.datastruct.list.LinkedQueue;
import com.future.datastruct.list.LinkedStack;
import com.future.datastruct.tree.define.ITree;
import com.future.utils.drawtree.IDrawableTree;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 每个结点的度为2
 * 二叉树是有序树，区分左子树和右子树
 * 即使某个结点只有一颗子树，也要区分左右子树
 * 非空二叉树的第i层，最多有 2^(i-1) 个结点。
 * 在高度为h的二叉树上最多有 2^h - 1 个结点。
 * <p>
 * 对于任何一颗非空二叉树，如果叶子结点个数为n0，度为2的结点个数为n2，度为1的结点个数为n1
 * 则有 n0 = n2 + 1 且 n = n0 + n1 + n2;
 * 二叉树的边数等于 T = n1 + 2 * n2 = n - 1 = n0 + n1 + n2 - 1;
 * <p>
 * 真二叉树：度为0要么为2的二叉树（有两颗叉）
 * 满二叉树：度为0要么为2，且所有的叶子结点都在最后一层的二叉树
 * 满二叉树一定是真二叉树，真二叉树不一定是满二叉树。
 * <p>
 * 同样结点数量的二叉树，完全二叉树是高度最小的二叉树
 *
 * @author jayzhou
 */
@SuppressWarnings("unused")
public abstract class BinaryTree<E> implements ITree<E> {
    protected int size;

    protected Node<E> root;

    public BinaryTree() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E element) {
        Iterator<E> eIterator = breadthFirstSearchIterator();
        while (eIterator.hasNext()) {
            if (Objects.equals(element, eIterator.next())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return depth();
    }

    @Override
    public int depth() {
        return depth(root);
    }

    /**
     * 返回任意一颗子树的深度
     */
    protected int depth(Node<E> node) {
        if (node == null) return 0;
        LinkedQueue<Node<E>> queue = new LinkedQueue<>();
        queue.push(node);
        int depth = 1;
        int levelNodes = 1;
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            if (poll.left != null) {
                queue.push(poll.left);
            }
            if (poll.right != null) {
                queue.push(poll.right);
            }
            levelNodes--;
            if (levelNodes == 0) {
                depth++;
                levelNodes = queue.size();
            }
        }
        return depth;
    }

    /**
     * 判断树中某个结点是不是叶子结点
     */
    public boolean isLeafNode(Node<E> node) {
        return (node.left == null || node.left.lChildThreaded()) && (node.right == null || node.right.rChildThreaded());
    }

    public boolean leftNotNull(Node<E> node) {
        return node.left != null && !node.left.lChildThreaded();
    }

    public boolean rightNotNull(Node<E> node) {
        return node.right != null && !node.right.rChildThreaded();
    }

    /**
     * 判断树中某个结点是不是度为2的结点
     */
    public boolean isDegreeTwo(Node<E> node) {
        return node.left != null && node.right != null;
    }

    /**
     * 是不是满二叉树
     */
    public boolean isFullBinaryTree() {
        return (size & (size + 1)) == 0;
    }

    /**
     * 判断这棵树是不是完全二叉树
     */
    public boolean isCompleteBinaryTree() {
        return isCompleteBinaryTree(root);
    }

    /**
     * 判断一颗子树是不是完全二叉树
     */
    protected boolean isCompleteBinaryTree(Node<E> node) {
        LinkedQueue<Node<E>> queue = new LinkedQueue<>();
        queue.push(root);
        boolean requireLeaf = false;
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            if (requireLeaf && !isLeafNode(poll)) return false;
            if (poll.left != null) {
                queue.push(poll.left);
            } else if (poll.right != null) {
                return false;
            }
            if (poll.right != null) {
                queue.push(poll.right);
            } else {
                requireLeaf = true;
            }
        }
        return true;
    }

    protected Node<E> newNode(E e) {
        return new Node<>(e);
    }

    public void removeTree(E e) {
        innerBreadthFirstSearch(node -> {
            if (node.left != null && node.left.value.equals(e)) {
                node.left = null;
            } else if (node.right != null && node.right.value.equals(e)) {
                node.right = null;
            }
        });
    }

    public void flip() {
        Iterator<Node<E>> nodeIterator = preOrderNodeIterator();
        while (nodeIterator.hasNext()) {
            Node<E> node = nodeIterator.next();
            Node<E> left = node.left;
            node.left = node.right;
            node.right = left;
        }
    }

    @SuppressWarnings("all")
    public E[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Iterator<E> iterator = breadthFirstSearchIterator();
        while (iterator.hasNext()) {
            array[index++] = iterator.next();
        }
        return (E[]) array;
    }

    private void traversal(Node<E> node, Consumer<E> consumer) {
        if (consumer != null) {
            consumer.accept(node.value);
        }
    }

    private void traversal(Node<E> node) {
        traversal(node, null);
    }

    private void innerBreadthFirstSearch(Consumer<Node<E>> nodeConsumer) {
        if (root == null) return;
        LinkedQueue<Node<E>> queue = new LinkedQueue<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            nodeConsumer.accept(node);
            if (node.left != null && !node.lChildThreaded()) {
                queue.push(node.left);
            }
            if (node.right != null && !node.rChildThreaded()) {
                queue.push(node.right);
            }
        }
    }

    public void breadthFirstSearch(Consumer<E> consumer) {
        innerBreadthFirstSearch((node -> traversal(node, consumer)));
    }

    public Iterator<E> breadthFirstSearchIterator() {
        return new BreathFirstSearchIterator();
    }

    public void preOrder(Consumer<E> consumer) {
        preOrder(root, consumer);
    }

    protected void preOrder(Node<E> node, Consumer<E> consumer) {
        if (node == null) return;
        traversal(node, consumer);
        if (!node.lChildThreaded()) {
            preOrder(node.left, consumer);
        }
        if (!node.rChildThreaded()) {
            preOrder(node.right, consumer);
        }
    }

    public void preOrderByStack(Consumer<E> consumer) {
        LinkedStack<Node<E>> stack = new LinkedStack<>();
        stack.push(root);
        Node<E> pop;
        while ((pop = stack.pop()) != null) {
            traversal(pop, consumer);
            if (pop.right != null && !pop.rChildThreaded()) stack.push(pop.right);
            if (pop.left != null && !pop.lChildThreaded()) stack.push(pop.left);
        }
    }

    public Iterator<E> preOrderIterator() {
        return new PreOrderIterator();
    }

    protected Iterator<Node<E>> preOrderNodeIterator() {
        return new PreOrderNodeIterator();
    }

    public void inOrder(Consumer<E> consumer) {
        inOrder(root, consumer);
    }

    protected void inOrder(Node<E> node, Consumer<E> consumer) {
        if (node == null) return;
        if (!node.lChildThreaded()) {
            inOrder(node.left, consumer);
        }
        traversal(node, consumer);
        if (!node.rChildThreaded()) {
            inOrder(node.right, consumer);
        }
    }

    public void inOrderByStack(Consumer<E> consumer) {
        LinkedStack<Node<E>> stack = new LinkedStack<>();
        Node<E> currentNode = root;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                if (currentNode.lChildThreaded()) {
                    currentNode = null;
                } else {
                    currentNode = currentNode.left;
                }
            }
            currentNode = stack.pop();
            if (currentNode == null) break;
            traversal(currentNode, consumer);
            // 把右节点压栈
            if (currentNode.rChildThreaded()) {
                currentNode = null;
            } else {
                currentNode = currentNode.right;
            }
        }
    }

    public Iterator<E> inOrderIterator() {
        return new InOrderIterator();
    }

    protected Iterator<Node<E>> inOrderNodeIterator() {
        return new InOrderNodeIterator();
    }

    public void postOrder(Consumer<E> consumer) {
        postOrder(root, consumer);
    }

    protected void postOrder(Node<E> node, Consumer<E> consumer) {
        if (node == null) return;
        if (!node.lChildThreaded()) {
            postOrder(node.left, consumer);
        }
        if (!node.rChildThreaded()) {
            postOrder(node.right, consumer);
        }
        traversal(node, consumer);
    }

    public void postOrderByStack(Consumer<E> consumer) {
        LinkedStack<Node<E>> stack = new LinkedStack<>();
        Node<E> currentNode = root;
        Node<E> rightNode = null;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                if (!currentNode.lChildThreaded()) {
                    currentNode = currentNode.left;
                } else {
                    currentNode = null;
                }
            }
            currentNode = stack.peek();
            if (currentNode.rChildThreaded() || currentNode.right == null || rightNode == currentNode.right) {
                stack.pop();
                traversal(currentNode, consumer);
                // 记录上次访问过的右节点
                rightNode = currentNode;
                currentNode = null;
            } else {
                // 把右节点压栈
                if (!currentNode.rChildThreaded()) {
                    currentNode = currentNode.right;
                } else {
                    currentNode = null;
                }
            }
        }
    }

    public Iterator<E> postOrderIterator() {
        return new PostOrderIterator();
    }

    protected Iterator<Node<E>> postOrderNodeIterator() {
        return new PostOrderNodeIterator();
    }

    protected Iterator<Node<E>> breadthFirstSearchNodeIterator() {
        return new BreathFirstSearchNodeIterator();
    }

    private class PreOrderIterator implements Iterator<E> {

        private final PreOrderNodeIterator nodeIterator = new PreOrderNodeIterator();

        @Override
        public boolean hasNext() {
            return nodeIterator.hasNext();
        }

        @Override
        public E next() {
            return nodeIterator.next().value;
        }
    }

    private class InOrderIterator implements Iterator<E> {
        private final InOrderNodeIterator nodeIterator = new InOrderNodeIterator();

        @Override
        public boolean hasNext() {
            return nodeIterator.hasNext();
        }

        @Override
        public E next() {
            return nodeIterator.next().value;
        }
    }

    private class PostOrderIterator implements Iterator<E> {
        private final PostOrderNodeIterator nodeIterator = new PostOrderNodeIterator();

        @Override
        public boolean hasNext() {
            return nodeIterator.hasNext();
        }

        @Override
        public E next() {
            return nodeIterator.next().value;
        }
    }

    private class BreathFirstSearchIterator implements Iterator<E> {

        private final BreathFirstSearchNodeIterator iterator = new BreathFirstSearchNodeIterator();

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public E next() {
            return iterator.next().value;
        }
    }

    private class BreathFirstSearchNodeIterator implements Iterator<Node<E>> {
        private final LinkedQueue<Node<E>> queue = new LinkedQueue<>();

        public BreathFirstSearchNodeIterator() {
            queue.push(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public Node<E> next() {
            Node<E> node = queue.poll();
            if (leftNotNull(node)) queue.push(node.left);
            if (rightNotNull(node)) queue.push(node.right);
            return node;
        }
    }

    private class PreOrderNodeIterator implements Iterator<Node<E>> {

        private final LinkedStack<Node<E>> stack = new LinkedStack<>();

        public PreOrderNodeIterator() {
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node<E> next() {
            Node<E> pop = stack.pop();
            traversal(pop, null);
            if (rightNotNull(pop)) {
                stack.push(pop.right);
            }
            if (leftNotNull(pop)) {
                stack.push(pop.left);
            }
            return pop;
        }
    }

    private class InOrderNodeIterator implements Iterator<Node<E>> {
        private final LinkedStack<Node<E>> stack = new LinkedStack<>();

        public InOrderNodeIterator() {
            Node<E> current = root;
            while (current != null) {
                stack.push(current);
                if (!current.lChildThreaded()) {
                    current = current.left;
                } else {
                    current = null;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node<E> next() {
            Node<E> pop = stack.pop();
            traversal(pop, null);
            Node<E> current = pop.right;
            if (pop.rChildThreaded()) {
                current = null;
            }
            while (current != null) {
                stack.push(current);
                if (!current.lChildThreaded()) {
                    current = current.left;
                } else {
                    current = null;
                }
            }
            return pop;
        }
    }

    private class PostOrderNodeIterator implements Iterator<Node<E>> {
        private final LinkedStack<Node<E>> stack = new LinkedStack<>();
        private Node<E> currentNode = root;
        private Node<E> rightNode = null;

        public PostOrderNodeIterator() {
            innerNext();
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node<E> next() {
            // 记录上次访问过的右节点
            rightNode = currentNode;
            Node<E> pop = stack.pop();
            traversal(pop, null);
            currentNode = null;
            innerNext();
            return pop;
        }

        private void innerNext() {
            while (currentNode != null || !stack.isEmpty()) {
                while (currentNode != null) {
                    stack.push(currentNode);
                    if (currentNode.lChildThreaded()) {
                        currentNode = null;
                    } else {
                        currentNode = currentNode.left;
                    }
                }
                currentNode = stack.peek();
                if (currentNode.rChildThreaded() || currentNode.right == null || rightNode == currentNode.right) {
                    break;
                } else {
                    // 把右节点压栈
                    if (currentNode.rChildThreaded()) {
                        currentNode = null;
                    } else {
                        currentNode = currentNode.right;
                    }
                }
            }
        }
    }

    /**
     * 二叉树节点
     */
    protected static class Node<T> implements Cloneable {
        public static final byte FLAG_CHILD = 0;
        public static final byte FLAG_THREADED = 1;

        public T value;
        public Node<T> left;
        public Node<T> right;
        public Node<T> parent;
        public byte lChildFlag;
        public byte rChildFlag;

        public boolean lChildThreaded() {
            return lChildFlag == FLAG_THREADED;
        }

        public boolean rChildThreaded() {
            return rChildFlag == FLAG_THREADED;
        }

        public Node(T value, Node<T> left) {
            this(value, left, null);
        }

        public Node(T value, Node<T> left, Node<T> right) {
            this(value, left, right, null);
        }

        public Node(T value, Node<T> left, Node<T> right, Node<T> parent) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.lChildFlag = FLAG_CHILD;
            this.rChildFlag = FLAG_CHILD;
        }

        public Node(T value) {
            this(value, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return value.toString();
        }

        protected Node<T> instance(T value) {
            return new Node<>(value);
        }

        protected Node<T> copy() {
            Node<T> node = instance(this.value);
            node.lChildFlag = this.lChildFlag;
            node.rChildFlag = this.rChildFlag;
            if (this.left != null) {
                node.left = this.left.copy();
                node.left.parent = node;
            }
            if (this.right != null) {
                node.right = this.right.copy();
                node.right.parent = node;
            }
            return node;
        }
    }

    @SuppressWarnings("all")
    public IDrawableTree getDrawableTree(boolean copy) {
        return new DrawableTree(copy);
    }
    public IDrawableTree getDrawableTree() {
        return new DrawableTree(true);
    }


    private class DrawableTree implements IDrawableTree<Node<E>> {

        private Node<E> newRoot;

        public DrawableTree(boolean copy) {
            if (copy) {
                newRoot = root.copy();
            } else {
                newRoot = root;
            }
        }

        @Override
        public Node<E> root() {
            return newRoot;
        }

        @Override
        public Node<E> left(Node<E> node) {
            if (leftNotNull(node)) return node.left;
            return null;
        }

        @Override
        public Node<E> right(Node<E> node) {
            if (rightNotNull(node)) return node.right;
            return null;
        }

    }
}
















