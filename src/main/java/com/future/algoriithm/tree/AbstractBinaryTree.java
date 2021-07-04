package com.future.algoriithm.tree;

import com.future.algoriithm.node.BinaryNode;
import com.future.algoriithm.queue.Queue;
import com.future.algoriithm.stack.Stack;

import java.util.Iterator;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public abstract class AbstractBinaryTree<E> {
    protected int size;

    protected BinaryNode<E> root;

    public AbstractBinaryTree() {
    }

    abstract public E add(E e);

    public int size() {
        return size;
    }

    protected BinaryNode<E> newNode(E e) {
        return new BinaryNode<>(e);
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

    public int maxDepth(BinaryNode<E> node) {
        if (node == null) return 0;
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public int minDepth(BinaryNode<E> node) {
        if (node == null) return 0;
        int leftDepth = minDepth(node.left);
        int rightDepth = minDepth(node.right);
        return Math.min(leftDepth, rightDepth) + 1;
    }

    private boolean preOrderTraversal(BinaryNode<E> node, Consumer<E> consumer) {
        onPreOrderTraversal(node);
        traversal(node, consumer);
        return true;
    }

    protected void onPreOrderTraversal(BinaryNode<E> node) {

    }

    private void inOrderTraversal(BinaryNode<E> node, Consumer<E> consumer) {
        onInOrderTraversal(node);
        traversal(node, consumer);
    }

    protected void onInOrderTraversal(BinaryNode<E> node) {

    }

    private void postOrderTraversal(BinaryNode<E> node, Consumer<E> consumer) {
        onPostOrderTraversal(node);
        traversal(node, consumer);
    }

    protected void onPostOrderTraversal(BinaryNode<E> node) {

    }

    private void traversal(BinaryNode<E> node, Consumer<E> consumer) {
        onTraversal(node);
        if (consumer != null) {
            consumer.accept(node.value);
        }
    }

    private void traversal(BinaryNode<E> node) {
        traversal(node, null);
    }

    protected void onTraversal(BinaryNode<E> node) {
    }

    private void innerBreadthFirstSearch(Consumer<BinaryNode<E>> nodeConsumer) {
        Queue<BinaryNode<E>> queue = new Queue();
        queue.push(root);
        while (!queue.isEmpty()) {
            BinaryNode<E> node = queue.poll();
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

    public void preOrder(BinaryNode<E> node, Consumer<E> consumer) {
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
        Stack<BinaryNode<E>> stack = new Stack<>();
        stack.push(root);
        BinaryNode<E> pop;
        while ((pop = stack.pop()) != null) {
            preOrderTraversal(pop, consumer);
            if (pop.right != null&& !pop.rChildThreaded()) stack.push(pop.right);
            if (pop.left != null && !pop.lChildThreaded()) stack.push(pop.left);
        }
    }

    public Iterator<E> preOrderIterator() {
        return new PreOrderIterator();
    }

    public void inOrder(Consumer<E> consumer) {
        inOrder(root, consumer);
    }

    public void inOrder(BinaryNode<E> node, Consumer<E> consumer) {
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
        Stack<BinaryNode<E>> stack = new Stack<>();
        BinaryNode<E> currentNode = root;
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

    public void postOrder(BinaryNode<E> node, Consumer<E> consumer) {
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
        Stack<BinaryNode<E>> stack = new Stack<>();
        BinaryNode<E> currentNode = root;
        BinaryNode<E> rightNode = null;
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
        private final Stack<BinaryNode<E>> stack = new Stack<>();

        public PreOrderIterator() {
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            BinaryNode<E> pop = stack.pop();
            preOrderTraversal(pop, null);
            E oldVal = pop.value;
            if(pop.right != null && !pop.rChildThreaded()) {
                stack.push(pop.right);
            }
            if (pop.left != null && !pop.lChildThreaded()) {
                stack.push(pop.left);
            }
            return oldVal;
        }
    }

    private class InOrderIterator implements Iterator<E> {
        private final Stack<BinaryNode<E>> stack = new Stack<>();

        public InOrderIterator() {
            BinaryNode<E> current = root;
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
            BinaryNode<E> pop = stack.pop();
            inOrderTraversal(pop, null);
            E oldVal = pop.value;
            BinaryNode<E> current = pop.right;
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
        private Stack<BinaryNode<E>> stack = new Stack<>();
        private BinaryNode<E> currentNode = root;
        private BinaryNode<E> rightNode = null;

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
            BinaryNode<E> pop = stack.pop();
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
        private final Queue<BinaryNode<E>> queue = new Queue();

        public BreathFirstSearchIterator() {
            queue.push(root);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public E next() {
            BinaryNode<E> node = queue.poll();
            traversal(node);
            if (node.left != null && !node.lChildThreaded()) queue.push(node.left);
            if (node.right != null && !node.rChildThreaded()) queue.push(node.right);
            return node.value;
        }
    }
}
















