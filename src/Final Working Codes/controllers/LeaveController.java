package com.mycompany.employeemanagementsystemgui.controllers;

import com.mycompany.employeemanagementsystemgui.models.LeaveModel;
import com.mycompany.employeemanagementsystemgui.views.LeaveView;
import com.mycompany.employeemanagementsystemgui.viewmodels.LeaveViewModel;
import com.mycompany.employeemanagementsystemgui.utils.DateLabelFormatter;

import javax.swing.*;
import org.jdatepicker.impl.*;
import java.awt.*;
import java.util.Properties;
import javax.swing.table.DefaultTableModel;

public class LeaveController {
    private LeaveView view;
    private LeaveViewModel viewModel;

    public LeaveController() {
        view = new LeaveView();
        viewModel = new LeaveViewModel();

        loadTableData();

        view.getAddLeaveButton().addActionListener(e -> openAddLeaveFrame());
        view.getDeleteLeaveButton().addActionListener(e -> deleteLeaveEntry());
        view.getViewHistoryButton().addActionListener(e -> openLeaveHistoryFrame());
    }

    private void loadTableData() {
        for (LeaveModel leave : viewModel.getLeaveHistory()) {
            view.getTableModel().addRow(new Object[]{leave.getEmployeeName(), leave.getLeaveType(), leave.getStartDate(), leave.getEndDate()});
        }
    }

    private void openAddLeaveFrame() {
        JFrame addFrame = new JFrame("Add Leave");
        addFrame.setSize(400, 300);
        addFrame.setLocationRelativeTo(view);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Employee Name:"));
        JTextField nameField = new JTextField(15);
        namePanel.add(nameField);

        JPanel leaveTypePanel = new JPanel();
        leaveTypePanel.add(new JLabel("Leave Type:"));
        String[] leaveTypes = {"Vacation", "Sick Leave", "Maternity", "Other"};
        JComboBox<String> leaveTypeBox = new JComboBox<>(leaveTypes);
        leaveTypePanel.add(leaveTypeBox);

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        UtilDateModel startModel = new UtilDateModel();
        JDatePanelImpl startDatePanel = new JDatePanelImpl(startModel, p);
        JDatePickerImpl startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());

        JPanel startDatePanelWrapper = new JPanel();
        startDatePanelWrapper.add(new JLabel("Start Date:"));
        startDatePanelWrapper.add(startDatePicker);

        UtilDateModel endModel = new UtilDateModel();
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endModel, p);
        JDatePickerImpl endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());

        JPanel endDatePanelWrapper = new JPanel();
        endDatePanelWrapper.add(new JLabel("End Date:"));
        endDatePanelWrapper.add(endDatePicker);

        JButton saveButton = new JButton("Save");

        panel.add(namePanel);
        panel.add(leaveTypePanel);
        panel.add(startDatePanelWrapper);
        panel.add(endDatePanelWrapper);
        panel.add(saveButton);

        addFrame.add(panel);
        addFrame.setVisible(true);

        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String leaveType = (String) leaveTypeBox.getSelectedItem();
            String startDate = startDatePicker.getJFormattedTextField().getText();
            String endDate = endDatePicker.getJFormattedTextField().getText();

            if (!name.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty()) {
                LeaveModel newLeave = new LeaveModel(name, leaveType, startDate, endDate);
                viewModel.addLeave(newLeave);
                view.getTableModel().addRow(new Object[]{name, leaveType, startDate, endDate});
                addFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(addFrame, "Please fill in all fields.");
            }
        });
    }

    private void deleteLeaveEntry() {
        int row = view.getLeaveTable().getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this leave entry?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                viewModel.deleteLeave(row);
                view.getTableModel().removeRow(row);
                JOptionPane.showMessageDialog(view, "Leave entry deleted.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select a leave entry to delete.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void openLeaveHistoryFrame() {
        JFrame historyFrame = new JFrame("Leave History");
        historyFrame.setSize(500, 500);
        historyFrame.setLocationRelativeTo(view);

        DefaultTableModel historyTableModel = new DefaultTableModel(new String[]{"Employee Name", "Leave Type", "Start Date", "End Date"}, 0);
        JTable historyTable = new JTable(historyTableModel);
        JScrollPane scrollPane = new JScrollPane(historyTable);
        historyFrame.add(scrollPane);

        for (LeaveModel leave : viewModel.getLeaveHistory()) {
            historyTableModel.addRow(new Object[]{leave.getEmployeeName(), leave.getLeaveType(), leave.getStartDate(), leave.getEndDate()});
        }

        historyFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LeaveController::new);
    }
}
