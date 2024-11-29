package week11;

import java.io.*;
import java.net.*;

public class EchoClient {
    public static final String SERVER_ADDRESS = "127.0.0.1"; // Localhost
    public static final int PORT = 1300;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to Echo Server");
            System.out.println("Type 3 lines of text to send to the server:");

            // Send 3 lines of text
            for (int i = 0; i < 3; i++) {
                String userInput = console.readLine(); // Read input from console
                out.println(userInput);              // Send to server
                String response = in.readLine();     // Read response from server
                System.out.println("Server replied: " + response);
            }

        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}
