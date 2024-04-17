package com.mycompany.metatraderbrokeradmin.SymbolConfig;

import com.mycompany.metatraderbrokeradmin.BrokerAdmin;
import com.mycompany.metatraderbrokeradmin.SymbolPanel;
import com.mycompany.metatraderbrokeradmin.TreePanel;
import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SixTabFrame extends JDialog {

    public SixTabFrame(String HCategory) {
        setTitle("Symbol Config");
        setModal(true);

        CommonSymbolPage tab1 = new CommonSymbolPage();
        CurrencySymbolPage tab2 = new CurrencySymbolPage();
        QuotesSymbolPage tab3 = new QuotesSymbolPage();
        TradeSymbolPage tab4 = new TradeSymbolPage();
        ExecutionSymbolPage tab5 = new ExecutionSymbolPage();
        MarginSymbolPage tab6 = new MarginSymbolPage();
        MarginRatesSymbolPage tab7 = new MarginRatesSymbolPage();
        SwapsSymbolPage tab8 = new SwapsSymbolPage();
        SessionsSymbolPage tab9 = new SessionsSymbolPage();

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add the tabs to the tabbed pane
        tabbedPane.addTab("Common", tab1);
        tabbedPane.addTab("Currency", tab2);
        tabbedPane.addTab("Quotes", tab3);
        tabbedPane.addTab("Trade", tab4);
        tabbedPane.addTab("Execution", tab5);
        tabbedPane.addTab("Margin", tab6);
        tabbedPane.addTab("Margin Rates", tab7);
        tabbedPane.addTab("Swaps", tab8);
        tabbedPane.addTab("Sessions", tab9);

        SubmitBtnPanel submitBtnPanel = new SubmitBtnPanel("Submit");

        JSplitPane js = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabbedPane, submitBtnPanel);
        submitDetails(submitBtnPanel, "no symbol id here", tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8, tab9, HCategory);
        add(js);
        setSize(750, 500);
        setLocationRelativeTo(null);
//        setVisible(true);
    }

    final void submitDetails(SubmitBtnPanel submitBtnPanel, String symbolId, CommonSymbolPage tab1, CurrencySymbolPage tab2, QuotesSymbolPage tab3, TradeSymbolPage tab4, ExecutionSymbolPage tab5, MarginSymbolPage tab6, MarginRatesSymbolPage tab7, SwapsSymbolPage tab8, SessionsSymbolPage tab9, String HCategory) {
        submitBtnPanel.getjButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Btn clicked");
                if ("Submit".equals(submitBtnPanel.getjButton1().getText())) {
                    System.out.println("submit create btn clicked");
                    try {
                        postData("createSymbolRequest", tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8, tab9, HCategory);
                        dispose();
                    } catch (JSONException | IOException ex) {
                        Logger.getLogger(SixTabFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if ("Copy".equals(submitBtnPanel.getjButton1().getText())) {
                    //System.out.println("create btn clicked");
                    try {
                        System.out.println("copying symbol");
                        postData("createSymbolRequest", tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8, tab9, HCategory);
                        dispose();
                    } catch (JSONException | IOException ex) {
                        Logger.getLogger(SixTabFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    try {
                        System.out.println("update btn clicked");
//                        updateData(symbolId);
                        System.out.println(symbolId);
                        postData(symbolId, tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8, tab9);
                        dispose();
                    } catch (IOException | JSONException exception) {
                        Logger.getLogger(SixTabFrame.class.getName()).log(Level.SEVERE, null, exception);
                    }
                }
            }
        });
    }

    public void postData(String symbolId, CommonSymbolPage tab1, CurrencySymbolPage tab2, QuotesSymbolPage tab3, TradeSymbolPage tab4, ExecutionSymbolPage tab5, MarginSymbolPage tab6, MarginRatesSymbolPage tab7, SwapsSymbolPage tab8, SessionsSymbolPage tab9, String HCategory) throws JSONException, IOException {
        String apiUrl = APIs.CREATE_SYMBOL;

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        // common symbol page values
        String symbol = tab1.getjTextField1().getText();
        String exchange = tab1.getjTextField2().getText();
        String isin = tab1.getjTextField3().getText();
        String cfi = tab1.getjTextField4().getText();
        String basis = tab1.getjComboBox1().getText().toString();
//        String spreadBalance = tab1.getjSlider1().getValue() + "";
//        String spreadBalanceText = tab1.getjLabel21().getText();
//        String[] splitSpreadBalance1 = spreadBalanceText.split("/");
        String spreadBalanceBid = tab1.getjTextField9().getText();
        String spreadBalanceAsk = tab1.getjTextField10().getText();
        JSONObject spreadBalance = new JSONObject();
        spreadBalance.put("bid", Integer.parseInt(spreadBalanceBid));
        spreadBalance.put("ask", Integer.parseInt(spreadBalanceAsk));
        // String background = tab1.getjComboBox2().getSelectedItem().toString();
        String digits = tab1.getjComboBox3().getSelectedItem().toString();
        String spread = tab1.getjComboBox4().getSelectedItem().toString();
        String international = tab1.getjTextField6().getText();
        String desc = tab1.getjTextField5().getText();
        String sector = tab1.getjComboBox5().getSelectedItem().toString();
        String industry = tab1.getjComboBox6().getSelectedItem().toString();
        String country = tab1.getjComboBox7().getSelectedItem().toString();
        String category = tab1.getjTextField7().getText();
        String page = tab1.getjTextField8().getText();
        String marketDepth = tab1.getjComboBox8().getSelectedItem().toString();
        String chartMode = tab1.getjComboBox9().getSelectedItem().toString();
        String source = tab1.getjComboBox10().getText().toString();
        // currency symbol page values
        String baseCurrency = tab2.getjComboBox1().getSelectedItem().toString();
        String currencyBaseDigits = tab2.getjComboBox2().getSelectedItem().toString();
        String currencyProfit = tab2.getjComboBox3().getSelectedItem().toString();
        String currencyProfitDigits = tab2.getjComboBox4().getSelectedItem().toString();
        String currencyMargin = tab2.getjComboBox5().getSelectedItem().toString();
        String currencyMarginDigits = tab2.getjComboBox6().getSelectedItem().toString();

        // quotes remained
        String allowRealtimeQuotesFlag = tab3.getjCheckBox1().isSelected() + "";
        String recieveMarketStaticsFlag = tab3.getjCheckBox3().isSelected() + "";
        String allowNegativeQuotesFlag = tab3.getjCheckBox2().isSelected() + "";
        String saveRawPricesFlag = tab3.getjCheckBox3().isSelected() + "";
        String softFiltrationLevel = tab3.getjTextField1().getText();
        String filter = tab3.getjComboBox1().getSelectedItem().toString();
        String hardFiltrationLevel = tab3.getjTextField2().getText();
        String discardFilterationLevel = tab3.getjTextField3().getText();
        String Quotesfilter = tab3.getjComboBox2().getSelectedItem().toString();
        String gapModeLevel = tab3.getjComboBox3().getSelectedItem().toString();
        String minSpread = tab3.getjComboBox4().getSelectedItem().toString();
        String delay4Subs = tab3.getjComboBox5().getSelectedItem().toString();
        String disableGapAfter = tab3.getjComboBox6().getSelectedItem().toString();
        String maxSpread = tab3.getjComboBox7().getSelectedItem().toString();

        // trade Symbol page
        String contractSize = tab4.getjTextField1().getText();
        String calculation = tab4.getjComboBox1().getSelectedItem().toString();
        String trade = tab4.getjComboBox2().getSelectedItem().toString();
        String gtc = tab4.getjComboBox3().getSelectedItem().toString();
        String filling = tab4.getjComboBox4().getSelectedItem().toString();
        String expiration = tab4.getjComboBox5().getSelectedItem().toString();
        String orders = tab4.getjComboBox6().getText().toString();
        String stopLevel = tab4.getjTextField2().getText();
        String freezeLevel = tab4.getjTextField3().getText();
        String maxQuoteDelay = tab4.getjTextField4().getText();
        String convertProfit = tab4.getjComboBox7().getSelectedItem().toString();
        String minVolumeTrade = tab4.getjComboBox8().getSelectedItem().toString();
        String stepVolumeTrade = tab4.getjComboBox9().getSelectedItem().toString();
        String maxVolumeTrade = tab4.getjComboBox10().getSelectedItem().toString();
        String limitVolumeTrade = tab4.getjComboBox11().getSelectedItem().toString();
        String enableTradingSignalFlag = tab4.getjCheckBox1().isSelected() + "";
        // execution 
        String execution = tab5.getjComboBox1().getSelectedItem().toString();
        String maxTimeDeveation = tab5.getjComboBox2().getSelectedItem().toString();
        String maxProfitDeveation = tab5.getjComboBox3().getSelectedItem().toString();
        String maxLosingDeviation = tab5.getjComboBox4().getSelectedItem().toString();
        String FastConfirmationFlag = tab5.getjCheckBox1().isSelected() + "";
        String maxVolume = tab5.getjTextField1().getText();

        // margin symbol page
        String initialMargin = tab6.getjTextField1().getText();
        String hedgedMargin = tab6.getjTextField2().getText();
        String maintaninenceMargin = tab6.getjTextField3().getText();
        String calculateHedgedMarginFlag = "" + tab6.getjCheckBox1().isSelected();
        String execLongPositionFlag = "" + tab6.getjCheckBox2().isSelected();
        String reCalculateMarginFlag = "" + tab6.getjCheckBox3().isSelected();
        String additionalMarginChecks = tab6.getjComboBox1().getSelectedItem().toString();

        // margin rates symbol
        String marginLiquidity = tab7.getjTextField1().getText();
        String marginCurrency = tab7.getjTextField2().getText();
        DefaultTableModel marginRateTableObj = (DefaultTableModel) tab7.getjTable1().getModel();
//        Vector<Vector> marginRateTable = marginRateTableObj.getDataVector();
//        System.out.println("swap table = " + marginRateTable);

        JSONArray mjsarray1 = new JSONArray();
        JSONArray mjsarray2 = new JSONArray();
        JSONArray mjsarray = new JSONArray();
        for (int i = 0; i < marginRateTableObj.getRowCount(); i++) {
            JSONObject js = new JSONObject();
            for (int j = 0; j < marginRateTableObj.getColumnCount(); j++) {
                js.put("market", marginRateTableObj.getValueAt(i, 1));
                js.put("limit_order", marginRateTableObj.getValueAt(i, 2));
                js.put("stop_order", marginRateTableObj.getValueAt(i, 3));
                js.put("stop_limit_order", marginRateTableObj.getValueAt(i, 4));
            }
            if (i == 1) {
                mjsarray1.put(0, js);
            }
            if (i == 2) {
                mjsarray1.put(1, js);
            }
            if (i == 4) {
                mjsarray2.put(0, js);
            }
            if (i == 5) {
                mjsarray2.put(1, js);
            }

        }
        mjsarray.put(0, mjsarray1);
        mjsarray.put(1, mjsarray2);

        String marginlevel = mjsarray.toString();
        System.out.println("swap table = " + mjsarray.toString());

        // Swap symbol 
        String enableSwapsFlag = "" + tab8.getjCheckBox1().isSelected();
        String type = tab8.getjComboBox1().getSelectedItem().toString();
        String longPosition = tab8.getjTextField1().getText();
        String shortPosition = tab8.getjTextField2().getText();
        String dayInYear = tab8.getjComboBox2().getSelectedItem().toString();
        String automaticallyConsiderHolidaysFlag = "" + tab8.getjCheckBox2().isSelected();
        DefaultTableModel swapTableObj = (DefaultTableModel) tab8.getjTable2().getModel();
//        Vector<Vector> swapTable = swapTableObj.getDataVector();

        JSONArray jsarray = new JSONArray();
        for (int i = 0; i < swapTableObj.getRowCount(); i++) {
            JSONObject js = new JSONObject();
            for (int j = 0; j < swapTableObj.getColumnCount(); j++) {
                js.put("day", swapTableObj.getValueAt(i, 0));
                js.put("value", swapTableObj.getValueAt(i, 1));
            }
            jsarray.put(i, js);
        }

//        String daysjsa = jsarray.toString();
//        System.out.println("swap table = " + daysjsa);
        // sessions
        String useTimeLimitFlag = tab9.getjCheckBox1().isSelected() + "";
        DefaultTableModel sessionTableObj = (DefaultTableModel) tab9.getjTable1().getModel();
        Vector<Vector> sessionTable = sessionTableObj.getDataVector();
        // creating json object
        JSONObject jsnObj = new JSONObject();
        jsnObj.put("Symbol", symbol);
        jsnObj.put("ISIN", isin);
        jsnObj.put("CFI", cfi);
        jsnObj.put("Exchange", exchange);
        jsnObj.put("Description", desc);
        jsnObj.put("International", international);
        jsnObj.put("Category", category);
        jsnObj.put("Page", page);
        jsnObj.put("Basis", basis);
        jsnObj.put("ChartMode", chartMode);
        jsnObj.put("Source", source);
//        jsnObj.put("Spread",);
//         jsnObj.put("ColorBackground", background);
        jsnObj.put("Digits", digits);
        jsnObj.put("Spread", spread);
        jsnObj.put("Sector", sector);
        jsnObj.put("Industry", industry);
        jsnObj.put("Country", country);
        jsnObj.put("MarketDepth", marketDepth);
        jsnObj.put("CurrencyBase", baseCurrency);
        jsnObj.put("CurrencyBaseDigits", currencyBaseDigits);
        jsnObj.put("CurrencyProfit", currencyProfit);
        jsnObj.put("CurrencyProfitDigits", currencyProfitDigits);
        jsnObj.put("CurrencyMargin", currencyMargin);
        jsnObj.put("CurrencyMarginDigits", currencyMarginDigits);
        jsnObj.put("ContractSize", contractSize);
        jsnObj.put("StopsLevel", stopLevel);
        jsnObj.put("FreezeLevel", freezeLevel);
        jsnObj.put("QuotesTimeout", maxQuoteDelay);
        jsnObj.put("CalcMode", calculation);
        jsnObj.put("TradeMode", trade);
        jsnObj.put("GTCMode", gtc);
        jsnObj.put("FillFlags", filling);
        jsnObj.put("ExpirFlags", expiration);
        jsnObj.put("OrderFlags", orders);
        jsnObj.put("MarginInitial", initialMargin);
        jsnObj.put("MarginHedge", hedgedMargin);
        jsnObj.put("MarginMaintenance", maintaninenceMargin);
        jsnObj.put("SwapMode", type);
        jsnObj.put("SwapLong", longPosition);
        jsnObj.put("SwapShort", shortPosition);
        jsnObj.put("SwapYearDay", dayInYear);
        jsnObj.put("MarginLiquidity", marginLiquidity);
        jsnObj.put("MarginCurrency", marginCurrency);
        jsnObj.put("AllowRealtimeQuotesFlag", allowRealtimeQuotesFlag);
        jsnObj.put("RecieveMarketStaticsFlag", recieveMarketStaticsFlag);
        jsnObj.put("AllowNegativeQuotesFlag", allowNegativeQuotesFlag);
        jsnObj.put("SaveRawPricesFlag", saveRawPricesFlag);
        jsnObj.put("SoftFiltrationLevel", softFiltrationLevel);
        jsnObj.put("Filter", filter);
        jsnObj.put("HardFilterationLevel", hardFiltrationLevel);
        jsnObj.put("FilterDiscard", discardFilterationLevel);
        jsnObj.put("QuotesFilter", Quotesfilter);
        jsnObj.put("GapModeLevel", gapModeLevel);
        jsnObj.put("MinSpread", minSpread);
        jsnObj.put("Delay4Subs", delay4Subs);
        jsnObj.put("DisableGapAfter", disableGapAfter);
        jsnObj.put("MaxSpread", maxSpread);
        jsnObj.put("ConvertProfit", convertProfit);
        jsnObj.put("MinVolumeTrade", minVolumeTrade);
        jsnObj.put("StepVolumeTrade", stepVolumeTrade);
        jsnObj.put("MaxVolumeTrade", maxVolumeTrade);
        jsnObj.put("LimitVolumeTrade", limitVolumeTrade);
        jsnObj.put("EnableTradingSignalFlag", enableTradingSignalFlag);
        jsnObj.put("Execution", execution);
        jsnObj.put("MaxTimeDeviation", maxTimeDeveation);
        jsnObj.put("MaxProfitDeviation:", maxProfitDeveation);
        jsnObj.put("MaxLosingDeviation", maxLosingDeviation);
        jsnObj.put("FastConfirmationFlag", FastConfirmationFlag);
        jsnObj.put("maxVolume", maxVolume);
        jsnObj.put("CalculateHedgeMarginFlag", calculateHedgedMarginFlag);
        jsnObj.put("ExecLongPositionHedgeFlag", execLongPositionFlag);
        jsnObj.put("ReCalculateMarginFlag", reCalculateMarginFlag);
        jsnObj.put("AdditionalMarginChecks", additionalMarginChecks);
        jsnObj.put("EnableSwapsFlag", enableSwapsFlag);
        jsnObj.put("AutoConsiderHolidayFlag", automaticallyConsiderHolidaysFlag);
        jsnObj.put("UseTimeLimitFlag", useTimeLimitFlag);
        jsnObj.put("MarginRatesTable", mjsarray);
        jsnObj.put("SwapMultiplyTable", jsarray);
        jsnObj.put("SessionTable", sessionTable);
        jsnObj.put("SpreadBalance", spreadBalance);

        jsnObj.put("HCategory", HCategory);
        if (!"createSymbolRequest".equals(symbolId)) {
            System.out.println("update request hit");
            jsnObj.put("symbolId", symbolId);
        } else {
            System.out.println("add user request hit");
        }
        System.out.println("request json = " + jsnObj);
        if (!"createSymbolRequest".equals(symbolId)) {
            apiUrl = APIs.UPDATE_SYMBOL;
        }

        System.out.println(apiUrl);
        RequestBody body = RequestBody.create(JSON, jsnObj.toString());

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        // Check for successful response
        if (response.isSuccessful()) {
            // Get response body as strings
            String responseBody = response.body().string();
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

    //this one
    public void postData(String symbolId, CommonSymbolPage tab1, CurrencySymbolPage tab2, QuotesSymbolPage tab3, TradeSymbolPage tab4, ExecutionSymbolPage tab5, MarginSymbolPage tab6, MarginRatesSymbolPage tab7, SwapsSymbolPage tab8, SessionsSymbolPage tab9) throws JSONException, IOException {
        String apiUrl = APIs.CREATE_SYMBOL;

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        // common symbol page values
        String symbol = tab1.getjTextField1().getText();
        String exchange = tab1.getjTextField2().getText();
        String isin = tab1.getjTextField3().getText();
        String cfi = tab1.getjTextField4().getText();
        String basis = tab1.getjComboBox1().getText().toString();
//        String spreadBalance = tab1.getjSlider1().getValue() + "";
//        String spreadBalanceText = tab1.getjLabel21().getText();
//        String[] splitSpreadBalance1 = spreadBalanceText.split("/");
        String spreadBalanceBid = tab1.getjTextField9().getText();
        String spreadBalanceAsk = tab1.getjTextField10().getText();
        JSONObject spreadBalance = new JSONObject();
        spreadBalance.put("bid", Integer.parseInt(spreadBalanceBid));
        spreadBalance.put("ask", Integer.parseInt(spreadBalanceAsk));
        // String background = tab1.getjComboBox2().getSelectedItem().toString();
        String digits = tab1.getjComboBox3().getSelectedItem().toString();
        String spread = tab1.getjComboBox4().getSelectedItem().toString();
        String international = tab1.getjTextField6().getText();
        String desc = tab1.getjTextField5().getText();
        String sector = tab1.getjComboBox5().getSelectedItem().toString();
        String industry = tab1.getjComboBox6().getSelectedItem().toString();
        String country = tab1.getjComboBox7().getSelectedItem().toString();
        String category = tab1.getjTextField7().getText();
        String page = tab1.getjTextField8().getText();
        String marketDepth = tab1.getjComboBox8().getSelectedItem().toString();
        String chartMode = tab1.getjComboBox9().getSelectedItem().toString();
        String source = tab1.getjComboBox10().getText().toString();
        // currency symbol page values
        String baseCurrency = tab2.getjComboBox1().getSelectedItem().toString();
        String currencyBaseDigits = tab2.getjComboBox2().getSelectedItem().toString();
        String currencyProfit = tab2.getjComboBox3().getSelectedItem().toString();
        String currencyProfitDigits = tab2.getjComboBox4().getSelectedItem().toString();
        String currencyMargin = tab2.getjComboBox5().getSelectedItem().toString();
        String currencyMarginDigits = tab2.getjComboBox6().getSelectedItem().toString();
//        String HCategory = tab2.getjComboBox7().
        int selectedHCategory = tab2.getjComboBox7().getSelectedIndex();
        String HCategoryId = tab2.folders.get(selectedHCategory).getFolderId();
        // System.out.println("HCategory: " + HCategoryId);
        // quotes remained
        String allowRealtimeQuotesFlag = tab3.getjCheckBox1().isSelected() + "";
        String recieveMarketStaticsFlag = tab3.getjCheckBox3().isSelected() + "";
        String allowNegativeQuotesFlag = tab3.getjCheckBox2().isSelected() + "";
        String saveRawPricesFlag = tab3.getjCheckBox3().isSelected() + "";
        String softFiltrationLevel = tab3.getjTextField1().getText();
        String filter = tab3.getjComboBox1().getSelectedItem().toString();
        String hardFiltrationLevel = tab3.getjTextField2().getText();
        String discardFilterationLevel = tab3.getjTextField3().getText();
        String Quotesfilter = tab3.getjComboBox2().getSelectedItem().toString();
        String gapModeLevel = tab3.getjComboBox3().getSelectedItem().toString();
        String minSpread = tab3.getjComboBox4().getSelectedItem().toString();
        String delay4Subs = tab3.getjComboBox5().getSelectedItem().toString();
        String disableGapAfter = tab3.getjComboBox6().getSelectedItem().toString();
        String maxSpread = tab3.getjComboBox7().getSelectedItem().toString();

        // trade Symbol page
        String contractSize = tab4.getjTextField1().getText();
        String calculation = tab4.getjComboBox1().getSelectedItem().toString();
        String trade = tab4.getjComboBox2().getSelectedItem().toString();
        String gtc = tab4.getjComboBox3().getSelectedItem().toString();
        String filling = tab4.getjComboBox4().getSelectedItem().toString();
        String expiration = tab4.getjComboBox5().getSelectedItem().toString();
        String orders = tab4.getjComboBox6().getText().toString();
        String stopLevel = tab4.getjTextField2().getText();
        String freezeLevel = tab4.getjTextField3().getText();
        String maxQuoteDelay = tab4.getjTextField4().getText();
        String convertProfit = tab4.getjComboBox7().getSelectedItem().toString();
        String minVolumeTrade = tab4.getjComboBox8().getSelectedItem().toString();
        String stepVolumeTrade = tab4.getjComboBox9().getSelectedItem().toString();
        String maxVolumeTrade = tab4.getjComboBox10().getSelectedItem().toString();
        String limitVolumeTrade = tab4.getjComboBox11().getSelectedItem().toString();
        String enableTradingSignalFlag = tab4.getjCheckBox1().isSelected() + "";
        // execution 
        String execution = tab5.getjComboBox1().getSelectedItem().toString();
        String maxTimeDeveation = tab5.getjComboBox2().getSelectedItem().toString();
        String maxProfitDeveation = tab5.getjComboBox3().getSelectedItem().toString();
        String maxLosingDeviation = tab5.getjComboBox4().getSelectedItem().toString();
        String FastConfirmationFlag = tab5.getjCheckBox1().isSelected() + "";
        String maxVolume = tab5.getjTextField1().getText();

        // margin symbol page
        String initialMargin = tab6.getjTextField1().getText();
        String hedgedMargin = tab6.getjTextField2().getText();
        String maintaninenceMargin = tab6.getjTextField3().getText();
        String calculateHedgedMarginFlag = "" + tab6.getjCheckBox1().isSelected();
        String execLongPositionFlag = "" + tab6.getjCheckBox2().isSelected();
        String reCalculateMarginFlag = "" + tab6.getjCheckBox3().isSelected();
        String additionalMarginChecks = tab6.getjComboBox1().getSelectedItem().toString();

        // margin rates symbol
        String marginLiquidity = tab7.getjTextField1().getText();
        String marginCurrency = tab7.getjTextField2().getText();
        DefaultTableModel marginRateTableObj = (DefaultTableModel) tab7.getjTable1().getModel();
//        Vector<Vector> marginRateTable = marginRateTableObj.getDataVector();
        JSONArray mjsarray1 = new JSONArray();
        JSONArray mjsarray2 = new JSONArray();
        JSONArray mjsarray = new JSONArray();
        for (int i = 0; i < marginRateTableObj.getRowCount(); i++) {
            JSONObject js = new JSONObject();
            for (int j = 0; j < marginRateTableObj.getColumnCount(); j++) {
                js.put("market", marginRateTableObj.getValueAt(i, 1));
                js.put("limit_order", marginRateTableObj.getValueAt(i, 2));
                js.put("stop_order", marginRateTableObj.getValueAt(i, 3));
                js.put("stop_limit_order", marginRateTableObj.getValueAt(i, 4));
            }
            if (i == 1) {
                mjsarray1.put(0, js);

            }
            if (i == 2) {
                mjsarray1.put(1, js);
            }
            if (i == 4) {
                mjsarray2.put(0, js);
            }
            if (i == 5) {
                mjsarray2.put(1, js);
            }

        }
        mjsarray.put(0, mjsarray1);
        mjsarray.put(1, mjsarray2);

        String marginlevel = mjsarray.toString();
        System.out.println("swap table = " + mjsarray.toString());
        // Swap symbol 
        String enableSwapsFlag = "" + tab8.getjCheckBox1().isSelected();
        String type = tab8.getjComboBox1().getSelectedItem().toString();
        String longPosition = tab8.getjTextField1().getText();
        String shortPosition = tab8.getjTextField2().getText();
        String dayInYear = tab8.getjComboBox2().getSelectedItem().toString();
        String automaticallyConsiderHolidaysFlag = "" + tab8.getjCheckBox2().isSelected();
        DefaultTableModel swapTableObj = (DefaultTableModel) tab8.getjTable2().getModel();
//        Vector<Vector> swapTable = swapTableObj.getDataVector();
        JSONArray jsarray = new JSONArray();
        for (int i = 0; i < swapTableObj.getRowCount(); i++) {
            JSONObject js = new JSONObject();
            for (int j = 0; j < swapTableObj.getColumnCount(); j++) {
                js.put("day", swapTableObj.getValueAt(i, 0));
                js.put("value", swapTableObj.getValueAt(i, 1));
            }
            jsarray.put(i, js);
        }

        String daysjsa = jsarray.toString();
        System.out.println("swap table = " + daysjsa);
//        System.out.println("swap table = " + swapTable);

        // sessions
        String useTimeLimitFlag = tab9.getjCheckBox1().isSelected() + "";
        DefaultTableModel sessionTableObj = (DefaultTableModel) tab9.getjTable1().getModel();
        Vector<Vector> sessionTable = sessionTableObj.getDataVector();
        // creating json object
        JSONObject jsnObj = new JSONObject();
        jsnObj.put("Symbol", symbol);
        jsnObj.put("ISIN", isin);
        jsnObj.put("CFI", cfi);
        jsnObj.put("Exchange", exchange);
        jsnObj.put("Description", desc);
        jsnObj.put("International", international);
        jsnObj.put("Category", category);
        jsnObj.put("Page", page);
        jsnObj.put("Basis", basis);
        jsnObj.put("ChartMode", chartMode);
        jsnObj.put("Source", source);
//         jsnObj.put("ColorBackground", background);
        jsnObj.put("Digits", digits);
        jsnObj.put("Spread", spread);
        jsnObj.put("Sector", sector);
        jsnObj.put("Industry", industry);
        jsnObj.put("Country", country);
        jsnObj.put("MarketDepth", marketDepth);
        jsnObj.put("CurrencyBase", baseCurrency);
        jsnObj.put("CurrencyBaseDigits", currencyBaseDigits);
        jsnObj.put("CurrencyProfit", currencyProfit);
        jsnObj.put("CurrencyProfitDigits", currencyProfitDigits);
        jsnObj.put("CurrencyMargin", currencyMargin);
        jsnObj.put("CurrencyMarginDigits", currencyMarginDigits);
        jsnObj.put("ContractSize", contractSize);
        jsnObj.put("StopsLevel", stopLevel);
        jsnObj.put("FreezeLevel", freezeLevel);
        jsnObj.put("QuotesTimeout", maxQuoteDelay);
        jsnObj.put("CalcMode", calculation);
        jsnObj.put("TradeMode", trade);
        jsnObj.put("GTCMode", gtc);
        jsnObj.put("FillFlags", filling);
        jsnObj.put("ExpirFlags", expiration);
        jsnObj.put("OrderFlags", orders);
        jsnObj.put("MarginInitial", initialMargin);
        jsnObj.put("MarginHedge", hedgedMargin);
        jsnObj.put("MarginMaintenance", maintaninenceMargin);
        jsnObj.put("SwapMode", type);
        jsnObj.put("SwapLong", longPosition);
        jsnObj.put("SwapShort", shortPosition);
        jsnObj.put("SwapYearDay", dayInYear);
        jsnObj.put("MarginLiquidity", marginLiquidity);
        jsnObj.put("MarginCurrency", marginCurrency);
        jsnObj.put("AllowRealtimeQuotesFlag", allowRealtimeQuotesFlag);
        jsnObj.put("RecieveMarketStaticsFlag", recieveMarketStaticsFlag);
        jsnObj.put("AllowNegativeQuotesFlag", allowNegativeQuotesFlag);
        jsnObj.put("SaveRawPricesFlag", saveRawPricesFlag);
        jsnObj.put("SoftFiltrationLevel", softFiltrationLevel);
        jsnObj.put("Filter", filter);
        jsnObj.put("HardFilterationLevel", hardFiltrationLevel);
        jsnObj.put("DiscardFilterationLevel", discardFilterationLevel);
        jsnObj.put("QuotesFilter", Quotesfilter);
        jsnObj.put("GapModeLevel", gapModeLevel);
        jsnObj.put("MinSpread", minSpread);
        jsnObj.put("Delay4Subs", delay4Subs);
        jsnObj.put("DisableGapAfter", disableGapAfter);
        jsnObj.put("MaxSpread", maxSpread);
        jsnObj.put("ConvertProfit", convertProfit);
        jsnObj.put("MinVolumeTrade", minVolumeTrade);
        jsnObj.put("StepVolumeTrade", stepVolumeTrade);
        jsnObj.put("MaxVolumeTrade", maxVolumeTrade);
        jsnObj.put("LimitVolumeTrade", limitVolumeTrade);
        jsnObj.put("EnableTradingSignalFlag", enableTradingSignalFlag);
        jsnObj.put("Execution", execution);
        jsnObj.put("MaxTimeDeviation", maxTimeDeveation);
        jsnObj.put("MaxProfitDeviation:", maxProfitDeveation);
        jsnObj.put("MaxLosingDeviation", maxLosingDeviation);
        jsnObj.put("FastConfirmationFlag", FastConfirmationFlag);
        jsnObj.put("maxVolume", maxVolume);
        jsnObj.put("CalculateHedgeMarginFlag", calculateHedgedMarginFlag);
        jsnObj.put("ExecLongPositionHedgeFlag", execLongPositionFlag);
        jsnObj.put("ReCalculateMarginFlag", reCalculateMarginFlag);
        jsnObj.put("AdditionalMarginChecks", additionalMarginChecks);
        jsnObj.put("EnableSwapsFlag", enableSwapsFlag);
        jsnObj.put("AutoConsiderHolidayFlag", automaticallyConsiderHolidaysFlag);
        jsnObj.put("UseTimeLimitFlag", useTimeLimitFlag);
        jsnObj.put("MarginRatesTable", mjsarray);
        jsnObj.put("SwapMultiplyTable", jsarray);
        jsnObj.put("SessionTable", sessionTable);
        jsnObj.put("SpreadBalance", spreadBalance);
        jsnObj.put("HCategory", HCategoryId);
        if (!"createSymbolRequest".equals(symbolId)) {
            System.out.println("update request hit");
            jsnObj.put("symbolId", symbolId);
        } else {
            System.out.println("add user request hit");
        }
        System.out.println("request json = " + jsnObj);
        if (!"createSymbolRequest".equals(symbolId)) {
            apiUrl = APIs.UPDATE_SYMBOL;
        }
        RequestBody body = RequestBody.create(JSON, jsnObj.toString());

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        // Check for successful response
        if (response.isSuccessful()) {
            // Get response body as strings
            String responseBody = response.body().string();
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

    final String getData(String symId) {
        OkHttpClient client = new OkHttpClient();
        String apiUrl = APIs.GET_SPECIFIC_SYMBOL + "?symbolId=" + symId;
        System.out.println("symbId: " + symId);
        // Create a Request object
        Request request = new Request.Builder()
                .url(apiUrl)
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

    public SixTabFrame(String type, String symbol) throws Exception {
        setTitle("Symbol Config");
        setModal(true);
        if (type == "new") {

            return;
        }

        String apiData = getData(symbol);
        System.out.println(apiData);
        JSONObject jspre = new JSONObject(apiData);

        JSONObject js = jspre.getJSONObject("message");
        String symbolId = js.getString("_id");
        System.out.println(symbolId);
        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        CommonSymbolPage tab1;
        if ("Copy".equals(type)) {
            tab1 = new CommonSymbolPage(true, js);
        } else {
            tab1 = new CommonSymbolPage(false, js);
        }

        CurrencySymbolPage tab2 = new CurrencySymbolPage(js);
        QuotesSymbolPage tab3 = new QuotesSymbolPage(js);
        TradeSymbolPage tab4 = new TradeSymbolPage(js);
        ExecutionSymbolPage tab5 = new ExecutionSymbolPage(js);
        MarginSymbolPage tab6 = new MarginSymbolPage(js);
        MarginRatesSymbolPage tab7 = new MarginRatesSymbolPage(js);
        SwapsSymbolPage tab8 = new SwapsSymbolPage(js);
        SessionsSymbolPage tab9 = new SessionsSymbolPage(js);

        // Add the tabs to the tabbed pane
        tabbedPane.addTab("Common", tab1);
        tabbedPane.addTab("Currency", tab2);
        tabbedPane.addTab("Quotes", tab3);
        tabbedPane.addTab("Trade", tab4);
        tabbedPane.addTab("Execution", tab5);
        tabbedPane.addTab("Margin", tab6);
        tabbedPane.addTab("Margin Rates", tab7);
        tabbedPane.addTab("Swaps", tab8);
        tabbedPane.addTab("Sessions", tab9);

        SubmitBtnPanel submitBtnPanel;
        JSplitPane jtabbedAndUpdateContainer;
        if ("Copy".equals(type)) {
            submitBtnPanel = new SubmitBtnPanel("Copy");
            jtabbedAndUpdateContainer = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabbedPane, submitBtnPanel);
            submitDetails(submitBtnPanel, symbolId, tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8, tab9, "new");
        } else {

            submitBtnPanel = new SubmitBtnPanel("Update");
            jtabbedAndUpdateContainer = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabbedPane, submitBtnPanel);
            submitDetails(submitBtnPanel, symbolId, tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8, tab9, "");
        }

        add(jtabbedAndUpdateContainer);
        setSize(750, 500);
        setLocationRelativeTo(null);
//        setVisible(true);
    }

//    
//    public SixTabFrame(String type, String HCategory) {
//        super("Symbol Config");
//
//        CommonSymbolPage tab1 = new CommonSymbolPage();
//        CurrencySymbolPage tab2 = new CurrencySymbolPage();
//        QuotesSymbolPage tab3 = new QuotesSymbolPage();
//        TradeSymbolPage tab4 = new TradeSymbolPage();
//        ExecutionSymbolPage tab5 = new ExecutionSymbolPage();
//        MarginSymbolPage tab6 = new MarginSymbolPage();
//        MarginRatesSymbolPage tab7 = new MarginRatesSymbolPage();
//        SwapsSymbolPage tab8 = new SwapsSymbolPage();
//        SessionsSymbolPage tab9 = new SessionsSymbolPage();
//
//        // Create a tabbed pane
//        JTabbedPane tabbedPane = new JTabbedPane();
//
//        // Add the tabs to the tabbed pane
//        tabbedPane.addTab("Common", tab1);
//        tabbedPane.addTab("Currency", tab2);
//        tabbedPane.addTab("Quotes", tab3);
//        tabbedPane.addTab("Trade", tab4);
//        tabbedPane.addTab("Execution", tab5);
//        tabbedPane.addTab("Margin", tab6);
//        tabbedPane.addTab("Margin Rates", tab7);
//        tabbedPane.addTab("Swaps", tab8);
//        tabbedPane.addTab("Sessions", tab9);
//
//        SubmitBtnPanel submitBtnPanel = new SubmitBtnPanel("Submit");
//
//        JSplitPane js = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabbedPane, submitBtnPanel);
//        submitDetails(submitBtnPanel, "no symbol id here", tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8, tab9);
//        add(js);
//        setSize(750, 500);
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
    public static void main(String[] args) {
//        new SixTabFrame();
    }
}
