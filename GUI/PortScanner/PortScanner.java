import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class PortScanner extends JFrame implements ActionListener{

    private JLabel prompt;
    private JTextField hostInput;
    private JTextArea report;
    private JButton seekButton,exitButton;
    private JPanel hostPanel,buttonPanel;
    
    private static Socket socket = null;

    public static void main(String[] args) {
        PortScanner frame = new PortScanner();

        frame.setSize(400,300);
        frame.setVisible(true);
        frame.addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent event){
                    if(socket != null){
                        try {
                            socket.close();
                        } catch (IOException ioEx) {
                            System.out.println("Unable to close connection");
                            System.exit(1);
                        }
                    }
                    System.exit(0);
                }   
            }
        );
    }
    public PortScanner(){
        hostPanel = new JPanel();
        prompt = new JLabel("Host name: ");
        hostInput = new JTextField(25);
        hostPanel.add(prompt);
        hostPanel.add(hostInput);
        add(hostPanel,BorderLayout.NORTH);
        report = new JTextArea(10,25);
        add(report,BorderLayout.CENTER);
        buttonPanel = new JPanel();
        seekButton = new JButton("Seek ports");
        seekButton.addActionListener(this);
        buttonPanel.add(seekButton);
        exitButton = new JButton("EXIT");
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);
        add(buttonPanel,BorderLayout.SOUTH);
        report.setLineWrap(true);
        report.setWrapStyleWord(true);
        add(new JScrollPane(report),BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent event){
        if (event.getSource()==exitButton) {
            System.exit(0);
        }
        report.setText("");
        String host = hostInput.getText();
        try {
            InetAddress address = InetAddress.getByName(host);
            report.append("The address is "+address+"\n\n");

            for(int i=0;i<25;i++){
                try {
                    socket = new Socket(host,i);
                    report.append("There is a service running on PORT "+ i+"\n");
                } catch (IOException ioEx) {
                    report.append("There is no service on PORT "+i+"\n");
                }
            }

        } catch (java.net.UnknownHostException ukEx) {
            hostInput.setText("");
            report.setText("Unknown host");
        }
    }
}
