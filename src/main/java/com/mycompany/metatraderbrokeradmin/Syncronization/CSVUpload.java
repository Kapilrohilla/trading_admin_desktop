package com.mycompany.metatraderbrokeradmin.Syncronization;

import com.mycompany.metatraderbrokeradmin.Utility.APIs;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author Lenovo
 */
public class CSVUpload extends javax.swing.JPanel {

    String filePath;
    String type;

    public CSVUpload(String type) {
        this.type = type;
        if (type == "csv") {
            jTextField1.setVisible(false);
        }
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setText("Please Upload CSV File  Here : ");

        jButton1.setText("Select File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Upload File");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("File Selected : ");

        jLabel3.setText("Symbol:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jTextField1))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton2)))
                                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel1)
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap(172, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser chooser = new JFileChooser();
//    FileNameExtensionFilter filter = new FileNameExtensionFilter(
//        "JPG & GIF Images", "jpg", "gif");
//    chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            filePath = chooser.getSelectedFile().getAbsolutePath();
            jLabel2.setText("File Selected " + chooser.getSelectedFile().getName());

        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
//        System.out.println("filePath: " + new File(filePath));
        OkHttpClient client = new OkHttpClient();

        if (type == "csv") {
            String symbolName = jTextField1.getText();

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("csv", filePath,
                            RequestBody.create(MediaType.parse("application/octet-stream"), new File(filePath)))
                    .addFormDataPart("name", symbolName)
                    .build();

            Request request = new Request.Builder()
                    .url(APIs.UPLOAD_CSV)
                    .post(requestBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                System.out.println("response: " + response.body().string());
            } catch (IOException exception) {
                System.out.println("Error: while uploading the CSV: " + exception.getMessage());
            }
        } else {
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("json", filePath,
                            RequestBody.create(MediaType.parse("application/octet-stream"), new File(filePath)))
                    .build();
            String url = type == "csv" ? APIs.UPLOAD_CSV : APIs.UPLOAD_JSON;
            System.out.println("url: " + url);
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                System.out.println("response: " + response.body().string());
            } catch (IOException exception) {
                System.out.println("Error: while uploading the CSV: " + exception.getMessage());
            }
        }

    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration                   
}
