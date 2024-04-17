/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin.orderndeals;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author techninza
 */
public class TicksPanel extends javax.swing.JPanel {

    /**
     * Creates new form TicksPanel
     */
    public TicksPanel() {
        //initComponents();
        tabledata();
    }

    public TicksPanel(JSONArray tableData) {
        System.out.println("quotepanel tableData = " + tableData);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Bid");
        model.addColumn("Ask");
        model.addColumn("Last");
        model.addColumn("Volume");

        for (int i = 0; i < tableData.length(); i++) {
            JSONObject js = tableData.getJSONObject(i);
            String bid = js.getFloat("bid") + "";
            String last = js.getFloat("ltp") + "";
            String ask = js.getFloat("ask") + "";
            String date = js.getString("createdAt");
            String volume = js.getFloat("volume") + "";
            String[] rowData = {date, bid, ask, last, volume};
            model.addRow(rowData);
        }

        JTable jTable1 = new JTable(model);
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(jTable1);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void tabledata() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Bid");
        model.addColumn("Ask");
        model.addColumn("Last");
        model.addColumn("Volume");

        Object[][] data = {
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},
            {"2019.02.06 09:58:18", "0.93906", "0.94006", "0.00000", "0.00"},};

        // Create the column names for the table
        String[] columnNames = {"Date", "Bid", "Ask", "Last", "Volume"};

        // Create the table model
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        JTable jTable1 = new JTable(tableModel);
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(jTable1);
        add(scrollPane, BorderLayout.CENTER);
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