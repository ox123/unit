package org.java.java8.stream;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StreamTest {
    static class AA {

    }

    public static void main(String[] args) {
        File f = new File("D:\\code\\project\\unit\\src\\main\\java\\org\\java\\java8\\stream\\StreamTest.java");
        System.out.println(f.getAbsolutePath());
        int num = 2;
        switch (num) {
            case 1:
                ++num;
            case 2:
                ++num;
            case 3:
                ++num;
            default:
                ++num;
                break;
        }
        System.out.println(num);

        int arr1[] = {0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1};
        int max = Arrays.stream(arr1).max().getAsInt();
        int min = Arrays.stream(arr1).min().getAsInt();
        int sum1 = Arrays.stream(arr1).sum();
        System.out.println(sum1);
        System.out.println(max + "\t\t" + min);

        IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            System.out.println("----->" + i);
        });
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

        List<Integer> list = Arrays.asList(1, 2, 30);
        list.parallelStream();
        list.stream().forEach((item) -> System.out.println(item));
    }
}
