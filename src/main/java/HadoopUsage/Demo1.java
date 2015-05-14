package HadoopUsage;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Demo1 {}
    /*
extends Configured implements Tool{


    public static void main(String[] args) throws Exception {
        System.exit(ToolRunner.run(new Demo1(), args));

    }

    public static class DemoMap extends Mapper<LongWritable, Text, Text, IntWritable>{

        @Override
        protected void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {

            String line = value.toString();
            String[] splitdata = line.split("\\,");
            String contry = splitdata[4];
            System.out.println("country:"+contry);
            if (contry.trim().equals("\"COUNTRY\"")) {
                return;
            } else {
                context.write(new Text(contry), new IntWritable(1));
            }
        }

    }

    public static class DemoReduce extends Reducer<Text, IntWritable, Text, IntWritable>{

        @Override
        protected void reduce(Text arg0, Iterable<IntWritable> arg1,Context context)
                throws IOException, InterruptedException {
            System.out.println("reduce");
            int sum = 0;
            for (IntWritable num : arg1) {
                sum += num.get();
            }
            context.write(arg0, new IntWritable(sum));
        }

    }
    @Override
    public int run(String[] arg0) throws Exception {
        Configuration conf = getConf();


        Job job = Job.getInstance(conf, "demo1");
        String inputPath = "input";
        String outputPath = "output";

        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        job.setJarByClass(Demo1.class);
        job.setMapperClass(DemoMap.class);
        job.setReducerClass(DemoReduce.class);
        job.setCombinerClass(DemoReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        return job.waitForCompletion(true)?0:1;
    }
    */
