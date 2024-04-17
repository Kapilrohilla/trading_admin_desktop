package com.mycompany.metatraderbrokeradmin;

import java.beans.PropertyChangeEvent;
import java.text.ParseException;
import javax.swing.JFrame;
import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

import java.beans.PropertyChangeListener;
//import org.jdatepicker.impl.JDateModel;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFormattedTextField.AbstractFormatter;

public class DatePicker extends JFrame {
    
    public static void main(String[] args) {
//        new DatePicker();
    }

//    String getDate(){
//        return date;
//    }
    public DatePicker(String text) {
        // Create date model and properties
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        // Create date panel and picker
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // Add datepicker directly to the frame's content pane
        getContentPane().add(datePicker);
        getContentPane().add(datePicker);
        Date selectedDate = (Date) datePicker.getModel().getValue();
        
        if (selectedDate != null) {
            // Do something with the selected date
            System.out.println("Selected date: " + selectedDate);
        } else {
            // Handle the case where no date is selected
            System.out.println("No date selected");
        }
        datePicker.getModel().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("value")) {
                    System.out.println(datePicker.getJFormattedTextField().getText());
                    if (text.equalsIgnoreCase("start1MinHistory")) {
                        ChartPanel.date1 = datePicker.getJFormattedTextField().getText();
                    } else if (text.equalsIgnoreCase("end1MinHistory")) {
                        ChartPanel.date2 = datePicker.getJFormattedTextField().getText();
                    } else if (text.equalsIgnoreCase("startBidAsk")) {
                        BidAskLastPanel.startDate = datePicker.getJFormattedTextField().getText();
                    } else if (text.equalsIgnoreCase("endBidAsk")) {
                        BidAskLastPanel.endDate = datePicker.getJFormattedTextField().getText();
                    }
                    dispose();
                }
            }
        });

        // Set frame properties
        setTitle("JDatePicker Demo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(250, 400);  // Set initial size
        setLocationRelativeTo(null);
        pack();  // Adjust layout
        setVisible(true);
    }
    
    class DateLabelFormatter extends AbstractFormatter {
        
        final private String datePattern = "YYYY-MM-dd";
        final private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
        
        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }
        
        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }
    }
    
}


/*
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
//}
 */
