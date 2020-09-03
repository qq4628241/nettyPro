package com.scott.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * @ClassName FileChannelExample
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/9/1 10:17 AM
 * @Version 1.0
 **/
public class CopyFileChannelExample {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("1.txt");

        FileChannel channelInput = fileInputStream.getChannel();

        //fileOutputStream得appendable->true指定了写入文件是追加而不是覆盖
        //FileOutputStream fileOutputStream = new FileOutputStream("2.txt", true);
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");

        FileChannel channelOutput = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(3);


        while (true) {

            byteBuffer.clear();

            int read = channelInput.read(byteBuffer);
            System.out.printf("read:%d\n", read);
            if (-1 == read) {
                break;
            }

            byteBuffer.flip();

            //channelOutput.position(channelOutput.size());
            channelOutput.write(byteBuffer);
            //position可以控制从文件的什么位置开始读或者写，但是无法控制是追加还是覆盖文件
//            channelOutput.write(byteBuffer, channelOutput.size());

            //每次都从0的位置开始写，一次写3个字符，所以文件最后只有rld
//            channelOutput.write(byteBuffer, 0);

        }

        // 关闭fileArray
        fileInputStream.close();
        fileOutputStream.close();


    }


}
