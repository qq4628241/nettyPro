package com.scott.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @ClassName NIOCleint
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/9/3 2:22 PM
 * @Version 1.0
 **/
public class NIOCleint {

  public static void main(String[] args) throws Exception {
    //得到一个网络通道
    SocketChannel socketChannel = SocketChannel.open();

    //设置非阻塞模式
    socketChannel.configureBlocking(false);

    //提供服务器ip和端口
    InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

    //连接服务器，如果连接失败
    if (!socketChannel.connect(inetSocketAddress)) {
      while (!socketChannel.finishConnect()) {//如果服务端没有开启，则这里会发生异常java.net.ConnectException: Connection refused
        System.out.println("因为连接服务器需要事件，客户端不会阻塞，可以做其他工作");
      }
    }

    //连接服务器，如果连接成功，就发送数据
    String str = "HelloWorld";

    //分配Buffer
    ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());

    //发送数据，将buffer数据写入channel
    socketChannel.write(buffer);

    System.in.read();
  }

}
