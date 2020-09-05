package com.scott.wechat.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @ClassName GroupChatClient
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/9/4 5:35 PM
 * @Version 1.0
 **/
public class GroupChatClient {

  //定义相关的属性
  private final static int PORT = 6667;
  private final static String HOST = "127.0.0.1";

  private Selector selector;

  private SocketChannel socketChannel;

  private String username;

  public GroupChatClient() throws IOException {
    selector = Selector.open();

    //连接服务器
    socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
    //设置非阻塞
    socketChannel.configureBlocking(false);
    //将channel 注册到selector
    socketChannel.register(selector, SelectionKey.OP_READ);
    //得到username
    username = socketChannel.getLocalAddress().toString().substring(1);

    System.out.println(username + " 准备好了...");
  }

  // 向服务器发送消息
  public void sendInfo(String info) {
    info = username + "说" + info;
    try {
      socketChannel.write(ByteBuffer.wrap(info.getBytes()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //读取从服务器发送过来的消息
  public void readInfo(){

    try {
      int readChannels = selector.select();//阻塞在这里
      if (readChannels>0) {//有可用的通道
        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        while (iterator.hasNext()) {
          SelectionKey key = iterator.next();
          if (key.isReadable()) {
            //得到相关的通道
            SocketChannel sc = (SocketChannel) key.channel();
            //得到一个Buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //读取
            sc.read(buffer);
            //把得到缓冲区的数据专程字符串
            String msg = new String(buffer.array());

            System.out.println(msg.trim());
          }
        }
      }else{
        System.out.println("没有可用的通道");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
