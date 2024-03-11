import java.io.*;
import java.net.*;
import java.util.*;

public class MultiEchoServer {

    private static ServerSocket serverSocket;
    private static final int PORT = 1234;

    public static void main(String[] args) throws IOException{
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("***Server is on port 1234***");
        } catch (IOException ioEx) {
            System.out.println("Unable to set up the port connection");
            System.exit(1);
        }
        do{
            Socket client = serverSocket.accept();
            System.out.println("New Client Accpted");

            ClientHandler handler = new ClientHandler(client);
            handler.start();
        }while(true);
    }
}

class ClientHandler extends Thread{
    private Socket client;
    private Scanner input;
    private PrintWriter output;

    public ClientHandler(Socket socket){
        client = socket;

        try {
            input = new Scanner(client.getInputStream());
            output = new PrintWriter(client.getOutputStream(),true);

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
    public void run(){
        String received;
        do{
            received = input.nextLine();
            output.println("ECHO: "+received);
        }while(!received.equals("QUIT"));
        try {
            if(client != null){
                System.out.println("Closing down connection");
                client.close();
            }
        } catch (IOException ioEx) {
            System.out.println("Enable to disconnected!!!!");
        }
    }
}
