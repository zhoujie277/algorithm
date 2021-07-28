package com.future.datastruct.union;

import com.future.datastruct.map.HashTable;

import java.util.Objects;

/**
 * 并查集的使用辅助类，
 * 实则是UnionFindEntry的代理类
 * 如果元素需要去重，并交给一个集合帮忙管理，请采用UnionFindEntry
 *
 * @author jayzhou
 */
public class HashUnionFind<E> implements IUnionFind<E> {

    private final LinkedUnionFind<Entry> unionFindSet = new LinkedUnionFind<>();

    private final HashTable<E, Entry> table = new HashTable<>();

    /**
     * 使元素重新归属自己的集合
     */
    public void makeSet(E e) {
        if (table.containsKey(e)) return;
        Entry entry = newEntry(e);
        unionFindSet.makeSet(entry);
        table.put(e, entry);
    }

    @Override
    public E find(E e) {
        Entry entry = table.get(e);
        if (entry != null)
            entry = unionFindSet.find(entry);
        return entry == null ? null : entry.element;
    }

    @Override
    public void union(E e1, E e2) {
        Entry entry1 = table.get(e1);
        Entry entry2 = table.get(e2);
        if (entry1 == null || entry2 == null) return;
        unionFindSet.union(entry1, entry2);
    }

    @Override
    public boolean isConnected(E e1, E e2) {
        Entry entry1 = table.get(e1);
        Entry entry2 = table.get(e2);
        if (entry1 == null || entry2 == null) return false;
        return unionFindSet.isConnected(entry1, entry2);
    }

    @Override
    public void reset() {
        unionFindSet.reset();
    }

    public void clear() {
        unionFindSet.clear();
        table.clear();
    }

    @Override
    public boolean remove(E element) {
        Entry remove = table.remove(element);
        return remove != null && unionFindSet.remove(remove);
    }

    private Entry newEntry(E e) {
        return new Entry(e);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (E next : table) {
            builder.append(next);
            builder.append(":");
            builder.append(table.get(next));
            builder.append("\n");
        }
        return builder.toString();
    }

    private class Entry extends UnionFindCore.Element {
        E element;

        public Entry(E element) {
            this.element = element;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return Objects.equals(element, entry.element);
        }

        @Override
        public int hashCode() {
            return 31 + element.hashCode();
        }

        @SuppressWarnings("unchecked")
        @Override
        public String toString() {
            Entry p = parent == this ? null : (Entry) parent;
            return "Node{" +
                    "element=" + element +
                    ", parent=" + p +
                    ", size=" + size +
                    '}';
        }
    }
}
