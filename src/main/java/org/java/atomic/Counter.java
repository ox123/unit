package org.java.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) {
        final Counter counter = new Counter();
        List<Thread> ts = new ArrayList<Thread>(5);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        counter.safeCount();
                        counter.count();
                    }
                }
            });
            ts.add(thread);
        }
        for (Thread t :
                ts) {
            t.start();
        }
        for (Thread t :
                ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(counter.i);
        System.out.println(counter.atomicInteger.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    public void safeCount() {
//        atomicInteger.incrementAndGet();
        for (; ; ) {
            int i = atomicInteger.get();
            boolean success = atomicInteger.compareAndSet(i, ++i);
            if (success) break;
        }
    }

    public void count() {
        i++;
    }
}
