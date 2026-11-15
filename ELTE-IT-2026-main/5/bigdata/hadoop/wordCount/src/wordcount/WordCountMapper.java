package wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// Тип параметров:
// Тип ключа входной пары 1
// Тип значения входной пары 2
// Тип выходной пары, ключ 3
// Тип значения выходной пары 4
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	// мы повторно используем переменные для повышения эффективности
	private Text key = new Text("");
	private IntWritable value = new IntWritable(1); 
	// он не использует обычные типы Java
	
	// ikey type = первый параметр типа класса
	// тип ivalue = второй параметр типа класса
	// ivalue содержит входное значение (в нашем случае — строку файла)
	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String[] words = ivalue.toString().split(" ");
		// мы разделяем строку по пробелам
		for (String word : words) {
			key.set(word); // пусть слово будет ключевым
			context.write(key, value); // создать и передать пару ключ-значение
		}
	}
}
