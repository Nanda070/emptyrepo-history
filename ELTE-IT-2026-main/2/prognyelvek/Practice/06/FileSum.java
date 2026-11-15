import java.io.*;

public class FileSum {
    public static void main(String[] args) /*throws FileNotFoundException - nem túl elegáns inkább try catchet használjunk */ {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("in.txt"));
            String line = null;
            while (null != (line = br.readLine())) {
                String[] num = line.split(",");
                long sum = 0;
                for (int i = 0; i < num.length; ++i) {
                    sum += Long.parseLong(num[i]);
                }
                System.out.println("Sum: " + sum);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find in.txt.");
        } catch (IllegalArgumentException e) {
            System.out.println("Bad arguments.");
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (Exception e) {
            System.out.println("Exception");
            // порядок важен, более конкретное исключение должно быть перехвачено раньше
        } finally {
            // наконец всегда работает, полезно, например. в управлении файлами, чтобы закрыть файл
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Couldn't close file.");
            }

        }
        
        
    }
}