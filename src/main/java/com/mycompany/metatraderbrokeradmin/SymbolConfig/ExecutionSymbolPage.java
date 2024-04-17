/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin.SymbolConfig;

import com.mycompany.metatraderbrokeradmin.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author techninza
 */
public class ExecutionSymbolPage extends javax.swing.JPanel {

    /**
     * Creates new form ExecutionSymbolPage
     */
    public ExecutionSymbolPage() {
        initComponents();
        setData();
        jComboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    Object item = event.getItem().toString();
                    System.out.println("item-> " + item);
                    if (item != "Instant") {
                        jLabel3.setVisible(false);
                        jComboBox2.setVisible(false);
                        jComboBox3.setVisible(false);
                        jComboBox4.setVisible(false);
                        jLabel4.setVisible(false);
                        jLabel5.setVisible(false);
                        jLabel6.setVisible(false);
                        jLabel7.setVisible(false);
                        jLabel8.setVisible(false);
                        jLabel9.setVisible(false);
                        jLabel10.setVisible(false);
                        jCheckBox1.setVisible(false);
                        jTextField1.setVisible(false);
                    } else {
                        jLabel3.setVisible(true);
                        jLabel4.setVisible(true);
                        jLabel5.setVisible(true);
                        jLabel6.setVisible(true);
                        jLabel7.setVisible(true);
                        jLabel8.setVisible(true);
                        jLabel9.setVisible(true);
                        jLabel10.setVisible(true);
                        jComboBox2.setVisible(true);
                        jComboBox3.setVisible(true);
                        jComboBox4.setVisible(true);
                        jCheckBox1.setVisible(true);
                        jTextField1.setVisible(true);
                    }
                }
            }
        });
    }

//);
//    }
    final String getJSONData(String s, JSONObject js) {
        try {
            return js.getString(s);
        } catch (JSONException e) {
            System.out.println(s + " not found");
            return "";
        }
    }

    public ExecutionSymbolPage(JSONObject js) {
        initComponents();
        String Execution = getJSONData("Execution", js);
        String MaxTimeDeviation = getJSONData("MaxTimeDeviation", js);
        String MaxProfitDeviation = getJSONData("MaxProfitDeviation", js);
        String MaxLosingDeviation = getJSONData("MaxLosingDeviation", js);
        boolean isFastConfirmationChecked = "true".equals(getJSONData("FastConfirmationFlag", js));
        System.out.println("Execution = " + Execution);
        System.out.println("MaxTimeDeviation = " + MaxTimeDeviation);
        System.out.println("Max ProfitDeviation = " + MaxProfitDeviation);
        System.out.println("Maxlosing DEviation = " + MaxLosingDeviation);
        System.out.println(isFastConfirmationChecked);
        jComboBox1.setSelectedItem(Execution);
        jComboBox2.setSelectedItem(MaxTimeDeviation);
        jComboBox3.setSelectedItem(MaxProfitDeviation);
        jComboBox4.setSelectedItem(MaxLosingDeviation);
        jCheckBox1.setSelected(isFastConfirmationChecked);
        setData();
    }

    private void setData() {
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Instant",
            "Request",
            "Market",
            "Exchange"
        }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"2", "0", "1", "3"}));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"2", "0", "1", "3"}));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"2", "0", "1", "3"}));
    }

    public JCheckBox getjCheckBox1() {
        return jCheckBox1;
    }

    public JComboBox<String> getjComboBox1() {
        return jComboBox1;
    }

    public JComboBox<String> getjComboBox2() {
        return jComboBox2;
    }

    public JComboBox<String> getjComboBox3() {
        return jComboBox3;
    }

    public JComboBox<String> getjComboBox4() {
        return jComboBox4;
    }

    public JTextField getjTextField1() {
        return jTextField1;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        jLabel1.setText("The setting up of execution of orders by the symbol. Please specify the execution types and its parameters.");

        jLabel2.setText("Execution : ");

        jLabel3.setText("Max Time deviation:");

        jLabel4.setText("Max Profit deviation:");

        jLabel5.setText("Max Losing deviation:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Request", "Instant", "Market", "Exchange" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCheckBox1.setText("Fast Confirmation of requotes within client deviation");

        jLabel6.setText("Maximum Volume : ");

        jLabel7.setText("before switching to Request execution");

        jLabel8.setText("seconds");

        jLabel9.setText("seconds");

        jLabel10.setText("seconds");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(68, 68, 68)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel9))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel8))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jCheckBox1))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4)
                                    .addComponent(jLabel10))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel7))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
