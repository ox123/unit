package org.java.cocurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ConcurrentHashmapTest {
    private static int LOOG_COUNT = 1000000;
    private static int thread_count = 10;
    private static int item_count = 1000;
    public static void main(String[] args) {
        ConcurrentHashMap<String,Long> freqs = new ConcurrentHashMap<>(item_count);
        ForkJoinPool forkJoinPool = new ForkJoinPool(thread_count);
        long start = System.currentTimeMillis();
        forkJoinPool.execute(()-> IntStream.rangeClosed(1,LOOG_COUNT).parallel().forEach(i->{
            String key = "item"+ThreadLocalRandom.current().nextInt(item_count);
            synchronized (freqs){
                if (freqs.contains(key)){
                    freqs.put(key, freqs.get(key)+1);
                }else{
                    freqs.put(key,1L);
                }
            }
        }));
        forkJoinPool.shutdown();
        try {
            forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(freqs);
    }
}
