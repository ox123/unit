package org.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class BasicThread {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
//                Thread.sleep(10);
                return "hello";
            }
        });
        new Thread(futureTask).start();
        try {
            String s = futureTask.get();
            System.out.println(s);
            long end = System.currentTimeMillis();
            System.out.println(end-start);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
