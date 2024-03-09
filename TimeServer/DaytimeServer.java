import java.io.*;
import java.net.*;
import java.util.Date;

public class DaytimeServer {
    public static void main(String[] args) {
        ServerSocket server;
        final int DAYTIME_PORT = 13;
        Socket socket;

        try {
            server = new ServerSocket(DAYTIME_PORT);
            do{
                socket = server.accept();
                PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
                Date date = new Date();

                output.println(date);
                socket.close();
            }while(true);
        } catch (IOException ioEx) {
            System.out.println(ioEx);
        }
    }
}
