package edu.cs.utexas.HadoopEx;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DelayReducer extends  Reducer<Text, IntWritable, Text, IntWritable> {

   public void reduce(Text text, Iterable<IntWritable> values, Context context)
           throws IOException, InterruptedException {
	   
        int delay = 0;
        int flights = 0;

        for (IntWritable value : values) {
            delay += value.get();
            flights++;
        }

        int ratio = (flights > 0) ? (delay / flights) : 0;
       
        context.write(text, new IntWritable(ratio));
   }
}