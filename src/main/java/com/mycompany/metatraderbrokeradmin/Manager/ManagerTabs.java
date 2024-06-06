/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin.Manager;

import javax.swing.JTabbedPane;
import org.json.JSONArray;

/**
 *
 * @author Lenovo
 */
public class ManagerTabs extends javax.swing.JDialog {

    public static ManagerTabs auth;

    public ManagerTabs(String mid, JSONArray js,JSONArray js1) {

        auth = this;
        setModal(true);
        GroupAssignPanel tab2 = new GroupAssignPanel(mid, js);
        PermissionsPanel tab3 = new PermissionsPanel(mid, js1);
        IPAccessList tab4 = new IPAccessList();
        Password tab5 = new Password(mid);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Common", tab2);
        tabbedPane.addTab("Permissions", tab3);
        tabbedPane.addTab("IP AccessList", tab4);
        tabbedPane.addTab("Password", tab5);

        add(tabbedPane);
        setSize(750, 500);
        setLocationRelativeTo(null);
    }
}
