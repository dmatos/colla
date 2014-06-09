package colla.dfs.app;

//192.168.0.12
import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import org.apache.tika.exception.TikaException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class main extends JFrame {

	private JPanel contentPane;
	private static JTextField Path;
	private static JTextField Word;
	public static TreeSet<String> textIndexer = new TreeSet<String>();	
	public static JTextArea resultTextArea = new JTextArea();
	public static String result = new String();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
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
	public main() {
		setTitle("JCL-Search Engine");
		setBackground(new Color(102, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 439);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(51, 51, 51));
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPesquisaDeConteuso = new JLabel("File or directory path:");
		lblPesquisaDeConteuso.setHorizontalAlignment(SwingConstants.LEFT);
		lblPesquisaDeConteuso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPesquisaDeConteuso.setForeground(Color.BLACK);
		lblPesquisaDeConteuso.setBounds(10, 11, 143, 20);
		contentPane.add(lblPesquisaDeConteuso);
		
		Path = new JTextField();
		Path.setBounds(145, 13, 302, 20);
		contentPane.add(Path);
		Path.setColumns(10);
		
		JLabel lblInsiraPPalavra = new JLabel("Word or sentence:");
		lblInsiraPPalavra.setHorizontalAlignment(SwingConstants.LEFT);
		lblInsiraPPalavra.setForeground(Color.BLACK);
		lblInsiraPPalavra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInsiraPPalavra.setBounds(10, 38, 132, 20);
		contentPane.add(lblInsiraPPalavra);
		
		Word = new JTextField();
		Word.setBounds(145, 42, 302, 20);
		contentPane.add(Word);
		Word.setColumns(10);
		
		JButton btnPesquisar = new JButton("Search");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String r = Search();
					resultTextArea.setText(r);
					
				} catch (TikaException e) {
					e.printStackTrace();
				}

				
			}
	
		});
		btnPesquisar.setBounds(457, 41, 108, 23);
		contentPane.add(btnPesquisar);
		resultTextArea.setEditable(false);
		resultTextArea.setForeground(Color.BLACK);
		resultTextArea.setBounds(10, 69, 555, 320);
		contentPane.add(resultTextArea);
		
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pF = ChooseFile();
				if(pF!=""){
					if(!pF.endsWith("\\")){
						pF = pF + "\\";
					}
				 	Path.setText(pF);
				}
			}
		});
		button.setBounds(457, 12, 33, 23);
		contentPane.add(button);
		
		JButton Indexer = new JButton("Index");
		Indexer.setHorizontalAlignment(SwingConstants.LEFT);
		Indexer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String r = Indexador();
				resultTextArea.setText(r);
			}
		});
		Indexer.setBounds(494, 13, 71, 23);
		contentPane.add(Indexer);
	}
	
	public static String Indexador(){
		
		long time1 = System.nanoTime();
		Set<String> s = new HashSet<String>();
		SearchWords aux = new SearchWords();
		String filePath = Path.getText();
		
		JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();
		File f = new File("../usefull_jars/WordFilesIndexer.jar");
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
	
	public static String Search() throws TikaException{
		
		long time1 = System.nanoTime();
		
		String t = Word.getText();
		SearchWords aux = new SearchWords();
		String resul = aux.SearchWord(t);
		
		long time2 = System.nanoTime();
		System.err.println("Search time: " + (time2 - time1));
		
		return resul;
	}
	
	 public static String ChooseFile(){   
       
       String chooseFlile;      
       JFileChooser arquivo = new JFileChooser();      
       arquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);    
         
       if(arquivo.showOpenDialog(arquivo) == JFileChooser.APPROVE_OPTION){   
           chooseFlile = arquivo.getSelectedFile().getPath();   
       }          
         
       else{   
           chooseFlile = "";   
             
       }   
       return chooseFlile;   
       } 
}
