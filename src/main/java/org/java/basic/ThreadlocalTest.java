package org.java.basic;

public class ThreadlocalTest {
    ThreadLocal<String> t1 = new ThreadLocal<>();
    private String content;

    public String getContent() {
        return t1.get();
    }

    public void setContent(String content) {
        t1.set(content);
    }

    public static void main(String[] args) {
        ThreadlocalTest test = new ThreadlocalTest();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    System.out.println((Thread.currentThread().getName()+"的数据"));
                    System.out.println(Thread.currentThread().getName()+"--------------"+test.getContent());
                }
            };
            thread.setName("线程"+i);
            thread.start();
        }
    }
}
