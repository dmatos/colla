package Utilities;

import java.util.List;

public class Sum 
{
	public static String execute(List<String> auxValues)
	{
		Double sum =  0.0;
		for(int i = 0; i < auxValues.size(); i++)
			sum += Double.parseDouble(auxValues.get(i));
		
		String aux = "" + sum;
		
		return aux;
	}
}
