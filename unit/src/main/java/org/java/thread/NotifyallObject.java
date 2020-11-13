package org.java.thread;

public class NotifyallObject {
    private static volatile Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("Thread A get the resourceA lock");
                    try {
                        System.out.println("Thread A begin to wait");
                        resourceA.wait();
                        System.out.println("Thread A end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("Thread B get the resourceA lock");
                    try {
                        System.out.println("Thread B begin to wait");
                        resourceA.wait();
                        System.out.println("Thread B end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
//                    System.out.println("notify");
                    resourceA.notify();
//                    resourceA.notifyAll();
                }
            }
        });
        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadC.start();
        threadA.join();
        threadB.join();
        threadC.join();
        System.out.println("over");
    }
}
