import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class OddEvenFileSplitter implements FileSplitter {
    public static void main(final String[] args) {

        SplitConfig config = new SplitConfig() {
            public String getSourceFilePath() {
                return args[0];
            }

            public String getOddLinesFilePath() {
                return args[1];
            }

            public String getEvenLinesFilePath() {
                return args[2];
            }
        };

        OddEvenFileSplitter splitter = new OddEvenFileSplitter();
        splitter.splitFile(config);
    }


    public void splitFile(FileSplitter.SplitConfig config) {


        int j = 0;
        ArrayList<String> odd = new ArrayList<String>();
        ArrayList<String> even = new ArrayList<String>();
        try {
            String[] str = new Scanner(new File(config.getSourceFilePath())).useDelimiter("\\Z").next().split("\\n");


            for (String s : str) {
                j++;

                if (j % 2 == 1) {
                    odd.add(s);
                } else {
                    even.add(s);
                }
            }
        } catch (FileNotFoundException r) {
            System.err.println("Ошибка.Файл не найден.");
        }


        try {
            OutputStream f = new FileOutputStream(config.getOddLinesFilePath(), true);
            OutputStream f1 = new FileOutputStream(config.getEvenLinesFilePath(), true);
            OutputStreamWriter writer = new OutputStreamWriter(f);
            OutputStreamWriter writer1 = new OutputStreamWriter(f1);
            BufferedWriter out = new BufferedWriter(writer);
            BufferedWriter out1 = new BufferedWriter(writer1);
            for (int i = 0; i < odd.size(); i++) {
                out.write("\n" + odd.get(i));
                out.flush();
            }
            for (int i = 0; i < even.size(); i++) {
                out1.write("\n" + even.get(i));
                out1.flush();
            }
        } catch (IOException e) {
            System.err.println("Ошибка");
        }
    }

}