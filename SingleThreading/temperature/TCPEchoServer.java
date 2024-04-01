package mid.mid1;


import java.io.*;
import java.net.*;
import java.util.*;
public class TCPEchoServer
{
   private static ServerSocket serverSocket;
   private static final int PORT = 1234;
   public static void main(String[] args)
   {
      System.out.println("Opening port...\n");
      
      try
      {
         serverSocket = new ServerSocket(PORT);    //Step 1.
      }
      
      catch(IOException ioEx)
      {
         System.out.println("Unable to attach to port!");
         System.exit(1);
      }
      
      do {
         handleClient();
      }while (true);
      
      
   }
   private static void handleClient()
   {
      Socket link = null;
      
      
      try {
         link = serverSocket.accept();
         
         
         Scanner input = new Scanner(link.getInputStream()); 
         PrintWriter output =new PrintWriter(link.getOutputStream(),true); //Step 3.
         String message;
         do{
            message = input.nextLine();
            String[] arrOfStr = message.split(" : ",2);
            if (arrOfStr[0].equals("celi"))
            {
                int result = Integer.parseInt(arrOfStr[1]) * 9/5 + 32;
                output.println(arrOfStr[1] + " in fehranhite is "+ result);
            }
            else if (arrOfStr[0].equals("fehr"))
            {
                int result = (Integer.parseInt(arrOfStr[1]) - 32) * 5/9;
                output.println(arrOfStr[1] + " in celicues is "+ result);
            }
            else
            {
                output.println("INVALID INPUT");
            }
         }while(!message.equals("***CLOSE***"));
          
      }
      
      
      catch(IOException ioEx)
      {
         ioEx.printStackTrace();
      }
      
      
      finally {
         
         
      try 
         {
            System.out.println("\n* Closing connection... *");      
            link.close(); //Step 5. 
         }
            
         catch(IOException ioEx)
         {
            System.out.println("Unable to disconnect!");
            System.exit(1);
         }
      } 
   }
}
