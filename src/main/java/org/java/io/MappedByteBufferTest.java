package org.java.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class MappedByteBufferTest {
    public static void main(String[] args) {
        File file = new File("D:/ting_log.txt");
        long length = file.length();
        byte[] bytes = new byte[(int) length];
        long start = System.currentTimeMillis();
        try {
            MappedByteBuffer mbb = new RandomAccessFile(file, "r")
                    .getChannel()
                    .map(FileChannel.MapMode.READ_ONLY, 0, length);
            for (int i = 0; i < length; i++) {
                byte b = mbb.get();
                bytes[i] = b;
            }
            Scanner scanner = new Scanner(new ByteArrayInputStream(bytes));
            while (scanner.hasNext()){

            }
            long end = System.currentTimeMillis();
            System.out.println(end-start);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
