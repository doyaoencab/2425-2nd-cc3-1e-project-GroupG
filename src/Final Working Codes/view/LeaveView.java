package com.mycompany.employeemanagementsystemgui.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LeaveView extends JFrame {
    private DefaultTableModel tableModel;
    private JTable leaveTable;
    private JButton addLeaveButton, deleteLeaveButton, viewHistoryButton;

    public LeaveView() {
        setTitle("Leave Management");
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new String[]{"Employee Name", "Leave Type", "Start Date", "End Date"}, 0);
        leaveTable = new JTable(tableModel);
        add(new JScrollPane(leaveTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addLeaveButton = new JButton("Add Leave");
        deleteLeaveButton = new JButton("Delete Leave");
        viewHistoryButton = new JButton("View Leave History");
        buttonPanel.add(addLeaveButton);
        buttonPanel.add(deleteLeaveButton);
        buttonPanel.add(viewHistoryButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JTable getLeaveTable() {
        return leaveTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getAddLeaveButton() {
        return addLeaveButton;
    }

    public JButton getDeleteLeaveButton() {
        return deleteLeaveButton;
    }

    public JButton getViewHistoryButton() {
        return viewHistoryButton;
    }
}
