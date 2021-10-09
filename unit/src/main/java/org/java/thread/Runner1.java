package org.java.thread;

public class Runner1 {
    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("" + i);
                }
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(Thread.interrupted());
                System.out.println(Thread.currentThread().isInterrupted());
            }
        };
        Thread thread = new Thread(task);
        thread.setName("test");
        thread.start();
        System.out.println(Thread.activeCount());
    }
}
