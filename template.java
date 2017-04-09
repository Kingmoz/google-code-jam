import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.nio.file.Paths;

public class template {
    public static void main(String[] args) throws Exception {
        BufferedReader in = scanFile(args[0]);
        int t = Integer.parseInt(in.readLine());
        String[] results = new String[t];

        String line;
        int counter = 1;
        while( (line = in.readLine()) != null) {
            //System.out.println(line);

            results[counter-1] = "Case #" + counter + ": " + line;
            System.out.println(results[counter-1]);
            counter ++;
        }
        in.close();

        List<String> lines = Arrays.asList(results);
        Path file = Paths.get("template.out");
        Files.write(file, lines, Charset.forName("UTF-8"));

        return;
    }

    private static BufferedReader scanFile(String fileName) throws Exception{
        return new BufferedReader(new FileReader(fileName));
    }
}
