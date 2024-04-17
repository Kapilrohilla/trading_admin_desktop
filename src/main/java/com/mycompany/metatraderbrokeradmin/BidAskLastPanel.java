/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin;

import static com.mycompany.metatraderbrokeradmin.ChartPanel.time2;
import com.mycompany.metatraderbrokeradmin.GroupSymbolpopup.PopupTree4Group;
import com.mycompany.metatraderbrokeradmin.GroupSymbolpopup.PopupTree4Symbol;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
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
 * @author kapilrohilla
 */
public class BidAskLastPanel extends javax.swing.JPanel {

    /**
     * Creates new form BidAskLastPanel
     */
    DefaultTableModel tableModel;
    static String startDate = "2024-01-28";
    static String endDate = "2024-01-29";
    static String time1 = "";
    static String time2 = "";
    public static String symbol = "XAUUSD";

    public BidAskLastPanel() {
        initComponents();
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Date");
        tableModel.addColumn("Bid");
        tableModel.addColumn("Ask");
        jt.setModel(tableModel);
        jt.setAutoCreateRowSorter(true);
        jComboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) e.getItem();
                    System.out.println("Selected item: " + selectedItem);
                    time1 = " " + selectedItem;
                }
            }
        });

        jComboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) e.getItem();
                    System.out.println("Selected item: " + selectedItem);
                    time2 = " " + selectedItem;
                }
            }
        });
    }

    String getData(String querySymbol) {
        System.out.println("startdate: " + startDate);
        System.out.println("EndDate: " + endDate);
        OkHttpClient client = new OkHttpClient();
        String url = com.mycompany.metatraderbrokeradmin.Utility.APIs.BID_ASK_LAST + querySymbol + "?startDate=" + startDate + time1 + "&endDate=" + endDate + time2;
        System.out.println("url: " + url);
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

    void openTickDialogBox(TickData t) {
        TickFrame tf = new TickFrame(t);
        tf.pack();
        tf.setVisible(true);
    }

    void bidAsk(String querySymbol) throws JSONException {
        ArrayList<TickData> arraList = new ArrayList();
        String apiData = getData(querySymbol);
        JSONArray js = new JSONArray(apiData);

        jComboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) e.getItem();
                    System.out.println("Selected item: " + selectedItem);
                    time1 = " " + selectedItem;
                }
            }
        });

        jComboBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) e.getItem();
                    System.out.println("Selected item: " + selectedItem);
                    time2 = " " + selectedItem;
                }
            }
        });

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Date");
        tableModel.addColumn("Bid");
        tableModel.addColumn("Ask");
        arraList.clear();

        // creating table model / column
        for (int i = 0; i < js.length(); i++) {
            JSONObject jsonobject = js.getJSONObject(i);
            // System.out.println("jsonobject: " + jsonobject);
            String bid = "" + jsonobject.getFloat("bid");
            String ask = "" + jsonobject.getFloat("ask");
            String date = jsonobject.getString("createdAt");
            String mesc = "" + jsonobject.getFloat("direction");
            String volume = "" + jsonobject.getFloat("volume");
            String ltp = "" + jsonobject.getFloat("ltp");
            String id = ""+jsonobject.getString("_id");
            Vector<String> data4row = new Vector();
            data4row.add(date);
            data4row.add(bid);
            data4row.add(ask);
            //data4row.add(mesc);
            //data4row.add(volume);
            //data4row.add(ltp);
            arraList.add(new TickData(date, volume, bid, ask, ltp, mesc,id));
            tableModel.addRow(data4row);
//           break;
        }
//        jt= new JTable(tableModel);
        jt.setModel(tableModel);
        jt.setEnabled(false);
//        JScrollPane sp = new JScrollPane(jt);
//        setLayout(new BorderLayout());
//        
        JPopupMenu menu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Add");
        JMenuItem menuItem2 = new JMenuItem("Edit");
        JMenuItem menuItem3 = new JMenuItem("Delete");

        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);

        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (e.getClickCount() == 2) {
                        int row = jt.rowAtPoint(e.getPoint());

                        int col = jt.columnAtPoint(e.getPoint());

                        // Get selected data
//                   Object selectedData = jt.getValueAt(row,col);
                        TickData t = arraList.get(row);
                        openTickDialogBox(t);
                    }
                }
//                if (SwingUtilities.isRightMouseButton(e)) {
//                    int row = jt.rowAtPoint(e.getPoint());
//                    
//                    int col = jt.columnAtPoint(e.getPoint());
//
//                    // Get selected data
//                    Object selectedData = jt.getValueAt(row, col);
//                    
//                    TickData t = arraList.get(row);
//                    // Show menu with selected data
//                    showMenu(e.getX(), e.getY(), selectedData);
//                }
            }

            private void showMenu(int x, int y, Object selectedData) {
                System.out.println(selectedData);
                menu.show(jt, x, y);
            }

        });
//       
    }

    /*
        String date = "2020.11.24";
        String bid = "12334";
        String ask = "293485";
        String data[][] = {
            {date, bid, ask},
            {date, bid, ask},
            {date, bid, ask},
            {date, bid, ask},{date, bid, ask},
            {date, bid, ask},
            {date, bid, ask},
            {date, bid, ask},{date, bid, ask},{date, bid, ask},{date, bid, ask},{date, bid, ask},{date, bid, ask},{date, bid, ask},
            {date, bid, ask},
            {date, bid, ask},
            {date, bid, ask},
            {date, bid, ask},{date, bid, ask},
            {date, bid, ask},
            {date, bid, ask},
            {date, bid, ask},{date, bid, ask},{date, bid, ask},{date, bid, ask},{date, bid, ask},{date, bid, ask},{date, bid, ask},
        };
        String column[] = {
            "Date", "Bid", "Ask"
        };
        
        JTable jt;
        jt = new JTable(data, column);
        setLayout(new BorderLayout());
        
        JScrollPane sp =new JScrollPane(jt);
//        add(sp, BorderLayout.CENTER);
        
        JPopupMenu menu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Add");
        JMenuItem menuItem2 = new JMenuItem("Edit");
        JMenuItem menuItem3 = new JMenuItem("Delete");

        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        
        jt.addMouseListener(new MouseAdapter() {
           @Override
           public void
               mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                   int row = jt.rowAtPoint(e.getPoint());

                   int col = jt.columnAtPoint(e.getPoint());

                   // Get selected data
                   Object selectedData = jt.getValueAt(row, col);

                   // Show menu with selected data
                   showMenu(e.getX(), e.getY(), selectedData);
                }
           }
           
            private void showMenu(int x, int y, Object selectedData) {
//                System.out.println(selectedData);
                menu.show(jt, x, y);
            }
            
//            menuItem1.addActionListener(e -> {
//                // Edit cell logic based on selected data
//            });
//
//            menuItem2.addActionListener(e -> {
//                // Copy cell value logic
//            });
//
//            menuItem3.addActionListener(e -> {
//            // Delete row logic
//            });

       });
        add(sp);
    }
     */
//   void popupMenu(){
//        final JPopupMenu menu = new JPopupMenu("Menu"); 
//     
//        JMenuItem open = new JMenuItem("Open");
//        JMenuItem cut = new JMenuItem("Cut");
//        JMenuItem copy = new JMenuItem("Copy");
//        JMenuItem paste = new JMenuItem("Paste");
//     
//        menu.add(open); 
//        menu.add(cut); 
//        menu.add(copy);
//        menu.add(paste);
//     
//        this.addMouseListener(new MouseAdapter() {
//        public void mouseClicked(MouseAdapter e) {
//        //right mouse click event
//            if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1){
//                menu.show(this , e.getX(), e.getY());
//            }          
//        }               
//   });
//        add(menu);
////    pack();
//   } 
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
        jt = new javax.swing.JTable();
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

        jt.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jt);

        jButton4.setText("Select Symbol");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("Selected Symbol");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String receivedDate = new com.mycompany.metatraderbrokeradmin.Utility.DatePicker(this).setPickedDate();
            jTextField1.setText(receivedDate);
            //         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String splitdate[] = receivedDate.split("-");
            startDate = splitdate[2] + "-" + splitdate[1] + "-" + splitdate[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            String receivedDate = new com.mycompany.metatraderbrokeradmin.Utility.DatePicker(this).setPickedDate();
            jTextField2.setText(receivedDate);
            String splitdate[] = receivedDate.split("-");
            endDate = splitdate[2] + "-" + splitdate[1] + "-" + splitdate[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        bidAsk(symbol);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
//        new AddSymbol2BidAsk1(1).setVisible(true);
        new PopupTree4Symbol("BidAsk").setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    public static javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable jt;
    // End of variables declaration//GEN-END:variables
}
