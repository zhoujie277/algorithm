package com.future.utils;

import com.future.datastruct.list.DynamicArray;
import com.future.datastruct.tree.define.Node;
import com.future.datastruct.list.LinkedQueue;
import com.future.datastruct.tree.AbstractBinaryTree;
import com.future.datastruct.tree.BinarySearchTree;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.Iterator;

public class DrawTreeUtil {

    private double initTopPoint = 0.96;
    private double initWidthPoint = 0.5;
    private double circleRadius = 0.025;
    private double textOffset = 0.005;
    private double initSpacingX = 0.2;
    private double initSpacingY = 0.08;
    private double minSpacing = 0.04;
    private double rootTopPoint = initTopPoint - 0.08;
    private double rootStartPoint = 0.5;

    private class DrawNode {
        double x;
        double y;
        String value;
        double spacing;
        double parentPositionX = 0d;
        double parentPositionY = 0d;

        public DrawNode(double x, double y, double spacing, String value) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.spacing = spacing;
        }

        public void draw() {
            StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
            if (parentPositionX > 0) {
                StdDraw.line(x, y, parentPositionX, parentPositionY);
            }
            drawNode(x, y, value);
        }
    }

    private DrawNode newDrawNode(double x, double y, double spacing, String value) {
        return new DrawNode(x, y, spacing, value);
    }

    public void drawInitArray(int[] array) {
        double recWidth = 0.9;
        double recHeight = 0.08;
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.rectangle(initWidthPoint, initTopPoint, recWidth * 0.5, recHeight * 0.5);
        double left = recWidth / array.length;
        double start = left;
        for (int i = 0; i < array.length; i++) {
            drawNode(start, initTopPoint, array[i]);
            start += left;
        }
    }

    public <T> void drawBinaryTree(AbstractBinaryTree<T> tree, Node<T> root) {
        DynamicArray<DrawNode> drawNodes = new DynamicArray<>();
        LinkedQueue<Node<T>> queue = new LinkedQueue<>();
        int index = 0;
        queue.push(root);
        drawNodes.add(newDrawNode(rootStartPoint, rootTopPoint, initSpacingX, root.value.toString()));
        while (!queue.isEmpty()) {
            Node<T> poll = queue.poll();
            DrawNode drawNode = drawNodes.get(index);
            double spacingX = drawNode.spacing - 0.04;
            if (spacingX < minSpacing) {
                spacingX = minSpacing;
            }
            System.out.println(spacingX);
            if (poll.left != null && !poll.lChildThreaded()) {
                queue.push(poll.left);
                DrawNode childNode = newDrawNode(drawNode.x - spacingX, drawNode.y - initSpacingY, spacingX, poll.left.value.toString());
                childNode.parentPositionX = drawNode.x;
                childNode.parentPositionY = drawNode.y;
                drawNodes.add(childNode);
            }
            if (poll.right != null && !poll.rChildThreaded()) {
                queue.push(poll.right);
                DrawNode childNode = newDrawNode(drawNode.x + spacingX, drawNode.y - initSpacingY, spacingX, poll.right.value.toString());
                childNode.parentPositionX = drawNode.x;
                childNode.parentPositionY = drawNode.y;
                drawNodes.add(childNode);
            }
            index++;
        }
        drawNodes(drawNodes);
    }

    private void drawNodes(DynamicArray<DrawNode> nodes) {
        Iterator<DrawNode> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            DrawNode next = iterator.next();
            next.draw();
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

    public static void main(String[] args) {
        int[] array = StdRandom.permutation(10);
        DrawTreeUtil util = new DrawTreeUtil();
        util.drawInitArray(array);
        StdDraw.circle(0.2, 0.2, 0.03);
        BinarySearchTree<Integer> searchTree = new BinarySearchTree();
        ;
        for (int i = 0; i < array.length; i++) {
            searchTree.add(array[i]);
        }
        util.drawBinaryTree(searchTree, searchTree.getRoot());
    }
}
