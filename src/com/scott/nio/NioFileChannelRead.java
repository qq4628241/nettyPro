package com.scott.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName NioFileChannelRead
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/8/27 10:45 AM
 * @Version 1.0
 **/
public class NioFileChannelRead {
    public static void main(String[] args) {
        try {
            //创建文件的输入流
            File file = new File("/tmp/xx.txt");
            FileInputStream fileInputStream = new FileInputStream(file);

            //通过fileInputStream 获取对应的FileChannel -> 实际类型
            FileChannel channel = fileInputStream.getChannel();

            //创建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

            //将通道的数据读入到buffer中
            channel.read(byteBuffer);

            //将字节转换成string
            byte[] array = byteBuffer.array();
            System.out.println(new String(array));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
