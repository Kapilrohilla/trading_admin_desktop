/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.managerapp.AccountDetails;

import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Kapil
 */
public class AccountDetailsContainer extends javax.swing.JDialog {

    String mname;
    String memail;
    String mgroup;
    String mcountry;
    String mbalance;
    String mId;
    String groupid;
    JSONObject jxo;

    public AccountDetailsContainer(String name, String email, String group, String country, String balance, String id,String groupid,JSONObject jxo) {

        this.mname = name;
        this.memail = email;
        this.mgroup = group;
        System.out.println("group: in account panel" + group);
        this.mcountry = country;
        this.mbalance = balance;
        this.mId = id;
        this.groupid=groupid;
        this.jxo=jxo;
        initComponents();
        tabbedPane();
        setModal(true);
        System.out.println("acc :" + mbalance);
        this.setLocationRelativeTo(null);
        pack();
    }

    final String getData(String url) {

        OkHttpClient client = new OkHttpClient();

        // Create a Request object
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response res = null;
        try {
            res = call.execute();
            return res.body().string();
        } catch (IOException e) {
            System.out.println("error Occurred at groupPanel : " + res);
            return "";
        }
    }

    private void tabbedPane() {
        JTabbedPane tabsPane = new JTabbedPane();
        long lo = System.currentTimeMillis();
        String apiUrl = APIs.GET_GROUPS + "?timestamp=" + lo;
        String apiData = getData(apiUrl);
        System.out.println("groups : " + apiData);
        String responseData = apiData;
        System.out.println(responseData);
        JSONObject json = new JSONObject(responseData);
        JSONArray jsa = json.getJSONArray("message");

        System.out.println("account group: " + this.mgroup);
        Overview overViewPanel = new Overview(mname, memail, mgroup, mcountry, mbalance, mId);
//        Exposure exposurePanel = new Exposure();
        Balance balance = new Balance();
        Account accountPanel = new Account(jsa, mgroup, mId,groupid,jxo);
        TradeContainer tradePanel = new TradeContainer();
        Security securityPanel = new Security(mId);
        tabsPane.addTab("Overview", overViewPanel);
//        tabsPane.addTab("Exposure", exposurePanel);
//        tabsPane.addTab("Personal", personalPanel);
        tabsPane.addTab("Personal", new Tradingpersonal(mname,mbalance , memail,mId, mgroup,jxo));
        tabsPane.addTab("Account", accountPanel);
//        tabsPane.addTab("Limit", new Limits());
        tabsPane.addTab("Limit", new Limits(mId,jxo));

        tabsPane.addTab("Profile", new JPanel());

        tabsPane.addTab("Subscriptions", new JPanel());
        //  tabsPane.addTab("Trade", new TradeRight());
        //tabsPane.addTab("History", new History());
        tabsPane.addTab("Security", securityPanel);
        setLayout(new BorderLayout());

        add(tabsPane);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AccountDetailsContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountDetailsContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountDetailsContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountDetailsContainer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
