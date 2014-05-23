package Utilities;

import java.util.Set;
import java.util.TreeSet;

public class Diferente
{
	public static Set<String> execute(Set<String> keys, String key)
	{
		Set<String> which = new TreeSet<String>();
		for(String aux:keys)
		{
			if(!aux.equalsIgnoreCase(key))
				which.add(aux);
		}
		
		return which;
	}
}

