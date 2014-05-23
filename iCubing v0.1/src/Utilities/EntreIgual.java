package Utilities;

import java.util.Set;
import java.util.TreeSet;

public class EntreIgual 
{

	public static Set<String> execute(Set<String> keys, String key, String key1)
	{
		Set<String> which = new TreeSet<String>();
		for(String aux:keys)
		{
			if(Double.parseDouble(key)<=Double.parseDouble(aux))
				if (Double.parseDouble(aux)<=Double.parseDouble(key1))
					which.add(aux);
		}
		
		return which;
	}
}