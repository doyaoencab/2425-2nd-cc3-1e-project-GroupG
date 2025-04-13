package com.mycompany.employeemanagementsystemgui.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PayrollView extends JFrame {
    private DefaultTableModel tableModel;
    private JTable payrollTable;
    private JButton addPayrollButton, deletePayrollButton;

    public PayrollView() {
        setTitle("Payroll Management");
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table Model and Table
        tableModel = new DefaultTableModel(new String[]{"Employee Name", "Hours Worked", "Hourly Rate", "Total Pay"}, 0);
        payrollTable = new JTable(tableModel);
        add(new JScrollPane(payrollTable), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        addPayrollButton = new JButton("Add Payroll");
        deletePayrollButton = new JButton("Delete Payroll");
        buttonPanel.add(addPayrollButton);
        buttonPanel.add(deletePayrollButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JTable getPayrollTable() {
        return payrollTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getAddPayrollButton() {
        return addPayrollButton;
    }

    public JButton getDeletePayrollButton() {
        return deletePayrollButton;
    }
}
