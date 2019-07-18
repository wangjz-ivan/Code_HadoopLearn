package com.kaikeba.hadoop.partitioner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

public class CustomPartitioner extends Partitioner<Text, IntWritable> {
    public static HashMap<String, Integer> dict = new HashMap<String, Integer>();

    static{
        dict.put("Dear", 0);
        dict.put("Bear", 1);
        dict.put("River", 2);
        dict.put("Car", 3);
    }

    public int getPartition(Text text, IntWritable intWritable, int i) {
        //
        int partitionIndex = dict.get(text.toString());
        return partitionIndex;
    }
}
