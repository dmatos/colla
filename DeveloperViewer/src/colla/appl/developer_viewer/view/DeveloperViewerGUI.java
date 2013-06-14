/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DeveloperViewerGUI.java
 * 
 *
 * Created on 15/03/2012, 00:19:40
 */
package colla.appl.developer_viewer.view;

import colla.appl.developer_viewer.ClientConfigurations;
import colla.appl.developer_viewer.ConfigException;
import colla.appl.developer_viewer.DevViewerLogin;
import colla.appl.developer_viewer.DeveloperViewerController;
import colla.kernel.api.CollAGroup;
import colla.kernel.api.CollAHost;
import colla.kernel.api.CollATask;
import colla.kernel.api.CollAUser;
import colla.kernel.util.ImagePane;
import colla.kernel.util.TimeAndDate;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.*;
import javax.swing.text.html.HTML;
import org.jbundle.thin.base.screen.jcalendarbutton.JCalendarButton;
import org.jbundle.thin.base.screen.jcalendarbutton.JTimeButton;
import org.openide.util.Exceptions;

/**
 * Graphical User Interface for project CollA users
 *
 * @author dmatos88 at gmail.com
 */
public class DeveloperViewerGUI extends javax.swing.JFrame implements Observer, CollADeveloperViewerUI {

    /**
     * Creates new form DeveloperViewerGUI
     */
    public DeveloperViewerGUI(DeveloperViewerController dev, String userName) {
        super("CollA Client - " + userName);
        this.setIconImage(new ImageIcon(getClass().getResource(BackGround.COLLA_LOGO_ICON.getPath())).getImage());
        UIManager.put("DesktopPaneUI", "javax.swing.plaf.basic.BasicDesktopPaneUI");
        this.devViewer = dev;
        this.chatWindow = new ChatWindow(dev.serverIPaddress, dev.serverPortNumber, userName, dev);
        this.thisUserName = userName;
        this.taskFile = null;
        this.dependencyFiles = new ArrayList<File>();
        this.methods = new HashMap<String, ArrayList<Method>>();
        this.refusedMembers = new HashMap<String, java.util.List<String>>();
        this.acceptedMembers = new HashMap<String, java.util.List<String>>();
        this.waitingMembers = new HashMap<String, java.util.List<String>>();
        this.argumentsFiles = new ArrayList<File>();
        this.initComponents();
        this.initCustomComponents();
        this.setLocation(200, 100);

        addWindowListener(new WindowAdapter() {
            /*
             * call the method shutdown to terminate all GUIs and connections
             */
            @Override
            public void windowClosing(WindowEvent e) {
                shutdown();
            }
        });
        try {
            this.restoredData();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

        jCalendarSendTask.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                setDateTime(jCalendarSendTask.getDate());
                System.out.println(jCalendarSendTask.getDate());
            }
        });

        jButtonTime.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getNewValue() instanceof Date) {
                    setDateTime((Date) evt.getNewValue());
                }
            }
        });

        this.setVisible(true);
    }

    public void shutdown() {
        try {
            this.storeData();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        this.chatWindow.dispose();
        devViewer.shutdown();
        try {
            DevViewerLogin devViewerLogin = new DevViewerLogin();
            devViewerLogin.useGUI(true);
            Client_Login loginGUI = new Client_Login(devViewerLogin);
            devViewerLogin.addObserver(loginGUI);
        } catch (ConfigException cex) {
            JOptionPane.showMessageDialog(null, "Could not read file server_conf.xml.", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        this.dispose();
    }

    private void storeData() throws Exception {
        //create data directory if it does not alredy exist
        new File("data/").mkdir();

        //store settings data
        FileOutputStream f_out = new FileOutputStream("data/config.data");
        ObjectOutputStream output = new ObjectOutputStream(f_out);
        output.writeObject(this.config);
        output.flush();
        output.close();
        f_out.close();
    }

    private boolean restoredData() throws Exception {
        //create data directory if it does not alredy exist
        new File("data/").mkdir();

        if (new File("data/config.data").exists()) {
            //restore clients
            FileInputStream f_in = new FileInputStream("data/config.data");
            ObjectInputStream input = new ObjectInputStream(f_in);
            this.config = (ClientConfigurations) input.readObject();
            input.close();
            f_in.close();
            this.resultsWindow.setResultsDir(this.config.getResulstDir());
            return true;
        }
        return false;
    }

    /**
     * initialize customized components
     */
    private void initCustomComponents() {
        this.config = new ClientConfigurations();
        this.resultsWindow = new ResultsWindow(this.devViewer, this.config.getResulstDir());
        this.dependencyModel = new DefaultListModel<File>();
        this.argumentsModel = new DefaultListModel<File>();
        this.AtachsList.setModel(dependencyModel);
        this.jList_parameters.setModel(argumentsModel);
        jList_contacts.setModel(new DefaultListModel<String>());
        jList_contacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel listSelectionModel = jList_contacts.getSelectionModel();
        listSelectionModel.addListSelectionListener(new JList_contacts_Listener());
        jList_listJoinGroup.setModel(new DefaultListModel<String>());
        jList_listJoinGroup.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jDesktopPane_desktop.add(chatWindow);
        jDesktopPane_desktop.add(resultsWindow);
        jList_groupRequests.setModel(new DefaultListModel<String>());
        jList_acceptMembers.setModel(new DefaultListModel<String>());
        jList_refuse.setModel(new DefaultListModel<String>());
        jList_members.setModel(new DefaultListModel<String>());
        jButton_generalSettings.setVerticalTextPosition(SwingConstants.BOTTOM);
        jButton_generalSettings.setHorizontalTextPosition(SwingConstants.CENTER);

        //to save results
        jFileChooser_resultDirectory = new JFileChooser();
        jFileChooser_resultDirectory.setDialogTitle("Select directory to save results");
        jFileChooser_resultDirectory.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    /**
     * update or insert many groups
     *
     * @param groups
     */
    @Override
    public void updateAllGroups(final HashMap<String, CollAGroup> groups) {
//        this.groupsMap = groups;
        jComboBox_groups.removeAllItems();
        Set<String> keys = devViewer.getUser().getGroups().keySet();
        if (keys.isEmpty()) {
            jComboBox_groups.addItem("My Groups");
        } else {
            for (String groupName : keys) {
                jComboBox_groups.addItem(groupName);
            }
        }
        this.jComboBox_groupsActions();
    }

    public void createGroup(String groupName) {
        if (groupName.length() > 0) {
            devViewer.createGroup(groupName);
            jTextField_groupName.setText("");
            jDialog_creatGroup.dispose();
        }
    }

    /**
     * lists the groups
     *
     * @param groups a Set of String of groups to join
     */
    @Override
    public void listGroupsToJoin(Set<String> groups) {
        groupsName = groups;
        DefaultListModel<String> listModel;
        listModel = (DefaultListModel<String>) jList_listJoinGroup.getModel();
        listModel.removeAllElements();
        for (String i : groupsName) {
            listModel.addElement(i);
        }
        jList_listJoinGroup.repaint();
    }

    public void closeJoinGroupDialog() {
        this.jDialog_joinGroup.dispose();
    }

    /**
     * update only one group
     *
     * @param groupName
     */
    @Override
    public void refresh_a_group(String groupName, Set<String> group) {
        jComboBox_groups.addItem(groupName);
        jComboBox_groups.repaint();
    }

    @Override
    public void setListOfTasks(HashMap<String, HashMap<String, CollATask>> tasks) {
        resultsWindow.setListOfTasks(tasks);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog_creatGroup = new javax.swing.JDialog();
        jPanel3 = new ImagePane(BackGround.SOFT_GREEN.getPath());
        jTextField_groupName = new javax.swing.JTextField();
        jButton_createGroup = new javax.swing.JButton();
        jButton_cancelCreateGroup = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jDialog_joinGroup = new javax.swing.JDialog();
        jPanel4 = new colla.kernel.util.ImagePane(BackGround.SOFT_GREEN.getPath());
        jScrollPane2 = new javax.swing.JScrollPane();
        jList_listJoinGroup = new javax.swing.JList<String>();
        jButton_join = new javax.swing.JButton();
        jButton_cancelJoinGroup = new javax.swing.JButton();
        jTextField_groupSearch = new javax.swing.JTextField();
        jButton_searchGroup = new javax.swing.JButton();
        jButton_clearSearch = new javax.swing.JButton();
        jDialog_FileChooser = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jFileChooser = new javax.swing.JFileChooser();
        jDialog_sendTask = new javax.swing.JDialog();
        jPanel6 = new colla.kernel.util.ImagePane(BackGround.SOFT_GREEN.getPath());
        jLabelError = new javax.swing.JLabel();
        AddTask = new javax.swing.JButton();
        jTextFieldTask = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox_classToExecute = new javax.swing.JComboBox<String>();
        jComboBox_methodToExecute = new javax.swing.JComboBox<String>();
        jScrollPane5 = new javax.swing.JScrollPane();
        AtachsList = new javax.swing.JList<File>();
        AddAtach = new javax.swing.JButton();
        RemoveAtach = new javax.swing.JButton();
        jComboBox_taskGroup = new javax.swing.JComboBox<String>();
        jLabel4 = new javax.swing.JLabel();
        Cancel = new javax.swing.JButton();
        jButton_submit = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        jList_parameters = new javax.swing.JList<File>();
        jButton_addArgument = new javax.swing.JButton();
        jButton_rmArgument = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jCheckBoxSchedule = new javax.swing.JCheckBox();
        jCalendarSendTask = new com.toedter.calendar.JCalendar();
        jButtonTime = new org.jbundle.thin.base.screen.jcalendarbutton.JTimeButton();
        jTextFieldSchedule = new javax.swing.JTextField();
        jDialog_manageGroups = new javax.swing.JDialog();
        imagePane1 = new colla.kernel.util.ImagePane(BackGround.RADIAL_GREEN.getPath());
        jComboBox_grpManager = new javax.swing.JComboBox<String>();
        jTabbedPane_manageGroups = new javax.swing.JTabbedPane();
        imagePane2 = new colla.kernel.util.ImagePane(BackGround.SMOOTH_GREEN.getPath());
        jScrollPane7 = new javax.swing.JScrollPane();
        jList_groupRequests = new javax.swing.JList<String>();
        jScrollPane8 = new javax.swing.JScrollPane();
        jList_acceptMembers = new javax.swing.JList<String>();
        jButton_toAccepted = new javax.swing.JButton();
        jButton_fromAccept2Wating = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        jList_refuse = new javax.swing.JList<String>();
        jButton_toRefused = new javax.swing.JButton();
        jButton_froRefuse2Wating = new javax.swing.JButton();
        imagePane3 = new colla.kernel.util.ImagePane(BackGround.SMOOTH_GREEN.getPath());
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextPane_memberInfo = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        jList_members = new javax.swing.JList<String>();
        jButton_okMngGrp = new javax.swing.JButton();
        jButton_cancelMngGrp = new javax.swing.JButton();
        jButton_applyGrpManager = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jDialog_settings = new javax.swing.JDialog();
        imagePane4 = new colla.kernel.util.ImagePane(BackGround.RADIAL_CENTER_SOFTGREEN.getPath());
        jPanel2 = new javax.swing.JPanel();
        jButton_generalSettings = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel_settingsMain = new javax.swing.JPanel(new CardLayout());
        jPanel_card1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_resultsDir = new javax.swing.JTextField();
        jButton_resultsDir = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField_nonFileResultsDir = new javax.swing.JTextField();
        jButton_ResultsDirNonFile = new javax.swing.JButton();
        jPanel_card2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSpinner_portNumber = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jButton_okSettings = new javax.swing.JButton();
        jButton_cancelSettings = new javax.swing.JButton();
        jSplitPane_desktopAndContacts = new javax.swing.JSplitPane();
        jSplitPane_contactsAndHosts = new javax.swing.JSplitPane();
        jPanel1 = new colla.kernel.util.ImagePane(BackGround.INVERSE_DENSE_GREEN.getPath());
        jScrollPane1 = new javax.swing.JScrollPane();
        jList_contacts = new javax.swing.JList<String>();
        jComboBox_groups = new javax.swing.JComboBox<String>();
        jPanel_myHosts = new colla.kernel.util.ImagePane(BackGround.DENSE_GREEN.getPath());
        jComboBox_hosts = new javax.swing.JComboBox<String>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea_hostProp = new javax.swing.JTextArea();
        jSplitPane2 = new javax.swing.JSplitPane();
        jDesktopPane_desktop = new javax.swing.JDesktopPane();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextPane_info = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem_SendTask = new javax.swing.JMenuItem();
        jMenuItem_Results = new javax.swing.JMenuItem();
        jMenuItem_quit = new javax.swing.JMenuItem();
        jMenu_groups = new javax.swing.JMenu();
        jMenuItem_joinGroup = new javax.swing.JMenuItem();
        jMenuItem_createGroup = new javax.swing.JMenuItem();
        jMenuItem_manageGroups = new javax.swing.JMenuItem();
        jMenu_tools = new javax.swing.JMenu();
        jMenuItem_settings = new javax.swing.JMenuItem();

        jDialog_creatGroup.setTitle(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jDialog_creatGroup.title")); // NOI18N
        jDialog_creatGroup.setMinimumSize(new java.awt.Dimension(449, 168));
        jDialog_creatGroup.setModal(true);
        jDialog_creatGroup.setResizable(false);

        jTextField_groupName.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jTextField_groupName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_groupName.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jTextField_groupName.text")); // NOI18N

        jButton_createGroup.setBackground(java.awt.Color.white);
        jButton_createGroup.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jButton_createGroup.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_createGroup.text")); // NOI18N
        jButton_createGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_createGroupMouseClicked(evt);
            }
        });
        jButton_createGroup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton_createGroupKeyReleased(evt);
            }
        });

        jButton_cancelCreateGroup.setBackground(java.awt.Color.white);
        jButton_cancelCreateGroup.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jButton_cancelCreateGroup.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_cancelCreateGroup.text")); // NOI18N
        jButton_cancelCreateGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_cancelCreateGroupMouseClicked(evt);
            }
        });
        jButton_cancelCreateGroup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton_cancelCreateGroupKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel2.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton_cancelCreateGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_createGroup)
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextField_groupName, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))))
                .addGap(56, 56, 56))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_cancelCreateGroup, jButton_createGroup});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_groupName, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_cancelCreateGroup)
                    .addComponent(jButton_createGroup))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog_creatGroupLayout = new javax.swing.GroupLayout(jDialog_creatGroup.getContentPane());
        jDialog_creatGroup.getContentPane().setLayout(jDialog_creatGroupLayout);
        jDialog_creatGroupLayout.setHorizontalGroup(
            jDialog_creatGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog_creatGroupLayout.setVerticalGroup(
            jDialog_creatGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog_joinGroup.setTitle(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jDialog_joinGroup.title")); // NOI18N
        jDialog_joinGroup.setModal(true);
        jDialog_joinGroup.setResizable(false);

        jList_listJoinGroup.setBorder(new javax.swing.border.MatteBorder(null));
        jList_listJoinGroup.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jList_listJoinGroup.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList_listJoinGroup);

        jButton_join.setBackground(java.awt.Color.white);
        jButton_join.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jButton_join.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_join.text")); // NOI18N
        jButton_join.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_joinMouseClicked(evt);
            }
        });

        jButton_cancelJoinGroup.setBackground(java.awt.Color.white);
        jButton_cancelJoinGroup.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jButton_cancelJoinGroup.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_cancelJoinGroup.text")); // NOI18N
        jButton_cancelJoinGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_cancelJoinGroupMouseClicked(evt);
            }
        });

        jTextField_groupSearch.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jTextField_groupSearch.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jTextField_groupSearch.text")); // NOI18N

        jButton_searchGroup.setBackground(java.awt.Color.white);
        jButton_searchGroup.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jButton_searchGroup.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_searchGroup.text")); // NOI18N
        jButton_searchGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_searchGroupMouseClicked(evt);
            }
        });

        jButton_clearSearch.setBackground(java.awt.Color.white);
        jButton_clearSearch.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jButton_clearSearch.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_clearSearch.text")); // NOI18N
        jButton_clearSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_clearSearchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                            .addComponent(jTextField_groupSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton_clearSearch)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_searchGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jButton_cancelJoinGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_join, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_clearSearch, jButton_searchGroup});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_cancelJoinGroup, jButton_join});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jTextField_groupSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_clearSearch)
                    .addComponent(jButton_searchGroup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_cancelJoinGroup)
                    .addComponent(jButton_join))
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialog_joinGroupLayout = new javax.swing.GroupLayout(jDialog_joinGroup.getContentPane());
        jDialog_joinGroup.getContentPane().setLayout(jDialog_joinGroupLayout);
        jDialog_joinGroupLayout.setHorizontalGroup(
            jDialog_joinGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog_joinGroupLayout.setVerticalGroup(
            jDialog_joinGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialog_joinGroup.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jDialog_joinGroup.AccessibleContext.accessibleName")); // NOI18N
        jDialog_joinGroup.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jDialog_joinGroup.AccessibleContext.accessibleDescription")); // NOI18N

        jDialog_FileChooser.setMinimumSize(new java.awt.Dimension(500, 400));
        jDialog_FileChooser.setModal(true);
        jDialog_FileChooser.setResizable(false);

        jFileChooser.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog_FileChooserLayout = new javax.swing.GroupLayout(jDialog_FileChooser.getContentPane());
        jDialog_FileChooser.getContentPane().setLayout(jDialog_FileChooserLayout);
        jDialog_FileChooserLayout.setHorizontalGroup(
            jDialog_FileChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog_FileChooserLayout.setVerticalGroup(
            jDialog_FileChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog_sendTask.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog_sendTask.setTitle(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jDialog_sendTask.title")); // NOI18N
        jDialog_sendTask.setLocationByPlatform(true);
        jDialog_sendTask.setMinimumSize(new java.awt.Dimension(588, 540));
        jDialog_sendTask.setModal(true);
        jDialog_sendTask.setResizable(false);

        jLabelError.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabelError.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jLabelError.text")); // NOI18N

        AddTask.setBackground(java.awt.Color.white);
        AddTask.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        AddTask.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.AddTask.text")); // NOI18N
        AddTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddTaskActionPerformed(evt);
            }
        });

        jTextFieldTask.setEditable(false);
        jTextFieldTask.setColumns(15);
        jTextFieldTask.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jTextFieldTask.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jTextFieldTask.text")); // NOI18N

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jLabel1.text")); // NOI18N

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel3.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jLabel3.text")); // NOI18N

        jComboBox_classToExecute.setBackground(java.awt.Color.white);
        jComboBox_classToExecute.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jComboBox_classToExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_classToExecuteActionPerformed(evt);
            }
        });

        jComboBox_methodToExecute.setBackground(java.awt.Color.white);
        jComboBox_methodToExecute.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

        AtachsList.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.AtachsList.border.title"))); // NOI18N
        AtachsList.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        AtachsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(AtachsList);

        AddAtach.setBackground(java.awt.Color.white);
        AddAtach.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        AddAtach.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.AddAtach.text")); // NOI18N
        AddAtach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAtachActionPerformed(evt);
            }
        });

        RemoveAtach.setBackground(java.awt.Color.white);
        RemoveAtach.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        RemoveAtach.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.RemoveAtach.text")); // NOI18N
        RemoveAtach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveAtachActionPerformed(evt);
            }
        });

        jComboBox_taskGroup.setBackground(java.awt.Color.white);
        jComboBox_taskGroup.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jLabel4.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jLabel4.text")); // NOI18N

        Cancel.setBackground(java.awt.Color.white);
        Cancel.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        Cancel.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.Cancel.text")); // NOI18N
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        jButton_submit.setBackground(java.awt.Color.white);
        jButton_submit.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jButton_submit.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_submit.text")); // NOI18N
        jButton_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_submitActionPerformed(evt);
            }
        });

        jList_parameters.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jList_parameters.border.title"))); // NOI18N
        jList_parameters.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jList_parameters.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane12.setViewportView(jList_parameters);

        jButton_addArgument.setBackground(java.awt.Color.white);
        jButton_addArgument.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jButton_addArgument.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_addArgument.text")); // NOI18N
        jButton_addArgument.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_addArgumentMouseClicked(evt);
            }
        });

        jButton_rmArgument.setBackground(java.awt.Color.white);
        jButton_rmArgument.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jButton_rmArgument.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_rmArgument.text")); // NOI18N
        jButton_rmArgument.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_rmArgumentMouseClicked(evt);
            }
        });

        jLabel11.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jLabel11.text")); // NOI18N

        jCheckBoxSchedule.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jCheckBoxSchedule.text")); // NOI18N
        jCheckBoxSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxScheduleActionPerformed(evt);
            }
        });

        jCalendarSendTask.setEnabled(false);
        jCalendarSendTask.setMinSelectableDate(new java.util.Date(-62135765914000L));

        jButtonTime.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButtonTime.text")); // NOI18N
        jButtonTime.setEnabled(false);

        jTextFieldSchedule.setEditable(false);
        jTextFieldSchedule.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jTextFieldSchedule.text")); // NOI18N
        jTextFieldSchedule.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelError)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox_taskGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBoxSchedule)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCalendarSendTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonTime, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton_submit, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_classToExecute, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_methodToExecute, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jTextFieldTask)
                        .addGap(18, 18, 18)
                        .addComponent(AddTask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane12)
                            .addComponent(jScrollPane5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddAtach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(RemoveAtach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_addArgument, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_rmArgument, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {AddAtach, AddTask, Cancel, RemoveAtach, jButton_addArgument, jButton_rmArgument, jButton_submit});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_taskGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(AddAtach)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RemoveAtach, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton_addArgument)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_rmArgument))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTask, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddTask, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_classToExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox_methodToExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButtonTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_submit)
                            .addComponent(Cancel)))
                    .addComponent(jCheckBoxSchedule)
                    .addComponent(jLabel11)
                    .addComponent(jCalendarSendTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {AddAtach, AddTask, Cancel, RemoveAtach, jButton_addArgument, jButton_rmArgument, jButton_submit});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jScrollPane12, jScrollPane5});

        javax.swing.GroupLayout jDialog_sendTaskLayout = new javax.swing.GroupLayout(jDialog_sendTask.getContentPane());
        jDialog_sendTask.getContentPane().setLayout(jDialog_sendTaskLayout);
        jDialog_sendTaskLayout.setHorizontalGroup(
            jDialog_sendTaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialog_sendTaskLayout.setVerticalGroup(
            jDialog_sendTaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialog_manageGroups.setTitle(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jDialog_manageGroups.title")); // NOI18N
        jDialog_manageGroups.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jDialog_manageGroups.setModal(true);
        jDialog_manageGroups.setResizable(false);

        imagePane1.setBackground(new java.awt.Color(165, 213, 163));

        jComboBox_grpManager.setBackground(java.awt.Color.white);
        jComboBox_grpManager.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jComboBox_grpManager.setBorder(null);
        jComboBox_grpManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_grpManagerActionPerformed(evt);
            }
        });

        jTabbedPane_manageGroups.setBackground(java.awt.Color.white);
        jTabbedPane_manageGroups.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N

        imagePane2.setBackground(new java.awt.Color(165, 230, 162));

        jList_groupRequests.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jList_groupRequests.border.title"))); // NOI18N
        jList_groupRequests.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jList_groupRequests.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane7.setViewportView(jList_groupRequests);

        jList_acceptMembers.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jList_acceptMembers.border.title"))); // NOI18N
        jList_acceptMembers.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jList_acceptMembers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane8.setViewportView(jList_acceptMembers);

        jButton_toAccepted.setIcon(new javax.swing.ImageIcon(getClass().getResource("/colla/appl/developer_viewer/view/images/arrow_button_right.jpg"))); // NOI18N
        jButton_toAccepted.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_toAccepted.text")); // NOI18N
        jButton_toAccepted.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_toAcceptedMouseClicked(evt);
            }
        });

        jButton_fromAccept2Wating.setIcon(new javax.swing.ImageIcon(getClass().getResource("/colla/appl/developer_viewer/view/images/arrow_button_left.jpg"))); // NOI18N
        jButton_fromAccept2Wating.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_fromAccept2Wating.text")); // NOI18N
        jButton_fromAccept2Wating.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_fromAccept2WatingMouseClicked(evt);
            }
        });

        jList_refuse.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jList_refuse.border.title"))); // NOI18N
        jList_refuse.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jList_refuse.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane11.setViewportView(jList_refuse);

        jButton_toRefused.setIcon(new javax.swing.ImageIcon(getClass().getResource("/colla/appl/developer_viewer/view/images/arrow_button_right.jpg"))); // NOI18N
        jButton_toRefused.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_toRefused.text")); // NOI18N
        jButton_toRefused.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_toRefusedMouseClicked(evt);
            }
        });

        jButton_froRefuse2Wating.setIcon(new javax.swing.ImageIcon(getClass().getResource("/colla/appl/developer_viewer/view/images/arrow_button_left.jpg"))); // NOI18N
        jButton_froRefuse2Wating.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_froRefuse2Wating.text")); // NOI18N
        jButton_froRefuse2Wating.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_froRefuse2WatingMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout imagePane2Layout = new javax.swing.GroupLayout(imagePane2);
        imagePane2.setLayout(imagePane2Layout);
        imagePane2Layout.setHorizontalGroup(
            imagePane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(imagePane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imagePane2Layout.createSequentialGroup()
                        .addGroup(imagePane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton_toRefused, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_froRefuse2Wating, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                    .addGroup(imagePane2Layout.createSequentialGroup()
                        .addGroup(imagePane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(imagePane2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jButton_toAccepted, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton_fromAccept2Wating, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)))
                .addContainerGap())
        );
        imagePane2Layout.setVerticalGroup(
            imagePane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePane2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jButton_toAccepted, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_fromAccept2Wating, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(jButton_toRefused, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_froRefuse2Wating, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(imagePane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(imagePane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addGroup(imagePane2Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane_manageGroups.addTab(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.imagePane2.TabConstraints.tabTitle"), imagePane2); // NOI18N

        jSplitPane1.setDividerLocation(150);

        jTextPane_memberInfo.setEditable(false);
        jTextPane_memberInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jTextPane_memberInfo.border.title"))); // NOI18N
        jTextPane_memberInfo.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jScrollPane10.setViewportView(jTextPane_memberInfo);

        jSplitPane1.setRightComponent(jScrollPane10);

        jList_members.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jList_members.border.title"))); // NOI18N
        jList_members.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jList_members.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList_members.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList_membersValueChanged(evt);
            }
        });
        jScrollPane9.setViewportView(jList_members);

        jSplitPane1.setLeftComponent(jScrollPane9);

        javax.swing.GroupLayout imagePane3Layout = new javax.swing.GroupLayout(imagePane3);
        imagePane3.setLayout(imagePane3Layout);
        imagePane3Layout.setHorizontalGroup(
            imagePane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePane3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addContainerGap())
        );
        imagePane3Layout.setVerticalGroup(
            imagePane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePane3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane_manageGroups.addTab(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.imagePane3.TabConstraints.tabTitle"), imagePane3); // NOI18N

        jButton_okMngGrp.setBackground(java.awt.Color.white);
        jButton_okMngGrp.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jButton_okMngGrp.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_okMngGrp.text")); // NOI18N
        jButton_okMngGrp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_okMngGrpMouseClicked(evt);
            }
        });

        jButton_cancelMngGrp.setBackground(java.awt.Color.white);
        jButton_cancelMngGrp.setFont(new java.awt.Font("DejaVu Sans", 0, 15)); // NOI18N
        jButton_cancelMngGrp.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_cancelMngGrp.text")); // NOI18N
        jButton_cancelMngGrp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_cancelMngGrpMouseClicked(evt);
            }
        });

        jButton_applyGrpManager.setBackground(java.awt.Color.white);
        jButton_applyGrpManager.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jButton_applyGrpManager.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_applyGrpManager.text")); // NOI18N
        jButton_applyGrpManager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_applyGrpManagerMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel7.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jLabel7.text")); // NOI18N

        javax.swing.GroupLayout imagePane1Layout = new javax.swing.GroupLayout(imagePane1);
        imagePane1.setLayout(imagePane1Layout);
        imagePane1Layout.setHorizontalGroup(
            imagePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(imagePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imagePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePane1Layout.createSequentialGroup()
                            .addComponent(jButton_cancelMngGrp, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton_applyGrpManager)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton_okMngGrp, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jComboBox_grpManager, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTabbedPane_manageGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        imagePane1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_applyGrpManager, jButton_cancelMngGrp, jButton_okMngGrp});

        imagePane1Layout.setVerticalGroup(
            imagePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imagePane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(6, 6, 6)
                .addComponent(jComboBox_grpManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane_manageGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(imagePane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_cancelMngGrp)
                    .addComponent(jButton_applyGrpManager)
                    .addComponent(jButton_okMngGrp))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        imagePane1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton_applyGrpManager, jButton_cancelMngGrp, jButton_okMngGrp});

        javax.swing.GroupLayout jDialog_manageGroupsLayout = new javax.swing.GroupLayout(jDialog_manageGroups.getContentPane());
        jDialog_manageGroups.getContentPane().setLayout(jDialog_manageGroupsLayout);
        jDialog_manageGroupsLayout.setHorizontalGroup(
            jDialog_manageGroupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagePane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialog_manageGroupsLayout.setVerticalGroup(
            jDialog_manageGroupsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagePane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog_settings.setTitle(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jDialog_settings.title")); // NOI18N
        jDialog_settings.setModal(true);
        jDialog_settings.setResizable(false);

        imagePane4.setBackground(new java.awt.Color(99, 254, 99));
        imagePane4.setName(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.imagePane4.name")); // NOI18N

        jPanel2.setBackground(java.awt.Color.white);

        jButton_generalSettings.setBackground(java.awt.Color.white);
        jButton_generalSettings.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton_generalSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/colla/appl/developer_viewer/view/images/Gear-icon.png"))); // NOI18N
        jButton_generalSettings.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_generalSettings.text")); // NOI18N
        jButton_generalSettings.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_generalSettings.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_generalSettings.setSelected(true);
        jButton_generalSettings.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_generalSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_generalSettingsMouseClicked(evt);
            }
        });

        jButton2.setBackground(java.awt.Color.white);
        jButton2.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/colla/appl/developer_viewer/view/images/Network-Internet-Connection-icon.png"))); // NOI18N
        jButton2.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton2.text")); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton_generalSettings)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton_generalSettings});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_generalSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel_settingsMain.setBackground(java.awt.Color.white);
        jPanel_settingsMain.setLayout(new java.awt.CardLayout());

        jPanel_card1.setBackground(java.awt.Color.white);
        jPanel_card1.setName(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jPanel_card1.name")); // NOI18N

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel5.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jLabel5.text")); // NOI18N

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jLabel6.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jLabel6.text")); // NOI18N

        jTextField_resultsDir.setEditable(false);
        jTextField_resultsDir.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jTextField_resultsDir.text")); // NOI18N

        jButton_resultsDir.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_resultsDir.text")); // NOI18N
        jButton_resultsDir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_resultsDirMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jLabel10.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jLabel10.text")); // NOI18N

        jTextField_nonFileResultsDir.setEditable(false);
        jTextField_nonFileResultsDir.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jTextField_nonFileResultsDir.text")); // NOI18N

        jButton_ResultsDirNonFile.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_ResultsDirNonFile.text")); // NOI18N
        jButton_ResultsDirNonFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ResultsDirNonFileMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel_card1Layout = new javax.swing.GroupLayout(jPanel_card1);
        jPanel_card1.setLayout(jPanel_card1Layout);
        jPanel_card1Layout.setHorizontalGroup(
            jPanel_card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_card1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addGroup(jPanel_card1Layout.createSequentialGroup()
                        .addComponent(jTextField_resultsDir, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_resultsDir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_card1Layout.createSequentialGroup()
                        .addComponent(jTextField_nonFileResultsDir, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_ResultsDirNonFile, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_card1Layout.setVerticalGroup(
            jPanel_card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_card1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_resultsDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_resultsDir))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_card1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_nonFileResultsDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_ResultsDirNonFile))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        jPanel_settingsMain.add(jPanel_card1, "card1");

        jPanel_card2.setBackground(java.awt.Color.white);
        jPanel_card2.setName(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jPanel_card2.name")); // NOI18N

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jLabel9.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jLabel9.text")); // NOI18N

        jSpinner_portNumber.setModel(new javax.swing.SpinnerNumberModel(0, 0, 65534, 1));

        jLabel8.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jLabel8.text")); // NOI18N

        javax.swing.GroupLayout jPanel_card2Layout = new javax.swing.GroupLayout(jPanel_card2);
        jPanel_card2.setLayout(jPanel_card2Layout);
        jPanel_card2Layout.setHorizontalGroup(
            jPanel_card2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_card2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_card2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_card2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner_portNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_card2Layout.setVerticalGroup(
            jPanel_card2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_card2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel_card2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jSpinner_portNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(211, Short.MAX_VALUE))
        );

        jPanel_settingsMain.add(jPanel_card2, "card2");

        jButton_okSettings.setBackground(java.awt.Color.white);
        jButton_okSettings.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jButton_okSettings.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_okSettings.text")); // NOI18N
        jButton_okSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_okSettingsMouseClicked(evt);
            }
        });

        jButton_cancelSettings.setBackground(java.awt.Color.white);
        jButton_cancelSettings.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        jButton_cancelSettings.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jButton_cancelSettings.text")); // NOI18N
        jButton_cancelSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_cancelSettingsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout imagePane4Layout = new javax.swing.GroupLayout(imagePane4);
        imagePane4.setLayout(imagePane4Layout);
        imagePane4Layout.setHorizontalGroup(
            imagePane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePane4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(imagePane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(imagePane4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_cancelSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_okSettings))
                    .addComponent(jPanel_settingsMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        imagePane4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_cancelSettings, jButton_okSettings});

        imagePane4Layout.setVerticalGroup(
            imagePane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePane4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel_settingsMain, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(imagePane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_okSettings)
                    .addComponent(jButton_cancelSettings))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog_settingsLayout = new javax.swing.GroupLayout(jDialog_settings.getContentPane());
        jDialog_settings.getContentPane().setLayout(jDialog_settingsLayout);
        jDialog_settingsLayout.setHorizontalGroup(
            jDialog_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagePane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog_settingsLayout.setVerticalGroup(
            jDialog_settingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imagePane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jSplitPane_desktopAndContacts.setDividerLocation(250);

        jSplitPane_contactsAndHosts.setBackground(java.awt.Color.green);
        jSplitPane_contactsAndHosts.setDividerLocation(300);
        jSplitPane_contactsAndHosts.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel1.setBackground(new java.awt.Color(234, 235, 237));

        jList_contacts.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jList_contacts.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jList_contacts.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "---Online---", "---Offline---" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList_contacts.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList_contacts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList_contactsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList_contacts);

        jComboBox_groups.setBackground(java.awt.Color.white);
        jComboBox_groups.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jComboBox_groups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_groupsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(jComboBox_groups, javax.swing.GroupLayout.Alignment.LEADING, 0, 226, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_groups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane_contactsAndHosts.setTopComponent(jPanel1);

        jPanel_myHosts.setBackground(new java.awt.Color(234, 235, 237));

        jComboBox_hosts.setBackground(java.awt.Color.white);
        jComboBox_hosts.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jComboBox_hosts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_hostsActionPerformed(evt);
            }
        });

        jTextArea_hostProp.setEditable(false);
        jTextArea_hostProp.setColumns(14);
        jTextArea_hostProp.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jTextArea_hostProp.setRows(5);
        jTextArea_hostProp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jScrollPane4.setViewportView(jTextArea_hostProp);

        javax.swing.GroupLayout jPanel_myHostsLayout = new javax.swing.GroupLayout(jPanel_myHosts);
        jPanel_myHosts.setLayout(jPanel_myHostsLayout);
        jPanel_myHostsLayout.setHorizontalGroup(
            jPanel_myHostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_myHostsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_myHostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(jComboBox_hosts, javax.swing.GroupLayout.Alignment.LEADING, 0, 226, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel_myHostsLayout.setVerticalGroup(
            jPanel_myHostsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_myHostsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_hosts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane_contactsAndHosts.setRightComponent(jPanel_myHosts);

        jSplitPane_desktopAndContacts.setLeftComponent(jSplitPane_contactsAndHosts);

        jSplitPane2.setDividerLocation(400);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jDesktopPane_desktop.setBackground(java.awt.Color.white);
        jSplitPane2.setTopComponent(jDesktopPane_desktop);

        jTextPane_info.setEditable(false);
        jTextPane_info.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jTextPane_info.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextPane_infoMouseClicked(evt);
            }
        });
        jTextPane_info.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTextPane_infoMouseMoved(evt);
            }
        });
        jScrollPane13.setViewportView(jTextPane_info);

        jSplitPane2.setRightComponent(jScrollPane13);

        jSplitPane_desktopAndContacts.setRightComponent(jSplitPane2);

        jMenuBar1.setForeground(new java.awt.Color(213, 220, 240));

        jMenu1.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jMenu1.text")); // NOI18N
        jMenu1.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

        jMenuItem_SendTask.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jMenuItem_SendTask.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jMenuItem_SendTask.text")); // NOI18N
        jMenuItem_SendTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_SendTaskActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_SendTask);

        jMenuItem_Results.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jMenuItem_Results.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jMenuItem_Results.text")); // NOI18N
        jMenuItem_Results.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ResultsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_Results);

        jMenuItem_quit.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jMenuItem_quit.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jMenuItem_quit.text")); // NOI18N
        jMenuItem_quit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem_quitMouseReleased(evt);
            }
        });
        jMenu1.add(jMenuItem_quit);

        jMenuBar1.add(jMenu1);

        jMenu_groups.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jMenu_groups.text")); // NOI18N
        jMenu_groups.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

        jMenuItem_joinGroup.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jMenuItem_joinGroup.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jMenuItem_joinGroup.text")); // NOI18N
        jMenuItem_joinGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem_joinGroupMouseReleased(evt);
            }
        });
        jMenu_groups.add(jMenuItem_joinGroup);

        jMenuItem_createGroup.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jMenuItem_createGroup.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jMenuItem_createGroup.text")); // NOI18N
        jMenuItem_createGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem_createGroupMouseReleased(evt);
            }
        });
        jMenu_groups.add(jMenuItem_createGroup);

        jMenuItem_manageGroups.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jMenuItem_manageGroups.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jMenuItem_manageGroups.text")); // NOI18N
        jMenuItem_manageGroups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_manageGroupsActionPerformed(evt);
            }
        });
        jMenu_groups.add(jMenuItem_manageGroups);

        jMenuBar1.add(jMenu_groups);

        jMenu_tools.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jMenu_tools.text")); // NOI18N
        jMenu_tools.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

        jMenuItem_settings.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jMenuItem_settings.setText(org.openide.util.NbBundle.getMessage(DeveloperViewerGUI.class, "DeveloperViewerGUI.jMenuItem_settings.text")); // NOI18N
        jMenuItem_settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_settingsActionPerformed(evt);
            }
        });
        jMenu_tools.add(jMenuItem_settings);

        jMenuBar1.add(jMenu_tools);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane_desktopAndContacts, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane_desktopAndContacts, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_createGroupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem_createGroupMouseReleased
        Point point = this.getLocation();
        Dimension parentSize = this.getSize();
        jDialog_creatGroup.setLocation(point.x + parentSize.width / 4, point.y + parentSize.width / 4);
        jDialog_creatGroup.setVisible(true);
    }//GEN-LAST:event_jMenuItem_createGroupMouseReleased

    private void jButton_cancelCreateGroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cancelCreateGroupMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            jTextField_groupName.setText("");
            jDialog_creatGroup.dispose();
        }
    }//GEN-LAST:event_jButton_cancelCreateGroupMouseClicked

    private void jButton_cancelCreateGroupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_cancelCreateGroupKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            jTextField_groupName.setText("");
            jDialog_creatGroup.dispose();
        }
    }//GEN-LAST:event_jButton_cancelCreateGroupKeyReleased

    private void jButton_createGroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_createGroupMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            createGroup(jTextField_groupName.getText());
        }
    }//GEN-LAST:event_jButton_createGroupMouseClicked

    private void jButton_createGroupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_createGroupKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            createGroup(jTextField_groupName.getText());
        }
    }//GEN-LAST:event_jButton_createGroupKeyReleased

    public void updatejComboBox_hosts(String contactName) {
        if (devViewer.getContact(contactName) != null) {
            this.jComboBox_hosts.removeAllItems();
            this.jTextArea_hostProp.setText("");
            for (CollAHost host : devViewer.getContact(contactName).getHosts()) {
                jComboBox_hosts.addItem(host.getName());
            }
        } else if (devViewer.getUser().getName().equals(contactName)) {
            this.jComboBox_hosts.removeAllItems();
            for (CollAHost host : devViewer.getUser().getHosts()) {
                jComboBox_hosts.addItem(host.getName());
            }
        }
    }

    /**
     * handle with groups combobox selection
     */
    private void jComboBox_groupsActions() {
        if (jComboBox_groups.getSelectedItem() != null) {
            String groupName = (String) jComboBox_groups.getSelectedItem();
            if ((groupName != null) && (devViewer.getUser().getGroups().get(groupName) != null)) {
                java.util.List<String> userSet = devViewer.getUser().getGroups().get(groupName).getMembers();
                Set<String> online = new TreeSet<String>();
                Set<String> offline = new TreeSet<String>();
                // refresh contacts list for the selected group

                //first this client itself
                online.add(this.thisUserName);
                if (userSet != null) {
                    for (String name : userSet) {
                        if (this.devViewer.getContact(name) != null) {
                            if (this.devViewer.getContact(name).isOnline()) {
                                online.add(name);
                                chatWindow.addContact(name, groupName);
                            } else {
                                offline.add(name);
                                chatWindow.removeContact(name);
                            }
                        }
                    }
                    DefaultListModel<String> listModel;
                    listModel = (DefaultListModel<String>) jList_contacts.getModel();
                    listModel.clear();
                    listModel.addElement("---Online---");
                    for (String name : online) {
                        listModel.addElement(name);
                    }
                    listModel.addElement("---Offline---");
                    for (String name : offline) {
                        listModel.addElement(name);
                    }
                    jList_contacts.repaint();
                }
            }
            jComboBox_groups.repaint();
        }
    }

    private void jMenuItem_quitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem_quitMouseReleased
        shutdown();
    }//GEN-LAST:event_jMenuItem_quitMouseReleased

    private void jMenuItem_joinGroupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem_joinGroupMouseReleased
        Point point = this.getLocation();
        Dimension parentSize = this.getSize();
        jDialog_joinGroup.setLocation(point.x + parentSize.width / 4, point.y + parentSize.height / 10);
        devViewer.getGroupsListFromServer();
        jTextField_groupSearch.setText("[Search]");
        jDialog_joinGroup.pack();
        jDialog_joinGroup.setVisible(true);
    }//GEN-LAST:event_jMenuItem_joinGroupMouseReleased

    private void jButton_clearSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_clearSearchMouseClicked
        jTextField_groupSearch.setText("[Search]");
        DefaultListModel<String> listModel = (DefaultListModel<String>) jList_listJoinGroup.getModel();
        listModel.removeAllElements();
        if (groupsName != null) {
            for (String i : groupsName) {
                listModel.addElement(i);
            }
        }
        jList_listJoinGroup.repaint();
    }//GEN-LAST:event_jButton_clearSearchMouseClicked

    private void jButton_searchGroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_searchGroupMouseClicked
        String groupSearch = jTextField_groupSearch.getText();
        if (!groupSearch.equals("[Search]") && !groupSearch.equals("")) {
            DefaultListModel<String> listModel = (DefaultListModel<String>) jList_listJoinGroup.getModel();
            listModel.removeAllElements();
            for (String i : groupsName) {
                if (i.toLowerCase().startsWith(groupSearch.toLowerCase())) {
                    listModel.addElement(i);
                }
            }
            jList_listJoinGroup.repaint();
        }
    }//GEN-LAST:event_jButton_searchGroupMouseClicked

    private void jButton_cancelJoinGroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cancelJoinGroupMouseClicked
        jTextField_groupSearch.setText("[Śearch]");
        jDialog_joinGroup.dispose();
    }//GEN-LAST:event_jButton_cancelJoinGroupMouseClicked

    private void jButton_joinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_joinMouseClicked
        if (jList_listJoinGroup.getSelectedIndex() >= 0) {
            String groupName = (String) jList_listJoinGroup.getSelectedValue();
            if (groupName != null) {
                this.closeJoinGroupDialog();
                this.displayMessage("Sending request to join group " + groupName + " ...");
                devViewer.joinGroup(groupName);
            }
        }
    }//GEN-LAST:event_jButton_joinMouseClicked

    private void jList_contactsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList_contactsMouseClicked
        int comboIndex;
        if (jList_contacts.getSelectedValue() != null) {
            String contactName = (String) jList_contacts.getSelectedValue();
            if (!contactName.equals("---Online---") && !contactName.equals("---Offline---")) {
                if (evt.getClickCount() == 1) {
                    updatejComboBox_hosts(contactName);
                } else if (!contactName.equals(this.thisUserName) && evt.getClickCount() == 2) {
                    comboIndex = jComboBox_groups.getSelectedIndex();
                    String groupname = (String) jComboBox_groups.getItemAt(comboIndex);
                    java.util.List<String> groupMembers = this.devViewer.getUser().getGroups().get(groupname).getMembers();
                    CollAUser usr = this.devViewer.getContact(contactName);
                    chatWindow.addContact(usr.getName(), groupname);
                    chatWindow.showMessage(contactName, "");
                }
            }
        }
    }//GEN-LAST:event_jList_contactsMouseClicked

    private void jComboBox_groupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_groupsActionPerformed
        jComboBox_groupsActions();
    }//GEN-LAST:event_jComboBox_groupsActionPerformed

    private void jComboBox_hostsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_hostsActionPerformed
        if (jList_contacts.getSelectedValue() != null) {
            String contactName = (String) jList_contacts.getSelectedValue();
            if (jComboBox_hosts.getSelectedItem() != null) {
                String hostName = (String) jComboBox_hosts.getSelectedItem();
                if (devViewer.getUser().getName().equals(contactName)) {
                    String properties = devViewer.getUser().getHost(hostName).getSystemProperties();
                    jTextArea_hostProp.setText(properties);
                } else if (devViewer.getContact(contactName) != null) {
                    String properties = devViewer.getContact(contactName).getHost(hostName).getSystemProperties();
                    jTextArea_hostProp.setText(properties);
                }
            }
        }
    }//GEN-LAST:event_jComboBox_hostsActionPerformed

    /**
     * to finish conflicts with another classes
     *
     * @param visibility true to show or false to hide
     */
    @Override
    public void setVisible(boolean visibility) {
        super.setVisible(true);
    }

    /**
     * Retrive classes and their methods from a jar file.
     *
     * @return an array containing the classes inside a jar file
     */
    private ArrayList<String> getClassesInsideJar() {
        ArrayList<String> classes = new ArrayList<String>();
        try {
            this.dependencyFiles.clear();
            for (int i = 0; i < dependencyModel.getSize(); i++) {
                this.dependencyFiles.add((File) dependencyModel.get(i));
            }

            URL[] urls = new URL[dependencyFiles.size() + 1];

            urls[0] = taskFile.toURL();

            for (int i = 0; i < dependencyFiles.size(); i++) {
                System.err.println(dependencyFiles.get(i).getAbsolutePath());
                urls[i + 1] = dependencyFiles.get(i).toURL();
            }

            JarFile jar = new JarFile(this.taskFile.getAbsolutePath());
            for (Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements();) {
                JarEntry entry = entries.nextElement();
                String file = entry.getName();
                String fileNameLastPart;
                if (file.endsWith(".class")) {
                    String classname = file.replace('/', '.').substring(0, file.length() - 6);
                    fileNameLastPart = file.substring(0, file.length() - 6);
                    if (file.lastIndexOf(File.separator) > -1) {
                        fileNameLastPart = file.substring(file.lastIndexOf(File.separator) + 1, file.length() - 6);
                    }

                    classes.add(fileNameLastPart);

                    URLClassLoader urlCL = new URLClassLoader(urls);
                    ArrayList<Method> methodsArray = new ArrayList<Method>();
                    try {
                        Class<?> c = urlCL.loadClass(classname);
                        methodsArray.addAll(Arrays.asList(c.getMethods()));
                        methods.put(fileNameLastPart, methodsArray);
                    } catch (ClassNotFoundException cnfe) {
//                        cnfe.printStackTrace();
                    }
                }
            }
        } catch (IOException io) {
//            io.printStackTrace();
        }
        return classes;
    }

    private void jMenuItem_SendTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_SendTaskActionPerformed
        if (devViewer.getUser().getGroups().size() > 0) {
            Point point = this.getLocation();
            Dimension parentSize = this.getSize();
            jDialog_sendTask.setLocation(point.x, point.y);
            jComboBox_taskGroup.removeAllItems();
            for (String gs : devViewer.getUser().getGroups().keySet()) {
                if (gs.length() > 2) {
                    jComboBox_taskGroup.addItem(gs);
                }
            }
            jDialog_sendTask.pack();            
            jCalendarSendTask.setMinSelectableDate(new Date());
            jDialog_sendTask.setVisible(true);
        } else {
            this.displayMessage("To send a task you must be part of a group.");
        }
    }//GEN-LAST:event_jMenuItem_SendTaskActionPerformed

    private void AddTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddTaskActionPerformed
        this.jFileChooser.setFileFilter(new JarFileChooserFilter());
        this.jFileChooser.setAcceptAllFileFilterUsed(false);
        this.jFileChooser.setDialogTitle("Select task jar");
        int returnFileChooser = jFileChooser.showOpenDialog(this);
        if (returnFileChooser == JFileChooser.APPROVE_OPTION) {
            taskFile = jFileChooser.getSelectedFile();
            clearTaskFields();
            if (taskFile.getAbsolutePath().endsWith(".jar")) {
                jTextFieldTask.setText(taskFile.getAbsolutePath());
                ArrayList<String> classes = getClassesInsideJar();
                for (String i : classes) {
                    jComboBox_classToExecute.addItem(i);
                }
            } else {
                jLabelError.setForeground(Color.red);
                jLabelError.setText("Task must be a JAR file");
            }
        }
    }//GEN-LAST:event_AddTaskActionPerformed

    private void AddAtachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAtachActionPerformed
        this.jFileChooser.setFileFilter(new JarFileChooserFilter());
        this.jFileChooser.setAcceptAllFileFilterUsed(false);
        this.jFileChooser.setDialogTitle("Select jar dependency");
        int returnFileChooser = jFileChooser.showOpenDialog(this);
        if (returnFileChooser == JFileChooser.APPROVE_OPTION) {
            dependencyModel.addElement(jFileChooser.getSelectedFile());
        } else {
            //System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_AddAtachActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        this.dependencyModel.clear();
        clearTaskFields();
        jDialog_sendTask.dispose();
    }//GEN-LAST:event_CancelActionPerformed

    private void jComboBox_classToExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_classToExecuteActionPerformed
        String tempClass = (String) jComboBox_classToExecute.getSelectedItem();
        jComboBox_methodToExecute.removeAllItems();
        if (tempClass != null) {
            if (methods.get(tempClass) != null) {
                for (Method i : methods.get(tempClass)) {
                    jComboBox_methodToExecute.addItem(i.getName());
                }
            }
        }
    }//GEN-LAST:event_jComboBox_classToExecuteActionPerformed

    private void RemoveAtachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveAtachActionPerformed
        int i = this.AtachsList.getSelectedIndex();
        if (i != -1) {
            this.dependencyModel.remove(i);
        }
    }//GEN-LAST:event_RemoveAtachActionPerformed

    /**
     * Sends task to execute in a host
     *
     * @param evt
     */
    private void jButton_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_submitActionPerformed
        String method = (String) jComboBox_methodToExecute.getSelectedItem();
        this.chosenGroup = (String) jComboBox_taskGroup.getSelectedItem();
        if (this.chosenGroup != null && this.chosenGroup.length() > 2) {
            if (this.taskFile != null && method != null) {
                String classToExecute = (String) jComboBox_classToExecute.getSelectedItem();
                String methodToExecute = method;
                //System.out.println(methodToExecute);
                if (classToExecute != null && classToExecute.length() > 0) {
                    // Get task files
                    this.dependencyFiles.clear();
                    for (int i = 0; i < dependencyModel.getSize(); i++) {
                        this.dependencyFiles.add((File) dependencyModel.get(i));
                    }
                    this.argumentsFiles.clear();
                    for (int i = 0; i < argumentsModel.getSize(); i++) {
                        this.argumentsFiles.add((File) argumentsModel.get(i));
                    }
                    if (this.jCheckBoxSchedule.isSelected()) {
                        try {
                            this.selectedDate = dateTimeFormat.parse(jTextFieldSchedule.getText());
                        } catch (ParseException ex) {
                            Exceptions.printStackTrace(ex);
                            this.selectedDate = null;
                        }
                    } else {
                        this.selectedDate = null;
                    }

                    this.devViewer.getAvailableHostsOnServer(taskFile, dependencyFiles, argumentsFiles, classToExecute, methodToExecute, chosenGroup, selectedDate);
                    /*
                     * Clear fields to send another task after that
                     */
                    this.dependencyModel.clear();
                    clearTaskFields();
                    jDialog_sendTask.dispose();
                } else {
                    this.jLabelError.setText("Error: Must select a class to execute.");
                    this.jLabelError.setForeground(Color.red);
                    this.jLabelError.setVisible(true);
                }
            } else {
                this.jLabelError.setText("Error: Must select a task.");
                this.jLabelError.setForeground(Color.red);
                this.jLabelError.setVisible(true);
            }
        }
    }//GEN-LAST:event_jButton_submitActionPerformed

    private void jMenuItem_ResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ResultsActionPerformed
        this.resultsWindow.setVisible(true);
    }//GEN-LAST:event_jMenuItem_ResultsActionPerformed

    private void jMenuItem_manageGroupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_manageGroupsActionPerformed
        this.redo_jDialog_manageGroups();
        Point point = this.getLocation();
        Dimension parentSize = this.getSize();
        jDialog_manageGroups.setLocation(point.x + parentSize.width / 4, point.y + parentSize.height / 10);
        this.jButton_cancelMngGrp.requestFocus();
        jDialog_manageGroups.repaint();
        jDialog_manageGroups.pack();
        this.jDialog_manageGroups.setVisible(true);
    }//GEN-LAST:event_jMenuItem_manageGroupsActionPerformed

    /**
     * redo lists at jDialog_manageGroups
     */
    private void redo_jDialog_manageGroups() {
        this.jTextPane_memberInfo.setText("");
        jComboBox_grpManager.removeAllItems();
        ((DefaultListModel) jList_acceptMembers.getModel()).removeAllElements();
        ((DefaultListModel) jList_groupRequests.getModel()).removeAllElements();
        ((DefaultListModel) jList_refuse.getModel()).removeAllElements();
        for (String groupName : devViewer.getUser().getGroups().keySet()) {
            CollAGroup group = devViewer.getUser().getGroups().get(groupName);
            this.waitingMembers.put(groupName, group.getWaitingList());
        }

        ((DefaultListModel) jList_groupRequests.getModel()).removeAllElements();
        for (String groupName : devViewer.getUser().getGroups().keySet()) {
            this.jComboBox_grpManager.addItem(groupName);
        }
        if (this.jComboBox_grpManager.getItemCount() > 0) {
            this.jComboBox_grpManager.setSelectedIndex(0);
        }
        jDialog_manageGroups.repaint();
    }

    private void jButton_cancelMngGrpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cancelMngGrpMouseClicked
        jDialog_manageGroups.setVisible(false);
    }//GEN-LAST:event_jButton_cancelMngGrpMouseClicked

    private void jComboBox_grpManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_grpManagerActionPerformed
        String groupName;
        DefaultListModel<String> mod = (DefaultListModel<String>) jList_groupRequests.getModel();
        mod.removeAllElements();
        jTextPane_memberInfo.setText("");
        ((DefaultListModel<String>) jList_acceptMembers.getModel()).removeAllElements();
        ((DefaultListModel<String>) jList_refuse.getModel()).removeAllElements();
        ((DefaultListModel<String>) jList_members.getModel()).removeAllElements();
        if (this.jComboBox_grpManager.getSelectedItem() != null) {
            groupName = (String) jComboBox_grpManager.getSelectedItem();
            if (devViewer.getUser().getGroups().get(groupName).getAdminsList().contains(devViewer.getUser().getName())) {
                if (this.waitingMembers.size() > 0) {
                    for (String waitingName : this.waitingMembers.get(groupName)) {
                        mod.addElement(waitingName);
                    }
                }
                if (this.acceptedMembers.size() > 0) {
                    for (String acptMember : acceptedMembers.get(groupName)) {
                        ((DefaultListModel<String>) jList_acceptMembers.getModel()).addElement(acptMember);
                    }
                }
                if (this.refusedMembers.size() > 0) {
                    for (String refuseMember : refusedMembers.get(groupName)) {
                        ((DefaultListModel<String>) jList_refuse.getModel()).addElement(refuseMember);
                    }
                }
                jTabbedPane_manageGroups.setEnabledAt(0, true);
            } else {
                jTabbedPane_manageGroups.setEnabledAt(0, false);
                jTabbedPane_manageGroups.setSelectedIndex(1);
            }
            for (String memberName : this.devViewer.getUser().getGroups().get(groupName).getMembers()) {
                ((DefaultListModel<String>) jList_members.getModel()).addElement(memberName);
            }
        }
        jList_groupRequests.repaint();
    }//GEN-LAST:event_jComboBox_grpManagerActionPerformed

    private void jButton_toAcceptedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_toAcceptedMouseClicked
        DefaultListModel<String> requestMod = (DefaultListModel<String>) jList_groupRequests.getModel();
        DefaultListModel<String> acceptMod = (DefaultListModel<String>) jList_acceptMembers.getModel();
        int listIndex = jList_groupRequests.getSelectedIndex();
        if (listIndex >= 0) {
            if (requestMod.getElementAt(listIndex) != null) {
                String member = (String) requestMod.getElementAt(listIndex);
                acceptMod.addElement(member);
                requestMod.removeElementAt(listIndex);
            }
        }
    }//GEN-LAST:event_jButton_toAcceptedMouseClicked

    private void jButton_fromAccept2WatingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_fromAccept2WatingMouseClicked
        DefaultListModel<String> requestMod = (DefaultListModel<String>) jList_groupRequests.getModel();
        DefaultListModel<String> acceptMod = (DefaultListModel<String>) jList_acceptMembers.getModel();
        int listIndex = jList_acceptMembers.getSelectedIndex();
        if (listIndex >= 0) {
            if (acceptMod.getElementAt(listIndex) != null) {
                String member = (String) acceptMod.getElementAt(listIndex);
                requestMod.addElement(member);
                acceptMod.removeElementAt(listIndex);
            }
        }
    }//GEN-LAST:event_jButton_fromAccept2WatingMouseClicked

    private void jButton_toRefusedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_toRefusedMouseClicked
        DefaultListModel<String> requestMod = (DefaultListModel<String>) jList_groupRequests.getModel();
        DefaultListModel<String> declineMod = (DefaultListModel<String>) jList_refuse.getModel();
        int listIndex = jList_groupRequests.getSelectedIndex();
        if (listIndex >= 0) {
            if (requestMod.getElementAt(listIndex) != null) {
                String member = (String) requestMod.getElementAt(listIndex);
                declineMod.addElement(member);
                requestMod.removeElementAt(listIndex);
            }
        }
    }//GEN-LAST:event_jButton_toRefusedMouseClicked

    private void jButton_froRefuse2WatingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_froRefuse2WatingMouseClicked
        DefaultListModel<String> requestMod = (DefaultListModel<String>) jList_groupRequests.getModel();
        DefaultListModel<String> declineMod = (DefaultListModel<String>) jList_refuse.getModel();
        int listIndex = jList_refuse.getSelectedIndex();
        if (listIndex >= 0) {
            if (declineMod.getElementAt(listIndex) != null) {
                String member = (String) declineMod.getElementAt(listIndex);
                requestMod.addElement(member);
                declineMod.removeElementAt(listIndex);
            }
        }
    }//GEN-LAST:event_jButton_froRefuse2WatingMouseClicked

    private void jButton_okMngGrpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_okMngGrpMouseClicked
        this.applyChangeToGroup();
        this.jDialog_manageGroups.setVisible(false);
    }//GEN-LAST:event_jButton_okMngGrpMouseClicked

    private void jButton_applyGrpManagerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_applyGrpManagerMouseClicked
        this.applyChangeToGroup();
    }//GEN-LAST:event_jButton_applyGrpManagerMouseClicked

    private void applyChangeToGroup() {
        if (jComboBox_grpManager.getSelectedIndex() >= 0) {

            String groupName = (String) jComboBox_grpManager.getSelectedItem();

            this.acceptedMembers.clear();
            this.refusedMembers.clear();

            java.util.List<String> acceptList = new java.util.LinkedList<String>();
            java.util.List<String> declineList = new java.util.LinkedList<String>();


            DefaultListModel<String> acceptMod = (DefaultListModel<String>) jList_acceptMembers.getModel();
            DefaultListModel<String> declineMod = (DefaultListModel<String>) jList_refuse.getModel();

            for (Object i : acceptMod.toArray()) {
                acceptList.add((String) i);
            }

            for (Object i : declineMod.toArray()) {
                declineList.add((String) i);
            }

            acceptedMembers.put(groupName, acceptList);
            refusedMembers.put(groupName, declineList);

            acceptMod.removeAllElements();
            declineMod.removeAllElements();

            jDialog_manageGroups.repaint();

            devViewer.manageGroups(acceptedMembers, refusedMembers);

            this.acceptedMembers.clear();
            this.refusedMembers.clear();

            this.redo_jDialog_manageGroups();
        }

    }

    private void jList_membersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList_membersValueChanged
        if (jList_members.getSelectedValue() != null) {
            String memberName = (String) jList_members.getSelectedValue();
            CollAUser user;
            if (memberName.equals(this.devViewer.getUser().getName())) {
                user = this.devViewer.getUser();
            } else {
                user = this.devViewer.getContact(memberName);
            }
            //mount string with member informations
            String memberInfo = "username: " + user.getName() + "\n";
            memberInfo = memberInfo + "e-mail: " + user.getEmail() + "\n";
            memberInfo = memberInfo + "Country: " + user.getCountry() + "\n";
            memberInfo = memberInfo + "Offering " + user.getHosts().size() + " hosts\n";
            jTextPane_memberInfo.setText(memberInfo);
        }
    }//GEN-LAST:event_jList_membersValueChanged

    private void jButton_addArgumentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_addArgumentMouseClicked
        jFileChooser.removeChoosableFileFilter(new JarFileChooserFilter());
        jFileChooser.setAcceptAllFileFilterUsed(true);
        jFileChooser.setFileFilter(jFileChooser.getAcceptAllFileFilter());
        jFileChooser.setDialogTitle("Select file for argument");
        int returnFileChooser = jFileChooser.showOpenDialog(this);
        if (returnFileChooser == JFileChooser.APPROVE_OPTION) {
            argumentsModel.addElement(jFileChooser.getSelectedFile());
        } else {
            //System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_jButton_addArgumentMouseClicked

    private void jButton_rmArgumentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_rmArgumentMouseClicked
        int i = this.jList_parameters.getSelectedIndex();
        if (i != -1) {
            this.argumentsModel.remove(i);
        }
    }//GEN-LAST:event_jButton_rmArgumentMouseClicked

    private void jTextPane_infoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextPane_infoMouseMoved
        Point pt = new Point(evt.getX(), evt.getY());
        int pos = jTextPane_info.viewToModel(pt);
        Document doc = jTextPane_info.getDocument();
        if (doc instanceof DefaultStyledDocument) {
            DefaultStyledDocument hdoc = (DefaultStyledDocument) doc;
            Element e = hdoc.getCharacterElement(pos);
            AttributeSet attr = e.getAttributes();
            String[] href = (String[]) attr.getAttribute(HTML.Attribute.HREF);
            if (href != null) {
                jTextPane_info.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            } else {
                jTextPane_info.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }//GEN-LAST:event_jTextPane_infoMouseMoved

    private void jTextPane_infoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextPane_infoMouseClicked
        Point pt = new Point(evt.getX(), evt.getY());
        int pos = jTextPane_info.viewToModel(pt);
        Document doc = jTextPane_info.getDocument();
        if (doc instanceof DefaultStyledDocument) {
            DefaultStyledDocument hdoc = (DefaultStyledDocument) doc;
            Element e = hdoc.getCharacterElement(pos);
            AttributeSet attr = e.getAttributes();
            String[] href = (String[]) attr.getAttribute(HTML.Attribute.HREF);
            if (href != null) {
                String groupName = href[0];
                String taskName = href[1];
                jComboBox_taskGroup.setSelectedItem(groupName);
                resultsWindow.selectAndShowResult(groupName, taskName);
            }
        }
    }//GEN-LAST:event_jTextPane_infoMouseClicked

    private void jMenuItem_settingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_settingsActionPerformed
        Point point = this.getLocation();
        Dimension parentSize = this.getSize();
        jDialog_settings.setLocation(point.x + parentSize.width / 4, point.y + parentSize.height / 10);
        jButton_generalSettings.setSelected(true);
        initializeCard1();
        jDialog_settings.pack();
        jDialog_settings.setVisible(true);
    }//GEN-LAST:event_jMenuItem_settingsActionPerformed

    private void jButton_generalSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_generalSettingsMouseClicked
        initializeCard1();
        CardLayout cl = (CardLayout) (jPanel_settingsMain.getLayout());
        cl.show(jPanel_settingsMain, "card1");
    }//GEN-LAST:event_jButton_generalSettingsMouseClicked

    private void initializeCard1() {
        jTextField_resultsDir.setText(this.config.getResulstDir());
        jTextField_nonFileResultsDir.setText(this.config.getNonFileResulstDir());
    }

    private void jButton_cancelSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_cancelSettingsMouseClicked
        jDialog_settings.setVisible(false);
    }//GEN-LAST:event_jButton_cancelSettingsMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if (devViewer.getUser().hasValidIP()) {
            jSpinner_portNumber.setEnabled(true);
            jSpinner_portNumber.setValue(this.devViewer.getUser().getPort());
        } else {
            jSpinner_portNumber.setEnabled(false);
        }
        CardLayout cl = (CardLayout) (jPanel_settingsMain.getLayout());
        cl.show(jPanel_settingsMain, "card2");
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton_resultsDirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_resultsDirMouseClicked
        jFileChooser_resultDirectory.setVisible(true);
        int returnFileChooser = jFileChooser_resultDirectory.showOpenDialog(this);
        String dir = "";
        if (returnFileChooser == JFileChooser.APPROVE_OPTION) {
            dir = jFileChooser_resultDirectory.getSelectedFile().getAbsolutePath().toString();
        }
        this.jTextField_resultsDir.setText(dir);
    }//GEN-LAST:event_jButton_resultsDirMouseClicked

    private void jButton_okSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_okSettingsMouseClicked
        if (jPanel_card1.isVisible()) {
            getInfoFromCard1();
        } else if (jPanel_card2.isVisible()) {
            getInfoFromCard2();
        }
        this.jDialog_settings.setVisible(false);
    }//GEN-LAST:event_jButton_okSettingsMouseClicked

    private void jButton_ResultsDirNonFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ResultsDirNonFileMouseClicked
        jFileChooser_resultDirectory.setVisible(true);
        int returnFileChooser = jFileChooser_resultDirectory.showOpenDialog(this);
        String dir = "";
        if (returnFileChooser == JFileChooser.APPROVE_OPTION) {
            dir = jFileChooser_resultDirectory.getSelectedFile().getAbsolutePath().toString();
        }
        this.jTextField_nonFileResultsDir.setText(dir);
    }//GEN-LAST:event_jButton_ResultsDirNonFileMouseClicked

    private void jCheckBoxScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxScheduleActionPerformed
        if (jCheckBoxSchedule.isSelected()) {
            this.showScheduleOptions();
        } else {
            this.hideScheduleOptions();
        }
    }//GEN-LAST:event_jCheckBoxScheduleActionPerformed

    private void showScheduleOptions() {
        this.jCalendarSendTask.setEnabled(true);
        this.jButtonTime.setEnabled(true);
        this.jTextFieldSchedule.setEnabled(true);
    }

    private void hideScheduleOptions() {
        this.jCalendarSendTask.setEnabled(false);
        this.jButtonTime.setEnabled(false);
        this.jTextFieldSchedule.setEnabled(false);
    }

    private void getInfoFromCard1() {
        //System.err.println("card 1");
        this.config.setResultDir(jTextField_resultsDir.getText());
        this.config.setNonFileResultDir(jTextField_nonFileResultsDir.getText());
        this.resultsWindow.setResultsDir(this.config.getResulstDir());
        this.resultsWindow.setNonFileResultDir(this.config.getNonFileResulstDir());
    }

    private void getInfoFromCard2() {
        this.config.setPortNumber((Integer) jSpinner_portNumber.getValue());
    }

    /**
     * clear task related fields but dependencies in jDialog_sendTask
     */
    private void clearTaskFields() {
        this.jTextFieldTask.setText("");
        jComboBox_classToExecute.removeAllItems();
        jComboBox_methodToExecute.removeAllItems();
        jCheckBoxSchedule.setSelected(false);
        jTextFieldSchedule.setText("");
        this.hideScheduleOptions();
    }

    /**
     * Show a pop-up with information
     *
     * @param info information to be displayed
     */
    @Override
    public void displayMessage(String info) {
        TimeAndDate time = new TimeAndDate();
        StyledDocument doc = this.jTextPane_info.getStyledDocument();
        SimpleAttributeSet horario = new SimpleAttributeSet();
        SimpleAttributeSet msgAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(msgAttr, Color.BLACK);
        StyleConstants.setForeground(horario, Color.BLUE);
        StyleConstants.setFontFamily(msgAttr, "sansserif");
        StyleConstants.setFontFamily(horario, "sansserif");
        StyleConstants.setFontSize(msgAttr, 14);
        StyleConstants.setFontSize(horario, 14);
        try {
            doc.insertString(doc.getLength(), " " + time.getHour(), horario);
            doc.insertString(doc.getLength(), " " + info + "\n", msgAttr);
            this.jTextPane_info.setCaretPosition(doc.getLength());
        } catch (BadLocationException ble) {
//            ble.printStackTrace();
        }
    }

    @Override
    public void displayTaskResultInfo(String groupName, String taskName) {
        String preInfo = "Result for ";
        String postInfo = "has just arrived.\n";
        TimeAndDate time = new TimeAndDate();
        StyledDocument doc = this.jTextPane_info.getStyledDocument();
        SimpleAttributeSet hourAttr = new SimpleAttributeSet();
        SimpleAttributeSet msgAttr = new SimpleAttributeSet();
        StyleConstants.setForeground(msgAttr, Color.BLACK);
        StyleConstants.setForeground(hourAttr, Color.GRAY);
        StyleConstants.setFontFamily(msgAttr, "sansserif");
        StyleConstants.setFontFamily(hourAttr, "sansserif");
        StyleConstants.setFontSize(msgAttr, 14);
        StyleConstants.setFontSize(hourAttr, 14);

        MutableAttributeSet taskLink = new SimpleAttributeSet();
        String[] groupAndTask = new String[2];
        groupAndTask[0] = groupName;
        groupAndTask[1] = taskName;
        taskLink.addAttribute(HTML.Attribute.HREF, groupAndTask);
        StyleConstants.setUnderline(taskLink, true);
        StyleConstants.setForeground(taskLink, Color.BLUE);
        StyleConstants.setFontFamily(taskLink, "sansserif");
        StyleConstants.setFontSize(taskLink, 14);

        try {
            doc.insertString(doc.getLength(), " " + time.getHour(), hourAttr);
            doc.insertString(doc.getLength(), " " + preInfo, msgAttr);
            doc.insertString(doc.getLength(), " " + taskName, taskLink);
            doc.insertString(doc.getLength(), " " + postInfo, msgAttr);
            this.jTextPane_info.setCaretPosition(doc.getLength());
        } catch (BadLocationException ble) {
//            ble.printStackTrace();
        }
    }

    @Override
    public void update(Observable subject, Object interest) {
        /**
         * Any notification that may come in the future must be handled
         */
        if (interest instanceof String) {
            String info = (String) interest;
            this.displayMessage(info);
        }
    }

    @Override
    public void displayChatMessage(String sender, String message) {
        this.chatWindow.showMessage(sender, message);
    }

    /**
     * implemenst a listener to the list of members of groups
     */
    class JList_contacts_Listener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            //gets double click and opens a chatWindow with the selected contact
        }
    }

    /**
     * Validate and set the datetime field on the screen given a datetime
     * string.
     *
     * @param dateTime The datetime string
     */
    public void setDateTime(String dateTimeString) {
        Date dateTime = null;
        try {
            if ((dateTimeString != null) && (dateTimeString.length() > 0)) {
                dateTime = dateTimeFormat.parse(dateTimeString);
            }
        } catch (Exception e) {
            dateTime = null;
        }
        this.setDateTime(dateTime);
    }

    /**
     * Validate and set the datetime field on the screen given a date.
     *
     * @param dateTime The datetime object
     */
    public void setDateTime(Date dateTime) {
        String dateTimeString = "";
        if (dateTime != null) {
            dateTimeString = dateTimeFormat.format(dateTime);
        }
        jTextFieldSchedule.setText(dateTimeString);

        JTimeButton btnTime = (JTimeButton) jButtonTime;

        btnTime.setTargetDate(dateTime);
    }
    private HashMap<String, java.util.List<String>> acceptedMembers;
    private HashMap<String, java.util.List<String>> refusedMembers;
    private HashMap<String, java.util.List<String>> waitingMembers;
    private ChatWindow chatWindow;
    private ResultsWindow resultsWindow;
    private Set<String> groupsName;
    private String thisUserName;
    private DeveloperViewerController devViewer;
    //Variables used to send a task
    private HashMap<String, ArrayList<Method>> methods; //key: classToExecute, value: methods of the class
    private File taskFile;
    private String chosenGroup;
    private ArrayList<File> dependencyFiles;
    private DefaultListModel<File> dependencyModel;
    private DefaultListModel<File> argumentsModel;
    private ArrayList<File> argumentsFiles;
    private Date selectedDate;
    public static DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);
    //Variables to manage settings
    private JFileChooser jFileChooser_resultDirectory;
    private final String generalSettings = "General settings";
    private ClientConfigurations config;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddAtach;
    private javax.swing.JButton AddTask;
    private javax.swing.JList<File> AtachsList;
    private javax.swing.JButton Cancel;
    private javax.swing.JButton RemoveAtach;
    private colla.kernel.util.ImagePane imagePane1;
    private colla.kernel.util.ImagePane imagePane2;
    private colla.kernel.util.ImagePane imagePane3;
    private colla.kernel.util.ImagePane imagePane4;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonTime;
    private javax.swing.JButton jButton_ResultsDirNonFile;
    private javax.swing.JButton jButton_addArgument;
    private javax.swing.JButton jButton_applyGrpManager;
    private javax.swing.JButton jButton_cancelCreateGroup;
    private javax.swing.JButton jButton_cancelJoinGroup;
    private javax.swing.JButton jButton_cancelMngGrp;
    private javax.swing.JButton jButton_cancelSettings;
    private javax.swing.JButton jButton_clearSearch;
    private javax.swing.JButton jButton_createGroup;
    private javax.swing.JButton jButton_froRefuse2Wating;
    private javax.swing.JButton jButton_fromAccept2Wating;
    private javax.swing.JButton jButton_generalSettings;
    private javax.swing.JButton jButton_join;
    private javax.swing.JButton jButton_okMngGrp;
    private javax.swing.JButton jButton_okSettings;
    private javax.swing.JButton jButton_resultsDir;
    private javax.swing.JButton jButton_rmArgument;
    private javax.swing.JButton jButton_searchGroup;
    private javax.swing.JButton jButton_submit;
    private javax.swing.JButton jButton_toAccepted;
    private javax.swing.JButton jButton_toRefused;
    private com.toedter.calendar.JCalendar jCalendarSendTask;
    private javax.swing.JCheckBox jCheckBoxSchedule;
    private javax.swing.JComboBox<String> jComboBox_classToExecute;
    private javax.swing.JComboBox<String> jComboBox_groups;
    private javax.swing.JComboBox<String> jComboBox_grpManager;
    private javax.swing.JComboBox<String> jComboBox_hosts;
    private javax.swing.JComboBox<String> jComboBox_methodToExecute;
    private javax.swing.JComboBox<String> jComboBox_taskGroup;
    private javax.swing.JDesktopPane jDesktopPane_desktop;
    private javax.swing.JDialog jDialog_FileChooser;
    private javax.swing.JDialog jDialog_creatGroup;
    private javax.swing.JDialog jDialog_joinGroup;
    private javax.swing.JDialog jDialog_manageGroups;
    private javax.swing.JDialog jDialog_sendTask;
    private javax.swing.JDialog jDialog_settings;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JList<String> jList_acceptMembers;
    private javax.swing.JList<String> jList_contacts;
    private javax.swing.JList<String> jList_groupRequests;
    private javax.swing.JList<String> jList_listJoinGroup;
    private javax.swing.JList<String> jList_members;
    private javax.swing.JList<File> jList_parameters;
    private javax.swing.JList<String> jList_refuse;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem_Results;
    private javax.swing.JMenuItem jMenuItem_SendTask;
    private javax.swing.JMenuItem jMenuItem_createGroup;
    private javax.swing.JMenuItem jMenuItem_joinGroup;
    private javax.swing.JMenuItem jMenuItem_manageGroups;
    private javax.swing.JMenuItem jMenuItem_quit;
    private javax.swing.JMenuItem jMenuItem_settings;
    private javax.swing.JMenu jMenu_groups;
    private javax.swing.JMenu jMenu_tools;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel_card1;
    private javax.swing.JPanel jPanel_card2;
    private javax.swing.JPanel jPanel_myHosts;
    private javax.swing.JPanel jPanel_settingsMain;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSpinner_portNumber;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane_contactsAndHosts;
    private javax.swing.JSplitPane jSplitPane_desktopAndContacts;
    private javax.swing.JTabbedPane jTabbedPane_manageGroups;
    private javax.swing.JTextArea jTextArea_hostProp;
    private javax.swing.JTextField jTextFieldSchedule;
    private javax.swing.JTextField jTextFieldTask;
    private javax.swing.JTextField jTextField_groupName;
    private javax.swing.JTextField jTextField_groupSearch;
    private javax.swing.JTextField jTextField_nonFileResultsDir;
    private javax.swing.JTextField jTextField_resultsDir;
    private javax.swing.JTextPane jTextPane_info;
    private javax.swing.JTextPane jTextPane_memberInfo;
    // End of variables declaration//GEN-END:variables
}
