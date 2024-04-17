/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin;

import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import com.mycompany.managerapp.AccountDetails.AccountDetailsContainer;
import com.mycompany.managerapp.AccountDetails.newAccount;
import com.mycompany.metacustomer.Auth.Demo;
import com.mycompany.metacustomer.Auth.DemoFrame;
import com.mycompany.metatraderbrokeradmin.Customer.temp;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
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
public final class TradingAccountTable extends javax.swing.JPanel {

    String name, email, group, country, balance, id;

    class over {

        String namm;
        String emm;
        String grr;
        String con;
        String bal;
        String id;
        String groupid;

        public over() {

        }

        public over(String namm, String emm, String grr, String con, String bal, String id, String groupid) {
            this.namm = namm;
            this.emm = emm;
            this.grr = grr;
            this.con = con;
            this.bal = bal;
            this.id = id;
            this.groupid = groupid;
        }

        public String getNamm() {
            return namm;
        }

        public void setNamm(String namm) {
            this.namm = namm;
        }

        public String getEmm() {
            return emm;
        }

        public void setEmm(String emm) {
            this.emm = emm;
        }

        public String getGrr() {
            return grr;
        }

        public void setGrr(String grr) {
            this.grr = grr;
        }

        public String getCon() {
            return con;
        }

        public void setCon(String con) {
            this.con = con;
        }

        public String getBal() {
            return bal;
        }

        public void setBal(String bal) {
            this.bal = bal;
        }

        public String getId() {
            return this.id;
        }

        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }

    }

    ArrayList<over> overarray = new ArrayList();
    ArrayList<JSONObject> jsonarray = new ArrayList();

    public TradingAccountTable() {
        initComponents();
        table();
    }

    final void table() {
        String apiData = getData();
        System.out.println("apiData in tradingAccounttable: " + apiData);
        JSONArray users = new JSONArray(apiData);
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("USERID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Group");
        tableModel.addColumn("Country");
        tableModel.addColumn("Balance/Checked");
        System.out.println("apiData = " + users);
        System.out.println("length = " + users.length());

        for (int i = 0; i < users.length(); i++) {
            JSONObject jso = users.getJSONObject(i);
            try {
                name = jso.getString("name");
                System.out.println("name = " + name);
            } catch (JSONException ex) {
                name = "";
            }

            try {
                email = jso.getString("email");
                System.out.println("email = " + email);
            } catch (JSONException ex) {
                email = "";
            }
            try {
                group = jso.getString("groupName");
                System.out.println("group = " + group);
            } catch (JSONException ex) {
                group = "";
            }
            try {
                country = jso.getString("country");
                System.out.println("country = " + country);
            } catch (JSONException ex) {
                country = "";
            }
            try {
                balance = jso.getFloat("balance") + "";
                System.out.println("balance = " + balance);
            } catch (JSONException ex) {
                balance = 0 + "";
            }
            id = jso.getString("_id");
            String groupid = jso.getString("group");
            String[] rowData = {email, name, group, country, balance, id, groupid};
            overarray.add(new over(name, email, group, country, balance, id, groupid));
            jsonarray.add(jso);
            tableModel.addRow(rowData);
        }

        JTable jt = new JTable(tableModel);
        jt.setAutoCreateRowSorter(true);
        jt.setEnabled(false);
        JScrollPane jsp = new JScrollPane(jt);
        setLayout(new BorderLayout());
        add(jsp, BorderLayout.CENTER);

        JPopupMenu jp = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Add");
        JMenuItem menuItem2 = new JMenuItem("Edit");
        JMenuItem menuItem3 = new JMenuItem("Delete");
        JMenuItem menuItem4 = new JMenuItem("Export To File");

        jp.add(menuItem1);
        jp.add(menuItem2);
        jp.add(menuItem3);
        jp.addSeparator();
        jp.add(menuItem4);

        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new newAccount().setVisible(true);
//                    new Demo().setVisible(true);
                //new DemoFrame().setVisible(true);
            }
        });

        jsp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    jp.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    jp.show(e.getComponent(), e.getX(), e.getY());
                } else if (e.getClickCount() == 2) {
                    int row = jt.rowAtPoint(e.getPoint());
                    int column = jt.columnAtPoint(e.getPoint());
                    over x = overarray.get(row);
                    new AccountDetailsContainer(x.namm, x.emm, x.grr, x.con, x.bal, x.id, x.groupid, jsonarray.get(row)).setVisible(true);
                }
            }
        });
    }

    String getData() {
        OkHttpClient client = new OkHttpClient();
        String url = APIs.GET_ALL_TRADING_ACCOUNT;

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
