package com.mycompany.employeemanagementsystemgui.controllers;

import com.mycompany.employeemanagementsystemgui.views.CalendarView;
import com.mycompany.employeemanagementsystemgui.viewmodels.CalendarViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CalendarController {
    private CalendarView view;
    private CalendarViewModel viewModel;
    
    private static int currentMonth = 0; // January = 0
    private static final int YEAR = 2025;
    private int selectedDay = -1;
    private JLabel selectedLabel = null;
    private Map<String, JLabel> dayLabels;
    
    public CalendarController() {
        view = new CalendarView();
        viewModel = new CalendarViewModel();
        dayLabels = new HashMap<>();
        
        updateCalendar();
        
        view.getPrevButton().addActionListener(e -> {
            if (currentMonth > 0) {
                currentMonth--;
                updateCalendar();
            }
        });
        
        view.getNextButton().addActionListener(e -> {
            if (currentMonth < 11) {
                currentMonth++;
                updateCalendar();
            }
        });
        
        view.getAddEventButton().addActionListener(e -> {
            if (selectedDay == -1) {
                JOptionPane.showMessageDialog(view, "Please select a day to add an event.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            JDialog eventDialog = new JDialog(view, "Add Event", true);
            eventDialog.setSize(300, 200);
            eventDialog.setLocationRelativeTo(view);
            
            JPanel dialogPanel = new JPanel(new GridLayout(3, 2));
            JLabel dateLabel = new JLabel("Date: " + selectedDay + " " + view.getMonthLabel().getText().split(" ")[0]);
            JTextField eventField = new JTextField();
            JButton saveButton = new JButton("Save Event");
            
            dialogPanel.add(dateLabel);
            dialogPanel.add(new JLabel());
            dialogPanel.add(new JLabel("Event Details:"));
            dialogPanel.add(eventField);
            
            eventDialog.add(dialogPanel, BorderLayout.CENTER);
            
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(saveButton);
            eventDialog.add(buttonPanel, BorderLayout.SOUTH);
            
            saveButton.addActionListener(ev -> {
                String eventDetails = eventField.getText();
                if (eventDetails.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Please enter event details.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String eventKey = selectedDay + "-" + currentMonth;
                    viewModel.addEvent(eventKey, eventDetails);
                    
                    if (dayLabels.containsKey(eventKey)) {
                        JLabel dayLabel = dayLabels.get(eventKey);
                        dayLabel.setBackground(Color.YELLOW);
                        dayLabel.setToolTipText(eventDetails);
                    }
                    
                    updateCalendar();
                    eventDialog.dispose();
                }
            });
            
            eventDialog.setVisible(true);
        });
    }
    
    private void updateCalendar() {
        JPanel panel = view.getPanel();
        panel.removeAll();
        dayLabels.clear();
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR, currentMonth, 1);
        
        String[] monthNames = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
        view.getMonthLabel().setText(monthNames[currentMonth] + " " + YEAR);
        
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : days) {
            JLabel label = new JLabel(day, SwingConstants.CENTER);
            label.setFont(new Font("SansSerif", Font.BOLD, 14));
            panel.add(label);
        }
        
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        for (int i = 1; i < firstDayOfWeek; i++) {
            panel.add(new JLabel());
        }
        
        for (int day = 1; day <= daysInMonth; day++) {
            JLabel label = new JLabel(String.valueOf(day), SwingConstants.CENTER);
            label.setOpaque(true);
            label.setBackground(Color.WHITE);
            label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            
            String eventKey = day + "-" + currentMonth;
            if (viewModel.getEvents().containsKey(eventKey)) {
                label.setText(label.getText() + " *");
                label.setToolTipText(viewModel.getEvent(eventKey));
                label.setBackground(Color.RED);
            }
            
            final int dayOfMonth = day;
            label.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent e) {
                    if (selectedLabel != null) {
                        selectedLabel.setBackground(Color.WHITE);
                        if (viewModel.getEvents().containsKey(selectedDay + "-" + currentMonth)) {
                            selectedLabel.setBackground(Color.YELLOW);
                        }
                    }
                    selectedDay = dayOfMonth;
                    selectedLabel = label;
                    label.setBackground(Color.GRAY);
                }
                
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        String eventKey = dayOfMonth + "-" + currentMonth;
                        if (viewModel.getEvents().containsKey(eventKey)) {
                            JOptionPane.showMessageDialog(view,
                                    "Event for " + dayOfMonth + " " + view.getMonthLabel().getText().split(" ")[0] + ":\n" + viewModel.getEvent(eventKey),
                                    "Event Details", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(view,
                                    "No event for this day.",
                                    "Event Details", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            });
            
            panel.add(label);
            dayLabels.put(day + "-" + currentMonth, label);
        }
        
        int totalCells = 42;
        int filled = (firstDayOfWeek - 1) + daysInMonth;
        for (int i = filled; i < totalCells; i++) {
            panel.add(new JLabel());
        }
        
        panel.revalidate();
        panel.repaint();
    }
}