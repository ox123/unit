package org.java.container;

import java.util.HashSet;
import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("aa");
        set.add("bb");
        set.removeIf("aa"::equals);
    }
}
