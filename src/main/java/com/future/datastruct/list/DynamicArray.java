package com.future.datastruct.list;

import com.future.datastruct.list.define.AbstractSequence;

/**
 * 动态数组
 *
 * @author jayzhou
 */
public class DynamicArray<E> extends AbstractSequence<E> {

    public DynamicArray() {
        super();
    }

    public DynamicArray(int capacity) {
        super(capacity);
    }
}
