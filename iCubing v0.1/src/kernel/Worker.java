package kernel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

import kernel.Attribute;
import kernel.Measure;
import kernel.Filter;
import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;
import drivers.ConnectionText;

public class Worker 
{
	static List <String> keys = new LinkedList<String>();
	private FileOutputStream finalTempFile;
	private JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();

	@SuppressWarnings("unchecked")
	public Boolean index(String fileName, int numofColumns,Map<Integer,String> mapColumns,TreeSet<Integer> measures,ArrayList<byte[]> byteList) throws IOException
	{
		System.err.println("Reading tuples...!");
		try
		{
			String[] tuple = null;
			
			Map<String, Set<String> >[] partialCube = new HashMap[numofColumns];
			HashMap<String, List<Double> > partialMeasure;
			
			for(int i = 0; i < numofColumns; i++)
				partialCube[i] = new HashMap<String, Set<String> >();
				
			partialMeasure = new HashMap<String, List<Double>>();
			
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
			
			while((tuple = ct.next()) != null)
			{				
				for (int i = 1; i < tuple.length; i++) 
				{
					if(measures.contains(i)) // se for measure
					{					
						List<Double> setMeasures =  partialMeasure.get(new Long(tuple[0]));
						
						if(setMeasures == null) 
						{
							setMeasures= new LinkedList<Double>();//cria um set para as measures de uma linha
							partialMeasure.put(tuple[0], setMeasures);
						}
						
						setMeasures.add(new Double(tuple[i])); 								
						
					}
					
					else
					{
						String pk = tuple[0]; 
						Set<String> pks = partialCube[i-1].get(tuple[i]);
						
						if (pks == null) 
						{
							pks = new TreeSet<String>();
							partialCube[i-1].put(tuple[i], pks);
						}
						
						if (!pks.contains(pk)) 
							pks.add(pk);
					}
												
				}//end for
				
				tuple = null;
										
			}//end while	
						
			System.err.println("Creating global vars...");
			
			JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();
								
			for(int i = 1; i < numofColumns; i++)
			{
				Set<String> keysColumn = new TreeSet<String>();
				for(String key : partialCube[i-1].keySet())
				{
					keys.add(key);
					keysColumn.add(key);
					
					if(javaCaLa.instantiateGlobalVar(key, partialCube[i-1].get(key)) == false)
					{
						JCL_result jclr = javaCaLa.getValueLocking(key);
						Set<String> pks = (Set<String>) jclr.getCorrectResult();
						if(pks != null)
						{
							pks.addAll(partialCube[i-1].get(key));
							javaCaLa.setValue(key, pks);
						}
					}
				}
				
				String whichCol = mapColumns.get(i);
				
				if(javaCaLa.instantiateGlobalVar(whichCol, keysColumn) == false)
				{
					JCL_result jclr = javaCaLa.getValueLocking(whichCol);
					Set<String> pks = (Set<String>) jclr.getCorrectResult();
					pks.addAll(keysColumn);
					javaCaLa.setValue(whichCol, pks);
				}
				
				keysColumn.clear();
				keysColumn = null;
			}			
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return true;
	} 
	
	public void imprime()
	{
		for(int i = 1; i <= 5; i++)
		{
			String var = "n" + i;
			JCL_result jclr = null;
			jclr = javaCaLa.getValue(var);
			if(jclr.getErrorResult() == null)
			{
				@SuppressWarnings("unchecked")
				Set<String> pks = (Set<String>) jclr.getCorrectResult();
				System.out.println("Coluna " + i + ":");
				for(String a : pks)
					System.out.print(" " + a);
				System.out.println();
			}
		}
		
		//System.out.println("Imprimindo...");
		
		if(keys != null)
			Collections.sort(keys);
		
		for(String e : keys)
		{
			System.out.println(e);
			JCL_result jclr = null;
			jclr = javaCaLa.getValue(e);
				
			if(jclr.getErrorResult() == null)
			{
				@SuppressWarnings("unchecked")
				Set<String> pks = (Set<String>) jclr.getCorrectResult();
				
				for(String a : pks)
					System.out.print(" " + a);
				System.out.println();
			}
		}		
	}
	
	@SuppressWarnings("unchecked")
	public Set<String> query(String wordsQuery)
	{		
		System.out.println("Preparing Query...");
		
		Map<String, List<Filter>> filters = new TreeMap<String, List<Filter>>();
		Map<String, List<Measure>> measures = new TreeMap<String, List<Measure>>();
		Map<String, Attribute> attributes = new TreeMap<String, Attribute>();
		Set<String> result = new TreeSet<String>();
		
		String[] slicedQuery = wordsQuery.split("\\$");
				
		try
		{
			if(slicedQuery[0] != null)
			{
				String[] pivotAt = slicedQuery[0].split("\\:");
				if (pivotAt!=null)
				{
					for(int i=0; i< pivotAt.length; i++)
					{
						Filter filter = new Filter();
						filter.setName(pivotAt[i]);
						filter.setPivotTable(true);
						
						if(slicedQuery[1] != null)
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
			
			if(slicedQuery[2] != null)
			{
				String[] thirdFilters = slicedQuery[2].split("\\#");
				
				if (thirdFilters!=null)
				{
					for(int i=0; i< thirdFilters.length; i++)
					{
						String[] oneM = thirdFilters[i].split("\\:");
						Measure measure = new Measure();
						measure.setFunction(oneM[1]);
						measure.setName(oneM[0]);
						
						if (oneM.length>=3)
							measure.setValues(oneM[2].split("\\;"));
						
						List<Measure> listMeasure = measures.get(oneM[0]);
						
						if (listMeasure == null)
						{
							listMeasure = new LinkedList<Measure>();
							measures.put(oneM[0], listMeasure);
						}
						
						listMeasure.add(measure);
					}
				}
			}
					
			//executando query
			for (String whichFilter : filters.keySet())
			{
				Attribute atttribute = new Attribute();
				
				JCL_result jclr = null;
				
				Set<String> values = new TreeSet<String>();
				
				jclr = javaCaLa.getValue(whichFilter);
					
				if(jclr.getErrorResult() == null)
				{
					values = (Set<String>) jclr.getCorrectResult();
					atttribute.setAttributes(whichFilter, values, filters.get(whichFilter));
					attributes.put(whichFilter, atttribute);				
				}
				
				else
					jclr.getErrorResult().printStackTrace();
			}
			
			//final...intersecoes e unioes
																		
			for(Attribute attribute : attributes.values())
				attribute.loadTids();
			
			String partialResult = new String("");
			
			for(Attribute attribute : attributes.values())
			{
				partialResult = attribute.getName();
				
				for(String aux : attribute.getAttributes())
				{
					partialResult += " ";
					partialResult += aux;
				}
				
				result.add(partialResult);
			}
			
			String filen = null;
			for(Attribute att:attributes.values())
			{
				File tMAGOinputF = new File("/home/joaovq/workspace/iCubing/iCubing v0.1/lib/"+att.getName()+".txt");
				
				if(!tMAGOinputF.exists())
					tMAGOinputF.createNewFile();
				
				BufferedReader fileR;
				BufferedWriter fileW = new BufferedWriter(new FileWriter(tMAGOinputF,true));
											
				if(filen!=null)
				{
				
					for(String aux: att.getAttributes()){
						fileR = new BufferedReader(new FileReader("/home/joaovq/workspace/iCubing/iCubing v0.1/lib/"+filen+".txt"));
						String str = null;
						while((str = fileR.readLine())!=null)
							fileW.write(str+"$"+att.getName()+":"+aux+"\r\n");
											
						fileR.close();
						fileR = null;
					}
					fileW.flush();
					fileW.close();
					fileW=null;
					File f = new File("../"+filen+".txt");
					f.delete();
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
			
			//uashuahsuah
			if(filen!=null)
			{
				File f = new File("/home/joaovq/workspace/iCubing/iCubing v0.1/lib/"+filen+".txt");
				BufferedReader fileR= new BufferedReader(new FileReader(f));
				String aux=null;
				
				long time1= System.nanoTime();
				Set<String> mainCache = new TreeSet<String>();
				
				while((aux = fileR.readLine()) != null)
				{
					String[] r = aux.split("\\$");
					String[][] rr = new String[r.length][];
					
					for(int i = 0; i< r.length; i++)
						rr[i] = r[i].split("\\:");
					
					int tidsMin = obtainMinTids(rr, attributes);
					
					Set<String> s = new TreeSet<String>();
					
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
						for(int i=0; i<r.length; i++)
						{							
							oneR += rr[i][0]+":"+rr[i][1]+"$";							
						}
						
						oneR += "evento:" +s.size();
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
				
				//System.err.println("tempo intersecoes ..... " + (System.nanoTime()-time1));
				fileR.close();
				fileR=null;
				
				mainCache.clear();
				mainCache = null;
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
			
			for (List<Measure> ms: measures.values())
			{
				for(Measure m : ms)
					m = null;
				
				ms.clear();
				ms=null;
			}
			
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
	
	private int obtainMinTids(String[][] rr, Map<String, Attribute> attributes)
	{
		int id = 0;
		int size = -1;
		for (int i=0; i<rr.length; i++)
		{
			if (i==0)
			{
				size = attributes.get(rr[i][0]).getTids().get(rr[i][1]).size();
			}
			
			else
			{
				int size1 = attributes.get(rr[i][0]).getTids().get(rr[i][1]).size();
				if (size1<size)
				{
					id = i;
					size = size1;
				}
			}
		}
		
		return id;
	}
}
