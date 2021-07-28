package com.future.datastruct.map;

/**
 * 用内存地址作为key的treemap
 *
 * @author jayzhou
 */
public class IdentityRBTreeMap<K, V> implements IMap<K, V> {

    private RBTreeMap<Integer, V> map = new RBTreeMap<>();

    @Override
    public boolean containsKey(K key) {
        int i = System.identityHashCode(key);
        return map.containsKey(i);
    }

    @Override
    public boolean containsValue(V v) {
        return map.containsValue(v);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public V put(K key, V value) {
        return map.put(System.identityHashCode(key), value);
    }

    @Override
    public V get(K key) {
        return map.get(System.identityHashCode(key));
    }

    @Override
    public V remove(K key) {
        return map.remove(System.identityHashCode(key));
    }

    @Override
    public IMapIterator<K, V> iterator() {
        return null;
    }
}
