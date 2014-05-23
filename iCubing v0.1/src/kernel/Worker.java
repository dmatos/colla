package kernel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
					if(pks != null)
					{
						pks.addAll(keysColumn);
						javaCaLa.setValue(whichCol, pks);
					}
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
		
		/*if(keys != null)
			Collections.sort(keys);
		
		for(String e : keys)
		{
			System.out.println(e);
			JCL_result jclr = null;
			jclr = javaCaLa.getValue(e);
				
			if(jclr.getErrorResult() == null)
			{
				@SuppressWarnings("unchecked")
				Set<Long> pks = (Set<Long>) jclr.getCorrectResult();
				
				for(Long a : pks)
					System.out.print(" " + a);
				System.out.println();
			}
		}		*/
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public Map<String, Set<String>> query(String wordsQuery)
	{		
		System.out.println("Preparing Query...");
		
		Map<String, List<Filter>> filters = new TreeMap<String, List<Filter>>();
		Map<String, List<Measure>> measures = new TreeMap<String, List<Measure>>();
		Map<String, Attribute> attributes = new TreeMap<String, Attribute>();
		Map<String, Set<String>> result = new TreeMap<String, Set<String>>();
		
		String[] slicedQuery = wordsQuery.split("\\$");
		
		Set<String> interPks = null;
		
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
				
				Set<String> pks = new TreeSet<String>();
				
				jclr = javaCaLa.getValue(whichFilter);
					
				if(jclr.getErrorResult() == null)
				{
					pks = (Set<String>) jclr.getCorrectResult();
					atttribute.setAttributes(whichFilter, pks, filters.get(whichFilter));
					attributes.put(whichFilter, atttribute);				
				}
			}
			
			//final...intersecoes e unioes
			
			System.out.println();
			System.out.println("Interseção");
			
			String ticket = "resultIntersection";
									
			for(Attribute attribute : attributes.values())
				attribute.loadTids(ticket);
			
			System.out.println("Ok");
						
			Attribute attribute = new Attribute();
			
			result = attribute.getTids(ticket);
		}	
		
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		return result;
	}
}
