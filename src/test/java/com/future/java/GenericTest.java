package com.future.java;

public class GenericTest {

    static class Node<E> {
        E e;

        public void setE(E e) {
            this.e = e;
        }

        public E getE() {
            return e;
        }
    }

    static class NodeExtends<E extends Fruit> {
        E e;

        public void setE(E e) {
            this.e = e;
        }

        public E getE() {
            return e;
        }
    }

    static class Food {
        String name;
    }

    static class Fruit extends Food {
        int color;
    }

    static class Apple extends Fruit {
        int hello;
    }

    // 协变
    public static Fruit getFood(Node<? extends Fruit> food) {
        return food.getE();
    }

    // 抗变
    public static void setFood(Node<? super Fruit> food) {
        food.setE(new Fruit());
    }

    public static void main(String[] args) {
        {
//            Node<Fruit> node = new Node<>();
//            Food food = new Food();
//            Fruit fruit = new Fruit();
//            Apple apple = new Apple();
//            node.setE(food);
//            node.setE(fruit);
//            node.setE(apple);
        }

        {
//            NodeExtends<Fruit> node = new NodeExtends<>();
//            Food food = new Food();
//            Fruit fruit = new Fruit();
//            Apple apple = new Apple();
//            node.setE(food);
//            node.setE(fruit);
//            node.setE(apple);
        }

        {
//            Node<? extends Fruit> node = new Node<>();
//            Food food = new Food();
//            Fruit fruit = new Fruit();
//            Apple apple = new Apple();
//            node.setE(food);
//            node.setE(fruit);
//            node.setE(apple);
//            Food e = node.getE();
//            Fruit e2 = node.getE();
//            Apple e3 = node.getE();
        }

        {
//            Node<? super Fruit> node = new Node<>();
//            Food food = new Food();
//            Fruit fruit = new Fruit();
//            Apple apple = new Apple();
//            node.setE(food);
//            node.setE(fruit);
//            node.setE(apple);
//            Food e = node.getE();
//            Fruit e2 = node.getE();
//            Apple e3 = node.getE();
        }

//        {
//            Node<Food> foodNode = new Node<>();
//            Node<Fruit> fruitNode = new Node<>();
//            Node<Apple> appleNode = new Node<>();
//            setFood(foodNode);
//            setFood(fruitNode);
//            setFood(appleNode);
//        }
//
//        {
//            Node<Food> foodNode = new Node<>();
//            Node<Fruit> fruitNode = new Node<>();
//            Node<Apple> appleNode = new Node<>();
//            getFood(foodNode);
//            getFood(fruitNode);
//            getFood(appleNode);
//        }
    }
}
