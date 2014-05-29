package kernel;

import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;

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
	JCL_result jclr = null;
	JCL_facade javaCaLa = JCL_FacadeImpl.getInstance();
	
	public void setAttributes (String name, Set<String> values, List<Filter> filters)
	{
		this.name = name;
		
		/*System.out.println(name);
		
		for(String aux : values)
			System.out.print(" " + aux);
		System.out.println(); */
			
		// maior, menor, entre, maior_igual, menor_igual, 
		//entre_igual, igual, diferente, contem, similar.
		
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
					Set<String> l = Maior.execute(values, filter.getValues()[0]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("menor"))
				{
					Set<String> l = Menor.execute(values, filter.getValues()[0]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("entre"))
				{
					Set<String> l = Entre.execute(values, filter.getValues()[0], filter.getValues()[1]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("maior_igual"))
				{
					Set<String> l = MaiorIgual.execute(values, filter.getValues()[0]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("menor_igual"))
				{
					Set<String> l = MenorIgual.execute(values, filter.getValues()[0]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("entre_igual"))
				{
					Set<String> l = EntreIgual.execute(values, filter.getValues()[0], filter.getValues()[1]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("igual"))
				{
					Set<String> l = Igual.execute(values, filter.getValues()[0]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("diferente"))
				{
					Set<String> l = Diferente.execute(values, filter.getValues()[0]);
					aux.addAll(l);
					sentinel = false;
				}
				
				if(filter.getType().equals("contem"))
				{
					Set<String> l = Contem.execute(values, filter.getValues()[0]);
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
	
	@SuppressWarnings("unchecked")
	public void loadTids()
	{
		tids = new TreeMap<String, Set<String>>();
		Set<String> all = new TreeSet<String>();
				
		for(String aux: result)
		{
			if(aux != "all")
			{
				JCL_result jclr = null;
				jclr = javaCaLa.getValue(aux);
				
				Set<String> pks = new TreeSet<String>();
					
				if(jclr.getErrorResult() == null)
				{
					pks = (Set<String>) jclr.getCorrectResult();
					
					tids.put(aux,pks);
					all.addAll(pks);
				}
				
				else
					jclr.getErrorResult().printStackTrace();
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
		result=null;
				
		tids.clear();
		tids=null;
	}
}

