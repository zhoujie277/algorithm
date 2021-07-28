package com.future.datastruct.map;

import com.future.datastruct.list.LinkedStack;

import java.util.Objects;

/**
 * 红黑树实现的Map映射数据结构
 *
 * @param <K> 必须具备可比较性
 * @author jayzhou
 */
public class RBTreeMap<K extends Comparable<K>, V> implements IMap<K, V> {

    private static final byte RED = 0;
    private static final byte BLACK = 1;

    private Node root = null;

    private int size = 0;

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V v) {
        if (v == null) return false;
        LinkedStack<Node> stack = new LinkedStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            if (Objects.equals(v, pop.value)) return true;
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
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
    public V put(K key, V value) {
        if (root == null) {
            root = newNode(key, value, null);
            setColor(root, BLACK);
            size++;
            return null;
        }
        Node p = root;
        Node parent;
        int dir;
        do {
            if ((dir = key.compareTo(p.key)) == 0) {
                V oldVal = p.value;
                p.value = value;
                return oldVal;
            }
            parent = p;
        } while ((p = dir > 0 ? p.right : p.left) != null);
        Node eNode = newNode(key, value, parent);
        if (dir > 0) {
            parent.right = eNode;
        } else {
            parent.left = eNode;
        }
        fixAfterInsertion(eNode);
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        if (key == null || root == null) return null;
        Node node = node(key);
        return node == null ? null : node.value;
    }

    @Override
    public V remove(K key) {
        if (key == null || root == null) return null;
        Node p = node(key);
        if (p == null) return null;
        V removeVal = p.value;
        if (p.left != null && p.right != null) {
            Node s = successor(p);
            p.key = s.key;
            p.value = s.value;
            p = s;
        }
        Node replacement = p.left != null ? p.left : p.right;
        if (replacement != null) {
            if (p.parent == null) {
                root = replacement;
            } else if (p.parent.left == p) {
                p.parent.left = replacement;
            } else {
                p.parent.right = replacement;
            }
            replacement.parent = p.parent;
            p.left = p.right = p.parent = null;
            if (p.color == BLACK)
                fixAfterDeletion(replacement);
        } else if (p.parent == null) {
            root = null;
        } else {
            if (p.color == BLACK)
                fixAfterDeletion(p);
            if (p.parent.left == p) {
                p.parent.left = null;
            } else {
                p.parent.right = null;
            }
            p.parent = null;
        }
        size--;
        return removeVal;
    }

    @Override
    public IMapIterator<K, V> iterator() {
        return new InOrderIterator();
    }

    private Node node(K key) {
        Node p = root;
        int dir;
        while (p != null) {
            if ((dir = key.compareTo(p.key)) == 0)
                return p;
            p = (dir > 0) ? p.right : p.left;
        }
        return null;
    }

    private Node successor(Node node) {
        if (node == null) return null;
        Node p;
        if (node.right != null) {
            p = node.right;
            while (p.left != null)
                p = p.left;
        } else {
            p = node.parent;
            Node ch = node;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
        }
        return p;
    }

    private Node newNode(K key, V value, Node parent) {
        return new Node(key, value, parent);
    }

    private void fixAfterInsertion(Node x) {
        while (x != null && x != root && colorOf(parentOf(x)) == RED) {
            if (leftOf(parentOf(parentOf(x))) == parentOf(x)) {
                Node u = rightOf(parentOf(parentOf(x)));
                if (colorOf(u) == RED) {
                    setColor(u, BLACK);
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (rightOf(parentOf(x)) == x) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {
                Node u = leftOf(parentOf(parentOf(x)));
                if (colorOf(u) == RED) {
                    setColor(u, BLACK);
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (leftOf(parentOf(x)) == x) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        setColor(root, BLACK);
    }

    private void fixAfterDeletion(Node x) {
        while (x != root && colorOf(x) == BLACK) {
            if (leftOf(parentOf(x)) == x) {
                Node sib = rightOf(parentOf(x));
                if (colorOf(sib) == RED) {
                    setColor(parentOf(x), RED);
                    setColor(sib, BLACK);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                }
                if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(rightOf(sib)) == BLACK) {
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(rightOf(sib), BLACK);
                    setColor(parentOf(x), BLACK);
                    rotateLeft(parentOf(x));
                    x = root;
                }
            } else {
                Node sib = leftOf(parentOf(x));
                if (colorOf(sib) == RED) {
                    setColor(parentOf(x), RED);
                    setColor(sib, BLACK);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                }
                if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib, RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(leftOf(sib), BLACK);
                    setColor(parentOf(x), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }
        setColor(x, BLACK);
    }

    private void rotateLeft(Node p) {
        Node r = p.right;
        p.right = r.left;
        if (r.left != null)
            r.left.parent = p;
        if (p.parent == null) {
            root = r;
        } else if (p.parent.left == p) {
            p.parent.left = r;
        } else {
            p.parent.right = r;
        }
        r.parent = p.parent;
        r.left = p;
        p.parent = r;
    }

    private void rotateRight(Node p) {
        Node l = p.left;
        p.left = l.right;
        if (l.right != null)
            l.right.parent = p;
        if (p.parent == null) {
            root = l;
        } else if (p.parent.right == p) {
            p.parent.right = l;
        } else {
            p.parent.left = l;
        }
        l.parent = p.parent;
        l.right = p;
        p.parent = l;
    }

    private Node parentOf(Node node) {
        return node == null ? null : node.parent;
    }

    private Node leftOf(Node node) {
        return node == null ? null : node.left;
    }

    private Node rightOf(Node node) {
        return node == null ? null : node.right;
    }

    private byte colorOf(Node node) {
        return node == null ? BLACK : node.color;
    }

    private void setColor(Node node, byte color) {
        node.color = color;
    }

    private class Node {
        byte color;
        Node left;
        Node right;
        Node parent;
        K key;
        V value;

        public Node(K key, V value, Node parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            color = RED;
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }

    private class InOrderIterator implements IMapIterator<K, V> {

        LinkedStack<Node> stack = new LinkedStack<>();
        private Node current;

        InOrderIterator() {
            pushLeft(root);
        }

        private void pushLeft(Node p) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            current = stack.pop();
            pushLeft(current.right);
            return current.key;
        }

        @Override
        public V value() {
            return current.value;
        }
    }
}
