package org.java.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class UnsafeTest {
    public static void main(String[] args) {
//        AtomicStampedReference atomicStampedReference = new AtomicStampedReference();
//        Reflection.getCallerClass();
        new AtomicStampedReference<>(1, 0);
    }
}
