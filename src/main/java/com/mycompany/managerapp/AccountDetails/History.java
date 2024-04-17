/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.managerapp.AccountDetails;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kapil
 */
public class History extends javax.swing.JPanel {

    public History() {
        initComponents();
        table();
    }

    final void table() {
        DefaultTableModel model = new DefaultTableModel();

        String columns[] = {"Time", "Deals", "Order", "Symbols", "Type Balance", "Entry", "Volume", "Price", "Profit"};
        for (String column : columns) {
            model.addColumn(column);
        }

        for (int i = 0; i < 5; i++) {
            String rowData[] = {"12-01-1010", "Deals", "OrderName", "XAUUSD", "tb", "entry", "volume", "price", "100%"};
            model.addRow(rowData);
        }

        JTable jt = new JTable(model);
        JScrollPane jsp = new JScrollPane(jt);
        setLayout(new BorderLayout());
        add(jsp, BorderLayout.CENTER);

    }

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
