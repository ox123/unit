package org.java.io.reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class EchoHandler implements Runnable {

    SelectionKey sk;
    SocketChannel socketChannel;

    EchoHandler(Selector selector, SocketChannel socketChannel) {
        try {
//            selector = sk;
            socketChannel = socketChannel;
            socketChannel.configureBlocking(false);
            this.sk = socketChannel.register(selector, 0);
            this.sk.attach(this);
            this.sk.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}
