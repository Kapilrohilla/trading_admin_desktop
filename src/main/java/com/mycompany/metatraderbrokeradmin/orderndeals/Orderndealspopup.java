/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin.orderndeals;

import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import com.mycompany.managerapp.AccountDetails.Visualization;
import static com.mycompany.managerapp.AccountDetails.Visualization.client_;
import com.mycompany.metatraderbrokeradmin.Utility.Config;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.cef.CefApp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author techninza
 */
enum PopupType {
    order,
    position
}

public class Orderndealspopup extends javax.swing.JDialog {

    String cid;

    /**
     * Creates new form Orderndealspopup
     */
    public Orderndealspopup() {
//        initComponents();
        setModal(true);
        tabledata();

        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    public Orderndealspopup(String type, String orderId) {
//        initComponents();
        cid = orderId;
        String apiData = getData(type, orderId);
        System.out.println("api Data == " + apiData);
        JSONObject js = new JSONObject(apiData);
        System.out.println("working..");
        tabledata(type, js);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private String getData(String type, String id) {
        String url;
        if ("order".equals(type)) {
            url = APIs.GET_ORDER_DETAIL + "?orderId=" + id;
        } else if ("position".equals(type)) {
            url = APIs.GET_POSITION_DETAIL + "?positionId=" + id;
        } else if ("deals".equals(type)) {
            url = APIs.GET_DEALS_DETAIL + "?dealId=" + id;
        } else {
            return "";
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);

        try {
            System.out.println("API executed successfully");
            Response res = call.execute();
            return res.body().string();
        } catch (IOException e) {
            System.out.println("API Failed");
            return "";
        }
    }

    private void tabledata() {

        Object[][] data = {
            {"John Doe", "john.doe@example.com", "df", "dfg", "dfg", 32},
            {"John Doe", "john.doe@example.com", "df", "dfg", "dfg", 32},
            {"John Doe", "john.doe@example.com", "df", "dfg", "dfg", 32}};
        // Create the column names for the table
        String[] columnNames = {"Ticket", "Time", "Type", "Volume", "Price", "Profit"};

        // Create the table model
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        JTable jTable1 = new JTable(tableModel);
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(jTable1);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Create the tabs
        CommonPanel tab1 = new CommonPanel();
//        JButton b=new JButton("OK");
//        tab1.add(b,BorderLayout.SOUTH);
//        tab1.add(new JLabel("Tab 1"));

        CurrencyPanel tab2 = new CurrencyPanel();
//        tab2.add(new JLabel("Tab 2"));

        TicksPanel tab3 = new TicksPanel();
//        tab3.add(new JLabel("Tab 3"));

        JournalPanel tab4 = new JournalPanel();
//        tab4.add(new JLabel("Tab 4"));

        // Add the tabs to the tabbed pane
        tabbedPane.addTab("Common", tab1);
        tabbedPane.addTab("Currency", tab2);
        tabbedPane.addTab("Quotes", tab3);
        tabbedPane.addTab("Trade", tab4);

        // Add the tabbed pane to the frame
//        add(tabbedPane);
        JSplitPane jSplitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, tabbedPane);
        jSplitPane1.setDividerLocation(150);
        add(jSplitPane1, BorderLayout.CENTER);
    }

    private void tabledata(String type, JSONObject js) {
        System.out.println("orderDealsPopup called ....");
        JSONObject orderDetails = null;

        DefaultTableModel tableModel = new DefaultTableModel();
        String[] columnNames = {"Ticket", "Time", "Type", "Volume", "Price", "Profit"};
        for (int i = 0; i < columnNames.length; i++) {
            tableModel.addColumn(columnNames[i]);
        }
        if ("order".equals(type)) {
            orderDetails = js.getJSONObject("order");
        } else if ("position".equals(type)) {
            orderDetails = js.getJSONObject("position");
        } else if ("deals".equals(type)) {
            orderDetails = js.getJSONObject("deals");
        }
        if (orderDetails != null) {
            String ticket = orderDetails.getString("ticket");
            String time = orderDetails.getString("createdAt");
            int orderType = orderDetails.getInt("type");
            String buyOrSell;
            if (orderType == 0) {
                buyOrSell = "sell";
            } else if (orderType == 1) {
                buyOrSell = "buy";
            } else {
                buyOrSell = type + "";
            }
            String volume;
            try {
                volume = orderDetails.getFloat("currentVolume") + "";
            } catch (JSONException ex) {
                volume = 0 + "";
            }
            String price = orderDetails.getFloat("price") + "";
            String profit;
            try {
                profit = orderDetails.getFloat("profit") + "";
            } catch (JSONException ex) {
                profit = 0 + "";
            }
            String[] rowData = {ticket, time, buyOrSell, volume, price, profit};
            tableModel.addRow(rowData);
        }

        JTable jTable1 = new JTable(tableModel);
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(jTable1);

        JTabbedPane tabbedPane = new JTabbedPane();
        CommonPanel commonPanel;
        if ("position".equals(type)) {
            commonPanel = new CommonPanel(js.getJSONObject("position"));
        } else if ("order".equals(type)) {
            commonPanel = new CommonPanel(js.getJSONObject("order"));
        } else {
            commonPanel = new CommonPanel(js.getJSONObject("deals"));
        }
        // Create the tabs
        CommonPanel tab1 = commonPanel;
//        CurrencyPanel tab2 = new CurrencyPanel("", js.getJSONArray("chart"));

        TicksPanel tab3 = new TicksPanel(js.getJSONArray("details"));

        JSONArray journalDetail = js.getJSONArray("journal");
        JournalPanel tab4 = new JournalPanel(journalDetail);

        String symbol = Config.BASE_URL + "visualize?orderId=" + cid;

        // Add the tabs to the tabbed pane
        tabbedPane.addTab("Details", tab1);
        tabbedPane.addTab("Visualization", new Visualization(symbol, false, false));
        tabbedPane.addTab("Ticks", tab3);
        tabbedPane.addTab("Journal", tab4);

        // Add the tabbed pane to the frame
//        add(tabbedPane);
        JSplitPane jSplitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, tabbedPane);
        jSplitPane1.setDividerLocation(150);
        add(jSplitPane1, BorderLayout.CENTER);

//        cefApp_
//         addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//               client_.dispose();
////                dispose();
//            }
//        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Orderndealspopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Orderndealspopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Orderndealspopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Orderndealspopup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Orderndealspopup();
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
