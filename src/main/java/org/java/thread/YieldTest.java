package org.java.thread;

public class YieldTest implements Runnable {
    YieldTest() {
        Thread thread = new Thread(this);
        thread.start();
    }


    public static void main(String[] args) {
        new YieldTest();
        new YieldTest();
        new YieldTest();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (i % 5 == 0) {
                System.out.println(Thread.currentThread() + "yield cpu...");
//                Thread.yield();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(Thread.currentThread() + "is over");
    }
}
