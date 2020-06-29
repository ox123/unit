package org.java.io.nio;

import java.io.IOException;
import java.net.Socket;

public class SocketTest {
    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
//            socket.
            socket.getInputStream().read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
