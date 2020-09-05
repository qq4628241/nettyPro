package com.scott.wechat;

import com.scott.wechat.server.GroupChatServer;

/**
 * @ClassName WeChatServerStart
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/9/4 5:57 PM
 * @Version 1.0
 **/
public class WeChatServerStart {

  public static void main(String[] args) {
    //创建服务器对象
    GroupChatServer groupChatServer = new GroupChatServer();
    groupChatServer.listen();
  }
}
