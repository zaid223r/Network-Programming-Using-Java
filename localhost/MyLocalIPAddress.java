import java.net.*;

public class MyLocalIPAddress {
    public static void main(String[] args) {
        try {
            System.out.println("Your IP address is "+ InetAddress.getLocalHost());
        } catch (java.net.UnknownHostException UkHE) {
            System.out.println("\n\nCouldn't find the your IP address");
        
    }
}
}
