/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin.Manager;

import com.mycompany.metatraderbrokeradmin.BrokerAdmin;
import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import static com.mycompany.metatraderbrokeradmin.GroupConfig.CommissionsGroupConfig.tablemodel;
import com.mycompany.metatraderbrokeradmin.GroupSymbolpopup.PopupTree4Group;
import com.mycompany.metatraderbrokeradmin.ManagerGroupAssign;
import com.mycompany.metatraderbrokeradmin.ManagerPanel;
import com.mycompany.metatraderbrokeradmin.Utility.GroupModel;
import com.mycompany.metatraderbrokeradmin.orderndeals.Orderndealspopup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
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
 * @author Lenovo
 */
public class GroupAssignPanel extends javax.swing.JPanel {
    
    int row = 0;
    String mid;
    public static DefaultTableModel tablemodel;
    public static ArrayList<GroupModel> groupmodelarraylist = new ArrayList();

    /**
     * Creates new form GroupAssignPanel
     */
    public GroupAssignPanel(String mid, JSONArray js) {
        initComponents();
        tablemodel = new DefaultTableModel();
        groupmodelarraylist.clear();
        this.mid = mid;
        if (tablemodel.getColumnCount() == 0) {
            String[] columns = {"Name"};
            for (String column : columns) {
                tablemodel.addColumn(column);
            }
        }
        for (int i = 0; i < js.length(); i++) {
            try {
                JSONObject jso = (JSONObject) js.get(i);
                String name = jso.getString("name");
                String id = jso.getString("id");
                groupmodelarraylist.add(new GroupModel(id, name));
                Vector<String> vec = new Vector();
                vec.add(name);
                tablemodel.addRow(vec);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        jTable1.setModel(tablemodel);
        jTable1.setEnabled(false);
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    row = jTable1.rowAtPoint(e.getPoint());
//                    String orderid = arraylist.get(row).getString("_id");
//                    if (e.getClickCount() == 2) {
//                        new Orderndealspopup("order", orderid);
//                    }
                    jTable1.setRowSelectionInterval(row, row);
                }
            }
        });
    }
    
    final void PostData() throws JSONException, IOException {
        OkHttpClient client = new OkHttpClient();
//        String token = Metacustomer.loginToken;

        // groups/symbol
        String symbolApiUrl = APIs.UPDATE_MANAGER;
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject jso = new JSONObject();
        jso.put("ManagerId", mid);
        
        JSONArray dataList = new JSONArray();
        
        for (int i = 0; i < groupmodelarraylist.size(); i++) {
//                
            JSONObject js = new JSONObject();
            js.put("id", groupmodelarraylist.get(i).getId());
            js.put("name", groupmodelarraylist.get(i).getName());
//                map.put("id", ""+updatearray.get(i));
//                System.out.println(updatearray.get(i));
            dataList.put(js);
        }
        
        jso.put("groups", dataList);
        System.out.println(jso.toString());
        RequestBody body = RequestBody.create(JSON, jso.toString());
        
        Request request = new Request.Builder()
                .url(symbolApiUrl)
                .post(body)
                .build();
        
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
//        System.out.println("resonse body" + responseBody);
        BrokerAdmin.splitPane.setRightComponent(new ManagerPanel());
        ManagerTabs.auth.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("Please Assign Groups to Manager Here : ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        new ManagerGroupAssign(mid).setVisible(true);
        new PopupTree4Group("GroupAssignManager").setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            PostData();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String[] selectedIndexData = modelData.get(selectedIndex);
//
//        new CommissionsFrame(selectedIndexData, selectedIndex).setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//        System.out.println("size: " + modelData.size());
//        System.out.println("modelSize: " + tablemodel.getRowCount());
//        modelData.remove(selectedIndex);

        tablemodel.removeRow(row);
        groupmodelarraylist.remove(row);
        // System.out.println("modelData: " + jarray.toString());
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
