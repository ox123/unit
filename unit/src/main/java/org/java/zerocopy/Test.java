package org.java.zerocopy;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class Test {
    public void testTransferTo() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("from.data", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("to.data", "rw");
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        fromChannel.transferTo(position, count, toChannel);
    }
}
