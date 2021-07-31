package com.future.utils.drawtree;

import com.future.datastruct.tree.BinarySearchTree;
import com.future.utils.ArrayUtils;
import com.future.utils.PrintUtils;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.Arrays;

public class DrawTreeUtil {

    private static final Font FONT = new Font("SansSerif", Font.PLAIN, 13);
    private static final int CANVAS_SIZE = 820;

    public static void init() {
        StdDraw.setCanvasSize(CANVAS_SIZE, CANVAS_SIZE);
        StdDraw.setFont(FONT);
    }

    public static <E> void drawInitArray(E[] array) {
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        double lineY = DrawTree.initTopPoint - DrawTree.circleRadius - 0.005;
        StdDraw.line(0, lineY, 1, lineY);
        double left = 1.0 / (array.length + 1);
        double start = left;
        for (E j : array) {
            if (j == null) {
                PrintUtils.error("j is null:" + Arrays.toString(array));
                continue;
            }
            drawNode(start, DrawTree.initTopPoint, j.toString());
            start += left;
        }
    }

    @SuppressWarnings("unused")
    public static void testDrawMaxNode(int max) {
        double left = DrawTree.circleRadius;
        for (int i = 0; i < max; i++) {
            drawNode(left, i);
            left += DrawTree.circleRadius * 2 + 0.001;
        }
    }

    private static void drawNode(double x, int value) {
        drawNode(x, 0.03, value + "");
    }

    static void drawNode(double x, double y, String text) {
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.filledCircle(x, y, DrawTree.circleRadius);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(x, y - DrawTree.textOffset, text);
    }

    public static void draw(DrawNode node) {
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        if (node.parent != null) {
            StdDraw.line(node.x, node.y, node.parent.x, node.parent.y);
        }
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.filledCircle(node.x, node.y, DrawTree.circleRadius);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(node.x, node.y - DrawTree.textOffset, node.value);
    }

    public static <E> void drawTree(IDrawableTree<E> tree) {
        init();
        DrawTree<E> drawTree = new DrawTree<>(tree);
        drawTree.horizontally();
        drawTree.draw();
    }

    @SafeVarargs
    public static <E> void drawTree(E[] initArray, IDrawableTree<E>... trees) {
        DrawTreeUtil.drawInitArray(initArray);
        int len = Math.min(4, trees.length);
        if (len == 1) {
            drawTree(trees[0]);
            return;
        }
        double offsetX;
        double offsetY;
        for (int i = 0; i < len; i++) {
            if (i == 1 || i == 3) {
                offsetX = 0.5;
            } else {
                offsetX = 0.02;
            }
            if (i >= 2) {
                offsetY = -0.42;
                offsetX += 0.01; // 斜一点，避免交叉
            } else {
                offsetY = 0;
            }
            DrawTree<E> drawTree = new DrawTree<>(trees[i]);
            drawTree.setOffset(offsetX, offsetY);
            drawTree.draw();
        }
    }

    public static void main(String[] args) {
        int[] array = StdRandom.permutation(10);
        Integer[] convert = ArrayUtils.wrap(array);
        BinarySearchTree<Integer> searchTree = new BinarySearchTree<>();
        for (int j : array) {
            searchTree.add(j);
        }
        drawTree(convert, searchTree.getDrawableTree());
    }

}
