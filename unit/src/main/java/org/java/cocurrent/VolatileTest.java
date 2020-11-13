package org.java.cocurrent;

import java.util.stream.IntStream;

public class VolatileTest {

    public static void main(String[] args) {
        InterestingTest interestingTest = new InterestingTest();
//        new Thread(() -> interestingTest.add()).start();
//        new Thread(() -> interestingTest.compare()).start();
        InterestingTest.reset();
        IntStream.rangeClosed(1, 10000).parallel().forEach(i -> interestingTest.wrong());
        System.out.println(InterestingTest.getCounter());
//        IntStream.rangeClosed(1,3).mapToObj()
    }
}
