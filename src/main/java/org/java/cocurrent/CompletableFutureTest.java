package org.java.cocurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.LongStream;

public class CompletableFutureTest {
    static ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "executor-" + count);
        }
    });

    public static void main(String[] args) {
        long[] longs = LongStream.rangeClosed(1, 1000).toArray();
        System.out.println(Runtime.getRuntime().availableProcessors());
        CompletableFuture completableFuture0 = CompletableFuture.completedFuture("messages").thenApplyAsync(s -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        }, executorService);
        System.out.println(completableFuture0.join());
        CompletableFuture completableFuture = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            System.out.println(Thread.currentThread().isDaemon());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        });
        System.out.println(completableFuture.isDone());
        completableFuture.join();
        System.out.println(completableFuture.getNow(null));
    }
}
