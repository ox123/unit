package org.java.container;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MyThreadPool {
    BlockingQueue<Runnable> workQueue;

    List<WorkThread> workThreads = new ArrayList<>();

    MyThreadPool(int poolSize, BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        for (int i = 0; i < poolSize; i++) {
            WorkThread workThread = new WorkThread();
            workThread.start();
            workThreads.add(workThread);
        }
    }

    void execute(Runnable command) throws InterruptedException {
        workQueue.put(command);
    }

    class WorkThread extends Thread {
        @Override
        public void run() {
            while (true) {
                Runnable task = null;
                try {
                    task = workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                task.run();
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(5);
        MyThreadPool pool = new MyThreadPool(2, workQueue);
        long start = System.currentTimeMillis();
        for (int i = 1; i < 100; i++) {
            int num = i;
            try {
                pool.execute(() -> {
//                    System.out.println(">>>>" + num + "-----");
                    int c = num/num;
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}


