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

    @SuppressWarnings("unchecked")
    public E[] toArray(E[] array) {
        for (int i = 0; i < size; i++) {
            array[i] = (E) elements[i];
        }
        return array;
    }
}
