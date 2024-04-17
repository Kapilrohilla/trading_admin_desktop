/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin.SymbolConfig;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author techninza
 */
public class MarginRatesSymbolPage extends javax.swing.JPanel {

    /**
     * Creates new form MarginRatesSymbolPage
     */
    public MarginRatesSymbolPage() {
        initComponents();
    }
    public MarginRatesSymbolPage(JSONObject js){
        initComponents();
        String MarginLiquidity = getJSONData("MarginLiquidity", js);
        String MarginCurrency = getJSONData("MarginCurrency", js);
        
        jTextField1.setText(MarginLiquidity);
        jTextField2.setText(MarginCurrency);
        
        
        JSONArray jsx=js.getJSONArray("MarginRatesTable");
        DefaultTableModel dataModel=new DefaultTableModel();
        dataModel.addColumn("");
         dataModel.addColumn("Market Order");
        dataModel.addColumn("Limit Order");
        dataModel.addColumn("Stop Order");
        dataModel.addColumn("Stop Limit Order");
        dataModel.addRow(new String[]{"InitialMargin","","","",""});
        try{
            
        
        dataModel.addRow(new String[]{"Buy",jsx.getJSONArray(0).getJSONObject(0).getString("market"),
            jsx.getJSONArray(0).getJSONObject(0).getString("limit_order"),
        jsx.getJSONArray(0).getJSONObject(0).getString("stop_order"),
        jsx.getJSONArray(0).getJSONObject(0).getString("stop_limit_order")});
        dataModel.addRow(new String[]{"Sell",jsx.getJSONArray(0).getJSONObject(1).getString("market"),
            jsx.getJSONArray(0).getJSONObject(1).getString("limit_order"),
        jsx.getJSONArray(0).getJSONObject(1).getString("stop_order"),
        jsx.getJSONArray(0).getJSONObject(1).getString("stop_limit_order")});
        
        
        dataModel.addRow(new String[]{"MaintainenaceMargin","","","",""});
        dataModel.addRow(new String[]{"Buy",jsx.getJSONArray(1).getJSONObject(0).getString("market"),
            jsx.getJSONArray(1).getJSONObject(0).getString("limit_order"),
        jsx.getJSONArray(1).getJSONObject(0).getString("stop_order"),
        jsx.getJSONArray(1).getJSONObject(0).getString("stop_limit_order")});
        dataModel.addRow(new String[]{"Sell",jsx.getJSONArray(1).getJSONObject(1).getString("market"),
            jsx.getJSONArray(1).getJSONObject(1).getString("limit_order"),
        jsx.getJSONArray(1).getJSONObject(1).getString("stop_order"),
        jsx.getJSONArray(1).getJSONObject(1).getString("stop_limit_order")});
        jTable1.setModel(dataModel);
        }catch(Exception ex){
            System.out.println("exception occurred in marginRatesSymbol panel");
        }
        
    }

    final String getJSONData(String s, JSONObject js){
        try{
            return js.getString(s);
        }catch(JSONException e){
            System.out.println(s  + " not found");
            return "";
        }
    }
    public JTable getjTable1() {
        return jTable1;
    }

    public JTextField getjTextField1() {
        return jTextField1;
    }

    public JTextField getjTextField2() {
        return jTextField2;
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
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel1.setText("Please specify margin rates for different types of trade operations.");

        jLabel2.setText("Liquidity Margin Rate : ");

        jLabel3.setText("Currency Margin Rate : ");

        jLabel4.setText("Margin Rates");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Initial Margin", null, null, null, null},
                {"Buy", "1.00000", "0.0", "0.0", "0.0"},
                {"Sell", "1.00000", "0.0", "0.0", "0.0"},
                {"Maintenance Margin", null, null, null, null},
                {"Buy", "0.00000", "0.0", "0.0", "0.0"},
                {"Sell", "0.00000", "0.0", "0.0", "0.0"},
                {"", null, null, null, null}
            },
            new String [] {
                "", "Market Order", "Limit Order", "Stop Order", "Stop Limit Order"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField2))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel4))
                        .addGap(0, 68, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}