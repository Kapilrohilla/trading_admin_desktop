/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin.GroupConfig;

import java.awt.FlowLayout;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Kapil
 */
public class AddNewSymbol extends javax.swing.JDialog {

    Vector<String> symbols = new Vector<>();
    NewSymbolCommon common;
    GroupTradeSymbolPage trade;
//    GroupTradeExecutionPage execution;
//    GroupTradeMarginPage marginP;
//    GroupTradeMarginRates marginRatesP;
    GroupTradeSwapPage swapP;
    JSONObject symbolData = new JSONObject();
//    JSONObject allsymbolData = new JSONObject();

    public AddNewSymbol() {
        initComponents();

        setModal(true);
//        String apiData = getData();
//        System.out.println("apiData: " + apiData);
//        JSONObject json = new JSONObject(apiData);
//        JSONArray js = json.getJSONArray("message");
//        for (int i = 0; i < js.length(); i++) {
////            arr.add(js.getJSONObject(i));
//        }
//        for (int i = 0; i < js.length(); i++) {
//
//            JSONObject jso = js.getJSONObject(i);
//            System.out.println("jso: " + jso);
//            String symbol = jso.getString("Symbol");
//            System.out.println("symbol: " + symbol);
//            symbols.add(symbol);
//        }

        JTabbedPane jt = new JTabbedPane();
        common = new NewSymbolCommon();
        trade = new GroupTradeSymbolPage();
//        execution = new GroupTradeExecutionPage();
//        marginP = new GroupTradeMarginPage();
//        marginRatesP = new GroupTradeMarginRates();
        swapP = new GroupTradeSwapPage();
        jt.addTab("Common", common);
        jt.addTab("Trade", trade);
//        jt.addTab("Execution", execution);
//        jt.addTab("Margin", marginP);
//        jt.addTab("Margin Rates", marginRatesP);
        jt.addTab("Swap", swapP);
        setLayout(new FlowLayout());

        Okcancel actionBtns = new Okcancel();
        JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jt, actionBtns);

        getContentPane().add(jsp);
        actionBtns.getjButton1().addActionListener((e) -> {
//            System.out.println("submit btn clicked");
//            String selectedSymbol = NewSymbolCommon.jLabel12.getText();
//            String[] data2DdisplayOnSymbolsTable = {selectedSymbol};
//            SymbolsGroupConfig.symbolsTableData.add(data2DdisplayOnSymbolsTable);
//            SymbolsGroupConfig.model.addRow(data2DdisplayOnSymbolsTable);
//            System.out.println("selected symbol: " + selectedSymbol);
            retrieveInfo();
            String symbolName = common.getjLabel12().getText();

            Vector<String> dataInTable = new Vector<>();
            dataInTable.add(symbolName);
            SymbolsGroupConfig.symbolsTableData.put(symbolData);
            String a[] = symbolName.split("/");
            System.out.println(a[a.length - 1].length());
            if (a[a.length - 1].length() == 1) {
                System.out.println("here");
                retrieveInfoall();
            } else {
                SymbolsGroupConfig.symbolTableData2.put(symbolData);
            }
            SymbolsGroupConfig.model.addRow(dataInTable);
            dispose();
        });

        actionBtns.getjButton2().addActionListener((e) -> {
            System.out.println("cancel btn clicked");
            dispose();
        });
        setSize(1000, 540);
        setLocationRelativeTo(null);
        setVisible(true);

//        pack();
    }
//
//    public AddNewSymbol(JSONObject prevSymbolData) {
//        initComponents();
//
//        String apiData = getData();
//        System.out.println("aaaaaaaaaaaa");
//        System.out.println(prevSymbolData);
//        System.out.println("aaaaaaaaaaaa");
////        System.out.println("apiData: " + apiData);
//        JSONObject json = new JSONObject(apiData);
//        JSONArray js = json.getJSONArray("message");
//        for (int i = 0; i < js.length(); i++) {
////            arr.add(js.getJSONObject(i));
//        }
//        for (int i = 0; i < js.length(); i++) {
//            JSONObject jso = js.getJSONObject(i);
//            System.out.println("jso: " + jso);
//            String symbol = jso.getString("Symbol");
//            System.out.println("symbol: " + symbol);
//            symbols.add(symbol);
//        }
//
//        JTabbedPane jt = new JTabbedPane();
//        common = new NewSymbolCommon(prevSymbolData);
//        trade = new GroupTradeSymbolPage();
//        execution = new GroupTradeExecutionPage();
//        marginP = new GroupTradeMarginPage();
//        marginRatesP = new GroupTradeMarginRates();
//        swapP = new GroupTradeSwapPage();
//        jt.addTab("Common", common);
//        jt.addTab("Trade", trade);
//        jt.addTab("Execution", execution);
//        jt.addTab("Margin", marginP);
//        jt.addTab("Margin Rates", marginRatesP);
//        jt.addTab("Swap", swapP);
//        setLayout(new FlowLayout());
//
//        Okcancel actionBtns = new Okcancel();
//        JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jt, actionBtns);
//
//        getContentPane().add(jsp);
//        actionBtns.getjButton1().addActionListener((e) -> {
//            retrieveInfo();
//            String symbolName = common.getjLabel12().getText();
//
//            Vector<String> dataInTable = new Vector<>();
//            dataInTable.add(symbolName);
//            SymbolsGroupConfig.symbolTableData2.add(symbolData);
//            SymbolsGroupConfig.model.addRow(dataInTable);
//
////            String[] data2DdisplayOnSymbolsTable = {selectedSymbol};
////            SymbolsGroupConfig.symbolsTableData.add(data2DdisplayOnSymbolsTable);
//////            SymbolsGroupConfig.model.setValueAt(index,);
////            int columnCountInModel = SymbolsGroupConfig.model.getColumnCount();
//////            for (int i = 0; i < columnCountInModel; i++) {
//////                SymbolsGroupConfig.model.setValueAt(data2DdisplayOnSymbolsTable[0], index, i);
//////            }
////            for (int data2Update = 0; data2Update < data2DdisplayOnSymbolsTable.length; data2Update++) {
////                for (int i = 0; i < columnCountInModel; i++) {
////                    try {
////                        SymbolsGroupConfig.model.setValueAt(data2DdisplayOnSymbolsTable[i], index, i);
////                    } catch (IndexOutOfBoundsException ex) {
////                        System.out.println("exception occurred during data update in symbolTable");
////                    }
////
////                }
////            }
////            SymbolsGroupConfig.symbolsTableData.set(index, data2DdisplayOnSymbolsTable);
//            //  SymbolsGroupConfig.model.ad(data2DdisplayOnSymbolsTable);
////            SymbolsGroupConfig.model.addRow(data2DdisplayOnSymbolsTable);
////            System.out.println("submit btn clicked");
////            String selectedSymbol = common.getjComboBox1().getSelectedItem() + "";
////            String[] data2DdisplayOnSymbolsTable = {selectedSymbol};
////            SymbolsGroupConfig.symbolsTableData.add(data2DdisplayOnSymbolsTable);
////            SymbolsGroupConfig.model.addRow(data2DdisplayOnSymbolsTable);
//////            System.out.println("updated array = " + SymbolsGroupConfig.symbolsTableData);
////            System.out.println("selected symbol: " + selectedSymbol);
//        });
//
//        actionBtns.getjButton2().addActionListener((e) -> {
//            System.out.println("cancel btn clicked");
//            dispose();
//        });
//        setLocationRelativeTo(null);
//        setVisible(true);
//
//        pack();
//    }

    public AddNewSymbol(JSONObject prevSymbolData) {
        initComponents();
        setModal(true);
//        String apiData = getData();
//        System.out.println("apiData: " + apiData);
//        JSONObject json = new JSONObject(apiData);
//        JSONArray js = json.getJSONArray("message");
//        for (int i = 0; i < js.length(); i++) {
////            arr.add(js.getJSONObject(i));
//        }
//        for (int i = 0; i < js.length(); i++) {
//
//            JSONObject jso = js.getJSONObject(i);
//            System.out.println("jso: " + jso);
//            String symbol = jso.getString("Symbol");
//            System.out.println("symbol: " + symbol);
//            symbols.add(symbol);
//        }

        JTabbedPane jt = new JTabbedPane();
        common = new NewSymbolCommon(prevSymbolData);
        trade = new GroupTradeSymbolPage(prevSymbolData);
//        execution = new GroupTradeExecutionPage(prevSymbolData);
//        marginP = new GroupTradeMarginPage();
//        marginRatesP = new GroupTradeMarginRates();
        swapP = new GroupTradeSwapPage();
        jt.addTab("Common", common);
        jt.addTab("Trade", trade);
//        jt.addTab("Execution", execution);
//        jt.addTab("Margin", marginP);
//        jt.addTab("Margin Rates", marginRatesP);
        jt.addTab("Swap", swapP);
        setLayout(new FlowLayout());

        Okcancel actionBtns = new Okcancel();
        JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jt, actionBtns);

        getContentPane().add(jsp);
        actionBtns.getjButton1().addActionListener((e) -> {
            retrieveInfo();

            String symbolName = common.getjLabel12().getText();

            Vector<String> dataInTable = new Vector<>();
            dataInTable.add(symbolName);
            SymbolsGroupConfig.symbolsTableData.put(symbolData);
//            String a[]=symbolName.split("/");
//            System.out.println(a[a.length-1].length());
//            if(a[a.length-1].length()==1)
//            {
//                System.out.println("here");
//                retrieveInfoall();
//            }
//            else {
//                SymbolsGroupConfig.symbolTableData2.put(symbolData);
//            }
//            SymbolsGroupConfig.symbolTableData2.add(symbolData);
            SymbolsGroupConfig.model.addRow(dataInTable);
            dispose();
        });

        actionBtns.getjButton2().addActionListener((e) -> {
            System.out.println("cancel btn clicked");
            dispose();
        });
        setSize(1000, 540);
        setLocationRelativeTo(null);
        setVisible(true);

//        pack();
    }

    private void retrieveInfoall() {
        // commonPanel
        String symbolname = common.getjLabel12().getText();
        if (symbolname.contains("*")) {
            for (int jk = 0; jk < NewSymbolCommon.categorylistsymbol.size(); jk++) {
                String symbolname1 = NewSymbolCommon.categorylistsymbol.get(jk);
                String cAsk = common.getAskTextField().getText();
                String cBid = common.getBidTextField().getText();
                JSONObject spreadBalance = new JSONObject();
                spreadBalance.put("ask", Integer.parseInt(cAsk));
                spreadBalance.put("bid", Integer.parseInt(cBid));
                boolean useDefaultPanelSettings = common.getjCheckBox5().isSelected();
//        boolean enableMarketDepthFlag = common.getjCheckBox1().isSelected();
//        boolean useDefaultSpreadFlag = common.getjCheckBox2().isSelected();
//        boolean useDefaultVolumeFlag = common.getjCheckBox3().isSelected();
                String marketDepthLimit = common.getjComboBox2().getSelectedItem().toString();
                String minVolume = common.getjComboBox4().getSelectedItem().toString();
                String stepVolume = common.getjComboBox5().getSelectedItem().toString();
                String maxVolume = common.getjComboBox6().getSelectedItem().toString();
                String limitVolume = common.getjComboBox7().getSelectedItem().toString();

                // Trade panel
                String tradeSettings = trade.getjComboBox2().getSelectedItem().toString();
                String others = trade.getjComboBox6().getSelectedItem().toString();

                // execution
//        String executionType = execution.getjComboBox1().getSelectedItem().toString();
//        String maxTimeDeviation = execution.getjComboBox2().getSelectedItem().toString();
//        String maxProfitDeviation = execution.getjComboBox3().getSelectedItem().toString();
//        String maxLosingDeviaiton = execution.getjComboBox4().getSelectedItem().toString();
//        boolean fastConfirmationOfQuotes = execution.getjCheckBox1().isSelected();
//        String execMaxVolume = execution.getjTextField1().getText();
//
//        // margin
//        String initialMargin = marginP.getjTextField1().getText();
//        String hedgedMargin = marginP.getjTextField2().getText();
//        String maintainanceMargin = marginP.getjTextField3().getText();
//        boolean calcHedgedMarginFlag = marginP.getjCheckBox1().isSelected();
//        boolean execLongPosFlag = marginP.getjCheckBox2().isSelected();
//        boolean reCalcMarginFlag = marginP.getjCheckBox3().isSelected();
//        String additionalMarginChecks = marginP.getjComboBox1().getSelectedItem().toString();
                // margin rates
//        String liqMr = marginRatesP.getjTextField1().getText();
//        String currencyMr = marginRatesP.getjTextField2().getText();
//        var tablemodel = marginRatesP.getjTable1().getModel();
//        JSONArray marginRatesTable = new JSONArray();
//        for (int i = 0; i < tablemodel.getRowCount(); i++) {
//            JSONArray jsa = new JSONArray();
//            for (int j = 0; j < tablemodel.getColumnCount(); j++) {
//                jsa.put(tablemodel.getValueAt(i, j));
//            }
//        }
                // tradeSwap
                boolean enableSwapFlag = swapP.getjCheckBox1().isSelected();
//        boolean useDefaultSwapFlag = swapP.getjCheckBox3().isSelected();
                String swapType = swapP.getjComboBox1().getSelectedItem().toString();
                String longPosition = swapP.getjTextField1().getText();
                String shortPositions = swapP.getjTextField2().getText();
                String daysInYear = swapP.getjComboBox2().getSelectedItem().toString();
                boolean autoConsiderHolidayFlag = swapP.getjCheckBox2().isSelected();
                JSONArray swapMultiTable = new JSONArray();
                var swapTableModel = swapP.getjTable2().getModel();
                for (int i = 0; i < swapTableModel.getRowCount(); i++) {
                    JSONArray jsa = new JSONArray();
                    for (int j = 0; j < swapTableModel.getColumnCount(); j++) {
                        jsa.put(swapTableModel.getValueAt(i, j));
                    }
                }

                JSONObject jsonsymbol = new JSONObject();
                jsonsymbol.put("Symbol", symbolname1); //d
                jsonsymbol.put("SpreadBalance", spreadBalance); //d
                jsonsymbol.put("useDefaultPanelSettings", useDefaultPanelSettings);//n
//        jsonsymbol.put("enableMarketDepthFlag", enableMarketDepthFlag);//n
//        jsonsymbol.put("useDefaultSpreadFlag", useDefaultSpreadFlag);//n
//        jsonsymbol.put("useDefaultVolumeFlag", useDefaultVolumeFlag);//n
                jsonsymbol.put("MarketDepth", marketDepthLimit);//d
                jsonsymbol.put("MinVolumeTrade", minVolume);//d
                jsonsymbol.put("StepVolumeTrade", stepVolume);//d
                jsonsymbol.put("MaxVolumeTrade", maxVolume);//d
                jsonsymbol.put("LimitVolumeTrade", limitVolume);//d

//        jsonsymbol.put("Execution", executionType);//d
//        jsonsymbol.put("MaxTimeDeviation", maxTimeDeviation);//d
//        jsonsymbol.put("MaxProfitDeviation", maxProfitDeviation);//d
//        jsonsymbol.put("MaxLosingDeviation", maxLosingDeviaiton);//d
//        jsonsymbol.put("FastConfirmationFlag", fastConfirmationOfQuotes);//d
//        jsonsymbol.put("maxVolume", execMaxVolume); //d
//        jsonsymbol.put("MarginInitial", initialMargin); //d
//        jsonsymbol.put("MarginHedge", hedgedMargin); //d
//        jsonsymbol.put("MarginMaintenance", maintainanceMargin);
//        jsonsymbol.put("CalculateHedgeMarginFlag", calcHedgedMarginFlag);//d
//        jsonsymbol.put("ExecLongPositionHedgeFlag", execLongPosFlag);//d
//        jsonsymbol.put("ReCalculateMarginFlag", reCalcMarginFlag);//d
//        jsonsymbol.put("AdditionalMarginChecks", additionalMarginChecks);//d
//        jsonsymbol.put("MarginLiquidity", liqMr);//d
//        jsonsymbol.put("MarginCurrency", currencyMr);//d
//        jsonsymbol.put("MarginRatesTable", marginRatesTable.toString());//d
                jsonsymbol.put("EnableSwapsFlag", enableSwapFlag);//d
                jsonsymbol.put("SwapLong", longPosition); //d
                jsonsymbol.put("SwapShort", shortPositions);//d

                jsonsymbol.put("SwapMode", swapType); //d
                jsonsymbol.put("SwapYearDay", daysInYear);//d
                jsonsymbol.put("AutoConsiderHolidayFlag", autoConsiderHolidayFlag);//d
                jsonsymbol.put("SwapMultiplyTable", swapMultiTable.toString()); //d

                jsonsymbol.put("TradeMode", tradeSettings);//d
                jsonsymbol.put("OrderFlags", others);//d

                SymbolsGroupConfig.symbolTableData2.put(jsonsymbol);
            }
        }

    }

    private void retrieveInfo() {
        // commonPanel
        String symbolname = common.getjLabel12().getText();
        String cAsk = common.getAskTextField().getText();
        String cBid = common.getBidTextField().getText();
        JSONObject spreadBalance = new JSONObject();
        try {
            float c1 = Float.parseFloat(cAsk);
            float b1 = Float.parseFloat(cBid);

            spreadBalance.put("ask", c1);
            spreadBalance.put("bid", b1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        boolean useDefaultPanelSettings = common.getjCheckBox5().isSelected();
//        boolean enableMarketDepthFlag = common.getjCheckBox1().isSelected();
//        boolean useDefaultSpreadFlag = common.getjCheckBox2().isSelected();
//        boolean useDefaultVolumeFlag = common.getjCheckBox3().isSelected();
        String marketDepthLimit = common.getjComboBox2().getSelectedItem().toString();
        String minVolume = common.getjComboBox4().getSelectedItem().toString();
        String stepVolume = common.getjComboBox5().getSelectedItem().toString();
        String maxVolume = common.getjComboBox6().getSelectedItem().toString();
        String limitVolume = common.getjComboBox7().getSelectedItem().toString();

        // Trade panel
        String tradeSettings = trade.getjComboBox2().getSelectedItem().toString();
        String others = trade.getjComboBox6().getSelectedItem().toString();

        // execution
//        String executionType = execution.getjComboBox1().getSelectedItem().toString();
//        String maxTimeDeviation = execution.getjComboBox2().getSelectedItem().toString();
//        String maxProfitDeviation = execution.getjComboBox3().getSelectedItem().toString();
//        String maxLosingDeviaiton = execution.getjComboBox4().getSelectedItem().toString();
//        boolean fastConfirmationOfQuotes = execution.getjCheckBox1().isSelected();
//        String execMaxVolume = execution.getjTextField1().getText();
//
//        // margin
//        String initialMargin = marginP.getjTextField1().getText();
//        String hedgedMargin = marginP.getjTextField2().getText();
//        String maintainanceMargin = marginP.getjTextField3().getText();
//        boolean calcHedgedMarginFlag = marginP.getjCheckBox1().isSelected();
//        boolean execLongPosFlag = marginP.getjCheckBox2().isSelected();
//        boolean reCalcMarginFlag = marginP.getjCheckBox3().isSelected();
//        String additionalMarginChecks = marginP.getjComboBox1().getSelectedItem().toString();
//        
        // margin rates
//        String liqMr = marginRatesP.getjTextField1().getText();
//        String currencyMr = marginRatesP.getjTextField2().getText();
//        
//        var tablemodel = marginRatesP.getjTable1().getModel();
//        JSONArray marginRatesTable = new JSONArray();
//        for (int i = 0; i < tablemodel.getRowCount(); i++) {
//            JSONArray jsa = new JSONArray();
//            for (int j = 0; j < tablemodel.getColumnCount(); j++) {
//                jsa.put(tablemodel.getValueAt(i, j));
//            }
//        }
        // tradeSwap
        boolean enableSwapFlag = swapP.getjCheckBox1().isSelected();
//        boolean useDefaultSwapFlag = swapP.getjCheckBox3().isSelected();
        String swapType = swapP.getjComboBox1().getSelectedItem().toString();
        String longPosition = swapP.getjTextField1().getText();
        String shortPositions = swapP.getjTextField2().getText();
        String daysInYear = swapP.getjComboBox2().getSelectedItem().toString();
        boolean autoConsiderHolidayFlag = swapP.getjCheckBox2().isSelected();
        JSONArray swapMultiTable = new JSONArray();
        var swapTableModel = swapP.getjTable2().getModel();
        for (int i = 0; i < swapTableModel.getRowCount(); i++) {
            JSONArray jsa = new JSONArray();
            for (int j = 0; j < swapTableModel.getColumnCount(); j++) {
                jsa.put(swapTableModel.getValueAt(i, j));
            }
        }

        symbolData.put("Symbol", symbolname); //d
        symbolData.put("SpreadBalance", spreadBalance); //d
        symbolData.put("useDefaultPanelSettings", useDefaultPanelSettings);//n
//        symbolData.put("enableMarketDepthFlag", enableMarketDepthFlag);//n
//        symbolData.put("useDefaultSpreadFlag", useDefaultSpreadFlag);//n
//        symbolData.put("useDefaultVolumeFlag", useDefaultVolumeFlag);//n
        symbolData.put("MarketDepth", marketDepthLimit);//d
        symbolData.put("MinVolumeTrade", minVolume);//d
        symbolData.put("StepVolumeTrade", stepVolume);//d
        symbolData.put("MaxVolumeTrade", maxVolume);//d
        symbolData.put("LimitVolumeTrade", limitVolume);//d

//        symbolData.put("Execution", executionType);//d
//        symbolData.put("MaxTimeDeviation", maxTimeDeviation);//d
//        symbolData.put("MaxProfitDeviation", maxProfitDeviation);//d
//        symbolData.put("MaxLosingDeviation", maxLosingDeviaiton);//d
//        symbolData.put("FastConfirmationFlag", fastConfirmationOfQuotes);//d
//        symbolData.put("maxVolume", execMaxVolume); //d
//        symbolData.put("MarginInitial", initialMargin); //d
//        symbolData.put("MarginHedge", hedgedMargin); //d
//        symbolData.put("maintainanceMargin", maintainanceMargin);
//        symbolData.put("CalculateHedgeMarginFlag", calcHedgedMarginFlag);//d
//        symbolData.put("ExecLongPositionHedgeFlag", execLongPosFlag);//d
//        symbolData.put("ReCalculateMarginFlag", reCalcMarginFlag);//d
//        symbolData.put("AdditionalMarginChecks", additionalMarginChecks);//d
//        symbolData.put("MarginLiquidity", liqMr);//d
//        symbolData.put("MarginCurrency", currencyMr);//d
//        symbolData.put("MarginRatesTable", marginRatesTable.toString());//d
        symbolData.put("EnableSwapsFlag", enableSwapFlag);//d
        symbolData.put("SwapLong", longPosition); //d
        symbolData.put("SwapShort", shortPositions);//d

        symbolData.put("SwapMode", swapType); //d
        symbolData.put("SwapYearDay", daysInYear);//d
        symbolData.put("AutoConsiderHolidayFlag", autoConsiderHolidayFlag);//d
        symbolData.put("SwapMultiplyTable", swapMultiTable.toString()); //d

        symbolData.put("TradeMode", tradeSettings);//d
        symbolData.put("OrderFlags", others);//d

    }

    final String getData() {
        OkHttpClient client = new OkHttpClient();
        // groups/symbol
        String symbolApiUrl = "http://65.0.59.137:8080/get-all-symbols?timestamp=" + "10";
        Request symbolRequest = new Request.Builder()
                .url(symbolApiUrl)
                .build();
        Call call2 = client.newCall(symbolRequest);
        try {
            Response symbolResponse = call2.execute();
            String groupSymbol = symbolResponse.body().string();
            String returns = groupSymbol;
            return returns;
        } catch (IOException e) {
            System.out.println("group config");

            return "";
        }
    }

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
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AddNewSymbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AddNewSymbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AddNewSymbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AddNewSymbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
////        java.awt.EventQueue.invokeLater(new Runnable() {
////            public void run() {
////                new AddNewSymbol().setVisible(true);
////            }
////        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
