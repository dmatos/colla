package Utilities;

import java.util.List;

public class Max 
{
	public static String execute(List<String> auxValues)
	{
		String max = auxValues.get(0);
		for(int i = 1; i < auxValues.size(); i++)
		{
			if(Double.parseDouble(auxValues.get(i)) > Double.parseDouble(max))
				max = auxValues.get(i);
		}
		
		return max;
	}
}
