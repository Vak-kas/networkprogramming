import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class test4 {
    public static void main(String[] args) {
        try (InputStream in = new FileInputStream("./data0927.txt")) {
            int bytesRead = 0;
            int bytesToRead = 1024;
            byte[] input = new byte[bytesToRead];
            while (bytesRead < bytesToRead) {
                int result = in.read(input, bytesRead, bytesToRead - bytesRead);
                System.out.println("result:" + result + "\n"); //for test
                if (result == -1)
                    break; // end of stream
                bytesRead += result;
            }

            for (int i = 0; i < 1024; i++) {
                System.out.print((char) input[i]);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
