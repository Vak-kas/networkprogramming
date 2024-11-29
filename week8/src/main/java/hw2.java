import java.io.*;
import java.net.*;

public class hw2 {
    public static void main(String[] args) {
        InputStream in = null;
        FileOutputStream out = null;

        try {
            URL url = new URL("https://www.hanbat.ac.kr/images/kor/main/images1.jpg");
            in = url.openStream();

            out = new FileOutputStream("data.jpg");

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }


        } catch (MalformedURLException ex) {
            System.err.println(args[0] + " is not a parseable URL");
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
// ignore
                }
            }
        }
    }
}
