
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;

import javax.swing.JButton;

//import appl.privateG.PrivateGAppl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//import appl.privateG.PrivateGAppl;

public class main extends JFrame {

	private JPanel contentPane;
	private JTextField Path;
	private JTextField Word;

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
		setBounds(100, 100, 517, 155);
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
		Path.setBounds(145, 13, 340, 20);
		contentPane.add(Path);
		Path.setColumns(10);
		
		JLabel lblInsiraPPalavra = new JLabel(" Word or sentence:");
		lblInsiraPPalavra.setHorizontalAlignment(SwingConstants.LEFT);
		lblInsiraPPalavra.setForeground(Color.BLACK);
		lblInsiraPPalavra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInsiraPPalavra.setBounds(10, 42, 132, 20);
		contentPane.add(lblInsiraPPalavra);
		
		Word = new JTextField();
		Word.setBounds(145, 42, 340, 20);
		contentPane.add(Word);
		Word.setColumns(10);
		
		JButton btnPesquisar = new JButton("Search");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fp = Path.getText();
				String t = Word.getText();
				executer(fp,t);
			}
	
		});
		btnPesquisar.setBounds(10, 82, 475, 23);
		contentPane.add(btnPesquisar);
	}
	public void executer(String filePath, String text){
		
		SearchWords wc = new SearchWords(filePath, text);
	}
}
