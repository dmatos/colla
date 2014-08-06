package View;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import InterfaceViewKernel.Executor;
import Kernel.Separate;

import javax.swing.JCheckBox;

public class CollAOLAPViewer  extends JFrame implements ActionListener
{
	public static final long serialVersionUID = 0;  
	
	private static CollAOLAPViewer uniqueInstance;
    
	private		JTabbedPane tabbedPane;
	
	private		JPanel		panel0;
	private		JPanel		panel2;
	private		JPanel		panel1;
	
	private     JButton     buttonCompute;
	private     JButton     buttonQuery;
	private     JButton     buttonAddLabel;
	private     JButton     buttonChart;
	private     JButton 	buttonAddMeasure;
	private 	JButton 	buttonClear;
	
	private 	JTextField 	fieldDirectoryFile;
	private 	JTextField 	labelValue1;
	private 	JTextField 	labelValue2;
	private 	JTextField 	fieldQueryTime;
	private 	JTextField 	fieldLabel;
	private    	JTextField 	textFieldCubeName;
	
	private 	TextArea 	areaCube;
	private 	TextArea 	areaResultQuery;
	private 	TextArea 	textQueryDetails;
	
	private 	Choice 		choiceSchedule;
	private 	Choice 		choiceLabel;
	private 	Choice 		choiceFilterLabel;
	private 	Choice 		choiceMeasure;
	private 	Choice 		choiceWhichMeasure;
	public 		Choice 		choiceCube;
	
	private 	JLabel 		labelQuerySentence;
	private 	JLabel 		labelSchedule;
	private 	JLabel 		labelQueryDetails;
	private 	JLabel 		labelQueryTime;
	private 	JLabel 		labelMeasure;
	private 	JLabel 		labelInformation7;
	private 	JLabel 		labelInformation6;
	private 	JLabel 		labelInformation5;
	private 	JLabel 		labelInformation4;
	private 	JLabel 		labelInformation3;
	private 	JLabel 		labelInformation2;
	private 	JLabel 		labelInformation1;
	private 	JLabel 		labelLabels;
	private 	JLabel 		labelCubeName;
	
	private 	JCheckBox 	checkBoxUpdate;

	private 	Component 	labelInformation8;

	private static CollAOLAPViewer 	mainFrame;
		
	private String filePath = null;
	
	private String whichCube = null;
		
	private String auxFieldQuery = null;
		
	private ArrayList<String> firstPart; 

	private ArrayList<String> secondPart; 

	private ArrayList<String> thirdPart; 
	
	public Boolean conditionCreatePage2;
		
	private Executor mp;
	
	private static JCL_facade javaCaLa;
	
	@SuppressWarnings("static-access")
	public CollAOLAPViewer()
	{
		// NOTE: to reduce the amount of code in this example, it uses
		// panels with a NULL layout.  This is NOT suitable for
		// production code since it may not display correctly for
		// a look-and-feel.
			
		setTitle( "i-Cubing - Version 0.1 - TextCubes Capabilities over JavaCa&La" );
		setSize( 639, 570 );
		setBackground(new Color(152,251,152));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );
		
		this.javaCaLa = JCL_FacadeImpl.getInstance();
		
		Map<String, Set<String>> auxCubes = new TreeMap<String, Set<String>>();
		Map<String, String []> auxLabelCubes = new TreeMap<String, String []>();
		
		javaCaLa.instantiateGlobalVar("Cubes", auxCubes);
		javaCaLa.instantiateGlobalVar("labelCubes", auxLabelCubes);
		
		mp = new Executor();
		
		conditionCreatePage2 = true;
		
		firstPart = new ArrayList<String>();
		secondPart = new ArrayList<String>();
		thirdPart = new ArrayList<String>();
		
		// Create the tab pages		
		createPage1();
		createPage2();
		createPage3();
		
		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(new Color(152,251,152));	
		tabbedPane.addTab("Cube", panel0 );
		
		tabbedPane.addTab("Cube Query", panel1 );
		tabbedPane.addTab("Information", panel2 );
		UIManager.put("TabbedPane.selected", Color.WHITE);
		SwingUtilities.updateComponentTreeUI(tabbedPane);
		
		topPanel.add( tabbedPane, BorderLayout.CENTER );
		topPanel.setBackground(Color.WHITE);	
	}
	
	
	public static synchronized CollAOLAPViewer getInstance() 
	{ 
		if (uniqueInstance == null) 
			uniqueInstance = new CollAOLAPViewer(); 
		
		return uniqueInstance; 
	}
	
	public void createPage1()
	{
		panel0 = new JPanel();
		panel0.setBackground(new Color(152, 251, 152));
		panel0.setLayout( null );
		
		labelCubeName = new JLabel("Cube name");
		labelCubeName.setBounds(10, 12, 98, 15);
		panel0.add(labelCubeName);
		
		textFieldCubeName = new JTextField();
		textFieldCubeName.setBounds(7, 27, 173, 19);
		textFieldCubeName.setColumns(10);
		panel0.add(textFieldCubeName);
		
		fieldDirectoryFile = new JTextField();
		fieldDirectoryFile.setBounds(10, 75, 406, 20);
		fieldDirectoryFile.setText("/home/joaovq/workspace/Files");
		panel0.add(fieldDirectoryFile);
		
		JButton bottonChooseDirectory = new JButton("Choose");
		bottonChooseDirectory.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser fc = new JFileChooser();
				int ret = fc.showOpenDialog(panel0);
				if (ret== JFileChooser.APPROVE_OPTION)
				{
					File file = fc.getCurrentDirectory();
				    fieldDirectoryFile.setText(file.getAbsolutePath());
				}
				
			}
		});
		bottonChooseDirectory.setBounds(442, 75, 94, 25);
		panel0.add(bottonChooseDirectory);
		
		JLabel labelFilesDirectory = new JLabel("Files Directory");
		labelFilesDirectory.setBounds(10, 58, 111, 15);
		panel0.add(labelFilesDirectory);
		
		//start computation
		buttonCompute = new JButton();
		buttonCompute.setText("Compute");
		buttonCompute.setBounds(10, 236, 100, 20);
		buttonCompute.addActionListener(this);
		panel0.add(buttonCompute );
		
		labelSchedule = new JLabel( "Schedule updates?" );
		labelSchedule.setBounds( 10, 154, 159, 20 );
		labelSchedule.setVisible(true);
		panel0.add( labelSchedule );
		
		checkBoxUpdate = new JCheckBox("");
		checkBoxUpdate.setBounds(159, 154, 21, 23);
		checkBoxUpdate.setBackground(new Color(152,251,152));
		panel0.add(checkBoxUpdate);
		
		choiceSchedule = new Choice();
		choiceSchedule.setBackground(Color.WHITE);
		choiceSchedule.add("1.Each Minute");
		choiceSchedule.add("2.Each Hour");
		choiceSchedule.add("3.Each Period of 8 hours");
		choiceSchedule.add("4.Each Day");
				
		choiceSchedule.setBounds(10, 180, 170, 20);
		panel0.add(choiceSchedule);
		
		labelLabels = new JLabel( "Label Columns" );
		labelLabels.setBounds( 12, 102, 200, 20 );
		labelLabels.setVisible(true);
		panel0.add( labelLabels );
		
		fieldLabel = new JTextField();
		fieldLabel.setBounds(10, 122, 406, 20);
		fieldLabel.setText("/home/joaovq/workspace/Label1/Label.properties");
		panel0.add(fieldLabel);
		
		JButton bottonChooseLabel = new JButton("Choose");
		bottonChooseLabel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser fc = new JFileChooser();
				int ret = fc.showOpenDialog(panel0);
				if (ret== JFileChooser.APPROVE_OPTION)
				{
					File file = fc.getSelectedFile();
					filePath = file.getPath();
					fieldLabel.setText(filePath);
				}
				
			}
		});
		bottonChooseLabel.setBounds(442, 122, 94, 25);
		panel0.add(bottonChooseLabel);
				
		areaCube = new TextArea("", 1, 100, TextArea.SCROLLBARS_VERTICAL_ONLY);
		areaCube.setBackground(UIManager.getColor("TextField.background"));
		areaCube.setBounds(10, 264, 610, 242);
		areaCube.setEditable(false);
				
		panel0.add(areaCube);
	}

	@SuppressWarnings("unchecked")
	public void createPage2()
	{
		panel1 = new JPanel();
		panel1.setBackground(new Color(152,251,152));
		panel1.setLayout( null );

		if(conditionCreatePage2)
		{		
			labelCubeName = new JLabel("Cube name");
			labelCubeName.setBounds(10, 12, 98, 15);
			panel1.add(labelCubeName);
						
			choiceCube = new Choice();
			choiceCube.setVisible(true);
			choiceCube.setBounds(10, 33, 150, 21);
			try
			{
				JCL_result jcl = javaCaLa.getValue("Cubes");
				Map<String, Set<String>> auxCubes = (Map<String, Set<String>>) jcl.getCorrectResult();
				choiceCube.addItem("");
				for(String aux : auxCubes.keySet())
					choiceCube.add(aux);
			}			
			catch(Exception e){};
			choiceCube.addItemListener(new ItemListener()
			{
				@Override
				public void itemStateChanged(ItemEvent e) 
				{
					whichCube = choiceCube.getSelectedItem();
					JCL_result jcl = javaCaLa.getValue("labelCubes");
					Map<String,String[]> result = (Map<String,String[]>) jcl.getCorrectResult();
					choiceLabel.removeAll();
					choiceMeasure.removeAll();
					
					for(String aux : result.keySet())
					{
						if(aux.equals(choiceCube.getSelectedItem()))
						{
							String [] columns = result.get(aux);
							for(int i = 1; i < columns.length; i++)
							{
								choiceLabel.addItem(i + "." + columns[i]);
								choiceMeasure.addItem(i + "." + columns[i]);
							}
						}
					}
				}
	        });
			panel1.add(choiceCube);
		
			labelQuerySentence = new JLabel( "Dimension Sentence" );
			labelQuerySentence.setBounds( 10, 64, 300, 20 );
			panel1.add( labelQuerySentence );
			
			choiceLabel = new Choice();
			choiceLabel.setBounds(10, 84, 150, 20);
			choiceLabel.setVisible(true);
			panel1.add(choiceLabel);
			
			choiceFilterLabel = new Choice();
			choiceFilterLabel.add("");
			choiceFilterLabel.add("=");
			choiceFilterLabel.add("!=");
			choiceFilterLabel.add(">");
			choiceFilterLabel.add(">=");
			choiceFilterLabel.add("<");
			choiceFilterLabel.add("<=");
			choiceFilterLabel.add("<>");
			choiceFilterLabel.add("Contains");
			choiceFilterLabel.add("Between");
			choiceFilterLabel.add("Between Equal");
					
			choiceFilterLabel.setBounds(183, 84, 90, 20);
			choiceFilterLabel.setVisible(true);
			panel1.add(choiceFilterLabel);
			
			labelValue1 = new JTextField();
			labelValue1.setBounds( 300, 84, 109, 20 );
			panel1.add( labelValue1 );
			
			labelValue2 = new JTextField();
			labelValue2.setBounds( 421, 84, 110, 20 );
			panel1.add( labelValue2 );
			
			buttonAddLabel = new JButton();
			buttonAddLabel.setText("ADD");
			buttonAddLabel.setBounds(543, 84, 70, 20);
			buttonAddLabel.addActionListener(this);
			panel1.add(buttonAddLabel );
			
			choiceMeasure = new Choice();
			choiceMeasure.setBounds(10, 134, 150, 21);
			panel1.add(choiceMeasure);
			
			choiceWhichMeasure = new Choice();
			choiceWhichMeasure.setBounds(183, 134, 90, 21);
			choiceWhichMeasure.add("");
			choiceWhichMeasure.add("Count");
			choiceWhichMeasure.add("Max");
			choiceWhichMeasure.add("Min");
			choiceWhichMeasure.add("Average");
			choiceWhichMeasure.add("Sum");
			panel1.add(choiceWhichMeasure);
			
			buttonAddMeasure = new JButton();
			buttonAddMeasure.setText("ADD");
			buttonAddMeasure.setBounds(300, 134, 63, 21);
			buttonAddMeasure.addActionListener(this);
			panel1.add(buttonAddMeasure);
			
			textQueryDetails = new TextArea("", 1, 100, TextArea.SCROLLBARS_VERTICAL_ONLY);
			textQueryDetails.setBounds(10, 187, 399, 60);
			panel1.add(textQueryDetails);
			
			//query
			buttonQuery = new JButton();
			buttonQuery.setText("Query");
			buttonQuery.setBounds(10, 304, 80, 20);
			buttonQuery.addActionListener(this);
			panel1.add(buttonQuery );
			
			buttonChart = new JButton();
			buttonChart.setText("Chart");
			buttonChart.setBounds(545, 304, 80, 20);
			buttonChart.addActionListener(this);
			panel1.add(buttonChart );
			
			labelQueryDetails = new JLabel( "Query in details" );
			labelQueryDetails.setBounds( 10, 167, 150, 20 );
			panel1.add( labelQueryDetails );
			
			labelMeasure = new JLabel( "Measure in details" );
			labelMeasure.setBounds( 10, 115, 150, 20 );
			panel1.add( labelMeasure );
			
			labelQueryTime = new JLabel( "Query Time" );
			labelQueryTime.setBounds( 421, 169, 150, 20 );
			panel1.add( labelQueryTime );
			
			fieldQueryTime = new JTextField();
			fieldQueryTime.setBackground(new Color(255, 255, 255));
			fieldQueryTime.setBounds( 421, 189, 110, 20 );
			fieldQueryTime.setEditable(false);
			panel1.add( fieldQueryTime );
			
			areaResultQuery = new TextArea("", 1, 100, TextArea.SCROLLBARS_VERTICAL_ONLY);
			areaResultQuery.setBounds(10, 330, 615, 176);
			areaResultQuery.setEditable(true);
			
			buttonClear = new JButton("Clear");
			buttonClear.setBounds(10, 253, 80, 20);
			buttonClear.addActionListener(this);
			panel1.add(buttonClear);
					
			panel1.add(areaResultQuery);
		}
		
		else
		{		
			choiceCube.removeAll();	
			
			choiceCube.setBounds(10, 33, 110, 21);
			try
			{
				JCL_result jcl = javaCaLa.getValue("Cubes");
				Map<String, Set<String>> auxCubes = (Map<String, Set<String>>) jcl.getCorrectResult();
				choiceCube.addItem("");
				for(String aux : auxCubes.keySet())
					choiceCube.add(aux);
			}
			
			catch(Exception e){};
		}			
	}
		
	
	public void createPage3() 
	{
		panel2 = new JPanel();
		panel2.setBackground(new Color(152,251,152));
		panel2.setLayout( null );
		try
		{
			BufferedImage myPicture = ImageIO.read(new File("iLOGO.png"));
			JLabel picLabel = new JLabel(new ImageIcon( myPicture ));
			picLabel.setBounds( 280, 10, 250, 150);
			
			panel2.add( picLabel );
		}
		
		catch (Exception e)
		{
			
		}
		
		labelInformation1 = new JLabel( "Build at UFOP - DECOM");
		labelInformation1.setBounds( 41, 78, 250, 20);
		labelInformation1.setVisible(true);
		panel2.add( labelInformation1 );
		
		labelInformation2 = new JLabel( "MG - Brazil");
		labelInformation2.setBounds( 41, 98, 250, 20);
		labelInformation2.setVisible(true);
		panel2.add( labelInformation2 );
		
		labelInformation3 = new JLabel( "i-cubing Multicore is part of i-Cubing project");
		labelInformation3.setBounds( 41, 121, 450, 20);
		labelInformation3.setVisible(true);
		panel2.add( labelInformation3 );
		
		labelInformation4 = new JLabel( "i-Cubing is a free software under GNU General Public License (GPL)");
		labelInformation4.setBounds( 41, 153, 494, 20);
		labelInformation4.setVisible(true);
		panel2.add( labelInformation4);
		
		labelInformation5 = new JLabel( "Copyright - i-Cubing 2005-2012");
		labelInformation5.setBounds( 36, 180, 250, 20);
		labelInformation5.setVisible(true);
		panel2.add( labelInformation5);
		
		labelInformation6 = new JLabel( "Contact: icubing@gmail.com");
		labelInformation6.setBounds( 36, 212, 250, 20);
		labelInformation6.setVisible(true);
		labelInformation6.setEnabled(true);
		labelInformation6.setFocusable(true);
		
		panel2.add( labelInformation6);
		
		labelInformation7 = new JLabel( "More information at: http://www.joubertlima.com.br/icubing.html");
		labelInformation7.setBounds( 36, 244, 471, 20);
		labelInformation7.setVisible(true);
		panel2.add( labelInformation7);
		
		labelInformation8 = new JLabel( "i-cubing was implemented on top of JavaCá&Lá Middleware");
		labelInformation8.setBounds( 34, 272, 450, 20);
		labelInformation8.setVisible(true);
		panel2.add( labelInformation8 );
		panel2.setEnabled(true);
		panel2.setFocusable(true);
	}
	
	
	public void actionPerformed(java.awt.event.ActionEvent event)
	{
		try
		{
			Object object = event.getSource();
			if (object == buttonCompute)
				buttonCompute_ActionPerformed(true,null);
			else if (object == buttonQuery)
				buttonQuery_ActionPerformed();		
			else if (object == buttonAddLabel)
				buttonAddLabel_ActionPerformed();
			else if (object == buttonAddMeasure)
				buttonAddMeasure_ActionPerformed();
			else if (object == buttonClear)
				buttonClear_ActionPerformed();
			//	button66_ActionPerformed(event);
						
		}
		
		catch (Exception e){e.printStackTrace();}
	}
	
	
	public void buttonCompute_ActionPerformed(Boolean firstTime,String [] files)
	{
		String input = fieldDirectoryFile.getText() + "/";//input path
		String labelInput = fieldLabel.getText();
		String cubeName = textFieldCubeName.getText();
		Boolean isSchedule = checkBoxUpdate.isSelected();
		
		System.out.println(input);
		System.out.println(cubeName);
						
		if(!cubeName.isEmpty())
		{
			try
			{
				File fileRegister = new File("../useful_jars/iCubing.jar");
				
				mp.setWaitType(testSchedule(choiceSchedule.getSelectedItem()));
				mp.setCubeName(cubeName);
				mp.setFileRegister(fileRegister);
				mp.setInput(input);
				mp.setLabelInput(labelInput);
				mp.startWork(areaCube,javaCaLa,isSchedule,firstTime,files);	
			} 			
			
			catch (Exception e1){ e1.printStackTrace(); }
			
			conditionCreatePage2 = false;
			choiceCubeUpdate(cubeName,labelInput);
			createPage2();
		}
		
		else
		{
			areaCube.append("You have to give a name for the cube!" + "\n\n");
		}
		
	}
	

	private void buttonQuery_ActionPerformed()
	{
		
		String partOne = jobParOne();
		String partTwo = jobPartTwo();
		String partThree = jobPartThree();
		
		String whichCube = choiceCube.getSelectedItem();
		
		String query = partOne + "$" + partTwo + "$" + partThree;
		Set<String> result = new TreeSet<String>();
		
		try
		{
			result = mp.startQuery(areaCube,query,whichCube,javaCaLa);	
		} 
		
		catch (Exception e1)
		{
			e1.printStackTrace();
		}		
		
		for(String aux : result)
		{
			areaResultQuery.append(aux);
			areaResultQuery.append("\n");
		}
		
		areaResultQuery.append("\n");
	}
	
	
	@SuppressWarnings("unchecked")
	private void buttonAddLabel_ActionPerformed()
	{
		JCL_result jcl = javaCaLa.getValue("labelCubes");
		Map<String,String[]> result = (Map<String,String[]>) jcl.getCorrectResult();
		String [] columns = null;
		
		for(String aux : result.keySet())
		{
			if(aux.equals(whichCube))
				columns = result.get(whichCube);
		}
		
		String aux = choiceLabel.getSelectedItem().split("\\.")[0];
		int index = Integer.parseInt(aux);
		firstPart.add(columns[index] + ":");
		int operator = testOperator(choiceFilterLabel.getSelectedItem());
		
		if(choiceFilterLabel.getSelectedItem().equals("Between") || choiceFilterLabel.getSelectedItem().equals("Between Equal"))
		{
			if(textQueryDetails.getText().trim().equals(""))
			{
				textQueryDetails.setText("(" + columns[index] + " " +  choiceFilterLabel.getSelectedItem() + " " + labelValue1.getText() + " " + labelValue2.getText() + ")");
				auxFieldQuery = "(" + columns[index] + " " +  choiceFilterLabel.getSelectedItem() + " " + labelValue1.getText() + " " + labelValue2.getText() + ")";
			}
			
			else
			{
				auxFieldQuery +=  (" AND " + "(" + columns[index] + " " +  choiceFilterLabel.getSelectedItem() + " " + labelValue1.getText() + " " + labelValue2.getText() + ")");
				textQueryDetails.setText(auxFieldQuery);
			}
			
			String op = "" + operator;
			String auxSelect = columns[index] + ":" +  whichOperator(op) + ":" + labelValue1.getText() + ";" + labelValue2.getText() +"#";
			secondPart.add(auxSelect);
		}
		
		else 
		{
			if(textQueryDetails.getText().trim().equals(""))
			{
				textQueryDetails.setText("(" + columns[index] + " " +  choiceFilterLabel.getSelectedItem() + " " + labelValue1.getText() + ") AND ");
				auxFieldQuery = ("(" + columns[index] +  choiceFilterLabel.getSelectedItem() +   labelValue1.getText() + ")");
			}
			
			else
			{
				auxFieldQuery +=  (" AND " + "(" + columns[index] + " " + choiceFilterLabel.getSelectedItem() + " " + labelValue1.getText() + ")");
				textQueryDetails.setText(auxFieldQuery);
			}
				
			String op = "" + operator;
			String auxSelect = columns[index] + ":" +  whichOperator(op) + ":" + labelValue1.getText() + "#";
			secondPart.add(auxSelect);
		} 
	}
	
	
	@SuppressWarnings("unchecked")
	private void buttonAddMeasure_ActionPerformed()
	{
		JCL_result jcl = javaCaLa.getValue("labelCubes");
		Map<String,String[]> result = (Map<String,String[]>) jcl.getCorrectResult();
		String [] columns = null;
		
		for(String aux : result.keySet())
		{
			if(aux.equals(whichCube))
				columns = result.get(whichCube);
		}
		
		String aux = choiceMeasure.getSelectedItem().split("\\.")[0];
		int index = Integer.parseInt(aux);
		String whichMeasure = choiceWhichMeasure.getSelectedItem();
		auxFieldQuery += (" AND " + "(" + columns[index] + ":" + whichMeasure + ")");
		textQueryDetails.setText(auxFieldQuery);
		thirdPart.add(columns[index] + ":" + whichMeasure + "#");
	}
		 
	
	private void buttonClear_ActionPerformed()
	{
		firstPart.clear();
		
		secondPart.clear();
		
		thirdPart.clear();
		
		textQueryDetails.setText("");
	}
	
	
	@SuppressWarnings({ "unchecked"})
	public void choiceCubeUpdate(String cubeName, String labelInput)
	{
		File labelsColumns = new File(labelInput);
			
		BufferedReader readLabels;
		
		String auxLine = null;
		
		try 
		{
			readLabels = new BufferedReader(new FileReader(labelsColumns));
			auxLine = readLabels.readLine();
		} 
		
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		
		String [] columns = auxLine.split(" ");
		
		try
		{
			Map<String, String[]> aux = new TreeMap<String, String[]>();
			aux.put(cubeName, columns);
			JCL_result jcl = javaCaLa.getValueLocking("labelCubes");
			Map<String,String[]> result = (Map<String,String[]>) jcl.getCorrectResult();
			result.putAll(aux);
			javaCaLa.setValueUnlocking("labelCubes", result);		
		}
		catch(Exception e){};
	}
	
	
	private String whichOperator(String operatorNumber)
	{		
		if(operatorNumber.equals("0"))
			return "igual";
		else if(operatorNumber.equals("1"))
			return "diferente";
		else if(operatorNumber.equals("2"))
			return "maior";
		else if(operatorNumber.equals("3"))
			return "maior_igual";
		else if(operatorNumber.equals("4"))
			return "menor";
		else if(operatorNumber.equals("5"))
			return "menor_igual";
		else if(operatorNumber.equals("6"))
			return "diferente";
		else if(operatorNumber.equals("7"))
			return "contem";
		else if(operatorNumber.equals("8"))
			return "distinto";
		else if(operatorNumber.equals("9"))
			return "entre";
		else if(operatorNumber.equals("10"))
			return "entre_igual";
		
		return null; 

	}
	
	
	private String jobParOne()
	{
		if(firstPart.size() > 0)
		{
			String partOne = firstPart.get(0);
						
			for(int i = 1; i < firstPart.size(); i++)
				partOne += firstPart.get(i);
						
			String result = Separate.separate(partOne,":");
			
			return result;
		
		}
		
		return null;
		
	}
	
	
	private String jobPartTwo()
	{
		if(secondPart.size() != 0)
		{
			String partTwo = secondPart.get(0);
			
			for(int i = 1; i < secondPart.size(); i++)
				partTwo += secondPart.get(i);
			
			String result = Separate.separate(partTwo,"#");
			
			return result;
		}
		
		return null;
		
	}
	
	
	private String jobPartThree()
	{
		if(thirdPart.size() != 0)
		{
			String partThree = thirdPart.get(0);
			
			for(int i = 1; i < thirdPart.size(); i++)
				partThree += thirdPart.get(i);
			
			String result = Separate.separate(partThree,"#");
			
			return result;
		}
		
		return null;
	}
	
	
	private int testSchedule(String schedule)
	{
		schedule = schedule.trim();
		
		if(schedule != null)
		{
			if(schedule.equals("1.Each Minute")) return 1;
			if(schedule.equals("2.Each Hour")) return 2;
			if(schedule.equals("3.Each Period of 8 hours")) return 3;
			if(schedule.equals("4.Each Day")) return 4;

			return -1;
		}
		
		else return -1;		
	}
	
	
	private int testOperator(String query)
	{
		query = query.trim();
		
		if(query != null)
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
			if(query.equals("Between")) return 9;
			if(query.equals("Between Equal")) return 10;
			return -1;
		}
		else return -1;
	}
	
	
	public static void main( String args[] )
	{
		// Create an instance of the test application
		mainFrame =  getInstance();
		mainFrame.setVisible( true );
		
	}
}
