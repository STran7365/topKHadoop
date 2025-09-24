package edu.cs.utexas.HadoopEx;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DelayMapper extends Mapper<Object, Text, Text, IntWritable> {

	// Create a counter and initialize with 0
	private IntWritable delay = new IntWritable(0);
	// Create a hadoop text object to store words
	private Text word = new Text();

	public void map(Object key, Text value, Context context) 
			throws IOException, InterruptedException {
		
		String data[] = value.toString().split(",");
		word.set(data[4]);

        try {
            if (Integer.parseInt(data[11]) > 0)
                delay.set(1);
        } catch (Exception e){}

        context.write(word, delay);
			
	}
}