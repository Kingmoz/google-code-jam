import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.nio.file.Paths;

public class q2016_2 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = scanFile(args[0]);
        int t = Integer.parseInt(in.readLine());
        String[] results = new String[t];

        String line;
        int counter = 1;
        while( (line = in.readLine()) != null) {
            //System.out.println(line);

            results[counter-1] = "Case #" + counter + ": " + maneuverCake(line);
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

    private static int maneuverCake(String pancake) {
        char[] c = pancake.toCharArray();
        int count = 0;
        int index = c.length - 1;
        char sign = '-';

        for(int i=index; i>=0; i--) {
            if (c[i] == sign) {
                sign = changeSign(sign);
                count ++;
            }
        }

        return count;
    }

    private static char changeSign(char curSign) {
        if (curSign == '-')
            return '+';
        if (curSign == '+')
            return '-';
        return '-';
    }
}
