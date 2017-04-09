import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.nio.file.Paths;

public class BathroomStalls {
    public static void main(String[] args) throws Exception {
        BufferedReader in = scanFile(args[0]);
        int t = Integer.parseInt(in.readLine());
        String[] results = new String[t];

        String line;
        int counter = 1;
        while( (line = in.readLine()) != null) {
            //System.out.println(line);

            results[counter-1] = "Case #" + counter + ": " + bathroom(line);
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

    private static String bathroom(String line) {
        String[] splitLine = line.split(" ");
        Comparator<Integer> comparator = new Numbercomparator();
        Queue<Integer> queue = new PriorityQueue<Integer>(comparator);
        int room = Integer.parseInt(splitLine[0]);
        int ppl = Integer.parseInt(splitLine[1]);

        if (room == ppl) {
            return "0 0";
        }

        //System.out.println(ppl);

        int l = room%2 == 0 ? room/2 - 1 : room/2;
        int r = room/2;
        queue.add(Math.max(l, r));
        queue.add(Math.min(l, r));
        // System.out.println(l);
        // System.out.println(r);

        for (int i=1; i<ppl; i++) {
            int space =  queue.poll();
            // System.out.println(i + " " + space);
            if (space == 0) {
                return "0 0";
            }

            l = space%2 == 0 ? space/2 - 1 : space/2;
            r = space/2;

            // System.out.println(" " + l);
            // System.out.println(" " + r);

            queue.add(Math.max(l, r));
            queue.add(Math.min(l, r));

        }

        return "" + r + " " + l;
    }

    public static class Numbercomparator implements Comparator<Integer>
    {
        @Override
        public int compare(Integer x, Integer y)
        {
            return y - x; // Using descending order
        }
    }
}
