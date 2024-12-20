/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin.GroupConfig;

import static com.mycompany.metatraderbrokeradmin.GroupConfig.SymbolsGroupConfig.model;
import static com.mycompany.metatraderbrokeradmin.GroupConfig.SymbolsGroupConfig.symbolsTableData;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author techninza
 */
public class CommissionsGroupConfig extends javax.swing.JPanel {

    public static Vector<String[]> modelData = new Vector<>();
    public static JSONArray jarray;
    public static DefaultTableModel tablemodel  = new DefaultTableModel();
    int selectedIndex;

    public JTable getjTable1() {
        return jTable1;
    }

    /**
     * Creates new form CommissionsGroupConfig
     */
    public CommissionsGroupConfig() {
        initComponents();
        jarray=new JSONArray();
        tablemodel = new DefaultTableModel();
        if (tablemodel.getColumnCount() == 0) {
            String[] columns = {"name", "symbol", "type", "range", "charge"};
            for (String column : columns) {
                tablemodel.addColumn(column);
            }
        }

//        for (String[] row : modelData) {
//            tablemodel.addRow(row);
//        }
//        for (String[] row : rowData) {
//            tablemodel.addRow(row);
//        }
        jTable1.setModel(tablemodel);

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    selectedIndex = jTable1.rowAtPoint(e.getPoint());
                }
            }
        });
        
        jTable1.setEnabled(false);
    }
    
    
    public CommissionsGroupConfig(JSONArray jsa) {
        initComponents();
        jarray=new JSONArray();
        tablemodel=new DefaultTableModel();
        if (tablemodel.getColumnCount() == 0) {
            String[] columns = {"name", "symbol", "type", "range", "charge"};
            for (String column : columns) {
                tablemodel.addColumn(column);
            }
        }
        
        modelData=new Vector();
        

//        for (String[] row : modelData) {
//            tablemodel.addRow(row);
//        }
//        for (JSONObject row : jsa) {
//            tablemodel.addRow(row);
//        }

        for(int i=0;i<jsa.length();i++)
        {
            JSONObject jso=jsa.getJSONObject(i);
            String symbol=jso.getString("symbol");
            String dealprofit=jso.getString("dealProfit");
            String charge=jso.getString("charge");
            String dealAction=jso.getString("dealAction");
            String name=jso.getString("name");
            String range=jso.getString("range");
            String turnoverCurrency=jso.getString("turnoverCurrency");
            String type=jso.getString("type");
            String dealReason=jso.getString("dealReason");
            String dealEntry=jso.getString("dealEntry");
            String[] allData = {name, symbol, type, range, charge, turnoverCurrency, dealEntry, dealAction, dealReason};
            modelData.add(allData);
            tablemodel.addRow(allData);
        }
        jTable1.setModel(tablemodel);

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    selectedIndex = jTable1.rowAtPoint(e.getPoint());
                }
            }
        });
        
        jTable1.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel1.setText("Please set up commissions for accounts in the group.");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButton1)
                .addGap(48, 48, 48)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(43, 43, 43))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new AddComissionFrame().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.out.println("size: " + modelData.size());
        System.out.println("modelSize: " + tablemodel.getRowCount());
        modelData.remove(selectedIndex);
        tablemodel.removeRow(selectedIndex);
        // System.out.println("modelData: " + jarray.toString());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String[] selectedIndexData = modelData.get(selectedIndex);

        new AddComissionFrame(selectedIndexData, selectedIndex).setVisible(true);


    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
