/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin;

import com.mycompany.metatraderbrokeradmin.SymbolConfig.SixTabFrame;
import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import okhttp3.Call;
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
 * @author techninza
 */
public class SymbolPanel extends javax.swing.JPanel {

    JTable jTable1;
    TableColumn column = new TableColumn();
    DefaultTableModel model = new DefaultTableModel();
    int row;

    /**
     * Creates new form TablePanel
     */
    public SymbolPanel() {
        try {
            tabledata();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String getData(String url) {
        OkHttpClient client = new OkHttpClient();

        // Create a Request object
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

    class symbol {

        String id;
        String sym;
        String description;
        String count;

        public symbol() {

        }

        public symbol(String id, String sym, String description, String count) {
            this.id = id;
            this.sym = sym;
            this.description = description;
            this.count = count;
        }

        public String getSym() {
            return sym;
        }

        public void setSym(String sym) {
            this.sym = sym;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    private void tabledata() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem add = new JMenuItem("Add");
        JMenuItem copy = new JMenuItem("Copy");
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
        popupMenu.add(copy);
        popupMenu.add(delete);
        popupMenu.add(moveup);
        popupMenu.add(movedown);
        popupMenu.add(sortalphabatecally);
        popupMenu.add(automationtriggers);
        popupMenu.add(automationaction);
        popupMenu.add(exporttofile);
        popupMenu.add(importfromfile);
        popupMenu.add(importfromserver);

        long lo = System.currentTimeMillis();
        ArrayList<symbol> arraylist = new ArrayList();
        ArrayList<String> symbolList = new ArrayList();
        String apiUrl = com.mycompany.metatraderbrokeradmin.Utility.APIs.GET_SYMBOLS + "?timestamp=" + lo;
        try {
            String responseData = getData(apiUrl);
            System.out.println("Response" + responseData);
            JSONObject json = new JSONObject(responseData);
            JSONArray js = json.getJSONArray("message");
            for (int i = 0; i < js.length(); i++) {
                JSONObject jsonobject = js.getJSONObject(i);
                String id = jsonobject.getString("_id");
                String symbol = jsonobject.getString("Symbol");
                symbolList.add(symbol);
                arraylist.add(new symbol(id, symbol, "", ""));
            }

            // Create the table model
            model.addColumn("Symbol");
            model.addColumn("B");
            model.addColumn("SO");
            model.addColumn("Description");
            model.addColumn("Count");

            // Populate the table with data
            for (symbol student : arraylist) {
                Object[] row = {student.getSym(), "", "", student.getDescription(), student.getCount()};
                model.addRow(row);
            }
            jTable1 = new JTable(model) {
                private static final long serialVersionUID = 1L;

                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            jTable1.setAutoCreateRowSorter(true);

        } catch (JSONException e) {
        }
        // Event for rightclick new button
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SixTabFrame("new");
            }
        });

        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String symId = arraylist.get(row).getId();
                try {
                    new SixTabFrame("Copy", symId).setVisible(true);
                } catch (Exception ex) {
                    System.out.println("exception occurred while copying the symbol");
                }

//                TableColumn newColumn = new TableColumn();
//                newColumn.setHeaderValue("NewColumn");
//                newColumn.setIdentifier("Value");
//                model.addColumn(newColumn);
//                new SixTabFrame("new");
            }
        });
          delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                String symId = arraylist.get(row).getId();
                
//                jTable1.removeColumn(column);
//                new SixTabFrame("new");

            JSONObject json=new JSONObject();
            json.put("Symbol", symId);
            
            System.out.println("SYMBOLS"+json.toString());
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, json.toString());

        Request request = new Request.Builder()
                .url(APIs.DELETE_CONFIG)
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        
        
        // Check for successful response
        if (response.isSuccessful()) {
            // Get response body as strings
            String responseBody = response.body().string();
            JOptionPane.showMessageDialog(SymbolPanel.this, "Symbol Deleted SuccessFully");
            BrokerAdmin.splitPane.setRightComponent(new SymbolPanel());
            System.out.println("_____________________*****************_________________");
            System.out.println(responseBody);
            System.out.println("_____________________*****************_________________");
        } else {
            // Handle error
            System.out.println("code: " + response.code());
            System.out.println("Error: " + response.body().toString());
        }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = jTable1.rowAtPoint(e.getPoint());
                if (e.getClickCount() == 2) {
                    System.out.println("row clicked: " + row);
                    String symId = arraylist.get(row).getId();
                    try {
                        new SixTabFrame("old", symId).setVisible(true);
                    } catch (Exception ex) {
                        Logger.getLogger(SymbolPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(jTable1);
        add(scrollPane, BorderLayout.CENTER);

        scrollPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

}
