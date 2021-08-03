package com.future.algoriithm.recursive;

/**
 * 尾调用
 * <p>
 * 递归调用会增加方法栈帧的深度，当深度大于某个阈值时，会抛出StackOverFlow异常
 * 递归优化有如下方式：
 * 1、将递归采用非递归形式（自己模拟函数栈调用），并根据具体代码重复利用其空间
 * 2、采用尾调用形式。一般的编程语言都有尾调用消除的优化，以达到重复利用栈帧的优化
 * <p>
 * 尾调用消除是指复用原来的栈帧空间（扩大或减小原来栈帧的高度），以达到内存优化的目的
 * 具体编译语言的尾调用优化取决于语言设计者。
 * <p>
 * 比如Java虚拟机会消除尾递归的尾调用，但不会消除一般函数的尾调用，因为其栈帧不能被改变。
 *
 * @author jayzhou
 */
public class TailCall {

    public void test1() {
        int n = 10;
        test2(n);
    }

    public void test2(int k) {
        System.out.println(k);
    }

    private static int factorialDirect(int n) {
        if (n <= 1) return 1;
        return n * factorialDirect(n - 1);
    }

    private static int factorial(int n) {
        return factorial(n, 1);
    }

    private static int factorial(int n, int result) {
        if (n <= 1) return result;
        return factorial(n - 1, n * result);
    }

    public static void main(String[] args) {
        int i = factorialDirect(10);
        int factorial = factorial(10);
        System.out.println(i);
        System.out.println(factorial);
    }
}
