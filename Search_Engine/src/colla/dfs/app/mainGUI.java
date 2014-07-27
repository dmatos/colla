package colla.dfs.app;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JTextField;
import org.apache.tika.exception.TikaException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mainGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField Path;
	private final JLabel lblWordOrSentence = new JLabel("Word or sentence:");
	private static JTextField Word;
	private static JTable ResultTextArea = new JTable();
  

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainGUI frame = new mainGUI();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainGUI() {
		initializeLookAndFeel();
		setTitle("Search Engine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 341, 500);
		contentPane = new JPanel();
		contentPane.setDoubleBuffered(false);
		contentPane.setEnabled(false);
		contentPane.setFocusTraversalPolicyProvider(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ResultTextArea.addMouseListener(new MouseAdapter() {  
		    public void mouseClicked(MouseEvent e)  
		    {  
		        if (e.getClickCount() == 1)  
		        {  
		        	int viewRow = ResultTextArea.getSelectedRow();  
		            Object path = ResultTextArea.getModel().getValueAt(viewRow, 0);
		            if(path.toString().contains("\\")||path.toString().contains("/")){
		            	File pb = new File(path.toString());
		            	try {
		            		Desktop dt = Desktop.getDesktop();
		            		dt.open(pb);
		            	} catch (IOException e1) {
		            		System.err.println("Não pode abrir o arquivo!");
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
				if(pF!=""){
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
						new Object[][] {
								{r},
							},
							new String[] {
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
					String colunas[] ={"file"};
					DefaultTableModel modelo = new DefaultTableModel(colunas, 0); 
					Set<String> r = Search();

					
					if(r.size()<1){
						modelo.addRow(new String[]{"The Word(s)  \""+ words +"\"  was not found in the file(s)"});
						
					}else{
						modelo.addRow(new String[]{"The Word(s)  \""+ words +"\" was found in the file(s):"});
						for(String rs:r){
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
		status.setIcon(new ImageIcon("../Search_Engine/imagem/radial_green_center_green.jpg")); 
		contentPane.add(status);
		
	

	}
public static String Indexador(){

		long time1 = System.nanoTime();
		Set<String> s = new HashSet<String>();
		SearchWords aux = new SearchWords();
		String filePath = Path.getText();
		JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();
		File f = new File("../user_jars/WordFilesIndexer.jar");
		File[] args = { f };
		javaCaLa.register(args, "WordFilesIndexer");
		
		
		try {
			aux.index(filePath, s);
		} catch (TikaException e) {
			e.printStackTrace();
		}catch(Exception e2){
			System.err.println("Errs exeption!");
		}
		
		for (String a : s) {
			javaCaLa.getResultBlocking(a);
		}
		
		long time2 = System.nanoTime();
		System.err.println("indexing time: " + (time2 - time1));
		
		return "Finished indexing";
		
	}
	
	public static Set<String> Search() throws TikaException{
		
		long time1 = System.nanoTime();
		
		String t = Word.getText();
		SearchWords aux = new SearchWords();
		
		Set<String> resul = aux.SearchWord(t);
		words = t;
		
		long time2 = System.nanoTime();
		System.err.println("Search time: " + (time2 - time1));
		
		return resul;
	}
	
	 public static String ChooseFile(){   
         
         String chooseFlile;      
         JFileChooser arquivo = new JFileChooser();      
         arquivo.setFileSelectionMode(JFileChooser.CUSTOM_DIALOG);    
           
         if(arquivo.showOpenDialog(arquivo) == JFileChooser.APPROVE_OPTION){   
             chooseFlile = arquivo.getSelectedFile().getPath();   
         }          
           
         else{   
             chooseFlile = "";   
               
         }   
         return chooseFlile;   
         } 
	 static String words = "";
	 
	 public  static String VerificaBarra(String pf){
		 String f = "\\";
		 if(pf.contains("/")){
			 f = "/";
		 }
		 if(!pf.endsWith(f)){
			 pf = pf+ f;
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
