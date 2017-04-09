import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.nio.file.Paths;

public class TidyNumbers {
    public static void main(String[] args) throws Exception {
        BufferedReader in = scanFile(args[0]);
        int t = Integer.parseInt(in.readLine());
        String[] results = new String[t];

        String line;
        int counter = 1;
        while( (line = in.readLine()) != null) {
            //System.out.println(line);

            results[counter-1] = "Case #" + counter + ": " + tidy(line);
            System.out.println(results[counter-1]);
            counter ++;
        }
        in.close();

        List<String> lines = Arrays.asList(results);
        Path file = Paths.get("holder.out");
        Files.write(file, lines, Charset.forName("UTF-8"));

        return;
    }

    private static BufferedReader scanFile(String fileName) throws Exception{
        return new BufferedReader(new FileReader(fileName));
    }

    private static String tidy(String number) {
        char[] c = number.toCharArray();
        int repeatIdx = -1;

        for (int i=1; i<c.length; i++) {
            int prev = (int)c[i - 1];
            int curr = (int)c[i];

            if (curr == prev) {
                repeatIdx = repeatIdx == -1 ? i - 1 : repeatIdx;
            } else {
                if (curr < prev) {
                    // System.out.println(c[i] + ": " + curr + " " + c[i - 1] + ": " + prev);
                    // System.out.println(" " + (char)(prev));
                    int index = repeatIdx == -1 ? i - 1 : repeatIdx;

                    c[index] = (char)(prev - 1);
                    changeAllToNine(c, index + 1);

                    if (c[0] == '0') {
                        return new String(c, 1, c.length - 1);
                    } else {
                        return String.copyValueOf(c);
                    }
                }
                repeatIdx = -1;
            }
        }

        return String.copyValueOf(c);
    }

    private static void changeAllToNine(char[] array, int idx) {
        for (int i=idx; i<array.length; i++) {
            array[i] = '9';
        }
    }
}
