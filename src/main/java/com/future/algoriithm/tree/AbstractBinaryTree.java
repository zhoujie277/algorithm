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

    public void breadthFirstSearch(Consumer<E> consumer) {
        Queue<BinaryNode<E>> queue = new Queue();
        queue.push(root);
        while (!queue.isEmpty()) {
            BinaryNode<E> node = queue.poll();
            if (node.left != null) {
                queue.push(node.left);
            }
            if (node.right != null) {
                queue.push(node.right);
            }
            consumer.accept(node.value);
        }
    }

    public Iterator<E> breadthFirstSearchIterator() {
        return new BreathFirstSearchIterator();
    }

    public void preOrder(Consumer<E> consumer) {
        preOrder(root, consumer);
    }

    public void preOrder(BinaryNode<E> node, Consumer<E> consumer) {
        if (node == null) return;
        consumer.accept(node.value);
        preOrder(node.left, consumer);
        preOrder(node.right, consumer);
    }

    public void preOrderByStack(Consumer<E> consumer) {
        Stack<BinaryNode<E>> stack = new Stack<>();
        stack.push(root);
        BinaryNode<E> pop;
        while ((pop = stack.pop()) != null) {
            consumer.accept(pop.value);
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
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
        inOrder(node.left, consumer);
        consumer.accept(node.value);
        inOrder(node.right, consumer);
    }

    public void inOrderByStack(Consumer<E> consumer) {
        Stack<BinaryNode<E>> stack = new Stack<>();
        BinaryNode<E> currentNode = root;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            consumer.accept(currentNode.value);
            // 把右节点压栈
            currentNode = currentNode.right;
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
        postOrder(node.left, consumer);
        postOrder(node.right, consumer);
        consumer.accept(node.value);
    }

    public void postOrderByStack(Consumer<E> consumer) {
        Stack<BinaryNode<E>> stack = new Stack<>();
        BinaryNode<E> currentNode = root;
        BinaryNode<E> rightNode = null;
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.peek();
            if (currentNode.right == null || rightNode == currentNode.right) {
                consumer.accept(currentNode.value);
                // 记录上次访问过的右节点
                rightNode = currentNode;
                stack.pop();
                currentNode = null;
            } else {
                // 把右节点压栈
                currentNode = currentNode.right;
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
            E oldVal = pop.value;
            stack.push(pop.right);
            stack.push(pop.left);
            return oldVal;
        }
    }

    private class InOrderIterator implements Iterator<E> {
        private final Stack<BinaryNode<E>> stack = new Stack<>();

        public InOrderIterator() {
            BinaryNode<E> current = root;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            BinaryNode<E> pop = stack.pop();
            E oldVal = pop.value;
            BinaryNode<E> current = pop.right;
            while (current != null) {
                stack.push(current);
                current = current.left;
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
            E oldVal = pop.value;
            currentNode = null;
            innerNext();
            return oldVal;
        }

        private void innerNext() {
            while (currentNode != null || !stack.isEmpty()) {
                while (currentNode != null) {
                    stack.push(currentNode);
                    currentNode = currentNode.left;
                }
                currentNode = stack.peek();
                if (currentNode.right == null || rightNode == currentNode.right) {
                    break;
                } else {
                    // 把右节点压栈
                    currentNode = currentNode.right;
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
            if (node.left != null) queue.push(node.left);
            if (node.right != null) queue.push(node.right);
            return node.value;
        }
    }
}
















