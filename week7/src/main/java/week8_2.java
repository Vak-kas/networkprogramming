import java.net.*;

public class week8_2 {

    public static int getVersion(InetAddress ia) {

        byte[] address = ia.getAddress();

        if (address.length == 4)

            return 4;

        else if (address.length == 16)

            return 6;

        else

            return -1;

    }

    public static void main(String[] args) {

        InetAddress ia;

        try {

// ia = InetAddress.getLocalHost();

            ia = InetAddress.getByName("www.hanbat.ac.kr");

            byte[] address = ia.getAddress();

            for (byte b : address)

                System.out.println(b);

            System.out.println(ia);

            switch (getVersion(ia)) {
                case 4:
                    System.out.println("IPv4");
                    break;
                case 6:
                    System.out.println("IPv6");
                    break;
                default:
                    System.out.println("Nothing");
            }
        } catch (UnknownHostException e) {
// TODO Auto-generated catch block
            e.printStackTrace();

        }

    }

}