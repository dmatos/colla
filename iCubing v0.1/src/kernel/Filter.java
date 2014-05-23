package kernel;

public class Filter 
{	
	private boolean pivotTable;
	private String type;
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
	
	public boolean isPivotTable() 
	{
		return pivotTable;
	}
	
	public void setPivotTable(boolean pivotTable)
	{
		this.pivotTable = pivotTable;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
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
