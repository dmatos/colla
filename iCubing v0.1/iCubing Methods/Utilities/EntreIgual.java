package Utilities;

import java.util.LinkedList;
import java.util.List;

public class EntreIgual 
{

	public static List<String> execute(List<String> keys, String key, String key1)
	{
		List<String> which = new LinkedList<String>();
		for(String aux:keys)
		{
			if(Double.parseDouble(key)<=Double.parseDouble(aux))
				if (Double.parseDouble(aux)<=Double.parseDouble(key1))
					which.add(aux);
		}
		
		return which;
	}
}