import java.net.InetAddress;
import java.net.*;

public class week8_1 {
    public static void main(String[] args){
        try{
            InetAddress me = InetAddress.getLocalHost();
            String dottedQuad = me.getHostAddress();
            System.out.println("My address is " + dottedQuad);
            System.out.println(me);
        } catch(UnknownHostException ex){
            System.out.println("Could not find this computer's address");
        }
    }
}
