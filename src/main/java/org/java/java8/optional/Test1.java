package org.java.java8.optional;

public class Test1 {
    public static void main(String[] args) {
        // 4, 7+4%7
        int startMs = 4;
        int tickMs = 7;
        System.out.println(startMs - (startMs % tickMs));

        System.out.println(System.nanoTime());
//        System.out.println(Arrays.toString(((URLClassLoader)Test1.class.getClassLoader()).getURLs()));
//        Optional<Date> date = Optional.of(new Date());
//        System.out.println(date.get());
//        Base base = new Base();
//        base.test();
        String a = "helloworld1";
        final String b = "helloworld";
        String c = "helloworld";
        String d = b + 2;
        String e = d + 2;
        System.out.println((a == d));
        System.out.println((a == e));
    }
}

class  Base{
    {
        System.out.println("{}");
    }
    static {
        System.out.println("static field");
    }

    public static void test(){
        System.out.println("static method");
    }
}
