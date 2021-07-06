package com.future.utils;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Objects;

/**
 * 数据工具类
 * 用于构造测试数据
 */
public class DataUtils {

    public static Student[] generateStudents(int length) {
        Student[] students = new Student[length];
        for (int i = 0; i < students.length; i++) {
            students[i] = newStudent();
        }
        return students;
    }

    public static Student newStudent() {
        return newStudent(randString());
    }

    public static Student newStudent(String name) {
        return new Student(randUUID(), randScore(), randAge(), name);
    }

    public static String[] generateStrings(int length) {
        String[] array = new String[length];
        for (int i = 0; i < length; i++) {
            array[i] = randString();
        }
        return array;
    }

    public static int randUUID() {
        return StdRandom.uniform(10000, 1000000);
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

    public static class Key<T> implements Hashable {
        private T value;
        public Key(T t) {
            this.value = t;
        }

        @Override
        public int hashValue() {
            return Objects.hashCode(value);
//            return 608374;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "value=" + value +
                    '}';
        }
    }

    public static class Student extends Sortable implements Hashable, Comparable<Student> {
        public final int score;
        public final int age;
        public final String name;
        public final int id;

        public Student(int id, int score, int age, String name) {
            this.id = id;
            this.score = score;
            this.age = age;
            this.name = name;
        }

        public Student(int score, int age, String name) {
            this(0, score, age, name);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "score=" + score +
                    ", age=" + age +
                    ", name='" + name + '\'' +
                    ", id=" + id +
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

        @Override
        public int hashValue() {
            return 10;
//            return hashCode();
        }

        @Override
        public int compareTo(Student o) {
            return score - o.score;
        }
    }

}

