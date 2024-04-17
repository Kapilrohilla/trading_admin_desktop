/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin.Customer;

import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author kapilrohilla
 */
public class RegulationPanel extends javax.swing.JPanel {

    /**
     * Creates new form RegulationPanel
     */
    public RegulationPanel() {
        initComponents();

        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nationality = jComboBox1.getSelectedItem().toString();
                String taxId = jTextField1.getText();
                String employmentStatus = jComboBox2.getSelectedItem().toString();
                String employmentIndustry = jComboBox3.getSelectedItem().toString();
                String educationLevel = jComboBox4.getSelectedItem().toString();
                String wealthSource = jComboBox5.getSelectedItem().toString();
                String netWorth = jTextField2.getText();
                String annualIncome = jTextField3.getText();
                String annualDeposit = jTextField4.getText();
                String forex = jComboBox5.getSelectedItem().toString();
                String stocks = jComboBox5.getSelectedItem().toString();
                String futures = jComboBox5.getSelectedItem().toString();
                String cfd = jComboBox5.getSelectedItem().toString();

                JSONObject js = new JSONObject();

                js.put("nationality", nationality);
                js.put("taxId", taxId);
                js.put("employementStatus", employmentStatus);
                js.put("employementIndustry", employmentIndustry);
                js.put("educationLevel", educationLevel);
                js.put("sourceOfWealth", wealthSource);
                js.put("netWorth", netWorth);
                js.put("annualIncome", annualIncome);
                js.put("annualDeposite", annualDeposit);
                js.put("forex", forex);
                js.put("stocks", stocks);
                js.put("futures", futures);
                js.put("cfd", cfd);
                try {
                    postData(js);
                } catch (JSONException ex) {
                    Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    final String getDataFromJSON(String field, JSONObject json) {
        try {
            return json.getString(field);
        } catch (JSONException | NullPointerException ex) {
            return "";
        }
    }

    public RegulationPanel(JSONObject clientDetais) {
        initComponents();
        // setting data
        jComboBox1.setSelectedItem(getDataFromJSON("nationality", clientDetais));
        jTextField1.setText(getDataFromJSON("taxId", clientDetais));
        jComboBox2.setSelectedItem(getDataFromJSON("employementStatus", clientDetais));
        jComboBox3.setSelectedItem(getDataFromJSON("employementIndustry", clientDetais));
        jComboBox4.setSelectedItem(getDataFromJSON("educationLevel", clientDetais));
        jComboBox5.setSelectedItem(getDataFromJSON("sourceOfWealth", clientDetais));
        jTextField2.setText(getDataFromJSON("netWorth", clientDetais));
        jTextField3.setText(getDataFromJSON("annualIncome", clientDetais));
        jTextField4.setText(getDataFromJSON("annualDeposite", clientDetais));
        jComboBox6.setSelectedItem(getDataFromJSON("forex", clientDetais));
        jComboBox7.setSelectedItem(getDataFromJSON("stocks", clientDetais));
        jComboBox8.setSelectedItem(getDataFromJSON("futures", clientDetais));
        jComboBox9.setSelectedItem(getDataFromJSON("cfd", clientDetais));

        // event
        updateBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nationality = jComboBox1.getSelectedItem().toString();
                String taxId = jTextField1.getText();
                String employmentStatus = jComboBox2.getSelectedItem().toString();
                String employmentIndustry = jComboBox3.getSelectedItem().toString();
                String educationLevel = jComboBox4.getSelectedItem().toString();
                String wealthSource = jComboBox5.getSelectedItem().toString();
                String netWorth = jTextField2.getText();
                String annualIncome = jTextField3.getText();
                String annualDeposit = jTextField4.getText();
                String forex = jComboBox6.getSelectedItem().toString();
                String stocks = jComboBox7.getSelectedItem().toString();
                String futures = jComboBox8.getSelectedItem().toString();
                String cfd = jComboBox9.getSelectedItem().toString();

                JSONObject js = new JSONObject();
                js.put("clientId", clientDetais.getString("_id"));
                js.put("nationality", nationality);
                js.put("taxId", taxId);
                js.put("employementStatus", employmentStatus);
                js.put("employementIndustry", employmentIndustry);
                js.put("educationLevel", educationLevel);
                js.put("sourceOfWealth", wealthSource);
                js.put("netWorth", netWorth);
                js.put("annualIncome", annualIncome);
                js.put("annualDeposite", annualDeposit);
                js.put("forex", forex);
                js.put("stocks", stocks);
                js.put("futures", futures);
                js.put("cfd", cfd);
                try {
                    postData(js);
                } catch (JSONException ex) {
                    Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    void postData(JSONObject js) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String apiUrl = APIs.UPDATE_CLIENT;
        RequestBody body = RequestBody.create(JSON, js.toString());
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .build();
        Response res = null;
        try {
            res = client.newCall(req).execute();
        } catch (IOException ex) {
            Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (res.isSuccessful()) {
            System.out.println("Data Submitted");
            System.out.println("data we send = " + js);
            System.out.println(res.body());
        } else {
            System.out.println("Submit Request failed with " + res.code());
        }
    }

    ;
 
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        updateBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Regulation");

        jLabel2.setText("Nationality");

        jComboBox1.setBackground(new java.awt.Color(225, 225, 225));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "United States" }));

        jLabel3.setText("Tax ID");

        jTextField1.setText("123456");

        jLabel4.setText("Employment Status");

        jComboBox2.setBackground(new java.awt.Color(225, 225, 225));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Employed" }));

        jLabel5.setText("Employment industry");

        jComboBox3.setBackground(new java.awt.Color(225, 225, 225));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Agriculture", "food and Natural Resource" }));

        jLabel6.setText("Education level");

        jComboBox4.setBackground(new java.awt.Color(225, 225, 225));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Master degree or equivalent" }));

        jLabel7.setText("Source of wealth");

        jComboBox5.setBackground(new java.awt.Color(225, 225, 225));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Employent or Business proceeds" }));

        jLabel8.setText("Net worth, USD");

        jLabel9.setText("Annual income, USD");

        jLabel10.setText("Annual deposit, USD");

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField2.setText("100 000");

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField3.setText("100 000");

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField4.setText("100 000");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Trading experience");

        jLabel12.setText("Forex");

        jLabel13.setText("Stocks");

        jLabel14.setText("Futures");

        jLabel15.setText("CFD");

        jComboBox6.setBackground(new java.awt.Color(225, 225, 225));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "> 3 years" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jComboBox7.setBackground(new java.awt.Color(225, 225, 225));
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "> 3 years" }));

        jComboBox8.setBackground(new java.awt.Color(225, 225, 225));
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "> 3 years" }));

        jComboBox9.setBackground(new java.awt.Color(225, 225, 225));
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "> 3 years" }));

        updateBtn.setText("Update");

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, 189, Short.MAX_VALUE)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel11)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel12)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel13))))
                                        .addGap(41, 41, 41)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel15)
                                                .addGap(51, 51, 51))
                                            .addComponent(jLabel10)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(59, 59, 59)
                                        .addComponent(jLabel9)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateBtn)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateBtn)
                    .addComponent(cancelBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
