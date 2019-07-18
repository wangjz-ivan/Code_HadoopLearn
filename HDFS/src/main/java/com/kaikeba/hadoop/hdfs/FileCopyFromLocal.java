package com.kaikeba.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;

/**
 * 将本地文件系统的文件通过java-API写入到HDFS文件
 */
public class FileCopyFromLocal {

    public static void main(String[] args){

        String source="/home/bruce/hdfs01.mp4"; //linux中的文件路徑,demo存在一定数据

        //先确保/data目录存在
        String destination="hdfs://node-01:9000/data/hdfs01.mp4";//HDFS的路徑

        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(source));
            //HDFS读写的配置文件
            Configuration conf = new Configuration();

            FileSystem fs = FileSystem.get(URI.create(destination),conf);

            //调用Filesystem的create方法返回的是FSDataOutputStream对象
            //该对象不允许在文件中定位，因为HDFS只允许一个已打开的文件顺序写入或追加
            OutputStream out = fs.create(new Path(destination));

            IOUtils.copyBytes(in, out, 4096, true);
        } catch (FileNotFoundException e) {
            System.out.println("exception");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("exception1");
            e.printStackTrace();
        }

    }
}
