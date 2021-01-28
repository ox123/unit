package org.redis;

import org.utils.Constant;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

public class RedisTest {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool(Constant.ADDRESS, 6379);

//        Jedis jedis = new Jedis(Constant.ADDRESS, 6379);

//        jedis.set("a","1");
//        jedis.set("b","2");
        new Thread(() -> {
            Jedis jedis = jedisPool.getResource();
            jedis.auth(Constant.REDIS_PASSWORD);
            for (int i = 0; i < 1000; i++) {
                String result = jedis.get("a");
                if (!result.equals("1")) {
                    System.out.println("Expect a to be 1 but fount " + result);
                    return;
                }
            }
        }).start();
        new Thread(() -> {
            Jedis jedis = jedisPool.getResource();
            jedis.auth(Constant.REDIS_PASSWORD);
            for (int i = 0; i < 1000; i++) {
                String result = jedis.get("b");
                if (!result.equals("2")) {
                    System.out.println("Expect b to be 2 but fount " + result);
                    return;
                }
            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (jedisPool != null) {
                jedisPool.close();
            }
        }));
    }
}
