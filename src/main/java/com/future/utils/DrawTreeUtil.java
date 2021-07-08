package com.future.utils;

import com.future.datastruct.list.LinkedQueue;
import com.future.datastruct.list.LinkedStack;
import com.future.datastruct.tree.BinarySearchTree;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.function.Consumer;

public class DrawTreeUtil {

    private static final double initTopPoint = 0.96;
    private static final double circleRadius = 0.015;
    private static final double textOffset = 0.0025;
    private static final double initSpacingY = 0.08;
    private static final double SpacingX = 0.03;
    private static final double minSpacing = 0.0031;
    private static final double CENTER = 0.5;
    private static final double rootTopPoint = initTopPoint - 0.08;
    private static final Font FONT = new Font("SansSerif", Font.PLAIN, 13);

    private static final int maxLevelNode = 25;

    private class DrawNode {
        double x;
        double y;
        String value;

        DrawNode left;
        DrawNode right;
        DrawNode parent;
        int depth;
        int column;

        public DrawNode(String value) {
            this.value = value;
        }

        public void draw() {
            StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
            if (parent != null) {
                StdDraw.line(x, y, parent.x, parent.y);
            }
            drawNode(x, y, value);
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public interface IDrawableTree<E> {
        E root();

        E left(E node);

        E right(E node);
    }

    private double midNodeX = 0;
    private int size = 0;

    public DrawTreeUtil() {
        StdDraw.setCanvasSize(820, 820);
        StdDraw.setFont(FONT);
    }

    @SuppressWarnings("unused")
    public void setCanvasSize(int width, int height) {
        StdDraw.setCanvasSize(width, height);
    }

    public void drawInitArray(int[] array) {
        double recWidth = 0.9;
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        double lineY = initTopPoint - circleRadius - 0.01;
        StdDraw.line(0, lineY, 1, lineY);
        double left = 1.0 / (array.length + 1);
        double start = left;
        for (int j : array) {
            drawNode(start, initTopPoint, j);
            start += left;
        }
    }

    @SuppressWarnings("unused")
    public void testDrawMaxNode(int max) {
        double left = circleRadius;
        for (int i = 0; i < max; i++) {
            drawNode(left, 0.03, i);
            left += circleRadius * 2 + 0.001;
        }
    }

    public <E> void drawBinaryTree(IDrawableTree<E> tree) {
        DrawNode root = initDrawNodes(tree);
        getDrawLevel(root);
        getDrawPosition(root);
        adjustRootPos(root);
        drawNodes(root);
    }

    private void adjustRootPos(DrawNode root) {
        final double offsetX = CENTER - midNodeX;
        PrintUtils.println("offsetx=" + offsetX);
        drawNodes(root, (node) -> {
            PrintUtils.println("node.x=" + node.x + ", current=" + node.value);
            node.x += offsetX;
            if (node.left != null && node.right != null) {
                node.x = (node.left.x + node.right.x) / 2;
            }
            PrintUtils.println("node.x=" + node.x + ", current=" + node.value);
        });
    }

    private void drawNodes(DrawNode root) {
        drawNodes(root, node -> node.draw());
    }

    private void drawNodes(DrawNode root, Consumer<DrawNode> consumer) {
        LinkedStack<DrawNode> stack = new LinkedStack<>();
        DrawNode prevRight = null;
        DrawNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.peek();

            DrawNode right = current.right;
            if (right == null || right == prevRight) {
                stack.pop();
                consumer.accept(current);
                prevRight = current;
                current = null;
            } else {
                current = right;
            }
        }
    }

    private <E> DrawNode initDrawNodes(IDrawableTree<E> tree) {
        LinkedQueue<E> queue = new LinkedQueue<>();
        LinkedQueue<DrawNode> drawQueue = new LinkedQueue<>();
        E root = tree.root();
        queue.push(root);
        DrawNode drawRoot = new DrawNode(root.toString());
        drawQueue.push(drawRoot);
        while (!queue.isEmpty()) {
            E poll = queue.poll();
            DrawNode drawNode = drawQueue.poll();
            size++;
            E left = tree.left(poll);
            E right = tree.right(poll);
            if (left != null) {
                queue.push(left);
                drawNode.left = new DrawNode(left.toString());
                drawNode.left.parent = drawNode;
                drawQueue.push(drawNode.left);
            }
            if (right != null) {
                queue.push(right);
                drawNode.right = new DrawNode(right.toString());
                drawNode.right.parent = drawNode;
                drawQueue.push(drawNode.right);
            }
        }
        return drawRoot;
    }

    @SuppressWarnings("all")
    public void getDrawLevel(DrawNode root) {
        LinkedQueue<DrawNode> queue = new LinkedQueue<>();
        int level = 1;
        int levelNodes = 1;
        queue.push(root);
        while (!queue.isEmpty()) {
            DrawNode poll = queue.poll();
            poll.depth = level;
            if (poll.left != null) {
                queue.push(poll.left);
            }
            if (poll.right != null) {
                queue.push(poll.right);
            }
            levelNodes--;
            if (levelNodes == 0) {
                level++;
                levelNodes = queue.size();
            }
        }
    }

    private void getDrawPosition(DrawNode root) {
        LinkedStack<DrawNode> stack = new LinkedStack<>();
        int column = 0;
        DrawNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            column++;
            current.column = column;
            current.y = rootTopPoint - (current.depth - 1) * initSpacingY;
            current.x = (column - 1) * SpacingX + circleRadius;
            if (column == (size >> 1)) {
                midNodeX = current.x;
                PrintUtils.println("midNodex=" + midNodeX + ", current=" + current.value + ", column=" + column + ", size=" + size);
            }
            current = current.right;
        }
    }

    private void drawNode(double x, double y, int value) {
        drawNode(x, y, value + "");
    }

    private void drawNode(double x, double y, String text) {
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.filledCircle(x, y, circleRadius);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(x, y - textOffset, text);
    }

    public static <E> void drawTree(IDrawableTree<E> tree) {
        DrawTreeUtil util = new DrawTreeUtil();
        util.drawBinaryTree(tree);
    }

    public static void main(String[] args) {
        int[] array = StdRandom.permutation(10);
        DrawTreeUtil util = new DrawTreeUtil();
        util.drawInitArray(array);
        StdDraw.circle(0.2, 0.2, 0.03);
        BinarySearchTree<Integer> searchTree = new BinarySearchTree<>();
        for (int i = 0; i < array.length; i++) {
            searchTree.add(array[i]);
        }
        util.drawBinaryTree(searchTree.getDrawableTree());

    }
}
