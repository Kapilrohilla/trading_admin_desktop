/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin;

import com.mycompany.metatraderbrokeradmin.GroupConfig.GroupConfig;
import com.mycompany.metatraderbrokeradmin.SymbolConfig.SixTabFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author techninza
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        setTitle("Broker Admin");
        initComponents();
        setState(MAXIMIZED_BOTH);
        menusetup();
        framesetup();
    }

    private void framesetup() {
        TreePanel leftPanel = new TreePanel();
//        leftPanel.setBackground(Color.RED);

//         TablePanel rightPanel = new TablePanel();
        JPanel rightPanel = new JPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(0.3);

//         BorderLayout layout = new BorderLayout();
//        layout.setHgap(10);
//        layout.setVgap(10);
        add(splitPane);
        pack();

//        getContentPane().add(scrollPane, BorderLayout.WEST);
//        getContentPane().add(rightPanel, BorderLayout.CENTER);
    }

    private void menusetup() {
        JMenuItem a1 = new JMenuItem("Add Server");
        JMenuItem a2 = new JMenuItem("Remove Server");
        JMenuItem a3 = new JMenuItem("Server Properties");
        JMenuItem a4 = new JMenuItem("Connect");
        JMenuItem a5 = new JMenuItem("Disconnect");
        JMenuItem a6 = new JMenuItem("Change Password");
        JMenuItem a7 = new JMenuItem("Export");
        JMenuItem a8 = new JMenuItem("Import");
        JMenuItem a9 = new JMenuItem("Open Data Folder");
        JMenuItem a10 = new JMenuItem("Print");
        JMenuItem a11 = new JMenuItem("Print Preview");
        JMenuItem a12 = new JMenuItem("Print Setup");
        JMenuItem a13 = new JMenuItem("Exit");
        jMenu1.add(a1);
        jMenu1.add(a2);
        jMenu1.add(a3);
        jMenu1.add(a4);
        jMenu1.add(a5);
        jMenu1.add(a6);
        jMenu1.add(a7);
        jMenu1.add(a8);
        jMenu1.add(a9);
        jMenu1.add(a10);
        jMenu1.add(a11);
        jMenu1.add(a12);
        jMenu1.add(a13);

        JMenuItem b1 = new JMenuItem("Apply Changes");
        JMenuItem b2 = new JMenuItem("Add");
        JMenuItem b3 = new JMenuItem("Edit");
        JMenuItem b4 = new JMenuItem("Delete");
        JMenuItem b5 = new JMenuItem("Move Up");
        JMenuItem b6 = new JMenuItem("Move Down");
        JMenuItem b7 = new JMenuItem("Sort Alphabetically");
        JMenuItem b8 = new JMenuItem("Enable");
        JMenuItem b9 = new JMenuItem("Disable");
        JMenuItem b10 = new JMenuItem("Find");
        JMenuItem b11 = new JMenuItem("Find Next");
        JMenuItem b12 = new JMenuItem("Find Previous");
        jMenu2.add(b1);
        jMenu2.add(b2);
        jMenu2.add(b3);
        jMenu2.add(b4);
        jMenu2.add(b5);
        jMenu2.add(b6);
        jMenu2.add(b7);
        jMenu2.add(b8);
        jMenu2.add(b9);
        jMenu2.add(b10);
        jMenu2.add(b11);
        jMenu2.add(b12);

        JMenuItem c1 = new JMenuItem("Languages");
        JMenuItem c2 = new JMenuItem("Toolbar");
        JMenuItem c3 = new JMenuItem("Status bar");
        JMenuItem c4 = new JMenuItem("Split");
        JMenuItem c5 = new JMenuItem("Navigate Forward");
        JMenuItem c6 = new JMenuItem("Navigate Backward");
        JMenuItem c7 = new JMenuItem("Toolbox");
        jMenu3.add(c1);
        jMenu3.add(c2);
        jMenu3.add(c3);
        jMenu3.add(c4);
        jMenu3.add(c5);
        jMenu3.add(c6);
        jMenu3.add(c7);

        JMenuItem d1 = new JMenuItem("Refresh Configuration");
        JMenuItem d2 = new JMenuItem("Restart Server");
        JMenuItem d3 = new JMenuItem("Deploy Server");
        JMenuItem d4 = new JMenuItem("Restart Gateways");
        JMenuItem d5 = new JMenuItem("Restart Datafeeds");
        JMenuItem d6 = new JMenuItem("Synchornize History");
        JMenuItem d7 = new JMenuItem("Start Live Update");
        JMenuItem d8 = new JMenuItem("Activate License");
        jMenu4.add(d1);
        jMenu4.add(d2);
        jMenu4.add(d3);
        jMenu4.add(d4);
        jMenu4.add(d5);
        jMenu4.add(d6);
        jMenu4.add(d7);
        jMenu4.add(d8);

        JMenuItem e1 = new JMenuItem("Help Topics");
        JMenuItem e2 = new JMenuItem("What's New");
        jMenu5.add(e1);
        jMenu5.add(e2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("View");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Services");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Help");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
