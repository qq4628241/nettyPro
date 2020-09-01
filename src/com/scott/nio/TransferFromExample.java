package com.scott.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * @ClassName TransferFromExample
 * @Description
 * @Company 逸驰云动
 * @Author edz
 * @Date 2020/9/1 11:15 AM
 * @Version 1.0
 **/
public class TransferFromExample {

  public static void main(String[] args) throws Exception {
    FileInputStream fileInputStream = new FileInputStream("1.txt");
    FileOutputStream fileOutputStream = new FileOutputStream("2.txt");

    FileChannel sourceChannel = fileInputStream.getChannel();
    FileChannel destinationChannel = fileOutputStream.getChannel();

    destinationChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

    sourceChannel.close();
    destinationChannel.close();
    fileInputStream.close();
    fileOutputStream.close();

  }

  //多文件合并
  private static void multipleFileMerge(File combfile, List<String> fileArray) throws Exception {

    FileChannel mFileChannel = new FileOutputStream(combfile, true).getChannel();
    FileChannel inFileChannel;

    for (String infile : fileArray) {
      File fin = new File(infile);
      inFileChannel = new FileInputStream(fin).getChannel();
      inFileChannel.transferTo(0, inFileChannel.size(), mFileChannel);
      inFileChannel.close();

    }
    mFileChannel.close();
  }
}
