package com.mycompany.metatraderbrokeradmin.GroupSymbolpopup;

import com.mycompany.metatraderbrokeradmin.BidAskLastPanel;
import com.mycompany.metatraderbrokeradmin.ChartPanel;
import com.mycompany.metatraderbrokeradmin.DealPanel;
import com.mycompany.metatraderbrokeradmin.GroupConfig.AddComissionFrame;
import com.mycompany.metatraderbrokeradmin.GroupConfig.NewSymbolCommon;
import com.mycompany.metatraderbrokeradmin.OrderPanel;
import com.mycompany.metatraderbrokeradmin.PositionPanel;
import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import com.mycompany.metatraderbrokeradmin.Utility.GroupModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Kapil
 */
public class PopupTree4Symbol extends javax.swing.JDialog {

    /**
     * Creates new form PopupTree4Symbol
     */
    JTree tree;
    ArrayList<JSONObject> symbolList = new ArrayList<>();

    String symbolname = "";
    String pathofsymbol = "";
    String symbolId;

    ArrayList<Category> categorylist = new ArrayList();

    class Category {

        String id;
        String name;
        ArrayList<String> symbols;

        public Category() {

        }

        public Category(String id, String name, ArrayList<String> symbols) {
            this.id = id;
            this.name = name;
            this.symbols = symbols;
        }

        public ArrayList<String> getSymbols() {
            return symbols;
        }

        public void setSymbols(ArrayList<String> symbols) {
            this.symbols = symbols;
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

    }

    public PopupTree4Symbol(String from) {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.setTitle("Add Symbol");
        long lo = System.currentTimeMillis();
        String apiUrl = APIs.GET_SYMBOL_CATEGORY + "?timestamp=" + lo;
        String responseData = getData(apiUrl);
        //  JSONObject jsa = new JSONObject(responseData);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("ROOT");
        JSONObject json = new JSONObject(responseData);
        JSONArray js = json.getJSONArray("message");

        for (int i = 0; i < js.length(); i++) {
//            System.out.println("working....");
            JSONObject jsonobject = js.getJSONObject(i);
            String symbol = jsonobject.getString("name");
            String symbolId = jsonobject.getString("_id");

            JSONArray symbolarray = jsonobject.getJSONArray("symbols");

            DefaultMutableTreeNode group = new DefaultMutableTreeNode(symbol);

            if (symbolarray.length() > 0) {
                ArrayList<String> symarrs = new ArrayList();
                for (int j = 0; j < symbolarray.length(); j++) {
                    JSONObject symbolData = symbolarray.getJSONObject(j);
                    symbolList.add(symbolData);
                    final String symbolName = symbolData.getString("Symbol");
                    symarrs.add(symbolName);
                    DefaultMutableTreeNode symbolNode = new DefaultMutableTreeNode(symbolName);
                    group.add(symbolNode);
                }
                categorylist.add(new Category(symbolId, symbol, symarrs));
            }
            root.add(group);
            recursionsymbol(jsonobject, group);
        }

        tree = new JTree(root);

        tree.addTreeSelectionListener((TreeSelectionEvent e) -> {
            TreePath selectedPath = e.getNewLeadSelectionPath();
            if (selectedPath != null) {
                pathofsymbol = selectedPath + "";
                pathofsymbol = pathofsymbol.substring(1, pathofsymbol.lastIndexOf("]"));
                System.out.println(pathofsymbol + "");
                String selectedNode = selectedPath.getLastPathComponent().toString();
                System.out.println("Selected node: " + selectedNode);

                if (selectedNode.equalsIgnoreCase("groups")) {
//                    BrokerAdmin.splitPane.setRightComponent(new GroupPanel());
                }
//                    System.out.println(symbolList);
                for (int i = 0; i < symbolList.size(); i++) {
                    String symName = symbolList.get(i).getString("Symbol");
                    if (selectedNode.equalsIgnoreCase(symName)) {
                        symbolname = symName;
//                            symbolId = symbolList.get(i).getString("_id");
                        System.out.println("tapped");
//                            groupmodel=new GroupModel(symbolId,symName);
//                            isFound = true;
                        break;
                    }
                }
//                    if (!isFound) {
//                        BrokerAdmin.splitPane.setRightComponent(new HomePage());
//                    }
            }

        });
        JButton update = new JButton("Update");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (from.equalsIgnoreCase("ChartPanel")) {
                    ChartPanel.symbol = symbolname;
                    ChartPanel.jLabel1.setText(symbolname);

                } else if (from.equalsIgnoreCase("BidAsk")) {
                    BidAskLastPanel.symbol = symbolname;
                    BidAskLastPanel.jLabel1.setText(symbolname);

                } else if (from.equalsIgnoreCase("GroupNewSymbol")) {
                    String a[] = pathofsymbol.split(",");
                    String forward = "";
                    for (int i = 0; i < a.length; i++) {
                        if (i == (a.length - 1)) {
                            if (issymbol(a[i])) {
                                forward += a[i];
                            } else {
                                forward += a[i] + "/*";
                                NewSymbolCommon.categorylistsymbol = iscategory(a[i]);
                                System.out.println("newsms" + NewSymbolCommon.categorylistsymbol.toString());
                            }
                            continue;
                        }
                        forward += a[i] + "/";
                    }

                    NewSymbolCommon.jLabel12.setText(forward);

                } else if (from.equalsIgnoreCase("OrderPanel")) {
//                    String a[]=pathofsymbol.split(",");
//                    String forward="";
//                    for(int i=0;i<a.length;i++)
//                    {
//                        if(i==(a.length-1))
//                        {
//                            if(issymbol(a[i]))
//                            forward+=a[i];
//                            else 
//                                forward+=a[i]+"/*";
//                            continue;
//                        }
//                        forward+=a[i]+"/";
//                    }
                    OrderPanel.jLabel1.setText(symbolname);
                } else if (from.equalsIgnoreCase("DealPanel")) {
                    String a[] = pathofsymbol.split(",");
                    String forward = "";
                    for (int i = 0; i < a.length; i++) {
                        if (i == (a.length - 1)) {
                            if (issymbol(a[i])) {
                                forward += a[i];
                            } else {
                                forward += a[i] + "/*";
                            }
                            continue;
                        }
                        forward += a[i] + "/";
                    }
                    DealPanel.jLabel1.setText(forward);
                } else if (from.equalsIgnoreCase("PositionPanel")) {
                    String a[] = pathofsymbol.split(",");
                    String forward = "";
                    for (int i = 0; i < a.length; i++) {
                        if (i == (a.length - 1)) {
                            if (issymbol(a[i])) {
                                forward += a[i];
                            } else {
                                forward += a[i] + "/*";
                            }
                            continue;
                        }
                        forward += a[i] + "/";
                    }
                    PositionPanel.jLabel2.setText(forward);
                } else if (from.equalsIgnoreCase("AddCommissionFrame")) {
                    String a[] = pathofsymbol.split(",");
                    String forward = "";
                    for (int i = 0; i < a.length; i++) {
                        if (i == (a.length - 1)) {
                            if (issymbol(a[i])) {
                                forward += a[i];
                            } else {
                                forward += a[i] + "/*";
                            }
                            continue;
                        }
                        forward += a[i] + "/";
                    }
                    AddComissionFrame.jLabel12.setText(forward);
                }

                dispose();
            }

        });
        setLayout(new BorderLayout());
        add(update, BorderLayout.NORTH);
        JScrollPane jsp = new JScrollPane(tree);
        add(jsp, BorderLayout.CENTER);
        setModal(true);
        setLocationRelativeTo(null);
//        setVisible(true);
    }

    ArrayList<String> iscategory(String name) {
        name = name.trim();
        for (int i = 0; i < categorylist.size(); i++) {
            if (categorylist.get(i).getName().equalsIgnoreCase(name)) {
                return categorylist.get(i).getSymbols();
            }
        }
        return new ArrayList();
    }

    boolean issymbol(String name) {
//        System.out.println(name+"namdsf");
        name = name.trim();
        for (int i = 0; i < symbolList.size(); i++) {
            String symName = symbolList.get(i).getString("Symbol");
            if (name.equalsIgnoreCase(symName)) {
//                            symbolname=symName;
//                            String symbolId = symbolList.get(i).getString("_id");
                System.out.println("tapped");
//                            groupmodel=new GroupModel(symbolId,symName);
//                            isFound = true;
                return true;

            }
        }
        return false;
    }

    final String getData(String url) {
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

    private void recursionsymbol(JSONObject jsonobject, DefaultMutableTreeNode parentNode) {
        JSONArray jxa = jsonobject.getJSONArray("nestedCategories");

        if (jxa.length() != 0) {
            for (int i = 0; i < jxa.length(); i++) {
                JSONObject jsoa = jxa.getJSONObject(i);
                String symbolName = jsoa.getString("name");
                DefaultMutableTreeNode symbol = new DefaultMutableTreeNode(symbolName);

                parentNode.add(symbol);
                System.out.println("symbol added successfully");

                if (jsoa.getJSONArray("nestedCategories").length() != 0) {
                    recursionsymbol(jsoa, symbol);
                }

            }
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PopupTree4Symbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopupTree4Symbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopupTree4Symbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopupTree4Symbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
////                new PopupTree4Symbol().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
