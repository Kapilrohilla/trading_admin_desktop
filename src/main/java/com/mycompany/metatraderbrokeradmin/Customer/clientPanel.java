package com.mycompany.metatraderbrokeradmin.Customer;

import com.mycompany.metatraderbrokeradmin.GroupSymbolpopup.PopupTree4Group;
import com.mycompany.metatraderbrokeradmin.SymbolConfig.SixTabFrame;
import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import com.mycompany.metatraderbrokeradmin.Utility.DatePicker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author kapilrohilla
 */
public class clientPanel extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public clientPanel() {
        initComponents();

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Btn clicked");
                    String type = jComboBox1.getSelectedItem().toString();
                    String status = jComboBox2.getSelectedItem().toString();
                    String kycStatus = jComboBox3.getSelectedItem().toString();
                    String assignetManager = jComboBox4.getSelectedItem().toString();
                    String preferTradingGroup = jButton3.getText();
                    String approvedBy = jComboBox6.getSelectedItem().toString();
                    String approvalDate = jButton1.getText();
                    String closeDate = jButton2.getText();
                    String leadSource = jTextField1.getText();
                    String leadCampaign = jTextField2.getText();
                    String introducer = jTextField3.getText();

                    JSONObject js = new JSONObject();
                    js.put("type", type);
                    js.put("status", status);
                    js.put("kycStatus", kycStatus);
                    js.put("assignManager", assignetManager);
                    js.put("preferredTradingGroup", preferTradingGroup);
                    js.put("approvedBy", approvedBy);
                    js.put("approvalDate", approvalDate);
                    js.put("closeDate", closeDate);
                    js.put("leadSource", leadSource);
                    js.put("leadCampaign", leadCampaign);
                    js.put("introducer", introducer);
                    postData(js);
                } catch (JSONException | IOException ex) {
                    Logger.getLogger(SixTabFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    final String getDataFromJSON(String field, JSONObject json) {
        try {
            return json.getString(field);
        } catch (JSONException | NullPointerException ex) {
            return "";
        }
    }

    public clientPanel(JSONObject clientDetails) {
        initComponents();
        if (getDataFromJSON("type", clientDetails).length() > 0) {
            jComboBox1.setSelectedItem(getDataFromJSON("type", clientDetails));
        }
        if (getDataFromJSON("status", clientDetails).length() > 0) {

            jComboBox2.setSelectedItem(getDataFromJSON("status", clientDetails));
        }
        if (getDataFromJSON("kycStatus", clientDetails).length() > 0) {
            jComboBox3.setSelectedItem(getDataFromJSON("kycStatus", clientDetails));
        }
        if (getDataFromJSON("assignManager", clientDetails).length() > 0) {
            jComboBox4.setSelectedItem(getDataFromJSON("assignManager", clientDetails));
        }
        if (getDataFromJSON("preferredTradingGroup", clientDetails).length() > 0) {
            jButton3.setText(getDataFromJSON("preferredTradingGroup", clientDetails));
        }
        if (getDataFromJSON("approvedBy", clientDetails).length() > 0) {
            jComboBox6.setSelectedItem(getDataFromJSON("approvedBy", clientDetails));
        }
        if (getDataFromJSON("approvalDate", clientDetails).length() > 0) {
            jButton1.setText(getDataFromJSON("approvalDate", clientDetails));
        }
        if (getDataFromJSON("closeDate", clientDetails).length() > 0) {
            jButton2.setText(getDataFromJSON("closeDate", clientDetails));
        }
        if (getDataFromJSON("leadSource", clientDetails).length() > 0) {
            jTextField1.setText(getDataFromJSON("leadSource", clientDetails));
        }
        if (getDataFromJSON("leadCampaign", clientDetails).length() > 0) {
            jTextField2.setText(getDataFromJSON("leadCampaign", clientDetails));
        }
        if (getDataFromJSON("introducer", clientDetails).length() > 0) {
            jTextField3.setText(getDataFromJSON("introducer", clientDetails));
        }
        System.out.println("no error found...");

        submitBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String type = jComboBox1.getSelectedItem().toString();
                    String status = jComboBox2.getSelectedItem().toString();
                    String kycStatus = jComboBox3.getSelectedItem().toString();
                    String assignetManager = jComboBox4.getSelectedItem().toString();
                    String preferTradingGroup = jButton3.getText();
                    String approvedBy = jComboBox6.getSelectedItem().toString();
                    String approvalDate = jButton1.getText();
                    String closeDate = jButton2.getText();
                    String leadSource = jTextField1.getText();
                    String leadCampaign = jTextField2.getText();
                    String introducer = jTextField3.getText();
                    System.out.println("introducer = " + introducer);
                    System.out.println("lead camapign = " + leadCampaign);

                    JSONObject js = new JSONObject();
                    js.put("clientId", clientDetails.getString("_id"));
                    js.put("type", type);
                    js.put("status", status);
                    js.put("kycStatus", kycStatus);
                    js.put("assignManager", assignetManager);
                    js.put("preferredTradingGroup", preferTradingGroup);
                    js.put("approvedBy", approvedBy);
                    js.put("approvalDate", approvalDate);
                    js.put("closeDate", closeDate);
                    js.put("leadSource", leadSource);
                    js.put("leadCampaign", leadCampaign);
                    js.put("introducer", introducer);
                    System.out.println(js);
                    postData(js);
                } catch (JSONException | IOException ex) {
                    Logger.getLogger(SixTabFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    void postData(JSONObject js) throws IOException {
        System.out.println("json data we send = " + js);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String apiUrl = APIs.UPDATE_CLIENT;
        RequestBody body = RequestBody.create(JSON, js.toString());
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .build();

        Response res = client.newCall(req).execute();

        if (res.isSuccessful()) {
            System.out.println("Data Submitted");
            System.out.println(res.body().toString());
        } else {
            System.out.println("Submit Request failed with " + res.code());
        }
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
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        submitBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Client");

        jLabel2.setText("Type");

        jComboBox1.setBackground(new java.awt.Color(225, 225, 225));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Individual" }));

        jLabel3.setText("Status");

        jComboBox2.setBackground(new java.awt.Color(225, 225, 225));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Approved" }));

        jLabel4.setText("KYC status");

        jComboBox3.setBackground(new java.awt.Color(225, 225, 225));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Approved" }));

        jLabel5.setText("Assignet manager");

        jComboBox4.setBackground(new java.awt.Color(225, 225, 225));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1030", "Kate Wilson" }));

        jLabel6.setText("Prefer trading group");

        jLabel7.setText("Approved by");

        jComboBox6.setBackground(new java.awt.Color(225, 225, 225));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1000 First Admin" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jLabel9.setText("Approval date");

        jLabel10.setText("Close date");

        jLabel11.setText("Lead Source");

        jTextField1.setText("www.abcbroker.com");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel12.setText("Lead campaign");

        jTextField2.setText("preliminary-desktop");

        jLabel13.setText("Introducer");

        jTextField3.setText("Kate Wilson");

        submitBtn.setText("Submit");

        jButton1.setText("Choose Date");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Choose Date");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Choose Group");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, 158, Short.MAX_VALUE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.Alignment.LEADING, 0, 198, Short.MAX_VALUE)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitBtn)
                .addContainerGap(40, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton1.setText(new DatePicker(this).setPickedDate());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jButton2.setText(new DatePicker(this).setPickedDate());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new PopupTree4Group("clientpanel").setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public static final javax.swing.JButton jButton3 = new javax.swing.JButton();
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
