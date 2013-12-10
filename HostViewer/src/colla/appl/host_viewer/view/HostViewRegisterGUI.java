/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.host_viewer.view;

import colla.appl.host_viewer.controller.HostViewerRegister;

/**
 *
 * @author Bruno
 */
public class HostViewRegisterGUI extends javax.swing.JFrame{
/** Creates new form HostViewRegisterGUI */
public HostViewRegisterGUI(HostViewerRegister hv){
    this.hostViewRegister = hv;
    initComponents();
    this.setLocation(400, 150);
    this.jLabel_warn.setVisible(false);
}

/** This method is called from within the constructor to
 * initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is
 * always regenerated by the Form Editor.
 */
@SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new colla.kernel.util.ImagePanel("/colla/appl/host_viewer/view/images/login_fundo.jpg");
        jTextFieldUser = new javax.swing.JTextField();
        jPasswordFieldPass = new javax.swing.JPasswordField();
        jButtonRegister = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPort = new javax.swing.JTextField();
        jLabel_warn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Host Register");

        jTextFieldUser.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jTextFieldUser.setBorder(javax.swing.BorderFactory.createTitledBorder("Username"));
        jTextFieldUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUserActionPerformed(evt);
            }
        });

        jPasswordFieldPass.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jPasswordFieldPass.setBorder(javax.swing.BorderFactory.createTitledBorder("Password"));
        jPasswordFieldPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldPassActionPerformed(evt);
            }
        });

        jButtonRegister.setBackground(java.awt.Color.white);
        jButtonRegister.setFont(new java.awt.Font("Verdana", 1, 16)); // NOI18N
        jButtonRegister.setText("Register");
        jButtonRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonRegisterMouseClicked(evt);
            }
        });
        jButtonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegisterActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel1.setForeground(java.awt.Color.black);
        jLabel1.setText("Enter the user data which the host will be linked:");

        jTextFieldPort.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jTextFieldPort.setText("6969");
        jTextFieldPort.setBorder(javax.swing.BorderFactory.createTitledBorder("Port number"));

        jLabel_warn.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_warn.setText("*Invalid username or password");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addComponent(jTextFieldUser, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldPort, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel_warn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPasswordFieldPass, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldUser, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPasswordFieldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRegister)
                    .addComponent(jLabel_warn))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPasswordFieldPass, jTextFieldPort, jTextFieldUser});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jTextFieldUserActionPerformed( java.awt.event.ActionEvent evt ){//GEN-FIRST:event_jTextFieldUserActionPerformed
}//GEN-LAST:event_jTextFieldUserActionPerformed

private void jPasswordFieldPassActionPerformed( java.awt.event.ActionEvent evt ){//GEN-FIRST:event_jPasswordFieldPassActionPerformed
}//GEN-LAST:event_jPasswordFieldPassActionPerformed

private void jButtonRegisterActionPerformed( java.awt.event.ActionEvent evt ){//GEN-FIRST:event_jButtonRegisterActionPerformed
    /* Registration Button action */
}//GEN-LAST:event_jButtonRegisterActionPerformed

private void jButtonRegisterMouseClicked( java.awt.event.MouseEvent evt ){//GEN-FIRST:event_jButtonRegisterMouseClicked
    String userName = jTextFieldUser.getText();
    String pass = new String( jPasswordFieldPass.getText() );
    int port = new Integer(jTextFieldPort.getText());
    if( port < 1 ) {
        port = 6969;
    }
    if(hostViewRegister.register(userName, pass, port)) {
        hostViewRegister.shutdown();
        hostViewRegister = null;         
        this.dispose();
    }
    else {
        this.jLabel_warn.setVisible(true);
    }
        
}//GEN-LAST:event_jButtonRegisterMouseClicked

private HostViewerRegister hostViewRegister;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_warn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordFieldPass;
    private javax.swing.JTextField jTextFieldPort;
    private javax.swing.JTextField jTextFieldUser;
    // End of variables declaration//GEN-END:variables
}
