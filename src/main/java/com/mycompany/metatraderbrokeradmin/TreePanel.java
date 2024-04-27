/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin;

import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import com.mycompany.metatraderbrokeradmin.GroupConfig.GroupConfig;
import com.mycompany.metatraderbrokeradmin.SymbolConfig.SixTabFrame;
import com.mycompany.metatraderbrokeradmin.Syncronization.HistoryChartAndSymbolSyncronization;
import com.mycompany.metatraderbrokeradmin.Syncronization.HistorySocket;
import com.mycompany.metatraderbrokeradmin.Utility.GroupModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author techninza
 */
public class TreePanel extends javax.swing.JPanel {

    /**
     * Creates new form TreePanel
     */
    String groupId;
    ArrayList<JSONObject> groupList = new ArrayList();
    ArrayList<JSONObject> symbolList = new ArrayList();
    ArrayList<GroupModel> categoryList = new ArrayList();
    JTree tree;
    JPopupMenu newSymbolPopupMenu = new JPopupMenu();
    JPopupMenu groupPopupMenu = new JPopupMenu();
    JMenuItem addGroupCategory = new JMenuItem("Add Group");
    JMenuItem deleteCategory = new JMenuItem("Delete Group");
    ArrayList<Categories> groupCategoriesList = new ArrayList<Categories>();
    String symbolId;
    String categoryId;
    JSONArray groupCategories;

    public TreePanel() {
        initComponents();
        groupList.clear();
        symbolList.clear();
        groupId = "";
        newSymbolPopupMenu = new JPopupMenu();
        groupPopupMenu = new JPopupMenu();
        addGroupCategory = new JMenuItem("Add Group");
        symbolId = "";
        categoryId = "";
        tree();
    }

    String getData(String url) {
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

    private void tree() {

        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Administrator");

        DefaultMutableTreeNode support = new DefaultMutableTreeNode("Support Center");
        DefaultMutableTreeNode news = new DefaultMutableTreeNode("News");
        DefaultMutableTreeNode articles = new DefaultMutableTreeNode("Articles");
        DefaultMutableTreeNode leads = new DefaultMutableTreeNode("MetaQuotes Leads");
        DefaultMutableTreeNode automation = new DefaultMutableTreeNode("Automation");
        DefaultMutableTreeNode vps = new DefaultMutableTreeNode("VPS");
        DefaultMutableTreeNode platform = new DefaultMutableTreeNode("Platform Documentation");
        DefaultMutableTreeNode api = new DefaultMutableTreeNode("Api Documentation");
        DefaultMutableTreeNode workshop = new DefaultMutableTreeNode("Workshops");
        DefaultMutableTreeNode certification = new DefaultMutableTreeNode("Certification");
        DefaultMutableTreeNode service = new DefaultMutableTreeNode("Service Desk");
        DefaultMutableTreeNode assistant = new DefaultMutableTreeNode("Online Assistant");
        DefaultMutableTreeNode search = new DefaultMutableTreeNode("Search");

        support.add(news);
        support.add(articles);
        support.add(leads);
        support.add(automation);
        support.add(vps);
        support.add(platform);
        support.add(api);
        support.add(workshop);
        support.add(certification);
        support.add(service);
        support.add(news);
        support.add(assistant);
        support.add(search);

        String apiData = getData(APIs.GET_GROUP_CATEGORIES);
        System.out.println("apiData: " + apiData);
        JSONObject gc_JSON = new JSONObject(apiData);
//        gc_JSON.getString("")
        boolean gc_JSON_status = gc_JSON.getBoolean("status");
        if (gc_JSON_status) {
            groupCategories = gc_JSON.getJSONArray("categories");
            System.out.println("cats: " + groupCategories);

        }

        DefaultMutableTreeNode server = new DefaultMutableTreeNode("Servers");
        DefaultMutableTreeNode meta = new DefaultMutableTreeNode("Main Trade Server");

        // Create the child nodes
        DefaultMutableTreeNode network = new DefaultMutableTreeNode("Network Cluster");
        DefaultMutableTreeNode integrations = new DefaultMutableTreeNode("Integrations");
        DefaultMutableTreeNode automations = new DefaultMutableTreeNode("Automations");
        DefaultMutableTreeNode security = new DefaultMutableTreeNode("Security");
        DefaultMutableTreeNode time = new DefaultMutableTreeNode("Time");
        DefaultMutableTreeNode holidays = new DefaultMutableTreeNode("Holidays");
        DefaultMutableTreeNode groups = new DefaultMutableTreeNode("Groups");
        DefaultMutableTreeNode clients = new DefaultMutableTreeNode("Clients & Accounts");
        DefaultMutableTreeNode orders = new DefaultMutableTreeNode("Orders & Deals");
        DefaultMutableTreeNode gateway = new DefaultMutableTreeNode("Gateways");
        DefaultMutableTreeNode datafeeds = new DefaultMutableTreeNode("DataFeeds");
        DefaultMutableTreeNode plugins = new DefaultMutableTreeNode("Plugins");
        DefaultMutableTreeNode reports = new DefaultMutableTreeNode("Reports");
        DefaultMutableTreeNode ecn = new DefaultMutableTreeNode("ECN");
        DefaultMutableTreeNode routing = new DefaultMutableTreeNode("Routing");
        DefaultMutableTreeNode funds = new DefaultMutableTreeNode("Funds & ETF");
        DefaultMutableTreeNode symbols = new DefaultMutableTreeNode("Symbols");
        DefaultMutableTreeNode spreads = new DefaultMutableTreeNode("Spreads");
        DefaultMutableTreeNode chart = new DefaultMutableTreeNode("Chart & Ticks");

        DefaultMutableTreeNode historychart = new DefaultMutableTreeNode("1 Min History Charts");
        DefaultMutableTreeNode bidlasttick = new DefaultMutableTreeNode("Bid/Ask/Last Ticks");
        DefaultMutableTreeNode synchronization = new DefaultMutableTreeNode("Synchornization");
        DefaultMutableTreeNode CSV = new DefaultMutableTreeNode("CSV");

        chart.add(historychart);
        chart.add(bidlasttick);
        chart.add(synchronization);
        chart.add(CSV);

        DefaultMutableTreeNode subscriptions = new DefaultMutableTreeNode("Subscriptions");

        // DefaultMutableTreeNode allocations = new DefaultMutableTreeNode("Allocations");
        DefaultMutableTreeNode client = new DefaultMutableTreeNode("Client");
        DefaultMutableTreeNode manager = new DefaultMutableTreeNode("Manager");
        DefaultMutableTreeNode tradingacc = new DefaultMutableTreeNode("Trading Account");

        //  clients.add(allocations);
        clients.add(client);
        clients.add(manager);
        clients.add(tradingacc);

        DefaultMutableTreeNode position = new DefaultMutableTreeNode("Position");
        DefaultMutableTreeNode order = new DefaultMutableTreeNode("Order");
        DefaultMutableTreeNode deals = new DefaultMutableTreeNode("Deals");

        orders.add(position);
        orders.add(order);
        orders.add(deals);
        // Add the child nodes to the root node
        meta.add(network);
        meta.add(integrations);
        meta.add(automations);
        meta.add(security);
        meta.add(time);
        meta.add(holidays);
        meta.add(groups);
        meta.add(clients);
        meta.add(orders);
        meta.add(gateway);
        meta.add(datafeeds);
        meta.add(plugins);
        meta.add(reports);
        meta.add(ecn);
        meta.add(routing);
        meta.add(funds);
        meta.add(symbols);
        meta.add(spreads);
        meta.add(chart);
        meta.add(subscriptions);

        server.add(meta);
        top.add(server);
        top.add(support);

        DefaultMutableTreeNode newGroup = new DefaultMutableTreeNode("New Group");
        DefaultMutableTreeNode newGroupCategory = new DefaultMutableTreeNode("New Group Category");

        groups.add(newGroup);
        groups.add(newGroupCategory);
        DefaultMutableTreeNode newSymbol = new DefaultMutableTreeNode("New Symbol Group");

        DefaultMutableTreeNode bulkUpload = new DefaultMutableTreeNode("Upload Symbols");
        symbols.add(newSymbol);
        symbols.add(bulkUpload);
        tree = new JTree(top);
        tree.setCellRenderer(new MyCustomRenderer());

        GroupPanel groupPanel = new GroupPanel();
        JMenuItem edit_menuItem = new JMenuItem("Add Symbol");

        newSymbolPopupMenu.add(edit_menuItem);

        groupPopupMenu.add(addGroupCategory);

        TreeWillExpandListener treeWillExpandListener;
        treeWillExpandListener = new TreeWillExpandListener() {
            @Override
            public void treeWillCollapse(TreeExpansionEvent treeExpansionEvent) throws ExpandVetoException {
                //Get the path of the node that will collapse
                TreePath path = treeExpansionEvent.getPath();

                //Get the node object from the path
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();

                //Print the name of the node
                String data = node.getUserObject().toString();
                System.out.println("WillCollapse: " + data);
            }

            @Override
            public void treeWillExpand(TreeExpansionEvent treeExpansionEvent) throws ExpandVetoException {
                //Get the path of the node that will expand
                TreePath path = treeExpansionEvent.getPath();

                //Get the node object from the path
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();

                //Print the name of the node
                String data = node.getUserObject().toString();

                long lo = System.currentTimeMillis();
                if ("Groups".equals(data)) {
                    String apiUrl = APIs.GET_CATEGORY_GROUPS;
                    getData(apiUrl);
                    String responseData = getData(apiUrl);
                    System.out.println(responseData);
                    JSONObject json = new JSONObject(responseData);
//                    JSONArray js = json.getJSONArray("message");
                    JSONObject jso = json.getJSONObject("p_gs");
                    System.out.println("p_gs: " + jso);
                    var map = jso.keys();
                    while (map.hasNext()) {
                        var key = map.next().toString();
                        DefaultMutableTreeNode cat = new DefaultMutableTreeNode(key);
                        groups.add(cat);
                        tree.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (e.getButton() == MouseEvent.BUTTON3) {
                                    TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                                    // System.out.println(path.getLastPathComponent());
                                    groupPopupMenu.show(tree, getX(), e.getY());
                                    for (int i = 0; i < groupCategories.length(); i++) {
                                        JSONObject gc = groupCategories.getJSONObject(i);
                                        String title = gc.getString("title");
                                        boolean isEqual = title.equalsIgnoreCase(path.getLastPathComponent().toString());
                                        if (isEqual) {
                                            String catId = gc.getString("_id");
                                            groupId = catId;
                                            System.out.println("catID: " + catId);
                                            break;
                                        }
                                    }
//                                        if (path != null && path.getLastPathComponent() instanceof DefaultMutableTreeNode) {
//                                            DefaultMutableTreeNode clickedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
//                                            if (clickedNode.getUserObject().equals(grpName)) {
//                                                System.out.println("Selected node: " + grpName);
//                                                addGroupCategory.addActionListener((popupEvent) -> {
//                                                    // System.out.println("called");
//                                                    createGroupCategoryPopup(groupId);
//                                                });
//                                                groupPopupMenu.show(tree, getX(), e.getY());
//
//                                            }
//                                        }
                                }
                            }
                        });
                        JSONArray grps = jso.getJSONArray(key);
                        for (int i = 0; i < grps.length(); i++) {
                            JSONObject grp = grps.getJSONObject(i);
                            String grpName = grp.getString("Name");
                            String groupId = grp.getString("_id");
                            System.out.println("grpName: " + grpName);
                            System.out.println("grpId: " + groupId);
                            DefaultMutableTreeNode group = new DefaultMutableTreeNode(grpName);
                            groupList.add(grp);
                            cat.add(group);

                        }
                    }
                } else if ("Symbols".equals(data)) {
                    String apiUrl = APIs.GET_SYMBOL_CATEGORY + "?timestamp=" + lo;
                    String responseData = getData(apiUrl);
                    System.out.println("Response" + responseData);
                    JSONObject json = new JSONObject(responseData);
                    JSONArray js = json.getJSONArray("message");

                    for (int i = 0; i < js.length(); i++) {
                        System.out.println("working....");
                        JSONObject jsonobject = js.getJSONObject(i);
                        String symbol = jsonobject.getString("name");
                        String symbolId = jsonobject.getString("_id");
                        categoryList.add(new GroupModel(symbolId, symbol));
                        JSONArray symbolarray = jsonobject.getJSONArray("symbols");

                        DefaultMutableTreeNode group = new DefaultMutableTreeNode(symbol);

                        if (symbolarray.length() > 0) {
                            for (int j = 0; j < symbolarray.length(); j++) {
                                JSONObject symbolData = symbolarray.getJSONObject(j);
                                symbolList.add(symbolData);
                                final String symbolName = symbolData.getString("Symbol");
                                DefaultMutableTreeNode symbolNode = new DefaultMutableTreeNode(symbolName);
                                group.add(symbolNode);
                            }
                        }
                        symbols.add(group);
//                        recursionsymbol(jsonobject, group);
                    }
                }
                System.out.println("Expand: " + data);
            }
        };
        tree.addTreeWillExpandListener(treeWillExpandListener);
        ArrayList<String> symbolsNameList;
        tree.addTreeSelectionListener((TreeSelectionEvent e) -> {
            TreePath selectedPath = e.getNewLeadSelectionPath();
            if (selectedPath != null) {
                String selectedNode = selectedPath.getLastPathComponent().toString();
                System.out.println("Selected node: " + selectedNode);
                if (selectedNode.equalsIgnoreCase("groups")) {
                    BrokerAdmin.splitPane.setRightComponent(new GroupPanel());
                } else if (selectedNode.equalsIgnoreCase("symbols")) {
                    BrokerAdmin.splitPane.setRightComponent(new SymbolPanel());
                } else if (selectedNode.equalsIgnoreCase("security")) {
                    BrokerAdmin.splitPane.setRightComponent(new SymbolPanel());
                } else if (selectedNode.equalsIgnoreCase("Chart & Ticks")) {
                    BrokerAdmin.splitPane.setRightComponent(new ChartInfoPanel());
                } else if (selectedNode.equalsIgnoreCase("spreads")) {
                    BrokerAdmin.splitPane.setRightComponent(new SpreadsPanel());
                } else if (selectedNode.equalsIgnoreCase("position")) {
                    BrokerAdmin.splitPane.setRightComponent(new PositionPanel());
                } else if (selectedNode.equalsIgnoreCase("order")) {
                    BrokerAdmin.splitPane.setRightComponent(new OrderPanel());
                } else if (selectedNode.equalsIgnoreCase("deals")) {
                    BrokerAdmin.splitPane.setRightComponent(new DealPanel());
                } else if (selectedNode.equalsIgnoreCase("client")) {
                    BrokerAdmin.splitPane.setRightComponent(new ClientPanel());
                } else if (selectedNode.equalsIgnoreCase("manager")) {
                    BrokerAdmin.splitPane.setRightComponent(new ManagerPanel());
                } else if (selectedNode.equalsIgnoreCase("time")) {
                    BrokerAdmin.splitPane.setRightComponent(new TimePanel());
                } else if (selectedNode.equalsIgnoreCase("Bid/Ask/Last Ticks")) {
                    BrokerAdmin.splitPane.setRightComponent(new BidAskLastPanel());
                } else if (selectedNode.equalsIgnoreCase("1 Min History Charts")) {
                    BrokerAdmin.splitPane.setRightComponent(new ChartPanel());
                } else if (selectedNode.equalsIgnoreCase("Synchornization")) {
                    new HistorySocket().setVisible(true);
                } else if (selectedNode.equalsIgnoreCase("CSV")) {
                    new HistoryChartAndSymbolSyncronization().setVisible(true);
                } else if (selectedNode.equalsIgnoreCase("Orders & Deals")) {
                    BrokerAdmin.splitPane.setRightComponent(new orderAndDealInfo());
                } else if (selectedNode.equalsIgnoreCase("Trading Account")) {
                    BrokerAdmin.splitPane.setRightComponent(new TradingAccountTable());
                } else if (selectedNode.equalsIgnoreCase("Allocations")) {
                    BrokerAdmin.splitPane.setRightComponent(new AllocationTable());
                } else if (selectedNode.equalsIgnoreCase("New Group")) {
                    new GroupConfig();
                } else if (selectedNode.equalsIgnoreCase("New Group Category")) {
                    String input_cname = JOptionPane.showInputDialog("Enter category Name.");
                    System.out.println("input_cname: " + input_cname);
                    JSONObject payload = new JSONObject();
                    payload.put("title", input_cname);
                    String cc_cat_api = APIs.CREATE_GROUP_CATEGORY;
                    OkHttpClient postclient = new OkHttpClient();
                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    RequestBody reqBody = RequestBody.create(JSON, payload.toString());
                    Request req = new Request.Builder().url(cc_cat_api).post(reqBody).build();

                    try {
                        Call newCall = postclient.newCall(req);
                        Response res = newCall.execute();
                        if (res.isSuccessful()) {
                            String body = res.body().string();
                            System.out.println("body: " + body);

                        }
                    } catch (IOException ex) {
                        System.out.println("creating group category: " + ex.getMessage());
                    }

                } else if (selectedNode.equalsIgnoreCase("DataFeeds")) {
                    BrokerAdmin.splitPane.setRightComponent(new AllocationTable());
                } else if (selectedNode.equalsIgnoreCase("Network Cluster")) {
//                    BrokerAdmin.splitPane.setRightComponent(new ServerSettings());
                    new ServerSettings().setVisible(true);
                } else if (selectedNode.equalsIgnoreCase("New Symbol Group")) {
                    createSymbolCategoryPopup(manager, "ROOT");
                } else if (selectedNode.equalsIgnoreCase("Upload Symbols")) {
                    new SymbolBulkUpload().setVisible(true);
                } else {
                    boolean isFound = false;
                    for (int i = 0; i < groupList.size(); i++) {
                        if (selectedNode.equalsIgnoreCase(groupList.get(i).getString("Name"))) {
                            String groupId = groupList.get(i).getString("_id");
                            new GroupConfig(groupId);
                            isFound = true;
                            break;
                        }
                    }
                    System.out.println(symbolList);
                    for (int i = 0; i < symbolList.size(); i++) {
                        String symName = symbolList.get(i).getString("Symbol");
                        if (selectedNode.equalsIgnoreCase(symName)) {
                            String symbolId = symbolList.get(i).getString("_id");
                            System.out.println("tapped");
                            try {
                                new SixTabFrame("old", symbolId).setVisible(true);
                            } catch (Exception ex) {
                                Logger.getLogger(TreePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            isFound = true;
                            break;
                        }
                    }
                    if (!isFound) {
                        BrokerAdmin.splitPane.setRightComponent(new StartPage());
                    }
                }

            }
        });
        // tree listner for group node
//        tree.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getButton() == MouseEvent.BUTTON3) {
//
//                    TreePath path = tree.getPathForLocation(e.getX(), e.getY());
//                    if (path != null && path.getLastPathComponent() instanceof DefaultMutableTreeNode) {
//                        DefaultMutableTreeNode clickedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
//                        for (int i = 0; i < groupList.size(); i++) {
//                            JSONObject jsoo = groupList.get(i);
//                            if (clickedNode.getUserObject().equals(jsoo.getString("Name"))) {
//                                System.out.println("Selected node: " + jsoo.getString("Name"));
//                                groupId = jsoo.getString("_id");
//
//                                //saddGroupCategory.removeActionListener(action);
//                                groupPopupMenu.show(tree, getX(), e.getY());
//                                break;
//                            }
//                        }
//
//                    }
//                }
//            }
//        });
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent popupEvent) {
                createGroupCategoryPopup(groupId);
            }
        };
        addGroupCategory.addActionListener(action);

        // tree listener for symbol node
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                    if (path != null && path.getLastPathComponent() instanceof DefaultMutableTreeNode) {
                        DefaultMutableTreeNode clickedNode = (DefaultMutableTreeNode) path.getLastPathComponent();

                        for (int i = 0; i < categoryList.size(); i++) {
                            categoryId = categoryList.get(i).getId();
                            String name = categoryList.get(i).getName();
                            System.out.println("name: " + name);
                            if (clickedNode.getUserObject().equals(name)) {
                                System.out.println("Selected node: " + name);
                                newSymbolPopupMenu.show(tree, getX(), e.getY());
                                break;
                            }
                        }
                    }
                }
            }
        });
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(tree);

        add(scrollPane, BorderLayout.CENTER);
//        add_menuItem.addActionListener(
//                (popupEvent) -> {
//                    System.out.println("called");
//                    createSymbolCategoryPopup(manager, categoryId);
//                }
//        );
        edit_menuItem.addActionListener(
                (popupEvent) -> {
                    new SixTabFrame(categoryId).setVisible(true);
                }
        );
    }

    /*
    private void recursiongroup(JSONObject jsonobject, DefaultMutableTreeNode groups) {
        JSONArray jxa = jsonobject.getJSONArray("nestedSymbols");
        if (jxa.length() != 0) {
            for (int i = 0; i < jxa.length(); i++) {
                JSONObject jsoa = jxa.getJSONObject(i);
                groupList.add(jsoa);
                String groupId = jsoa.getString("_id");
                String groupName = jsoa.getString("Name");
                DefaultMutableTreeNode group = new DefaultMutableTreeNode(groupName);
//                        groupList.add(jsonobject);
                groups.add(group);

                if (jsoa.getJSONArray("nestedSymbols").length() != 0) {
                    recursiongroup(jsoa, group);
                }
            }
        }
    }

    private void recursionsymbol(JSONObject jsonobject, DefaultMutableTreeNode groups) {
        JSONArray jxa = jsonobject.getJSONArray("nestedCategories");
        // System.out.println("symbol node: " + groups);
        if (jxa.length() != 0) {
            for (int i = 0; i < jxa.length(); i++) {
                JSONObject jsoa = jxa.getJSONObject(i);
                String symbolName = jsoa.getString("name");
                DefaultMutableTreeNode symbol = new DefaultMutableTreeNode(symbolName);

                groups.add(symbol);
                System.out.println("symbol added successfully");
                tree.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                            if (path != null && path.getLastPathComponent() instanceof DefaultMutableTreeNode) {
                                DefaultMutableTreeNode clickedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                                if (clickedNode.getUserObject().equals(symbol)) {
                                    System.out.println("Selected node: " + symbol);
                                    newSymbolPopupMenu.show(tree, getX(), e.getY());

                                }
                            }
                        }
                    }
                });

//                if (jsoa.getJSONArray("nestedCategories").length() != 0) {
//                    recursionsymbol(jsoa, symbol);
//                }
            }
        }
    }
     */
    //    private void createSymbolCategoryPopup(DefaultMutableTreeNode manager, String subcat, DefaultMutableTreeNode currentNode) {
    private void createSymbolCategoryPopup(DefaultMutableTreeNode manager, String subcat) {
        String categoryName = JOptionPane.showInputDialog(this, "Symbol Category");

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject jsnObj = new JSONObject();
        jsnObj.put("name", categoryName);
        jsnObj.put("subcat", subcat);
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, jsnObj.toString());
        Request request = new Request.Builder().url(APIs.ADD_SYMBOL_CAT).method("POST", body).build();

        System.out.println(body);
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("symbol created successfully : " + response.body().string());
            } else {
                System.out.println("failed to create symbol category: " + response.body().string());
            }
        } catch (IOException iox) {
            System.out.println("Exception occurred while creating category: " + iox.getMessage());
        }
        // DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(categoryName);
    }

    private void createGroupCategoryPopup(String HCategory) {
        new GroupConfig(0, HCategory);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

class MyCustomRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, hasFocus);

        setText(((DefaultMutableTreeNode) value).getUserObject().toString()); // Access node data

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) { // Check for right-click
                    TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                    if (path != null && path.getLastPathComponent() instanceof DefaultMutableTreeNode) {
                        DefaultMutableTreeNode clickedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                        // Handle right-click event on the clicked node (if it's the expected one)
                        if (clickedNode.getUserObject().equals("Specific Node Name")) {
                            System.out.println("Right-clicked on node: Specific Node Name");
                        }
                    }
                }
            }
        });

        return this;
    }
}

class Categories {

    String id;
    String name;

    public Categories(String id, String name) {
        this.id = id;
        this.name = name;
    }

    String getId() {
        return this.id;
    }

    String getName() {
        return this.name;
    }

}
