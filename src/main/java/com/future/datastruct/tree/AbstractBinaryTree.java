package com.future.datastruct.tree;

import com.future.datastruct.list.LinkedQueue;
import com.future.datastruct.list.LinkedStack;
import com.future.datastruct.tree.define.Node;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 对于任何一颗非空二叉树，如果叶子结点个数为n0，度为2的结点个数为n2，度为1的结点个数为n1
 * 则有 n0 = n2 + 1 且 n = n0 + n1 + n2;
 * 二叉树的边数等于 T = n1 + 2 * n2 = n - 1 = n0 + n1 + n2 - 1;
 * 非二叉树的第i层，最多有 2^(i-1) 个结点
 * 在高度为h的二叉树上最多有 2^h - 1 个结点
 * 真二叉树：度为0要么为2的二叉树
 * 满二叉树：度为0要么为2，且所有的叶子结点都在最后一层的二叉树
 * 满二叉树一定是真二叉树，真二叉树不一定是满二叉树。
 * 完全二叉树：叶子结点只会出现在最后两层，而且最后一层叶子结点是靠左对齐。
 * 故  完全二叉树度为1的结点最多只有一个，
 *    且总结点数量为：2^(h-1) <= n < 2^h - 1; 可得出： h - 1 <= logN < h, h = floor(logN) + 1
 * @param <E>
 */
@SuppressWarnings("unused")
public abstract class AbstractBinaryTree<E> {
    protected int size;

    protected Node<E> root;

    public AbstractBinaryTree() {
    }

    public Node<E> getRoot(){return root;}

    abstract public E add(E e);

    public int size() {
        return size;
    }

    protected Node<E> newNode(E e) {
        return new Node<>(e);
    }

    public void removeTree(E e) {
        innerBreadthFirstSearch(node -> {
            if (node.left != null && node.left.equals(e)) {
                node.left = null;
            } else if (node.right != null || node.right.equals(e)) {
                node.right = null;
            }
        });
    }

    public int maxDepth(Node<E> node) {
        if (node == null) return 0;
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public int minDepth(Node<E> node) {
        if (node == null) return 0;
        int leftDepth = minDepth(node.left);
        int rightDepth = minDepth(node.right);
        return Math.min(leftDepth, rightDepth) + 1;
    }

    private boolean preOrderTraversal(Node<E> node, Consumer<E> consumer) {
        onPreOrderTraversal(node);
        traversal(node, consumer);
        return true;
    }

    protected void onPreOrderTraversal(Node<E> node) {

    }

    private void inOrderTraversal(Node<E> node, Consumer<E> consumer) {
        onInOrderTraversal(node);
        traversal(node, consumer);
    }

    protected void onInOrderTraversal(Node<E> node) {

    }

    private void postOrderTraversal(Node<E> node, Consumer<E> consumer) {
        onPostOrderTraversal(node);
        traversal(node, consumer);
    }

    protected void onPostOrderTraversal(Node<E> node) {

    }

    private void traversal(Node<E> node, Consumer<E> consumer) {
        onTraversal(node);
        if (consumer != null) {
            consumer.accept(node.value);
        }
    }

    private void traversal(Node<E> node) {
        traversal(node, null);
    }

    protected void onTraversal(Node<E> node) {
    }

    private void innerBreadthFirstSearch(Consumer<Node<E>> nodeConsumer) {
        if (root == null) return;
        LinkedQueue<Node<E>> queue = new LinkedQueue();
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

    public void preOrder(Node<E> node, Consumer<E> consumer) {
        if (node == null) return;
        preOrderTraversal(node, consumer);
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
            preOrderTraversal(pop, consumer);
            if (pop.right != null && !pop.rChildThreaded()) stack.push(pop.right);
            if (pop.left != null && !pop.lChildThreaded()) stack.push(pop.left);
        }
    }

    public Iterator<E> preOrderIterator() {
        return new PreOrderIterator();
    }

    public void inOrder(Consumer<E> consumer) {
        inOrder(root, consumer);
    }

    public void inOrder(Node<E> node, Consumer<E> consumer) {
        if (node == null) return;
        if (!node.lChildThreaded()) {
            inOrder(node.left, consumer);
        }
        inOrderTraversal(node, consumer);
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
            inOrderTraversal(currentNode, consumer);
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

    public void postOrder(Consumer<E> consumer) {
        postOrder(root, consumer);
    }

    public void postOrder(Node<E> node, Consumer<E> consumer) {
        if (node == null) return;
        if (!node.lChildThreaded()) {
            postOrder(node.left, consumer);
        }
        if (!node.rChildThreaded()) {
            postOrder(node.right, consumer);
        }
        postOrderTraversal(node, consumer);
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
                postOrderTraversal(currentNode, consumer);
                // 记录上次访问过的右节点
                rightNode = currentNode;
                stack.pop();
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

    private class PreOrderIterator implements Iterator<E> {
        private final LinkedStack<Node<E>> stack = new LinkedStack<>();

        public PreOrderIterator() {
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            Node<E> pop = stack.pop();
            preOrderTraversal(pop, null);
            E oldVal = pop.value;
            if (pop.right != null && !pop.rChildThreaded()) {
                stack.push(pop.right);
            }
            if (pop.left != null && !pop.lChildThreaded()) {
                stack.push(pop.left);
            }
            return oldVal;
        }
    }

    private class InOrderIterator implements Iterator<E> {
        private final LinkedStack<Node<E>> stack = new LinkedStack<>();

        public InOrderIterator() {
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
        public E next() {
            Node<E> pop = stack.pop();
            inOrderTraversal(pop, null);
            E oldVal = pop.value;
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
            return oldVal;
        }
    }

    private class PostOrderIterator implements Iterator<E> {
        private LinkedStack<Node<E>> stack = new LinkedStack<>();
        private Node<E> currentNode = root;
        private Node<E> rightNode = null;

        public PostOrderIterator() {
            innerNext();
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            // 记录上次访问过的右节点
            rightNode = currentNode;
            Node<E> pop = stack.pop();
            postOrderTraversal(pop, null);
            E oldVal = pop.value;
            currentNode = null;
            innerNext();
            return oldVal;
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

    private class BreathFirstSearchIterator implements Iterator<E> {
        private final LinkedQueue<Node<E>> queue = new LinkedQueue();

        public BreathFirstSearchIterator() {
            queue.push(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public E next() {
            Node<E> node = queue.poll();
            traversal(node);
            if (node.left != null && !node.lChildThreaded()) queue.push(node.left);
            if (node.right != null && !node.rChildThreaded()) queue.push(node.right);
            return node.value;
        }
    }
}
















