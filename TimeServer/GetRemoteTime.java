import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class GetRemoteTime extends JFrame implements ActionListener{
    
    private JTextField hostInput;
    private JTextArea display;
    private JButton timeButton;
    private JButton exitButton;
    private JPanel buttonPanel;

    private static Socket socket = null;

    public static void main(String[] args) {
        GetRemoteTime frame = new GetRemoteTime();
        frame.setSize(400,300);
        frame.setVisible(true);

        frame.addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent event){
                    if(socket != null){
                        try {
                            socket.close();
                        } catch (IOException ioEx) {
                            System.out.println("Unable to close link");
                            System.exit(1);
                        }
                    }
                    System.exit(0);
                }
            }
        );
    }
    public GetRemoteTime()
    {
        hostInput = new JTextField(20);
        add(hostInput,BorderLayout.NORTH);

        display = new JTextArea(10,15);
        display.setWrapStyleWord(true);
        display.setLineWrap(true);

        add(new JScrollPane(display),BorderLayout.CENTER);

        buttonPanel = new JPanel();

        timeButton = new JButton("Get date & time");
        timeButton.addActionListener(this);
        buttonPanel.add(timeButton);

        exitButton = new JButton("EXIT");
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);

        add(buttonPanel,BorderLayout.SOUTH);

    }
    public void actionPerformed(ActionEvent event){
        if (event.getSource() == exitButton) {
            System.exit(1);
        }
        String theTime;
        String host = hostInput.getText();
        final int DAYTIME_PORT = 13;

        try {
            socket = new Socket(host,DAYTIME_PORT);
            Scanner input = new Scanner(socket.getInputStream());

            theTime= input.nextLine();

            display.append("The date/time at "+ host +" is "+ theTime+"\n");
            hostInput.setText("");

        } catch (java.net.UnknownHostException uhEx) {
            display.append("No such host\n");
            hostInput.setText("");
        }catch (IOException ioEx){
            display.append(ioEx.toString()+"\n");

        }
        finally
        {
            try {
                if(socket != null){
                    socket.close();
                }
            } catch (IOException ioEx) {
                System.out.println("Unable to disconnect!"); 
                System.exit(1); 
            }
        }

    }
}
