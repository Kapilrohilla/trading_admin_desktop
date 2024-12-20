/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin;

import com.mycompany.metatraderbrokeradmin.Syncronization.HistoryChartAndSymbolSyncronization;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author kapilrohilla
 */
public class SyncronizationPanel extends javax.swing.JPanel {

    /**
     * Creates new form Syncronization
     */
    public SyncronizationPanel() {
//        initComponents();
        syncTable();
    }

    String server = "access RapidTrader";
    String from = "2021.11.12";
    String to = "293485";
    String symbol = "Forex\\*";

    void syncTable() {
        String data[][] = {
            {server, from, to, symbol},
            {server, from, to, symbol},
            {server, from, to, symbol},};
        String column[] = {
            "Server", "Frome", "To", "Symbol"
        };

        JTable jt;
        jt = new JTable(data, column);
        jt.setAutoCreateRowSorter(true);
        jt.setEnabled(false);

        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // to open dialog box
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (e.getClickCount() == 2) {
                        int row = jt.rowAtPoint(e.getPoint());
                        int col = jt.columnAtPoint(e.getPoint());
                        new HistoryChartAndSymbolSyncronization().setVisible(true);
                    }
                }
                // to open right click menu
                if (SwingUtilities.isRightMouseButton(e)) {

                    int row = jt.rowAtPoint(e.getPoint());
                    int col = jt.columnAtPoint(e.getPoint());
                    // Get selected data
                    Object selectedData = jt.getValueAt(row, col);
//                    TickData t=arraList.get(row);
                    // Show menu with selected data
                    showMenu(e.getX(), e.getY(), selectedData);

                }
            }

            private void showMenu(int x, int y, Object selectedData) {
                System.out.println(selectedData);
//                menu.show(jt, x, y);
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

        setLayout(new BorderLayout());

        JScrollPane sp = new JScrollPane(jt);
        add(sp, BorderLayout.CENTER);

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
