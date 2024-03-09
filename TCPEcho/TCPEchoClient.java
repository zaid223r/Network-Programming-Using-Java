import java.util.*;
import java.net.*;
import java.io.*;

public class TCPEchoClient {
    private static InetAddress server;
    private static final int PORT= 1234;

    public static void main(String[] args) {
        try {
            server = InetAddress.getLocalHost();
        } catch (java.net.UnknownHostException uhEx) {
            System.out.println("Unable to reach the Server");
            System.exit(1);
        }
        accessServer();
    }

    private static void accessServer(){
        Socket link = null;
        try {

            link = new Socket(server, PORT);
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(),true);
            Scanner clinetEntry = new Scanner(System.in);

            String message,response;
            do{
            System.out.print("Enter your message :");
            message = clinetEntry.nextLine();
            output.println(message);
            response = input.nextLine();
            System.out.println(response);
            }while(!message.equals("CLOSE"));          

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        finally
        {
            try {
                System.out.println("Closing Connection");
                link.close();
            } catch (IOException ioEx) {
                System.out.println("Unable to close connection");
                System.exit(1);
            }
        }
    
    }
}
