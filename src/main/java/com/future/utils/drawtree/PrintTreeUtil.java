package com.future.utils.drawtree;

import com.future.datastruct.tree.BinarySearchTree;
import com.future.utils.ArrayUtils;
import com.future.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;

import java.lang.reflect.Field;

public class PrintTreeUtil {
    public static void main(String[] args) {
//        int[] array = new int[]{9, 5, 3, 7, 0, 4, 6, 8, 2, 1};
        int[] array = StdRandom.permutation(15);
        Integer[] convert = ArrayUtils.wrap(array);
        BinarySearchTree<Integer> searchTree = new BinarySearchTree<>();
        for (int j : array) {
            searchTree.add(j);
        }
//        drawTree(convert, searchTree.getDrawableTree());
        printTree(searchTree.getDrawableTree());
    }

    public static <E> void printTree(IDrawableTree<E> tree) {
        DrawTree<E> drawTree = new DrawTree<>(tree);
        drawTree.println();
        PrintUtils.println();
    }

    public static void printTree(Object tree) {
        DrawTree<Object> drawTree = new DrawTree<>(new ProxyTree(tree));
        drawTree.println();
        PrintUtils.println();
    }

    public static void printHeap(Object tree) {
        DrawTree<Integer> drawTree = new DrawTree<>(new ProxyHeap(tree));
        drawTree.println();
        PrintUtils.println();
    }

    public static void printIndexHeap(Object tree) {
        DrawTree<Integer> drawTree = new DrawTree<>(new ProxyIndexHeap(tree));
        drawTree.println();
        PrintUtils.println();
    }

    private static class ProxyTree implements IDrawableTree<Object> {
        final Object tree;

        public ProxyTree(Object instance) {
            this.tree = instance;
        }

        @Override
        public Object root() {
            try {
                Class<?> aClass = tree.getClass();
                Field root = aClass.getDeclaredField("root");
                root.setAccessible(true);
                return root.get(tree);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public Object left(Object node) {
            try {
                Field left = node.getClass().getDeclaredField("left");
                left.setAccessible(true);
                return left.get(node);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public Object right(Object node) {
            try {
                Field right = node.getClass().getDeclaredField("right");
                right.setAccessible(true);
                return right.get(node);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public boolean isRed(Object node) {
            try {
                Field color = node.getClass().getDeclaredField("color");
                color.setAccessible(true);
                Object o = color.get(node);
                byte v = (byte) o;
                return v == 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private static class ProxyHeap implements IDrawableTree<Integer> {
        final Object heap;
        protected Object[] elements;
        protected int size;

        public ProxyHeap(Object heap) {
            this.heap = heap;
            try {
                Class<?> aClass = heap.getClass();
                Field array = aClass.getDeclaredField("elements");
                array.setAccessible(true);
                this.elements = (Object[]) array.get(heap);
                Field size = aClass.getDeclaredField("size");
                size.setAccessible(true);
                this.size = (int) size.get(heap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public Integer root() {
            return 0;
        }

        @Override
        public Integer left(Integer node) {
            int index = (node << 1) + 1;
            return index < size ? index : null;
        }

        @Override
        public Integer right(Integer node) {
            int index = (node << 1) + 2;
            return index < size ? index : null;
        }

        @Override
        public String string(Integer node) {
            return elements[node].toString();
        }
    }

    private static class ProxyIndexHeap extends ProxyHeap {
        protected int[] indexes;
        public ProxyIndexHeap(Object heap) {
            super(heap);
            try {
                Class<?> aClass = heap.getClass();
                Field array = aClass.getDeclaredField("indexes");
                array.setAccessible(true);
                this.indexes = (int[]) array.get(heap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public String string(Integer node) {
            int index = indexes[node];
            return elements[index].toString();
        }
    }
}
