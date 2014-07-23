package Kernel;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Kernel.Worker;
import Utilities.Avg;
import Utilities.Count;
import Utilities.Max;
import Utilities.Min;
import Utilities.Sum;

public class Measure 
{
	private String function;
	private List<String> values;
	private String name;
	List<FilterMeasure> measures;
	private String result = null;
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getFunction() 
	{
		return function;
	}
	
	public void setFunction(String function)
	{
		this.function = function;
	}
	
	public List<String> getValues()
	{
		return values;
	}
	
	public void setValues(List<String> values)
	{
		this.values = values;
	}
	
	public void processMeasures(Set<String> pks)
	{		
				
		for (int i=0; i < measures.size(); i++)
		{
			FilterMeasure filter = measures.get(i);
												
			if(filter.getFunction() != null)
			{
				Boolean sentinel = true;
				
				if(filter.getFunction().equals("Count"))
				{
					this.setFunction("count");
					List<String> finalValues = filterValues(pks);
					System.out.println("Final values");
					for(String a : finalValues)
						System.out.print(" " + a);
					System.out.println();
					result = Count.execute(finalValues);
					sentinel = false;
				}
				
				if(filter.getFunction().equals("Min"))
				{
					this.setFunction("min");
					List<String> finalValues = filterValues(pks);
					System.out.println("Final values");
					for(String a : finalValues)
						System.out.print("" + a);
					System.out.println();
					if(finalValues != null)
						result = Min.execute(finalValues);
					sentinel = false;
				}
				
				if(filter.getFunction().equals("Max"))
				{
					this.setFunction("max");
					List<String> finalValues = filterValues(pks);
					result = Max.execute(finalValues);
					sentinel = false;
				}
				
				if(filter.getFunction().equals("Average"))
				{
					this.setFunction("avg");
					List<String> finalValues = filterValues(pks);
					result = Avg.execute(finalValues);
					sentinel = false;
				}
				
				if(filter.getFunction().equals("Sum"))
				{
					this.setFunction("sum");
					List<String> finalValues = filterValues(pks);
					result = Sum.execute(finalValues);
					sentinel = false;
				}
								
				if(sentinel)
					System.err.println("Filtro Inválido");
			}
		}
		
	}

	public List<String> filterValues(Set<String> orginalTIDs)
	{
		List<String> finalValues = new LinkedList<String>();
		
		System.out.println("Values");
		for(String aux : values)
		{
			System.out.println(aux);
			Map<String, Set<String>> auxPartialCube = Worker.partialCube.get(Worker.reverseColumns.get(getName()) - 1).get(Worker.getCubeName());
			Set<String> tIDs = auxPartialCube.get(aux);
			System.out.println("Tids");
			for(String a : tIDs)
				System.out.print(" " + a);
			System.out.println();
			for(String eachtID : tIDs)
			{
				if(orginalTIDs.contains(eachtID))
					finalValues.add(aux);
			}
		}
		System.out.println();
		
		return finalValues;
	}
	
	public void setMeasures(String whichMeasures, List<String> values, List<FilterMeasure> measures)
	{
		this.setName(whichMeasures);
		this.values = values;
		this.measures = measures;		
	}

	public String getResult()
	{
		return result;
	}
}
