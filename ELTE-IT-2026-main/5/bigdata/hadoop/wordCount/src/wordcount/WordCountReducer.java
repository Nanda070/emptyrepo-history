package wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// На этапе перетасовки идентичные пары ключ-значение отправляются в редуктор.
// Тип параметров:
// 1. Тип входной пары ключей
// 2. тип значения входной пары
// Тип выходной пары, ключ 3
// Тип значения выходной пары 4
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	// _key — общий ключ, значения находятся в коллекции значений
	// Тип _key — это первый параметр типа класса.
	// Параметр типа значений — это 2-й параметр типа класса.
	public void reduce(Text _key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int count = 0;
		
		for (IntWritable entryValue : values) {
			count += entryValue.get(); // поэтому редуктор также можно использовать в качестве объединителя
		}
		
		context.write(_key, new IntWritable(count)); // прохожу пару
	}
}
