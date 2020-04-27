package org.java.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SingleThread {
    private static  ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryImpl(
            "NSScheduledThread"));

    public static void main(String[] args) {

        scheduledExecutorService.scheduleAtFixedRate(()->{
            System.out.println("aaaa");
        },5,10, TimeUnit.SECONDS);
        System.out.println("-----------");
    }
}
