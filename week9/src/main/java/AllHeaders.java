import java.io.*;
import java.net.*;
public class AllHeaders {
    public static void main(String[] args) {
//        for (int i = 0; i < args.length; i++) {
        try {
            URL u = new URL("https://www.oreilly.com");
            URLConnection uc = u.openConnection();
            for (int j = 1; ; j++) {
                String header = uc.getHeaderField(j);
                if (header == null) break;
                System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
            }
        } catch (MalformedURLException ex) {
            System.err.println(" is not a URL I understand.");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println();
    }
//    }
}