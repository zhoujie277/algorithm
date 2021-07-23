package com.future.datastruct.tree.huffman;

import com.future.datastruct.map.HashTable;

import java.io.Serializable;
import java.util.Iterator;

/**
 * 哈夫曼码表
 */
public class HuffmanTable<K> implements Iterable<K> , Serializable {
    private HashTable<K, String> encodeTable;
    private HashTable<String, K> decodeTable;


    public HuffmanTable() {
        this.encodeTable = new HashTable<>();
        this.decodeTable = new HashTable<>();
    }

    public void put(K key, String value) {
        encodeTable.put(key, value);
        decodeTable.put(value, key);
    }

    public K get(String codec) {
        return decodeTable.get(codec);
    }

    public String get(K key) {
        return encodeTable.get(key);
    }

    @Override
    public Iterator<K> iterator() {
        return encodeTable.iterator();
    }
}
