package com.future.algoriithm.recursive;

import java.util.Arrays;

public class HanoiTower {

    final Tower[] towers = new Tower[3];
    private int count = 0;

    private int moveCount = 0;

    public HanoiTower(int count) {
        this.count = count;
        towers[0] = new Tower("A", count);
        towers[1] = new Tower("B", count);
        towers[2] = new Tower("C", count);
        for (int i = 0; i < count; i++) {
            towers[0].addDisk(new Disk(i));
        }
    }

    private void move(int n, int left, int mid, int right) {
        if (n == 1) {
            Disk remove = towers[left].remove();
            moveCount++;
            towers[right].addDisk(remove);
            return;
        }
        move(n - 1, left, right, mid);
        Disk remove = towers[left].remove();
        towers[right].addDisk(remove);
        moveCount++;
        move(n - 1, mid, left, right);
    }

    public void move() {
        move(count, 0, 1, 2);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HanoiTower:\n");
        for (Tower tower : towers) {
            builder.append(tower);
            builder.append("\n");
        }
        builder.append("moveCount=").append(moveCount);
        return builder.toString();
    }

    public static void main(String[] args) {
        HanoiTower tower = new HanoiTower(10);
        tower.move();
        System.out.println(tower);
    }

    static class Disk {
        final int no;

        public Disk(int no) {
            this.no = no;
        }

        @Override
        public String toString() {
            return no + "";
        }
    }

    static class Tower {
        final String name;
        final Disk[] disks;
        int size = 0;

        public Tower(String name, int count) {
            this.name = name;
            disks = new Disk[count];
        }

        public void addDisk(Disk disk) {
            disks[size++] = disk;
        }

        public Disk remove() {
            if (size == 0) return null;
            Disk value = disks[--size];
            disks[size] = null;
            return value;
        }

        @Override
        public String toString() {
            return "Tower{" +
                    "name='" + name + '\'' +
                    ", disks=" + Arrays.toString(disks) +
                    ", size=" + size +
                    '}';
        }
    }
}
