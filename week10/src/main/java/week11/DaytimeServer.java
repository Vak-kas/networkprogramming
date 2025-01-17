package week11;
import java.net.*;
import java.io.*;
import java.util.Date;
public class DaytimeServer {
    public final static int PORT = 1300;
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try (Socket connection = server.accept()) {
                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    Date now = new Date();
                    out.write(now.toString() +"\r\n");
                    System.out.println("Server port : " + PORT + " Client Port : " + connection.getPort());
                    out.flush();
                    connection.close();
                } catch (IOException ex) {}
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}