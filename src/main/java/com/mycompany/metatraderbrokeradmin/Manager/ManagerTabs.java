/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin.Manager;

import com.mycompany.metatraderbrokeradmin.SymbolConfig.CommonSymbolPage;
import com.mycompany.metatraderbrokeradmin.SymbolConfig.CurrencySymbolPage;
import com.mycompany.metatraderbrokeradmin.SymbolConfig.ExecutionSymbolPage;
import com.mycompany.metatraderbrokeradmin.SymbolConfig.MarginRatesSymbolPage;
import com.mycompany.metatraderbrokeradmin.SymbolConfig.MarginSymbolPage;
import com.mycompany.metatraderbrokeradmin.SymbolConfig.QuotesSymbolPage;
import com.mycompany.metatraderbrokeradmin.SymbolConfig.SessionsSymbolPage;
import com.mycompany.metatraderbrokeradmin.SymbolConfig.SubmitBtnPanel;
import com.mycompany.metatraderbrokeradmin.SymbolConfig.SwapsSymbolPage;
import com.mycompany.metatraderbrokeradmin.SymbolConfig.TradeSymbolPage;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
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
//        Signup tab1 = new Signup();
        GroupAssignPanel tab2 = new GroupAssignPanel(mid, js);
        PermissionsPanel tab3 = new PermissionsPanel(mid, js1);
        IPAccessList tab4 = new IPAccessList();

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add the tabs to the tabbed pane
//        tabbedPane.addTab("Account", tab1);
        tabbedPane.addTab("Common", tab2);
        tabbedPane.addTab("Permissions", tab3);
        tabbedPane.addTab("IP AccessList", tab4);

//        SubmitBtnPanel submitBtnPanel = new SubmitBtnPanel("Submit");
//        JSplitPane js = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabbedPane, submitBtnPanel);
//        submitDetails(submitBtnPanel, "no symbol id here", tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8, tab9);
        add(tabbedPane);
        setSize(750, 500);
        setLocationRelativeTo(null);
//        setVisible(true);
    }
}
