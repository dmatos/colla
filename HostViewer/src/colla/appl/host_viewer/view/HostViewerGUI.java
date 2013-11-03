/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.host_viewer.view;

import colla.appl.host_viewer.controller.HostViewerController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingWorker;

/**
 *
 * @author Bruno
 */
public class HostViewerGUI extends javax.swing.JFrame {

    /**
     * Creates new form HostViewRegisterGUI
     */
    private HostViewerGUI() {
        initComponents();
        
        this.setLocation(400, 150);
        
        this.addWindowListener(new WindowAdapter() {
            /* call the method shutdown to terminate the GUIs and connections
             */
            @Override
            public void windowClosing(WindowEvent e) {
                HostViewerGUI.getInstance().shutdown();
            }
        });
        //System.out.println("construcao com sucesso");
    }
    
    public static synchronized HostViewerGUI getInstance() {
        if (instance == null) {
            instance = new HostViewerGUI();
        }
        return instance;
    }
    
    public void shutdown() {
        //System.out.println("shuting down");
        try {
            HostViewerController.getInstance().shutdown();
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
        this.dispose();        
        HostViewerGUI.instance = null;      
    }

    /*
     * exibe informações ao usuário na tela de login
     */
    public void displayStatus(String s) {
        status = s;

        /*
         * SwingWorker é a classe que atualiza componentes swing em uma
         * nova thread sem que haja necessidade de ficar esperando
         * as ações de outros componentes ou processos terminarem
         */
        SwingWorker display = new SwingWorker() {
            @Override
            protected Object doInBackground() {
                jTextAreaStatus.append(" " + status + "\n");
                jTextAreaStatus.setCaretPosition(jTextAreaStatus.getText().length() - 1);
                jTextAreaStatus.update(jTextAreaStatus.getGraphics());
                jTextAreaStatus.repaint();
                return null;
            }
            
            @Override
            public void done() {
                //do nothing
            }
        };
        display.execute();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     *
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new colla.kernel.util.ImagePane("/colla/appl/host_viewer/view/images/login_fundo.jpg");
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaStatus = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CollAHost");

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N
        jLabel1.setText("Host is logging...");

        jScrollPane1.setBackground(java.awt.Color.white);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 0, 15))); // NOI18N

        jTextAreaStatus.setBackground(java.awt.Color.black);
        jTextAreaStatus.setColumns(20);
        jTextAreaStatus.setEditable(false);
        jTextAreaStatus.setForeground(java.awt.Color.green);
        jTextAreaStatus.setRows(5);
        jScrollPane1.setViewportView(jTextAreaStatus);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 185, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );

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
    private static HostViewerGUI instance = null;
    private String systemProperties;
    private String status; //para exibir no jText_status    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaStatus;
    // End of variables declaration//GEN-END:variables
}
