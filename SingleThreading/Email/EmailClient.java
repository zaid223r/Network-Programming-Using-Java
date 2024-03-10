import java.io.*;
import java.net.*;
import java.util.*;

public class EmailClient {
    private static InetAddress host;
    private static final int PORT = 1234;
    private static String name;
    private static Scanner networkInput, userEntry;
    private static PrintWriter networkOutput;

    public static void main(String[] args)
            throws IOException {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException uhEx) {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        userEntry = new Scanner(System.in);
        do {
            System.out.print(
                    "\nEnter name ('Client1' or 'Client2'): ");
            name = userEntry.nextLine();
        } while (!name.equals("Client1")
                && !name.equals("Client2"));
        talkToServer();
    }

    private static void talkToServer() throws IOException {
        String option, message, response, another;
        do {
            Socket link = new Socket(host, PORT);
            networkInput = new Scanner(link.getInputStream());
            networkOutput = new PrintWriter(link.getOutputStream(),true);
            userEntry = new Scanner(System.in);
            do{
            System.out.print("read or send? ");
            option = userEntry.nextLine();
            if(option.equals("send"))
                doSend();
            else if(option.equals("read"))
                doRead();
            System.out.print("Do you want another operation?Y/n  ");
            another = userEntry.nextLine();
            }while(another.equals("Y"));
        } while (!option.equals("send")&& !option.equals("read"));
    }

    private static void doSend() {
        System.out.print("\nEnter 1-line message: ");
        String message = userEntry.nextLine();
        networkOutput.println(name);
        networkOutput.println("send");
        networkOutput.println(message);
    }

    private static void doRead() throws IOException {
        networkOutput.println(name);
        networkOutput.println("read");
        int numofmessages = Integer.parseInt(networkInput.nextLine());
        for(int i = 0; i < numofmessages ; i++){
            System.out.println(networkInput.nextLine());
        }
    }
}
