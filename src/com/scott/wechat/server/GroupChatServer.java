package com.scott.wechat.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName GroupChatServer
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/9/3 5:43 PM
 * @Version 1.0
 **/
public class GroupChatServer {
  //定义属性
  private Selector selector;

  private ServerSocketChannel listenChannel;

  private static final int PORT = 6667;

  public GroupChatServer() {
    try {
      //得到选择器
      selector = Selector.open();
      //ServerSocketChannel
      listenChannel = ServerSocketChannel.open();
      //绑定端口
      listenChannel.socket().bind(new InetSocketAddress(PORT));
      //设置非阻塞模式
      listenChannel.configureBlocking(false);
      //将listenerChannel 注册到selector
      listenChannel.register(selector, SelectionKey.OP_ACCEPT);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //监听
  public void listen(){
    try {
      //循环处理
      while (true) {
        int count = selector.select(3000);
        if(0<count){//有事件处理
          //遍历得到selectionKeys
          Set<SelectionKey> selectionKeys = selector.selectedKeys();
          Iterator<SelectionKey> iterator = selectionKeys.iterator();
          while (iterator.hasNext()) {
            //取出selectionKey
            SelectionKey key = iterator.next();

            //监听连接事件accept
            if (key.isAcceptable()) {
              SocketChannel sc = listenChannel.accept();
              sc.configureBlocking(false);
              //将该sc注册到selector
              sc.register(selector, SelectionKey.OP_READ);
              //提示
              System.out.println(sc.getRemoteAddress()+"上线了");
            }

            //监听读事件read
            if (key.isReadable()) {//通道发送read事件，即通道为可读状态
              //处理读
            }

            //手动从集合中移除key，避免重复处理
            iterator.remove();
          }
        }else{
          System.out.println("请等待");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //读取客户端消息
  private void readData(SelectionKey key){
    //定义一个socketChannel
    SocketChannel channel = null;
    try {
      channel = (SocketChannel)key.channel();

      //创建Buffer
      ByteBuffer buffer = ByteBuffer.allocate(1024);

      int read = channel.read(buffer);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
