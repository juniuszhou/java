package main.java.HadoopUsage;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;



public class RunViaTool extends Configured implements Tool {
    public class WCoMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        @Override
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {

    /*
     * 将行内容全部转换为小写格式.
     */
            String line_lc = value.toString().toLowerCase();
            String before = null;

    /*
     *  将行拆分成单词
     *  并且key是前一个单词加上后一个单词
     *  value 是 1
     */
            for (String word : line_lc.split("\\W+")) { //循环行内容,按照空格进行分割单词
                if (word.length() > 0) {
                    if (before != null) { //如果前词不为空,则写入上下文(第一次前词一定是空,直接跳到下面的before = word)
                        context.write(new Text(before + "," + word), new IntWritable(1));
                    }
                    before = word; //将现词赋值给前词
                }
            }
        }
    }

    public class WCoReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {

            int wordCount = 0;
            for (IntWritable value : values) {
                wordCount += value.get(); //单纯计算word count
            }
            context.write(key, new IntWritable(wordCount));
        }
    }

    @Override
    public int run(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.printf("Usage: hadoop jar wco.WCo <input> <output>\n");
            return -1;
        }

        Job job = new Job(getConf());
        job.setJarByClass(RunViaTool.class);
        job.setJobName("Word Co Occurrence");

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(WCoMapper.class);
        job.setReducerClass(WCoReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Configuration(), new RunViaTool(), args);
        System.exit(exitCode);
    }
}

