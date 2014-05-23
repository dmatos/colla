package view;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;








import kernel.Executor;


public class iCubingForms  extends JFrame implements ActionListener
{
	public static final long serialVersionUID = 0;   
    
	private		JTabbedPane tabbedPane;
	
	private		JPanel		panel0;
	private		JPanel		panel3;
	private		JPanel		panel5;
	
	private Checkbox cb;
	
	private     JButton     button2;
	private     JButton     button3;
	private     JButton     button4;
	private     JButton     button5;
	private     JButton     button55;
	private     JButton     button6;
	private     JButton     button66;
	private     JButton     button666;
	
	private JTextField field;
	private JLabel label22;
	private JLabel label17;
	private TextArea area;
	private TextArea area1;
	private TextArea area11;
	private JTextField field6;
	private JTextField field36;
	private JTextField field16;
	private JTextField field7;
	private JTextField field777;
	private JLabel label435;
	private JLabel label18;
	private JTextField field8;
	private JLabel label19;
	private JLabel label43;
	private JLabel label437;
	private JLabel label20;
	private Choice choice2;
	private Choice choice222;
	private Choice choice15;
	private Choice choice16;
	private JTextField field3;
	private JTextField field66;
	private JTextField field166;
		
	private JLabel label45;
	
	private Choice choice166;

	private Choice choice155;

	private JTextField field333;

	private JTextField field0;

	private JLabel label88;

	private JTextField field89;

	private JComboBox<String> choice55;

	private JLabel label44;

	private JLabel label431;

	private JLabel label42;

	private JLabel label41;

	private JLabel label40;

	private JLabel label39;

	private JLabel label38;

	private Component label400;

	private JTextField field361;

	private JLabel label430;

	private static iCubingForms mainFrame;
	
	public iCubingForms()
	{
		// NOTE: to reduce the amount of code in this example, it uses
		// panels with a NULL layout.  This is NOT suitable for
		// production code since it may not display correctly for
		// a look-and-feel.
			
		setTitle( "i-Cubing - Version 0.1 - TextCubes Capabilities over JavaCa&La" );
		setSize( 550, 550 );
		setBackground( Color.LIGHT_GRAY );
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create the tab pages		
		createPage0();
		createPage3();
		createPage5();
		
				
		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Cube", panel0 );
		tabbedPane.addTab( "Cube Query", panel3 );
		tabbedPane.addTab( "Information", panel5 );
		
		topPanel.add( tabbedPane, BorderLayout.CENTER );
				
		
	}
	
	public void createPage0()
	{
		panel0 = new JPanel();
		panel0.setLayout( null );

		label20 = new JLabel( "i-Cubing Version" );
		label20.setBounds( 10, 15, 150, 20);
		panel0.add( label20 );
		
		choice2 = new Choice();
		choice2.add("Parallel");
		choice2.add("Distributed");
				
		choice2.setBounds(160, 15, 100, 20);
		panel0.add(choice2);	
		
		label45 = new JLabel( "Inputs at each host %" );
		label45.setBounds( 10, 45, 150, 20 );
		label45.setVisible(true);
		panel0.add( label45 );
		
		field = new JTextField();
		field.setBounds(160, 45, 265, 20);
		field.setText("/home/joaovq/workspace/Files/");
		panel0.add(field);
		
		label43 = new JLabel( "Number of input Columns" );
		label43.setBounds( 10, 75, 150, 20 );
		label43.setVisible(true);
		panel0.add( label43 );
		
		field3 = new JTextField();
		field3.setBounds(160, 75, 60, 20);
		field3.setText("6");
		panel0.add(field3);
		
		label437 = new JLabel( "Measure Columns %" );
		label437.setBounds( 10, 110, 200, 20 );
		label437.setVisible(true);
		panel0.add( label437 );
		
		field36 = new JTextField();
		field36.setBounds(160, 110, 60, 20);
		field36.setText("");
		panel0.add(field36);
		
		//start computation
		button2 = new JButton();
		button2.setText("Compute");
		button2.setBounds(10, 240, 100, 20);
		button2.addActionListener(this);
		panel0.add(button2 );
		
		label435 = new JLabel( "Schedule updates ?" );
		label435.setBounds( 10, 180, 120, 20 );
		label435.setVisible(true);
		panel0.add( label435 );
		
		cb = new Checkbox();
		cb.setBounds(160, 180, 20, 20);
		cb.setFocusable(true);
		cb.setEnabled(true);
		cb.setState(true);
		panel0.add(cb);
		
		choice222 = new Choice();
		choice222.add("1.Each Minute");
		choice222.add("2.Each Hour");
		choice222.add("3.Each Period of 8 hours");
		choice222.add("4.Each Day");
				
		choice222.setBounds(190, 180, 170, 20);
		panel0.add(choice222);
		
		label430 = new JLabel( "Labels" );
		label430.setBounds( 10, 140, 200, 20 );
		label430.setVisible(true);
		panel0.add( label430 );
		
		field361 = new JTextField();
		field361.setBounds(160, 140, 200, 20);
		field361.setText("config.properties");
		panel0.add(field361);
				
		area = new TextArea("", 1, 100, TextArea.SCROLLBARS_VERTICAL_ONLY);
		area.setBounds(10, 285, 465, 100);
		area.setEditable(false);
				
		panel0.add(area);
	}

	public void createPage3()
	{
		panel3 = new JPanel();
		panel3.setLayout( null );

		label17 = new JLabel( "Dimension Sentence" );
		label17.setBounds( 10, 15, 300, 20 );
		panel3.add( label17 );
		
		choice15 = new Choice();
		
		// Read properties file.
		Properties properties = new Properties();
		try 
		{
		    properties.load(new FileInputStream(field361.getText()));
		}
		
		catch (IOException e)
		{
			
		}
		
		Set<String> e = properties.stringPropertyNames();
		int aux=1;
		
		for (String dimension : e)
		{			
			if(dimension.startsWith("dimension"))
			{
				//System.err.println(dimension);
				choice15.add(aux+"."+properties.getProperty(dimension));
				aux++;
			}
		}
		
		choice15.setBounds(10, 35, 110, 20);
		choice15.setVisible(true);
		panel3.add(choice15);
		
		choice16 = new Choice();
		choice16.add("=");
		choice16.add("!=");
		choice16.add(">");
		choice16.add(">=");
		choice16.add("<");
		choice16.add("<=");
		choice16.add("<>");
		choice16.add("Contains");
		choice16.add("Distinct");
				
		choice16.setBounds(130, 35, 90, 20);
		choice16.setVisible(true);
		panel3.add(choice16);
		
		field6 = new JTextField();
		field6.setBounds( 230, 35, 50, 20 );
		panel3.add( field6 );
		
		field16 = new JTextField();
		field16.setBounds( 290, 35, 50, 20 );
		panel3.add( field16 );
		
		button5 = new JButton();
		button5.setText("ADD");
		button5.setBounds(350, 35, 60, 20);
		button5.addActionListener(this);
		panel3.add(button5 );
		
		//query
		button4 = new JButton();
		button4.setText("Query");
		button4.setBounds(10, 125, 80, 20);
		button4.addActionListener(this);
		panel3.add(button4 );
		
		button66 = new JButton();
		button66.setText("Chart");
		button66.setBounds(390, 255, 80, 20);
		button66.addActionListener(this);
		panel3.add(button66 );
		
		label18 = new JLabel( "Query in details" );
		label18.setBounds( 10, 75, 150, 20 );
		panel3.add( label18 );
		
		field7 = new JTextField();
		field7.setBounds( 10, 95, 300, 20 );
		field7.setEditable(true);
		field7.setText("");
		panel3.add( field7 );
		
		label88 = new JLabel( "Measure in details" );
		label88.setBounds( 330, 75, 150, 20 );
		panel3.add( label88 );
		
		choice55 = new JComboBox<String>();
		// Read properties file.
		properties = new Properties();
		try
		{
		    properties.load(new FileInputStream(field361.getText()));
		} 
		
		catch (IOException e1)
		{
			
		}
		
		e = properties.stringPropertyNames();
			
		for (String dimension : e)
		{			
			if(dimension.startsWith("measure"))
			{
				//System.err.println(dimension);
				choice55.addItem(properties.getProperty(dimension));
			}
		}
						
		choice55.setBounds(370, 95, 100, 20);
		choice55.setVisible(true);
		choice55.setEditable(true);
		choice55.setFocusable(true);
		panel3.add(choice55);
		
		field89 = new JTextField();
		field89.setBounds( 330, 95, 30, 20 );
		field89.setEditable(true);
		field89.setText("");
		panel3.add( field89 );
		
		label19 = new JLabel( "Cube Query Time" );
		label19.setBounds( 10, 175, 150, 20 );
		panel3.add( label19 );
		
		field8 = new JTextField();
		field8.setBounds( 10, 195, 300, 20 );
		field8.setEditable(false);
		field8.setText("0");
		panel3.add( field8 );
		
		label22 = new JLabel( "Results" );
		label22.setBounds( 10, 255, 150, 20 );
		panel3.add( label22 );
		
		area1 = new TextArea("", 1, 100, TextArea.SCROLLBARS_VERTICAL_ONLY);
		area1.setBounds(10, 285, 465, 100);
		area1.setEditable(true);
				
		panel3.add(area1);
		
	}
		
	public void createPage5() 
	{
		panel5 = new JPanel();
		panel5.setLayout( null );
		try
		{
			BufferedImage myPicture = ImageIO.read(new File("iLOGO.png"));
			JLabel picLabel = new JLabel(new ImageIcon( myPicture ));
			picLabel.setBounds( 280, 10, 250, 150);
			
			panel5.add( picLabel );
		}
		
		catch (Exception e)
		{
			
		}
		
		label38 = new JLabel( "Build at UFOP - DECOM");
		label38.setBounds( 100, 100, 250, 20);
		label38.setVisible(true);
		panel5.add( label38 );
		
		label39 = new JLabel( "MG - Brazil");
		label39.setBounds( 100, 115, 250, 20);
		label39.setVisible(true);
		panel5.add( label39 );
		
		label40 = new JLabel( "i-cubing Multicore is part of i-Cubing project");
		label40.setBounds( 100, 150, 450, 20);
		label40.setVisible(true);
		panel5.add( label40 );
		
		label41 = new JLabel( "i-Cubing is a free software under GNU General Public License (GPL)");
		label41.setBounds( 100, 180, 450, 20);
		label41.setVisible(true);
		panel5.add( label41);
		
		label42 = new JLabel( "Copyright - i-Cubing 2005-2012");
		label42.setBounds( 100, 210, 250, 20);
		label42.setVisible(true);
		panel5.add( label42);
		
		label431 = new JLabel( "Contact: icubing@gmail.com");
		label431.setBounds( 100, 240, 250, 20);
		label431.setVisible(true);
		label431.setEnabled(true);
		label431.setFocusable(true);
		
		panel5.add( label431);
		
		label44 = new JLabel( "More information at: http://www.joubertlima.com.br/icubing.html");
		label44.setBounds( 100, 270, 450, 20);
		label44.setVisible(true);
		panel5.add( label44);
		
		label400 = new JLabel( "i-cubing was implemented on top of JavaC�&L� Middleware");
		label400.setBounds( 100, 300, 450, 20);
		label400.setVisible(true);
		panel5.add( label400 );
		panel5.setEnabled(true);
		panel5.setFocusable(true);
	}
	
	public void actionPerformed(java.awt.event.ActionEvent event)
	{
		try
		{
			Object object = event.getSource();
			if (object == button2)
				button2_ActionPerformed(event);
			else if (object == button3)
				button3_ActionPerformed(event);
			else if (object == button4)
				button4_ActionPerformed(event);		
			else if (object == button5)
				button5_ActionPerformed(event);		
			else if (object == button6)
				button6_ActionPerformed(event);	
			else if (object == button55)
				button55_ActionPerformed(event);
			else if (object == button66)
				button66_ActionPerformed(event);
			else if (object == button666)
				button666_ActionPerformed(event);
						
		}
		
		catch (Exception e){e.printStackTrace();}
	}
	
	private void button2_ActionPerformed(java.awt.event.ActionEvent event)
	{
		String input = field.getText();	//input path
		String columns = field3.getText(); // numbers of columns
		//String measures = field36.getText(); // % of measures
				
		TreeSet < Integer > measures = new TreeSet< Integer >(); 
		measures.add(2);
		measures.add(4);
		
		// cb is checkbox for scheduling
		// choice222 is choice of checkbox
		
		try
		{
			Executor mp = new Executor(input, Integer.parseInt(columns), measures);
			mp.startWork();	
			area.setText("i-Cubing runtime: " + mp.getTime() + " sec");
		} 
		
		catch (Exception e1)
		{
			e1.printStackTrace();
		}				
			 
	}	
	
	private void button3_ActionPerformed(java.awt.event.ActionEvent event)
	{
		String input = field0.getText(); // input path
		String columns = field333.getText();
		//String measures = field366.getText();		
		
		TreeSet< Integer > measures = new TreeSet< Integer >(); 
		measures.add(2);
		measures.add(4);
				
		try
		{
			Executor mp = new Executor(input, Integer.parseInt(columns), measures);
			mp.startWork();	
			area11.setText("i-Cubing runtime: " + mp.getTime() + " sec");
				
		} 
		
		catch (Exception e1){e1.printStackTrace();}							
	}
	
	/*private Sentence[] query(String query){
		String[] subQueries = query.split("AND");
		Sentence[] sentences = new Sentence[subQueries.length-1];
		for(int i=0; i<subQueries.length-1; i++){
			Sentence s = new Sentence();
			subQueries[i] = subQueries[i].trim();
			subQueries[i] = subQueries[i].substring(1, subQueries[i].length()-1);
			
			String[] sentenceAttr = subQueries[i].split(":");
			
			switch (sentenceAttr.length){
			case 2: {
				s.dimension = Integer.parseInt(sentenceAttr[0]);
				s.operator = Integer.parseInt(sentenceAttr[1]);
				break;
			}
			case 3: {
				s.dimension = Integer.parseInt(sentenceAttr[0]);
				s.operator = Integer.parseInt(sentenceAttr[1]);
				Vector<String> v = new Vector<String>();
				v.add(sentenceAttr[2]);
				s.values = v;
				
				break;
			}
			case 4: {
				s.dimension = Integer.parseInt(sentenceAttr[0]);
				s.operator = Integer.parseInt(sentenceAttr[1]);
				Vector<String> v = new Vector<String>();
				v.add(sentenceAttr[2]);
				v.add(sentenceAttr[3]);
				s.values = v;
				break;
			}
			}
			
			System.err.println("sentence: " + s.dimension + s.operator + s.values);
			
			sentences[i] = s;
		}
		
		return sentences;
	}*/
	
	private int testOperator(String query)
	{
		query = query .trim();
		
		if(query!=null)
		{
			if(query.equals("=")) return 0;
			if(query.equals("!=")) return 1;
			if(query.equals(">")) return 2;
			if(query.equals(">=")) return 3;
			if(query.equals("<")) return 4;
			if(query.equals("<=")) return 5;
			if(query.equals("<>")) return 6;
			if(query.equals("Contains")) return 7;
			if(query.equals("Distinct")) return 8;
			
			return -1;
			
		}
		
		else return -1;
	}

	@SuppressWarnings("unused")
	private void button4_ActionPerformed(java.awt.event.ActionEvent event)
	{
		//JOptionPane.showMessageDialog(this, "Query Strategy only works with FLOAT cube indexing type");
		String measure = choice55.getSelectedItem().toString();
		
				
		/*MainQuery mq = new MainQuery();
		Map<String, String> result = mq.query(this.query(field7.getText()), field.getText(), measure, new Integer(-1));
		
		for(String aKey: result.keySet()){
			
			String text = aKey + "=>" + result.get(aKey) + "\r\n";
			area1.append(text);
		}
		*/
	}
	
	private void button6_ActionPerformed(java.awt.event.ActionEvent event)
	{
		JOptionPane.showMessageDialog(this, "Query Strategy only works with FLOAT cube indexing type");
	}
	
	private void button666_ActionPerformed(java.awt.event.ActionEvent event)
	{
		JOptionPane.showMessageDialog(this, "not opened");		
	}
	
	private void button5_ActionPerformed(java.awt.event.ActionEvent event)
	{
		String aux = field7.getText();
		int operator = this.testOperator(choice16.getSelectedItem());
		
		if(choice16.getSelectedItem().equals("Distinct"))
			this.field7.setText(aux+ "(" + choice15.getSelectedItem().split("\\.")[0] +":"+ operator  + ") AND ");
		else 
		{
			if(choice16.getSelectedItem().equals("<>"))
				this.field7.setText(aux+ "(" + choice15.getSelectedItem().split("\\.")[0] +":"+  operator  +":"+  field6.getText() + ":" + field16.getText() + ") AND ");
			else
				this.field7.setText(aux+ "(" + choice15.getSelectedItem().split("\\.")[0] +":"+  operator +":"+   field6.getText() + ") AND ");
		} 
	}
	
	private void button55_ActionPerformed(java.awt.event.ActionEvent event)
	{
		String aux = field777.getText();
		int operator = this.testOperator(choice166.getSelectedItem());
		
		if(choice166.getSelectedItem().equals("Distinct"))
			this.field777.setText(aux+ "(" + choice155.getSelectedItem().split("\\.")[0] +":"+ operator  + ") AND ");
		else
		{
			if(choice166.getSelectedItem().equals("<>"))
				this.field777.setText(aux+ "(" + choice155.getSelectedItem().split("\\.")[0] +":"+  operator  +":"+  field66.getText() + ":" + field166.getText() + ") AND ");
			else 
				this.field777.setText(aux+ "(" + choice155.getSelectedItem().split("\\.")[0] +":"+  operator +":"+   field66.getText() + ") AND ");
		} 
	}
	
	private void button66_ActionPerformed(java.awt.event.ActionEvent event)
	{
		/*DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(6, "Science", "Rahul");
		dataset.setValue(8, "Maths", "Rahul");
		dataset.setValue(5, "Science", "Deepak");
		dataset.setValue(3, "Maths", "Deepak");
		dataset.setValue(6, "Science", "Vinod");
		dataset.setValue(9, "Maths", "Vinod");
		dataset.setValue(2, "Science", "Chandan");
		dataset.setValue(4, "Maths", "Chandan");
	    JFreeChart chart = ChartFactory.createBarChart3D("Comparison between Students","Students", "Marks", dataset, PlotOrientation.VERTICAL, true,true, false);
		chart.setBackgroundPaint(Color.white);
		chart.getTitle().setPaint(Color.blue); 
		CategoryPlot p = chart.getCategoryPlot(); 
		p.setRangeGridlinePaint(Color.red); 
		ChartFrame frame1=new ChartFrame("i-Cubing: 3D Bar Chart",chart);
		frame1.setVisible(true);
		frame1.setSize(300,300);
		
		
		DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
		dataset1.setValue(6, "Rahul", "Science");
		dataset1.setValue(8, "Rahul", "Maths");
		dataset1.setValue(5, "Deepak", "Science");
		dataset1.setValue(3, "Deepak", "Maths");
		dataset1.setValue(6, "Vinod", "Science");
		dataset1.setValue(9, "Vinod", "Maths");
		dataset1.setValue(2, "Chandan", "Science");
		dataset1.setValue(4, "Chandan", "Maths");
	    JFreeChart chart1 = ChartFactory.createBarChart3D("Comparison between Students - pivoted","Discipline", "Marks", dataset1, PlotOrientation.VERTICAL, true,true, false);
		chart1.setBackgroundPaint(Color.white);
		chart1.getTitle().setPaint(Color.blue); 
		CategoryPlot p1 = chart1.getCategoryPlot(); 
		p1.setRangeGridlinePaint(Color.red); 
		ChartFrame frame2=new ChartFrame("i-Cubing: 3D Bar Chart",chart1);
		frame2.setVisible(true);
		frame2.setSize(300,300);
		*/
	}
		 
	// Main method to get things started
	public static void main( String args[] )
	{
		// Create an instance of the test application
		mainFrame = new iCubingForms();
		mainFrame.setVisible( true );
		
	}
}
