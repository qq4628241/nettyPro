package com.scott.nio;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering: 将数据写入到buffer时，可以采用buffer数据，依次写入【分散】
 * Gathering: 从buffer读取数据时，可以采用buffer数据，依次读 [聚合]
 * @ClassName ScatteringAndGatheringExample
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/9/1 3:34 PM
 * @Version 1.0
 **/
public class ScatteringAndGatheringExample {

  public static void main(String[] args) throws Exception {
    //使用 ServerSocketChannel 和 ScoketChannel网络
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

    //绑定端口到socket，并启动
    serverSocketChannel.socket().bind(inetSocketAddress);

    //创建buffer数组
    ByteBuffer[] byteBufferAry = new ByteBuffer[2];
    byteBufferAry[0] = ByteBuffer.allocate(5);
    byteBufferAry[1] = ByteBuffer.allocate(3);

    //等待客户端连接
    SocketChannel socketChannel = serverSocketChannel.accept();
    int messageLength = 8; //假定从客户端接收8个字节

    //循环的读取
    while (true) {
      int byteRead = 0;

      while (byteRead < messageLength) {
        long read = socketChannel.read(byteBufferAry);
        byteRead += read;// 累计读取的字节数
        System.out.println("byteRead="+byteRead);

        //使用流打印， 看看当前的这个buffer的position和limit
        Arrays.asList(byteBufferAry).stream()
            .map(p -> "position:" + p.position() + " limit:" + p.limit())
            .forEach(System.out::println);
      }

      //将所有的buffer进行flip
      Arrays.asList(byteBufferAry).forEach(p-> p.flip());

      // 将数据读出显示到客户端
      long byteWrite = 0;
      while(byteWrite < messageLength){
        long write = socketChannel.write(byteBufferAry);
        byteWrite += write;
      }

      //将所有的buffer 进行clear
      Arrays.asList(byteBufferAry).forEach(p -> p.clear());

      System.out.println("byteRead:="+byteRead+" byteWrite:="+byteWrite+" messageLength="+messageLength);
    }

  }
}
