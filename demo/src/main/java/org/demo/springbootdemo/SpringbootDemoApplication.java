package org.demo.springbootdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@RestController
@SpringBootApplication
public class SpringbootDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }


    private static final Logger log = LoggerFactory.getLogger(SpringbootDemoApplication.class);
    private static int thread_count = 10;
    private static int item_count = 1000;

    private ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);

    private ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream.rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(),
                        Function.identity(),
                        (o1, o2) -> o1, ConcurrentHashMap::new));
    }

    @GetMapping("wrong")
    public String wrong() {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(item_count - 100);
        log.info("start");
        concurrentHashMap.forEach((key, val) -> {

            log.info(key, val);

        });
        log.info("end");
        log.info("initial size:{}", concurrentHashMap.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool(thread_count);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            synchronized (concurrentHashMap) {
                int gap = item_count - concurrentHashMap.size();
                log.info("gap size: {}", gap);
                concurrentHashMap.putAll(getData(gap));
            }
        }));
        forkJoinPool.shutdown();
        try {
            forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping("/hello")
    public Map index(@RequestParam("userId") Integer userId) {
        /**
         * Have the thread pool issues
         */
//        String before = Thread.currentThread().getName()+"->"+ currentUser.get();
//        currentUser.set(userId);
//        String after = Thread.currentThread().getName() +"after ->"+currentUser.get();
//        Map map = new HashMap();
//        map.put("before",before);
//        map.put("after",after);
        String before = Thread.currentThread().getName() + "->" + currentUser.get();
        currentUser.set(userId);
        try {
            String after = Thread.currentThread().getName() + "after ->" + currentUser.get();
            Map map = new HashMap();
            map.put("before", before);
            map.put("after", after);
            log.info(map.values().toString());
            return map;
        } finally {
            currentUser.remove();
        }

    }

}
