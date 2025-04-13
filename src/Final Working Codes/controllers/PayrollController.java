package com.mycompany.employeemanagementsystemgui.controllers;

import com.mycompany.employeemanagementsystemgui.models.PayrollModel;
import com.mycompany.employeemanagementsystemgui.views.PayrollView;
import com.mycompany.employeemanagementsystemgui.viewmodels.PayrollViewModel;

import javax.swing.*;
import java.awt.*;

public class PayrollController {
    private PayrollView view;
    private PayrollViewModel viewModel;

    public PayrollController() {
        view = new PayrollView();
        viewModel = new PayrollViewModel();

        loadTableData();

        view.getAddPayrollButton().addActionListener(e -> openAddPayrollFrame());
        view.getDeletePayrollButton().addActionListener(e -> deletePayrollEntry());
    }

    private void loadTableData() {
        for (PayrollModel payroll : viewModel.getPayrollList()) {
            view.getTableModel().addRow(new Object[]{
                payroll.getEmployeeName(),
                payroll.getHoursWorked(),
                payroll.getHourlyRate(),
                String.format("%.2f", payroll.getTotalPay())
            });
        }
    }

    private void openAddPayrollFrame() {
        JFrame addFrame = new JFrame("Add Payroll");
        addFrame.setSize(400, 250);
        addFrame.setLocationRelativeTo(view);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        JTextField nameField = new JTextField();
        JTextField hoursField = new JTextField();
        JTextField rateField = new JTextField();
        JButton saveButton = new JButton("Save");

        panel.add(new JLabel("Employee Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Hours Worked (in 15 days):"));
        panel.add(hoursField);
        panel.add(new JLabel("Hourly Rate:"));
        panel.add(rateField);
        panel.add(new JLabel());
        panel.add(saveButton);

        addFrame.add(panel);
        addFrame.setVisible(true);

        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String hoursText = hoursField.getText().trim();
            String rateText = rateField.getText().trim();
            if (!name.isEmpty() && !hoursText.isEmpty() && !rateText.isEmpty()) {
                try {
                    double hours = Double.parseDouble(hoursText);
                    double rate = Double.parseDouble(rateText);
                    PayrollModel newPayroll = new PayrollModel(name, hours, rate);

                    viewModel.addPayroll(newPayroll);
                    view.getTableModel().addRow(new Object[]{name, hours, rate, String.format("%.2f", newPayroll.getTotalPay())});
                    addFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(addFrame, "Please enter valid numbers for hours and rate.");
                }
            } else {
                JOptionPane.showMessageDialog(addFrame, "Please fill in all fields.");
            }
        });
    }

    private void deletePayrollEntry() {
        int row = view.getPayrollTable().getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this payroll entry?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                viewModel.deletePayroll(row);
                view.getTableModel().removeRow(row);
                JOptionPane.showMessageDialog(view, "Payroll entry deleted.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select a payroll entry to delete.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PayrollController::new);
    }
}
