package Utilities;

import java.util.List;

public class Min 
{
	public static String execute(List<String> auxValues)
	{
		String min = auxValues.get(0);
		for(int i = 1; i < auxValues.size(); i++)
		{
			if(Double.parseDouble(auxValues.get(i)) < Double.parseDouble(min))
				min = auxValues.get(i);
		}
		
		return min;
	}
}
