package wordmean;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class WordMeanReducer extends Reducer<NullWritable, IntWritable, NullWritable, DoubleWritable> {
	// обратите внимание на сигнатуру типа сокращения, тип _key должен быть таким же, как первый параметр типа класса
	// и параметр типа итерируемого объекта со вторым параметром типа класса
	public void reduce(NullWritable _key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int wordCount = 0;
		int wordLengthSum = 0;
		
		for (IntWritable entryValue : values) {
			wordCount++;
			wordLengthSum += entryValue.get();
		}
		
		context.write(NullWritable.get(), new DoubleWritable((double) wordLengthSum / wordCount));
	}
}