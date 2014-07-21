package kernel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import kernel.Attribute;
import kernel.Measure;
import kernel.Filter;
//import implementations.dm_kernel.user.JCL_FacadeImpl;
//import interfaces.kernel.JCL_facade;
//import interfaces.kernel.JCL_result;
import drivers.ConnectionText;

public class Worker 
{
	private FileOutputStream finalTempFile;
	private static int numberOfColumns;
	private static String cubeName;
	private static Vector <Map<String,Map<String, List<String>> >> columnsItens;
	public  static Vector <Map<String,Map<String, Set<String>>  >> partialCube;
	public  static Map<String, Integer> reverseColumns;
	
	public static String getCubeName()
	{
		return cubeName;
	}
	
	public static void setCubeName(String cName)
	{
		cubeName = cName;
	}
	
	public static void inicializateWorker(String cName,int numofColumns,Map<Integer,String> mapColumns)
	{
		setCubeName(cName);
		columnsItens = new Vector<Map<String,Map<String, List<String>> >>();
		partialCube = new Vector<Map<String,Map<String, Set<String>>  >>();
		
		for(int i = 0; i < numofColumns; i++)
		{
			Map<String,Map<String, List<String>> > auxMap = new TreeMap<String,Map<String, List<String>> >();
			columnsItens.add(auxMap);
		}
		
		for(int i = 0; i < numofColumns; i++)
		{
			Map<String,Map<String, Set<String>> > auxMap = new TreeMap<String,Map<String, Set<String>> >();
			partialCube.add(auxMap);
		}
		
		numberOfColumns = numofColumns;
				
		reverseColumns = new HashMap<String, Integer>();
		
		for(int i = 1; i <= numberOfColumns; i++)
			reverseColumns.put(mapColumns.get(i), i);
	}
	
	public Boolean index(String fileName, int numofColumns,Map<Integer,String> mapColumns,ArrayList<byte[]> byteList,String host) throws IOException
	{
		
		System.out.println("Processing" + fileName + "...!");
		
		try
		{			
			String[] tuple = null;
							
			File tempDir = new File(System.getProperty("java.io.tmpdir"));
		    File tempFile = File.createTempFile("auxFile", ".tmp", tempDir);
		    
	        finalTempFile = new FileOutputStream(tempFile);
	        
	        for(int i = 0; i < byteList.size(); i++)
	        {
	        	finalTempFile.write(byteList.get(i));
	        	String newLine = new String("\n");
	        	finalTempFile.write(newLine.getBytes());
	        }
	        						
			ConnectionText ct = new ConnectionText();
			ct.configure(tempFile.getAbsolutePath());
			ct.connect(); 
			
			System.err.println("Reading tuples...!");
			
			while((tuple = ct.next()) != null)
			{
				for (int i = 1; i < tuple.length; i++) 
				{
					Map<String, List<String>> auxColumnsItens = columnsItens.get(i-1).get(getCubeName());
					
					if(auxColumnsItens == null)
					{
						Map<String,Map<String, List<String>> > auxMap = new TreeMap<String,Map<String, List<String>> >();
						auxColumnsItens = new TreeMap <String, List<String>>();
						auxMap.put(getCubeName(), auxColumnsItens);
						columnsItens.set(i-1, auxMap);
						
						List<String> values = new LinkedList<String>();
						auxColumnsItens.put(mapColumns.get(i), values);
					
						if (!values.contains(tuple[i])) 
							values.add(tuple[i]);
					}
					
					else
					{	
						List<String> values = auxColumnsItens.get(mapColumns.get(i));
						
						if(values == null)
						{
							values = new LinkedList<String>();
							auxColumnsItens.put(mapColumns.get(i), values);
							Map<String,Map<String, List<String>> > auxMap = new TreeMap<String,Map<String, List<String>> >();
							auxMap.put(getCubeName(), auxColumnsItens);
							columnsItens.set(i-1, auxMap);
						}
						
						if (!values.contains(tuple[i])) 
							values.add(tuple[i]);
					}
					
					String pk = tuple[0]; 
					Map<String, Set<String>> auxPartialCube = partialCube.get(i-1).get(getCubeName());
					
					if(auxPartialCube == null)
					{
						Map<String,Map<String, Set<String>> > auxMap = new TreeMap<String,Map<String, Set<String>> >();
						auxPartialCube = new TreeMap<String, Set<String>>();
						Set<String> pks = new TreeSet<String>();
						auxPartialCube.put(tuple[i], pks);
						auxMap.put(getCubeName(), auxPartialCube);
						partialCube.set(i-1, auxMap);
					
						if (!pks.contains(pk)) 
							pks.add(pk);
					}
					
					else
					{
						Set<String> pks = auxPartialCube.get(tuple[i]);
						
						if(pks == null)
						{
							pks = new TreeSet<String>();
							auxPartialCube.put(tuple[i], pks);
							Map<String,Map<String, Set<String>> > auxMap = new TreeMap<String,Map<String, Set<String>> >();
							auxMap.put(getCubeName(), auxPartialCube);
							partialCube.set(i-1,auxMap);
						}
						
						if (!pks.contains(pk)) 
							pks.add(pk);
					}
				}
																				
				tuple = null;
										
			}//end while	
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return true;
	} 
	
	public void imprime(String [] columns)
	{ 
		for(int i = 1; i < columns.length; i++)
		{
			String whichCol = columns[i];
			Map<String, List<String>> auxColumnsItens = columnsItens.get(i-1).get(getCubeName());
			System.out.println(whichCol);
			for(String a : auxColumnsItens.get(whichCol))
				System.err.print(" " + a);
			System.out.println(); 
		}	
	}
	
	@SuppressWarnings({"unused" })
	public List<String> query(String wordsQuery)
	{	
		System.out.println("Starting query...");
		Map<String, List<Filter>> filters = new TreeMap<String, List<Filter>>();
		Map<String, List<FilterMeasure>> filtersMeasures = new TreeMap<String, List<FilterMeasure>>();
		Map<String, Measure> measures = new TreeMap<String, Measure>();
		Map<String, Attribute> attributes = new TreeMap<String, Attribute>();
		List<String> result = new LinkedList<String>();
				
		String[] slicedQuery = wordsQuery.split("\\$");
						
		try
		{
			if(!slicedQuery[0].equals("null"))
			{
				String[] pivotAt = slicedQuery[0].split("\\:");
				if (pivotAt!=null)
				{
					for(int i=0; i< pivotAt.length; i++)
					{
						Filter filter = new Filter();
						filter.setName(pivotAt[i]);
						filter.setPivotTable(true);
						
						if(!slicedQuery[1].equals("null"))
						{
							String[] secondFilters = slicedQuery[1].split("\\#");
							
							if (secondFilters != null)
							{
								for(int j=0; j< secondFilters.length; j++)
								{
									String[] oneF = secondFilters[j].split("\\:");
								
									if(pivotAt[i].equals(oneF[0]))
									{
										filter.setType(oneF[1]);
										
										if(oneF.length >= 3)
											filter.setValues(oneF[2].split("\\;"));	
									}									
								}
							}
						}
						
						List<Filter> listFilter = new LinkedList<Filter>();
						listFilter.add(filter);
						filters.put(pivotAt[i], listFilter);
					}
				}
			}
			
			if(!slicedQuery[2].equals("null"))
			{
				String[] thirdFilters = slicedQuery[2].split("\\#");
				
				if (thirdFilters!=null)
				{
					for(int i=0; i< thirdFilters.length; i++)
					{
						String[] oneM = thirdFilters[i].split("\\:");
						FilterMeasure filter = new FilterMeasure();
						filter.setName(oneM[0]);
						filter.setFunction(oneM[1]);
						
						if (oneM.length >= 3)
							filter.setValues(oneM[2].split("\\;"));
						
						List<FilterMeasure> listFilter = new LinkedList<FilterMeasure>();
						listFilter.add(filter);
						filtersMeasures.put(oneM[0], listFilter);
					}
				}
			}
					
			/*
			 * Executing Query
			 */
			System.out.println("Executing query...");
			System.out.println("Filters...");
			for (String whichFilter : filters.keySet())
			{
				Attribute atttribute = new Attribute();
				
				try
				{
					List<String> values = new LinkedList<String>();
					Map<String, List<String>> auxColumnsItens = columnsItens.get(reverseColumns.get(whichFilter) - 1).get(getCubeName());
					values = auxColumnsItens.get(whichFilter);
					for(String a : values)
						System.out.print(" " + a);
					atttribute.setAttributes(whichFilter, values, filters.get(whichFilter));
					attributes.put(whichFilter, atttribute);
				
				}
				
				catch(Exception e)
				{
					System.out.println("Não foi possível localizar os valores da coluna: " + whichFilter);
				}
			}
			System.out.println();
			
			/*
			 * Executing Measures
			 */			
			System.out.println("Measures");
			for(String whichMeasure : filtersMeasures.keySet())
			{
				Measure measure = new Measure();
					
				try
				{										
					List<String> values = new LinkedList<String>();
					Map<String, List<String>> auxColumnsItens = columnsItens.get(reverseColumns.get(whichMeasure) - 1).get(getCubeName());
					values = auxColumnsItens.get(whichMeasure);
					for(String a : values)
						System.out.print(" " + a);
					measure.setMeasures(whichMeasure,values,filtersMeasures.get(whichMeasure));
					measures.put(whichMeasure, measure);
				}
				
				catch(Exception e)
				{
					
				}

			}
			System.out.println();
			
			/*
			 * Intersections and Unions
			 */																		
			for(Attribute attribute : attributes.values())
				attribute.loadTids(reverseColumns);
			
			String filen = null;
			
			for(Attribute att: attributes.values())
			{
				File tMAGOinputF = new File("../" + att.getName()+ ".txt");
				
				if(!tMAGOinputF.exists())
					tMAGOinputF.createNewFile();
				
				BufferedReader fileR;
				BufferedWriter fileW = new BufferedWriter(new FileWriter(tMAGOinputF,true));
											
				if(filen!=null)
				{
				
					for(String aux: att.getAttributes())
					{
						fileR = new BufferedReader(new FileReader("../"+filen+".txt"));
						String str = null;
						while((str = fileR.readLine())!=null)
							fileW.write(str+"$"+att.getName()+":"+aux+"\r\n");
											
						fileR.close();
						fileR = null;
					}
					
					fileW.flush();
					fileW.close();
					fileW=null;
				}
				
				else
				{
					for(String aux: att.getAttributes())
						fileW.write(att.getName()+":"+aux+"\r\n");
					
					fileW.flush();
					fileW.close();
					fileW=null;
				}
				
				filen = att.getName();
			}
									
			if(filen != null)
			{
				filen = orderFile(filen);

				File f = new File("../"+filen+".txt");
				BufferedReader fileR = new BufferedReader(new FileReader(f));
				String aux = null;
				
				Set<String> mainCache = new TreeSet<String>();
				
				while((aux = fileR.readLine()) != null)
				{
					String[] r = aux.split("\\$");
					String[][] rr = new String[r.length][];
					
					for(int i = 0; i< r.length; i++)
						rr[i] = r[i].split("\\:");
										
					int tidsMin = obtainMinTids(rr, attributes);
					
					Set<String> s = new TreeSet<String>();
					
					if(attributes.get(rr[tidsMin][0]).getTids().get(rr[tidsMin][1]) != null)
						s.addAll(attributes.get(rr[tidsMin][0]).getTids().get(rr[tidsMin][1]));
					
					String cache = "";
					
					for(int i = 0; i < r.length; i++)
					{
						cache += r[i];
						
						if(i != tidsMin)
						{
							if(!s.isEmpty() && !mainCache.contains(cache))
								s.retainAll(attributes.get(rr[i][0]).getTids().get(rr[i][1]));
							
							else mainCache.add(cache);
							
						}
					}
					
					String oneR = "";
					
					
					if(s.size()!=0)
					{						
						for(int i=0; i< r.length; i++)	
							oneR += rr[i][0]+":"+rr[i][1]+"#";	
						
						oneR = Separate.separate(oneR,"#");
												
						if(measures.size() != 0)
							oneR+= "$";	
						
						Boolean condition = false;
					
						for(String m : measures.keySet())
						{
							System.out.println("Measure" + m);
							Set<String> valuesIntersection = new TreeSet<String>();
							
							for(String aux1 : s)
								valuesIntersection.add(aux1);		
							
							System.out.println("Values intersection");
							for(String a : valuesIntersection)
								System.out.print(" " + a);
							System.out.println();
							
							Measure measure = measures.get(m);
							measure.processMeasures(valuesIntersection);
														
							condition = true;
							oneR += measure.getName() + ":" + measure.getFunction() + ":" + measure.getResult() + "#";
						}
						
						if(condition)
							oneR = Separate.separate(oneR,"#");
						
						result.add(oneR);
					}
					
					for(String r_aux : r)
						r_aux = null;
					
					for(String[] rr_aux : rr)
					{
						for(String r_aux:rr_aux)
							r_aux = null;
						rr_aux = null;
					}
					
					s.clear();
				}
				
				fileR.close();
				fileR=null;
				
				mainCache.clear();
				mainCache = null;
			}
			
			//deleting files
			File folder = new File("../"); 
			if (folder.isDirectory()) 
			{
			    File[] sun = folder.listFiles();  
			    for (File toDelete : sun) 
			        toDelete.delete();  
			} 
						
			for (List<Filter> l: filters.values())
			{
				for(Filter f: l)
					f = null;
				
				l.clear();
				l = null;
			}
			
			filters.clear();
			filters = null;
						
			measures.clear();
			measures = null;
			
			for (Attribute att: attributes.values())
			{
				att.clear();
				att = null;
			}
				
			attributes.clear();
			attributes = null;
			
			return result;
			
		}	
		
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	private String orderFile(String nameFile) throws IOException
	{
		String file = nameFile + "1";
		
		try 
		{
			BufferedReader 	fileR = new BufferedReader(new FileReader("../"+nameFile+".txt"));
			List<String> fileValues = new LinkedList<String>();
			String line = null; 
			
			while((line = fileR.readLine()) != null)
				fileValues.add(line);
			
			fileR.close();
			fileR = null;
			
			Collections.sort(fileValues);
			
			File inputF = new File("../" + file + ".txt");
			
			inputF.createNewFile();
			
			BufferedWriter fileW = new BufferedWriter(new FileWriter(inputF,true));
			
			for(String aux : fileValues)
			{
				fileW.write(aux);
				fileW.write("\n");
			}
			
			fileW.flush();
			fileW.close();
			fileW=null;
		} 
		
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return file;
	}
	
	private int obtainMinTids(String[][] rr, Map<String, Attribute> attributes)
	{
		int id = 0;
		int size = -1;
		
		for (int i=0; i<rr.length; i++)
		{
			if (i==0)
			{				
				if(attributes.get(rr[i][0]).getTids().get(rr[i][1]) != null)
					size = attributes.get(rr[i][0]).getTids().get(rr[i][1]).size();
			}
			
			else
			{
				int size1 = -1;
		
				if(attributes.get(rr[i][0]).getTids().get(rr[i][1]) != null)
					size1 = attributes.get(rr[i][0]).getTids().get(rr[i][1]).size();
				
				if (size1 < size)
				{
					id = i;
					size = size1;
				}
			}
		}
		
		return id;
	}
}