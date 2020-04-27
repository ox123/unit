package org.java.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StreamTest {
    public static void main(String[] args) {
        int n = 10000;
        long result = 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(arr, i + 1);
        }
        long start = System.currentTimeMillis();
//        for (int i = 0; i < n; i++) {
//            result += arr[i];
//        }
        int sum = Arrays.stream(arr).sum();
        IntStream stream = Arrays.stream(arr);
//        stream.collect()
        long end = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println(end - start);

        List<Integer> list = Arrays.asList(1,2,30);
        list.parallelStream();
        list.stream().forEach((item)-> System.out.println(item));
    }
}
