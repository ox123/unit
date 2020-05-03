package org.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioDiscardClient {
    public static void main(String[] args) {
        new NioDiscardClient().startCLient();
    }

    public void startCLient() {
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(8888));
            socketChannel.configureBlocking(false);
            while (!socketChannel.finishConnect()) {
                System.out.println("client connect success");
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                byteBuffer.put("hello world".getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                socketChannel.shutdownOutput();
                socketChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
