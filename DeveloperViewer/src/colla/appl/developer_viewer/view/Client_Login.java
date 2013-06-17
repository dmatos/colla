/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Client_Login.java
 *
 * Created on 19/12/2011, 23:14:33
 */
package colla.appl.developer_viewer.view;

import colla.appl.developer_viewer.DevViewerLogin;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

/**
 *
 * @author dmatos
 */
public class Client_Login extends javax.swing.JFrame implements Observer{

    /** Creates new form Client_Login */
    /*
     * Construtor da GUI de Login
     */
    public Client_Login(DevViewerLogin loginViewer) {        
        super("DeveloperViewer");
        this.devLogin = loginViewer;
        this.initializeLookAndFeel();
        this.setIconImage(new ImageIcon(getClass().getResource(BackGround.COLLA_LOGO_ICON.getPath())).getImage());        
        this.initComponents();        
        this.setLocation(400, 150);//       
        this.requestFocus();
        this.displayMessage("Welcome!");
        this.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog_signUp = new javax.swing.JDialog();
        jPanel1 = new colla.kernel.util.ImagePane(BackGround.GREEN_SPIRAL.getPath());
        jLabel1 = new javax.swing.JLabel();
        jLabel_signUpWarning = new javax.swing.JLabel();
        jTextField_signUpName = new javax.swing.JTextField();
        jPasswordField_signUpPass = new javax.swing.JPasswordField();
        jPasswordField_signUpRepeatPass = new javax.swing.JPasswordField();
        jTextField_email = new javax.swing.JTextField();
        jComboBox_countries = new javax.swing.JComboBox();
        jButton_signUp = new javax.swing.JButton();
        jButton_cancel = new javax.swing.JButton();
        jTextField_company = new javax.swing.JTextField();
        jPanel_fundo = new colla.kernel.util.ImagePane(BackGround.GREEN_SPIRAL.getPath());
        jTextField_username = new javax.swing.JTextField();
        jButton_login = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_status = new javax.swing.JTextArea();
        jPasswordField_password = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel_signup = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jDialog_signUp.setTitle("Signup");
        jDialog_signUp.setLocationByPlatform(true);
        jDialog_signUp.setModal(true);
        jDialog_signUp.setResizable(false);

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jLabel1.setText("Please, enter the required information:");

        jLabel_signUpWarning.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        jLabel_signUpWarning.setForeground(java.awt.Color.red);
        jLabel_signUpWarning.setText("*Error");

        jTextField_signUpName.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jTextField_signUpName.setBorder(javax.swing.BorderFactory.createTitledBorder("Username"));

        jPasswordField_signUpPass.setBorder(javax.swing.BorderFactory.createTitledBorder("Password"));

        jPasswordField_signUpRepeatPass.setBorder(javax.swing.BorderFactory.createTitledBorder("Repeat password"));

        jTextField_email.setBorder(javax.swing.BorderFactory.createTitledBorder("e-mail"));

        jComboBox_countries.setBackground(java.awt.Color.white);
        jComboBox_countries.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Antilles", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina-Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Channel Islands", "Chile", "China", "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Côte d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Faeroes", "Falkland Islands", "Fiji", "Finland", "France", "French Guiana", "French Polynesia", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guatemala", "Guiana", "Guinea", "Guinea-Bissau", "Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxemburg", "Macao", "Macedonia", "Madagascar", "Malawi", "Malay States", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Melanesia", "Mexico", "Micronesia", "Micronesia", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau Islands", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Pitcairn Island", "Poland", "Polynesia", "Portugal", "Puerto Rico", "Qatar", "Republic of the Philippines", "Réunion", "Romania", "Russian Federation", "Rwanda", "Saint Christopher-Nevis", "Saint Helena", "Saint Lucia", "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "São Tomé e Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "Spain", "Sri Lanka", "Sudan", "Surinam", "Svalbard", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States of America", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Wallis and Futuna Islands", "Yemen Republic", "Yugoslavia", "Zambia", "Zimbabwe" }));
        jComboBox_countries.setBorder(javax.swing.BorderFactory.createTitledBorder("country"));

        jButton_signUp.setBackground(java.awt.Color.white);
        jButton_signUp.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jButton_signUp.setText("Signup");
        jButton_signUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_signUpMouseClicked(evt);
            }
        });
        jButton_signUp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_signUpKeyPressed(evt);
            }
        });

        jButton_cancel.setBackground(java.awt.Color.white);
        jButton_cancel.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        jButton_cancel.setText("Cancel");
        jButton_cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_cancelMouseClicked(evt);
            }
        });
        jButton_cancel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_cancelKeyPressed(evt);
            }
        });

        jTextField_company.setBorder(javax.swing.BorderFactory.createTitledBorder("company"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel_signUpWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox_countries, javax.swing.GroupLayout.Alignment.LEADING, 0, 317, Short.MAX_VALUE)
                            .addComponent(jTextField_email)
                            .addComponent(jPasswordField_signUpRepeatPass)
                            .addComponent(jTextField_signUpName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordField_signUpPass, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_company))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_signUp)
                        .addGap(73, 73, 73))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_cancel, jButton_signUp});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_signUpWarning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_signUpName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField_signUpPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField_signUpRepeatPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_company, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox_countries, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_cancel)
                    .addComponent(jButton_signUp, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton_cancel, jButton_signUp});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPasswordField_signUpPass, jPasswordField_signUpRepeatPass, jTextField_company, jTextField_email, jTextField_signUpName});

        javax.swing.GroupLayout jDialog_signUpLayout = new javax.swing.GroupLayout(jDialog_signUp.getContentPane());
        jDialog_signUp.getContentPane().setLayout(jDialog_signUpLayout);
        jDialog_signUpLayout.setHorizontalGroup(
            jDialog_signUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
        );
        jDialog_signUpLayout.setVerticalGroup(
            jDialog_signUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Colla - Developer Viewer");
        setResizable(false);

        jPanel_fundo.setBackground(new java.awt.Color(56, 229, 69));

        jTextField_username.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jTextField_username.setBorder(javax.swing.BorderFactory.createTitledBorder("username"));
        jTextField_username.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_usernameFocusLost(evt);
            }
        });
        jTextField_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_usernameKeyPressed(evt);
            }
        });

        jButton_login.setBackground(java.awt.Color.white);
        jButton_login.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jButton_login.setText("Login");
        jButton_login.setBorder(new javax.swing.border.MatteBorder(null));
        jButton_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_loginMouseClicked(evt);
            }
        });
        jButton_login.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_loginKeyPressed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTextArea_status.setBackground(java.awt.Color.white);
        jTextArea_status.setColumns(20);
        jTextArea_status.setEditable(false);
        jTextArea_status.setForeground(java.awt.Color.black);
        jTextArea_status.setLineWrap(true);
        jTextArea_status.setRows(3);
        jTextArea_status.setWrapStyleWord(true);
        jTextArea_status.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setViewportView(jTextArea_status);

        jPasswordField_password.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jPasswordField_password.setBorder(javax.swing.BorderFactory.createTitledBorder("password"));
        jPasswordField_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField_passwordKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        jLabel5.setText("New user?");

        jLabel_signup.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel_signup.setText("Signup");
        jLabel_signup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_signupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_signupMouseEntered(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/colla/appl/developer_viewer/view/images/colla_logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_fundoLayout = new javax.swing.GroupLayout(jPanel_fundo);
        jPanel_fundo.setLayout(jPanel_fundoLayout);
        jPanel_fundoLayout.setHorizontalGroup(
            jPanel_fundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_fundoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_fundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jTextField_username)
                    .addComponent(jPasswordField_password)
                    .addGroup(jPanel_fundoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_fundoLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_signup)
                        .addGap(69, 69, 69)
                        .addComponent(jButton_login, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel_fundoLayout.setVerticalGroup(
            jPanel_fundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_fundoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTextField_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField_password, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_fundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_login, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_signup)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_fundoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPasswordField_password, jTextField_username});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_fundo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_fundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_usernameFocusGained( java.awt.event.FocusEvent evt ){//GEN-FIRST:event_jTextField_usernameFocusGained
        if (jTextField_username.getText().equals("[enter username]")) {
            jTextField_username.setText("");
        }
    }//GEN-LAST:event_jTextField_usernameFocusGained

    private void jTextField_usernameFocusLost( java.awt.event.FocusEvent evt ){//GEN-FIRST:event_jTextField_usernameFocusLost
        if (jTextField_username.getText().equals("")) {
            jTextField_username.setText("[enter username]");
        }
    }//GEN-LAST:event_jTextField_usernameFocusLost

    
    private void jButton_loginMouseClicked( java.awt.event.MouseEvent evt ){//GEN-FIRST:event_jButton_loginMouseClicked
        logIn();
    }//GEN-LAST:event_jButton_loginMouseClicked

    private void showSignUpDialog() {
        jLabel_signUpWarning.setVisible(false);
        Dimension parentSize = this.getSize();
        Point p = this.getLocation();
        jDialog_signUp.setLocation(p.x , p.y);
        jDialog_signUp.pack();
        jDialog_signUp.setVisible(true);
    }

    private void jButton_cancelMouseClicked( java.awt.event.MouseEvent evt ){//GEN-FIRST:event_jButton_cancelMouseClicked
        clearSignUpDialog();
    }//GEN-LAST:event_jButton_cancelMouseClicked

    //erase jDialog_cadastrar fields
    public void clearSignUpDialog() {
        jTextField_signUpName.setText("");
        jPasswordField_signUpRepeatPass.setText("");
        jPasswordField_signUpPass.setText("");
        jTextField_email.setText("");
        jDialog_signUp.dispose();
    }

    private void jButton_signUpMouseClicked( java.awt.event.MouseEvent evt ){//GEN-FIRST:event_jButton_signUpMouseClicked
        tryToSignUp();
    }//GEN-LAST:event_jButton_signUpMouseClicked

    /**
     * verifies if e-mail has valid characters
     * @param mail the e-mail to be checked
     * @return true if e-mail has valid characters, false otherwise
     */
    public boolean isValidaEmail(String mail) {
        boolean isValid = false;

        if (mail != null && mail.length() > 0) {
            String expressao = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expressao, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(mail);
            if (matcher.matches()) {
                isValid = true;
            }
        }
        return isValid;
    }

    /**
     * check all filds for sign up
     */
    public void tryToSignUp() {
        //checar erros de insercao nos campos e apresentar mensagem
        boolean realizarCadastro = true;
        if (jTextField_signUpName.getText().length() < 4) {
            jLabel_signUpWarning.setText("Name must have at least 4 characters");
            realizarCadastro = false;
        } else if (jTextField_signUpName.getText().contains(" ") || jTextField_signUpName.getText().contains("_")) {
            jLabel_signUpWarning.setText("Name must not contain space or underline");
            realizarCadastro = false;
        } else if (jTextField_signUpName.getText().contains("Online") || jTextField_signUpName.getText().contains("Offline")) {
            jLabel_signUpWarning.setText("Name not available.");
            realizarCadastro = false;
        } else if (jPasswordField_signUpPass.getPassword().length < 6) {
            jLabel_signUpWarning.setText("Passowrd must have at least 6 characters");
            realizarCadastro = false;
        } else if (!jPasswordField_signUpPass.getText().equals(
                jPasswordField_signUpRepeatPass.getText())) {
            jLabel_signUpWarning.setText("Passwords don't match.");
            realizarCadastro = false;
        } else if (!isValidaEmail(jTextField_email.getText())) {
            jLabel_signUpWarning.setText("Enter a valid e-mail");
            realizarCadastro = false;
        }

        if (realizarCadastro) {
           this.devLogin.getUser().setName(jTextField_signUpName.getText());
           this.devLogin.getUser().setCompany(jTextField_company.getText());
            if (jComboBox_countries.getSelectedIndex() >= 0) {
               this.devLogin.getUser().setCountry((String) jComboBox_countries.getSelectedItem());
            } else {
               this.devLogin.getUser().setCountry(" Brasil");
            }
           this.devLogin.getUser().setEmail(jTextField_email.getText());
            /*
             * signUp é o método que tenta se conectar
             * ao servidor para realizar o cadastro do usuário
             * e retorna true caso o cadastro tenha sido realizado
             * com sucesso e false caso contrário
             */
            if (signUp(jPasswordField_signUpPass.getText())) {
                jTextField_signUpName.setText("");
                jPasswordField_signUpPass.setText("");
                jPasswordField_signUpRepeatPass.setText("");
                jTextField_email.setText("");
                jDialog_signUp.dispose();
            } else {
                displayMessage(status);
            }
        } else {
            jLabel_signUpWarning.setVisible(true);
        }
    }

    private void jLabel_signupMouseClicked( java.awt.event.MouseEvent evt ){//GEN-FIRST:event_jLabel_signupMouseClicked
        showSignUpDialog();
    }//GEN-LAST:event_jLabel_signupMouseClicked

    private void jLabel_signupMouseEntered( java.awt.event.MouseEvent evt ){//GEN-FIRST:event_jLabel_signupMouseEntered
        jLabel_signup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }//GEN-LAST:event_jLabel_signupMouseEntered

    private void jTextField_usernameKeyPressed( java.awt.event.KeyEvent evt ){//GEN-FIRST:event_jTextField_usernameKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            logIn();
        }
    }//GEN-LAST:event_jTextField_usernameKeyPressed

    private void jPasswordField_passwordKeyPressed( java.awt.event.KeyEvent evt ){//GEN-FIRST:event_jPasswordField_passwordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            logIn();
        }
    }//GEN-LAST:event_jPasswordField_passwordKeyPressed

    private void jButton_loginKeyPressed( java.awt.event.KeyEvent evt ){//GEN-FIRST:event_jButton_loginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            logIn();
        }
    }//GEN-LAST:event_jButton_loginKeyPressed

    private void jButton_cancelKeyPressed( java.awt.event.KeyEvent evt ){//GEN-FIRST:event_jButton_cancelKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            clearSignUpDialog();
        }
    }//GEN-LAST:event_jButton_cancelKeyPressed

    private void jButton_signUpKeyPressed( java.awt.event.KeyEvent evt ){//GEN-FIRST:event_jButton_signUpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tryToSignUp();
        }
    }//GEN-LAST:event_jButton_signUpKeyPressed

    /**
     * log in server
     */
    private void logIn() {
        if (!jTextField_username.getText().equals("[enter username]")
                && jTextField_username.getText().length() > 3) {
            displayMessage("Connecting to server...");
            String nomeDoUsuario = jTextField_username.getText();
            try{
                String password = new String(jPasswordField_password.getPassword());
                this.conexao_estabelecida = (this.devLogin.logInServer(nomeDoUsuario, password) == null)? false: true;
            } catch (NoRouteToHostException nrth) {
                displayMessage("Error: can't establish connection with server.\nAre you connected to the Internet?");
                conexao_estabelecida = false;
            } catch (SocketTimeoutException time) {
                displayMessage("Error: connection timeout");
                conexao_estabelecida = false;
            } catch (SocketException e) {
                displayMessage("Error: can't establish connection with server.");
                conexao_estabelecida = false;
            } catch (EOFException e) {
                displayMessage("Erro: server has finished the connection.");
                conexao_estabelecida = false;
            } catch (IOException e) {
                displayMessage("Erro: couldn't send data to server");
                conexao_estabelecida = false;
            } catch (Exception e) {
                displayMessage("Error: couldn't establish connection with server.");
            }
            if (conexao_estabelecida) {                
                this.setVisible(false);                
                this.dispose();
            } else {
               this.devLogin.getUser().setOffline();
            }
        }

    }


    //metodo para realizar o cadastro do usuario no server
    public boolean signUp(String password) {
        Socket conecta = null;        
        try {
            if(this.devLogin.signUpForServer(password)){   
            jLabel_signUpWarning.setText(devLogin.getUser().getName()+" is not available.");
                jLabel_signUpWarning.setVisible(true);
                return false;
            }
            //esconder dialogo
            jDialog_signUp.setVisible(false);


        } catch (ConnectException ce) {
            displayMessage("Error: couldn't establish connection with server.\nAre you connected to the Internet?");
            clearSignUpDialog();
            jDialog_signUp.dispose();
            return false;
        } catch (SocketTimeoutException time) {
            displayMessage("Error: Connection timeout");
            clearSignUpDialog();
            jDialog_signUp.dispose();
            return false;
        } catch (Exception e) {
            displayMessage("Error: unexpected behavior");
            clearSignUpDialog();
            jDialog_signUp.dispose();
            return false;
        }
        displayMessage("You have succesfully signed up for server");
        return true;

    }

    
    public final void displayMessage(String s) {
        try {
            status = s;

            /*
             * SwingWorker is the class that updates swing components in a new thread
             * without having to wait the actions of other components or processes finish
             */
            SwingWorker display = new SwingWorker() {

                @Override
                protected Object doInBackground() throws Exception {
                    jTextArea_status.append(" " + status + "\n");
                    jTextArea_status.setCaretPosition(jTextArea_status.getText().length() - 1);
                    jTextArea_status.update(jTextArea_status.getGraphics());
                    jTextArea_status.repaint();
                    throw new UnsupportedOperationException("Not supported yet.");
                }

                @Override
                public void done() {
                    //do nothing
                }
            };
            display.execute();
        } catch (Exception ex) {
        }
    }      
    

    @Override
    public void update(Observable subject, Object interest) {
        if(interest instanceof String){
            String info = (String)interest;
            this.displayMessage(info);            
        }
    }
    
   
   

     /**
     * @param args the command line arguments
     */
    public final void initializeLookAndFeel() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client_Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>a
        
        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Client_Login().setVisible(true);
            }
        });*/
    }
    
    private String status; 
    private String userSelecionado;
    private boolean conexao_estabelecida;
    private DevViewerLogin devLogin;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_cancel;
    private javax.swing.JButton jButton_login;
    private javax.swing.JButton jButton_signUp;
    private javax.swing.JComboBox jComboBox_countries;
    private javax.swing.JDialog jDialog_signUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_signUpWarning;
    private javax.swing.JLabel jLabel_signup;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_fundo;
    private javax.swing.JPasswordField jPasswordField_password;
    private javax.swing.JPasswordField jPasswordField_signUpPass;
    private javax.swing.JPasswordField jPasswordField_signUpRepeatPass;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_status;
    private javax.swing.JTextField jTextField_company;
    private javax.swing.JTextField jTextField_email;
    private javax.swing.JTextField jTextField_signUpName;
    private javax.swing.JTextField jTextField_username;
    // End of variables declaration//GEN-END:variables

}