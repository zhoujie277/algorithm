package com.future.algoriithm.sort;

/**
 * 抽象排序器
 * 为比较排序算法提供模版
 *
 * @author jayzhou
 */
public abstract class Sorter<E extends Comparable<E>> implements Comparable<Sorter<E>> {
    public static final int VERSION_DEFAULT = 1;
    protected E[] elements;
    private int compareCount;
    private int swapCount;
    private long sortTime;
    protected int version = VERSION_DEFAULT;
    private int moveCount;

    public Sorter() {
    }

    public Sorter(int version) {
        this.version = version;
    }

    public void sortOrigin(E[] array) {
        this.elements = array;
        long start = System.currentTimeMillis();
        sort();
        sortTime = (System.currentTimeMillis() - start);
    }

    @SuppressWarnings("all")
    public void sort(E[] array) {
        E[] newArray = (E[]) new Comparable[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        sortOrigin(newArray);
    }

    protected void move(int src, int dst) {
        moveCount++;
        elements[dst] = elements[src];
    }

    protected void swap(int i, int j) {
        swapCount++;
        E tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

    protected int compare(int i, int j) {
        compareCount++;
        return elements[i].compareTo(elements[j]);
    }

    protected int compare(E e1, E e2) {
        compareCount++;
        return e1.compareTo(e2);
    }

    protected String numberString(int count) {
        if (count < 10000) return count + "";
        if (count < 1000000) return String.format("%.3f", count * 1.0f / 1000) + "k";
        return String.format("%.3f", count * 1.0f / 1000000) + " million";
    }

    @Override
    public String toString() {
        return "" + getClass().getSimpleName() + ", v" + version + " {" +
                ", sortTime=" + sortTime + "ms" +
                ", compareCount=" + numberString(compareCount) +
                ", swapCount=" + numberString(swapCount) +
                ", moveCount=" + numberString(moveCount) +
                '}';
    }

    @Override
    public int compareTo(Sorter<E> o) {
        int result = (int) (sortTime - o.sortTime);
        if (result == 0)
            result = compareCount - o.compareCount;
        if (result == 0)
            result = swapCount - o.swapCount;
        return result;
    }

    public boolean ascSorted() {
        for (int i = 1; i < elements.length; i++) {
            if (elements[i - 1].compareTo(elements[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    public E[] getElements() {
        return elements;
    }

    public E[] getElements(E[] array) {
        System.arraycopy(elements, 0, array, 0, elements.length);
        return array;
    }

    public int getVersion() {
        return version;
    }

    protected abstract void sort();
}
