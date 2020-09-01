package com.scott.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName BIOServer
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/8/26 3:49 PM
 * @Version 1.0
 *
 * 使用telnet作为客户端连接服务器做测试
 **/
public class BIOServer {
    public static void main(String[] args) throws IOException {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了");

        while(true){

            //监听，等待客户端连接
            System.out.printf("主线程的名字:%s, ID:%s", Thread.currentThread().getName(), Thread.currentThread().getId());
            System.out.println("等待连接。。。");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            //创建一个线程,与之通讯
            newCachedThreadPool.execute(() -> {
                //可以和客户端通讯
                handler(socket);
            });
        }

    }

    public static void handler(Socket socket){
        try {
            byte[] bytes = new byte[1024];
            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            while(true){
                System.out.printf("线程的名字:%s, ID:%s", Thread.currentThread().getName(), Thread.currentThread().getId());
                System.out.println("等待读取。。。");
                int read = inputStream.read(bytes);
                if(-1 != read){
                    //输出客户端发出的数据
                    System.out.println(new String(bytes,0, read));

                }else{
                    break;
                }
            }

        } catch(Exception e){

        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
