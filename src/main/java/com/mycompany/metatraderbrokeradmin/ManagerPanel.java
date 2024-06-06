/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import com.mycompany.metacustomer.Auth.AuthContainer;
import com.mycompany.metacustomer.Auth.Login;
import com.mycompany.metatraderbrokeradmin.Customer.temp;
import com.mycompany.metatraderbrokeradmin.Manager.ManagerTabs;
import com.mycompany.metatraderbrokeradmin.Manager.Signup;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
import org.json.JSONObject;

/**
 *
 * @author techninza
 */
public class ManagerPanel extends javax.swing.JPanel {

    public static DefaultTableModel tablemodel;

    /**
     * Creates new form ManagerPanel
     */
    public ManagerPanel() {
        initComponents();
        tabledata();
    }

//    public static void refresh()
//    {
//        ManagerPanel managerpanel=new ManagerPanel();
//        managerpanel.tabledata();
//    }
//    
    String getData(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);

        try {
            Response res = call.execute();
            return res.body().string();
        } catch (Exception e) {
            System.out.println("Runtime error");
            return "";
        }
    }

    ArrayList<String> idlist = new ArrayList();
    ArrayList<JSONArray> groups = new ArrayList();
    ArrayList<JSONArray> perm = new ArrayList();

    public void tabledata() {
        idlist.clear();
        groups.clear();
        String apiUrl = APIs.GET_ALL_MANAGERS;
        String apiData = getData(apiUrl);
        System.out.println(apiData);

        tablemodel = new DefaultTableModel();
        tablemodel.addColumn("Email");
        tablemodel.addColumn("Name");
        tablemodel.addColumn("Mailbox");
        tablemodel.addColumn("Groups");

        JSONArray js = new JSONObject(apiData).getJSONArray("Managers");
        for (int i = 0; i < js.length(); i++) {
            JSONObject jso = js.getJSONObject(i);
            String id = jso.getString("_id");
            idlist.add(id);
            String name = jso.getString("name");
            String email = jso.getString("email");
            String mailBox = "mailBox";//jso.getString("mailBox");
            groups.add(jso.getJSONArray("groups"));
            String group = jso.getJSONArray("groups").toString();
            JSONArray permissions;
            try {
                permissions = jso.getJSONArray("permissions");
                perm.add(permissions);
            } catch (Exception e) {
                e.printStackTrace();
                permissions = new JSONArray();
            }
//            for(int j=0; j<groups.length(); j++){
//                if(i==0){
//                    group = group + groups.getString(i);
//                }else{
//                    group = group + ", "+groups.getString(i);
//                }   
//            }
            String[] rowData = {email, name, mailBox, group};
            tablemodel.addRow(rowData);
        }

        JTable jt = new JTable(tablemodel);
        jt.setEnabled(false);
        jt.setAutoCreateRowSorter(true);

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

        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filepath = "";
                JFileChooser chooser = new JFileChooser();
//    FileNameExtensionFilter filter = new FileNameExtensionFilter(
//        "JPG & GIF Images", "jpg", "gif");
//    chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(ManagerPanel.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    filepath = chooser.getSelectedFile().getAbsolutePath().toString();
                    System.out.println(filepath);
//            jLabel2.setText("File Selected " + chooser.getSelectedFile().getName());

                }
//               new AuthContainer(1).setVisible(true);
                try {
                    Document doc = new Document();
                    PdfWriter.getInstance(doc, new FileOutputStream(filepath));
                    doc.open();
                    for (int i = 0; i < js.length(); i++) {
                        JSONObject jso = js.getJSONObject(i);
                        String name = jso.getString("name");
                        String email = jso.getString("email");
                        String mailBox = "mailBox";
                        String group = jso.getJSONArray("groups").toString();
                        doc.add(new Paragraph((i + 1) + ". " + name + " : " + email + ": " + ":" + group));
                    }

                    doc.close();
                    JOptionPane.showMessageDialog(ManagerPanel.this, "PDF Created SuccessFully");
                    System.out.println("PDF created successfully!");
                } catch (Exception ef) {

                }
            }
        });

        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AuthContainer(1).setVisible(true);
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
                if (e.getClickCount() == 2) {
                    int row = jt.rowAtPoint(e.getPoint());
                    System.out.println("groups: " + groups.get(row));
                    int column = jt.columnAtPoint(e.getPoint());
                    new ManagerTabs(idlist.get(row), groups.get(row), perm.get(row)).setVisible(true);
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    jp.show(e.getComponent(), e.getX(), e.getY());
                }
//                String id = arrayList.get(row)[0];
//                System.out.println("client id = " + id);
////                Object selectedData = table.getValueAt(row, column);
//                // Get the value of the clicked cell
//                Object value = table.getValueAt(row, column);
//                if (SwingUtilities.isLeftMouseButton(e)) {
////                    if (e.getClickCount() == 2) {
//                    new temp(id);
////                    }
//                }
            }
        });
        /*
         Object[][] data = {
            {"1000","John Doe", "Administrator", "*"},
            {"1001","Jane Doe",  "Administrator", "*"},
            {"1003","Peter Jones",  "Administrator", "*"},
        };
         */
        // Create the column names for the table
//        String[] columnNames = {"Login","Name", "Mailbox", "Groups"};

        // Create the table model
//        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
//        JTable jTable1=new JTable(tablemodel);
//        setLayout(new BorderLayout());
//        JScrollPane scrollPane = new JScrollPane(jTable1);
//        add(scrollPane,BorderLayout.CENTER);
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
