
/*
 * CollAServerGUI.java
 *
 * Created on 10/01/2012, 14:42:22
 */
package colla.appl.server.GUI;

import colla.appl.server.Server;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAJob;
import colla.kernel.api.CollASession;
import colla.kernel.api.CollAUser;
import colla.kernel.exceptions.server.NonExistentUser;
import colla.kernel.exceptions.server.ServerInitializationException;
import colla.kernel.impl.Session;
import colla.kernel.util.Debugger;
import colla.kernel.util.LogWriter;
import colla.kernel.util.NetworkDevices;
import colla.kernel.util.TimeAndDate;

/**
 * @todo esta implementação não pode lidar diretamente com os users no sentido
 * de armazenar informações dos mesmos em memória. Como por exemplo as
 * activities de um usuário, pois chegará num ponto onde não haverá memória
 * suficiente. As atividades de um usuário devem ser salvas em disco e
 * recuperadas pelo server apenas para exibição.
 */
/**
 * @author dmatos
 */
public class CollAServerGUI extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form CollAServerGUI
     */
    public CollAServerGUI() {

        initializeLookAndFeel();

        this.connectionTime = new TimeAndDate();

        initComponents();

        this.setLocation(400, 150);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Clients");
        DefaultTreeModel model = new DefaultTreeModel(root);
        jTree_clients.setModel(model);
        jTree_clients.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jTree_clients.addTreeSelectionListener(new UsersTreeSelectionListener());
        jTabbedPane_server.setTitleAt(0, "Server Status");
        jTabbedPane_server.setTitleAt(1, "Clients");
        displayMessage("Server initialized.");

        //search and shows the IP addresses of the machine
        NetworkDevices netDevices = new NetworkDevices();
        try {
            for (String ip : netDevices.getIPs()) {
                displayMessage("IP Address: " + ip);
            }
        } catch (SocketException ex) {
            Logger.getLogger(CollAServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        addWindowListener(new WindowAdapter() {

            /*
             * call the method shutdown to store all server data
             */
            @Override
            public void windowClosing(WindowEvent e) {
                shutdown();
            }
        });

        this.setVisible(true);

    }

    /**
     * Shutdown the server, closing its connections and saving its data to
     * permanent memory.
     */
    public void shutdown() {
        try {
            Server server = Server.getInstance();           
            server.shutdown();
        } catch (Exception e) {
            LogWriter.generateLog("Couldn't store all data");
        }
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane_server = new javax.swing.JTabbedPane();
        jPanel_serverStatus = new colla.kernel.util.ImagePanel(BackGround.RADIAL_CENTER_SOFTGREEN.getPath());
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane_serverStatus = new javax.swing.JTextPane();
        jSplitPane4 = new javax.swing.JSplitPane();
        jSplitPane5 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel_userStatus = new colla.kernel.util.ImagePanel(BackGround.SMOOTH_GREEN.getPath());
        jTextField_nome = new javax.swing.JTextField();
        jTextField_isOnline = new javax.swing.JTextField();
        jTextField_group = new javax.swing.JTextField();
        jTextField_ipValido = new javax.swing.JTextField();
        jTextField_email = new javax.swing.JTextField();
        jTextField_country = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea_hostProperties = new javax.swing.JTextArea();
        jTextField_clientCompany = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree_clients = new javax.swing.JTree();
        jPanel3 = new colla.kernel.util.ImagePanel(BackGround.RADIAL_CENTER_SOFTGREEN.getPath());
        jComboBox_activitiesList = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea_activities = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CollA Server");

        jTabbedPane_server.setToolTipText("");
        jTabbedPane_server.setAutoscrolls(true);
        jTabbedPane_server.setName("ServerStatus"); // NOI18N

        jPanel_serverStatus.setBackground(java.awt.Color.green);
        jPanel_serverStatus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Server Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), java.awt.Color.black)); // NOI18N

        jScrollPane2.setAutoscrolls(true);

        jTextPane_serverStatus.setBackground(java.awt.Color.white);
        jTextPane_serverStatus.setEditable(false);
        jTextPane_serverStatus.setDragEnabled(true);
        jScrollPane2.setViewportView(jTextPane_serverStatus);

        javax.swing.GroupLayout jPanel_serverStatusLayout = new javax.swing.GroupLayout(jPanel_serverStatus);
        jPanel_serverStatus.setLayout(jPanel_serverStatusLayout);
        jPanel_serverStatusLayout.setHorizontalGroup(
            jPanel_serverStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_serverStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_serverStatusLayout.setVerticalGroup(
            jPanel_serverStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_serverStatusLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane_server.addTab("tab1", jPanel_serverStatus);

        jSplitPane4.setDividerLocation(500);

        jSplitPane5.setDividerLocation(150);

        jScrollPane4.setBackground(java.awt.Color.gray);

        jPanel_userStatus.setBackground(java.awt.Color.green);
        jPanel_userStatus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), java.awt.Color.black)); // NOI18N
        jPanel_userStatus.setToolTipText("Status");
        jPanel_userStatus.setAutoscrolls(true);
        jPanel_userStatus.setPreferredSize(new java.awt.Dimension(300, 515));

        jTextField_nome.setEditable(false);
        jTextField_nome.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField_nome.setBorder(javax.swing.BorderFactory.createTitledBorder("Name"));

        jTextField_isOnline.setEditable(false);
        jTextField_isOnline.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField_isOnline.setBorder(javax.swing.BorderFactory.createTitledBorder("Online"));

        jTextField_group.setEditable(false);
        jTextField_group.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField_group.setBorder(javax.swing.BorderFactory.createTitledBorder("IP"));

        jTextField_ipValido.setEditable(false);
        jTextField_ipValido.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField_ipValido.setBorder(javax.swing.BorderFactory.createTitledBorder("public IP"));

        jTextField_email.setEditable(false);
        jTextField_email.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField_email.setBorder(javax.swing.BorderFactory.createTitledBorder("e-mail"));

        jTextField_country.setEditable(false);
        jTextField_country.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField_country.setBorder(javax.swing.BorderFactory.createTitledBorder("Country"));

        jScrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Host Properties", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), java.awt.Color.black)); // NOI18N

        jTextArea_hostProperties.setColumns(17);
        jTextArea_hostProperties.setEditable(false);
        jTextArea_hostProperties.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jTextArea_hostProperties.setRows(8);
        jTextArea_hostProperties.setBorder(null);
        jScrollPane5.setViewportView(jTextArea_hostProperties);

        jTextField_clientCompany.setEditable(false);
        jTextField_clientCompany.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField_clientCompany.setBorder(javax.swing.BorderFactory.createTitledBorder("Company"));

        javax.swing.GroupLayout jPanel_userStatusLayout = new javax.swing.GroupLayout(jPanel_userStatus);
        jPanel_userStatus.setLayout(jPanel_userStatusLayout);
        jPanel_userStatusLayout.setHorizontalGroup(
            jPanel_userStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_userStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_userStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_isOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_group, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_ipValido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_country, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(jTextField_email)
                    .addComponent(jTextField_clientCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_userStatusLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane5, jTextField_clientCompany, jTextField_country, jTextField_email, jTextField_group, jTextField_ipValido, jTextField_isOnline, jTextField_nome});

        jPanel_userStatusLayout.setVerticalGroup(
            jPanel_userStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_userStatusLayout.createSequentialGroup()
                .addComponent(jTextField_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_isOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_group, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_ipValido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_country, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_clientCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel_userStatusLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField_country, jTextField_email, jTextField_group, jTextField_ipValido, jTextField_isOnline, jTextField_nome});

        jScrollPane4.setViewportView(jPanel_userStatus);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );

        jSplitPane5.setRightComponent(jPanel1);

        jTree_clients.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree_clients.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jTree_clients);

        jSplitPane5.setLeftComponent(jScrollPane1);

        jSplitPane4.setLeftComponent(jSplitPane5);

        jPanel3.setBackground(java.awt.Color.green);
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sessions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), java.awt.Color.black)); // NOI18N

        jComboBox_activitiesList.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jComboBox_activitiesList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_activitiesListActionPerformed(evt);
            }
        });

        jTextArea_activities.setColumns(20);
        jTextArea_activities.setEditable(false);
        jTextArea_activities.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jTextArea_activities.setLineWrap(true);
        jTextArea_activities.setRows(5);
        jTextArea_activities.setWrapStyleWord(true);
        jScrollPane3.setViewportView(jTextArea_activities);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(jComboBox_activitiesList, javax.swing.GroupLayout.Alignment.LEADING, 0, 273, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jComboBox_activitiesList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane4.setRightComponent(jPanel3);

        jTabbedPane_server.addTab("tab2", jSplitPane4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane_server, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane_server, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jTabbedPane_server.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_activitiesListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_activitiesListActionPerformed
        if (jComboBox_activitiesList.getSelectedIndex() >= 0) {
            DefaultMutableTreeNode node =
                    (DefaultMutableTreeNode) jTree_clients.getLastSelectedPathComponent();
            comboBoxActions((String) node.getUserObject());
        }
    }//GEN-LAST:event_jComboBox_activitiesListActionPerformed

    /**
     * Exhibits a message.
     *
     * @param msg message to exhibit.
     */
    public final void displayMessage(String msg) {
        StyledDocument doc = jTextPane_serverStatus.getStyledDocument();
        SimpleAttributeSet dateAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(dateAttr, Color.BLUE);
        SimpleAttributeSet msgAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(msgAttr, Color.BLACK);
        StyleConstants.setFontFamily(msgAttr, "dejavu sans");
        StyleConstants.setFontFamily(dateAttr, "dejavu sans");
        StyleConstants.setFontSize(msgAttr, 14);
        StyleConstants.setFontSize(dateAttr, 14);
        try {
            doc.insertString(doc.getLength(), connectionTime.getSimpleDate()
                    + " " + connectionTime.getHour()
                    + "\n", dateAttr);
            doc.insertString(doc.getLength(), msg + "\n", msgAttr);
            jTextPane_serverStatus.setCaretPosition(doc.getLength());
        } catch (BadLocationException ble) {
//            ble.printStackTrace();
        }

    }

    /**
     * Updates the Tree of clients on the GUI.
     */
    public void updateClientsTree() {
        try {
            Server server = Server.getInstance();
            Set<String> clientsSet = server.getUsersSet();
            DefaultTreeModel model = (DefaultTreeModel) jTree_clients.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
            root.removeAllChildren();
            for (String userName : clientsSet) {
                DefaultMutableTreeNode client = new DefaultMutableTreeNode(userName);
                root.add(client);
                try {
                    CollAUser usr = server.getUser(userName);
                    for (CollAHost cHost : usr.getHosts()) {
                        DefaultMutableTreeNode host = new DefaultMutableTreeNode(cHost.getName());
                        client.add(host);
                        //System.out.println(cHost.getName());
                    }
                } catch (NonExistentUser nExUsr) {
                    //nExUsr.printStackTrace();
                }
            }
            model.setRoot(root);
            jTree_clients.repaint();
        } catch (ServerInitializationException serverEx) {
            Debugger.debug(serverEx);
        }
    }

    @Override
    public void update(Observable subject, Object interest) {
        if (interest instanceof String) {
            String info = (String) interest;
            this.displayMessage(info);
        } else {
            this.updateClientsTree();
        }
    }

    class UsersTreeSelectionListener implements TreeSelectionListener {

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree_clients.getLastSelectedPathComponent();
            if (node == null) {
                return;
            }
            Color green = new Color(2, 161, 14);
            String nodeName = (String) node.getUserObject();
            try {
                Server server = Server.getInstance();
                CollAUser usr = server.getUser(nodeName);

                jTextField_nome.setText(usr.getName());
                jTextField_group.setText(usr.getIp()+":"+usr.getPort());
                if (usr.isOnline()) {
                    jTextField_isOnline.setForeground(green);
                } else {

                    jTextField_isOnline.setForeground(Color.red);
                }
                if (usr.hasValidIP()) {
                    jTextField_ipValido.setForeground(green);
                } else {
                    jTextField_ipValido.setForeground(Color.red);
                }

                jTextField_isOnline.setText("" + usr.isOnline());
                jTextField_ipValido.setText("" + usr.hasValidIP());
                jTextField_clientCompany.setText("" + usr.getCompany());
                jTextField_email.setText(usr.getEmail());
                jTextField_email.setVisible(true);
                jScrollPane5.setVisible(false);
                jTextField_country.setText(usr.getCountry());
                /*
                 * add activities data to combobox in inverse order of dates
                 */
                jComboBox_activitiesList.removeAllItems();
                for (int i = usr.getOrderedSessions().size() - 1; i >= 0; i--) {
                    jComboBox_activitiesList.addItem(usr.getOrderedSessions().get(i));
                }

                //jComboBox_listaAtitvidades.repaint();
            } catch (ServerInitializationException serverEx) {
                //serverEx.printStackTrace();
            } catch (NonExistentUser nExUsr) {
                //nExUsr.printStackTrace();
            } finally {
                DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();
                if (parentNode == null) {
                    return;
                }
                CollAUser user;
                try {
                    Server server = Server.getInstance();
                    user = server.getUser((String) parentNode.getUserObject());
                    CollAHost hst = user.getHost(nodeName);
                    if (hst != null) {
                        jTextField_email.setVisible(false);
                        jScrollPane5.setVisible(true);
                        jTextField_nome.setText(hst.getName());
                        jTextField_group.setText(hst.getIp()+":"+hst.getPort());
                        if (hst.IsOnline()) {
                            jTextField_isOnline.setForeground(green);
                        } else {
                            jTextField_isOnline.setForeground(Color.red);
                        }
                        if (hst.hasValidIP()) {
                            jTextField_ipValido.setForeground(green);
                        } else {
                            jTextField_ipValido.setForeground(Color.red);
                        }

                        jTextField_isOnline.setText("" + hst.IsOnline());
                        jTextField_ipValido.setText("" + hst.hasValidIP());
                        jTextField_email.setText("-");
                        jTextField_country.setText(hst.getCountry());
                        jTextArea_hostProperties.setText(hst.getSystemProperties());
                        jComboBox_activitiesList.removeAllItems();
                        HashMap<String, String> atividadesListadas = hst.getActivities();
                        Set<String> chaves = atividadesListadas.keySet();
                        for (String j : chaves) {
                            jComboBox_activitiesList.addItem(j);
                        }
                        jTextArea_activities.setText("");
                    }
                } catch (NonExistentUser nExUsr) {
                    //nExUsr.printStackTrace();
                } catch (ServerInitializationException serverEx) {
                    //serverEx.printStackTrace();
                }
            }
        }
    }

    /**
     * Deals with jCombobox_activitiesList actions.
     *
     * @param arg0 value get at jCombobox_activitiesList
     */
    private void comboBoxActions(String arg0) {
        int index = jComboBox_activitiesList.getSelectedIndex();
        jTextArea_activities.setText("");        
        try {
            Server server = Server.getInstance();
            CollAUser user = server.getUser(arg0);
            if (index >= 0) {
                String selectedDate = (String) jComboBox_activitiesList.getItemAt(index);
                HashMap<String, CollASession> userSessions = user.getSessions();
                Session session = (Session) userSessions.get(selectedDate);
                jTextArea_activities.setText("Session ID: " + session.getSessionID() + "\n");
                CollAJob[] jobs = session.getJobs();
                //System.out.println("number of jobs :" + jobs.length);
                for (CollAJob j : jobs) {
                    if (j != null) {
                        jTextArea_activities.append(j.getJobDateAndTime() + " " + j.getJobName() + "\n");
                    }
                }

                jTextArea_activities.repaint();
            }
        } catch (NonExistentUser nExUsr) {
            jTextArea_activities.setText("");
            jComboBox_activitiesList.removeAllItems();
        } catch (ServerInitializationException serverEx){
            serverEx.printStackTrace();
        }
    }

    /**
     * lookAndFell copied from Netbeans 7.0.1
     */
    private void initializeLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CollAServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CollAServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CollAServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CollAServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    private TimeAndDate connectionTime;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox_activitiesList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel_serverStatus;
    private javax.swing.JPanel jPanel_userStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JSplitPane jSplitPane5;
    private javax.swing.JTabbedPane jTabbedPane_server;
    private javax.swing.JTextArea jTextArea_activities;
    private javax.swing.JTextArea jTextArea_hostProperties;
    private javax.swing.JTextField jTextField_clientCompany;
    private javax.swing.JTextField jTextField_country;
    private javax.swing.JTextField jTextField_email;
    private javax.swing.JTextField jTextField_group;
    private javax.swing.JTextField jTextField_ipValido;
    private javax.swing.JTextField jTextField_isOnline;
    private javax.swing.JTextField jTextField_nome;
    private javax.swing.JTextPane jTextPane_serverStatus;
    private javax.swing.JTree jTree_clients;
    // End of variables declaration//GEN-END:variables
}
