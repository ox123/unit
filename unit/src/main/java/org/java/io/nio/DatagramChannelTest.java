package org.java.io.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

public class DatagramChannelTest {
    public static void main(String[] args) {
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            FileChannel channel = new RandomAccessFile(new File(""), "rw+").getChannel();
//            SelectionKey
            Selector selector = Selector.open();
            int select = selector.select();
            selector.selectNow();
            if (select > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey key :
                        selectionKeys) {
//                    key.
                    if (key.isAcceptable()) {

                    }
                }
            }
            int i = datagramChannel.validOps();
            datagramChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
