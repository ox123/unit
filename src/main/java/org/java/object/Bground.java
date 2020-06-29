package org.java.object;

public class Bground extends Thread {
    public static void main(String[] args) {
        Bground gb = new Bground();
//        gb.run();
        System.out.println(System.getenv("user.home"));
    }

    @Override
    public void run() {
        System.out.println("-----");
    }

    public void start(){
        for(int i=0;i<10;i++){
            System.out.println("Value of i = "+i);
        }
    }
}
