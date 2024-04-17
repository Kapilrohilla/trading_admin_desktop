/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin;

import com.mycompany.metatraderbrokeradmin.GroupConfig.GroupConfig;
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
public class GroupPanel extends javax.swing.JPanel {

    private JTable jTable1;

    ArrayList<groups> arraylist;

    /**
     * Creates new form TablePanel
     */
    public GroupPanel() {

        tabledata();
    }

    class groups {

        String id;
        String name;
        String server;
        String company;
        String type;
        String application;

        public groups() {

        }

        public groups(String id, String name, String server, String company, String type, String application) {
            this.id = id;
            this.name = name;
            this.server = server;
            this.company = company;
            this.type = type;
            this.application = application;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getApplication() {
            return application;
        }

        public void setApplication(String application) {
            this.application = application;
        }

    }

    String getData(String url) {
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

    private void tabledata() {

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem add = new JMenuItem("Add");
        JMenuItem edit = new JMenuItem("Edit");
        JMenuItem delete = new JMenuItem("Delete");
        JMenuItem moveup = new JMenuItem("Move Up");
        JMenuItem movedown = new JMenuItem("Move Down");
        JMenuItem sortalphabatecally = new JMenuItem("Sort Alphabatecally");
        JMenuItem automationtriggers = new JMenuItem("Automation Triggers");
        JMenuItem automationaction = new JMenuItem("Automation Action");

        JMenuItem exporttofile = new JMenuItem("Export To File");
        JMenuItem importfromfile = new JMenuItem("Import From File");
        JMenuItem importfromserver = new JMenuItem("Import From Server");
        
        popupMenu.add(add);
        popupMenu.add(edit);
        popupMenu.add(delete);
        popupMenu.add(moveup);
        popupMenu.add(movedown);
        popupMenu.add(sortalphabatecally);
        popupMenu.add(automationtriggers);
        popupMenu.add(automationaction);
        popupMenu.add(exporttofile);
        popupMenu.add(importfromfile);
        popupMenu.add(importfromserver);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GroupConfig();
            }
        });

        long lo = System.currentTimeMillis();
        arraylist = new ArrayList();
        String apiUrl = com.mycompany.metatraderbrokeradmin.Utility.APIs.GET_GROUPS+"?timestamp=" + lo;
        try {
            String responseData = getData(apiUrl);
            System.out.println(responseData);
            JSONObject json = new JSONObject(responseData);
            JSONArray js = json.getJSONArray("message");

            for (int i = 0; i < js.length(); i++) {
                JSONObject jsonobject = js.getJSONObject(i);
                String id = jsonobject.getString("_id");
                String name = jsonobject.getString("Name");
                String company, server;

                try {
                    company = jsonobject.getString("Company");
                } catch (JSONException e) {
                    company = "";
                }
                try {
                    server = jsonobject.getString("MailServer");
                } catch (JSONException e) {
                    server = "";
                }
                arraylist.add(new groups(id, name, server, company, "", ""));
            }

            // Create the table model
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Group");
            model.addColumn("Company");
            model.addColumn("Server");
            model.addColumn("Type");
            model.addColumn("Application");

            // Populate the table with data
            for (groups student : arraylist) {
                Object[] row = {student.getName(), student.getCompany(), student.getServer(), student.getType(), student.getApplication()};
                model.addRow(row);
            }
            jTable1 = new JTable(model);
            jTable1.setEnabled(false);
        } catch (JSONException e) {
        }
        if(jTable1!=null){
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                int row = jTable1.rowAtPoint(e.getPoint());
//                int column = jTable1.columnAtPoint(e.getPoint());

//                Object value = jTable1.getValueAt(row, column);
                String groupid = arraylist.get(row).getId();
                new GroupConfig(groupid);
                }
                  else if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        }

        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(jTable1);
        add(scrollPane, BorderLayout.CENTER);

        scrollPane.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
// 
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

    }

}
