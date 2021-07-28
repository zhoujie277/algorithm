package com.future.datastruct.union;

import java.util.Objects;

/**
 * 并查集核心类
 * 只提供合并查找等核心算法，
 * 不提供元素的管理
 * 如果需要元素的管理，请使用TreeUnionFind和HashUnionFind
 * <p>
 * 基于QuickUnion + size + pathHalving 方案实现的并查集
 * 采用路径压缩/分裂/减半等方案如果采用rank的方案，需要维护rank的更新，带来一定复杂度，
 * 而且随着find次数越多，待合并的两个集合高度几乎不会相差太多，甚至大概率会相等，不如采用size的优化更合适
 *
 * @author jayzhou
 */
public class UnionFindCore<E extends UnionFindCore.Element> {

    /**
     * 使元素重新归属自己的集合
     */
    public void makeSet(E e) {
        e.parent = e;
        e.size = 1;
    }

    /**
     * 查找元素所属的集合
     *
     * @param e 指定元素
     * @return 所属集合的代表元素
     */
    @SuppressWarnings("unchecked")
    public E find(E e) {
        Element element = e;
        //path halving
        while (element.parent != element) {
            element.parent = element.parent.parent;
            element = element.parent;
        }
        return (E) element;
    }

    public void union(E e1, E e2) {
        E p1 = find(e1);
        E p2 = find(e2);
        if (Objects.equals(p1, p2)) return;
        if (p1.size <= p2.size) {
            p1.parent = p2;
            p2.size += p1.size;
        } else {
            p2.parent = p1;
            p1.size += p2.size;
        }
    }

    public boolean isConnected(E e1, E e2) {
        return Objects.equals(find(e1), find(e2));
    }

    /**
     * parent == this 说明就是该集合的代表元素（根结点）
     */
    public static class Element {
        Element parent;
        int size;

        public Element() {
            this.size = 1;
            this.parent = this;
        }
    }
}
