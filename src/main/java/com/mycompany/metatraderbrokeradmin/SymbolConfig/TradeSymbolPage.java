/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.metatraderbrokeradmin.SymbolConfig;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author techninza
 */
public class TradeSymbolPage extends javax.swing.JPanel {

    private ArrayList<String> stringarray=new ArrayList();
    
    private void loaddata()
    {
        String text="";
        for(int i=0;i<stringarray.size();i++)
        {
            text+=stringarray.get(i)+",";
        }
        text=text.substring(0, text.length()-1);
        jLabel21.setText(text);
    }
    /**
     * Creates new form TradeSymbolPage
     */
    public TradeSymbolPage() {
        initComponents();
        
        jComboBox8.setEditable(true);
        jComboBox9.setEditable(true);
        jComboBox10.setEditable(true);
        jComboBox11.setEditable(true);
//        jComboBox6.setEditable(true);
        
         jCheckBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean checked = abstractButton.getModel().isSelected();
                if (checked) {
                    stringarray.add("Market");
                    System.out.println("Checkbox is checked.");
                } else {
                    stringarray.remove("Market");
                    System.out.println("Checkbox is unchecked.");
                }
                loaddata();
            }
        });
         
          jCheckBox3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean checked = abstractButton.getModel().isSelected();
                if (checked) {
                    stringarray.add("Limit");
                    System.out.println("Checkbox is checked.");
                } else {
                    stringarray.remove("Limit");
                    System.out.println("Checkbox is unchecked.");
                }
                
                loaddata();
            }
        });
          
           jCheckBox4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean checked = abstractButton.getModel().isSelected();
                if (checked) {
                    stringarray.add("Stop");
                    System.out.println("Checkbox is checked.");
                } else {
                    stringarray.remove("Stop");
                    System.out.println("Checkbox is unchecked.");
                }
                loaddata();
            }
        });
           jCheckBox5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean checked = abstractButton.getModel().isSelected();
                if (checked) {
                    stringarray.add("Stop Limit");
                    System.out.println("Checkbox is checked.");
                } else {
                    stringarray.remove("Stop Limit");
                    System.out.println("Checkbox is unchecked.");
                }
                loaddata();
            }
        });
            jCheckBox6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean checked = abstractButton.getModel().isSelected();
                if (checked) {
                    stringarray.add("Stop Loss");
                    System.out.println("Checkbox is checked.");
                } else {
                    stringarray.remove("Stop Loss");
                    System.out.println("Checkbox is unchecked.");
                }
                
                loaddata();
            }
        });
            
            
             jCheckBox7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean checked = abstractButton.getModel().isSelected();
                if (checked) {
                    stringarray.add("Take Profit");
                    System.out.println("Checkbox is checked.");
                } else {
                    stringarray.remove("Take Profit");
                    System.out.println("Checkbox is unchecked.");
                }
                loaddata();
            }
        });
             
              jCheckBox8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean checked = abstractButton.getModel().isSelected();
                if (checked) {
                    stringarray.add("Close By");
                    System.out.println("Checkbox is checked.");
                } else {
                    stringarray.remove("Close By");
                    System.out.println("Checkbox is unchecked.");
                }
                loaddata();
            }
        });
              
              jCheckBox2.setSelected(true);
              jCheckBox3.setSelected(true);
              jCheckBox4.setSelected(true);
              jCheckBox5.setSelected(true);
              jCheckBox6.setSelected(true);
              jCheckBox7.setSelected(true);
              jCheckBox8.setSelected(true);
              
              
        jComboBox1.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) e.getItem();
                    // Perform actions based on the selected item
                    System.out.println("Selected item: " + selectedItem);

                    if (selectedItem.equalsIgnoreCase("CFD")) {
                        jTextField5.setVisible(true);
                        jTextField6.setVisible(true);
                        jLabel18.setVisible(true);
                        jLabel19.setVisible(true);
                    } else {
                        jTextField5.setVisible(false);
                        jTextField6.setVisible(false);
                        jLabel18.setVisible(false);
                        jLabel19.setVisible(false);
                    }
                }
//                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }

    final String getJSONData(String s, JSONObject js) {
        try {
            return ""+js.getString(s);
        } catch (JSONException e) {
            System.out.println(s + " not found");
            return "";
        }
    }

    public TradeSymbolPage(JSONObject js) {
        initComponents();

        jComboBox8.setEditable(true);
        jComboBox9.setEditable(true);
        jComboBox10.setEditable(true);
        jComboBox11.setEditable(true);
//        jComboBox6.setEditable(true);


jCheckBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean checked = abstractButton.getModel().isSelected();
                if (checked) {
                    stringarray.add("Market");
                    System.out.println("Checkbox is checked.");
                } else {
                    stringarray.remove("Market");
                    System.out.println("Checkbox is unchecked.");
                }
                loaddata();
            }
        });
         
          jCheckBox3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean checked = abstractButton.getModel().isSelected();
                if (checked) {
                    stringarray.add("Limit");
                    System.out.println("Checkbox is checked.");
                } else {
                    stringarray.remove("Limit");
                    System.out.println("Checkbox is unchecked.");
                }
                
                loaddata();
            }
        });
          
           jCheckBox4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean checked = abstractButton.getModel().isSelected();
                if (checked) {
                    stringarray.add("Stop");
                    System.out.println("Checkbox is checked.");
                } else {
                    stringarray.remove("Stop");
                    System.out.println("Checkbox is unchecked.");
                }
                loaddata();
            }
        });
           jCheckBox5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean checked = abstractButton.getModel().isSelected();
                if (checked) {
                    stringarray.add("Stop Limit");
                    System.out.println("Checkbox is checked.");
                } else {
                    stringarray.remove("Stop Limit");
                    System.out.println("Checkbox is unchecked.");
                }
                loaddata();
            }
        });
            jCheckBox6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean checked = abstractButton.getModel().isSelected();
                if (checked) {
                    stringarray.add("Stop Loss");
                    System.out.println("Checkbox is checked.");
                } else {
                    stringarray.remove("Stop Loss");
                    System.out.println("Checkbox is unchecked.");
                }
                
                loaddata();
            }
        });
            
            
             jCheckBox7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean checked = abstractButton.getModel().isSelected();
                if (checked) {
                    stringarray.add("Take Profit");
                    System.out.println("Checkbox is checked.");
                } else {
                    stringarray.remove("Take Profit");
                    System.out.println("Checkbox is unchecked.");
                }
                loaddata();
            }
        });
             
              jCheckBox8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AbstractButton abstractButton = (AbstractButton) e.getSource();
                boolean checked = abstractButton.getModel().isSelected();
                if (checked) {
                    stringarray.add("Close By");
                    System.out.println("Checkbox is checked.");
                } else {
                    stringarray.remove("Close By");
                    System.out.println("Checkbox is unchecked.");
                }
                loaddata();
            }
        });


        
        jTextField1.setText(getJSONData("ContractSize", js));
        jTextField2.setText(getJSONData("StopsLevel", js));
        jTextField3.setText(getJSONData("FreezeLevel", js));
        jTextField4.setText(getJSONData("QuotesTimeout", js));

        if (getJSONData("CalcMode", js).length() > 1) {
            jComboBox1.setSelectedItem(getJSONData("CalcMode", js));
        }
        if (getJSONData("TradeMode", js).length() > 1) {
            jComboBox2.setSelectedItem(getJSONData("TradeMode", js));
        }
        if (getJSONData("GTCMode", js).length() > 1) {
            jComboBox3.setSelectedItem(getJSONData("GTCMode", js));
        }
        if (getJSONData("FillFlags", js).length() > 1) {
            jComboBox4.setSelectedItem(getJSONData("FillFlags", js));
        }
        if (getJSONData("ExpirFlags", js).length() > 1) {
            jComboBox5.setSelectedItem(getJSONData("ExpirFlags", js));
        }
        if (getJSONData("OrderFlags", js).length() > 1) {
//            jComboBox6.setSelectedItem(getJSONData("OrderFlags", js));
            jLabel21.setText(getJSONData("OrderFlags",js));
            
            String x[]=jLabel21.getText().toString().split(",");
            for(int i=0;i<x.length;i++)
            {
                if(x[i].equalsIgnoreCase("Market"))
                {
                    jCheckBox2.setSelected(true);
                }
                if(x[i].equalsIgnoreCase("Limit"))
                {
                    jCheckBox3.setSelected(true);
                }
                if(x[i].equalsIgnoreCase("Stop"))
                {
                    jCheckBox4.setSelected(true);
                }
                if(x[i].equalsIgnoreCase("Stop Limit"))
                {
                    jCheckBox5.setSelected(true);
                }
                if(x[i].equalsIgnoreCase("Stop Loss"))
                {
                    jCheckBox6.setSelected(true);
                }
                if(x[i].equalsIgnoreCase("Take Profit"))
                {
                    jCheckBox7.setSelected(true);
                }
                if(x[i].equalsIgnoreCase("Close By"))
                {
                    jCheckBox8.setSelected(true);
                }
                stringarray.add(x[i]);    
            }
            
            
        }
        if (getJSONData("ConvertProfit", js).length() > 0) {
            jComboBox7.setSelectedItem(getJSONData("ConvertProfit", js));
        }
//        if (getJSONData("MinVolumeTrade", js).length() > 0) {
//            System.out.println(getJSONData("MinVolumeTrade",js));
//            jComboBox8.setSelectedItem(getJSONData("MinVolumeTrade", js));
//        }

           jComboBox8.setSelectedItem(js.getFloat("MinVolumeTrade"));
           jComboBox10.setSelectedItem(js.getFloat("MaxVolumeTrade"));
        if (getJSONData("StepVolumeTrade", js).length() > 0) {
            jComboBox9.setSelectedItem(getJSONData("StepVolumeTrade", js));
        }
//        if (getJSONData("MaxVolumeTrade", js).length() > 0) {
//            jComboBox10.setSelectedItem(getJSONData("MaxVolumeTrade", js));
//        }
        if (getJSONData("LimitVolumeTrade", js).length() > 0) {
            jComboBox11.setSelectedItem(getJSONData("LimitVolumeTrade", js));
        }

        jCheckBox1.setSelected("true".equals(getJSONData("EnableTradingSignalFlag", js)));

    }

    public JComboBox<String> getjComboBox1() {
        return jComboBox1;
    }

    public JComboBox<String> getjComboBox2() {
        return jComboBox2;
    }

    public JComboBox<String> getjComboBox3() {
        return jComboBox3;
    }

    public JComboBox<String> getjComboBox4() {
        return jComboBox4;
    }

    public JComboBox<String> getjComboBox5() {
        return jComboBox5;
    }

    public JLabel getjComboBox6() {
        return jLabel21;
    }

    public JTextField getjTextField1() {
        return jTextField1;
    }

    public JTextField getjTextField2() {
        return jTextField2;
    }

    public JTextField getjTextField3() {
        return jTextField3;
    }

    public JTextField getjTextField4() {
        return jTextField4;
    }

    public JComboBox<String> getjComboBox7() {
        return jComboBox7;
    }

    public JComboBox<String> getjComboBox8() {
        return jComboBox8;
    }

    public JComboBox<String> getjComboBox9() {
        return jComboBox9;
    }

    public JComboBox<String> getjComboBox10() {
        return jComboBox10;
    }

    public JComboBox<String> getjComboBox11() {
        return jComboBox11;
    }

    public JCheckBox getjCheckBox1() {
        return jCheckBox1;
    }
    
    static class CheckBoxListCellRenderer implements ListCellRenderer<String> {
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JCheckBox checkbox = new JCheckBox(value);
            checkbox.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            checkbox.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            checkbox.setSelected(isSelected);
            return checkbox;
        }
    }

    private void setData() {
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Forex",
            "Forex No Leverage",
            "Futures",
            "CFD",
            "CFD Index",
            "CFD Leverage",
            "Exchange Stocks",
            "Exchange MOEX Stocks",
            "Exchange Bonds",
            "Exchange MOEX Bonds",
            "Exchange Futures",
            "Exchange FORTS Futures",
            "Exchange Options",
            "Collateral"}));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Disabled",
            "Long only",
            "Short only",
            "Close only",
            "Full access"}));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Good till cancelled",
            "Good till today Including SL/TP",
            "Good till today excluding SL/TP"}));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Fill or Kill",
            "Immediate or Cancel",
            "Book or Cancel"}));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Good till cancelled",
            "Day",
            "Specified time",
            "Specified day"}));
        
//        jComboBox6.setRenderer(new CheckBoxListCellRenderer());

//        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Market",
//            "Limit",
//            "Stop",
//            "Stop Limit",
//            "Stop Loss",
//            "Take Profit",
//            "Close By"}));
//        
//        jComboBox6.setEditable(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jComboBox10 = new javax.swing.JComboBox<>();
        jComboBox11 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();

        jLabel1.setText("The setting up of symbol trading. Please specify the type of profit calculation, type of placed orders, allowed trade volumes, etc.");

        jLabel2.setText("Contract Size:");

        jLabel3.setText("Calculation:");

        jLabel4.setText("Trade :");

        jLabel5.setText("GTC :");

        jLabel6.setText("Filing:");

        jLabel7.setText("Expiration:");

        jLabel8.setText("Orders:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Forex", "Forex No Leverage", "Futures", "CFD", "CFD Index", "CFD Leverage", "Exchange Stocks", "Exchange MOEX Stocks", "Exchange Bonds", "Exchange MOEX Bonds", "Exchange Futures", "Exchange FORTS Futures", "Exchange Options", "Collateral" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disabled", "Long only", "Short only", "Close only", "Full access" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good till today including SL/TP", "Good till canceled", "Good till today excluding SL/TP" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fill or Kill", "Immediate or Cancel", "Book or Cancel", "Return" }));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Good till canceled", "Day", "Specified time", "Specified day" }));

        jLabel9.setText("Limit & Stop Level:");

        jLabel10.setText("Freeze Level:");

        jLabel11.setText("Convert Profit : ");

        jLabel12.setText("Max Quote Delay:");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "by deal", "by market" }));

        jCheckBox1.setText("Enable Trading Signals");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel13.setText("Volumes");

        jLabel14.setText("Minimum");

        jLabel15.setText("Step");

        jLabel16.setText("Maximum");

        jLabel17.setText("Limit");

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0.01", "1", "10", "100" }));

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0.01", "1", "10", "100" }));
        jComboBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox9ActionPerformed(evt);
            }
        });

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "10", "100", "1000" }));

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "10", "100", "1000" }));

        jLabel18.setText("Tick Size");

        jLabel19.setText("Tick Value");

        jLabel21.setText("jLabel21");

        jCheckBox2.setText("Market");

        jCheckBox3.setText("Limit");

        jCheckBox4.setText("Stop");

        jCheckBox5.setText("Stop Limit");

        jCheckBox6.setText("Stop Loss");

        jCheckBox7.setText("Take Profit");

        jCheckBox8.setText("Close By");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(70, 70, 70)
                                            .addComponent(jLabel11))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(52, 52, 52)
                                            .addComponent(jLabel9)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32)
                                                .addComponent(jLabel18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(42, 42, 42)
                                                .addComponent(jLabel19))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel10))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(69, 69, 69)
                                                    .addComponent(jLabel12))))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextField2)
                                                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(264, 264, 264)
                                        .addComponent(jCheckBox1))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jLabel8))
                                        .addComponent(jLabel7)))
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox8))
                            .addComponent(jLabel13)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jCheckBox2))
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox3)
                            .addComponent(jCheckBox4)
                            .addComponent(jCheckBox5)
                            .addComponent(jCheckBox6)
                            .addComponent(jCheckBox7)
                            .addComponent(jCheckBox8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jLabel21)
                .addGap(5, 5, 5)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jLabel16)
                        .addComponent(jLabel17)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jComboBox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox9ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
