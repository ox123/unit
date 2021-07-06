package org.java.thread;

public class ThreadTest {
    public static void main(String[] args) {
        System.out.println(Thread.activeCount());
        Thread.currentThread().getThreadGroup().list();
    }
}
