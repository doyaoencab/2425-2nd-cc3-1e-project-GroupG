package com.mycompany.employeemanagementsystemgui.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RecruitmentView extends JFrame {
    private DefaultTableModel tableModel;
    private JTable recruitmentTable;
    private JButton addRecruitmentButton, editRecruitmentButton, deleteRecruitmentButton;

    public RecruitmentView() {
        setTitle("Employee Recruitment Management");
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"Employee Name", "Position", "Interview Date", "Status"}, 0);
        recruitmentTable = new JTable(tableModel);
        add(new JScrollPane(recruitmentTable), BorderLayout.CENTER);

        // Buttons setup
        JPanel buttonPanel = new JPanel();
        addRecruitmentButton = new JButton("Add Recruitment");
        editRecruitmentButton = new JButton("Edit Recruitment");
        deleteRecruitmentButton = new JButton("Delete Recruitment");
        buttonPanel.add(addRecruitmentButton);
        buttonPanel.add(editRecruitmentButton);
        buttonPanel.add(deleteRecruitmentButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JTable getRecruitmentTable() {
        return recruitmentTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getAddRecruitmentButton() {
        return addRecruitmentButton;
    }

    public JButton getEditRecruitmentButton() {
        return editRecruitmentButton;
    }

    public JButton getDeleteRecruitmentButton() {
        return deleteRecruitmentButton;
    }
}
