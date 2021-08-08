package org.java.basic;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {
    public static final int capacity = 1 << 30;

    public static void main(String[] args) {
        System.out.println(capacity);
        System.out.println(tableSizeFor(31));
//        test();
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean mben :
                garbageCollectorMXBeans) {
            System.out.println("--->"+mben.getCollectionCount());
        }
//        testString();
    }

    private static void test() {
        long begin = System.currentTimeMillis();
        List<String> collect = IntStream.rangeClosed(1, 10000000).mapToObj(i -> String.valueOf(i).intern()).collect(Collectors.toList());
        System.out.println(System.currentTimeMillis() - begin);

    }

    private static void testString() {
        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);
        String aa = "1";
        String bb = new String("1");
        System.out.println(aa == bb);
        System.out.println(aa.equals(bb));
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= capacity ? capacity : n + 1);
    }
}
