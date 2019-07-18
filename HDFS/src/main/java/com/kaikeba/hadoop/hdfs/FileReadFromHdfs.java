package com.kaikeba.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

/**
 * 从HDFS读取文件
 * 打包运行jar包 [bruce@node-01 Desktop]$ hadoop jar com.kaikeba.hadoop-1.0-SNAPSHOT.jar  com.kaikeba.hadoop.hdfs.FileReadFromHdfs
 */
public class FileReadFromHdfs {

    public static void main(String[] args) {
        try {
            //
            String srcFile = "hdfs://node-01:9000/data/hdfs01.mp4";
            Configuration conf = new Configuration();

            FileSystem fs = FileSystem.get(URI.create(srcFile),conf);
            FSDataInputStream hdfsInStream = fs.open(new Path(srcFile));

            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("/home/bruce/hdfs01.mp4"));

            IOUtils.copyBytes(hdfsInStream, outputStream, 4096, true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
