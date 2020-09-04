package org.java.cocurrent;

/**
 * TODO synchronized 代码块级别与类级别在字节码方面的区别
 * 1. 即使我们确实有一些共享资源需要保护，也要尽可能降低锁的粒度，仅对必要的代码块与必要的保护资源进行枷锁
 *
 */
public class InterestingTest {
    volatile int a = 1;
    volatile int b = 1;
    private static int counter = 0;

    public static int getCounter() {
        return counter;
    }

    public static int reset() {
        counter = 0;
        return 0;
    }

    public synchronized void wrong() {
        counter++;
    }

    public synchronized void add() {
        System.out.println("start");
        for (int i = 0; i < 10000; i++) {
            a++;
            b++;
        }
        System.out.println("add done");
    }

    public synchronized void compare() {
        System.out.println("compare start");
        for (int i = 0; i < 10000; i++) {
            if (a < b) {
                System.out.println(a + "\t\t" + b + "\t\t" + (a > b));
            }
        }
        System.out.println("compare end");
    }

}
