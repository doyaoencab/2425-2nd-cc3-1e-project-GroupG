package com.mycompany.employeemanagementsystemgui.views;

import javax.swing.*;
import java.awt.*;

public class CalendarView extends JFrame {
    private JPanel panel;
    private JLabel monthLabel;
    private JButton prevButton, nextButton, addEventButton;

    public CalendarView() {
        setTitle("Calendar");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        monthLabel = new JLabel("", SwingConstants.CENTER);
        monthLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        panel = new JPanel(new GridLayout(7, 7));
        panel.setPreferredSize(new Dimension(480, 480));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        prevButton = new JButton("<");
        nextButton = new JButton(">");

        topPanel.add(prevButton);
        topPanel.add(monthLabel);
        topPanel.add(nextButton);

        add(topPanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        addEventButton = new JButton("Add Event");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(addEventButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getMonthLabel() {
        return monthLabel;
    }

    public JButton getPrevButton() {
        return prevButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getAddEventButton() {
        return addEventButton;
    }
}
