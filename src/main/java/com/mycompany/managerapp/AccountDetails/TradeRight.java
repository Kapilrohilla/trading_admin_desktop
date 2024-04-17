/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.managerapp.AccountDetails;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
public class TradeRight extends javax.swing.JPanel {

    /**
     * Creates new form OrderRightPanel
     */
    public TradeRight() {
        initComponents();
        hideSecondType();
        hideExpirationAndExpirationDate();
        try {
            placeOrder();
            sellOrBuy();
        } catch (Exception ex) {
            System.out.println("Error occurred");
        }
    }

    final protected void hideSecondType() {
        jComboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    Object item = event.getItem().toString();
                    if (item != "Pending order") {
                        jLabel10.setVisible(false);
                        jSpinner4.setVisible(false);
                        jLabel13.setVisible(false);
                        jButton1.setVisible(false);
                        jButton2.setVisible(false);
                        jComboBox4.setVisible(false);
                        jButton3.setVisible(true);
                    } else {
                        jLabel10.setVisible(true);
                        jSpinner4.setVisible(true);
                        jLabel13.setVisible(true);
                        jComboBox4.setVisible(true);
                        jButton1.setVisible(true);
                        jButton2.setVisible(true);
                        jButton3.setVisible(false);
                    }
                }
            }
        });
    }

    final protected void hideExpirationAndExpirationDate() {
        jComboBox4.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    Object item = event.getItem().toString();
                    if (!(item == "Buy Stop Limit" || item == "Sell Stop Limit")) {
                        jLabel11.setVisible(false);
                        jComboBox5.setVisible(false);
                        jLabel12.setVisible(false);
                    } else {
                        jLabel11.setVisible(true);
                        jComboBox5.setVisible(true);
                        jLabel12.setVisible(true);
                    }
                }
            }
        });
    }

    final void placeOrder() throws IOException {
        jButton3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Btn clicked");
                try {
                    // Code to execute when the button is clicked
                    String apiUrl = "http://65.0.59.137:8080/create-order";
                    String symbol = "EURUSD";
                    String typeValue = jComboBox2.getSelectedItem().toString();
                    int type = (typeValue == "Sell Limit" || typeValue == "Sell Stop" || typeValue == "Sell Stop Limit") ? 0 : 1;
                    int exc_type = (jComboBox2.getSelectedItem().toString() == "Pending order") ? 1 : 0;
                    float qty = (int) jSpinner1.getValue();
                    float price = 1.2f;
                    float limit = (int) jSpinner4.getValue();
                    float stop_loss = (int) jSpinner2.getValue();
                    float take_profit = (int) jSpinner3.getValue();
                    String comment = jTextField1.getText();
                    String expiration = jComboBox5.getSelectedItem().toString();

                    JSONObject jso = new JSONObject();
                    jso.put("symbol", symbol);
                    jso.put("type", type);
                    jso.put("exc_type", exc_type);
                    jso.put("qty", qty);
                    jso.put("price", price);
                    jso.put("limit", limit);
                    jso.put("stop_loss", stop_loss);
                    jso.put("take_profit", take_profit);
                    jso.put("comment", comment);
                    jso.put("expiration", expiration);

                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    RequestBody body = RequestBody.create(JSON, jso.toString());

                    Request request = new Request.Builder()
                            .url(apiUrl)
                            .post(body)
                            .header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NTliYTdhMTRiNGQyZmEzMDU4ZDM5ZWMiLCJpYXQiOjE3MDUwNDY0MjcsImV4cCI6MTcwNTY1MTIyN30.-MzdsFKH9a9kF_sdcJoSX7KqAvSmnGtqhp0HPqTrK5g")
                            .build();

                    OkHttpClient client = new OkHttpClient();
                    System.out.println(body);
                    Response response = client.newCall(request).execute();

                    System.out.println("Status: " + response.message());

                    if (response.isSuccessful()) {
                        System.out.println("Api hit successful");
                        // Get response body as strings
                        String responseBody = response.body().string();
                        System.out.println("_____________________*****************_________________");
                        System.out.println(responseBody);
                        System.out.println("_____________________*****************_________________");
                    } else {
                        System.out.println("Error: " + response.body().toString());
                    }
                } catch (JSONException | IOException ex) {
                    Logger.getLogger(TradeRight.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    final void sellOrBuy() {
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Sell Button clicked");
                    String apiUrl = "http://65.0.59.137:8080/create-order";
                    String symbol = "EURUSD";
                    // String typeValue = jComboBox2.getSelectedItem().toString();
                    int type = 0;
                    int exc_type = (jComboBox2.getSelectedItem().toString() == "Pending order") ? 1 : 0;
                    float qty = (int) jSpinner1.getValue();
                    float price = 1.2f;
                    float limit = (int) jSpinner4.getValue();
                    float stop_loss = (int) jSpinner2.getValue();
                    float take_profit = (int) jSpinner3.getValue();
                    String comment = jTextField1.getText();
                    String expiration = jComboBox5.getSelectedItem().toString();

                    JSONObject jso = new JSONObject();
                    jso.put("symbol", symbol);
                    jso.put("type", type);
                    jso.put("exc_type", exc_type);
                    jso.put("qty", qty);
                    jso.put("price", price);
                    jso.put("limit", limit);
                    jso.put("stop_loss", stop_loss);
                    jso.put("take_profit", take_profit);
                    jso.put("comment", comment);
                    jso.put("expiration", expiration);

                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    RequestBody body = RequestBody.create(JSON, jso.toString());

                    Request request = new Request.Builder()
                            .url(apiUrl)
                            .post(body)
                            .header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NTcyYmQ4Y2I4MWZhNzEyNzQxNmUyNGEiLCJpYXQiOjE3MDQ1MzU3MTksImV4cCI6MTcwNTE0MDUxOX0.SKCkax0DMW0a6-rc1rB6yJlyXKV03_gbsmIDRpnlu68")
                            .build();

                    OkHttpClient client = new OkHttpClient();
                    System.out.println(body);
                    Response response = client.newCall(request).execute();

                    // Check for successful response
                    if (response.isSuccessful()) {
                        // Get response body as strings
                        String responseBody = response.body().string();
                        System.out.println("_____________________*****************_________________");
                        System.out.println(responseBody);
                        System.out.println("_____________________*****************_________________");
                    } else {
                        System.out.println("Error: " + response.body().toString());
                    }
                } catch (JSONException | IOException ex) {
                    Logger.getLogger(TradeRight.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String apiUrl = "http://65.0.59.137:8080/create-order";
                    String symbol = "EURUSD";
                    String typeValue = jComboBox2.getSelectedItem().toString();
                    int type = 1;
                    int exc_type = (jComboBox2.getSelectedItem().toString() == "Pending order") ? 1 : 0;
                    float qty = (int) jSpinner1.getValue();
                    float price = 1.2f;
                    float limit = (int) jSpinner4.getValue();
                    float stop_loss = (int) jSpinner2.getValue();
                    float take_profit = (int) jSpinner3.getValue();
                    String comment = jTextField1.getText();
                    String expiration = jComboBox5.getSelectedItem().toString();

                    JSONObject jso = new JSONObject();
                    jso.put("symbol", symbol);
                    jso.put("type", type);
                    jso.put("exc_type", exc_type);
                    jso.put("qty", qty);
                    jso.put("price", price);
                    jso.put("limit", limit);
                    jso.put("stop_loss", stop_loss);
                    jso.put("take_profit", take_profit);
                    jso.put("comment", comment);
                    jso.put("expiration", expiration);

                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    RequestBody body = RequestBody.create(JSON, jso.toString());

                    Request request = new Request.Builder()
                            .url(apiUrl)
                            .post(body)
                            .header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NTcyYmQ4Y2I4MWZhNzEyNzQxNmUyNGEiLCJpYXQiOjE3MDQ1MzU3MTksImV4cCI6MTcwNTE0MDUxOX0.SKCkax0DMW0a6-rc1rB6yJlyXKV03_gbsmIDRpnlu68")
                            .build();

                    OkHttpClient client = new OkHttpClient();
                    System.out.println(body);
                    Response response = client.newCall(request).execute();
                    System.out.println("Buy Btn clicked");
                    // Check for successful response
                    if (response.isSuccessful()) {
                        // Get response body as strings
                        String responseBody = response.body().string();
                        System.out.println("_____________________*****************_________________");
                        System.out.println(responseBody);
                        System.out.println("_____________________*****************_________________");
                    } else {
                        System.out.println("Error: " + response.body().toString());
                    }
                } catch (JSONException | IOException ex) {
                    Logger.getLogger(TradeRight.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    final void postData() throws JSONException, IOException {
        String apiUrl = "http://65.0.59.137:8080/create-order";
        String symbol = "EURUSD";
        String typeValue = jComboBox2.getSelectedItem().toString();
        int type = (typeValue == "Sell Limit" || typeValue == "Sell Stop" || typeValue == "Sell Stop Limit") ? 0 : 1;
        int exc_type = (jComboBox2.getSelectedItem().toString() == "Pending order") ? 1 : 0;
        float qty = (float) jSpinner1.getValue();
        float price = 1.2f;
        float limit = (float) jSpinner4.getValue();
        float stop_loss = (float) jSpinner2.getValue();
        float take_profit = (float) jSpinner3.getValue();
        String comment = jTextField1.getText();
        String expiration = jComboBox5.getSelectedItem().toString();

        JSONObject jso = new JSONObject();
        jso.put("symbol", symbol);
        jso.put("type", type);
        jso.put("exc_type", exc_type);
        jso.put("qty", qty);
        jso.put("price", price);
        jso.put("limit", limit);
        jso.put("stop_loss", stop_loss);
        jso.put("take_profit", take_profit);
        jso.put("comment", comment);
        jso.put("expiration", expiration);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jso.toString());

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NTcyYmQ4Y2I4MWZhNzEyNzQxNmUyNGEiLCJpYXQiOjE3MDQ1MzU3MTksImV4cCI6MTcwNTE0MDUxOX0.SKCkax0DMW0a6-rc1rB6yJlyXKV03_gbsmIDRpnlu68")
                .build();

        OkHttpClient client = new OkHttpClient();
        System.out.println(body);
        Response response = client.newCall(request).execute();

        // Check for successful response
        if (response.isSuccessful()) {
            // Get response body as strings
            String responseBody = response.body().string();
            System.out.println("_____________________*****************_________________");
            System.out.println(responseBody);
            System.out.println("_____________________*****************_________________");
        } else {
            System.out.println("Error: " + response.body().toString());
        }
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSpinner4 = new javax.swing.JSpinner();
        jButton3 = new javax.swing.JButton();

        jLabel1.setText("Symbol:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EURUSD\\EURO VS US Dollor" }));

        jLabel2.setText("Type:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending order", "Instant Execution" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Volume:");

        jLabel4.setText("0 EUR");

        jLabel5.setText("Stop Loss:");

        jLabel6.setText("Take Profit:");

        jLabel7.setText("Comment:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Deviation:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("1.0761o / 1.0761o");

        jButton1.setText("Sell");

        jButton2.setText("Buy");

        jLabel10.setText("Type:");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buy Limit", "Sell Limit", "Buy Stop", "Sell Stop", "Buy Stop Limit", "Sell Stop Limit" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jLabel11.setText("Expiration:");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GTC", "Specified" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel12.setText("Expiration date:");

        jLabel13.setText("Stop Limit Price:");

        jButton3.setText("Place Order");
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
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSpinner2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel13))
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSpinner3, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(jSpinner4)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel13)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
