package kernel;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Executor extends Thread
{
	private float time;
	private String input;
	private int numofColumns;
	private TreeSet <Integer> measures;
	private File fileRegister;
	private BufferedReader readFile;
	private final int BLOCK_SIZE = 12;
	ArrayList<byte[]> byteList;
	ArrayList<String> tickets;
	JCL_facade javaCaLa;
	JCL_result jcl;
	
	public Executor(String input, int numofColumns, TreeSet <Integer> measures)
	{
		this.input = input;
		this.numofColumns = numofColumns;
		this.measures = measures;
		this.fileRegister = null;
		this.readFile = null;
		this.byteList = new ArrayList<byte[]>();
		this.tickets = new ArrayList<String>();
		this.javaCaLa = JCL_FacadeImpl.getInstance();
		this.jcl = null;
		
		try
		{
																
			fileRegister = new File("../useful_jars/Worker1.jar");
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
			
	public float getTime() 
	{
		return time;
	}

	/*
	 * Executes the files in directory that was passed 
	 * It reads the files and for which file it processes one
	 */
	private void executeIndex()
	{
		File file = null; 
		try 
		{
			 file = new File(input); 
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String[] files = file.list();		
		
		//registering...
		File[] args = {fileRegister};
		javaCaLa.register(args, "Worker");
				
		long inicio = System.nanoTime();
							
		try
		{
			long numberOfFiles = files.length;
			
			for(int i = 0; i < numberOfFiles; i++) 
			{
				int countBlock = 0;
				File fileInput = new File(input + files[i]);
				readFile = new BufferedReader(new FileReader(fileInput)); 
				String line = null;
				
				System.out.println("Processing " + files[i]);
				
				line = readFile.readLine();
								
				String [] columns = line.split(" ");
				
				Map<Integer,String> mapColumns = new HashMap<Integer, String>();
				
				for(int j = 0; j < numofColumns ; j++)
					mapColumns.put(j, columns[j]);
								
				while((line = readFile.readLine()) != null)
				{
					byteList.add(line.getBytes());
					countBlock++;
					if(countBlock == BLOCK_SIZE)
					{
						Object[] args1 = {files[i],new Integer(numofColumns),mapColumns,measures,byteList};
						tickets.add(javaCaLa.execute("Worker","index", args1));	
						
						countBlock = 0;
						byteList.clear(); 
					}
				}
				
				if(byteList.size() != 0)
				{
					Object[] args1 = {files[i],new Integer(numofColumns),mapColumns,measures,byteList};
					tickets.add(javaCaLa.execute("Worker","index", args1));	
				}
				
				byteList.clear(); 
				
				System.out.println("Relation indexed: " + files[i]);
				
				for(String ticket : tickets)
				{
					jcl = javaCaLa.getResultBlocking(ticket);
					if(jcl.getErrorResult()!=null)
						jcl.getErrorResult().printStackTrace();
				}
				
				tickets.clear();
				
			}//end for 
		} //end try
		
		catch (Exception e)
		{
			System.out.println("Exception");
			e.printStackTrace();
		} //end catch
		
		time = 	(System.nanoTime() - inicio)/1000000000;	
		
		System.err.println("Time :\t" + time +" seconds");								
	}
	
	@SuppressWarnings("unchecked")
	public void executeQuery()
	{
		//1000001_5;11_1
		//ano:mes:rua$mes:entre:03;11$evento:count
		String query = new String("n1$n1:maior:20$n1:count");
		Object [] args1 = {query};
		
		String ticket = javaCaLa.execute("Worker","query",args1);
		
		jcl = javaCaLa.getResultBlocking(ticket);
		
		Map<String, Set<String>> result = new TreeMap<String, Set<String>>();
				
		if(jcl.getErrorResult() == null)
			result = (Map<String, Set<String>>) jcl.getCorrectResult();
		
		else
			jcl.getErrorResult().printStackTrace();
		
		
		System.out.println("Result query");
		
		String[] pivotTables = query.split("\\$");
		
		if(pivotTables[0] != null)
		{
			String[] pivotAt = pivotTables[0].split("\\:");
			if (pivotAt!=null)
			{
				for(int i=0; i< pivotAt.length; i++)
				{
					System.out.print(pivotAt[i] + ": ");
					Set<String> aux = result.get(pivotAt[i]);
					for(String aux1 : aux)
						System.out.println(" " + aux1);
					System.out.println();
				}
			}
		}
	}
	
	public void startWork()
	{
		this.executeIndex();	
		this.executeQuery();
	}
	
}
