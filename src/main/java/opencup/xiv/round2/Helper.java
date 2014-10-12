package opencup.xiv.round2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * Helper, that write binary file for problem C
 */
public class Helper {

    public static void main(String[] args) throws IOException {

        ByteBuffer buf = ByteBuffer.allocate(1048576); // 1 MiB
        buf.order(ByteOrder.LITTLE_ENDIAN);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; ++i) {
            buf.putInt(sc.nextInt());
        }
        buf.flip();
        FileChannel out = new FileOutputStream("c.in").getChannel();
        out.write(buf);
        out.close();
    }

}
