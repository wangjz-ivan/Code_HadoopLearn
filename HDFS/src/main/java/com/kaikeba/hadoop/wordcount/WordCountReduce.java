package com.kaikeba.hadoop.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
    /*
        (hello, 1)
        (hello, 1)
        (hello, 1)
        ...
        (spark, 1)

        key: hello
        value: List(1, 1, 1)
    */
    public void reduce(Text key, Iterable<IntWritable> values,
                          Context context) throws IOException, InterruptedException {
        int sum = 0;

        for (IntWritable count : values) {
            sum += count.get();
        }

        context.write(key, new IntWritable(sum));// 输出最终结果
    };
}