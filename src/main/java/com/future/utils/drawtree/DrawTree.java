package com.future.utils.drawtree;

import com.future.datastruct.list.LinkedQueue;
import com.future.datastruct.list.LinkedStack;
import com.future.utils.PrintUtils;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

class DrawTree<E> {
    static final double initTopPoint = 0.98;
    static final double circleRadius = 0.015;
    static final double textOffset = 0.0025;
    static final double initSpacingY = 0.08;
    static final double SpacingX = 0.03;
    @SuppressWarnings("unused")
    private static final double minSpacing = 0.0031;
    static final double CENTER = 0.5;
    static final double rootTopPoint = initTopPoint - 0.045;

    private double offsetX = 0;
    private double offsetY = 0;
    private int size = 0;
    private double midNodeX = 0;

    private final DrawNode root;

    public DrawTree(IDrawableTree<E> tree) {
        this.root = initDrawNodes(tree);
        getDrawLevel(root);
        getDrawPosition(root);
    }

    public void setOffset(double offsetX, double offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public void horizontally() {
        offsetX = CENTER - midNodeX;
    }


    private int oldLevel = 0;
    int linePosition = 0;

    public void println() {
        drawNodes(root, node -> {
            if (node.left != null && node.right != null) {
                node.consoleColumn = (node.left.consoleColumn + node.right.consoleColumn) >>> 1;
            }

//            PrintUtils.println("node.value=" + node.value + ", node.column=" +node.consoleColumn);
        });
        final LinkedQueue<DrawNode> queue = new LinkedQueue<>();
        breadthDrawTree((node, level) -> {
            if (level > oldLevel) {
                oldLevel = level;
                linePosition = 0;
                PrintUtils.println();
                while (!queue.isEmpty()) {
                    linePosition = queue.poll().printLine(linePosition);
                }
                linePosition = 0;
                PrintUtils.println();
            } else {
//                PrintUtils.print("--------");
            }
            linePosition = node.print(linePosition);
            queue.push(node);
//            PrintUtils.print("(" + linePosition + ")");
        });
    }

    public void draw() {
        adjustRootPos(root);
        drawNodes(root);
    }

    private void adjustRootPos(DrawNode root) {
        drawNodes(root, (node) -> {
            node.x += offsetX;
            node.y += offsetY;
            if (node.left != null && node.right != null) {
                node.x = (node.left.x + node.right.x) / 2;
            }
        });
    }

    private void drawNodes(DrawNode root) {
        drawNodes(root, DrawTreeUtil::draw);
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

    private DrawNode initDrawNodes(IDrawableTree<E> tree) {
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
            current.consoleColumn = column;
            current.y = rootTopPoint - (current.depth - 1) * initSpacingY;
            current.x = (column - 1) * SpacingX + circleRadius;
            if (column == (size >> 1)) {
                midNodeX = current.x;
            }
            current = current.right;
        }
    }

    private void breadthDrawTree(BiConsumer<DrawNode, Integer> consumer) {
        LinkedQueue<DrawNode> queue = new LinkedQueue<>();
        int level = 1;
        int levelNodes = 1;
        queue.push(root);
        while (!queue.isEmpty()) {
            DrawNode poll = queue.poll();
            consumer.accept(poll, level);
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
}
