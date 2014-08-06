package Utilities;

import java.util.List;

public class Avg 
{
	public static String execute(List<String> auxValues)
	{
		String sum = Sum.execute(auxValues);
		String count = Count.execute(auxValues);
		String aux = sum + ";" + count;		
		return aux;
	}
}
