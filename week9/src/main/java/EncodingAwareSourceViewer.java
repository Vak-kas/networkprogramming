import java.io.*;
import java.net.*;
public class EncodingAwareSourceViewer {
    public static void main (String[] args) {
//        for (int i = 0; i < args.length; i++) {
        try {
// set default encoding
            String encoding = "ISO-8859-1";
            URL u = new URL("https://www.hanbat.ac.kr");
            URLConnection uc = u.openConnection();
            String contentType = uc.getContentType();
            System.out.println("contentType : " + contentType);

            int encodingStart = contentType.indexOf("charset=");
            if (encodingStart != -1) {
                encoding = contentType.substring(encodingStart + 8);
            }

            InputStream in = new BufferedInputStream(uc.getInputStream());
            Reader r = new InputStreamReader(in, encoding);
            int c;
            while ((c = r.read()) != -1) {
//                System.out.print((char) c);
            }
            r.close();
        } catch (MalformedURLException ex) {
            System.err.println(" is not a parseable URL");
        } catch (UnsupportedEncodingException ex) {
            System.err.println(
                    "Server sent an encoding Java does not support: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}