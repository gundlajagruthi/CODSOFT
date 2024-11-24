package task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM {
    public static void main(String args[]) {
        // Creating the user's bank account with an initial balance
        BankAccount userAccount = new BankAccount(10000);

        // Creating the main JFrame for the ATM interface
        JFrame frame = new JFrame("ATM System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(7, 2, 10, 10));  // Adjusted grid layout for better spacing

        // Labels and text fields
        JLabel balanceLabel = new JLabel("Balance: $" + userAccount.checkBalance());
        JLabel amountLabel = new JLabel("Enter amount: ");
        JTextField amountField = new JTextField();
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton exitButton = new JButton("Exit");
        JLabel messageLabel = new JLabel("");

        // Styling for balance label
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        balanceLabel.setForeground(new Color(34, 139, 34)); // Green for balance

        // Styling for amount field
        amountField.setFont(new Font("Arial", Font.PLAIN, 14));
        amountField.setBackground(Color.WHITE);
        amountField.setForeground(Color.BLACK);

        // Button styling
        withdrawButton.setBackground(new Color(255, 69, 0)); // Red button for withdrawal
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 14));

        depositButton.setBackground(new Color(34, 139, 34)); // Green button for deposit
        depositButton.setForeground(Color.WHITE);
        depositButton.setFont(new Font("Arial", Font.BOLD, 14));

        checkBalanceButton.setBackground(new Color(100, 149, 237)); // Blue button for check balance
        checkBalanceButton.setForeground(Color.WHITE);
        checkBalanceButton.setFont(new Font("Arial", Font.BOLD, 14));

        exitButton.setBackground(new Color(255, 0, 0)); // Red button for exit
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Adding components to the frame
        frame.add(balanceLabel);
        frame.add(new JLabel(""));  // Empty label for alignment
        frame.add(amountLabel);
        frame.add(amountField);
        frame.add(withdrawButton);
        frame.add(depositButton);
        frame.add(checkBalanceButton);
        frame.add(exitButton);
        frame.add(messageLabel);

        // Action listener for withdraw button
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double withdrawAmount = Double.parseDouble(amountField.getText());
                    if (withdrawAmount <= 0) {
                        messageLabel.setText("Amount must be greater than 0.");
                    } else if (userAccount.withdraw(withdrawAmount)) {
                        balanceLabel.setText("Balance: $" + userAccount.checkBalance());
                        messageLabel.setText("Successfully withdrew $" + withdrawAmount);
                    } else {
                        messageLabel.setText("Insufficient balance!");
                    }
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Invalid amount entered!");
                }
            }
        });

        // Action listener for deposit button
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double depositAmount = Double.parseDouble(amountField.getText());
                    if (depositAmount <= 0) {
                        messageLabel.setText("Amount must be greater than 0.");
                    } else {
                        userAccount.deposit(depositAmount);
                        balanceLabel.setText("Balance: $" + userAccount.checkBalance());
                        messageLabel.setText("Successfully deposited $" + depositAmount);
                    }
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Invalid amount entered!");
                }
            }
        });

        // Action listener for check balance button
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText("Current balance: $" + userAccount.checkBalance());
            }
        });

        // Action listener for exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Exit the application
            }
        });

        // Setting the frame visible
        frame.setVisible(true);
    }
}
