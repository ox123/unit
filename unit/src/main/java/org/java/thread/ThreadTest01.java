package org.java.thread;

public class ThreadTest01 {
    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                Thread t = Thread.currentThread();
                System.out.println("TestName:"+t.getName());
            }
        };
        Thread thread = new Thread(task);
        thread.setName("test-thread-1");
        thread.setDaemon(true); //后台线程，做业务的线程应该是前台线程；后台线程：如刷新缓存
        thread.start();
    }
}
