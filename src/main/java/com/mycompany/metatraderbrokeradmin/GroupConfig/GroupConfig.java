package com.mycompany.metatraderbrokeradmin.GroupConfig;

import com.mycompany.managerapp.AccountDetails.newAccount;
import com.mycompany.metatraderbrokeradmin.BrokerAdmin;
import com.mycompany.metatraderbrokeradmin.GroupPanel;
import com.mycompany.metatraderbrokeradmin.SymbolConfig.SixTabFrame;
import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;

public class GroupConfig extends JDialog {

    public static ArrayList<String> group_data = new ArrayList();

    public void postData(String groupId, CommonGroupPage tab1, CompanyGroupPage tab2, NewsEmail tab3, PermissionsGroupConfig tab4, MarginGroupConfig tab5, SymbolsGroupConfig tab6, CommissionsGroupConfig tab7, ReportGroupConfig tab8) throws JSONException, IOException {
        String apiUrl = com.mycompany.metatraderbrokeradmin.Utility.APIs.CREATE_GROUP;
        if (!"createGroup".equals(groupId)) {
            apiUrl = com.mycompany.metatraderbrokeradmin.Utility.APIs.UPDATE_GROUP;
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // Common Group page values
        String name = tab1.getjTextField1().getText();
        String pushNotification = tab1.getjComboBox1().getSelectedItem().toString();
        String tradeServer = tab1.getjComboBox2().getSelectedItem().toString();
        String auth = tab1.getjComboBox3().getSelectedItem().toString();
        String otp = tab1.getjComboBox4().getSelectedItem().toString();
        String minPasswordLength = tab1.getjTextField5().getText();
        String currency = tab1.getjComboBox5().getSelectedItem().toString();
        String digits = tab1.getjComboBox6().getSelectedItem().toString();
        String enableConntectionFlag = "" + tab1.getjCheckBox1().isSelected();
        String enablecertiFlag = "" + tab1.getjCheckBox2().isSelected();
        String changePswdFlag = "" + tab1.getjCheckBox3().isSelected();
        String showRiskFlag = "" + tab1.getjCheckBox4().isSelected();
        String enforceCountryFlag = "" + tab1.getjCheckBox5().isSelected();

        // Company Group tab value
        String company = tab2.getjTextField1().getText();
        String compSite = tab2.getjTextField2().getText();
        String compEmail = tab2.getjTextField3().getText();
        String depositSite = tab2.getjTextField4().getText();
        String withdrawlSite = tab2.getjTextField6().getText();
        String supportSite = tab2.getjTextField7().getText();
        String supportEmail = tab2.getjTextField8().getText();
        String templateFolder = tab2.getjTextField5().getText();

        // news and mail tab value
        String news = tab3.getjComboBox1().getSelectedItem().toString();
        String newsCat = tab3.getjComboBox2().getSelectedItem().toString();
        String newsLang = tab3.getjComboBox3().getSelectedItem().toString();
        String enableEMail = "" + tab3.getjCheckBox1().isSelected();

        // PermissionsGroupConfig
        String maxSymbol = tab4.getjComboBox1().getSelectedItem().toString();
        String maxPosition = tab4.getjComboBox2().getSelectedItem().toString();
        String availHistory = tab4.getjComboBox3().getSelectedItem().toString();
        String maxOrder = tab4.getjComboBox4().getSelectedItem().toString();
        String defaultLevrage = tab4.getjComboBox5().getSelectedItem().toString().split(":")[1].trim();
        System.out.println("default leverage: " + defaultLevrage);
        String tradingSignal = tab4.getjComboBox6().getSelectedItem().toString();
        String fundTransfer = tab4.getjComboBox7().getSelectedItem().toString();
        String defaultDeposit = tab4.getjTextField1().getText();
        String AnnualInterestRate = tab4.getjTextField2().getText();
        String enableTrading = tab4.getjCheckBox1().isSelected() + "";
        String enablePosition = tab4.getjCheckBox2().isSelected() + "";
        String enableTrailing = tab4.getjCheckBox4().isSelected() + "";
        String prohibitHedgePosition = tab4.getjCheckBox5().isSelected() + "";
        String enableSwapChange = tab4.getjCheckBox6().isSelected() + "";

        // marginGroupConfig
        String riskMgmt = tab5.getjComboBox1().getSelectedItem().toString();
        String in = tab5.getjComboBox2().getSelectedItem().toString();
        String unrealizedProfit = tab5.getjComboBox3().getSelectedItem().toString();
        String delayProfit = tab5.getjComboBox4().getSelectedItem().toString();
        String marginCallLevel = tab5.getjTextField1().getText();
        String stopOutFlag = tab5.getjCheckBox1().isSelected() + "";
        String compensateNegativeBalance = tab5.getjCheckBox2().isSelected() + "";
        String withdrawCredit = tab5.getjCheckBox3().isSelected() + "";
        String ReleaseFixedProfit = tab5.getjCheckBox4().isSelected() + "";
        String stopOutLevel = tab5.getjTextField2().getText();

        // reportGropConfig
        String genDailyData = tab8.getjCheckBox1().isSelected() + "";
        String genDailyState = tab8.getjCheckBox2().isSelected() + "";
        String sendDailyState = tab8.getjCheckBox3().isSelected() + "";
        String sendCopy = tab8.getjCheckBox4().isSelected() + "";
        String mailServer = tab8.getjComboBox1().getSelectedItem().toString();

        // symbols group config
        JTable symbolTable = tab6.getjTable1();
        int rowCount = symbolTable.getRowCount();
        int colCount = symbolTable.getColumnCount();
        String[] columnNames = new String[colCount];
        Object[][] data = new Object[rowCount][colCount];

        for (int i = 0; i < colCount; i++) {
            columnNames[i] = symbolTable.getColumnName(i);
        }

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i][j] = symbolTable.getValueAt(i, j);
            }
        }
        /*
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            Map<String, Object> map = new HashMap<>();
            for (int j = 0; j < colCount; j++) {
                map.put(columnNames[j], data[i][j]);
            }
            dataList.add(map);
        }
        
        System.out.println(dataList.toString() + "<<<--- datalist");
         */
//        return;/

        // commsiion group config
//        JTable commissionTable = tab7.getjTable1();
//        DefaultTableModel model = CommissionsGroupConfig.tablemodel;
//        int comrowCount = model.getRowCount();
//        int comcolCount = model.getColumnCount();
//        String[] comcolumnNames = new String[comcolCount];
//        Object[][] comdata = new Object[comrowCount][comcolCount];
//
//        for (int i = 0; i < comcolCount; i++) {
//            comcolumnNames[i] = model.getColumnName(i);
//        }
//        System.out.println("rowCount: " + comrowCount);
//        System.out.println("comcolcount: " + comcolCount);
//        for (int i = 0; i < comrowCount; i++) {
//            for (int j = 0; j < comcolCount; j++) {
//                comdata[i][j] = model.getValueAt(i, j);
//            }
//        }
//       
//        List<Map<String, Object>> comdataList = new ArrayList<>();
//        for (int i = 0; i < comrowCount; i++) {
//            Map<String, Object> map = new HashMap<>();
//            for (int j = 0; j < comcolCount; j++) {
//                map.put(comcolumnNames[j], comdata[i][j]);
//            }
//            comdataList.add(map);
//        }
//        ArrayList<JSONObject> symbolTableData = SymbolsGroupConfig.symbolsTableData;
        JSONArray symbolTableData = SymbolsGroupConfig.symbolsTableData;
        var modelData = CommissionsGroupConfig.modelData;
        String[] array = new String[modelData.size()];
        for (int i = 0; i < modelData.size(); i++) {
            array[i] = Arrays.toString(modelData.get(i));
        }
        String result = Arrays.toString(array);
        System.out.println("result: " + result);
        String commissionString = Arrays.toString(array);
        // Creating json object from variable
        JSONObject jsnObj = new JSONObject();
        jsnObj.put("Name", name);
        jsnObj.put("TradeServer", tradeServer);
        jsnObj.put("Authentication", auth);
        jsnObj.put("Otp", otp);
        jsnObj.put("PushNotification", pushNotification);
        jsnObj.put("Currency", currency);
        jsnObj.put("Digits", digits);
        jsnObj.put("MiniPasswordLength", minPasswordLength);
        jsnObj.put("EnableConnectionFlag", enableConntectionFlag);
        jsnObj.put("EnableCertificateFlag", enablecertiFlag);
        jsnObj.put("ChangePasswordFlag", changePswdFlag);
        jsnObj.put("ShowRiskFlag", showRiskFlag);
        jsnObj.put("EnforceCountryFlag", enforceCountryFlag);
        jsnObj.put("Company", company);
        jsnObj.put("CompanySite", compSite);
        jsnObj.put("CompanyEmail", compEmail);
        jsnObj.put("DepositSite", depositSite);
        jsnObj.put("WithdrawalSite", withdrawlSite);
        jsnObj.put("SupportSite", supportSite);
        jsnObj.put("SupportEmail", supportEmail);
        jsnObj.put("TemplateFolder", templateFolder);

        jsnObj.put("News", news);
        jsnObj.put("NewsCat", newsCat);
        jsnObj.put("NewsLang", newsLang);
        jsnObj.put("EnableInternalMailFlag", enableEMail);
        jsnObj.put("MaximumSymbol", maxSymbol);
        jsnObj.put("MaxPosition", maxPosition);
        jsnObj.put("AvailableHistory", availHistory);
        jsnObj.put("MaxOrders", maxOrder);
        jsnObj.put("LeverageByDefault", defaultLevrage);
        jsnObj.put("TradingSignals", tradingSignal);
        jsnObj.put("TransferOfFunds", fundTransfer);
        jsnObj.put("DepositByDefault", defaultDeposit);
        jsnObj.put("AnnualInterestRate", AnnualInterestRate);
        jsnObj.put("EnableAdviserTradingFlag", enableTrading);
        jsnObj.put("EnablePositionFlag", enablePosition);
        jsnObj.put("EnableTrailingStopsFlag", enableTrailing);
        jsnObj.put("HedgePositionFlag", prohibitHedgePosition);
        jsnObj.put("EnableChargeSwapsFlag", enableSwapChange);
        jsnObj.put("RiskMgmt", riskMgmt);
        jsnObj.put("StopOutLevelIn", in);
        jsnObj.put("StopOutLevel", stopOutLevel);
        jsnObj.put("UnrealizedProfit", unrealizedProfit);

        jsnObj.put("DelayProfit", delayProfit);
        jsnObj.put("MarginCallLevel", marginCallLevel);
        jsnObj.put("WithdrawCreditFlag", withdrawCredit);
        jsnObj.put("StopOutFullyFlag", stopOutFlag);
        jsnObj.put("CompensateNegativeBalanceFlag", compensateNegativeBalance);
        jsnObj.put("ReleaseFixedProfitFlag", ReleaseFixedProfit);
        jsnObj.put("GenDailyDataFlag", genDailyData);
        jsnObj.put("GenDailyStatementFlag", genDailyState);
        jsnObj.put("SendDailyStatementFlag", sendDailyState);
        jsnObj.put("SendCopyFlag", sendCopy);
        jsnObj.put("MailServer", mailServer);
        jsnObj.put("SymbolTable", new JSONArray(symbolTableData));
        jsnObj.put("SymbolSetting", new JSONArray(SymbolsGroupConfig.symbolTableData2));
        if ("createGroup".equals(groupId)) {
            jsnObj.put("HCategory", "ROOT");
        }
//        jsnObj.put("ComissionTable", commissionString);
        jsnObj.put("ComissionTable", CommissionsGroupConfig.jarray);
        if (!"createGroup".equals(groupId)) {
            jsnObj.put("groupId", groupId);
        }

        System.out.println("gorup json : " + jsnObj);
        RequestBody body = RequestBody.create(JSON, jsnObj.toString());

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        System.out.println(body);
        Response response = client.newCall(request).execute();

        // Check for successful response
        if (response.isSuccessful()) {
            // Get response body as strings
            String responseBody = response.body().string();
            BrokerAdmin.splitPane.setRightComponent(new GroupPanel());
            System.out.println("_____________________*****************_________________");
            System.out.println(responseBody);
            System.out.println("_____________________*****************_________________");
        } else {
            System.out.println("Error: " + response.body().toString());
        }

    }

    final public void postData(String groupId, CommonGroupPage tab1, CompanyGroupPage tab2, NewsEmail tab3, PermissionsGroupConfig tab4, MarginGroupConfig tab5, SymbolsGroupConfig tab6, CommissionsGroupConfig tab7, ReportGroupConfig tab8, String HCategory) throws JSONException, IOException {
        String apiUrl = com.mycompany.metatraderbrokeradmin.Utility.APIs.CREATE_GROUP;
        if (!"createGroup".equals(groupId)) {
            apiUrl = com.mycompany.metatraderbrokeradmin.Utility.APIs.UPDATE_GROUP;
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // Common Group page values
        String name = tab1.getjTextField1().getText();
        String pushNotification = tab1.getjComboBox1().getSelectedItem().toString();
        String tradeServer = tab1.getjComboBox2().getSelectedItem().toString();
        String auth = tab1.getjComboBox3().getSelectedItem().toString();
        String otp = tab1.getjComboBox4().getSelectedItem().toString();
        String minPasswordLength = tab1.getjTextField5().getText();
        String currency = tab1.getjComboBox5().getSelectedItem().toString();
        String digits = tab1.getjComboBox6().getSelectedItem().toString();
        String enableConntectionFlag = "" + tab1.getjCheckBox1().isSelected();
        String enablecertiFlag = "" + tab1.getjCheckBox2().isSelected();
        String changePswdFlag = "" + tab1.getjCheckBox3().isSelected();
        String showRiskFlag = "" + tab1.getjCheckBox4().isSelected();
        String enforceCountryFlag = "" + tab1.getjCheckBox5().isSelected();

        // Company Group tab value
        String company = tab2.getjTextField1().getText();
        String compSite = tab2.getjTextField2().getText();
        String compEmail = tab2.getjTextField3().getText();
        String depositSite = tab2.getjTextField4().getText();
        String withdrawlSite = tab2.getjTextField6().getText();
        String supportSite = tab2.getjTextField7().getText();
        String supportEmail = tab2.getjTextField8().getText();
        String templateFolder = tab2.getjTextField5().getText();

        // news and mail tab value
        String news = tab3.getjComboBox1().getSelectedItem().toString();
        String newsCat = tab3.getjComboBox2().getSelectedItem().toString();
        String newsLang = tab3.getjComboBox3().getSelectedItem().toString();
        String enableEMail = "" + tab3.getjCheckBox1().isSelected();

        // PermissionsGroupConfig
        String maxSymbol = tab4.getjComboBox1().getSelectedItem().toString();
        String maxPosition = tab4.getjComboBox2().getSelectedItem().toString();
        String availHistory = tab4.getjComboBox3().getSelectedItem().toString();
        String maxOrder = tab4.getjComboBox4().getSelectedItem().toString();
        String defaultLevrage = tab4.getjComboBox5().getSelectedItem().toString().split(":")[1].trim();
        System.out.println("default leverage: " + defaultLevrage);
        String tradingSignal = tab4.getjComboBox6().getSelectedItem().toString();
        String fundTransfer = tab4.getjComboBox7().getSelectedItem().toString();
        String defaultDeposit = tab4.getjTextField1().getText();
        String AnnualInterestRate = tab4.getjTextField2().getText();
        String enableTrading = tab4.getjCheckBox1().isSelected() + "";
        String enablePosition = tab4.getjCheckBox2().isSelected() + "";
        String enableTrailing = tab4.getjCheckBox4().isSelected() + "";
        String prohibitHedgePosition = tab4.getjCheckBox5().isSelected() + "";
        String enableSwapChange = tab4.getjCheckBox6().isSelected() + "";

        // marginGroupConfig
        String riskMgmt = tab5.getjComboBox1().getSelectedItem().toString();
        String in = tab5.getjComboBox2().getSelectedItem().toString();
        String unrealizedProfit = tab5.getjComboBox3().getSelectedItem().toString();
        String delayProfit = tab5.getjComboBox4().getSelectedItem().toString();
        String marginCallLevel = tab5.getjTextField1().getText();
        String stopOutFlag = tab5.getjCheckBox1().isSelected() + "";
        String compensateNegativeBalance = tab5.getjCheckBox2().isSelected() + "";
        String withdrawCredit = tab5.getjCheckBox3().isSelected() + "";
        String ReleaseFixedProfit = tab5.getjCheckBox4().isSelected() + "";
        String stopOutLevel = tab5.getjTextField2().getText();

        // reportGropConfig
        String genDailyData = tab8.getjCheckBox1().isSelected() + "";
        String genDailyState = tab8.getjCheckBox2().isSelected() + "";
        String sendDailyState = tab8.getjCheckBox3().isSelected() + "";
        String sendCopy = tab8.getjCheckBox4().isSelected() + "";
        String mailServer = tab8.getjComboBox1().getSelectedItem().toString();

        // symbols group config
        JSONArray symbolTable = SymbolsGroupConfig.symbolsTableData;
        //JTable symbolTable = tab6.getjTable1();
//        int rowCount = symbolTable.getRowCount();
//        int colCount = symbolTable.getColumnCount();
//        String[] columnNames = new String[colCount];
//        Object[][] data = new Object[rowCount][colCount];
//
//        for (int i = 0; i < colCount; i++) {
//            columnNames[i] = symbolTable.getColumnName(i);
//        }
//
//        for (int i = 0; i < rowCount; i++) {
//            for (int j = 0; j < colCount; j++) {
//                data[i][j] = symbolTable.getValueAt(i, j);
//            }
//        }

//        List<Map<String, Object>> dataList = new ArrayList<>();
//        for (int i = 0; i < rowCount; i++) {
//            Map<String, Object> map = new HashMap<>();
//            for (int j = 0; j < colCount; j++) {
//                map.put(columnNames[j], data[i][j]);
//            }
//            dataList.add(map);
//        }
//
//        System.out.println(dataList.toString() + "<<<--- datalist");
//        return;/
        // commsiion group config
//        JTable commissionTable = tab7.getjTable1();
//        DefaultTableModel model = CommissionsGroupConfig.tablemodel;
//        int comrowCount = model.getRowCount();
//        int comcolCount = model.getColumnCount();
//        String[] comcolumnNames = new String[comcolCount];
//        Object[][] comdata = new Object[comrowCount][comcolCount];
//
//        for (int i = 0; i < comcolCount; i++) {
//            comcolumnNames[i] = model.getColumnName(i);
//        }
//        System.out.println("rowCount: " + comrowCount);
//        System.out.println("comcolcount: " + comcolCount);
//        for (int i = 0; i < comrowCount; i++) {
//            for (int j = 0; j < comcolCount; j++) {
//                comdata[i][j] = model.getValueAt(i, j);
//            }
//        }
//       
//        List<Map<String, Object>> comdataList = new ArrayList<>();
//        for (int i = 0; i < comrowCount; i++) {
//            Map<String, Object> map = new HashMap<>();
//            for (int j = 0; j < comcolCount; j++) {
//                map.put(comcolumnNames[j], comdata[i][j]);
//            }
//            comdataList.add(map);
//        }
        var modelData = CommissionsGroupConfig.modelData;
        String[] array = new String[modelData.size()];
        for (int i = 0; i < modelData.size(); i++) {
            array[i] = Arrays.toString(modelData.get(i));
        }
        String result = Arrays.toString(array);
        System.out.println("result: " + result);
        String commissionString = Arrays.toString(array);
//        JSONArray symbolTableData = new JSONArray(SymbolsGroupConfig.symbolTableData2);
        // Creating json object from variable
        JSONObject jsnObj = new JSONObject();
        jsnObj.put("Name", name);
        jsnObj.put("TradeServer", tradeServer);
        jsnObj.put("Authentication", auth);
        jsnObj.put("Otp", otp);
        jsnObj.put("PushNotification", pushNotification);
        jsnObj.put("Currency", currency);
        jsnObj.put("Digits", digits);
        jsnObj.put("MiniPasswordLength", minPasswordLength);
        jsnObj.put("EnableConnectionFlag", enableConntectionFlag);
        jsnObj.put("EnableCertificateFlag", enablecertiFlag);
        jsnObj.put("ChangePasswordFlag", changePswdFlag);
        jsnObj.put("ShowRiskFlag", showRiskFlag);
        jsnObj.put("EnforceCountryFlag", enforceCountryFlag);
        jsnObj.put("Company", company);
        jsnObj.put("CompanySite", compSite);
        jsnObj.put("CompanyEmail", compEmail);
        jsnObj.put("DepositSite", depositSite);
        jsnObj.put("WithdrawalSite", withdrawlSite);
        jsnObj.put("SupportSite", supportSite);
        jsnObj.put("SupportEmail", supportEmail);
        jsnObj.put("TemplateFolder", templateFolder);

        jsnObj.put("News", news);
        jsnObj.put("NewsCat", newsCat);
        jsnObj.put("NewsLang", newsLang);
        jsnObj.put("EnableInternalMailFlag", enableEMail);
        jsnObj.put("MaximumSymbol", maxSymbol);
        jsnObj.put("MaxPosition", maxPosition);
        jsnObj.put("AvailableHistory", availHistory);
        jsnObj.put("MaxOrders", maxOrder);
        jsnObj.put("LeverageByDefault", defaultLevrage);
        jsnObj.put("TradingSignals", tradingSignal);
        jsnObj.put("TransferOfFunds", fundTransfer);
        jsnObj.put("DepositByDefault", defaultDeposit);
        jsnObj.put("AnnualInterestRate", AnnualInterestRate);
        jsnObj.put("EnableAdviserTradingFlag", enableTrading);
        jsnObj.put("EnablePositionFlag", enablePosition);
        jsnObj.put("EnableTrailingStopsFlag", enableTrailing);
        jsnObj.put("HedgePositionFlag", prohibitHedgePosition);
        jsnObj.put("EnableChargeSwapsFlag", enableSwapChange);
        jsnObj.put("RiskMgmt", riskMgmt);
        jsnObj.put("StopOutLevelIn", in);
        jsnObj.put("StopOutLevel", stopOutLevel);
        jsnObj.put("UnrealizedProfit", unrealizedProfit);

        jsnObj.put("DelayProfit", delayProfit);
        jsnObj.put("MarginCallLevel", marginCallLevel);
        jsnObj.put("WithdrawCreditFlag", withdrawCredit);
        jsnObj.put("StopOutFullyFlag", stopOutFlag);
        jsnObj.put("CompensateNegativeBalanceFlag", compensateNegativeBalance);
        jsnObj.put("ReleaseFixedProfitFlag", ReleaseFixedProfit);
        jsnObj.put("GenDailyDataFlag", genDailyData);
        jsnObj.put("GenDailyStatementFlag", genDailyState);
        jsnObj.put("SendDailyStatementFlag", sendDailyState);
        jsnObj.put("SendCopyFlag", sendCopy);
        jsnObj.put("MailServer", mailServer);
        jsnObj.put("SymbolTable", symbolTable);
        jsnObj.put("SymbolSetting", SymbolsGroupConfig.symbolTableData2);
        jsnObj.put("HCategory", HCategory);
        jsnObj.put("ComissionTable", CommissionsGroupConfig.jarray);
        if (!"createGroup".equals(groupId)) {
            jsnObj.put("groupId", groupId);
        }

        System.out.println("gorup json : " + jsnObj);
        RequestBody body = RequestBody.create(JSON, jsnObj.toString());
        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        System.out.println(body);
//        
//        
        Response response = client.newCall(request).execute();

        // Check for successful response
        if (response.isSuccessful()) {
            // Get response body as strings
            String responseBody = response.body().string();
            BrokerAdmin.splitPane.setRightComponent(new GroupPanel());
            System.out.println("_____________________*****************_________________");
            System.out.println(responseBody);
            System.out.println("_____________________*****************_________________");
        } else {
            System.out.println("Error: " + response.body().toString());
        }

    }

    public GroupConfig() {
//        super("Group Config");
        setTitle("Group Config");
        setModal(true);
//        String symbolData = getSymbolData();

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create the tabs
        CommonGroupPage tab1 = new CommonGroupPage();

        CompanyGroupPage tab2 = new CompanyGroupPage();

        NewsEmail tab3 = new NewsEmail();

        PermissionsGroupConfig tab4 = new PermissionsGroupConfig();

        MarginGroupConfig tab5 = new MarginGroupConfig();
//        JSONObject jso = new JSONObject(symbolData);
//        JSONArray symbolDataObject = jso.getJSONArray("message");
        SymbolsGroupConfig tab6 = new SymbolsGroupConfig();

        CommissionsGroupConfig tab7 = new CommissionsGroupConfig();

        ReportGroupConfig tab8 = new ReportGroupConfig();

        // Add the tabs to the tabbed pane
        tabbedPane.addTab("Common", tab1);
        tabbedPane.addTab("Company", tab2);
        tabbedPane.addTab("News & Mail", tab3);
        tabbedPane.addTab("Permissions", tab4);
        tabbedPane.addTab("Margin", tab5);
        tabbedPane.addTab("Symbols", tab6);
        tabbedPane.addTab("Commissions", tab7);
        tabbedPane.addTab("Reports", tab8);

        setLayout(new FlowLayout());
        // Add the tabbed pane to the frame
        getContentPane().add(tabbedPane);

        JButton jbutton1 = new JButton("Submit");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(jbutton1);

        jbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Btn clicked");
                    postData("createGroup", tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8);
                    dispose();
                } catch (IOException | JSONException ex) {
                    Logger.getLogger(SixTabFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        add(panel);
        pack();
        // Set the frame size and visibility
        setSize(720, 540);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @SuppressWarnings("empty-statement")
    final String getSymbolData() {
        OkHttpClient client = new OkHttpClient();
        String symbolApiUrl = APIs.GET_SYMBOLS + "?timestamp=" + "10";
        Request symbolRequest = new Request.Builder()
                .url(symbolApiUrl)
                .build();
        Call call2 = client.newCall(symbolRequest);
        try {
            Response symbolResponse = call2.execute();
            return symbolResponse.body().string();
        } catch (Exception ex) {
            return "";
        }
    }

    final String[] getData(String groupId) {
        OkHttpClient client = new OkHttpClient();
        String apiUrl = com.mycompany.metatraderbrokeradmin.Utility.APIs.GET_SINGLE_GROUP + "?groupId=" + groupId;

        // Create a Request object
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();
        Call call = client.newCall(request);

        try {
            Response res = call.execute();
//            Response symbolResponse = call2.execute();
            String groupResponse = res.body().string();
//            System.out.println("Group Response"+groupResponse);
//            String groupSymbol = symbolResponse.body().string();
            String[] returns = {groupResponse};
            return returns;
        } catch (IOException e) {
            System.out.println("group config");
            String[] returns = {"", ""};
            return returns;
        }
    }

    public GroupConfig(String groupId) {
//        super("Group Config");
        setTitle("Group Config");
        setModal(true);
        System.out.println("GroupId = " + groupId);
        String[] apiData = getData(groupId);
        System.out.println("GROUP DATA : " + apiData.toString());
        String groupData = apiData[0];
        JSONObject jspre = new JSONObject(groupData);
        JSONObject js = jspre.getJSONObject("message");

        JSONArray symbolData4Table = js.getJSONArray("SymbolTable");
        JSONArray comissionData4Table = js.getJSONArray("ComissionTable");
        System.out.println("__________Get Response json________-");
        System.out.println(js);
        System.out.println("___________Get Response json__________-");

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create the tabs
        CommonGroupPage tab1 = new CommonGroupPage(js);

//        tab1.add(new JLabel("Tab 1"));
        CompanyGroupPage tab2 = new CompanyGroupPage(js);
//        tab2.add(new JLabel("Tab 2"));

        NewsEmail tab3 = new NewsEmail(js);
//        tab3.add(new JLabel("Tab 3"));

        PermissionsGroupConfig tab4 = new PermissionsGroupConfig(js);
//        tab4.add(new JLabel("Tab 4"));

        MarginGroupConfig tab5 = new MarginGroupConfig(js);
//        tab5.add(new JLabel("Tab 5"));

        SymbolsGroupConfig tab6 = new SymbolsGroupConfig(symbolData4Table, true);
//        tab6.add(new JLabel("Tab 6"));

        CommissionsGroupConfig tab7 = new CommissionsGroupConfig(comissionData4Table);
//        tab7.add(new JLabel("Tab 6"));

        ReportGroupConfig tab8 = new ReportGroupConfig();
//        tab8.add(new JLabel("Tab 6"));

        // Add the tabs to the tabbed pane
        tabbedPane.addTab("Common", tab1);
        tabbedPane.addTab("Company", tab2);
        tabbedPane.addTab("News & Mail", tab3);
        tabbedPane.addTab("Permissions", tab4);
        tabbedPane.addTab("Margin", tab5);
        tabbedPane.addTab("Symbols", tab6);
        tabbedPane.addTab("Commissions", tab7);
        tabbedPane.addTab("Reports", tab8);

        setLayout(new FlowLayout());
        // Add the tabbed pane to the frame
        getContentPane().add(tabbedPane);

        JButton jbutton1 = new JButton("Update");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(jbutton1);

        jbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Btn clicked");
                    postData(groupId, tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8);
                    dispose();
                } catch (IOException | JSONException ex) {
                    Logger.getLogger(SixTabFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
//        getContentPane().add(panel);
        add(panel);
        pack();
        // Set the frame size and visibility
        setSize(720, 540);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // here we doesn't need nonRequired number in our logic is was written just t
    public GroupConfig(int nonRequiredNumber, String HCategory) {
        setTitle("Group Config");
        setModal(true);
        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create the tabs
        CommonGroupPage tab1 = new CommonGroupPage();
        CompanyGroupPage tab2 = new CompanyGroupPage();
        NewsEmail tab3 = new NewsEmail();
        PermissionsGroupConfig tab4 = new PermissionsGroupConfig();
        MarginGroupConfig tab5 = new MarginGroupConfig();
        SymbolsGroupConfig tab6 = new SymbolsGroupConfig();
        CommissionsGroupConfig tab7 = new CommissionsGroupConfig();
        ReportGroupConfig tab8 = new ReportGroupConfig();

        // Add the tabs to the tabbed pane
        tabbedPane.addTab("Common", tab1);
        tabbedPane.addTab("Company", tab2);
        tabbedPane.addTab("News & Mail", tab3);
        tabbedPane.addTab("Permissions", tab4);
        tabbedPane.addTab("Margin", tab5);
        tabbedPane.addTab("Symbols", tab6);
        tabbedPane.addTab("Commissions", tab7);
        tabbedPane.addTab("Reports", tab8);

        setLayout(new FlowLayout());
        // Add the tabbed pane to the frame
        getContentPane().add(tabbedPane);

        JButton jbutton1 = new JButton("Submit");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(jbutton1);

        jbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Btn clicked");
                    postData("createGroup", tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8, HCategory);
                    dispose();
                } catch (IOException | JSONException ex) {
                    Logger.getLogger(SixTabFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        add(panel);
        pack();
        // Set the frame size and visibility
        setSize(720, 540);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GroupConfig();
    }
}
