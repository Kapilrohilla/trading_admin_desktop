/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.managerapp.AccountDetails;

import com.mycompany.metatraderbrokeradmin.GroupSymbolpopup.PopupTree4Group;
import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Kapil
 */
public class Tradingpersonal extends javax.swing.JPanel {

    String id;

    public Tradingpersonal() {
        initComponents();
    }

    public Tradingpersonal(String xname, String balance, String email, String jsa, String personGroupId, JSONObject jxo) {
        initComponents();

        id = jsa;
        jLabel21.setText(email);
        balanceLabel.setText(balance);
        try {
            jTextField15.setText(jxo.getString("clientId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
//
//        DefaultComboBoxModel<String> groupComboBox = new DefaultComboBoxModel<>();
//        for (int i = 0; i < jsa.length(); i++) {
//            JSONObject jsonobject = jsa.getJSONObject(i);
//            String name = jsonobject.getString("Name");
//            String groupId = jsonobject.getString("_id");
//            groupComboBox.addElement(name);
//            System.out.println("groupID: " + groupId);
//            if (groupId == personGroupId) {
//                System.out.println("groupId matched for : " + name);
//                jComboBox1.setSelectedItem(name);
//            }
//        }
//
//        jComboBox1.setModel(groupComboBox);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        balanceLabel = new javax.swing.JLabel();

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Login Id : ");

        jLabel3.setText("Exisiting Client :");

        jLabel4.setText("Name :");

        jButton4.setText("Search");

        jLabel20.setText("Client Name");

        jLabel21.setText("jLabel21");

        jLabel2.setText("Balance:");

        balanceLabel.setText("00000");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(307, 307, 307))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(balanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(balanceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

//        String prefferedLogin = jTextField1.getText();
//        String group = jLabel19.getText();
//        String exisitingClient = jComboBox2.getSelectedItem().toString();
//        String firstname = jTextField2.getText().toString();
//        String lastName = jTextField3.getText().toString();
//        String middleName = jTextField4.getText();
//        String company = jTextField5.getText();
//        String email = jTextField6.getText().toString();
//        String phone = jTextField7.getText().toString();
//        String country = jComboBox3.getSelectedItem().toString();
//        String zipCode = jTextField8.getText();
//        String state = jTextField9.getText();
//        String city = jTextField10.getText();
//        String address = jTextField11.getText();
//        String password = jTextField12.getText().toString();
//       / System.out.println("groupName: " + group);
        try {
            JSONObject payload = new JSONObject();
//            payload.put("name", firstname);
//            payload.put("lastName", lastName);
//            payload.put("middleName", middleName);
//            payload.put("email", email);
//            payload.put("password", password);
//            payload.put("prefferedMode", prefferedLogin);
//            payload.put("group", group);
            payload.put("clientId", jTextField15.getText().toString());
            payload.put("userId", id);

            System.out.println("_______________");
            System.out.println("payload - trading account create: " + payload);
            System.out.println("_______________");
            OkHttpClient client = new OkHttpClient();

            String apiUrl = APIs.UPDATE_ACCOUNT;
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, payload.toString());

            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JOptionPane.showMessageDialog(Tradingpersonal.this, "SuccessFully Updated");
                System.out.println("Signup successful");
                System.out.println("Response: " + response.body().string());
            } else {
                System.out.println("Signup Failed" + response.message());
            }
        } catch (IOException | JSONException ex) {
            System.out.println("Error occurred while login");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel balanceLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
