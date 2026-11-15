package wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountDriver {
	public static void main(String[] args) throws Exception {
		// HDFS хранит здесь временные файлы
		System.setProperty("hadoop.tmp.dir", "c:\\BigData\\tmp\\hadoop-xx");
		
		// каталог, в котором установлен Hadoop
		System.setProperty("hadoop.home.dir", "c:\\BigData\\hadoop-3.3.6");
		
		Configuration conf = new Configuration();
		
		// название должности (не имеет значения)
		Job job = Job.getInstance(conf, "WordCount"); 
		
		job.setJarByClass(wordcount.WordCountDriver.class);
		
		// указание класса картографа
		job.setMapperClass(wordcount.WordCountMapper.class);
		
		// Укажите класс редуктора
		job.setReducerClass(wordcount.WordCountReducer.class);
		
		// указание типа выходной пары ключ-значение
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// указание входных и выходных каталогов
		FileInputFormat.setInputPaths(job, new Path("input")); // входная папка
		FileOutputFormat.setOutputPath(job, new Path("out")); // выходная папка

		// выполняю работу
		if (!job.waitForCompletion(true)) return;
	}
}
