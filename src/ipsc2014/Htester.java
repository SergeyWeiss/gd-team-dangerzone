package ipsc2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Htester {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader in = new BufferedReader(new FileReader("src/ipsc2014/h.out"));
        HashSet<Long> hashset = new HashSet<Long>();
        for (String x = in.readLine(); x != null; x = in.readLine())
            hashset.add(Long.parseLong(x));
        System.out.println(System.currentTimeMillis() - start);
    }
}
