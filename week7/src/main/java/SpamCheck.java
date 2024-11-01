import java.net.*;


public class SpamCheck {
//    public static final String BLACKHOLE = "sbl.spamhaus.org";
    public static final String BLACKHOLE = "zen.spamhaus.org"; //"sbl.spamhaus.org"; //www.spamhaus.org

    public static void main(String[] args) throws UnknownHostException {

        args=new String[] {"www.hanbat.ac.kr", "5.199.130.188", "86.57.13.235"}; // 5.199.130.188 not spam. //
        for (String arg: args) {
            if (isSpammer(arg)) {
                System.out.println(arg + " is a known spammer.");
            } else {
                System.out.println(arg + " appears legitimate.");
            }
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