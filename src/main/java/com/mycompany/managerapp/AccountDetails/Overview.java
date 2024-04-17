/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.managerapp.AccountDetails;

import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Kapil
 */
public class Overview extends javax.swing.JPanel {

    /**
     * Creates new form Overview
     */
    String mname;
    String memail;
    String mgroup;
    String mcountry;
    String mbalance;
    String id;

    final String getData(String id) {
        OkHttpClient client = new OkHttpClient();
        String url = APIs.USER_ACCOUNT_ORDERS + "?userId=" + id;
        
        System.out.println(url);

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);

        try {
            Response res = call.execute();
            return res.body().string();
        } catch (IOException e) {
            System.out.println("Error occurred while fetching trading account specific overview");
            return "";
        }
    }

//    String extractStringData(String key, JSONObject jso) {
//        try {
//            return jso.getString(key);
//        } catch (Exception ex) {
//            System.out.println("exception occureed while fetching " + key);
//        }
//        return "";
//    }
    public Overview(String name, String email, String group, String country, String balance, String id) {

        initComponents();

        jLabel6.setVisible(false);
        this.mname = name;
        this.memail = email;
        this.mgroup = group;
        this.mcountry = country;
        this.mbalance = balance;
        this.id = id;

        System.out.println("overview" + mbalance);
        jLabel1.setText("Name : " + mname);
        jLabel1.setVisible(false);
        jLabel2.setText("UserID : " + memail);
        jLabel3.setText("Group : " + mgroup);
        //jLabel4.setText("Country : " + mcountry);
        jLabel4.setVisible(false);
        jLabel5.setText("Balance : " + mbalance);

        String apiData = getData(id);
        DefaultTableModel tablemodel = new DefaultTableModel();
        String[] columns = {"Symbol", "Time", "Type", "Volume", "Price", "StopLoss", "Take Profit", "Swap", "Profit"};
        for (String column : columns) {
            tablemodel.addColumn(column);
        }

        JSONArray jsa = new JSONArray(apiData);

        for (int i = 0; i < jsa.length(); i++) {
            JSONObject jso = jsa.getJSONObject(i);
            System.out.println("jso: " + jso);
//            String jssymbol, jstime, jstype, jsvolume, jsprice, jsstoploss, jstakeProfit, jsprice, jsswap, jsprofit;
            String jsSymbol, jsTime, jsType, jsVolume, jsPrice, jsStopLoss, jsTakeProfit, jsSwap, jsProfit;

//            jsSymbol = extractStringData("symbol", jso);
//            jsTime = extractStringData("", jso)
            try {
                jsSymbol = jso.getString("symbol");

            } catch (JSONException ex) {
                jsSymbol = "";
            }
            try {
                jsTime = jso.getString("createdAt");
                jsType = jso.getFloat("type") == 0 ? "Sell " : "Buy";
                jsVolume = jso.getFloat("initialVolume") + "";

            } catch (JSONException ex) {
                jsTime = "";
                jsType = "";
                jsVolume = "";
            }

            try {
                jsVolume = jso.getFloat("initialVolume") + "";
            } catch (JSONException ex) {
                jsVolume = "";
            }
            try {
                jsPrice = jso.getFloat("currentPrice") + "";
            } catch (JSONException ex) {
                jsPrice = "";
            }
            try {
                jsStopLoss = jso.getFloat("stopLoss") + "";
            } catch (JSONException ex) {
                jsStopLoss = "";
            }

            try {
                jsTakeProfit = jso.getFloat("takeProfit") + "";
            } catch (JSONException ex) {
                jsTakeProfit = "";
            }

            try {
                jsSwap = jso.getFloat("swap") + "";
            } catch (JSONException ex) {
                jsSwap = "";
            }

            try {
                jsProfit = jso.getFloat("profit") + "";
            } catch (JSONException ex) {
                jsProfit = "";
            }

            String[] rowData = {jsSymbol, jsTime, jsType, jsVolume, jsPrice, jsStopLoss, jsTakeProfit, jsSwap, jsProfit};
            tablemodel.addRow(rowData);
        }

        jTable1.setModel(tablemodel);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("Brown Jackson, 181176, demo\\demoforex-5,1 : 100");

        jLabel2.setText("06101, Turkey, Ankara, Celal Bayar Blv");

        jLabel3.setText("+918287842425");

        jLabel4.setText("Jackson.Brown@testmail.net");

        jLabel5.setText("Registerd: 2016. 11. 24 Last Access: 2016.11.24 13:23 Last Address: 192.168.0.1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Symbol", "Time", "Type", "Volume", "Price", "S / L", "T / P", "Price", "Swap", "Profit"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Balance: 53.45814 USD Equity 58.955.42 Commission: -0.20 Margin: 63.079.36 FreeMargin: -4123.95  5.497");

        jButton1.setText("Ok");

        jButton2.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
