package com.future.datastruct.tree;

import com.future.datastruct.tree.define.ITree;

import java.util.Arrays;

/**
 * B树。（Balanced-Tree）
 * B树是一棵平衡的多路查找树，多用于文件系统、数据库的实现
 * B树的性质：
 * 1、一个结点可以存储多个数据，可以拥有多个子结点（分为3阶B树，4阶B树，m阶B树等等）
 * 2、依然是一棵排序树
 * 3、平衡，每个结点的所有子树的高度一致。
 * 4、比较矮
 * <p>
 * m 阶B树的性质
 * 假设一个结点存储的元素个数为x
 * 根结点：1 ≤ x ≤ m-1
 * 非根结点：ceil(m/2) - 1 ≤ x ≤ m-1
 * 如果有子结点，则子结点个数为y = x + 1;
 * ·  根结点：2 ≤ y ≤ m;
 * ·  非根结点：ceil(m/2) ≤ y ≤ m
 * 比如 m = 3, 2 ≤ y ≤ 3, 因此称为(2，3)树, 2-3树，其实就是3阶B树
 * 比如 m = 4，2 ≤ y ≤ 4, 因此称为(2，4)树, 2-3-4树，其实就是4阶B树。
 * 比如 m = 5，3 ≤ y ≤ 5, 因此称为(3，5)树
 * 比如 m = 6，3 ≤ y ≤ 6, 因此称为(3,6)树。
 * 比如 m = 7, 4 ≤ y ≤ 7，因此称为(4,7)树。
 * <p>
 * B树 VS 二叉搜索树
 * B树和二叉搜索树，在逻辑上是等价的
 * 多代结点(父子结点)合并，可以获得一个超级结点(可以存储多个元素的结点)
 * 2代合并的超级结点，最多拥有4个子结点(至少是4阶B树)
 * 3代合并的超级结点，最多拥有8个子结点(至少是8阶B树)
 * n代合并的超级结点，最多拥有2ⁿ个子结点(至少是2ⁿ阶B树)
 * <p>
 * 搜索
 * 1、先从结点内部从小到大开始搜索元素（也可二分搜索）
 * 2、如果命中，搜索结束
 * 3、如果未命中，再去对应的的子结点中搜索元素，重复步骤1
 * <p>
 * 添加：新添加的元素必定是添加到叶子结点（这是B树的性质）
 * 上溢(Overflow)：叶子结点的元素个数将超过限制
 * 发生上溢结点的元素个数必然等于 m。
 * 假设上溢结点最中间元素的位置为k，则将k位置的元素向上与父结点合并。
 * 将[0,k-1]和[k+1,m-1]位置的元素分裂成两个子结点，这两个子结点的元素个数，必然都不会低于最低限制 ceil(m/2) - 1;
 * 一次分裂完毕后，有可能导致父结点上溢，则需要继续向上解决。
 * 最极端的情况，一直上溢到根结点。
 * <p>
 * 删除：
 * 如果删除的结点是叶子结点，则直接删除即可
 * 如果是非叶子结点，这需要找到前驱或者后继元素，覆盖所需删除元素的值
 * 在将前驱和后继元素删除。
 * 在B树中，非叶子结点的前驱或后继元素，必定在叶子结点中。
 * 故：真正的删除操作都是发生在叶子结点中。
 * <p>
 * 下溢(underflow)：叶子结点被删掉一个元素后，元素个数可能低于最低限制(ceil(m/2) - 1)
 * 故：下溢结点的元素数量必然等于 ceil(m/2) - 2;
 * 如果下溢结点附近的兄弟结点，有至少ceil(m/2)个元素，可以向其借一个元素
 * --  将父结点的对应元素挪下来插入到下溢结点的最左侧位置。
 * --  用兄弟结点的最右侧元素替代父结点的元素b。
 * --  这种操作其实就是：旋转。
 * 如果下溢结点附近的兄弟结点，只有 ceil(m/2) - 1 个元素。
 * --  则将父结点的对应元素挪下来和左右结点进行合并。
 * --  合并后的结点元素个数等于 ceil(m/2) + ceil(m/2) - 2，不超过 m - 1;
 * 这个操作可能会导致父结点下溢，依然按照上述办法解决，直到根结点
 */
public class BTree<E extends Comparable<E>> implements ITree<E> {
    private int elementSize;
    private Node<E> root;
    private final int degree;
    private int height;

    public BTree(int degree) {
        this.degree = degree;
    }

    @Override
    public boolean contains(E element) {
        if (element == null || root == null) return false;
        return node(element) != null;
    }

    @Override
    public E add(E element) {
        if (root == null) {
            root = newNode();
            root.add(new Entry<>(element));
            height++;
        } else {
            Node<E> current = null;
            Node<E> child = root;
            int index = 0;
            while (child != null) {
                index = child.indexOf(element);
                if (index > 0) {
                    return child.replace(index, element);
                }
                index = ~index;
                current = child;
                child = child.children(index);
            }
            Entry<E> entry = new Entry<>(element);
            while (current.full()) {
                Node<E> parent = current.parent;
                if (parent == null) {
                    parent = newNode();
                    current.parent = parent;
                    parent.tail = current;
                    if (current == root) {
                        root = parent;
                        height++;
                    }
                }
                Node<E> left = newNode(parent);
                Entry<E> overEntry = current.overflow(left, entry, index);
                left.tail = overEntry.prev;
                left.fixChildren();
                overEntry.prev = left;
                index = parent.indexOf(overEntry.element);
                if (index < 0) index = ~index;
                // 添加到父结点
                entry = overEntry;
                current = parent;
            }
            current.add(index, entry);
        }
        elementSize++;
        return null;
    }

    @Override
    public E remove(E element) {
        Node<E> node = root;
        int index = 0;
        while (node != null) {
            index = node.indexOf(element);
            if (index >= 0) break;
            node = node.children(~index);
        }
        if (index < 0 || node == null) return null;
        Entry<E> entry = node.entry(index);
        Node<E> predecessor = predecessor(entry);
        //没有前驱，则说明删除的是叶子结点的元素
        if (predecessor == null) predecessor = node;
        E oldVal = entry.element;
        entry.element = predecessor.last().element;
        // delete predecessor's entry
        predecessor.removeLastEntry();
        Node<E> current = predecessor;
        while (current.lowerLimit()) {
            Node<E> parent = current.parent;
            Node<E> mergeNode = underflow(parent, current);
            if (parent == root && mergeNode != null) {
                root = mergeNode;
                height--;
                break;
            }
            current = parent;
        }
        elementSize--;
        return oldVal;
    }

    private Node<E> predecessor(Entry<E> entry) {
        if (entry.prev == null) return null;
        Node<E> current = entry.prev;
        while (current.tail != null) {
            current = current.tail;
        }
        return current;
    }

    private Node<E> underflow(Node<E> parent, Node<E> current) {
        int indexOfParent = current.indexOfParent();
        int entryIndex = (indexOfParent == parent.size) ? indexOfParent - 1 : indexOfParent;
        Entry<E> parentEntry = parent.entry(entryIndex);
        // 先问兄弟元素可不可以旋转解决
        Node<E> leftChild = sibling(parent, indexOfParent - 1);
        if (leftChild != null && leftChild.abundant()) {
            // rightRotate
            Entry<E> siblingEntry = leftChild.last();
            Entry<E> newEntry = new Entry<>(parentEntry.element);
            newEntry.prev = leftChild.tail;
            leftChild.tail = siblingEntry.prev;
            parentEntry.element = siblingEntry.element;
            current.add(newEntry);
            //todo 此处可优化，有可旋转元素时，不删除entry，等到合并时，再删除entry，可提升内存使用率
            leftChild.removeLastEntry();
            return null;
        }
        Node<E> rightChild;
        if (indexOfParent + 1 == parent.size) {
            rightChild = parent.tail;
        } else {
            rightChild = sibling(parent, indexOfParent + 1);
        }
        if (rightChild != null && rightChild.abundant()) {
            // leftRotate
            Entry<E> siblingEntry = rightChild.first();
            Entry<E> newEntry = new Entry<>(parentEntry.element);
            newEntry.prev = current.tail;
            current.tail = siblingEntry.prev;
            parentEntry.element = siblingEntry.element;
            current.add(newEntry);
            rightChild.removeFirstEntry();
            return null;
        }

        // merge 统一往右边merge
        if (leftChild != null) {
            merge(leftChild, parentEntry.element, current);
            parent.removeEntry(indexOfParent - 1);
            current.fixChildren();
            return current;
        }

        // right merge
        if (rightChild != null) {
            merge(current, parentEntry.element, rightChild);
            parent.removeEntry(indexOfParent);
            rightChild.fixChildren();
        }
        return rightChild;
    }

    private void merge(Node<E> current, E parentElement, Node<E> rightChild) {
        Entry<E> newEntry = new Entry<>(parentElement);
        newEntry.prev = current.tail;
        rightChild.add(0, newEntry);
        for (int i = 0; i < current.size; i++) {
            rightChild.add(0, current.entry(i));
        }
    }

    private Node<E> sibling(Node<E> parent, int indexOfParent) {
        Entry<E> entry = parent.entry(indexOfParent);
        if (entry == null) return null;
        if (entry.prev == null) return null;
        return entry.prev;
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int index = node.indexOf(element);
            if (index > 0) break;
            node = node.children(~index);
        }
        return node;
    }

    @Override
    public int size() {
        return elementSize;
    }

    @Override
    public boolean isEmpty() {
        return elementSize == 0;
    }

    @Override
    public void clear() {
        elementSize = 0;
        root = null;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public int depth() {
        return height;
    }

    protected Node<E> newNode() {
        return newNode(null);
    }

    protected Node<E> newNode(Node<E> parent) {
        return new Node<>(parent, degree);
    }

    @Override
    public String toString() {
        return "BTree{" +
                "elementSize=" + elementSize +
                ", root=" + root +
                ", degree=" + degree +
                ", height=" + height +
                '}';
    }

    private final static class Entry<E extends Comparable<E>> implements Comparable<E> {
        E element;
        Node<E> prev;

        public Entry(E element) {
            this(element, null);
        }

        public Entry(E element, Node<E> prev) {
            this.element = element;
            this.prev = prev;
        }

        @Override
        public int compareTo(E o) {
            return element.compareTo(o);
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    protected static class Node<E extends Comparable<E>> {
        Entry<E>[] entries;
        int size;
        Node<E> parent;
        Node<E> tail;
        private final int degree;

        public Node(int degree) {
            this(null, degree);
        }

        @SuppressWarnings("all")
        public Node(Node<E> parent, int degree) {
            this.parent = parent;
            this.degree = degree;
            entries = new Entry[degree - 1];
            tail = null;
            size = 0;
        }

        public int indexOf(E element) {
            // m阶b树的查找，m≥10，可采用二分查找提高效率
            for (int i = 0; i < size; i++) {
                int cmp = entries[i].compareTo(element);
                if (cmp == 0)
                    return i;
                if (cmp > 0)
                    return ~i;
            }
            return ~size;
        }

        public void removeFirstEntry() {
            removeEntry(0);
        }

        public void removeLastEntry() {
            Entry<E> lastEntry = entries[size - 1];
            lastEntry.element = null;
            lastEntry.prev = null;
            entries[--size] = null;
        }

        public void removeEntry(int index) {
            System.arraycopy(entries, index + 1, entries, index, size - index - 1);
            entries[--size] = null;
        }

        @SuppressWarnings("unused")
        public Entry<E> first() {
            return entries[0];
        }

        public Entry<E> last() {
            return entries[size - 1];
        }

        public int indexOfParent() {
            if (parent.tail == this) return parent.size;
            for (int i = 0; i < parent.size; i++) {
                if (parent.entries[i].prev == this) {
                    return i;
                }
            }
            throw new VerifyError("没在父结点中找到自己，父结点的prev指向有错误");
        }

        public Entry<E> entry(int index) {
            if (index < 0 || index >= size) return null;
            return entries[index];
        }

        @SuppressWarnings("unused")
        public void setEntry(int index, Entry<E> entry) {
            if (index < 0 || index >= size) return;
            entries[index] = entry;
        }

        public Node<E> children(int index) {
            if (index == size) return tail;
            if (entries[index] == null) return null;
            return entries[index].prev;
        }

        public E replace(int index, E element) {
            E oldVal = entries[index].element;
            entries[index].element = element;
            return oldVal;
        }

        public Entry<E> overflow(Node<E> left, Entry<E> entry, int elementIndex) {
            int oldSize = size;
            int midIndex = (entries.length + 1) >>> 1;
            Entry<E> midValue;
            if (midIndex == elementIndex) {
                midValue = entry;

                moveEntry(0, midIndex, left);
                moveEntry(midIndex, size, this);
            } else if (elementIndex < midIndex) {
                midValue = entries[midIndex - 1];

                moveEntry(0, elementIndex, midIndex - 1, entry, left);
                if (elementIndex == midIndex - 1) {
                    left.entries[elementIndex] = entry;
                    left.size++;
                }
                moveEntry(midIndex, size, this);
            } else {
                midValue = entries[midIndex];
                moveEntry(0, midIndex, left);
                moveEntry(midIndex + 1, elementIndex, size, entry, this);
                if (elementIndex == oldSize) {
                    entries[midIndex - 1] = entry;
                    size++;
                }
            }
            entries[midIndex] = null;
            return midValue;
        }

        private void moveEntry(int start, int mid, int end, Entry<E> entry, Node<E> dst) {
            int j = 0;
            for (int i = start; i < end; i++) {
                if (i == mid) {
                    dst.entries[mid + 1] = entries[i];
                    dst.entries[mid] = entry;
                    j += 2;
                } else {
                    dst.entries[j] = entries[i];
                    j++;
                }
                entries[i] = null;
            }
            dst.size = j;
        }

        private void moveEntry(int start, int end, Node<E> dst) {
            int j = 0;
            for (int i = start; i < end; i++) {
                dst.entries[j] = entries[i];
                j++;
                entries[i] = null;
            }
            dst.size = j;
        }

        public boolean abundant() {
            return size > ((degree + 1) >>> 1) - 1;
        }

        // degree阶B树的下限，ceil(degree/2) - 1;
        public boolean lowerLimit() {
            return size < ((degree + 1) >>> 1) - 1;
        }

        public boolean full() {
            return size == entries.length;
        }

        public int add(Entry<E> entry) {
            entries[size] = entry;
            size++;
            return size;
        }

        public int add(int index, Entry<E> entry) {
            System.arraycopy(entries, index, entries, index + 1, size - index);
            entries[index] = entry;
            size++;
            return index;
        }

        public void fixChildren() {
            if (tail != null) {
                tail.parent = this;
            }
            for (int i = 0; i < size; i++) {
                if (entries[i].prev != null)
                    entries[i].prev.parent = this;
            }
        }

        @Override
        public String toString() {
            return Arrays.toString(entries);
        }
    }
}
