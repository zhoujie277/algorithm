package com.future.algoriithm.tree.huffman;

import com.future.algoriithm.search.HashTab;

import java.io.Serializable;
import java.util.Iterator;

/**
 * 哈夫曼码表
 */
public class HuffmanTable<K> implements Iterable<K> , Serializable {
    private HashTab<K, String> encodeTable;
    private HashTab<String, K> decodeTable;


    public HuffmanTable() {
        this.encodeTable = new HashTab<>();
        this.decodeTable = new HashTab<>();
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
