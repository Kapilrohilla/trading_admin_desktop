/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin;

import com.mycompany.metatraderbrokeradmin.Utility.Config;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
 * @author techninza
 */
public class TablePanel extends javax.swing.JPanel {

    /**
     * Creates new form TablePanel
     */
    String uid;

    public TablePanel(String uid) {
        this.uid = uid;
        try {
            tabledata();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private String getData(String userId) {
        OkHttpClient client = new OkHttpClient();
        System.out.println("userId: " + userId);
        String url = Config.BASE_URL + "?clientId=" + userId;
        // Create a Request object
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        try {
            Response res = call.execute();
            return res.body().string();
        } catch (IOException e) {
            System.out.println("API Error");
            return "";
        }
    }

    private void tabledata() throws JSONException {
        String apiData = getData(uid);
        System.out.println(apiData);
        JSONObject jsonob = new JSONObject(apiData);
        JSONArray js = jsonob.getJSONArray("users");
        ArrayList<JSONObject> arraylist = new ArrayList<>();
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Id");
        tableModel.addColumn("ClientId");
        tableModel.addColumn("Balance");

        for (int i = 0; i < js.length(); i++) {
            JSONObject jso = js.getJSONObject(i);
            String server = jso.getString("_id");
            String text = jso.getString("clientId");
            String created = jso.getFloat("balance") + "";

            String rowData[] = {server, text, created};
            arraylist.add(jso);
            tableModel.addRow(rowData);
        }
        
        JTable jt = new JTable(tableModel);

        jt.setEnabled(false);
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(jt);
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