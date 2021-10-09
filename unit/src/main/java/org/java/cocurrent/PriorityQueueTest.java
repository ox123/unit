package org.java.cocurrent;

import java.util.*;

public class PriorityQueueTest {
    public static PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    });
    public int k = 10;
    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
        queue.add(2);
        queue.add(1);
        queue.add(3);
        while (!queue.isEmpty()){
            Integer i = queue.poll();
            System.out.println(i);
        }
//        extracted();

    }

    private static void extracted() {
        Random r = new Random();
        PriorityQueueTest t = new PriorityQueueTest();

        for (int i = 0; i < 100; i++) {
            t.add(r.nextInt(100));
        }
        while (!queue.isEmpty()) {
            Integer i = queue.poll();
            System.out.println(i);
        }
    }

    public void add(Integer i){
        if (queue.size() < k){
            queue.add(i);
        }else{
            int min = queue.peek();
            if (min<i){
                queue.poll();
                queue.add(i);
            }
        }
    }
}
