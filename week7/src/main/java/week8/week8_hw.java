package week8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class week8_hw {
    public static void main(String[] args) {
        try (FileInputStream fin = new FileInputStream("a.txt");
             Reader in = new InputStreamReader(fin);
             BufferedReader bin = new BufferedReader(in)) {

            for (String entry = bin.readLine(); entry != null; entry = bin.readLine()) {
                int index = entry.indexOf(' ');
                if (index != -1) {
                    String ip = entry.substring(0, index);
                    String theRest = entry.substring(index);

                    try {
                        InetAddress address = InetAddress.getByName(ip);
                        System.out.println(address.getHostName() + theRest);
                    } catch (UnknownHostException ex) {
                        System.out.println("Unknown host: " + ip);
                    }
                } else {
                    System.out.println("Invalid entry: " + entry);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
