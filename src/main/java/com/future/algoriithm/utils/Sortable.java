package com.future.algoriithm.utils;

public abstract class Sortable {

    public abstract int key();

    public int keyAt(int keyIndex) {
        return keys()[keyIndex];
    }

    public int[] keys() {
        return null;
    }
}
