import java.io.*;
import java.util.*;
import java.net.*;

public class TCPEchoServer {
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        System.out.println("LOADING...");

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("The port has been opened");
        } catch (IOException ioEx) {
            System.out.println("Couldn;t open the port");
            System.exit(1);
        }

        do{
            handleClinet();
        } while (true);
    }

    private static void handleClinet() {
        Socket link = null;

        try {
            
            link = serverSocket.accept();
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(),true);

            String message;
            
            do{
                message = input.nextLine();
                System.out.println("Message received.");
                output.println("Message : " + message);    
            }while (!message.equals("CLOSE"));

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                System.out.println("\n\nClosing Connection");
                link.close();
            } catch (IOException ioEx) {
                System.out.println("\n\nUnable to close connection");
                System.exit(1);
            }
        }
    }

}
