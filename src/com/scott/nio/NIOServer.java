package com.scott.nio;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName NIOServer
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/9/3 10:41 AM
 * @Version 1.0
 **/
public class NIOServer {

  public static void main(String[] args) throws Exception {
    //创建ServerSocketChannel -> ServerSocket
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

    //得到一个Seletor对象
    Selector selector = Selector.open();

    //绑定一个端口6666, 在服务器监听
    serverSocketChannel.socket().bind(new InetSocketAddress(6666));

    //设置为非阻塞
    serverSocketChannel.configureBlocking(false);

    //把serverSocketChannel注册到selector 关心事件 OP_ACCEPT
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    while (true) {

      //这里我们等待1秒，如果没有事件发生，返回
      if (selector.select(1000) == 0) {
        System.out.println("服务器等待1秒，无连接");
        continue;
      }

      //如果返回的>0
      //1. 如果返回>0, 表示已经获取到关注的事件
      //2. selector。selectedKeys() 返回关注事件的集合
      // 通过selectionKeys返回获取通道
      Set<SelectionKey> selectionKeys = selector.selectedKeys();

      //遍历Set<SelectionKey>, 使用迭代器
      Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

      while (keyIterator.hasNext()) {
        //获取到SelectionKey
        SelectionKey key = keyIterator.next();

        //根据key对应的通道发生的事件做相应处理
        if (key.isAcceptable()) {//如果是OP_ACCEP， 有新的客户端连接
          //该客户端生成一个SocketChannel
          SocketChannel socketChannel = serverSocketChannel.accept();
          //将当前的socketChannel 注册到selector, 关注事件OP_READ,同时给socketChannel关联一个Buffer
          socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
        }

        if (key.isReadable()) {//发生了OP_READ
          //通过key，反响获取到对应channel
          SocketChannel channel = (SocketChannel)key.channel();
          //获取到该channel关联的buffer
          key.attachment();
        }
      }

    }
  }
}
