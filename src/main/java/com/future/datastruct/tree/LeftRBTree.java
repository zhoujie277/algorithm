package com.future.datastruct.tree;

/**
 * 2-3左倾红黑树
 *
 * @author jayzhou
 */
public class LeftRBTree<K extends Comparable<K>, V> {
    private static final byte RED = 0;
    private static final byte BLACK = 1;


    private Node<K, V> root;
    private int size;

    public LeftRBTree() {
    }

    public boolean containsKey(K key) {
        return node(key) != null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public V put(K key, V value) {
        if (key == null) throw new NullPointerException();
        if (root == null) {
            root = newNode(key, value, null);
            root.color = BLACK;
            size++;
            return null;
        }
        Node<K, V> cur = root;
        Node<K, V> p;
        int dir;
        do {
            p = cur;
            if ((dir = key.compareTo(cur.key)) == 0) {
                V oldVal = cur.value;
                cur.value = oldVal;
                return oldVal;
            }
        } while ((cur = (dir > 0) ? cur.right : cur.left) != null);
        Node<K, V> kvNode = newNode(key, value, p);
        if (dir < 0) {
            p.left = kvNode;
        } else {
            p.right = kvNode;
        }
        fixAfterInsertion(kvNode);
        return null;
    }

    public V get(K key) {
        Node<K, V> node = node(key);
        return node == null ? null : node.value;
    }

    public V remove(K key) {
        Node<K, V> node = node(key);
        if (node == null) return null;
        V oldVal = node.value;
        if (node.left != null && node.right != null) {
            Node<K, V> s = node.right;
            while (s.left != null) {
                s = s.left;
            }
            node.key = s.key;
            node.value = s.value;
            node = s;
        }
        Node<K, V> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            replacement.parent = node.parent;
            if (node.parent == null) {
                root = replacement;
            } else if (node.parent.left == node) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            fixAfterDeletion(replacement);
            node.parent = node.left = node.right = null;
        } else if (node.parent == null) {
            root = null;
        } else {
            fixAfterDeletion(node);
            if (node.parent.left == node) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            node.parent = null;
        }
        size--;
        return oldVal;
    }

    private void fixAfterInsertion(Node<K, V> node) {
        while (node != root) {
            if (colorOf(parentOf(node)) == RED) {
                if (rightOf(parentOf(node)) == node) {
                    node = parentOf(node);
                    rotateLeft(node);
                }
                setColor(parentOf(parentOf(node)), RED);
                setColor(parentOf(node), BLACK);
                setColor(node, RED);
                rotateRight(parentOf(parentOf(node)));
            }
            if (colorOf(leftOf(parentOf(node))) == RED && colorOf(rightOf(parentOf(node))) == RED) {
                setColor(leftOf(parentOf(node)), BLACK);
                setColor(rightOf(parentOf(node)), BLACK);
                setColor(parentOf(node), RED);
                node = parentOf(node);
            } else {
                if (rightOf(parentOf(node)) == node) {
                    setColor(parentOf(node), RED);
                    setColor(node, BLACK);
                    rotateLeft(parentOf(node));
                }
                break;
            }
        }
        setColor(root, BLACK);
    }

    private void fixAfterDeletion(Node<K, V> node) {

    }

    private Node<K, V> parentOf(Node<K, V> node) {
        return node == null ? null : node.parent;
    }

    private Node<K, V> leftOf(Node<K, V> node) {
        return node == null ? null : node.left;
    }

    private Node<K, V> rightOf(Node<K, V> node) {
        return node == null ? null : node.right;
    }

    private byte colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private void setColor(Node<K, V> node, byte color) {
        node.color = color;
    }

    private Node<K, V> rotateLeft(Node<K, V> p) {
        Node<K, V> r = p.right;
        p.right = r.left;
        if (r.left != null) {
            r.left.parent = p;
        }
        if (p.parent == null) {
            root = r;
        } else if (p.parent.left == p) {
            p.parent.left = r;
        } else {
            p.parent.right = r;
        }
        r.left = p;
        r.parent = p.parent;
        p.parent = r;
        return r;
    }

    private Node<K, V> rotateRight(Node<K, V> p) {
        Node<K, V> l = p.left;
        p.left = l.right;
        if (l.right != null) {
            l.right.parent = p;
        }
        if (p.parent == null) {
            root = l;
        } else if (p.parent.left == p) {
            p.parent.left = l;
        } else {
            p.parent.right = l;
        }
        l.right = p;
        l.parent = p.parent;
        p.parent = l;
        return l;
    }

    private Node<K, V> node(K key) {
        Node<K, V> cur = root;
        int dir;
        while (cur != null) {
            if ((dir = key.compareTo(cur.key)) == 0) {
                return cur;
            }
            cur = dir > 0 ? cur.right : cur.left;
        }
        return null;
    }

    private Node<K, V> newNode(K key, V value, Node<K, V> parent) {
        return new Node<>(key, value, parent);
    }

    private static class Node<K, V> {
        byte color;
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        public Node(K key, V value, Node<K, V> parent) {
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
}
