package org.java.list;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedAndArrayList {
    public static void main(String[] args) {
        LinkedList<String> programmingLanguages = new LinkedList<>();

        programmingLanguages.add("Assembly");
        programmingLanguages.add("Fortran");
        programmingLanguages.add("Pascal");
        programmingLanguages.add("C");
        programmingLanguages.add("C++");
        programmingLanguages.add("Java");
        programmingLanguages.add("C#");
        programmingLanguages.add("Kotlin");
        programmingLanguages.removeIf(p -> p.startsWith("C"));
        programmingLanguages.forEach(item -> {
            System.out.println(item);
        });
        System.out.println("-----------");
        Iterator<String> iterator = programmingLanguages.iterator();
        iterator.forEachRemaining(name -> {
            System.out.println(name);
        });
        System.out.println("-------------");
        Iterator<String> stringIterator = programmingLanguages.descendingIterator();
        stringIterator.forEachRemaining(name -> {
            System.out.println(name);
        });
    }
}
