package com.future.datastruct.node;

public class WeightNode<T> extends BinaryNode<T> implements Comparable<WeightNode<T>> {
    public int weight;

    public WeightNode(T value, int weight) {
        this.weight = weight;
        this.value = value;
    }

    public WeightNode(T value, int weight, WeightNode<T> left, WeightNode<T> right) {
        this.weight = weight;
        this.left = left;
        this.right = right;
        this.value = value;
    }

    @Override
    public String toString() {
        return "WeightNode{" +
                "value=" + value +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(WeightNode<T> o) {
        return o.weight - weight;
    }
}
