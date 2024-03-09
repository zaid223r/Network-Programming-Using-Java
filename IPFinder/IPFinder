import java.net.*;
import java.util.*;

public class IPFinder {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String host;
        InetAddress address;

        System.out.print("\n\nEnter the host name:");
        host = input.nextLine();
        try {
            address = InetAddress.getByName(host);
            System.out.println("\n\nThe IP address of " +host+ " is "+address);
        } catch (java.net.UnknownHostException UkHE) {
            System.out.println("\n\nCouldn't find the IP address of "+host);
        }
    }
}
