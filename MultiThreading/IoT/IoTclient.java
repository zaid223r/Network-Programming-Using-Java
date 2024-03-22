package lab;

import java.util.*;
import java.net.*;
import java.io.*;

public class IoTclient {

    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (java.net.UnknownHostException ukEx) {
            System.out.println("Host IP address not found");
            System.exit(1);
        }
        sendmessage();
    }
    private static void sendmessage(){
        Socket socket = null;
        try {
            socket = new Socket(host, PORT);
            Scanner networkInput = new Scanner(socket.getInputStream());
            PrintWriter networkOutput = new PrintWriter(socket.getOutputStream(),true);
            Scanner userEntry = new Scanner(System.in);

            String message, response;
            do{
                System.out.print("Enter (temperature:'number'),(humidity:'number') or (QUIT to exit): ");
                message = userEntry.nextLine();
                networkOutput.println(message);
                response = networkInput.nextLine();
                System.out.println(response);
            }while(!message.equals("QUIT"));
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        finally{
            try {
                System.out.println("Closing connection....");
                socket.close();
            } catch (IOException ioEx) {
                System.out.println("Enable to close connection");
                System.exit(1);
            }
        }
    }
}