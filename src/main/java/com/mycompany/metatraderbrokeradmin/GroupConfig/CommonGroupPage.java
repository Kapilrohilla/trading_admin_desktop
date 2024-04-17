/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin.GroupConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author techninza
 */
public class CommonGroupPage extends javax.swing.JPanel {

    /**
     * Creates new form CommonSymbolPage
     */
    public CommonGroupPage() {
        initComponents();
        setData();
    }

    final String getJSONData(String s, JSONObject js) {
        try {
            return js.getString(s);
        } catch (JSONException e) {
            System.out.println(s + " not found");
            return "        ";
        }
    }

    public CommonGroupPage(JSONObject js) {
        initComponents();

        jTextField1.setText(getJSONData("Name", js));
        
        minPassword.setText(getJSONData("MiniPasswordLength", js));

        tradeServer.setSelectedItem(getJSONData("TradeServer", js));

        auth.setSelectedItem(getJSONData("Authentication", js));
        System.out.println( getJSONData("Authentication",js).equals("1024 RSA SSL Certificate"));
        currency.setSelectedItem(getJSONData("Currency", js));

        digits.setSelectedItem(getJSONData("Digits", js));

        otp.setSelectedItem(getJSONData("Otp", js));

//        pushNotification.setSelectedItem(getJSONData("PushNotification", js));
        for(int i=0; i<pushNotification.getModel().getSize()-1; i++){
            if(pushNotification.getItemAt(i).equals(getJSONData("PushNotification",js))){
                pushNotification.setSelectedIndex(i);
                break;
            }
        }
        jCheckBox1.setSelected("true".equals(getJSONData("EnableConnectionFlag", js)));
        jCheckBox2.setSelected("true".equals(getJSONData("EnableCertificateFlag", js)));
        jCheckBox3.setSelected("true".equals(getJSONData("ChangePasswordFlag", js)));
        jCheckBox4.setSelected("true".equals(getJSONData("ShowRiskFlag", js)));
        jCheckBox5.setSelected("true".equals(getJSONData("EnforceCountryFlag", js)));
        setData();
    }

    public JCheckBox getjCheckBox1() {
        return jCheckBox1;
    }

    public JCheckBox getjCheckBox2() {
        return jCheckBox2;
    }

    public JCheckBox getjCheckBox3() {
        return jCheckBox3;
    }

    public JCheckBox getjCheckBox4() {
        return jCheckBox4;
    }

    public JCheckBox getjCheckBox5() {
        return jCheckBox5;
    }

    public JComboBox<String> getjComboBox1() {
        return pushNotification;
    }

    public JComboBox<String> getjComboBox2() {
        return tradeServer;
    }

    public JComboBox<String> getjComboBox3() {
        return auth;
    }

    public JComboBox<String> getjComboBox4() {
        return otp;
    }

    public JComboBox<String> getjComboBox5() {
        return currency;
    }

    public JComboBox<String> getjComboBox6() {
        return digits;
    }

    public JTextField getjTextField1() {
        return jTextField1;
    }

    public JTextField getjTextField5() {
        return minPassword;
    }

    private void getData(String id) {
        try {
            // Replace with your target URL
            String url = "https://shopninja.in/meta/api2/public/index.php/show_groupid";

            // Create the POST request
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            String postData = "id=" + URLEncoder.encode(id, "UTF-8");

            // Send the POST request
            try (var outputStream = connection.getOutputStream()) {
                outputStream.write(postData.getBytes("UTF-8"));
            }

            // Read the response
            try (var inputStream = connection.getInputStream(); var reader = new BufferedReader(new InputStreamReader(inputStream))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line).append("\n");
                }

                //JOptionPane.showMessageDialog(null, ""+response);
                System.out.println("response : " + response);
                // JSONObject json=new JSONObject(response);
                JSONArray jSONArray = new JSONArray(response.toString());
                JSONObject json = jSONArray.getJSONObject(0);
                String name = json.getString("name");
                String digi = json.getString("digits");
                boolean enableconn = Boolean.parseBoolean(json.getString("enableconnections"));
                boolean certificate = Boolean.parseBoolean(json.getString("enablecertificateinfo"));
                boolean changepasslogin = Boolean.parseBoolean(json.getString("changepasslogin"));
                boolean riskwarnwind = Boolean.parseBoolean(json.getString("riskwarnwind"));
                boolean countryspecific = Boolean.parseBoolean(json.getString("countryspecific"));

                jCheckBox1.setSelected(enableconn);
                jCheckBox2.setSelected(certificate);
                jCheckBox3.setSelected(changepasslogin);
                jCheckBox4.setSelected(riskwarnwind);
                jCheckBox5.setSelected(countryspecific);
                jTextField1.setText(name);
                minPassword.setText(digi);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setData() {
        pushNotification.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"None", "Deals", "Orders", "Balance"}));
        tradeServer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"MainTradeServer , 1"}));
        auth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Normal", "1024 RSA SSL Certificate", "2048 RSA SSL Certificate", "Custom SSL Certificate"}));
        otp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Disabled", "TOTP/2FA ALL", "TOPT/2FA WEB ONLY"}));
        currency.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"USD", "EUR", "GBP", "JPY", "CHF", "RUR"}));
        // digits.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"MainTradeServer , 1"}));
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
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        pushNotification = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        tradeServer = new javax.swing.JComboBox<>();
        auth = new javax.swing.JComboBox<>();
        otp = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        currency = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        digits = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        minPassword = new javax.swing.JTextField();
        jCheckBox7 = new javax.swing.JCheckBox();

        jLabel1.setText("Group is a set of users that have the same permission settings and service conditions.");

        jLabel2.setText("Name");

        jLabel3.setText("Trade Server");

        jLabel4.setText("Authentication");

        jLabel5.setText("One-time Password");

        jLabel6.setText("Push Notifications");

        jTextField1.setPreferredSize(new java.awt.Dimension(70, 22));

        pushNotification.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "enable", "disable" }));

        jLabel19.setText("Please specify name of group, deposit currency, trade server, and authentication type.");

        jCheckBox1.setText("Enable Connections");

        jCheckBox2.setText("Enable Certificate Informations");

        jCheckBox3.setText("Change Password At First Login");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox4.setText("Show the Risk Warning Window After Connection");

        jCheckBox5.setText("Enforce country-specific regulatory restrictions for retial clients");

        tradeServer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        auth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Authentication", "1024-bit RSA SSL certificate" }));

        otp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Currency");

        currency.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "EUR", "GBP", "JPY", "CHF", "RUR" }));

        jLabel8.setText("Digits");

        digits.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));

        jLabel9.setText("Minimum Password Length");

        minPassword.setText("                ");

        jCheckBox7.setText("Force one time password usage");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox2)
                                    .addComponent(jCheckBox3)
                                    .addComponent(auth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox4)
                                    .addComponent(jCheckBox5)
                                    .addComponent(jCheckBox1)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jCheckBox7)
                                        .addGap(19, 19, 19))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(tradeServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel8))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(149, 149, 149)
                                                .addComponent(jLabel7)))
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(currency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(digits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 6, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(otp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(pushNotification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(currency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel7))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tradeServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(digits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(auth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(otp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(pushNotification, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(minPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(jCheckBox7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox5)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> auth;
    private javax.swing.JComboBox<String> currency;
    private javax.swing.JComboBox<String> digits;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField minPassword;
    private javax.swing.JComboBox<String> otp;
    private javax.swing.JComboBox<String> pushNotification;
    private javax.swing.JComboBox<String> tradeServer;
    // End of variables declaration//GEN-END:variables

}
