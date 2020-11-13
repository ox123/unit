package org.java.map;

import java.util.PriorityQueue;

public class QueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq =
                new PriorityQueue<Integer>(
//                        10, (a, b) -> {
//                    return a - b;}
                );
        pq.add(3);
        pq.add(0);
        pq.add(1);
        pq.forEach(item -> {
            System.out.println(item);
        });
        System.out.println(pq);
    }
}
