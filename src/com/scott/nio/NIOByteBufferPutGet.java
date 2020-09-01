package com.scott.nio;

import java.nio.ByteBuffer;

/**
 * @ClassName NIOByteBufferPutGet
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/9/1 11:30 AM
 * @Version 1.0
 **/
public class NIOByteBufferPutGet {

  public static void main(String[] args) {
    ByteBuffer byteBuffer = ByteBuffer.allocate(64);

    byteBuffer.putInt(100);
    byteBuffer.putLong(9);
    byteBuffer.putChar('写');

    byteBuffer.putShort((short)7);

    //取出
    byteBuffer.flip();

    System.out.println();

    System.out.println(byteBuffer.getInt());
    System.out.println(byteBuffer.getLong());
    System.out.println(byteBuffer.getChar());
    System.out.println(byteBuffer.getShort());
  }
}
