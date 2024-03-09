package lab;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class GUILAB extends JFrame implements ActionListener{
    private JTextField num1,num2;
    private JPanel input;

    private JTextArea display;

    private JButton addButton,subButton,mulButton,divButton,exitButton;
    private JPanel buttonPanel;

    public static void main(String[] args) {
        GUILAB frame = new GUILAB();
        frame.setSize(400,400);
        frame.setVisible(true);
        frame.setResizable(true);
        
    }   
    public GUILAB(){
        num1 = new JTextField(20);
        num2 =new JTextField(20);
        input = new JPanel();

        input.add(new JLabel("Enter num1 : "));
        input.add(num1);
        
        input.add(new JLabel("Enter num2 : "));
        input.add(num2);

        add(input,BorderLayout.NORTH);

        display = new JTextArea(10,15);
        // display.setWrapStyleWord(true);
        // display.setLineWrap(true);
        add(new JScrollPane(display),BorderLayout.CENTER);
        add(display,BorderLayout.CENTER);

        addButton = new JButton("ADD");
        addButton.addActionListener(this);
        subButton = new JButton("SUB");
        subButton.addActionListener(this);
        mulButton = new JButton("MUL");
        mulButton.addActionListener(this);
        divButton = new JButton("DIV");
        divButton.addActionListener(this);
        exitButton = new JButton("EXIT");
        exitButton.addActionListener(this);
        buttonPanel = new JPanel();

        buttonPanel.add(addButton);
        buttonPanel.add(subButton);
        buttonPanel.add(mulButton);
        buttonPanel.add(divButton);
        buttonPanel.add(exitButton);
        add(buttonPanel,BorderLayout.SOUTH);
    }
    public void actionPerformed(ActionEvent event){
        if (event.getSource() == exitButton) {
            System.exit(1);
       }
       try {
            double output = 0;
            int number1 = Integer.parseInt(num1.getText());
            int number2 = Integer.parseInt(num2.getText());
            if (event.getSource() == addButton) {
                output = number1 + number2;
            }
            else if (event.getSource() == subButton) {
                output = number1 - number2;
            }
            else if (event.getSource() == mulButton) {
                output = number1 * number2;
            }
            else{
                output = number1/number2;
            }
            display.append(output+"");
            num1.setText("");
            num2.setText("");
       } catch (NumberFormatException nfEx) {
            display.setText(nfEx.toString());
       }
    }
}
