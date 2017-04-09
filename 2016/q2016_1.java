import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.nio.file.Paths;

public class q2016_1 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = scanFile(args[0]);
        int t = Integer.parseInt(in.readLine());
        String[] results = new String[t];

        String line;
        int counter = 1;
        while( (line = in.readLine()) != null) {
            //System.out.println(line);

            results[counter-1] = "Case #" + counter + ": " + countSheep(Integer.parseInt(line));
            System.out.println(results[counter-1]);
            counter++;
        }

        List<String> lines = Arrays.asList(results);
        Path file = Paths.get("q2016_1.out");
        Files.write(file, lines, Charset.forName("UTF-8"));

        in.close();
        return;
    }

    private static BufferedReader scanFile(String fileName) throws Exception{
        return new BufferedReader(new FileReader(fileName));
    }

    private static String countSheep(int counter) {
        if (counter == 0) return "INSOMNIA";

        boolean[] hash = new boolean[10];
        int existCount = 45; // 1 + 2 + 3...
        int i = 0;

        while(existCount != 0 || (existCount == 0 && !hash[0])) {
            int tmp = counter * ++i;

            while (tmp != 0) {
                if (!hash[tmp % 10]) {
                    hash[tmp % 10] = true;
                    existCount -= tmp % 10;
                }
                tmp /= 10;
            }
        }

        return "" + (counter * i);
    }
}
