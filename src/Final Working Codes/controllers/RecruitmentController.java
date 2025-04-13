package com.mycompany.employeemanagementsystemgui.controllers;

import com.mycompany.employeemanagementsystemgui.models.RecruitmentModel;
import com.mycompany.employeemanagementsystemgui.views.RecruitmentView;
import com.mycompany.employeemanagementsystemgui.viewmodels.RecruitmentViewModel;
import org.jdatepicker.impl.*;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class RecruitmentController {
    private RecruitmentView view;
    private RecruitmentViewModel viewModel;

    public RecruitmentController() {
        view = new RecruitmentView();
        viewModel = new RecruitmentViewModel();

        loadTableData();

        view.getAddRecruitmentButton().addActionListener(e -> openRecruitmentForm(false, -1));
        view.getEditRecruitmentButton().addActionListener(e -> {
            int selectedRow = view.getRecruitmentTable().getSelectedRow();
            if (selectedRow >= 0) {
                openRecruitmentForm(true, selectedRow);
            } else {
                JOptionPane.showMessageDialog(view, "Please select a recruitment entry to edit.");
            }
        });
        view.getDeleteRecruitmentButton().addActionListener(e -> deleteRecruitmentEntry());
    }

    private void loadTableData() {
        for (RecruitmentModel recruitment : viewModel.getRecruitmentList()) {
            view.getTableModel().addRow(new Object[]{
                recruitment.getEmployeeName(),
                recruitment.getPosition(),
                recruitment.getInterviewDate(),
                recruitment.getStatus()
            });
        }
    }

    private void openRecruitmentForm(boolean isEdit, int rowIndex) {
        JFrame formFrame = new JFrame(isEdit ? "Edit Recruitment" : "Add Recruitment");
        formFrame.setSize(450, 300);
        formFrame.setLocationRelativeTo(view);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nameField = new JTextField();
        JTextField positionField = new JTextField();

        UtilDateModel dateModel = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new com.mycompany.employeemanagementsystemgui.utils.RecruitmentDateFormatter());

        String[] statusOptions = {"N/A", "Hired", "Not Hired"};
        JComboBox<String> statusBox = new JComboBox<>(statusOptions);

        if (isEdit && rowIndex >= 0) {
            RecruitmentModel recruitment = viewModel.getRecruitmentList().get(rowIndex);
            nameField.setText(recruitment.getEmployeeName());
            positionField.setText(recruitment.getPosition());
            try {
                java.util.Date parsedDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(recruitment.getInterviewDate());
                dateModel.setValue(parsedDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
            statusBox.setSelectedItem(recruitment.getStatus());
        }

        JButton saveButton = new JButton("Save");

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Employee Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Position:"), gbc);
        gbc.gridx = 1;
        panel.add(positionField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Interview Date:"), gbc);
        gbc.gridx = 1;
        panel.add(datePicker, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        panel.add(statusBox, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(saveButton, gbc);

        formFrame.add(panel);
        formFrame.setVisible(true);

                saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String position = positionField.getText();
            String interviewDate = datePicker.getJFormattedTextField().getText();
            String status = (String) statusBox.getSelectedItem();

            if (!name.isEmpty() && !position.isEmpty() && !interviewDate.isEmpty()) {
                RecruitmentModel recruitment = new RecruitmentModel(name, position, interviewDate, status);

                if (isEdit) {
                    viewModel.editRecruitment(rowIndex, recruitment);
                    view.getTableModel().setValueAt(name, rowIndex, 0);
                    view.getTableModel().setValueAt(position, rowIndex, 1);
                    view.getTableModel().setValueAt(interviewDate, rowIndex, 2);
                    view.getTableModel().setValueAt(status, rowIndex, 3);
                    JOptionPane.showMessageDialog(view, "Recruitment entry updated.");
                } else {
                    viewModel.addRecruitment(recruitment);
                    view.getTableModel().addRow(new Object[]{name, position, interviewDate, status});
                    JOptionPane.showMessageDialog(view, "Recruitment entry added.");
                }
                formFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(formFrame, "Please fill in all fields.");
            }
        });
    }

    private void deleteRecruitmentEntry() {
        int row = view.getRecruitmentTable().getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this recruitment entry?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                viewModel.deleteRecruitment(row);
                view.getTableModel().removeRow(row);
                JOptionPane.showMessageDialog(view, "Recruitment entry deleted.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select a recruitment entry to delete.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RecruitmentController::new);
    }
}
