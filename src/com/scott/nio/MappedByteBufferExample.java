package com.scott.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * @ClassName MappedByteBufferTest
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/9/1 2:31 PM
 * @Version 1.0
 **/
public class MappedByteBufferExample {

  public static void main(String[] args) throws Exception {

    //MappedByteBuffer 可让文件直接在内存(堆外内存)修改，操作系统不需要拷贝一次
    //MappedByteBuffer mappedByteBuffer = new MappedByteBuffer("1.txt","rw");

    RandomAccessFile rw = new RandomAccessFile("2.txt", "rw");
    FileChannel channel = rw.getChannel();

    /**
     * 1. 参数1 FileChannel.MapModel.READ_WRITE 使用的读写模式
     * 2. 参数2 0： 可以直接修改的起始位置
     * 3. 参数3 5: 是映射到内存的大小，即将1.txt的多少字节映射到内存
     * 可以直接修改的范围就是0-5
     */
    MappedByteBuffer mappedByteBuffer = channel.map(MapMode.READ_WRITE, 0, 5);

    mappedByteBuffer.put(0, (byte) 'H');

    mappedByteBuffer.put(3, (byte) '7');

    //java.lang.IndexOutOfBoundsException
    //mappedByteBuffer.put(5, (byte) '7');
    rw.close();
    System.out.println("修改成功");
  }
}
