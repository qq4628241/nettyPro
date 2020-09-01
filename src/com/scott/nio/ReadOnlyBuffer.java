package com.scott.nio;

import java.nio.ByteBuffer;

/**
 * @ClassName ReadOnlyBuffer
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/9/1 2:19 PM
 * @Version 1.0
 **/
public class ReadOnlyBuffer {

  public static void main(String[] args) {
    ByteBuffer byteBuffer = ByteBuffer.allocate(64);

    for (int i = 0; i < 64; i++) {
      byteBuffer.put((byte) i);
    }

    byteBuffer.flip();
    byteBuffer.clear();

    ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
    System.out.println(readOnlyBuffer.getClass());

    //read
    while (readOnlyBuffer.hasRemaining()) {
      System.out.println(readOnlyBuffer.get());
    }

    //throw java.nio.ReadOnlyBufferException
    readOnlyBuffer.put((byte) 1);

  }
}
