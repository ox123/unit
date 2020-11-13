package org.demo.springbootdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class CountKeysController {

    private static final Logger log = LoggerFactory.getLogger(CountKeysController.class);
    private static int loop_count = 10000000;
    private static int thread_count = 10;
    private static int item_count = 1000;

    private Map<String, Long> normaluse() {
        ConcurrentHashMap<String, Long> freqs = new ConcurrentHashMap<>(item_count);
        ForkJoinPool forkJoinPool = new ForkJoinPool(thread_count);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, loop_count).parallel().forEach((i) -> {
            String key = "item" + ThreadLocalRandom.current().nextInt(item_count);
            synchronized (freqs) {
                if (freqs.contains(key)) {
                    freqs.put(key, freqs.get(key) + 1);
                } else {
                    freqs.put(key, 1L);
                }
            }
        }));
        forkJoinPool.shutdown();
        try {
            forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return freqs;
    }

    private Map<String, Long> goodUse() {
        ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>(item_count);
        ForkJoinPool forkJoinPool = new ForkJoinPool(thread_count);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, loop_count).parallel().forEach((i) -> {
            String key = "item" + ThreadLocalRandom.current().nextInt(item_count);
            freqs.computeIfAbsent(key, k -> new LongAdder()).increment();
        }));
        forkJoinPool.shutdown();
        try {
            forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return freqs.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().longValue()));
    }

    @GetMapping("good")
    public String good() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("normaluse");
        Map<String, Long> normaluse = normaluse();
        stopWatch.stop();
        stopWatch.start("goodUse");
        Map<String, Long> goodUse = goodUse();

        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
//        Assert.isTrue();
        return "ok";
    }
}
