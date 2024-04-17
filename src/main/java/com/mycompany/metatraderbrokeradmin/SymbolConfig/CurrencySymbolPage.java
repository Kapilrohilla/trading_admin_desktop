/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin.SymbolConfig;

import com.mycompany.metatraderbrokeradmin.*;
import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author techninza
 */
public class CurrencySymbolPage extends javax.swing.JPanel {

    /**
     * Creates new form CurrencySymbolPage
     */
    class FolderNames {

        String folderName;
        String folderId;

        FolderNames(String name, String id) {
            this.folderId = id;
            this.folderName = name;
        }

        public String getFolderId() {
            return folderId;
        }

        public String getFolderName() {
            return folderName;
        }

    }
    public static ArrayList<FolderNames> folders = new ArrayList<>();

    public CurrencySymbolPage() {
        initComponents();
        setData();
        jLabel8.setVisible(false);
        jComboBox7.setVisible(false);
    }

    final String getJSONData(String s, JSONObject js) {
        try {
            return js.getString(s);
        } catch (JSONException e) {
            System.out.println(s + " not found");
            return "";
        }
    }

    public CurrencySymbolPage(JSONObject js) {
        initComponents();
        setData();
        setHCategory();
        if (getJSONData("CurrencyBase", js).length() > 0) {
            jComboBox1.setSelectedItem(getJSONData("CurrencyBase", js));
        }

        if (getJSONData("CurrencyBaseDigits", js).length() > 0) {
            System.out.println(getJSONData("Basis", js));
            jComboBox2.setSelectedItem(getJSONData("CurrencyBaseDigits", js));
        }

        if (getJSONData("CurrencyProfit", js).length() > 0) {
            jComboBox3.setSelectedItem(getJSONData("CurrencyProfit", js));
        }

        if (getJSONData("CurrencyProfitDigits", js).length() > 0) {
            jComboBox4.setSelectedItem(getJSONData("CurrencyProfitDigits", js));
        }

        if (getJSONData("CurrencyMargin", js).length() > 0) {
            jComboBox5.setSelectedItem(getJSONData("CurrencyMargin", js));
            System.out.println("Currency margin: " + getJSONData("CurrencyMargin", js));
        }
        if (getJSONData("CurrencyMarginDigits", js).length() > 0) {
            jComboBox6.setSelectedItem(getJSONData("CurrencyMarginDigits", js));
        }

//        if(getJSONData(TOOL_TIP_TEXT_KEY, js))
        String HCategory = getJSONData("HCategory", js);
        System.out.println("hCategory: " + HCategory);
        int desiredIndex = 0;
        for (int i = 0; i < folders.size(); i++) {
            String folderId = folders.get(i).getFolderId();
            System.out.println("folderId: " + folderId);
            if (folderId.equalsIgnoreCase(HCategory)) {
                desiredIndex = i;
                break;
            }
        }
        System.out.println("desiredIndex: " + desiredIndex);

        jComboBox7.setSelectedIndex(desiredIndex);
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

    public JComboBox<String> getjComboBox5() {
        return jComboBox5;
    }

    public JComboBox<String> getjComboBox6() {
        return jComboBox6;
    }

    public JComboBox<String> getjComboBox7() {
        return jComboBox7;
    }

    private void setData() {
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"USD", "INR", "BTC", "AUD"}));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"USD", "INR", "BTC", "AUD"}));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"USD", "INR", "BTC", "AUD"}));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"2", "3", "4", "5"}));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"2", "3", "4", "5"}));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"2", "3", "4", "5"}));
    }

    private void setHCategory() {
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder().url(APIs.GET_SYMBOL_CATEGORY).build();
        JSONArray msg;
        try {
            Response res = client.newCall(req).execute();
            String responseBody = res.body().string();
            JSONObject jso = new JSONObject(responseBody);
            msg = jso.getJSONArray("message");
        } catch (IOException | JSONException ex) {
            msg = new JSONArray();
            System.out.println(ex.getMessage());
        }
        DefaultComboBoxModel folderModel = new DefaultComboBoxModel();
        for (int i = 0; i < msg.length(); i++) {
            JSONObject jso = msg.getJSONObject(i);
            String name = jso.getString("name");
            String id = jso.getString("_id");
           // System.out.println("name: " + name + ", id: " + id);
            FolderNames fn = new FolderNames(name, id);
            folders.add(fn);
            folderModel.addElement(name);
        }

        jComboBox7.setModel(folderModel);
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
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();

        jLabel1.setText("The setting up of base, profit, and margin currencies of the symbol.");

        jLabel2.setText("Base Currency");

        jLabel3.setText("Base Currency digits");

        jLabel4.setText("Profit Currency");

        jLabel5.setText("Profit Currency digits");

        jLabel6.setText("Margin Currency");

        jLabel7.setText("Margin Currency digits");

        jComboBox1.setEditable(true);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "EUR", "GBP", "JPY", "CHF", "RUR" }));

        jComboBox2.setEditable(true);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));

        jComboBox3.setEditable(true);
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "EUR", "GBP", "JPY", "CHF", "RUR" }));

        jComboBox4.setEditable(true);
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));

        jComboBox5.setEditable(true);
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USD", "EUR", "GBP", "JPY", "CHF", "RUR" }));

        jComboBox6.setEditable(true);
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" }));

        jLabel8.setText("Folder:");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(23, 23, 23))
                                .addComponent(jLabel5))
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel8)))
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    public static javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    public static javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
