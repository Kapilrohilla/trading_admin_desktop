/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kapilrohilla
 */
public class orderAndDealInfo extends javax.swing.JPanel {

    /**
     * Creates new form orderAndDealPanel
     */
    public orderAndDealInfo() {
        initComponents();
        introductionTable();
    }

    void introductionTable() {
//        DefaultTableModel tableModel = new DefaultTableModel();
//        
//        tableModel.addColumn("Name");
//        tableModel.addColumn("Description");
//        
//        String[] row1 = {"Position", ""};
//        String[] row2 = {"Order", ""};
//        String[] row3 = {"Deals", ""};
//        
//        tableModel.addRow(row1);
//        tableModel.addRow(row2);
//        tableModel.addRow(row3);

        Object[][] data = {
            {"Position", ""},
            {"Order", ""},
            {"Deals", ""},};

        // Create the column names for the table
        String[] columnNames = {"Name", "Description"};

        // Create the table model
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        JTable jt = new JTable(tableModel);
        jt.setAutoCreateRowSorter(true);
        setLayout(new BorderLayout());

        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    System.out.println("Row " + row + " clicked");
                    if (row == 0) {
//                        new PositionPanel().setVisible(true);
                        BrokerAdmin.splitPane.setRightComponent(new PositionPanel());
                    } else if (row == 1) {
//                        new OrderPanel().setVisible(true);
                        BrokerAdmin.splitPane.setRightComponent(new OrderPanel());
                    } else {
                        BrokerAdmin.splitPane.setRightComponent(new DealPanel());
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(jt);
        add(scrollPane, BorderLayout.CENTER);
        //   add(jt, BorderLayout.CENTER);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}