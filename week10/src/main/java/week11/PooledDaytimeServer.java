package week11;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
public class PooledDaytimeServer {
    public final static int PORT = 1300;
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(50);
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                try {
                    Socket connection = server.accept();
                    Callable<Void> task = new DaytimeTask(connection);
                    pool.submit(task);
                } catch (IOException ex) {}
            }
        } catch (IOException ex) {
            System.err.println("Couldn't start server");
        }
    }
    private static class DaytimeTask implements Callable<Void> {
        private Socket connection;
        DaytimeTask(Socket connection) {
            this.connection = connection;
        }
        @Override
        public Void call() {
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    PrintWriter out = new PrintWriter(connection.getOutputStream(), true)
            ) {
                // Read and echo back 3 lines of text
                for (int i = 0; i < 3; i++) {
                    String line = in.readLine();
                    if (line == null) break;
                    System.out.println("받은 데이터: " + line);
                    out.println(line);
                }
            } catch (IOException ex) {
                System.err.println(ex);
            } finally {
                try {
                    connection.close();
                } catch (IOException e) {
                    // ignore;
                }
            }
            return null;
        }
    }
}