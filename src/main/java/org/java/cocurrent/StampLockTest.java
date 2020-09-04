package org.java.cocurrent;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

public class StampLockTest {
    static Thread[] holdCpuThreads = new Thread[3];
    static final StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        new Thread() {
            public void run() {
                long readLong = lock.writeLock();
                // 一直占着锁，可使其他线程被挂起
                LockSupport.parkNanos(6100000000L);
                lock.unlockWrite(readLong);
            }
        }.start();

        Thread.sleep(100);
        for (int i = 0; i < 3; ++i) {
            holdCpuThreads[i] = new Thread(new HoldCPUReadThread());
            holdCpuThreads[i].start();
        }

        Thread.sleep(10000);
        // 中断三个线程：中断是问题的关键原因
        for (int i = 0; i < 3; i++) {
            holdCpuThreads[i].interrupt();
        }
    }

    private static class HoldCPUReadThread implements Runnable {
        public void run() {
            // 获取读锁，将被阻塞，循环
            long lockr = lock.readLock();
            System.out.println(Thread.currentThread().getName() + " get read lock");
            lock.unlockRead(lockr);
        }
    }
}
