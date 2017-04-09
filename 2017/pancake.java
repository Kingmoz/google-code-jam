import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.nio.file.Paths;

public class pancake {
    public static void main(String[] args) throws Exception {
        BufferedReader in = scanFile(args[0]);
        int t = Integer.parseInt(in.readLine());
        String[] results = new String[t];

        String line;
        int counter = 1;
        while( (line = in.readLine()) != null) {
            //System.out.println(line);

            results[counter-1] = "Case #" + counter + ": " + countFlip(line);
            System.out.println(results[counter-1]);
            counter ++;
        }
        in.close();

        List<String> lines = Arrays.asList(results);
        Path file = Paths.get("large.out");
        Files.write(file, lines, Charset.forName("UTF-8"));

        return;
    }

    private static BufferedReader scanFile(String fileName) throws Exception{
        return new BufferedReader(new FileReader(fileName));
    }

    private static String countFlip(String line) {
        String[] splitLine = line.split(" ");
        char[] row = splitLine[0].toCharArray();
        int flipNum = Integer.parseInt(splitLine[1]);
        int count = 0;

        for (int i=0; i<row.length; i++) {
            if (row[i] == '-') {
                if (i > row.length - flipNum) {
                    return "IMPOSSIBLE";
                }
                for (int j=i; j<i+flipNum; j++) {
                    row[j] = changeSign(row[j]);
                }
                //System.out.println(String.copyValueOf(row));
                count ++;
            }
        }

        return "" + count;
    }

    private static char changeSign(char curSign) {
        if (curSign == '-')
            return '+';
        if (curSign == '+')
            return '-';
        return '-';
    }
}
