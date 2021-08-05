package com.future.utils.drawtree;

import com.future.datastruct.heap.BinaryIndexHeap;
import com.future.datastruct.tree.BinarySearchTree;
import com.future.utils.ArrayUtils;
import com.future.utils.PrintUtils;
import edu.princeton.cs.algs4.StdRandom;

import java.lang.reflect.Field;
import java.util.Arrays;

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

    public static void printPriority(Object queue, String name) {
        DrawTree<Integer> drawTree = new DrawTree<>(new ProxyPriorityQueue(queue, name));
        drawTree.println();
        PrintUtils.println();
    }

    private static Object getField(Object inObject, String name) {
        Class<?> aClass = inObject.getClass();
        try {
            java.util.List<Field> array = new java.util.ArrayList<>();
            do {
                array.addAll(Arrays.asList(aClass.getDeclaredFields()));
                aClass = aClass.getSuperclass();
            } while (aClass != null);
            for (Field field : array) {
                if (field.getName().equals(name)) {
                    field.setAccessible(true);
                    return field.get(inObject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class ProxyTree implements IDrawableTree<Object> {
        final Object tree;

        public ProxyTree(Object instance) {
            this.tree = instance;
        }

        @Override
        public Object root() {
            return getField(tree, "root");
        }

        @Override
        public Object left(Object node) {
            return getField(node, "left");
        }

        @Override
        public Object right(Object node) {
            return getField(node, "right");
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
            return size > 0 ? 0 : null;
        }

        @Override
        public Integer left(Integer node) {
            if (node == null) return null;
            int index = (node << 1) + 1;
            return index < size ? index : null;
        }

        @Override
        public Integer right(Integer node) {
            if (node == null) return null;
            int index = (node << 1) + 2;
            return index < size ? index : null;
        }

        @Override
        public String string(Integer node) {
            if (node == null) return "";
            return elements[node].toString();
        }
    }

    private static class ProxyIndexHeap extends ProxyHeap {
        protected BinaryIndexHeap.ElementIndex[] indexes;

        public ProxyIndexHeap(Object heap) {
            super(heap);
            try {
                Class<?> aClass = heap.getClass();
                Field array = aClass.getDeclaredField("elementIndexes");
                array.setAccessible(true);
                this.indexes = (BinaryIndexHeap.ElementIndex[]) array.get(heap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public String string(Integer node) {
            if (node == null) return "";
            int index = indexes[node].get();
            return elements[index].toString();
        }
    }

    private static class ProxyPriorityQueue extends ProxyHeap {

        private int[] heap;

        public ProxyPriorityQueue(Object heap, String heapName) {
            super(heap);
            this.heap = (int[]) getField(heap, heapName);
        }

        @Override
        public String string(Integer node) {
            if (node == null) return "";
            int index = heap[node];
            return elements[index] == null? "null" : elements[index].toString();
        }
    }
}
