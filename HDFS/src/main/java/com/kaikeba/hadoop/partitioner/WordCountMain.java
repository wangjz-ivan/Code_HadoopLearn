package com.kaikeba.hadoop.partitioner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class WordCountMain {
    public static void main(String[] args) throws IOException,
            ClassNotFoundException, InterruptedException {
        if (args.length != 2 || args == null) {
            System.out.println("please input Path!");
            System.exit(0);
        }

        Configuration configuration = new Configuration();
        configuration.set("mapreduce.job.jar","/home/bruce/project/kkbhdp01/target/com.kaikeba.hadoop-1.0-SNAPSHOT.jar");

        Job job = Job.getInstance(configuration, WordCountMain.class.getSimpleName());

        // 打jar包
        job.setJarByClass(WordCountMain.class);

        // 通过job设置输入/输出格式
        //job.setInputFormatClass(TextInputFormat.class);
        //job.setOutputFormatClass(TextOutputFormat.class);

        // 设置输入/输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 设置处理Map/Reduce阶段的类
        job.setMapperClass(WordCountMap.class);
        //map combine
        //job.setCombinerClass(WordCountReduce.class);
        job.setReducerClass(WordCountReduce.class);
        //如果map、reduce的输出的kv对类型一致，直接设置reduce的输出的kv对就行；如果不一样，需要分别设置map, reduce的输出的kv类型
        //job.setMapOutputKeyClass(.class)
        // 设置最终输出key/value的类型m
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setPartitionerClass(CustomPartitioner.class);
        job.setNumReduceTasks(4);

        // 提交作业
        job.waitForCompletion(true);

    }
}
