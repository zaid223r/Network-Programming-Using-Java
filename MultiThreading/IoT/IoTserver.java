package lab;

import java.io.*;
import java.net.*;
import java.util.*;

public class IoTserver {

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
            String[] splited = received.split(":",2);
            if(!(splited[0].equals("temperature") || splited[0].equals("humidity"))){
                output.println("Choose either (temperature or humidity) please.");
                continue;
            }
            if (splited[0].equals("temperature"))
            {
                int temp = 0;
                try {
                    temp = Integer.parseInt(splited[1]);
                } catch (Exception e) {
                    output.println("Enter Number Please");
                }
                if (temp < 10){
                    output.println("It's too cold now!!");
                }
                else if (temp < 30){
                    output.println("That's my favorite time to go out.");
                }
                else{
                    output.println("It's too hot in that place :(");
                }
            }
            else
            {
                int humid = 0;
                try {
                    humid = Integer.parseInt(splited[1]);
                } catch (Exception e) {
                    output.println("Enter a number between 0 and 100 (that's the percentage of humidity)");
                }
                if (humid < 10) {
                    output.println("Are You in the hell?");
                }
                else if (humid < 20){
                    output.println("This range is comfortable for those who enjoy outdoor activities in dry climates, as it reduces the risk of heat exhaustion and dehydration.");
                }
                else if (humid < 40){
                    output.println("This range is comfortable for those with respiratory issues, as it reduces the risk of asthma attacks and other breathing problems.");
                }
                else if (humid < 60){
                    output.println("This range is considered the most comfortable for humans as it helps to maintain optimal respiratory and skin health, and prevents the growth of harmful bacteria and viruses.");
                }
                else if (humid < 80){
                    output.println("This level is ideal for reducing allergic reactions, as it helps to keep airways moist and prevent nasal irritation.");
                }
                else if (humid < 90){
                    output.println("This range is comfortable for those with dry skin or eczema, as it helps to keep skin hydrated and reduce itching and flaking.");
                }
                else {
                    output.println("Are you in the ocean?");
                }
            }
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