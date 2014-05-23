package kernel;

public class Measure 
{
	private String function;
	private String[] values;
	private String name;
	
	
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
	
	public String[] getValues()
	{
		return values;
	}
	
	public void setValues(String[] values)
	{
		this.values = values;
	}
}
