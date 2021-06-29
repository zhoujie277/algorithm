package com.future.algoriithm.utils;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 数据工具类
 * 用于构造测试数据
 */
public class DataUtils {

    public static Student[] generateStudents(int length) {
        Student[] students = new Student[length];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(randScore(), randAge(), randString());
        }
        return students;
    }

    public static String[] generateStrings(int length) {
        String[] array = new String[length];
        for (int i = 0; i < length; i++) {
            array[i] = randString();
        }
        return array;
    }

    private static int randScore() {
        return StdRandom.uniform(450, 700);
    }

    private static int randAge() {
        return StdRandom.uniform(12, 20);
    }

    private static String randString() {
        int min = 'a';
        int max = 'z';
        int len = StdRandom.uniform(4, 12);
        StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0; i < len; i++) {
            c = (char) StdRandom.uniform(min, max + 1);
            sb.append(c);
        }
        return sb.toString();
    }

    public static class Student extends Sortable {
        public final int score;
        public final int age;
        public final String name;

        public Student(int score, int age, String name) {
            this.score = score;
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "score=" + score +
                    ", age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public int key() {
            return score;
        }

        @Override
        public int[] keys() {
            int[] a = {age, score};
            return a;
        }
    }

}

