package org.java.map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {

//        String[] strArr = new String[]{"a", "b", "c"};
//        List<String> list = Arrays.asList(strArr);
//        Stream<String> stream = list.stream();
//        Stream<String> stream1 = Arrays.stream(strArr);
//        System.out.println(stream.count());
//
//        Stream<String> distinct = stream.distinct();
//        System.out.println(Arrays.toString(distinct.toArray()));
//        System.out.println(stream.filter(str->str.isEmpty()).count());

//        for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
//            System.out.println( filter.value() );
//        }
//
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("aa", "cc");
        map.forEach((key, val) -> {
            System.out.println(key + "---" + val);
        });
//        System.out.println(map.getOrDefault("dd", "eee"));
        int a = 2, b = 4;
//        map.computeIfAbsent("cc",(a,b)->a+b);
        HashMap<String, Integer> countMap = new HashMap<String, Integer>();
//add a lot of entries
        countMap.put("a", 10);
        countMap.put("c", 40);
        countMap.put("b", 20);
        ValueComparator vc = new ValueComparator(countMap);
        TreeMap<String, Integer> sortedMap = new TreeMap<>(vc);
        sortedMap.putAll(countMap);
        sortedMap.forEach((key,val)->{
            System.out.println(key+"---"+val);
        });
    }
}

class ValueComparator implements Comparator<String> {
    Map<String, Integer> base;

    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    @Override
    public int compare(String o1, String o2) {
        if (base.get(o1) > base.get(o2)) {
            return -1;
        }
        return 1;
    }
}
