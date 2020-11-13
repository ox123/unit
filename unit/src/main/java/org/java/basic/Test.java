package org.java.basic;

public class Test {
    public static final int capacity = 1 << 30;

    public static void main(String[] args) {
        System.out.println(capacity);
        System.out.println(tableSizeFor(31));
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
