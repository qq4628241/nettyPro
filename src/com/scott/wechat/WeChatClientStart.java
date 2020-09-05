package com.scott.wechat;

import com.scott.wechat.client.GroupChatClient;
import java.io.IOException;
import java.util.Scanner;

/**
 * @ClassName WeChatStart
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/9/4 5:48 PM
 * @Version 1.0
 **/
public class WeChatClientStart {

  public static void main(String[] args) throws IOException {
    //启动我们客户端
    GroupChatClient chatClient = new GroupChatClient();

    //启动一个线程
    new Thread(){
      public void run(){
        while (true) {
          chatClient.readInfo();
          try {
            Thread.currentThread().sleep(3000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }.start();

    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String msg = scanner.nextLine();
      chatClient.sendInfo(msg);
    }
  }
}
