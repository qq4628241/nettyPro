package com.scott.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName NioFileChannel
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/8/27 10:32 AM
 * @Version 1.0
 **/
public class NioFileChannelWrite {
    public static void main(String[] args) {
        String str = "hello world";


        try {
            //创建一个输出流-》channel
            FileOutputStream fileOutputStream = new FileOutputStream("/tmp/xx.txt");

            //通过fileOutputStream 获取对应的 FileChannel
            //这个 fileChannel 真实类型是 FileChannelImpl
            FileChannel fileChannel = fileOutputStream.getChannel();

            //创建一个缓冲区 ByteBuffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            //将str 放入 byteBuffer
            byteBuffer.put(str.getBytes());

            //对byteBuffer 进行flip
            byteBuffer.flip();

            //将byteBuffer 数据写入 fileChannel
            fileChannel.write(byteBuffer);

            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
