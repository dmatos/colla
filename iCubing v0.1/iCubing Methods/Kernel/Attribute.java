package Kernel;

//import interfaces.kernel.JCL_facade;
//import interfaces.kernel.JCL_result;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import Utilities.Contem;
import Utilities.Diferente;
import Utilities.Entre;
import Utilities.EntreIgual;
import Utilities.Igual;
import Utilities.Maior;
import Utilities.MaiorIgual;
import Utilities.Menor;
import Utilities.MenorIgual;

public class Attribute 
{
	private String name;
	private List<String> result;
	private Map<String, Set<String>> tids;
	private List<String> aux; 
	
	public void setAttributes (String name, List<String> values, List<Filter> filters)
	{
		this.name = name;
		
		List<List<String>> parciais = new LinkedList<List<String>>();
		
		for (int i=0; i < filters.size(); i++)
		{
			aux = new ArrayList<String>();
			Filter filter = filters.get(i);
									
			if(filter.getType() == null)
				aux.addAll(values);
			
			else
			{
				Boolean sentinel = true;
				
				if(filter.getType().equals("maior"))
				{
					List<String> l = Maior.execute(values, filter.getValues()[0]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("menor"))
				{
					List<String> l = Menor.execute(values, filter.getValues()[0]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("entre"))
				{
					List<String> l = Entre.execute(values, filter.getValues()[0], filter.getValues()[1]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("maior_igual"))
				{
					List<String> l = MaiorIgual.execute(values, filter.getValues()[0]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("menor_igual"))
				{
					List<String> l = MenorIgual.execute(values, filter.getValues()[0]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("entre_igual"))
				{
					List<String> l = EntreIgual.execute(values, filter.getValues()[0], filter.getValues()[1]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("igual"))
				{
					List<String> l = Igual.execute(values, filter.getValues()[0]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("diferente"))
				{
					List<String> l = Diferente.execute(values, filter.getValues()[0]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("contem"))
				{
					List<String> l = Contem.execute(values, filter.getValues()[0]);
					aux.addAll(l);
					sentinel = false;
				}
								
				if(sentinel)
					System.err.println("Filtro Inválido");
			}
			
			if(aux != null)
				parciais.add(aux);
		}
		
		List<String> intersection = new ArrayList<String>();
		
		intersection.addAll(parciais.get(0));
		
		for(int i = 1; i < parciais.size(); i++)
			intersection.retainAll(parciais.get(i));
			
		if(result == null) 
			result = new LinkedList<String>();
		
		result.add("all");
		result.addAll(intersection);
				
		parciais.clear();
		parciais = null;
	}
	
	public List<String> getAttributes()
	{
		return this.result;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void loadTids(Map<String, Integer> reverseColumns)
	{
		tids = new TreeMap<String, Set<String>>();
		Set<String> all = new TreeSet<String>();
						
		for(String aux: result)
		{
			if(aux != "all")
			{				
				try
				{					
					Set<String> pks = new TreeSet<String>();
					Map<String, Set<String>> auxPartialCube = Worker.partialCube.get(reverseColumns.get(getName()) - 1).get(Worker.getCubeName());
					pks = auxPartialCube.get(aux);
					
					tids.put(aux,pks);
					all.addAll(pks);						
				}
				
				catch(Exception e)
				{
					System.out.println("Não foi possível locarlizar o valor: " + aux);
				}
			}
		}								
		tids.put("all", all);
	} 
	
	public Map<String, Set<String>> getTids()
	{
		return tids;
	}

	public void clear()
	{
		result.clear();
		result = null;
				
		tids.clear();
		tids=null;
	}
}

