package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private final JFrame frame;
    private final JTextField textField;
    private String operator = "";
    private double num1;
    private double result;

    public static void main(String[] args) {
        // Create an instance of Calculator and display the frame
        Calculator calculator = new Calculator();
        calculator.frame.setVisible(true);
    }

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setBounds(100, 100, 350, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
        textField.setBounds(10, 11, 310, 50);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JButton[] buttons = new JButton[16];
        String[] labels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(labels[i]);
            buttons[i].setFont(new Font("Tahoma", Font.PLAIN, 20));
            buttons[i].setBounds(10 + (i % 4) * 75, 72 + (i / 4) * 60, 70, 50);
            buttons[i].addActionListener(new ButtonClickListener());
            frame.getContentPane().add(buttons[i]);
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if ("0123456789.".contains(command)) {
                // If the button is a number or dot, append to the text field
                textField.setText(textField.getText() + command);
            } else if (command.equals("=")) {
                // If "=" is clicked, perform the operation
                double num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case "+" -> result = num1 + num2;
                    case "-" -> result = num1 - num2;
                    case "*" -> result = num1 * num2;
                    case "/" -> {
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            result = Double.NaN; // Error in division
                        }
                    }
                }
                textField.setText(String.valueOf(result));
                operator = "";
            } else {
                if (!operator.isEmpty()) {
                    return;
                }
                num1 = Double.parseDouble(textField.getText());
                operator = command;
                textField.setText("");
            }
        }
    }
}
