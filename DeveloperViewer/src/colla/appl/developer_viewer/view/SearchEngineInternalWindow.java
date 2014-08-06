/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer.view;

import colla.dfs.app.SearchWords;
import colla.dfs.app.mainGUI;
import colla.kernel.util.Debugger;
import colla.kernel.util.ImagePanel;
import interfaces.kernel.JCL_facade;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.apache.tika.exception.TikaException;

/**
 *
 * @author dmatos
 */
public class SearchEngineInternalWindow extends JInternalFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static JTextField Path;
    private final JLabel lblWordOrSentence = new JLabel("Word or sentence:");
    private static JTextField Word;
    private static JTable ResultTextArea = new JTable();

    public SearchEngineInternalWindow() {
        super("Search Engine", true, true, true, true);
        initComponents();
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    private void initComponents() {
        this.mainGUI();
    }

    /**
     * Create the frame.
     */
    public void mainGUI() {
        //initializeLookAndFeel();
        setTitle("Search Engine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 341, 450);
        contentPane = new ImagePanel(BackGround.RADIAL_CENTER_SOFTGREEN.getPath());
        contentPane.setDoubleBuffered(false);
        contentPane.setEnabled(false);
        contentPane.setFocusTraversalPolicyProvider(true);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        ResultTextArea.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int viewRow = ResultTextArea.getSelectedRow();
                    Object path = ResultTextArea.getModel().getValueAt(viewRow, 0);
                    if (path.toString().contains("\\") || path.toString().contains("/")) {
                        File pb = new File(path.toString());
                        try {
                            Desktop dt = Desktop.getDesktop();
                            dt.open(pb);
                        } catch (IOException e1) {
                            System.err.println("Nao pode abrir o arquivo!");
                        }
                    }
                }
            }
        });
        contentPane.add(ResultTextArea);
        JButton Browse = new JButton("...");
        Browse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String pF = ChooseFile();
                if (pF != "") {
                    pF = VerificaBarra(pF);
                    Path.setText(pF);
                }
            }
        });

        Browse.setBackground(Color.WHITE);
        Browse.setFont(new Font("Verdana", Font.BOLD, 14));
        Browse.setBounds(268, 24, 47, 28);
        contentPane.add(Browse);

        JLabel lblFileOrDrectory = new JLabel("File or directory path:");
        lblFileOrDrectory.setIconTextGap(5);
        lblFileOrDrectory.setIgnoreRepaint(true);
        lblFileOrDrectory.setDoubleBuffered(true);
        lblFileOrDrectory.setFont(new Font("Verdana", Font.PLAIN, 15));
        lblFileOrDrectory.setToolTipText("");
        lblFileOrDrectory.setBounds(10, 0, 187, 36);
        contentPane.add(lblFileOrDrectory);

        Path = new JTextField();
        Path.setBounds(10, 26, 255, 28);
        contentPane.add(Path);
        Path.setColumns(10);

        JButton btnNewButton = new JButton("Index");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Object r = Indexador();
                ResultTextArea.setModel(new DefaultTableModel(
                        new Object[][]{
                            {r},},
                        new String[]{
                            ""
                        }
                ));
                contentPane.add(ResultTextArea);
            }
        });
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setFont(new Font("Verdana", Font.BOLD, 14));
        btnNewButton.setBounds(209, 56, 110, 28);
        contentPane.add(btnNewButton);
        lblWordOrSentence.setFont(new Font("Verdana", Font.PLAIN, 15));
        lblWordOrSentence.setBounds(10, 65, 189, 28);
        contentPane.add(lblWordOrSentence);

        Word = new JTextField();
        Word.setColumns(10);
        Word.setBounds(10, 88, 305, 28);
        contentPane.add(Word);

        JButton btnNewButton_1 = new JButton("Search");
        btnNewButton_1.setBackground(Color.WHITE);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                try {
                    String colunas[] = {"file"};
                    DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
                    Set<String> r = Search();

                    if (r == null || r.size() < 1) {
                        modelo.addRow(new String[]{"Word(s)  \"" + words + "\"  not found in the file(s)"});

                    } else {
                        modelo.addRow(new String[]{"Word(s)  \"" + words + "\" found in the file(s):"});
                        for (String rs : r) {
                            modelo.addRow(new String[]{rs});

                        }
                    }
                    ResultTextArea.setModel(modelo);
                    contentPane.add(ResultTextArea);

                } catch (TikaException e) {
                    e.printStackTrace();
                }
            }
        });

        btnNewButton_1.setFont(new Font("Verdana", Font.BOLD, 14));
        btnNewButton_1.setBounds(205, 119, 110, 28);
        contentPane.add(btnNewButton_1);
        ResultTextArea.setBounds(10, 158, 305, 293);
        contentPane.add(ResultTextArea);
        JLabel status = new JLabel();
        status.setAlignmentX(Component.RIGHT_ALIGNMENT);
        status.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        status.setBounds(-33, -36, 394, 516);
        status.setIcon(new ImageIcon(BackGround.RADIAL_CENTER_SOFTGREEN.getPath()));
        contentPane.add(status);

    }

    public static String Indexador() {

        long time1 = System.nanoTime();
        Set<String> s = new HashSet<String>();
        SearchWords aux = new SearchWords();
        String filePath = Path.getText();
        JCL_facade javaCaLa = implementations.dm_kernel.user.JCL_FacadeImpl.getInstance();
        File f = new File("WordFilesIndexer.jar");
        File[] args = {f};
        javaCaLa.register(args, "WordFilesIndexer");

        try {
            aux.index(filePath, s);
        } catch (TikaException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            Debugger.debug(e2);
        }

        for (String a : s) {
            javaCaLa.getResultBlocking(a);
        }

        long time2 = System.nanoTime();
        System.err.println("indexing time: " + (time2 - time1));

        return "Finished indexing";

    }

    public static Set<String> Search() throws TikaException {

        long time1 = System.nanoTime();

        String t = Word.getText();
        SearchWords aux = new SearchWords();

        System.err.println("searching " + t);

        Set<String> resul = aux.SearchWord(t);

        words = t;

        long time2 = System.nanoTime();
        System.err.println("Search time: " + (time2 - time1));

        return resul;
    }

    public static String ChooseFile() {

        String chooseFlile;
        JFileChooser arquivo = new JFileChooser();
        arquivo.setFileSelectionMode(JFileChooser.CUSTOM_DIALOG);

        if (arquivo.showOpenDialog(arquivo) == JFileChooser.APPROVE_OPTION) {
            chooseFlile = arquivo.getSelectedFile().getPath();
        } else {
            chooseFlile = "";

        }
        return chooseFlile;
    }
    static String words = "";

    public static String VerificaBarra(String pf) {
        String f = "\\";
        if (pf.contains("/")) {
            f = "/";
        }
        if (!pf.endsWith(f)) {
            pf = pf + f;
        }
        return pf;
    }

    public final void initializeLookAndFeel() {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
