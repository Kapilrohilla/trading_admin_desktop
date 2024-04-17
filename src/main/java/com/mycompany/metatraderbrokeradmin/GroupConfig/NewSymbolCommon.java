/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin.GroupConfig;

import com.mycompany.metatraderbrokeradmin.GroupSymbolpopup.PopupTree4Symbol;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Kapil
 */
public class NewSymbolCommon extends javax.swing.JPanel {

    public static ArrayList<String> categorylistsymbol = new ArrayList();

    public JCheckBox getjCheckBox4() {
        return jCheckBox4;
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

    public JComboBox<String> getjComboBox5() {
        return jComboBox5;
    }

    public JComboBox<String> getjComboBox6() {
        return jComboBox6;
    }

    public JComboBox<String> getjComboBox7() {
        return jComboBox7;
    }

    public JTextField getBidTextField() {
        return bidInput;
    }

    public JTextField getAskTextField() {
        return askInput;
    }

    public JCheckBox getjCheckBox5() {
        return jCheckBox5;
    }

    public static JLabel getjLabel12() {
        return jLabel12;
    }

    /**
     * Creates new form NewSymbolCommon
     */
    public NewSymbolCommon() {
        initComponents();
//        jCheckBox5.setSelected(true);
//        jCheckBox4.setEnabled(false);
//        jComboBox2.setEnabled(false);
//        jComboBox3.setEnabled(false);
//        jComboBox4.setEnabled(false);
//        jComboBox5.setEnabled(false);
//        jComboBox6.setEnabled(false);
//        jComboBox7.setEnabled(false);
//        bidInput.setEnabled(false);
//        askInput.setEnabled(false);
//        jCheckBox5.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    jCheckBox1.setEnabled(false);
//                    jCheckBox2.setEnabled(false);
//                    jCheckBox3.setEnabled(false);
//                    jCheckBox4.setEnabled(false);
//                    jComboBox2.setEnabled(false);
//                    jComboBox3.setEnabled(false);
//                    jComboBox7.setEnabled(false);
//                    bidInput.setEnabled(false);
//                    askInput.setEnabled(false);
//
//                } else {
//                    jCheckBox1.setEnabled(true);
//                    jCheckBox2.setEnabled(true);
//                    jCheckBox3.setEnabled(true);
//                    jCheckBox4.setEnabled(true);
//                    jComboBox2.setEnabled(true);
//                    jComboBox6.setEnabled(true);
//                    jComboBox7.setEnabled(true);
//                    bidInput.setEnabled(true);
//                    askInput.setEnabled(true);
//                }
//            }
//        });

    }

    String getJSONData(JSONObject jso, String key) {
        try {
            String keyData = jso.getString(key);
            return keyData;
        } catch (JSONException ex) {
            System.out.println("key: " + key + " not found");
            return "Not found";
        }
    }

    public NewSymbolCommon(JSONObject jso) {
        initComponents();

        System.out.println("jso: " + jso);
        //jCheckBox5.setSelected(true);
//        jCheckBox1.setEnabled(false);
//        jCheckBox2.setEnabled(false);
//        jCheckBox3.setEnabled(false);
//        jCheckBox4.setEnabled(false);
//        jComboBox2.setEnabled(false);
//        jComboBox3.setEnabled(false);
//        jComboBox4.setEnabled(false);
//        jComboBox5.setEnabled(false);
//        jComboBox6.setEnabled(false);
//        jComboBox7.setEnabled(false);
//        bidInput.setEnabled(false);
//        askInput.setEnabled(false);
        try {
            String symName = getJSONData(jso, "Symbol");
            if (symName != "Not found") {
                jLabel12.setText(symName);
            }
            JSONObject spreadBalance = jso.getJSONObject("SpreadBalance");
            String bid = spreadBalance.getFloat("bid") + "";
            String ask = spreadBalance.getFloat("ask") + "";
            boolean useDefaultPanelSettigs = jso.getBoolean("useDefaultPanelSettings");
//            boolean enableMarketDepthFlag = jso.getBoolean("enableMarketDepthFlag");
//            boolean useDefaultSpreadFlag = jso.getBoolean("useDefaultSpreadFlag");
//            boolean useDefaultVolume = jso.getBoolean("useDefaultVolumeFlag");
            String marketDepthLimit = getJSONData(jso, "MarketDepth");
            String minVolume = getJSONData(jso, "MinVolumeTrade");
            String stepVolume = getJSONData(jso, "StepVolumeTrade");
            String maxVolume = getJSONData(jso, "MaxVolumeTrade");
            String limitVolume = getJSONData(jso, "LimitVolumeTrade");
//            String spreadDifference = getJSONData(jso, "spreadDifference"); // need to send
//            boolean useDefaultLimit = jso.getBoolean("") // remaining to send
            System.out.println("bid: " + bid);
            System.out.println("ask: " + ask);
            System.out.println("useDefault settings: " + useDefaultPanelSettigs);

            bidInput.setText(bid);
            askInput.setText(ask);
            jCheckBox5.setSelected(useDefaultPanelSettigs);
            jCheckBox4.setSelected(true); // remaining to send
            jComboBox2.setSelectedItem(marketDepthLimit);

            jComboBox4.setSelectedItem(minVolume);
            jComboBox5.setSelectedItem(stepVolume);
            jComboBox6.setSelectedItem(maxVolume);
            jComboBox7.setSelectedItem(limitVolume);
        } catch (JSONException ex) {
            System.out.println("ex: " + ex.getMessage());
        }
//        jCheckBox5.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    jCheckBox1.setEnabled(false);
//                    jCheckBox2.setEnabled(false);
//                    jCheckBox3.setEnabled(false);
//                    jCheckBox4.setEnabled(false);
//                    jComboBox2.setEnabled(false);
//                    jComboBox3.setEnabled(false);
//                    jComboBox4.setEnabled(false);
//                    jComboBox5.setEnabled(false);
//                    jComboBox6.setEnabled(false);
//                    jComboBox7.setEnabled(false);
//                    bidInput.setEnabled(false);
//                    askInput.setEnabled(false);
//
//                } else {
//                    jCheckBox1.setEnabled(true);
//                    jCheckBox2.setEnabled(true);
//                    jCheckBox3.setEnabled(true);
//                    jCheckBox4.setEnabled(true);
//                    jComboBox2.setEnabled(true);
//                    jComboBox3.setEnabled(true);
//                    jComboBox4.setEnabled(true);
//                    jComboBox5.setEnabled(true);
//                    jComboBox6.setEnabled(true);
//                    jComboBox7.setEnabled(true);
//                    bidInput.setEnabled(true);
//                    askInput.setEnabled(true);
//                }
//            }
//        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        bidInput = new javax.swing.JTextField();
        askInput = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jLabel1.setText("Please setup up main paramters of symbols for the group.");

        jLabel2.setText("Symbol:");

        jComboBox2.setEditable(true);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unlimited" }));

        jLabel3.setText("Market depth limit:");

        jLabel4.setText("Spread difference:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0" }));
        jComboBox3.setEnabled(false);

        jLabel5.setText("pt");

        jLabel8.setText("Minimum:");

        jComboBox4.setEditable(true);
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1.00" }));

        jLabel9.setText("Step:");

        jComboBox5.setEditable(true);
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1.00" }));

        jLabel10.setText("Maximum:");

        jComboBox6.setEditable(true);
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1.00" }));

        jCheckBox4.setText("Use default limit");

        jLabel11.setText("Limit:");

        jComboBox7.setEditable(true);
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0.00" }));

        jButton1.setText("Select Symbol");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel12.setText("Select Symbol");

        jCheckBox5.setText("Use default common settings.");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jLabel13.setText("Difference Balance:");

        bidInput.setMinimumSize(new java.awt.Dimension(70, 22));
        bidInput.setPreferredSize(new java.awt.Dimension(70, 22));

        askInput.setMinimumSize(new java.awt.Dimension(70, 22));
        askInput.setPreferredSize(new java.awt.Dimension(70, 22));

        jLabel6.setText("Bid");

        jLabel7.setText("Ask");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBox4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bidInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(askInput, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel12)
                                .addGap(45, 45, 45)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox5))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jButton1)
                        .addComponent(jLabel12))
                    .addComponent(jCheckBox5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel13)
                    .addComponent(bidInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(askInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new PopupTree4Symbol("GroupNewSymbol").setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField askInput;
    private javax.swing.JTextField bidInput;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    public static javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
