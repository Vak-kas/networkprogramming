import java.net.*;
public class OReillyByName {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
//            InetAddress address = InetAddress.getByName("www.naver.com");
//            InetAddress address = InetAddress.getByName("www.google.com");
//            InetAddress address = InetAddress.getByName("www.oreilly.com");
            System.out.println(address);
//
            System.out.println("Host name : " + address.getHostName());
            System.out.println("Canonical Host Name: " + address.getCanonicalHostName());

//            InetAddress address2 = InetAddress.getByName("223.194.192.38");
//            System.out.println(address2.getHostName());
//            System.out.println(InetAddress.getByName("123.123.123.123").getHostName());

//            InetAddress[] addresses = InetAddress.getAllByName("www.naver.com");
//            for (InetAddress address3 : addresses){
//                System.out.println(address3);
//            }

        } catch (UnknownHostException ex) {
            System.out.println("Could not find www.google.com");
        }
    }
}