import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class week8_hw {
    public static final String BLACKHOLE = "zen.spamhaus.org"; //"sbl.spamhaus.org"; //www.spamhaus.org

    public static void main(String[] args) {
        try (FileInputStream fin = new FileInputStream("a.txt");
             Reader in = new InputStreamReader(fin);
             BufferedReader bin = new BufferedReader(in)) {

            for (String entry = bin.readLine(); entry != null; entry = bin.readLine()) {
                int index = entry.indexOf(' ');
                if (index != -1) {
                    String ip = entry.substring(0, index);
                    String theRest = entry.substring(index);
                    boolean isSpam = isSpammer(ip);

                    try {
                        InetAddress address = InetAddress.getByName(ip);

                        if(isSpam) {
                            System.out.println(address.getHostName() + " - SPAM" + theRest);

                        }
                        else{
                            System.out.println(address.getHostName() + theRest);
                        }

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

    private static boolean isSpammer(String arg) {
        try {
            InetAddress address = InetAddress.getByName(arg);
            byte[] quad = address.getAddress();
            String query = BLACKHOLE;
            for (byte octet : quad) {
                int unsignedByte = octet < 0 ? octet + 256 : octet;
                query = unsignedByte + "." + query;
            }
            InetAddress.getByName(query);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }
}
