/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin;

import com.mycompany.metatraderbrokeradmin.GroupSymbolpopup.PopupTree4Symbol;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
//import javax.xml.rpc.Call;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mycompany.metatraderbrokeradmin.OneMinHistoryPopup;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

/**
 *
 * @author techninza
 */
public class ChartPanel extends javax.swing.JPanel {

    public static String date1 = "2024-01-28";
    public static String date2 = "2024-01-29";
    public static String time1 = "";
    public static String time2 = "";
    public static String symbol = "XAUUSD";
    ArrayList<JSONObject> tableData = new ArrayList<>();
    DefaultTableModel model;

    public ChartPanel() {
        initComponents();
        model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Open");
        model.addColumn("High");
        model.addColumn("Low");
        model.addColumn("Close");
        jTable1.setModel(model);
        jTable1.setAutoCreateRowSorter(true);
//        table(symbol);
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    System.out.println("btn clieck");
                    int row = jTable1.rowAtPoint(e.getPoint());
                    if (e.getClickCount() == 2) {
                        JSONObject vec = tableData.get(row);
                        new OneMinHistoryPopup(symbol,vec).setVisible(true);
                    }
                }
            }
        });

        jComboBox1.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) e.getItem();
                    // Perform actions based on the selected item
                    System.out.println("Selected item: " + selectedItem);

                    time2 = " " + selectedItem;
                }
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });

        jComboBox2.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) e.getItem();
                    // Perform actions based on the selected item
                    System.out.println("Selected item: " + selectedItem);

                    time1 = " " + selectedItem;
                }
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }

//    public static void refresh()
//    {
//        jLabel1.setText("");
//    }
    private String getData(String query) {
//        System.out.println("date1: " + date1);
//        System.out.println("date2: " + date2);
//        System.out.println("url = " + "http://65.0.59.137:8080/get-data/"+query + "/?startDate=" + date1 + "&endDate=" +date2);

        String url = com.mycompany.metatraderbrokeradmin.Utility.APIs.ONE_MIN_CHART_URL + query + "/?startDate=" + date1 + time1 + "&endDate=" + date2 + time2;
        System.out.println("url" + url);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);

        try {
            Response res = call.execute();
            return res.body().string();
        } catch (IOException e) {
            System.out.println("Runtime error");
            return "";
        }

    }

    protected final void table(String tb) {
        String data = getData(tb);
        System.out.println("data: " + data);

        tableData.clear();
        JSONArray js = new JSONArray(data);
        System.out.println(js);
        model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Open");
        model.addColumn("High");
        model.addColumn("Low");
        model.addColumn("Close");

        for (int i = 0; i < js.length(); i++) {
            JSONObject jsonobject = js.getJSONObject(i);
            String date = jsonobject.getString("fullDate");
            String open = jsonobject.getFloat("open") + "";
            String high = jsonobject.getFloat("high") + "";
            String low = jsonobject.getFloat("low") + "";
            String close = jsonobject.getFloat("close") + "";
            String volume = jsonobject.getFloat("volume") + "";
            String id = jsonobject.getString("_id");

//            Vector<String> row = new Vector();
            String[] row = {date, open, high, low, close, volume, id};
//            row.add(date);
//            row.add(open);
//            row.add(high);
//            row.add(low);
//            row.add(close);
//            row.add(volume);
//            System.out.println(row);
//            tableData.add(row);
            tableData.add(jsonobject);
            model.addRow(row);
        }
        // Create the JTable and JScrollPane
        jTable1.setModel(model);
        jTable1.setEnabled(false);

//        setLayout(new BorderLayout());
//        add(jScrollPane1,BorderLayout.CENTER);
//        setLayout(new BorderLayout());
//        JScrollPane scrollPane = new JScrollPane(table);
//        searchOneMinHistory searchPanel= new searchOneMinHistory("1MinHistory");
//        JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT,scrollPane, searchPanel);
//         searchPanel.getjButton1().addMouseListener(new MouseAdapter(){
//             @Override
//             public void mouseClicked(MouseEvent e){
//                 if(SwingUtilities.isLeftMouseButton(e)){
//                     String queryString = searchPanel.getjTextArea1().getText();
//                     System.out.println(queryString);
//                     model.setRowCount(0);
//                     table(queryString);
//                 }
//             }
//         });
//        add(jsp, BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();

        jTextField1.setText("Start Date");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Start Date");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField2.setText("End Date");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton2.setText("End Date");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Request");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton4.setText("Select Symbol");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("Selected Symbol");
        jLabel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabel1PropertyChange(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(15, 15, 15))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String receivedDate = new com.mycompany.metatraderbrokeradmin.Utility.DatePicker(this).setPickedDate();
            jTextField1.setText(receivedDate);
//         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");' 
            String splitdate[] = receivedDate.split("-");
            date1 = splitdate[2] + "-" + splitdate[1] + "-" + splitdate[0];
            System.out.println("date1: " + date1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String receivedDate = new com.mycompany.metatraderbrokeradmin.Utility.DatePicker(this).setPickedDate();
            jTextField2.setText(receivedDate);
            String splitdate[] = receivedDate.split("-");
            date2 = splitdate[2] + "-" + splitdate[1] + "-" + splitdate[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        table(symbol);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
//        new AddSymbol2BidAsk1(0).setVisible(true);

        new PopupTree4Symbol("ChartPanel").setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabel1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1PropertyChange

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed

//        jComboBox2.addActionListener(e -> {
//            String selectedItem = (String) jComboBox2.getSelectedItem();
//            this.time1 = " " + selectedItem;
//        });
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
//        jComboBox1.addActionListener(e -> {
//            String selectedItem = (String) jComboBox2.getSelectedItem();
//            this.time2 = " " + selectedItem;
//        });
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    public static javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
