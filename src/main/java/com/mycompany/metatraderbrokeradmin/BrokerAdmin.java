/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin;

import com.mycompany.metatraderbrokeradmin.Utility.Config;
import com.mycompany.managerapp.AccountDetails.newAccount;
import com.mycompany.metacustomer.Auth.AuthContainer;
import com.mycompany.metacustomer.Auth.HandlePreference;
import io.socket.client.IO;
import io.socket.client.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import org.json.JSONObject;

/**
 *
 * @author techninza
 */
public class BrokerAdmin extends JFrame {

    public static String loginToken;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration                   
    public static String APPNAME = "RapidTrader";
    public static JSplitPane splitPane;
    public static String adminId = "";

    public BrokerAdmin() {

        HandlePreference savedLoginCredentials = new HandlePreference();
//        savedLoginCredentials.logout()
        String token = savedLoginCredentials.retrieveToken();
        Config.BASE_URL = savedLoginCredentials.retrieveBaseurl();
        System.out.println("token: " + token);
        JSONObject tokenJSON = new JSONObject(token);
        JSONObject jsn = tokenJSON.getJSONObject("admin");
        this.adminId = jsn.getString("_id");
        System.out.println("adminId: " + adminId);
        if ("not found".equals(token) || "not found".equals(Config.BASE_URL)) {
            dispose();
            new AuthContainer(0).setVisible(true);
        } else {
            setTitle("Broker Admin");
            setState(MAXIMIZED_BOTH);
            menubarsetup();
            toolbar();
            menusetup();
            framesetup();
        }

    }

    private void toolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setOrientation(JToolBar.HORIZONTAL);

        JButton disconnectBtn = new JButton("Disconnect");

        ImageIcon goBack = new ImageIcon("assets/left.png");
        JButton backBtn = new JButton(goBack);

        ImageIcon forward = new ImageIcon("assets/right.png");
        JButton forwardBtn = new JButton(forward);
        forwardBtn.setEnabled(false);

        JButton refreshBtn = new JButton("Refresh");
        JButton restartBtn = new JButton("Restart Server");
        restartBtn.setEnabled(false);

        JButton exportBtn = new JButton("Export");
        JButton importBtn = new JButton("Import");
        JButton applyBtn = new JButton("Apply");

        ImageIcon plus = new ImageIcon("assets/plus.png");
        ImageIcon setting = new ImageIcon("assets/setting.png");
        ImageIcon cross = new ImageIcon("assets/cross.png");
        ImageIcon sort = new ImageIcon("assets/sort.png");
        ImageIcon up = new ImageIcon("assets/up.png");
        ImageIcon down = new ImageIcon("assets/down.png");
        ImageIcon play = new ImageIcon("assets/play.png");
        ImageIcon pause = new ImageIcon("assets/stop.png");

        JButton plusBtn = new JButton(plus);
        JButton settingBtn = new JButton(setting);
        JButton crossBtn = new JButton(cross);
        JButton sortBtn = new JButton(sort);
        JButton upBtn = new JButton(up);
        JButton downBtn = new JButton(down);
        JButton playBtn = new JButton(play);
        JButton pauseBtn = new JButton(pause);

        plusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new newAccount().setVisible(true);
            }

        });

        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BrokerAdmin.splitPane.setLeftComponent(new TreePanel());
            }

        });

        disconnectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                HandlePreference handle = new HandlePreference();
                handle.logout();
                System.exit(0);
            }
        });
        toolBar.add(disconnectBtn);
        toolBar.addSeparator();
        toolBar.add(backBtn);
        toolBar.add(forwardBtn);
        toolBar.addSeparator();
        toolBar.add(refreshBtn);
        toolBar.add(restartBtn);
        toolBar.addSeparator();
        applyBtn.setEnabled(false);

        toolBar.add(exportBtn);
        toolBar.add(importBtn);
        toolBar.addSeparator();
        toolBar.add(applyBtn);
        toolBar.addSeparator();
        toolBar.add(plusBtn);
        toolBar.add(settingBtn);
        toolBar.add(crossBtn);
        toolBar.add(sortBtn);
        toolBar.add(upBtn);
        toolBar.add(downBtn);
        toolBar.add(playBtn);
        toolBar.add(pauseBtn);

        add(toolBar, BorderLayout.NORTH);
    }

    private void menubarsetup() {
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

//        jMenu2.setText("Edit");
//        jMenuBar1.add(jMenu2);
//        jMenu3.setText("View");
//        jMenuBar1.add(jMenu3);
        jMenu4.setText("Services");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Help");
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

    }

    private void framesetup() {
        TreePanel leftPanel = new TreePanel();
//        leftPanel.setBackground(Color.RED);

        HomePage rightPanel = new HomePage();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
//        splitPane.setDividerLocation(400);
        splitPane.setDividerLocation(300);
        JSplitPane jSplitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPane, new BottomPanel());
        jSplitPane1.setDividerLocation(0.3);

//         BorderLayout layout = new BorderLayout();
//        layout.setHgap(10);
//        layout.setVgap(10);
        add(jSplitPane1);
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

        a1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AuthContainer(0).setVisible(true);
            }

        });
    }

    public static void main(String args[]) {
        HandlePreference preference = new HandlePreference();
//        preference.logout();
        new BrokerAdmin().setVisible(true);
    }
}
