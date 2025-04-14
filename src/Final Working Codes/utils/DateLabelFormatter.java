package com.mycompany.employeemanagementsystemgui.utils;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Object stringToValue(String text) throws java.text.ParseException {
        return dateFormatter.parse(text);
    }

    @Override
    public String valueToString(Object value) {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }
}
