package InterfaceViewKernel;

import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;

import java.util.List;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import View.CollAOLAPViewer;

public class Executor extends Thread
{
	private 	String 	input;
	private 	String 	labelInput;
	private 	String 	cubeName;
	
	private 	int 	numofColumns;
	private 	final 	int 	BLOCK_SIZE = 1;
	private 	static int 	waitType;
	
	private 	File 	fileRegister;
	
	private 	float 	time;
	
	private 	ArrayList<String> 	tickets;
	
	private 	JCL_result 	jcl;
	
	private 	Set<String> 	hostSet;
	
	private 	BufferedReader 	readLabels;
	
	private 	static boolean 	isFinished = false;
 	@SuppressWarnings("static-access")
	public Executor()
	{
		this.fileRegister = null;
		this.time = 0;
		this.numofColumns = 0;
		this.tickets = new ArrayList<String>();
		this.jcl = null;
		this.hostSet = new TreeSet<String>();		
		this.waitType = 0;
	}
	
	public int getWaitType() 
	{
		return waitType;
	}

	@SuppressWarnings("static-access")
	public void setWaitType(int waitType)
	{
		this.waitType = waitType;
	}

	public String getInput() 
	{
		return input;
	}

	public void setInput(String input) 
	{
		this.input = input;
	}

	public String getLabelInput()
	{
		return labelInput;
	}

	public void setLabelInput(String labelInput) 
	{
		this.labelInput = labelInput;
	}

	public String getCubeName()
	{
		return cubeName;
	}

	public void setCubeName(String cubeName)
	{
		this.cubeName = cubeName;
	}

	public File getFileRegister()
	{
		return fileRegister;
	}

	public void setFileRegister(File fileRegister)
	{
		this.fileRegister = fileRegister;
	}

	public void setFinished ()
	{
		isFinished = true;
	}
	
	public static class CheckNewFiles extends Thread
	{
		private Set<String> originalFiles; 
		private String 		 input;
		
		
		public CheckNewFiles(Set<String> filesCube,String input)
		{
			this.originalFiles = filesCube;
			this.input = input;
		}
		
		public void run()
		{			
			while(!isFinished)
			{
				System.out.println("While");
				
				File file = new File(input); 
				
				System.out.println();
				
				System.out.println("Original");
				for(String aux : originalFiles)
					System.out.print(" " + aux);
				
				System.out.println();
				
				String[] files = file.list();
			
				List<String> auxFiles = new LinkedList<String>();
				
				System.out.println("New");
				for(String aux : files)
				{
					System.out.print(" " + aux);
					auxFiles.add(aux);
				}
				
				System.out.println();
				
				Collections.sort(auxFiles);
				
				if(!originalFiles.containsAll(auxFiles))
				{
					System.out.println("OPaaa");
					auxFiles.removeAll(originalFiles);
					String [] auxFiles1 = auxFiles.toArray(new String[auxFiles.size()]);
					
					originalFiles.addAll(auxFiles);

					CollAOLAPViewer view = CollAOLAPViewer.getInstance();
					view.buttonCompute_ActionPerformed(false, auxFiles1);
				}
				
				System.out.println("uhsuahs");
				
				try
				{
					switch(waitType)
					{
						//minute
						case 1:
						{
							synchronized (this) 
							{
								this.wait(60000);
							}
							break;
						}
						
						//hour
						case 2:
						{
							synchronized (this) 
							{
								this.wait(3600000);
							}
							break;
						}
						
						//period of 8 hours
						case 3:
						{
							synchronized (this)
							{
								this.wait(28800000);
							}
							
							break;
						}
						
						//day
						case 4:
						{
							synchronized (this) 
							{
								this.wait(86400000);
							}
							break;
						}					
					}
					
				} catch (Exception e){ e.printStackTrace(); }
					
			}
		}
	}
	
	@SuppressWarnings({ "resource", "unchecked"})
	private Boolean executeIndex(TextArea areaCube, JCL_facade javaCaLa,String[] files, Boolean isSchedule, Boolean firstTime)
	{
		Map<String,Set<String>> auxCubes = new TreeMap<String,Set<String>>();
		Set<String> filesCube = new TreeSet<String>();
		
		try
		{
			jcl = javaCaLa.getValue("Cubes");
			auxCubes = (Map<String,Set<String>>) jcl.getCorrectResult();
		}
		
		catch(Exception e){ };

		if(!auxCubes.containsKey(getCubeName()) || !firstTime)
		{
			if(firstTime)
			{
				areaCube.append("Indexing " + getCubeName() + "\n");

				long inicio = System.nanoTime();
									
				try
				{						
					long numberOfFiles = files.length;
					
					//registering...
					File[] args = {fileRegister};
					javaCaLa.register(args, "Worker");
								
					ArrayList<String> host = (ArrayList<String>) javaCaLa.getHosts();
					
					int numberOfHosts = host.size();
								
					File labelsColumns = new File(getLabelInput());
					readLabels = new BufferedReader(new FileReader(labelsColumns)); 
					
					String auxLine = readLabels.readLine();
					
					String [] columns = auxLine.split(" ");
					
					numofColumns = columns.length;
								
					Map<Integer,String> mapColumns = new HashMap<Integer, String>();
					
					for(int j = 0; j < numofColumns ; j++)
						mapColumns.put(j, columns[j]);			
					
					for(String aux : host)
					{
						Object[] args1 = {getCubeName(),numofColumns,mapColumns};
						javaCaLa.executeOnHost(aux, "Worker", "inicializateWorker", args1);
					}
					
					int countHost = 0;
					
					for(int i = 0; i < numberOfFiles; i++) 
					{
						filesCube.add(files[i]);
						
						ArrayList<byte[]> byteList = new ArrayList<byte[]>();
						int countBlock = 0;
						String whichHost = new String();
						
						File fileInput = new File(input + files[i]);
						BufferedReader readFile = new BufferedReader(new FileReader(fileInput)); 
						String line = null;
											
						if(countHost < numberOfHosts)
						{
							whichHost = host.get(countHost);
							hostSet.add(whichHost);
						}
						
						else
						{
							countHost = 0;
							whichHost = host.get(countHost);
						}	
						
						while((line = readFile.readLine()) != null)
						{
							byteList.add(line.getBytes());
							countBlock++;
							
							if(countBlock == BLOCK_SIZE)
							{
								Object[] args1 = {files[i],new Integer(numofColumns),mapColumns,byteList,whichHost};
								tickets.add(javaCaLa.executeOnHost(whichHost, "Worker", "index", args1));	
								
								countBlock = 0;
								byteList.clear(); 
							}
						}
						
						if(byteList.size() != 0)
						{
							Object[] args1 = {files[i],new Integer(numofColumns),mapColumns,byteList,whichHost};
							tickets.add(javaCaLa.executeOnHost(whichHost, "Worker", "index", args1));	
						}
						
						countHost++;
							
						areaCube.append("Relation indexed: " + files[i] + "\n");
						
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
					e.printStackTrace();
				} //end catch
				
				Map<String,Set<String>> thisCube = new TreeMap<String, Set<String>>();
				thisCube.put(cubeName, hostSet);
				
				try
				{
					jcl = javaCaLa.getValueLocking("Cubes");
					Map<String,Set<String>> aux = (Map<String,Set<String>>) jcl.getCorrectResult();
					aux.putAll(thisCube);
					javaCaLa.setValueUnlocking("Cubes", aux);
				}
				
				catch(Exception e){};
									
				time = 	(System.nanoTime() - inicio)/1000000000;	
				
				areaCube.append(getCubeName() + " indexed" + "\n");
				areaCube.append("Time : " + time +" seconds" + "\n\n");
				
				if(isSchedule)
				{
					CheckNewFiles checkFiles = new CheckNewFiles(filesCube,getInput());
					checkFiles.start();
				}
				
				return true;	
			}
		
			else
			{
				areaCube.append("Re-indexing " + getCubeName() + "\n");

				long inicio = System.nanoTime();
				
				try
				{						
					long numberOfFiles = files.length;
					
					//registering...
					File[] args = {fileRegister};
					javaCaLa.register(args, "Worker");
					
					jcl = javaCaLa.getValue("Cubes");
					Map<String,Set<String>> aux = (Map<String,Set<String>>) jcl.getCorrectResult();
								
					ArrayList<String> host = new ArrayList<String>(aux.get(getCubeName()));
					
					int numberOfHosts = host.size();
								
					File labelsColumns = new File(getLabelInput());
					readLabels = new BufferedReader(new FileReader(labelsColumns)); 
					
					String auxLine = readLabels.readLine();
					
					String [] columns = auxLine.split(" ");
					
					numofColumns = columns.length;
								
					Map<Integer,String> mapColumns = new HashMap<Integer, String>();
					
					for(int j = 0; j < numofColumns ; j++)
						mapColumns.put(j, columns[j]);			
					
					int countHost = 0;
					
					for(int i = 0; i < numberOfFiles; i++) 
					{						
						ArrayList<byte[]> byteList = new ArrayList<byte[]>();
						int countBlock = 0;
						String whichHost = new String();
						
						File fileInput = new File(input + files[i]);
						BufferedReader readFile = new BufferedReader(new FileReader(fileInput)); 
						String line = null;
											
						if(countHost < numberOfHosts)
							whichHost = host.get(countHost);
						
						else
						{
							countHost = 0;
							whichHost = host.get(countHost);
						}	
						
						while((line = readFile.readLine()) != null)
						{
							byteList.add(line.getBytes());
							countBlock++;
							
							if(countBlock == BLOCK_SIZE)
							{
								Object[] args1 = {files[i],new Integer(numofColumns),mapColumns,byteList,whichHost};
								tickets.add(javaCaLa.executeOnHost(whichHost, "Worker", "index", args1));	
								
								countBlock = 0;
								byteList.clear(); 
							}
						}
						
						if(byteList.size() != 0)
						{
							Object[] args1 = {files[i],new Integer(numofColumns),mapColumns,byteList,whichHost};
							tickets.add(javaCaLa.executeOnHost(whichHost, "Worker", "index", args1));	
						}
						
						countHost++;
							
						areaCube.append("Relation indexed: " + files[i] + "\n");
						
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
					e.printStackTrace();
				} //end catch
								
				time = 	(System.nanoTime() - inicio)/1000000000;	
				
				areaCube.append(getCubeName() + " re-indexed" + "\n");
				areaCube.append("Time : " + time +" seconds" + "\n\n");

				
				return true;	
			}
		}
		
		else
		{
			areaCube.append(getCubeName() + " already exists!\n\n");
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Set<String> executeQuery(TextArea areaCube,String query, String whichCube, JCL_facade javaCaLa) 
	{	
		Set<String> result = new TreeSet<String>();

		try
		{
			Boolean containsAverage = checkAverage(query);
		
			Object [] args1 = {query};
			
			ArrayList<String> tickets = new ArrayList<String>();
			
			Set<String> hostsExecute = new TreeSet<String>();
			
			Map<String,Set<String>> auxCubes = new TreeMap<String,Set<String>>();
			
			try
			{
				jcl = javaCaLa.getValue("Cubes");
				auxCubes = (Map<String,Set<String>>) jcl.getCorrectResult();
			}
			
			catch(Exception e){ };
			
			if(auxCubes.containsKey(whichCube))
				hostsExecute = auxCubes.get(whichCube);
			
					
			for(String aux : hostsExecute)
				tickets.add(javaCaLa.executeOnHost(aux,"Worker","query",args1));
			
			List<Set<String>> allResults = new LinkedList<Set<String>>();
			
			for(String aux : tickets)
			{
				jcl = javaCaLa.getResultBlocking(aux);		   
			
				if(jcl.getErrorResult() == null)
				{
					Set<String> aux1 = (Set<String>) jcl.getCorrectResult();
					allResults.add(aux1);
				} 
												
				else
					jcl.getErrorResult().printStackTrace();
			}
			
			if(containsAverage)
				result = ProcessMeasure.executeWithAverage(allResults);
			else
				result = ProcessMeasure.execute(allResults);
		}
		
		catch(Exception e){ areaCube.append("No result!\n\n");}; 
		
		return result;
	}
	
	public Boolean startWork(TextArea areaCube, JCL_facade javaCaLa, Boolean isSchedule, Boolean firstTime, String [] filesInput)
	{
		if(!firstTime)
			return executeIndex(areaCube,javaCaLa,filesInput,isSchedule,firstTime);	
		
		else
		{
			File file = new File(getInput()); 
			String[] files = file.list();
		
			return executeIndex(areaCube,javaCaLa,files,isSchedule,true);	
		}

	}
	
	public Set<String> startQuery(TextArea areaCube,String query, String whichCube,JCL_facade javaCaLa) 
	{
		Set<String> result = this.executeQuery(areaCube,query,whichCube,javaCaLa);
		return result;
	}

	public Boolean checkAverage(String query)
	{
		Boolean existAverage = false; 
		
		String[] slicedQuery = query.split("\\$");
		
		if(!slicedQuery[2].equals("null"))
		{
			String[] thirdFilters = slicedQuery[2].split("\\#");
			
			if (thirdFilters!=null)
			{
				for(int i=0; i< thirdFilters.length; i++)
				{
					String[] oneM = thirdFilters[i].split("\\:");
					if(oneM[1].equals("Average"))
						existAverage = true;
				}
			}
		}
		
		return existAverage;

		
	}
}