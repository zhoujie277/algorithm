package com.future.datastruct.map;

import com.future.utils.PrintUtils;
import com.future.utils.drawtree.IDrawableTree;
import com.future.utils.drawtree.PrintTreeUtil;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * 哈希表
 * <p>
 * 本哈希表参照Java8的HashMap设计实现。
 * 数据结构：数组+链表+红黑树
 * <p>
 * 散列设计：
 * 用对象的hashCode的高16位和低16位进行异或运算，充分利用所有关键信息参与运算，增加平均散列程度。
 * <p>
 * 容量设计：容量始终为2的幂次方，主要目的如下：
 * 1、为元素快速找到桶，用位运算取代取模%运算。
 * 2、在扩容时，元素重新分配桶时，不需要重新计算hash和桶索引，因为其要么在圆桶，要么在原索引+原容量的新索引。用hash值和旧容量进行与运算即可判断。
 * <p>
 * 扩容设计(负载因子）：
 * 为了保证空间利用和散列查找达到最优，原Java的HashMap采用了0.75的负载因子，也就是说，当元素达到总容量75%的时候就开始扩容，
 * 如果元素为了提高空间利用，而提高负载因子，则会增加hash冲突的可能性，扩大链表数量，降低查找速度。
 * 如果元素为了散列更加均匀，而降低负载因子，则会过多浪费hashMap的空间。
 * 事实上，Java做了大量的工业测试来最终确定加载因子为0.75最为合适。
 * <p>
 * 红黑树设计：
 * 在某些情况下，特别是HashMap存储多种数据类型时，仍然避免不了Hash冲突，当链表长度超过8时，则会进行树化。
 * 其目的是链表查找速度较慢，设一个链表的长度为k，链表的添加、查找、删除时间复杂度都是O(k)，而红黑树是平衡二叉搜索树，可达到O(logK)
 * <p>
 * 红黑树退化链表设计：
 * 当红黑树结点较少时，为了节约内存，一个TreeNode比普通Node耗费内存近一倍。所以需要退化成链表。
 * 而退化链表的阈值为6，低于树化的阈值，这样设计其目的是防止复杂度震荡，避免出现反复树化和反树化的情况出现
 * </p>
 *
 * @author jayzhou
 */
@SuppressWarnings("all")
public class HashTable<K, V> implements IMap<K, V>, Serializable {
    private static final int DEFAULT_INIT_CAPACITY = 1 << 4;
    private static final float DEFAULT_FACTOR = 0.75f;
    static final int TREEIFY_THRESHOLD = 8;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final int UNTREEIFY_THRESHOLD = 6;
    static final int MIN_TREEIFY_CAPACITY = 64;

    private Node<K, V>[] table;
    private int size;
    private int threshold;

    public HashTable() {
        this(DEFAULT_INIT_CAPACITY);
    }

    public HashTable(int capacity) {
        size = 0;
        capacity = tabSizeFor(capacity);
        table = new Node[capacity];
        threshold = (int) (capacity * DEFAULT_FACTOR);
    }

    private static int tabSizeFor(int cap) {
        if (cap <= 0) return DEFAULT_INIT_CAPACITY;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    private Node<K, V> node(K key, int hash) {
        int index = indexOf(hash);
        Node<K, V> current = table[index];
        if (current instanceof TreeNode) {
            return ((TreeNode<K, V>) current).find(hash, key);
        }
        while (current != null) {
            if (hash == current.hash && Objects.equals(current.key, key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key, hash(key));
        return node == null ? null : node.value;
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key);
        int index = indexOf(hash);
        Node<K, V> p = table[index];
        Node<K, V> result = null;
        if (p == null) {
            table[index] = newNode(hash, key, value, null);
        } else {
            if (p.hash == hash && Objects.equals(p.key, key)) {
                result = p;
            } else if (p instanceof TreeNode) {
                result = ((TreeNode<K, V>) p).putTreeVal(table, hash, key, value);
            } else {
                int binCount = 1;
                while ((result = p.next) != null) {
                    binCount++;
                    if (result.hash == hash && Objects.equals(result.key, key)) {
                        break;
                    }
                    p = result;
                }
                if (result == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount > TREEIFY_THRESHOLD) {
                        treeifyBin(table, hash);
                    }
                }
            }
        }
        if (result != null) {
            V oldVal = result.value;
            result.value = value;
            return oldVal;
        }
        if (++size > threshold) {
            resize();
        }
        afterNodeInsertion();
        return null;
    }

    protected void afterNodeInsertion() {
    }

    @Override
    public boolean containsKey(K key) {
        return node(key, hash(key)) != null;
    }

    @Override
    public boolean containsValue(V v) {
        for (Node<K, V> kvNode : table) {
            Node<K, V> cur = kvNode;
            while (cur != null) {
                if (Objects.equals(v, cur.value)) {
                    return true;
                }
                cur = cur.next;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public V remove(K key) {
        int hash = hash(key);
        int index = indexOf(hash);
        Node<K, V> prev = table[index];
        if (prev == null) return null;
        Node<K, V> node;
        if (prev.hash == hash && Objects.equals(prev.key, key)) {
            node = prev;
        } else if (prev instanceof TreeNode) {
            node = ((TreeNode<K, V>) prev).getTreeNode(hash, key);
        } else {
            while ((node = prev.next) != null) {
                if (node.hash == hash && Objects.equals(node.key, key)) {
                    break;
                }
                prev = node;
            }
        }
        if (node == null) return null;
        V oldVal = node.value;
        if (node instanceof TreeNode) {
            ((TreeNode<K, V>) node).removeTreeVal(this, table);
        } else if (node == prev) {
            table[index] = node.next;
        } else {
            prev.next = node.next;
        }
        size--;
        return oldVal;
    }

    private void resize() {
        grow();
    }

    private void grow() {
        Node<K, V>[] oldTab = table;
        int oldCap = oldTab.length;
        int newCap = oldCap << 1;
        if (newCap > MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }
        threshold = (int) (newCap * DEFAULT_FACTOR);
        Node<K, V>[] newTab = new Node[newCap];
        for (int i = 0; i < oldTab.length; i++) {
            Node<K, V> node = oldTab[i];
            if (node instanceof TreeNode) {
                ((TreeNode<K, V>) node).split(this, newTab, i, oldCap);
            } else {
                Node<K, V> loHead = null, loTail = null;
                Node<K, V> hiHead = null, hiTail = null;
                while (node != null) {
                    if ((node.hash & oldCap) == 0) {
                        if (loHead == null) {
                            loHead = node;
                        } else {
                            loTail.next = node;
                        }
                        loTail = node;
                    } else {
                        if (hiHead == null) {
                            hiHead = node;
                        } else {
                            hiTail.next = node;
                        }
                        hiTail = node;
                    }
                    node = node.next;
                }
                if (loTail != null) {
                    loTail.next = null;
                    newTab[i] = loHead;
                }
                if (hiTail != null) {
                    hiTail.next = null;
                    newTab[i + oldCap] = hiHead;
                }
            }
        }
        table = newTab;
    }

    private int hash(K key) {
        int h;
        return (key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16));
    }

    private int indexOf(int hashcode) {
        return hashcode & (table.length - 1);
    }

    private int indexOf(K key) {
        return hash(key) & (table.length - 1);
    }

    private void treeifyBin(Node<K, V>[] tab, int hash) {
        PrintUtils.info("treeifyBin: " + tab.length);
        if (tab.length < MIN_TREEIFY_CAPACITY) {
            resize();
        } else {
            int index = (tab.length - 1) & hash;
            Node<K, V> node = table[index];
            PrintUtils.warning("real treeify: " + tab.length + "  " + node);
            TreeNode<K, V> head = null;
            TreeNode<K, V> tmp;
            TreeNode<K, V> prev = null;
            do {
                tmp = replacementTreeNode(node, null);
                if (prev == null) {
                    head = tmp;
                } else {
                    tmp.prev = prev;
                    prev.next = tmp;
                }
                prev = tmp;
            } while ((node = node.next) != null);
            if ((tab[index] = head) != null) {
                head.treeify(table);
            }
        }
    }

    protected Node<K, V> newNode(int hash, K key, V value, Node<K, V> next) {
        return new Node<>(key, value, next, hash);
    }

    protected TreeNode<K, V> newTreeNode(Node<K, V> node) {
        return new TreeNode<>(node.key, node.value, node.next, node.hash);
    }

    protected TreeNode<K, V> replacementTreeNode(Node<K, V> node, Node<K, V> next) {
        return new TreeNode<>(node.key, node.value, next, node.hash);
    }

    protected Node<K, V> replacementNode(Node<K, V> p, Node<K, V> next) {
        return new Node<>(p.key, p.value, next, p.hash);
    }

    @Override
    public IMapIterator<K, V> iterator() {
        return new KeyItr();
    }

    private class KeyItr implements IMapIterator<K, V> {

        private int index;
        private Node<K, V> current;

        private KeyItr() {
            index = 0;
            current = findNext();
        }

        private Node<K, V> findNext() {
            Node<K, V> c = null;
            while (index < table.length && (c = table[index]) == null) {
                index++;
            }
            return c;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public K next() {
            K val = current.key;
//            PrintUtils.println("key:" + current.key + ", tabIndex=" + hash(current.hash));
            current = current.next;
            if (current == null) {
                index++;
                current = findNext();
            }
            return val;
        }

        @Override
        public V value() {
            return current.value;
        }
    }

    protected static class Node<K, V> {
        K key;
        V value;
        int hash;
        Node<K, V> next;

        public Node(K key, V value, Node<K, V> next, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    protected static class TreeNode<K, V> extends Node<K, V> {
        static final byte RED = 1;
        static final byte BLACK = 0;
        TreeNode<K, V> left;
        TreeNode<K, V> right;
        TreeNode<K, V> parent;
        TreeNode<K, V> prev;
        byte color;

        public TreeNode(K key, V value, Node<K, V> next, int hash) {
            super(key, value, next, hash);
            color = RED;
        }

        final void treeify(Node<K, V>[] tab) {
            TreeNode<K, V> root = this, prev = this, cur;
            root.parent = root.left = root.right = null;
            root.color = BLACK;
            int dir;
            while (prev.next != null) {
                cur = (TreeNode<K, V>) prev.next;
                TreeNode<K, V> t = root;
                TreeNode<K, V> parent;
                do {
                    if (cur.hash > t.hash) {
                        dir = 1;
                    } else if (cur.hash < t.hash) {
                        dir = -1;
                    } else if (cur.key instanceof Comparable && t.key instanceof Comparable
                            && (dir = ((Comparable<K>) cur.key).compareTo(t.key)) != 0) {
                    } else {
                        dir = System.identityHashCode(cur.key) - System.identityHashCode(t.key);
                    }
                    parent = t;
                    t = (dir < 0) ? t.left : t.right;
                } while (t != null);
                if (dir < 0)
                    parent.left = cur;
                else
                    parent.right = cur;
                cur.parent = parent;
                root = balanceInsertion(root, cur);
                prev = cur;
            }
            moveRootToFront(tab, root);
            PrintUtils.debug("The linked was treeify:" + root.value + ",hash=" + root.hash);
            print(tab, hash);
        }

        final Node<K, V> untreeify(HashTable<K, V> map) {
            PrintUtils.info("untreeify, value=" + value);
            Node<K, V> head = null, tail = null;
            for (Node<K, V> p = this; p != null; p = p.next) {
                Node<K, V> node = map.replacementNode(p, null);
                if (tail == null) {
                    head = tail = node;
                } else {
                    tail.next = node;
                    tail = node;
                }
            }
            return head;
        }

        final void split(HashTable<K, V> map, Node<K, V>[] tab, int index, int bit) {
            PrintUtils.info("tree node split method invoked: hash=" + hash);
            TreeNode<K, V> first = this;
            TreeNode<K, V> loHead = null, loTail = null;
            TreeNode<K, V> hiHead = null, hiTail = null;
            int loCount = 0, hiCount = 0;
            TreeNode<K, V> current = first, next;
            while (current != null) {
                next = (TreeNode<K, V>) current.next;
                current.next = null;
                if ((current.hash & bit) == 0) {
                    if (loHead == null) {
                        loHead = loTail = current;
                    } else {
                        loTail.next = current;
                        current.prev = loTail;
                        loTail = current;
                    }
                    loCount++;
                } else {
                    if (hiHead == null) {
                        hiHead = hiTail = current;
                    } else {
                        hiTail.next = current;
                        current.prev = hiTail;
                        hiTail = current;
                    }
                    hiCount++;
                }
                current = next;
            }
            if (loHead != null) {
                if (loCount < UNTREEIFY_THRESHOLD) {
                    tab[index] = loHead.untreeify(map);
                } else {
                    tab[index] = loHead;
                    if (hiHead != null)
                        loHead.treeify(tab);
                }
            }
            if (hiHead != null) {
                if (hiCount < UNTREEIFY_THRESHOLD) {
                    tab[index + bit] = hiHead.untreeify(map);
                } else {
                    tab[index + bit] = hiHead;
                    if (loHead != null)
                        hiHead.treeify(tab);
                }
            }
        }

        void print(Node<K, V>[] tab, int hash) {
            int index = (tab.length - 1) & hash;
            final TreeNode<K, V> root = (TreeNode<K, V>) tab[index];
            PrintTreeUtil.printTree(new IDrawableTree<TreeNode<K, V>>() {
                @Override
                public TreeNode<K, V> root() {
                    return root;
                }

                @Override
                public TreeNode<K, V> left(TreeNode<K, V> node) {
                    return node.left;
                }

                @Override
                public TreeNode<K, V> right(TreeNode<K, V> node) {
                    return node.right;
                }

                @Override
                public boolean isRed(TreeNode<K, V> node) {
                    return node.color == RED;
                }
            });
        }

        TreeNode<K, V> putTreeVal(Node<K, V>[] table, int h, K k, V value) {
            PrintUtils.info("TreeNode putTreeVal key=" + k + ", value=" + value + ", hash=" + hash);
            TreeNode<K, V> root = (parent != null) ? root() : this;
            TreeNode<K, V> current = root;
            TreeNode<K, V> parent;
            int dir;
            boolean searched = false;
            do {
                parent = current;
                K ck = current.key;
                if (h > current.hash) {
                    dir = 1;
                } else if (h < current.hash) {
                    dir = -1;
                } else if (Objects.equals(k, ck)) {
                    return current;
                } else if (k != null && ck != null
                        && k.getClass() == ck.getClass() && k instanceof Comparable && ck instanceof Comparable
                        && (dir = ((Comparable<K>) k).compareTo(ck)) != 0) {
                } else if (k == null || ck == null) {
                    dir = -1;
                } else {
                    TreeNode<K, V> ch;
                    if (!searched) {
                        searched = true;
                        if (((ch = current.left) != null && (ch = ch.find(k)) != null)
                                || ((ch = current.right) != null && (ch = ch.find(k)) != null)) {
                            return ch;
                        }
                    }
                    dir = System.identityHashCode(k) - System.identityHashCode(ck);
                }
                current = dir < 0 ? current.left : current.right;
            } while (current != null);
            TreeNode<K, V> newNode = new TreeNode<>(k, value, parent.next, h);
            newNode.parent = parent;
            if (dir < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            if (parent.next != null) {
                ((TreeNode<K, V>) parent.next).prev = newNode;
            }
            parent.next = newNode;
            newNode.prev = parent;
            moveRootToFront(table, balanceInsertion(root, newNode));
            print(table, hash);
            return null;
        }

        final void removeTreeVal(HashTable<K, V> map, Node<K, V>[] tab) {
            int index = hash & (tab.length - 1);
            TreeNode<K, V> first = (TreeNode<K, V>) tab[index], root = first;
            TreeNode<K, V> suc = (TreeNode<K, V>) next, pred = prev;
            if (pred == null) {
                tab[index] = first = suc;
            } else {
                pred.next = suc;
            }
            if (suc != null) {
                suc.prev = pred;
            }
            if (first == null) return;
            if (root.parent != null) {
                root = root();
            }
            if (root == null || root.right == null || root.left == null || root.left.left == null) {
                tab[index] = untreeify(map);
                return;
            }

            TreeNode<K, V> target = this;
            if (left != null && right != null) {
                TreeNode<K, V> successor = right;
                while (successor.left != null) {
                    successor = successor.left;
                }
                hash = successor.hash;
                key = successor.key;
                value = successor.value;
                target = successor;
            }
            TreeNode<K, V> replacement = target.left != null ? target.left : target.right;
            if (replacement != null) {
                replacement.parent = target.parent;
                if (target.parent == null) {
                    tab[index] = replacement;
                } else if (target.parent.left == target) {
                    target.parent.left = replacement;
                } else {
                    target.parent.right = replacement;
                }
                target.left = target.right = target.parent = null;
                if (target.color == BLACK) {
                    root = (TreeNode<K, V>) tab[index];
                    moveRootToFront(tab, balanceDeletion(root, replacement));
                }
            } else if (target.parent == null) {
                tab[index] = null;
            } else {
                if (target.color == BLACK)
                    moveRootToFront(tab, balanceDeletion(root(), target));
                if (target.parent.left == this) {
                    target.parent.left = null;
                } else {
                    target.parent.right = null;
                }
                target.parent = null;
            }
            print(tab, index);
        }

        final TreeNode<K, V> find(int h, K k) {
            TreeNode<K, V> p = this;
            TreeNode<K, V> q;
            int dir;
            do {
                if (h > p.hash) {
                    p = p.right;
                } else if (h < p.hash) {
                    p = p.left;
                } else if (Objects.equals(k, p.key)) {
                    return p;
                } else if (p.left == null) {
                    p = p.right;
                } else if (p.right == null) {
                    p = p.left;
                } else if (p.key instanceof Comparable && k instanceof Comparable
                        && (dir = ((Comparable<K>) k).compareTo(p.key)) != 0) {
                    p = (dir > 0) ? p.right : p.left;
                } else if ((q = p.right.find(h, k)) != null) {
                    return q;
                } else {
                    p = p.left;
                }
            } while (p != null);
            return null;
        }

        final TreeNode<K, V> root() {
            TreeNode<K, V> p = this;
            while (p.parent != null) {
                p = p.parent;
            }
            return p;
        }

        final TreeNode<K, V> getTreeNode(int h, K key) {
            return (parent != null ? root() : this).find(h, key);
        }

        static <K, V> void moveRootToFront(Node<K, V>[] tab, TreeNode<K, V> root) {
            int index = root.hash & (tab.length - 1);
            TreeNode<K, V> first = (TreeNode<K, V>) tab[index];
            if (first != root) {
                tab[index] = root;
                if (root.prev != null)
                    root.prev.next = root.next;
                if (root.next != null) {
                    ((TreeNode<K, V>) root.next).prev = root.prev;
                }
                if (first != null)
                    first.prev = root;
                root.next = first;
                root.prev = null;
            }
        }

        static <K, V> TreeNode<K, V> balanceInsertion(TreeNode<K, V> root, TreeNode<K, V> node) {
            while (true) {
                if (parentOf(node) == null) {
                    node.color = BLACK;
                    return node;
                }
                if (colorOf(parentOf(node)) == BLACK) return root;
                if (leftOf(parentOf(parentOf(node))) == parentOf(node)) {
                    TreeNode<K, V> u = rightOf(parentOf(parentOf(node)));
                    if (colorOf(u) == RED) {
                        //overflow
                        setColor(u, BLACK);
                        setColor(parentOf(node), BLACK);
                        setColor(parentOf(parentOf(node)), RED);
                        node = parentOf(parentOf(node));
                    } else {
                        if (rightOf(parentOf(node)) == node) {
                            node = parentOf(node);
                            root = rotateLeft(root, node);
                        }
                        setColor(node, RED);
                        setColor(parentOf(node), BLACK);
                        setColor(parentOf(parentOf(node)), RED);
                        root = rotateRight(root, parentOf(parentOf(node)));
                    }
                } else {
                    TreeNode<K, V> u = leftOf(parentOf(parentOf(node)));
                    if (colorOf(u) == RED) {
                        //overflow
                        setColor(u, BLACK);
                        setColor(parentOf(node), BLACK);
                        setColor(parentOf(parentOf(node)), RED);
                        node = parentOf(parentOf(node));
                    } else {
                        if (leftOf(parentOf(node)) == node) {
                            node = parentOf(node);
                            root = rotateRight(root, node);
                        }
                        setColor(node, RED);
                        setColor(parentOf(node), BLACK);
                        setColor(parentOf(parentOf(node)), RED);
                        root = rotateLeft(root, parentOf(parentOf(node)));
                    }
                }
            }
        }

        static <K, V> TreeNode<K, V> balanceDeletion(TreeNode<K, V> root, TreeNode<K, V> node) {
            while (node != root && colorOf(node) == BLACK) {
                if (leftOf(parentOf(node)) == node) {
                    TreeNode<K, V> sib = rightOf(parentOf(node));
                    if (colorOf(sib) == RED) {
                        setColor(parentOf(node), RED);
                        setColor(sib, BLACK);
                        root = rotateLeft(root, parentOf(node));
                        sib = rightOf(parentOf(node));
                    }
                    if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
                        setColor(sib, RED);
                        node = parentOf(node);
                    } else {
                        if (colorOf(rightOf(sib)) == BLACK) {
                            setColor(sib, RED);
                            setColor(leftOf(sib), BLACK);
                            root = rotateRight(root, sib);
                            sib = rightOf(parentOf(node));
                        }
                        setColor(sib, colorOf(parentOf(node)));
                        setColor(rightOf(sib), BLACK);
                        setColor(parentOf(node), BLACK);
                        root = rotateLeft(root, parentOf(node));
                        node = root;
                    }
                } else {
                    TreeNode<K, V> sib = leftOf(parentOf(node));
                    if (colorOf(sib) == RED) {
                        setColor(parentOf(node), RED);
                        setColor(sib, BLACK);
                        root = rotateRight(root, parentOf(node));
                        sib = leftOf(parentOf(node));
                    }
                    if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
                        setColor(sib, RED);
                        node = parentOf(node);
                    } else {
                        if (colorOf(leftOf(sib)) == BLACK) {
                            setColor(sib, RED);
                            setColor(leftOf(sib), BLACK);
                            root = rotateLeft(root, sib);
                            sib = leftOf(parentOf(node));
                        }
                        setColor(sib, colorOf(parentOf(node)));
                        setColor(leftOf(sib), BLACK);
                        setColor(parentOf(node), BLACK);
                        root = rotateRight(root, parentOf(node));
                        node = root;
                    }
                }
            }
            setColor(node, BLACK);
            return root;
        }

        TreeNode<K, V> find(K key) {
            if (!Objects.equals(this.key, key)) {
                TreeNode<K, V> result = null;
                if (left != null) {
                    result = left.find(key);
                }
                if (result == null && right != null) {
                    result = right.find(key);
                }
                return result;
            }
            return this;
        }

        static <K, V> TreeNode<K, V> parentOf(TreeNode<K, V> node) {
            return node == null ? null : node.parent;
        }

        static <K, V> byte colorOf(TreeNode<K, V> node) {
            return node == null ? BLACK : node.color;
        }

        static <K, V> TreeNode<K, V> leftOf(TreeNode<K, V> node) {
            return node == null ? null : node.left;
        }

        static <K, V> TreeNode<K, V> rightOf(TreeNode<K, V> node) {
            return node == null ? null : node.right;
        }

        static <K, V> void setColor(TreeNode<K, V> node, byte color) {
            node.color = color;
        }

        static <K, V> TreeNode<K, V> rotateLeft(TreeNode<K, V> root, TreeNode<K, V> p) {
            TreeNode<K, V> pp, r, rl;
            if (p != null && (r = p.right) != null) {
                if ((rl = p.right = r.left) != null) {
                    rl.parent = p;
                }
                if ((pp = r.parent = p.parent) == null) {
                    (root = r).color = BLACK;
                } else if (pp.left == p) {
                    pp.left = r;
                } else {
                    pp.right = r;
                }
                r.left = p;
                p.parent = r;
            }

            return root;
        }

        static <K, V> TreeNode<K, V> rotateRight(TreeNode<K, V> root, TreeNode<K, V> p) {
            TreeNode<K, V> pp, l, lr;
            if (p != null && (l = p.left) != null) {
                if ((lr = p.left = l.right) != null) {
                    lr.parent = p;
                }
                if ((pp = l.parent = p.parent) == null) {
                    (root = l).color = BLACK;
                } else if (pp.left == p) {
                    pp.left = l;
                } else {
                    pp.right = l;
                }
                l.right = p;
                p.parent = l;
            }
            return root;
        }

    }
}
