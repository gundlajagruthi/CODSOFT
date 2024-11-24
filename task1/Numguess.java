package task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Numguess {
    
    private static int randomNumber;
    private static int tryCount;
    
    private static JTextArea displayArea;
    private static JTextField inputField;
    private static JButton guessButton;
    private static JButton restartButton;
    private static JButton quitButton;

    public static void main(String args[]) {
        
        JFrame frame = new JFrame("Number Guessing Game");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // Random number generation
        Random r = new Random();
        randomNumber = r.nextInt(100) + 1;
        tryCount = 0;

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setText("Welcome to the Number Guessing Game! \nGuess a number between 1 and 100.\n");
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        displayArea.setBackground(Color.LIGHT_GRAY); // Slightly darker background for the text area
        displayArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Font size increased
        
        JScrollPane scrollPane = new JScrollPane(displayArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(Color.DARK_GRAY); // Darker bottom panel for better contrast
        
        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBackground(Color.DARK_GRAY); // Same dark background for input area
        inputField = new JTextField(20);
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputField.setBackground(Color.WHITE); // White background for text field
        inputPanel.add(inputField);
        bottomPanel.add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.DARK_GRAY); // Dark background for buttons
        guessButton = new JButton("Guess");
        restartButton = new JButton("Play Again");
        restartButton.setEnabled(false); 
        quitButton = new JButton("Quit");
        
        // Customizing button colors
        guessButton.setBackground(new Color(34, 177, 76)); // Green button for Guess
        guessButton.setForeground(Color.WHITE); // White text on buttons
        guessButton.setFont(new Font("Arial", Font.BOLD, 14)); // Bolder font
        
        restartButton.setBackground(new Color(255, 165, 0)); // Orange button for Play Again
        restartButton.setForeground(Color.WHITE); // White text
        
        quitButton.setBackground(new Color(255, 0, 0)); // Red button for Quit
        quitButton.setForeground(Color.WHITE); // White text
        
        // Adding buttons to the panel
        buttonPanel.add(guessButton);
        buttonPanel.add(restartButton);
        buttonPanel.add(quitButton);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numGuess = Integer.parseInt(inputField.getText());
                    tryCount++;

                    if (numGuess == randomNumber) {
                        displayArea.append("CORRECT! You've guessed it in " + tryCount + " trials.\n");
                        displayArea.append("Do you want to play again?\n");

                        guessButton.setEnabled(false);  // Disable Guess button
                        restartButton.setEnabled(true); // Enable Play Again button
                    } else if (numGuess < randomNumber) {
                        displayArea.append("That's low! Try again.\n");
                    } else {
                        displayArea.append("That's high! Try again.\n");
                    }
                } catch (NumberFormatException ex) {
                    displayArea.append("Invalid input! Please enter a number.\n");
                }
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random r = new Random();
                randomNumber = r.nextInt(100) + 1;
                tryCount = 0;

                displayArea.setText("New Game! Guess a number between 1 and 100.\n");
                inputField.setText("");
                guessButton.setEnabled(true);  // Re-enable Guess button
                restartButton.setEnabled(false); // Disable Play Again button
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });

        // Set frame visibility
        frame.setVisible(true);
    }
}
