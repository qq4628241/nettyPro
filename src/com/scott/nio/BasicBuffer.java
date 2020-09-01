package com.scott.nio;

import java.nio.IntBuffer;

/**
 * @ClassName BasicBuffer
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/8/26 5:00 PM
 * @Version 1.0
 **/
public class BasicBuffer {
    public static void main(String[] args) {
        //举例说明Buffer的使用
        //创建一个Buffer,大小为5, 即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //向buffer存放数据
//        intBuffer.put(10);
//        intBuffer.put(11);
//        intBuffer.put(12);
//        intBuffer.put(13);
//        intBuffer.put(14);
        for (int i=0; i<intBuffer.capacity(); i++){
            intBuffer.put(i*2);
        }

        //如何从buffer读取数据
        //将buffer转换，读写切换
        intBuffer.flip();

        //intBuffer.position(1);//从第1个位置开始读
        //intBuffer.limit(3);//读取最多数组3的未知

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
