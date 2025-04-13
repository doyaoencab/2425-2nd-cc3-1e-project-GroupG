package com.mycompany.employeemanagementsystemgui.controllers;

import com.mycompany.employeemanagementsystemgui.models.EmployeeModel;
import com.mycompany.employeemanagementsystemgui.views.EmployeeView;
import com.mycompany.employeemanagementsystemgui.viewmodels.EmployeeViewModel;

import javax.swing.*;
import java.awt.GridLayout;

public class EmployeeController {
    private EmployeeView view;
    private EmployeeViewModel viewModel;

    public EmployeeController() {
        view = new EmployeeView();
        viewModel = new EmployeeViewModel();

        // Load employee data into the table
        loadTableData();

        // Add button actions
        view.getAddButton().addActionListener(e -> openAddEmployeeFrame());
        view.getUpdateButton().addActionListener(e -> openUpdateEmployeeFrame());
        view.getDeleteButton().addActionListener(e -> deleteSelectedEmployee());
    }

    private void loadTableData() {
        for (EmployeeModel employee : viewModel.getEmployeeList()) {
            view.getTableModel().addRow(new String[]{employee.getId(), employee.getName(), employee.getPosition()});
        }
    }

    private void openAddEmployeeFrame() {
        JFrame addFrame = new JFrame("Add Employee");
        addFrame.setSize(300, 200);
        addFrame.setLocationRelativeTo(view);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField positionField = new JTextField();
        JButton saveButton = new JButton("Save");

        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Position:"));
        panel.add(positionField);
        panel.add(new JLabel());
        panel.add(saveButton);

        addFrame.add(panel);
        addFrame.setVisible(true);

        saveButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String position = positionField.getText().trim();
            if (!id.isEmpty() && !name.isEmpty() && !position.isEmpty()) {
                EmployeeModel newEmployee = new EmployeeModel(id, name, position);
                viewModel.addEmployee(newEmployee);
                view.getTableModel().addRow(new String[]{id, name, position});
                addFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(addFrame, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void openUpdateEmployeeFrame() {
        int row = view.getTable().getSelectedRow();
        if (row >= 0) {
            String id = (String) view.getTableModel().getValueAt(row, 0);
            String name = (String) view.getTableModel().getValueAt(row, 1);
            String position = (String) view.getTableModel().getValueAt(row, 2);

            JFrame updateFrame = new JFrame("Update Employee");
            updateFrame.setSize(300, 200);
            updateFrame.setLocationRelativeTo(view);

            JPanel panel = new JPanel(new GridLayout(4, 2));
            JTextField idField = new JTextField(id);
            JTextField nameField = new JTextField(name);
            JTextField positionField = new JTextField(position);
            JButton saveButton = new JButton("Save");

            panel.add(new JLabel("ID:"));
            panel.add(idField);
            panel.add(new JLabel("Name:"));
            panel.add(nameField);
            panel.add(new JLabel("Position:"));
            panel.add(positionField);
            panel.add(new JLabel());
            panel.add(saveButton);

            updateFrame.add(panel);
            updateFrame.setVisible(true);

            saveButton.addActionListener(e -> {
                String newId = idField.getText().trim();
                String newName = nameField.getText().trim();
                String newPosition = positionField.getText().trim();
                if (!newId.isEmpty() && !newName.isEmpty() && !newPosition.isEmpty()) {
                    EmployeeModel updatedEmployee = new EmployeeModel(newId, newName, newPosition);
                    viewModel.updateEmployee(row, updatedEmployee);

                    view.getTableModel().setValueAt(newId, row, 0);
                    view.getTableModel().setValueAt(newName, row, 1);
                    view.getTableModel().setValueAt(newPosition, row, 2);
                    updateFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(updateFrame, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        } else {
            JOptionPane.showMessageDialog(view, "Please select an employee to update.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteSelectedEmployee() {
        int row = view.getTable().getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this employee?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                viewModel.deleteEmployee(row);
                view.getTableModel().removeRow(row);
                JOptionPane.showMessageDialog(view, "Employee deleted.");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select an employee to delete.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmployeeController::new);
    }
}
