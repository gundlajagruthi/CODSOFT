package task2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stugrade {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Student Grade Calculation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);
        frame.setLayout(new FlowLayout());

        // Instructions Label
        JLabel instructionLabel = new JLabel("Enter marks (0-100) for each subject:");
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(instructionLabel);

        // Labels for subjects
        JLabel labelMaths = new JLabel("Enter marks for Maths: ");
        JLabel labelEnglish = new JLabel("Enter marks for English: ");
        JLabel labelPhysics = new JLabel("Enter marks for Physics: ");
        JLabel labelChemistry = new JLabel("Enter marks for Chemistry: ");

        // Text fields for input
        JTextField textMaths = new JTextField(10);
        JTextField textEnglish = new JTextField(10);
        JTextField textPhysics = new JTextField(10);
        JTextField textChemistry = new JTextField(10);

        // Input Panel with GridLayout for arranging labels and text fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(new Color(245, 245, 245)); // Light background
        inputPanel.add(labelMaths);
        inputPanel.add(textMaths);
        inputPanel.add(labelEnglish);
        inputPanel.add(textEnglish);
        inputPanel.add(labelPhysics);
        inputPanel.add(textPhysics);
        inputPanel.add(labelChemistry);
        inputPanel.add(textChemistry);

        // Calculate button
        JButton calculateButton = new JButton("Calculate Grade");
        calculateButton.setBackground(new Color(34, 177, 76)); // Green button
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Table to display results
        String[] columnNames = {"Total Marks", "Average Percentage", "Grade"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
        JTable resultTable = new JTable(tableModel);
        resultTable.setRowHeight(30); // Increased row height for better readability
        resultTable.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setPreferredSize(new Dimension(400, 100));

        // Adding components to the frame
        frame.add(inputPanel);
        frame.add(calculateButton);
        frame.add(scrollPane);

        // Button Action Listener
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parsing input marks from text fields
                    int maths = Integer.parseInt(textMaths.getText());
                    int english = Integer.parseInt(textEnglish.getText());
                    int physics = Integer.parseInt(textPhysics.getText());
                    int chemistry = Integer.parseInt(textChemistry.getText());

                    // Validation for marks input
                    if (maths < 0 || maths > 100 || english < 0 || english > 100 || physics < 0 || physics > 100 || chemistry < 0 || chemistry > 100) {
                        JOptionPane.showMessageDialog(frame, "Marks should be between 0 and 100. Please try again.");
                        return;
                    }

                    // Calculating total and average percentage
                    int totalMarks = maths + english + physics + chemistry;
                    float averagePercentage = (float) totalMarks / 400 * 100;

                    // Assigning grade based on average percentage
                    String grade;
                    if (averagePercentage >= 90) {
                        grade = "A+(Excellent)";
                    } else if (averagePercentage >= 80) {
                        grade = "A(Very Good)";
                    } else if (averagePercentage >= 70) {
                        grade = "B+(Good)";
                    } else if (averagePercentage >= 50) {
                        grade = "B(Average)";
                    } else if (averagePercentage >= 40) {
                        grade = "C(Pass)";
                    } else {
                        grade = "F(FAIL!!)";
                    }

                    // Updating the table with calculated results
                    tableModel.setRowCount(0);  // Clear previous rows
                    tableModel.addRow(new Object[]{totalMarks + " / 400", String.format("%.2f", averagePercentage) + "%", grade});

                } catch (NumberFormatException ex) {
                    // Handling invalid number input
                    JOptionPane.showMessageDialog(frame, "Please enter valid numeric marks.");
                }
            }
        });

        // Frame visibility
        frame.setVisible(true);
    }
}
